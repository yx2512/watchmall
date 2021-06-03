package com.yx.watchmall.repository;

import com.yx.watchmall.pojo.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAllByUserId(Integer userId);
    Page<Order> findAllByUserId(Integer userId, Pageable pageable);

    Optional<Order> findByOrderNum(Long orderNum);
}
