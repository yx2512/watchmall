package com.yx.watchmall.pojo;

import lombok.Data;

@Data
public class CartProduct {
    private Integer productId;
    private Integer quantity;
    private Boolean selected;

    public CartProduct(Integer productId, Integer quantity, Boolean selected) {
        this.productId = productId;
        this.quantity = quantity;
        this.selected = selected;
    }

    public CartProduct() {
    }
}
