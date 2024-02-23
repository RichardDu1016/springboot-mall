package com.cloudpb.springmall.service;

import com.cloudpb.springmall.dto.UserLoginRequest;
import com.cloudpb.springmall.dto.UserResgisterRequest;
import com.cloudpb.springmall.model.User;

public interface UserService {


    Integer register(UserResgisterRequest userResgisterRequest);
    User getUserById(Integer userId);

    User login (UserLoginRequest userLoginRequest);
}
