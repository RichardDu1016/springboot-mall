package com.cloudpb.springmall.dao;

import com.cloudpb.springmall.dto.UserResgisterRequest;
import com.cloudpb.springmall.model.User;

public interface UserDao {
    Integer createUser(UserResgisterRequest resgisterRequest);
    User getUserById(Integer userId);
    User getUserByEmail(String email);
}
