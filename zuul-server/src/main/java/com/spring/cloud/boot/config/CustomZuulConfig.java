package com.spring.cloud.boot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"com.spring.cloud.msc.web"})
@Configuration
public class CustomZuulConfig {
}