package com.banksteel.bone.cloud.boot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.spring.cloud.msc.consumer.movie")
@ComponentScan(basePackages = "com.spring.cloud.msc.consumer.movie")
public class ConsumerMovieConfiguration {

}
