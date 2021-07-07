package com.yx.watchmall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yx.watchmall.form.OrderCreateForm;
import com.yx.watchmall.mallConst.MallConst;
import com.yx.watchmall.pojo.User;
import com.yx.watchmall.service.OrderService;
import com.yx.watchmall.vo.OrderVo;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class OrderController {

    private OrderService orderService;

    @Autowired
    public void setCartService(OrderService service) {
        orderService = service;
    }

    @PostMapping("/order")
    public ResponseVo<OrderVo> placeOrder(@Valid @RequestBody OrderCreateForm form, HttpSession session) throws JsonProcessingException {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return orderService.placeOrder(user.getId(),form.getShipmentId());
    }

    @GetMapping("/orders")
    public ResponseVo<Page<OrderVo>> list(@RequestParam(value = "pageNum",required = false, defaultValue = "0") Integer pageNum,
                                          @RequestParam(value = "pageSize",required = false, defaultValue = "10") Integer pageSize,
                                          HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return orderService.listOrders(user.getId(), pageNum, pageSize);
    }

    @GetMapping("/orders/{orderNo}")
    public ResponseVo<OrderVo> detail(@PathVariable Long orderNo, HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return orderService.detail(user.getId(), orderNo);
    }

    @PutMapping("/orders/{orderNo}")
    public ResponseVo cancel(@PathVariable Long orderNo, HttpSession session) {
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return orderService.cancel(user.getId(),orderNo);
    }

}
