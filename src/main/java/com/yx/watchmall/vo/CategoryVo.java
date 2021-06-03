package com.yx.watchmall.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryVo {
    Integer Id;
    Integer parentId;
    String category;
    Integer sortOrder;
    List<CategoryVo> subCategories;
}
