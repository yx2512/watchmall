package com.yx.watchmall.repository;

import com.yx.watchmall.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByIdAndStatus(Integer Id, Integer status);
    @Query("SELECT DISTINCT p FROM Product p INNER JOIN ProductFeature pf " +
            "on pf.productId = p.id " +
            "WHERE (COALESCE(:categoryIds, -1) = -1 or p.category in (:categoryIds)) " +
            "and  (COALESCE(:featureSet, -1) = -1 or pf.featureId in (:featureSet)) " +
            "and (COALESCE(:dialColorSet, -1) = -1 or p.dialColor in (:dialColorSet)) " +
            "and (COALESCE(:sizeSet, -1) = -1 or p.size in (:sizeSet)) " +
            "and p.status = :status order by p.price")
    Page<Product> findAllByAttributes(Set<Integer> categoryIds, Set<Integer> featureSet, Set<Integer> dialColorSet, Set<Double> sizeSet, Integer status, Pageable pageRequest);
}
