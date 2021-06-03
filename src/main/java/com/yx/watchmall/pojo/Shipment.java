package com.yx.watchmall.pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "mall_shipping")
public class Shipment {
    private Integer id;
    private Integer userId;
    private String receiverName;
    private String receiverMobile;
    private String receiverState;
    private String receiverCity;
    private String receiverAddr;
    private String receiverApt;
    private String receiverZip;
    private Date createTime;
    private Date updateTime;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(nullable = false, length = 20)
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Column(nullable = false, length = 20)
    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    @Column(nullable = false,length = 20)
    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    @Column(nullable = false,length = 20)
    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    @Column(nullable = false)
    public String getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(String receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    @Column(length = 20)
    public String getReceiverApt() {
        return receiverApt;
    }

    public void setReceiverApt(String receiverApt) {
        this.receiverApt = receiverApt;
    }

    @Column(nullable = false,length = 5)
    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
    }
    @Column
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Column
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "id=" + id +
                ", userId=" + userId +
                ", receiverName='" + receiverName + '\'' +
                ", receiverMobile='" + receiverMobile + '\'' +
                ", receiverState='" + receiverState + '\'' +
                ", receiverCity='" + receiverCity + '\'' +
                ", receiverAddr='" + receiverAddr + '\'' +
                ", receiverApt='" + receiverApt + '\'' +
                ", receiverZip='" + receiverZip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipment shipment = (Shipment) o;
        return id.equals(shipment.id) && userId.equals(shipment.userId) && receiverName.equals(shipment.receiverName) && receiverMobile.equals(shipment.receiverMobile) && receiverState.equals(shipment.receiverState) && receiverCity.equals(shipment.receiverCity) && receiverAddr.equals(shipment.receiverAddr) && Objects.equals(receiverApt, shipment.receiverApt) && receiverZip.equals(shipment.receiverZip) && Objects.equals(createTime, shipment.createTime) && Objects.equals(updateTime, shipment.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, receiverName, receiverMobile, receiverState, receiverCity, receiverAddr, receiverApt, receiverZip, createTime, updateTime);
    }
}
