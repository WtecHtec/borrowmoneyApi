package com.example.demo.serviceImp;

import com.example.demo.dao.mapper.UserMapper;
import com.example.demo.dao.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.uitl.BaseResponse;
import com.example.demo.uitl.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserMapper userMapper;

    private IdWorker idWorker = new IdWorker(1,1);
    @Override
    public BaseResponse<String> createUserInfo(User userInfo) {
        log.info("注册账户");
        BaseResponse<String> result = new BaseResponse<>();
        User checkparam = new User();
        checkparam.setUseremail(userInfo.getUseremail());
        User re =  userMapper.selectAnyParams(checkparam);
        if (null !=  re) {
            result.setStatusCode(201);
            result.setResponseData("注册失败,邮箱已注册！");
            return result;
        }
        userInfo.setUserid(String.valueOf(idWorker.nextId()));
        //加密后的字符串
        String pwd =  DigestUtils.md5Hex(userInfo.getPwd());
        userInfo.setPwd(pwd);
        userInfo.setCreatetime(new Date());
        Integer status =  userMapper.insert(userInfo);
        if (status > 0) {
            result.setStatusCode(200);
            result.setResponseData("注册成功");
        }else {
            result.setStatusCode(201);
            result.setResponseData("注册失败");
        }
        return result;
    }
}
