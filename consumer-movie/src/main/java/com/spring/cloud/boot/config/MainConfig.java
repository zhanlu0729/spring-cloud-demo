package com.spring.cloud.boot.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.spring.cloud.msc")
@ComponentScan(basePackages = "com.spring.cloud.msc")
public class MainConfig {

}
