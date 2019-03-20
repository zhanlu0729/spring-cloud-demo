package com.spring.cloud.msc.oauth.service.impl;

import com.spring.cloud.msc.oauth.dao.IUserDao;
import com.spring.cloud.msc.oauth.entity.User;
import com.spring.cloud.msc.oauth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User findUserByName(String name) {
        return userDao.selectByName(name);
    }
}
