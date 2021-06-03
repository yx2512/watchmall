package com.yx.watchmall.controller;

import com.yx.watchmall.service.CategoryService;
import com.yx.watchmall.vo.CategoryVo;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService service) {
        categoryService = service;
    }

    @GetMapping("/categories")
    public ResponseVo<List<CategoryVo>> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/category/{id}")
    public ResponseVo<CategoryVo> findCategoryById(@PathVariable("id") Integer categoryId) {
        return categoryService.findCategoryById(categoryId);
    }
}
