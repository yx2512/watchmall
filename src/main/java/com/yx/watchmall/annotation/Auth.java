package com.yx.watchmall.annotation;

import com.yx.watchmall.enums.UserGroup;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Inherited
public @interface Auth {
    UserGroup value();
}
