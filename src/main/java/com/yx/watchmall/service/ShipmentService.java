package com.yx.watchmall.service;

import com.yx.watchmall.enums.ResponseEnum;
import com.yx.watchmall.form.ShipmentForm;
import com.yx.watchmall.pojo.Shipment;
import com.yx.watchmall.repository.ShipmentRepository;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ShipmentService {

    private ShipmentRepository shipmentRepository;

    @Autowired
    public void setShipmentRepository(ShipmentRepository repository) {
        shipmentRepository = repository;
    }

    public ResponseVo<Page<Shipment>> findByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        Pageable pageRequest = PageRequest.of(pageNum, pageSize);
        final Page<Shipment> shipments = shipmentRepository.findAllByUserId(userId, pageRequest);
        if(shipments == null || shipments.isEmpty()) {
            return ResponseVo.error(ResponseEnum.NO_CORRESPONDING_SHIPPING);
        }
        return ResponseVo.success(shipments);
    }

    public ResponseVo update(Integer userId, Integer shipmentId, ShipmentForm shipmentForm) {
        final Optional<Shipment> optionalShipment = shipmentRepository.findByIdAndUserId(shipmentId, userId);
        if(optionalShipment.isEmpty()) {
            return ResponseVo.error(ResponseEnum.NO_CORRESPONDING_SHIPPING);
        }
        Shipment updatableShipment = optionalShipment.get();
        BeanUtils.copyProperties(shipmentForm,updatableShipment);
        shipmentRepository.save(updatableShipment);
        return ResponseVo.success();
    }

    public ResponseVo delete(Integer userId, Integer shipmentId) {
        shipmentRepository.deleteByIdAndUserId(shipmentId, userId);
        return ResponseVo.success();
    }

    public ResponseVo<Map<String,Integer>> save(Integer userId, ShipmentForm shipmentForm) {
        Shipment newShipment = new Shipment();
        BeanUtils.copyProperties(shipmentForm,newShipment);
        newShipment.setUserId(userId);
        final Shipment savedShipment = shipmentRepository.save(newShipment);
        Map<String, Integer> returnMap = new HashMap<>();
        returnMap.put("shipmentId",savedShipment.getId());
        return ResponseVo.success(returnMap);
    }
}
