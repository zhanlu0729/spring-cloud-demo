package com.spring.cloud.msc.consumer.movie.web;

import com.spring.cloud.boot.model.JsonResult;
import com.spring.cloud.msc.consumer.movie.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @Value("${provider.user.serviceUrl}")
    private String userServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/{id}")
    public Object findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

}
