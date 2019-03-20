package com.spring.cloud.msc.consumer.movie.feign;

import com.spring.cloud.msc.consumer.movie.entity.User;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFeignClientImpl implements UserFeignClient {
    @Override
    public User findById(Long id) {
        User user= new User();
        user.setId(-1L);
        user.setUsername("no person");
        return user;
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
