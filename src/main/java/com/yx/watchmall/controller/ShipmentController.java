package com.yx.watchmall.controller;

import com.yx.watchmall.form.ShipmentForm;
import com.yx.watchmall.mallConst.MallConst;
import com.yx.watchmall.pojo.Shipment;
import com.yx.watchmall.pojo.User;
import com.yx.watchmall.service.ShipmentService;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@RestController
public class ShipmentController {

    private ShipmentService shipmentService;

    @Autowired
    public void setShipmentService(ShipmentService service) {
        shipmentService = service;
    }

    @GetMapping("/shipments")
    public ResponseVo<Page<Shipment>> listShipments(@RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                                                    @RequestParam(value = "pageSize",defaultValue = "10", required = false) Integer pageSize,
                                                    HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shipmentService.findByUserId(user.getId(), pageNum, pageSize);
    }

    @PutMapping("/shipments/{shipmentId}")
    public ResponseVo updateShipment(@PathVariable(value = "shipmentId") Integer shipmentId,
                                     @Valid @RequestBody ShipmentForm shipmentForm,
                                     HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shipmentService.update(user.getId(), shipmentId, shipmentForm);
    }

    @DeleteMapping("/shipments/{shipmentId}")
    public ResponseVo deleteShipment(@PathVariable(value = "shipmentId") Integer shipmentId, HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shipmentService.delete(user.getId(),shipmentId);
    }

    @PostMapping("/shipments")
    public ResponseVo<Map<String,Integer>> addShipment(@Valid @RequestBody ShipmentForm shipmentForm, HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shipmentService.save(user.getId(),shipmentForm);
    }
}
