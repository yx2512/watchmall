package com.yx.watchmall.service;

import com.yx.watchmall.enums.ResponseEnum;
import com.yx.watchmall.pojo.User;
import com.yx.watchmall.repository.UserRepository;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseVo<User> login(String email, String password) {
        final User user = userRepository.findByEmail(email);
        if(user == null) {
            return ResponseVo.error(ResponseEnum.LOGIN_FAILED);
        }

        if(!user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            return ResponseVo.error(ResponseEnum.LOGIN_FAILED);
        }
        user.setPassword(null);
        user.setCreate_time(null);
        user.setUpdate_time(null);
        return ResponseVo.success(user);
    }

    public ResponseVo register(User user) {
        int count = userRepository.countByEmail(user.getEmail());
        if(count != 0) {
            return ResponseVo.error(ResponseEnum.USER_EXISTS);
        }

        count = 0;
        count = userRepository.countByUsername(user.getUsername());
        if(count != 0) {
            return ResponseVo.error(ResponseEnum.USER_EXISTS);
        }

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        user.setRole(0);
        userRepository.save(user);
        return ResponseVo.success();
    }
}
