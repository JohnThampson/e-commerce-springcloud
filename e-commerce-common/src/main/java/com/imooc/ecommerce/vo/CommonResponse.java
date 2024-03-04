package com.imooc.ecommerce.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @title: 通用响应对象定义
 * {
 *     "code": 0,
 *     "message": "",
 *     "data": {}
 * }
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> implements Serializable {

    /** 错误码 */
    private Integer code;
    /** 错误信息 */
    private String msg;
    /** 泛型响应数据 */
    private T data;

    public CommonResponse(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
