package com.example.demo.service;

import com.example.demo.dao.pojo.User;
import com.example.demo.uitl.BaseResponse;

public interface UserService {
    // 注册
    BaseResponse<String> createUserInfo(User userInfo);
}
