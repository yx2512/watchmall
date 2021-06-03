package com.yx.watchmall.repository;

import com.yx.watchmall.pojo.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User,Integer> {
    User findByEmail(String email);
    int countByEmail(String email);
    int countByUsername(String username);
}
