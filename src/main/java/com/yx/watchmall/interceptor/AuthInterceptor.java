package com.yx.watchmall.interceptor;

import com.yx.watchmall.annotation.Auth;
import com.yx.watchmall.exception.InsufficientPermissionException;
import com.yx.watchmall.exception.UserLoginException;
import com.yx.watchmall.mallConst.MallConst;
import com.yx.watchmall.pojo.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(! (handler instanceof HandlerInterceptor)) {
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;

        Auth annoAboveMethod = method.getMethod().getAnnotation(Auth.class);
        Auth annoAboveClass = method.getBean().getClass().getAnnotation(Auth.class);

        int resourceRate = annoAboveClass == null ? 0 : annoAboveClass.value().ordinal();
        resourceRate = annoAboveMethod == null ? resourceRate : annoAboveMethod.value().ordinal();

        int userRate = 0;
        User currentUser = (User) request.getSession().getAttribute(MallConst.CURRENT_USER);
        if(currentUser != null) {
            userRate = currentUser.getRole();
        }

        if(resourceRate > userRate) {
            if(userRate == 0) {
                throw new UserLoginException();
            }
            throw new InsufficientPermissionException();
        }
        return true;
    }
}
