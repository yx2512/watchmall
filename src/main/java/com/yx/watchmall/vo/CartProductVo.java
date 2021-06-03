package com.yx.watchmall.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartProductVo {
    private Integer productId;
    private Integer quantity;
    private String description;
    private String mainImg;
    private BigDecimal price;
    private Integer status;

    private BigDecimal totalPrice;
    private Boolean selected;
}
