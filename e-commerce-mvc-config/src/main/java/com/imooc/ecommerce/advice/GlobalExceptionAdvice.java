package com.imooc.ecommerce.advice;

import com.imooc.ecommerce.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <h2>全局异常处理</h2>
 * Controller在执行方法的过程中,会产生各种异常情况, 由于不想让客户端抛出一场还是想使用统一响应,所以就需要一个全局的异常捕获处理
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    // 捕捉那些类型的异常 --> 捕获所有异常
    @ExceptionHandler(value = Exception.class)
    public CommonResponse<String> handlerCommerceException (HttpServletRequest req, Exception ex){
        CommonResponse<String> response = new CommonResponse<>(
                -1, "business error"    // 如果响应code值是-1, 则返回业务错误
        );
        response.setData(ex.getMessage());
        log.error("commerce service has error: [{}]", ex.getMessage(), ex);
        return  response;
    }
}
