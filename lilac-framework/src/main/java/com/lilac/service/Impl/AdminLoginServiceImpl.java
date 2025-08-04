package com.lilac.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lilac.mapper.UserMapper;
import com.lilac.pojo.entity.User;
import com.lilac.pojo.result.ResponseResult;
import com.lilac.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        queryWrapper.eq("password", user.getPassword());
        User loginUser = userMapper.selectOne(queryWrapper);
        return ResponseResult.okResult(loginUser);
    }
}
