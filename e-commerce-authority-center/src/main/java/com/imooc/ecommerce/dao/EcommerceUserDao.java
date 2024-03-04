package com.imooc.ecommerce.dao;


import com.imooc.ecommerce.entity.EcommerceUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1>EcommerceUser Dao 接口定义</h1>
 */
public interface EcommerceUserDao extends JpaRepository<EcommerceUser, Long> {

    /**
     * 根据用户名查询 EcommerceUser 对象
     * @SQL select * from t_ecommerce_user where username = ?
     * @param username
     * @return
     */
    EcommerceUser findByUsername(String username);

    /**
     * 根据用户名和密码查询实体对象
     * @param username
     * @param password
     * @return
     */
    EcommerceUser findByUsernameAndPassword(String username, String password);
}
