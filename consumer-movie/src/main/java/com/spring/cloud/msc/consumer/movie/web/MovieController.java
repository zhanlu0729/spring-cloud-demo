package com.spring.cloud.msc.consumer.movie.web;

import com.spring.cloud.msc.consumer.movie.entity.User;
import com.spring.cloud.msc.consumer.movie.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @Value("${provider.user.serviceUrl}")
    private String userServiceUrl;

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }


}
