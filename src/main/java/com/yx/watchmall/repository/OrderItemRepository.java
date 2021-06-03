package com.yx.watchmall.repository;

import com.yx.watchmall.pojo.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findAllByOrderNum(Iterable<Long> orderNums);
}
