package com.spring.cloud.msc.consumer.movie.feign;

import com.spring.cloud.boot.model.JsonResult;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserFeignClientImpl implements UserFeignClient {

    @Override
    public JsonResult findById(Long id) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", -1L);
        return JsonResult.builder().data(userMap).build();
    }

    @Override
    public List<ServiceInstance> userInstances() {
        return null;
    }

    @Override
    public ServiceInstance userInstance() {
        return null;
    }
}
