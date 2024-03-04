package com.imooc.ecommerce.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h1>忽略统一响应注解定义</h1>
 */

@Target({ElementType.TYPE, ElementType.METHOD}) // 当前注解标识在什么地方，比如：类、属性、方法.....
@Retention(RetentionPolicy.RUNTIME)    // 标识当前注解需要保留到什么时候 --> (运行池)
public @interface IgnoreResponseAdvice {

}
