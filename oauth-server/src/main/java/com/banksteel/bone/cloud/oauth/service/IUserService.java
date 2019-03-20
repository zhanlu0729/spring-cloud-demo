package com.banksteel.bone.cloud.oauth.service;

import com.banksteel.bone.cloud.oauth.entity.User;

public interface IUserService {

    User findUserByName(String name);

}
