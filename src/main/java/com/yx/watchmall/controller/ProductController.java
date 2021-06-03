package com.yx.watchmall.controller;

import com.yx.watchmall.mallConst.MallConst;
import com.yx.watchmall.service.ProductService;
import com.yx.watchmall.vo.ProductConciseVo;
import com.yx.watchmall.vo.ProductDetailVo;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService service) {
        this.productService = service;
    }

    @GetMapping("/products")
    public ResponseVo<Page<ProductConciseVo>> listAllProducts(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                                                              @RequestParam(value = "features", required = false,defaultValue = MallConst.PARAMETER_PLACEHOLDER) String features,
                                                              @RequestParam(value = "dialColor", required = false,defaultValue = MallConst.PARAMETER_PLACEHOLDER) String dialColor,
                                                              @RequestParam(value = "size",required = false,defaultValue = MallConst.PARAMETER_PLACEHOLDER) String size,
                                                              @RequestParam(value = "pageNum",required = false,defaultValue = "0") Integer pageNum,
                                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        return productService.listAllProducts(categoryId,features,dialColor,size,pageNum,pageSize);
    }

    @GetMapping("/product/{id}")
    public ResponseVo<ProductDetailVo> listProductDetail(@PathVariable(value = "id") Integer productId) {
        return productService.listProductDetail(productId);
    }
}
