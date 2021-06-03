package com.yx.watchmall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartVo {

    private List<CartProductVo> cartProductVoList;
    private Boolean selectAll;
    private BigDecimal totalPrice;
    private Integer totalQuantity;
}
