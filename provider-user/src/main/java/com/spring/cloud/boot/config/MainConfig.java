package com.spring.cloud.boot.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.spring.cloud.msc")
@EnableJpaRepositories(basePackages = "com.spring.cloud.msc")
@ComponentScan(basePackages = "com.spring.cloud.msc")
public class MainConfig {

}
