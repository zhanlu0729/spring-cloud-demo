package com.spring.cloud.msc.provider.user.web;

import com.spring.cloud.boot.model.JsonResult;
import com.spring.cloud.msc.provider.user.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Value("${server.port:0}")
    private Integer serverPort;

    @GetMapping("/{id}")
    public JsonResult findById(@PathVariable Long id) {
        return JsonResult.builder().status(200).success(Boolean.TRUE).data(userDao.findById(id)).build();
    }

}
