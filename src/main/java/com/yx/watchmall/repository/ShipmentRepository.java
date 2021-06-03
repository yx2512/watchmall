package com.yx.watchmall.repository;

import com.yx.watchmall.pojo.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends PagingAndSortingRepository<Shipment,Integer> {
    Page<Shipment> findAllByUserId(Integer userId, Pageable pageable);
    Optional<Shipment> findByIdAndUserId(Integer Id, Integer userId);
    void deleteByIdAndUserId(Integer Id, Integer userId);
}
