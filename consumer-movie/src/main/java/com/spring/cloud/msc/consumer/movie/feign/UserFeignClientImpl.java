package com.spring.cloud.msc.consumer.movie.feign;

import com.spring.cloud.boot.model.JsonResult;
import com.spring.cloud.msc.consumer.movie.model.User;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFeignClientImpl implements UserFeignClient {

    @Override
    public Object findById(Long id) {
        User user = new User();
        user.setId(-1L);
        return JsonResult.builder().data(user).build();
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
