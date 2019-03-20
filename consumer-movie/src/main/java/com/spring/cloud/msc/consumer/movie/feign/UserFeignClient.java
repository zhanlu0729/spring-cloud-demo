package com.spring.cloud.msc.consumer.movie.feign;

import com.spring.cloud.boot.model.JsonResult;
import java.util.List;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "provider-user", path = "/user",fallback = UserFeignClientImpl.class)
public interface UserFeignClient {

    @GetMapping(value = "/{id}")
    JsonResult findById(@PathVariable("id") Long id);

    @GetMapping(value = "/instances")
    List<ServiceInstance> userInstances();

    @GetMapping(value = "/instance")
    ServiceInstance userInstance();
}
