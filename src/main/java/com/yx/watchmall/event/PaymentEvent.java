package com.yx.watchmall.event;

import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentEvent extends ApplicationEvent {
    private Integer id;
    private Integer userId;
    private Long orderNo;
    private Integer platform;
    private String platformNum;
    private String platformStatus;
    private BigDecimal amount;
    private Date createTime;
    private Date updateTime;

    public PaymentEvent(Object source, Long _orderNo, Integer _platform, String _platformStatus, BigDecimal _amount) {
        super(source);
        orderNo = _orderNo;
        platform = _platform;
        platformStatus = _platformStatus;
        amount = _amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getPlatformNum() {
        return platformNum;
    }

    public void setPlatformNum(String platformNum) {
        this.platformNum = platformNum;
    }

    public String getPlatformStatus() {
        return platformStatus;
    }

    public void setPlatformStatus(String platformStatus) {
        this.platformStatus = platformStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderNo=" + orderNo +
                ", platform=" + platform +
                ", platformNum='" + platformNum + '\'' +
                ", platformStatus='" + platformStatus + '\'' +
                ", amount=" + amount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
