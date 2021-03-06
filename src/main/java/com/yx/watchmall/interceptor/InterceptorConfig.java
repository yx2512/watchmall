package com.yx.watchmall.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())
                .addPathPatterns("/shipments","/shipments/*","/cart","/cart/**", "/order","/orders/*");
        //registry.addInterceptor(new AuthInterceptor()).addPathPatterns();
    }
}
