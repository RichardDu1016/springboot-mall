package com.cloudpb.springmall.controller;

import com.cloudpb.springmall.dao.UserDao;
import com.cloudpb.springmall.dto.UserLoginRequest;
import com.cloudpb.springmall.dto.UserResgisterRequest;
import com.cloudpb.springmall.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDao userDao;

    private ObjectMapper objectMapper = new ObjectMapper();

    // 註冊新帳號
    @Test

    public void register_success() throws Exception {
        UserResgisterRequest userResgisterRequest = new UserResgisterRequest();
        userResgisterRequest.setEmail("test1@gmail.com");
        userResgisterRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(userResgisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.userId", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo("test1@gmail.com")))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));

        // 檢查資料庫中的密碼不為明碼
        User user = userDao.getUserByEmail(userResgisterRequest.getEmail());
        assertNotEquals(userResgisterRequest.getPassword(), user.getPassword());

    }

    @Test
    public void register_invalidEmailFormat() throws Exception {
        UserResgisterRequest userResgisterRequest = new UserResgisterRequest();
        userResgisterRequest.setEmail("sdsdfsdf");
        userResgisterRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(userResgisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    public void register_emailAreadyExist() throws Exception {

        //  先註冊一個帳號
        UserResgisterRequest userResgisterRequest = new UserResgisterRequest();
        userResgisterRequest.setEmail("test2@gamil.com");
        userResgisterRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(userResgisterRequest);

        RequestBuilder requestBuilders = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilders)
                .andExpect(status().is(201));

        // 在使用同一個 email 註冊
        mockMvc.perform(requestBuilders)
                .andExpect(status().is(400));
    }

    // 登入

    @Test
    public void login_success() throws Exception {
        // 先註冊一個新帳號 (也可以先在data.sql 放入測試用數據，但登入功能建議還是創建新帳號來測試)
        UserResgisterRequest userResgisterRequest = new UserResgisterRequest();
        userResgisterRequest.setEmail("test3@gmail.com");
        userResgisterRequest.setPassword("123");

        register(userResgisterRequest);

        // 在測試登入功能
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail(userResgisterRequest.getEmail());
        userLoginRequest.setPassword(userResgisterRequest.getPassword());

        String json = objectMapper.writeValueAsString(userLoginRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.userId", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo(userLoginRequest.getEmail())))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));


    }
    @Test
    public void login_wrongPassword() throws Exception {
        // 先創建帳號
        UserResgisterRequest userResgisterRequest = new UserResgisterRequest();
        userResgisterRequest.setEmail("test4@gmail.com");
        userResgisterRequest.setPassword("123");

        register(userResgisterRequest);

        // 在測試登入功能
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail(userResgisterRequest.getEmail());
        userLoginRequest.setPassword("unknown");

        String json = objectMapper.writeValueAsString(userLoginRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }
    @Test
    public void login_invalidEmailFormat() throws Exception {
// 先創建帳號
        UserResgisterRequest userResgisterRequest = new UserResgisterRequest();
        userResgisterRequest.setEmail("test5@gmail.com");
        userResgisterRequest.setPassword("123");

        register(userResgisterRequest);

        // 在測試登入功能
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail("dfgdfgdf");
        userLoginRequest.setPassword(userResgisterRequest.getPassword());

        String json = objectMapper.writeValueAsString(userLoginRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }
    @Test
    public void login_emailNotExist() throws Exception {
        UserResgisterRequest userResgisterRequest = new UserResgisterRequest();
        userResgisterRequest.setEmail("ccc@gmail.com");
        userResgisterRequest.setPassword("123");

        String json = objectMapper.writeValueAsString(userResgisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    private void register(UserResgisterRequest userResgisterRequest) throws  Exception {
        String json = objectMapper.writeValueAsString(userResgisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));
    }

}