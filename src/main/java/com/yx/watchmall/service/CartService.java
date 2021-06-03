package com.yx.watchmall.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yx.watchmall.enums.ResponseEnum;
import com.yx.watchmall.form.CartModifyForm;
import com.yx.watchmall.mallConst.MallConst;
import com.yx.watchmall.pojo.CartProduct;
import com.yx.watchmall.pojo.Product;
import com.yx.watchmall.repository.ProductRepository;
import com.yx.watchmall.vo.CartProductVo;
import com.yx.watchmall.vo.CartVo;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.mysql.cj.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private ProductRepository productRepository;
    private StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setProductRepository(ProductRepository repository) {
        productRepository = repository;
    }

    public ResponseVo<CartVo> modify(Integer userId,Integer productId, CartModifyForm cartModifyForm) throws JsonProcessingException {
        final Optional<Product> productOptional = productRepository.findByIdAndStatus(productId, MallConst.NORMAL_STATUS);
        if(productOptional.isEmpty()) {
            return ResponseVo.error(ResponseEnum.NO_SUCH_PRODUCT);
        }

        final Product product = productOptional.get();
        if(product.getStock() <= 0 || product.getStock() < cartModifyForm.getQuantity()) {
            return ResponseVo.error(ResponseEnum.PRODUCT_UNAVAILABLE);
        }

        String productIdString = String.valueOf(productId);
        final HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        final String redisUserKey = String.format(MallConst.CART_REDIS_KEY_TEMPLATE, userId);
        final String item = opsForHash.get(redisUserKey, productIdString);
        CartProduct cartProduct = null;
        if(StringUtils.isNullOrEmpty(item)) {
            cartProduct = new CartProduct(productId,cartModifyForm.getQuantity(),cartModifyForm.getSelected());
        } else {
            cartProduct = objectMapper.readValue(item, CartProduct.class);
            cartProduct.setQuantity(cartModifyForm.getQuantity());
            cartProduct.setSelected(cartModifyForm.getSelected());
        }
        opsForHash.put(redisUserKey,productIdString,objectMapper.writeValueAsString(cartProduct));
        redisTemplate.expire(redisUserKey,10, TimeUnit.DAYS);
        return list(userId);
    }

    public ResponseVo<CartVo> list(Integer userId) throws JsonProcessingException {
        final HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        final String redisUserKey = String.format(MallConst.CART_REDIS_KEY_TEMPLATE, userId);
        final Map<String, String> entries = opsForHash.entries(redisUserKey);
        List<Product> products = null;
        boolean selectAll = true;
        Integer quantityAll = 0;
        BigDecimal priceAll = BigDecimal.ZERO;
        List<CartProductVo> cartProductVos = new ArrayList<>();
        final Set<String> keySet = entries.keySet();
        final Set<Integer> idSet = keySet.stream().map(Integer::parseInt).collect(Collectors.toSet());
        if(idSet.size() != 0) {
            products = productRepository.findAllById(idSet);
        } else {
            products = new ArrayList<>();
        }

        for(Product product : products) {
            if(product != null) {
                final CartProduct cartProduct = objectMapper.readValue(entries.get(product.getId().toString()), CartProduct.class);
                CartProductVo cartProductVo = new CartProductVo();
                cartProductVo.setProductId(product.getId());
                cartProductVo.setQuantity(cartProduct.getQuantity());
                cartProductVo.setDescription(product.getDescription());
                cartProductVo.setMainImg(product.getMainImg());
                cartProductVo.setPrice(product.getPrice());
                cartProductVo.setStatus(product.getStatus());

                cartProductVo.setSelected(cartProduct.getSelected());
                cartProductVo.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(cartProduct.getQuantity())));

                cartProductVos.add(cartProductVo);
                if(!cartProduct.getSelected()) {
                    selectAll = false;
                } else {
                    quantityAll += cartProductVo.getQuantity();
                    priceAll = priceAll.add(cartProductVo.getTotalPrice());
                }
            }
        }

        CartVo cartVo = new CartVo();
        cartVo.setCartProductVoList(cartProductVos);
        cartVo.setSelectAll(selectAll);
        cartVo.setTotalQuantity(quantityAll);
        cartVo.setTotalPrice(priceAll);
        return ResponseVo.success(cartVo);
    }

    public ResponseVo<CartVo> delete(Integer userId, Integer productId) throws JsonProcessingException {
        String redisUserKey = String.format(MallConst.CART_REDIS_KEY_TEMPLATE, userId);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String cartProductString = opsForHash.get(redisUserKey, String.valueOf(productId));
        if(StringUtils.isNullOrEmpty(cartProductString)) {
            return ResponseVo.error(ResponseEnum.NO_SUCH_PRODUCT_IN_CART);
        }

        opsForHash.delete(redisUserKey,String.valueOf(productId));
        return list(userId);
    }

    public ResponseVo<CartVo> toggleAllSelected(Integer userId, Boolean selectAll) throws JsonProcessingException {
        String redisUserKey = String.format(MallConst.CART_REDIS_KEY_TEMPLATE,userId);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        Map<String, String> entries = opsForHash.entries(redisUserKey);
        for(Map.Entry<String, String> entry : entries.entrySet()) {
            String cartProductString = entry.getValue();
            CartProduct cartProduct = objectMapper.readValue(cartProductString, CartProduct.class);
            cartProduct.setSelected(selectAll);
            entries.put(entry.getKey(),objectMapper.writeValueAsString(cartProduct));
        }
        opsForHash.putAll(redisUserKey,entries);
        return list(userId);
    }

    public ResponseVo<Integer> sum(Integer userId) throws JsonProcessingException {
        List<CartProduct> cartProducts = listForCartProduct(userId);
        Integer sum = cartProducts.stream().map(CartProduct::getQuantity).reduce(0, Integer::sum);
        return ResponseVo.success(sum);
    }

    public List<CartProduct> listForCartProduct(Integer userId) throws JsonProcessingException {
        List<CartProduct> list = new ArrayList<>();
        String redisUserKey = String.format(MallConst.CART_REDIS_KEY_TEMPLATE,userId);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        Map<String, String> entries = opsForHash.entries(redisUserKey);
        for(Map.Entry<String, String> entry : entries.entrySet()) {
            list.add(objectMapper.readValue(entry.getValue(),CartProduct.class));
        }
        return list;
    }

}
