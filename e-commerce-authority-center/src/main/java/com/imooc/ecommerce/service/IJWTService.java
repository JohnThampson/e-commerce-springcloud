package com.imooc.ecommerce.service;

import com.imooc.ecommerce.vo.UserAndPassword;

/**
 * <h1>JWT相关服务接口定义</h1>
 */
public interface IJWTService {

    /**
     * <h2>生成JWT Token，使用默认的超时时间</h2>
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    String generateToken(String username, String password) throws Exception;

    /**
     * <h2>生成指定超时时间 Token, 单位是天</h2>
     * @param username
     * @param password
     * @param expire
     * @return
     * @throws Exception
     */
    String generateToken(String username, String password, int expire) throws Exception;

    /**
     * <h2>注册用户并生成Token返回</h2>
     * @param userAndPassword
     * @return
     * @throws Exception
     */
    String registerUserAndGenerateToken(UserAndPassword userAndPassword) throws Exception;
}
