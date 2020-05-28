package com.qianfeng.boot.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author quincey
 * Date 2020/5/28 16:53
 * 自定义注解：就表示需要做权限检测
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CheckPermisssions {

    String value();
}
