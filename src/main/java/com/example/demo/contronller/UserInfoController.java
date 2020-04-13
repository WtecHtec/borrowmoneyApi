package com.example.demo.contronller;


import com.example.demo.dao.pojo.User;
import com.example.demo.emailUtil.EmailTool;
import com.example.demo.jwt.JwtIgnore;
import com.example.demo.request.EmailInfo;
import com.example.demo.serviceImp.UserServiceImp;
import com.example.demo.uitl.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Service;

@Slf4j
@RestController
public class UserInfoController {

     // 发送邮件
     @Autowired
     private EmailTool emailTool;

     @Autowired
     private UserServiceImp userServiceImp;

    private  String fromEmail = "1079463751@qq.com";
     @RequestMapping(value = "/sendEmail",method =  RequestMethod.POST)
     @JwtIgnore
     public String startCron(EmailInfo emailInfo) {
         log.info("注册验证码");
         emailTool.sendOneEmail(fromEmail, emailInfo.getToEmail(),"注册验证码","验证码为："+ emailInfo.getContext());
         return "success";
     }

    @RequestMapping(value = "/registered",method =  RequestMethod.POST)
    @JwtIgnore
    public BaseResponse<String> registered(@RequestBody User user) {
        log.info("注册");
        BaseResponse<String> result = new BaseResponse<>();
        result =  userServiceImp.createUserInfo(user);
        return result;
    }





}
