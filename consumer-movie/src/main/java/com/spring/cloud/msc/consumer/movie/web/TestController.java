package com.spring.cloud.msc.consumer.movie.web;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/test/add")
    public String add(Integer a, Integer b) {
        return "本地跳转：" + (a + b);
    }

    @GetMapping(value = "/test")
    public String auth(HttpServletRequest request) {
        System.err.println("-----------header------starter-----------");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            System.err.println(key + ":" + request.getHeader(key));
        }
        System.err.println("-----------header------end-----------");
        return "test";
    }

}
