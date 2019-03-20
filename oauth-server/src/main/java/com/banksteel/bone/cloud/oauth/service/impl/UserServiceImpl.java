package com.banksteel.bone.cloud.oauth.service.impl;

import com.banksteel.bone.cloud.oauth.dao.IUserDao;
import com.banksteel.bone.cloud.oauth.entity.User;
import com.banksteel.bone.cloud.oauth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User findUserByName(String name) {
        return userDao.selectByName(name);
    }
}
