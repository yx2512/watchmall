package com.yx.watchmall.controller;

import com.yx.watchmall.enums.ResponseEnum;
import com.yx.watchmall.form.LoginForm;
import com.yx.watchmall.form.RegisterForm;
import com.yx.watchmall.mallConst.MallConst;
import com.yx.watchmall.pojo.User;
import com.yx.watchmall.service.UserService;
import com.yx.watchmall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class UserController {
    UserService userService;

    @Autowired
    public void setUserService(UserService service) {
        userService = service;
    }

    @PostMapping("/user/login")
    public ResponseVo<User> login(@Valid @RequestBody LoginForm loginForm, HttpSession session) {
        final ResponseVo<User> userByEmail = userService.login(loginForm.getEmail(), loginForm.getPassword());
        session.setAttribute(MallConst.CURRENT_USER,userByEmail.getData());
        return userByEmail;
    }

    @PostMapping("/user/register")
    public ResponseVo register(@Valid @RequestBody RegisterForm registerForm) {
        User user = new User();
        BeanUtils.copyProperties(registerForm,user);
        return userService.register(user);
    }

    @GetMapping("/user")
    public ResponseVo<User> getUser(HttpSession session) {
        final User currentUser = (User) session.getAttribute(MallConst.CURRENT_USER);
        if(currentUser == null) {
            return ResponseVo.error(ResponseEnum.NO_CURRENTUSER);
        } else {
            return ResponseVo.success(currentUser);
        }
    }

    @GetMapping("/user/logout")
    public ResponseVo logout(HttpSession session) {
        session.removeAttribute(MallConst.CURRENT_USER);
        return ResponseVo.success();
    }
}
