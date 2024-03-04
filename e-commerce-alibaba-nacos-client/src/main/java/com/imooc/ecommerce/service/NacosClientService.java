package com.imooc.ecommerce.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NacosClientService {
    private final DiscoveryClient discoveryClient;

    public NacosClientService(DiscoveryClient discoveryClient){
        this.discoveryClient = discoveryClient;
    }

    /**
     * <h2>打印 Nacos Client 信息到日志中</h2>
     * 根据serviceId可以找到对应的全部实例信息
     * @param serviceId
     * @return
     */
    public List<ServiceInstance> getNacosClientInfo(String serviceId){
        log.info("reuqest nacos client to get service instance info: [{}]", serviceId);
        return discoveryClient.getInstances(serviceId);
    }
}
