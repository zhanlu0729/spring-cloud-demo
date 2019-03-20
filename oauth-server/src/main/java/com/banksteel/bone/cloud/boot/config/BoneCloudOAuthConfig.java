package com.banksteel.bone.cloud.boot.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication  //SpringBoot 启动入口
@EnableDiscoveryClient  //自动注册和服务发现
@EnableCircuitBreaker   //开启Hystrix监控
public class BoneCloudOAuthConfig {

}
