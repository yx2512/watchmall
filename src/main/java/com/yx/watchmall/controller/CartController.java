package com.yx.watchmall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yx.watchmall.form.CartModifyForm;
import com.yx.watchmall.mallConst.MallConst;
import com.yx.watchmall.pojo.User;
import com.yx.watchmall.service.CartService;
import com.yx.watchmall.vo.CartVo;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class CartController {
    private CartService cartService;

    @Autowired
    public void setCartService(CartService service) {
        cartService = service;
    }

    @GetMapping("/cart")
    public ResponseVo<CartVo> list(HttpSession session) throws JsonProcessingException {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.list(user.getId());
    }


    @PostMapping("/cart/{productId}")
    public ResponseVo<CartVo> update(@PathVariable(value = "productId") Integer productId, @Valid @RequestBody CartModifyForm cartModifyForm, HttpSession session) throws JsonProcessingException {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.modify(user.getId(),productId,cartModifyForm);
    }

    @DeleteMapping("/cart/{productId}")
    public ResponseVo<CartVo> delete(@PathVariable(value = "productId") Integer productId, HttpSession session) throws JsonProcessingException {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.delete(user.getId(),productId);
    }

    @PutMapping("/cart/selectAll")
    public ResponseVo<CartVo> selectAll(HttpSession session) throws JsonProcessingException {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.toggleAllSelected(user.getId(),true);
    }

    @PutMapping("/cart/deselectAll")
    public ResponseVo<CartVo> deselectAll(HttpSession session) throws JsonProcessingException {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.toggleAllSelected(user.getId(),false);
    }

    @GetMapping("/cart/products/sum")
    public ResponseVo<Integer> totalQuantity(HttpSession session) throws JsonProcessingException {
        User user = (User)session.getAttribute(MallConst.CURRENT_USER);
        return cartService.sum(user.getId());
    }
}
