package com.cloudpb.springmall.service.impl;

import com.cloudpb.springmall.dao.UserDao;
import com.cloudpb.springmall.dto.UserResgisterRequest;
import com.cloudpb.springmall.model.User;
import com.cloudpb.springmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserResgisterRequest userResgisterRequest) {
        return userDao.createUser(userResgisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
