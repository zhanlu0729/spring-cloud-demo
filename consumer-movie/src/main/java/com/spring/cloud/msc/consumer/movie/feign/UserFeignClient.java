package com.spring.cloud.msc.consumer.movie.feign;

import com.spring.cloud.msc.consumer.movie.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "provider-user", path = "/user")
public interface UserFeignClient {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);

    @RequestMapping(value = "/instances", method = RequestMethod.GET)
    List<ServiceInstance> userInstances();

    @RequestMapping(value = "/instance", method = RequestMethod.GET)
    ServiceInstance userInstance();
}
