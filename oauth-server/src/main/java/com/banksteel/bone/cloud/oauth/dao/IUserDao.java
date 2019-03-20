package com.banksteel.bone.cloud.oauth.dao;

import com.banksteel.bone.cloud.oauth.entity.User;

public interface IUserDao {

    int deleteByPrimaryKey(Long id);

    int insert(User entity);

    int insertSelective(User entity);

    User selectByPrimaryKey(Long id);

    User selectByName(String name);

    int updateByPrimaryKey(User entity);

}
