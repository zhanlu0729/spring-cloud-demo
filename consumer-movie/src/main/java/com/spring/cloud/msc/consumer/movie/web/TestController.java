package com.spring.cloud.msc.consumer.movie.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/add")
    public String add(Integer a, Integer b) {
        return "本地跳转：" + (a + b);
    }

}
