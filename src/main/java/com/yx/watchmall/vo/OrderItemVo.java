package com.yx.watchmall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderItemVo {
    private Long orderNum;
    private Integer productId;
    private String description;
    private String mainImg;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal totalPrice;
}
