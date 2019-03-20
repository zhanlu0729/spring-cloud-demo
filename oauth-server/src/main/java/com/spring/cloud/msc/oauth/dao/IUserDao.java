package com.spring.cloud.msc.oauth.dao;

import com.spring.cloud.msc.oauth.entity.User;

public interface IUserDao {

    int deleteByPrimaryKey(Long id);

    int insert(User entity);

    int insertSelective(User entity);

    User selectByPrimaryKey(Long id);

    User selectByName(String name);

    int updateByPrimaryKey(User entity);

}
