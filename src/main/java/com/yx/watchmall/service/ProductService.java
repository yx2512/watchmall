package com.yx.watchmall.service;

import com.yx.watchmall.enums.ResponseEnum;
import com.yx.watchmall.mallConst.MallConst;
import com.yx.watchmall.pojo.Product;
import com.yx.watchmall.repository.ProductRepository;
import com.yx.watchmall.vo.ProductConciseVo;
import com.yx.watchmall.vo.ProductDetailVo;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryService categoryService;

    @Autowired
    public void setProductRepository(ProductRepository repository) {
        this.productRepository = repository;
    }

    @Autowired
    public void setCategoryService(CategoryService service) {
        this.categoryService = service;
    }

    public ResponseVo<Page<ProductConciseVo>> listAllProducts(Integer categoryId, String features, String dialColor, String size, Integer pageNum, Integer pageSize) {
        final Pageable pageRequest = PageRequest.of(pageNum, pageSize);
        Page<Product> products = null;
        Set<Integer> categoryIds = null;
        Set<Integer> featureSet = string2IntegerSet(features);
        Set<Integer> dialColorSet = string2IntegerSet(dialColor);
        Set<Double> sizeSet = string2DoubleSet(size);
        if(categoryId != null) {
            categoryIds = categoryService.findSubcategoryId(categoryId);
        } else {
            categoryIds = new HashSet<>();
            categoryIds.add(-1);
        }
        products = productRepository.findAllByAttributes(categoryIds,featureSet,dialColorSet,sizeSet,MallConst.NORMAL_STATUS,pageRequest);
        final Page<ProductConciseVo> conciseVos = products.map(this::product2ProductConciseVo);
        return ResponseVo.success(conciseVos);
    }

    public ResponseVo<ProductDetailVo> listProductDetail(Integer productId) {
        final Optional<Product> optionalProduct = productRepository.findByIdAndStatus(productId, MallConst.NORMAL_STATUS);
        if(optionalProduct.isEmpty()) {
            return ResponseVo.error(ResponseEnum.NO_SUCH_PRODUCT);
        }

        final Product product = optionalProduct.get();
        return ResponseVo.success(product2ProductDetailVo(product));
    }

    private ProductConciseVo product2ProductConciseVo(Product product) {
        ProductConciseVo productConciseVo = new ProductConciseVo();
        BeanUtils.copyProperties(product,productConciseVo);
        return productConciseVo;
    }

    private ProductDetailVo product2ProductDetailVo(Product product) {
        final ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product,productDetailVo);
        return productDetailVo;
    }

    private Set<Integer> string2IntegerSet(String str) {
        if(str == null || str.length() == 0) return null;

        final String[] strings = str.split("_");
        Set<Integer> resultSet = new HashSet<>(strings.length);
        for(String item : strings) {
            final int val = Integer.parseInt(item);
            resultSet.add(val);
        }

        return resultSet;
    }

    private Set<Double> string2DoubleSet(String str) {
        if(str == null || str.length() == 0) return null;

        final String[] strings = str.split("_");
        Set<Double> resultSet = new HashSet<>(strings.length);
        for(String item : strings) {
            final double val = Double.parseDouble(item);
            resultSet.add(val);
        }

        return resultSet;
    }
}
