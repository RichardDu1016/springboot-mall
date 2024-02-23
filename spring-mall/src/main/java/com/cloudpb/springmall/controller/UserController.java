package com.cloudpb.springmall.controller;

import com.cloudpb.springmall.dto.UserResgisterRequest;
import com.cloudpb.springmall.model.User;
import com.cloudpb.springmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserResgisterRequest userResgisterRequest) {

        Integer userId = userService.register(userResgisterRequest);
        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }
}
