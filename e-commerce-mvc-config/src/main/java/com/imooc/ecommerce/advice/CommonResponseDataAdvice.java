package com.imooc.ecommerce.advice;

import com.imooc.ecommerce.annotation.IgnoreResponseAdvice;
import com.imooc.ecommerce.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 实现统一响应
 */
@RestControllerAdvice(value = "com.imooc.ecommerce")
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 判断是否需要对ResponseBody返回的响应进行处理
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    @SuppressWarnings("all")    // 屏蔽警告信息
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        // 如果该Controller类被自定义的注解标识则不需要对响应进行特殊处理 --> 直接返回false
        if(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }

        // 如果当前方法被自定义注解所标识则不需要对响应继续特殊处理 --> 直接返回false
        if(methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings("all")    // 屏蔽警告信息
    // 在body响应回给客户端之前要做的事情
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // 定义最终的返回对象
        CommonResponse<Object> response = new CommonResponse<>(0,"");

        if (null == o){
            return response;    // 如果响应为空, 则不需要包装
        }else if (o instanceof CommonResponse){
            response = (CommonResponse<Object>) o;
        }else{
            response.setData(o);    // 将 o 所持有的返回信息变成response
        }
        return response;    // 最终返回response对象
    }
}
