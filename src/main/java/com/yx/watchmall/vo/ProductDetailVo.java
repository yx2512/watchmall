package com.yx.watchmall.vo;

import lombok.Data;

@Data
public class ProductDetailVo {
    private Integer Id;
    private Integer category;
    private String serialNum;
    private Double price;
    private String mainImg;
    private String subImg;
    private Double size;
    private Integer dialColor;
    private String movement;
    private String description;
    private Integer status;
    private Integer stock;
}
