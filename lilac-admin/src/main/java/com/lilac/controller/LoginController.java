package com.lilac.controller;

import com.lilac.enums.AppHttpCodeEnum;
import com.lilac.pojo.entity.User;
import com.lilac.pojo.result.ResponseResult;
import com.lilac.service.AdminLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        log.info("用户登录：{}", user);
        if(!StringUtils.hasText(user.getUserName())){
            return ResponseResult.errorResult(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        ResponseResult login = adminLoginService.login(user);
        return ResponseResult.okResult(login);
    }
}
