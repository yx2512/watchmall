package com.yx.watchmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ShipmentForm {
    @NotBlank
    String receiverName;
    @NotBlank
    String receiverMobile;
    @NotBlank
    String receiverState;
    @NotBlank
    String receiverCity;
    @NotBlank
    String receiverAddr;
    @NotBlank
    String receiverApt;
    @NotBlank
    String receiverZip;
}
