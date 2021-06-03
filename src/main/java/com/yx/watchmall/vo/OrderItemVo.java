package com.yx.watchmall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderItemVo {
    private Long orderNum;
    private Integer productId;
    private String productDesc;
    private String mainImg;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Date createTime;
}
