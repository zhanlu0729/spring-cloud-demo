package com.spring.cloud.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class GatewayServerMain {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerMain.class, args);
    }

}
