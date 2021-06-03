package com.yx.watchmall.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    CANCELED(0,"CANCELED"),
    NO_PAY(10,"NEED PAYMENT"),
    PAID(20,"PAID"),
    SHIPPED(40,"SHIPPED"),
    SUCCESS(50,"SUCCESS"),
    CLOSED(60,"CLOSED");

    Integer code;
    String msg;

    OrderStatusEnum(Integer _code, String _msg) {
        code = _code;
        msg = _msg;
    }
}
