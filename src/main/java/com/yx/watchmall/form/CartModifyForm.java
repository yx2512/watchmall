package com.yx.watchmall.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CartModifyForm {
    @Positive
    private Integer quantity;
    @NotNull
    private Boolean selected;
}
