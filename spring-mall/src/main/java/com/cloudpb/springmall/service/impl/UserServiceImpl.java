package com.cloudpb.springmall.service.impl;

import com.cloudpb.springmall.dao.UserDao;
import com.cloudpb.springmall.dto.UserLoginRequest;
import com.cloudpb.springmall.dto.UserResgisterRequest;
import com.cloudpb.springmall.model.User;
import com.cloudpb.springmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserResgisterRequest userResgisterRequest) {
        // 檢查註冊的email
        User user = userDao.getUserByEmail(userResgisterRequest.getEmail());
        if( user != null) {
            // log 中 { } 代表的是變數
            log.warn("該 email {} 已經被註冊", userResgisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        // 註冊新帳號
        return userDao.createUser(userResgisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        // 檢查email 尚未註冊
        if(user == null) {
            log.warn("該 email {} 尚未被註冊", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (user.getPassword().equals(userLoginRequest.getPassword())) {
            return user;
        } else {
            // 若密碼不正確
            log.warn("email {} 的密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
}
