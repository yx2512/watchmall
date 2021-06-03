package com.yx.watchmall.service;

import com.yx.watchmall.WatchmallApplication;
import com.yx.watchmall.WatchmallApplicationTests;
import com.yx.watchmall.pojo.User;
import com.yx.watchmall.repository.UserRepository;
import com.yx.watchmall.vo.ResponseVo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserServiceTest extends WatchmallApplicationTests {

    @Autowired
    private static UserService service;

    @Test
    void getUserByEmail() {
        final ResponseVo<User> res = service.login("admin@watchmall.com", "sueyurt");
        System.out.println(res);
    }
}