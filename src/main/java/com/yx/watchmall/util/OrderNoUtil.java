package com.yx.watchmall.util;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class OrderNoUtil {

    private static final Pattern HOSTNAME = Pattern.compile("^.*\\D+([0-9]+)$");
    private static final long OFFSET = LocalDate.of(2020,1,1).atStartOfDay(ZoneId.of("Z")).toEpochSecond();
    private static final long MAX_NEXT = 0b11111_11111111_111L;
    private static final long SHARD_ID = getServerIdAsLong();
    private static long offset = 0;
    private static long lastEpoch = 0;

    public static long nextOrderNo() {
        return nextOrderNo(System.currentTimeMillis()/1000);
    }

    public static synchronized long nextOrderNo(long epochSecond) {
        if(epochSecond < lastEpoch) {
            log.warn("clock is turning back");
            epochSecond = lastEpoch;
        }

        if(epochSecond != lastEpoch) {
            lastEpoch = epochSecond;
            reset();
        }

        offset ++;

        long next = offset & MAX_NEXT;

        if(next == 0) {
            log.warn("maximum number of order num reached in one second, current epoch: " + epochSecond);
            return nextOrderNo(epochSecond + 1);
        }

        return generateOrderNum(epochSecond,next);
    }

    private static long generateOrderNum(long epochSecond, long next) {
        return ((epochSecond - OFFSET) << 21) | (next << 5) | OrderNoUtil.SHARD_ID;
    }

    private static void reset() {
        offset = 0;
    }

    private static long getServerIdAsLong() {
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            Matcher matcher = HOSTNAME.matcher(hostName);
            if(matcher.matches()) {
                long n = Long.parseLong(matcher.group(1));
                if(n >= 0 && n<32) {
                    log.info("detect server id from host name {} : {}",hostName,n);
                    return n;
                }
            }
        } catch (UnknownHostException e) {
            log.warn("Unable to get host name. Set server id to 0.");
        }
        return 0;
    }
}
