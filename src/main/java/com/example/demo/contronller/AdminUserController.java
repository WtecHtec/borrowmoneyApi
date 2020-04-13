package com.example.demo.contronller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.mapper.UserMapper;
import com.example.demo.dao.pojo.User;
import com.example.demo.jwt.JwtIgnore;
import com.example.demo.jwt.Audience;
import com.example.demo.jwt.JwtTokenUtil;
import com.example.demo.timedtask.ScheduledData;
import com.example.demo.uitl.BaseResponse;
import com.example.demo.uitl.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy@Slf4j
 * Date：2019/7/18 10:41
 * Version: v1.0
 * ========================
 */
@Slf4j
@RestController
public class AdminUserController {
    private static final Logger loger = LoggerFactory.getLogger(AdminUserController.class);
    @Autowired
    private Audience audience;

    @Autowired
    private UserMapper userMapper;

    @JwtIgnore
    @PostMapping("/login")
    public BaseResponse<JSONObject>  adminLogin( @RequestBody User userInfo,HttpServletResponse response) {
        BaseResponse<JSONObject>  rs  =  new BaseResponse<>();
        JSONObject result = new JSONObject();
        // 这里模拟测试, 默认登录成功，返回用户ID和角色信息
        User loginUser =  userMapper.selectAnyParams(userInfo);
        if (null == loginUser || null == userInfo.getUseremail() || null == userInfo.getPwd() ){
            rs.setStatusCode(201);
            result.put("statusMsg","请输入正确的邮箱或密码！");
            rs.setResponseData(result);
            return rs;
        }
        // 创建token
        String token = JwtTokenUtil.createJWT(loginUser.getUserid(), loginUser.getUsername(), "user", audience);
        log.info("### 登录成功, token={} ###", token);
        // 将token放在响应头
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        // 将token响应给客户端
        result.put("userid", loginUser.getUserid());
        // 将token响应给客户端
        result.put("token", token);
        rs.setStatusCode(200);
        rs.setResponseData(result);
        return  rs;
    }
    @JwtIgnore
    @GetMapping("/users")
    public Result userList() {
        log.info("### 查询所有用户列表 ###");
        loger.info("### 查询所有用户列表 ###");
        return Result.SUCCESS();
    }
}
