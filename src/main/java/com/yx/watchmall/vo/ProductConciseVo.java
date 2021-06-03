package com.yx.watchmall.vo;

import lombok.Data;

@Data
public class ProductConciseVo {
    private Integer Id;
    private Integer category;
    private String serialNum;
    private Double price;
    private String mainImg;
    private Integer dialColor;
    private String description;
}
