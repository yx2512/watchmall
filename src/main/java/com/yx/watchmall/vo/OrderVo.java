package com.yx.watchmall.vo;

import com.yx.watchmall.pojo.Shipment;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderVo {
    private Long orderNum;
    private BigDecimal payment;
    private Integer postage;
    private Date paymentTime;
    private Date shippedTime;
    private Date receivedTime;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private Date closedTime;

    List<OrderItemVo> orderItemVoList;
    private Integer shippingId;
    private Shipment shipping;

}
