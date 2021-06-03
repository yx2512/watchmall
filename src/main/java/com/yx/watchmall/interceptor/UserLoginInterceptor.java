package com.yx.watchmall.interceptor;

import com.yx.watchmall.exception.UserLoginException;
import com.yx.watchmall.mallConst.MallConst;
import com.yx.watchmall.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        if(user == null) {
            throw new UserLoginException();
        }
        return true;
    }
}
