package com.imooc.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * <h1>授权中心启动入口</h1>
 */
@EnableJpaAuditing  // 实现JPA的自动审计 -> 在对表记录进行操作时能够帮助对时间等信息进行标识
@EnableDiscoveryClient
@SpringBootApplication
public class AuthorityCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityCenterApplication.class, args);
    }
}
