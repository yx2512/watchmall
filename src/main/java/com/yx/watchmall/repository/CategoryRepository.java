package com.yx.watchmall.repository;

import com.yx.watchmall.pojo.Category;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CategoryRepository extends CrudRepository<Category,Integer> {
    List<Category> findByStatus(Integer status);
}
