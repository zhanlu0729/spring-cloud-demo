package com.spring.cloud.msc.oauth.service;

import com.spring.cloud.msc.oauth.entity.User;

public interface IUserService {

    User findUserByName(String name);

}
