package com.lilac.service;

import com.lilac.pojo.entity.User;
import com.lilac.pojo.result.ResponseResult;

public interface AdminLoginService {
    /**
     * 管理员登录
     * @param user
     * @return
     */
    ResponseResult login(User user);
}
