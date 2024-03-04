package com.imooc.ecommerce.service;

import com.alibaba.fastjson.JSON;
import com.imooc.ecommerce.dao.EcommerceUserDao;
import com.imooc.ecommerce.entity.EcommerceUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class EcommerceFindTest {

    @Autowired
    private EcommerceUserDao ecommerceUserDao;

    @Test
    public void FindUser(){
//        EcommerceUser ecommerceUser = new EcommerceUser();
        log.info("find user [{}]", JSON.toJSON(ecommerceUserDao.findByUsername("2602154347@qq.com")));

    }
}
