package com.spring.cloud.boot;

import com.spring.cloud.msc.filter.FirstPreFilter;
import com.spring.cloud.msc.filter.SecondPreFilter;
import com.spring.cloud.msc.filter.ThirdPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

//@EnableOAuth2Sso
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulServerMain {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerMain.class, args);
    }

    @Bean
    public FirstPreFilter firstPreFilter() {
        return new FirstPreFilter();
    }

    @Bean
    public SecondPreFilter secondPreFilter() {
        return new SecondPreFilter();
    }

    @Bean
    public ThirdPreFilter thirdPreFilter() {
        return new ThirdPreFilter();
    }

}
