package com.yx.watchmall.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {
    RESOURCE_NOT_FOUND(-1,"RESOURCE NOT FOUND"),
    INSUFFICIENT_PERMISSION(1,"INSUFFICIENT PERMISSION"),
    PARAM_ERROR(2,"INVALID REQUEST PARAM"),

    LOGIN_FAILED(10,"INVALID EMAIL OR PASSWORD"),
    USER_EXISTS(11,"USER EXISTS"),
    NO_CURRENTUSER(12,"PLEASE LOGIN FIRST"),

    NO_SUCH_CATEGORY(20,"NO SUCH CATEGORY"),

    NO_CORRESPONDING_SHIPPING(30,"NO CORRESPONDING SHIPPING ADDRESS"),

    NO_SUCH_PRODUCT(40,"NO SUCH PRODUCT"),
    PRODUCT_UNAVAILABLE(41,"PRODUCT_UNAVAILABLE"),
    LIMITED_STOCK(42,"LIMITED STOCK"),

    NO_SUCH_PRODUCT_IN_CART(50,"NO SUCH PRODUCT IN CART"),
    CART_SELECTED_EMPTY(51,"NOTHING IS SELECTED IN CART"),

    NO_SUCH_ORDER(60,"NO SUCH ORDER"),
    ORDER_STATUS_ERROR(61,"ORDER STATUS ERROR"),

    PAYMENT_ERROR(70,"PAYMENT ERROR");



    Integer code;
    String msg;

    ResponseEnum(Integer _code, String _msg) {
        code = _code;
        msg = _msg;
    }
}
