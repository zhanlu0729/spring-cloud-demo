package com.spring.cloud.msc.consumer.movie.feign;

import com.spring.cloud.msc.consumer.movie.entity.User;
import java.util.List;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "provider-user", path = "/user",fallback = UserFeignClientImpl.class)
public interface UserFeignClient {

    @GetMapping(value = "/{id}")
    User findById(@PathVariable("id") Long id);

    @GetMapping(value = "/instances")
    List<ServiceInstance> userInstances();

    @GetMapping(value = "/instance")
    ServiceInstance userInstance();
}
