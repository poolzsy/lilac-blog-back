package com.lilac.service.Impl;

import com.lilac.constants.SystemConstants;
import com.lilac.constants.SystemExceptionConstants;
import com.lilac.pojo.entity.LoginUser;
import com.lilac.pojo.entity.User;
import com.lilac.pojo.result.ResponseResult;
import com.lilac.service.AdminLoginService;
import com.lilac.utils.JwtUtil;
import com.lilac.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    /**
     * 管理员登录
     *
     * @param user
     * @return
     */
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断认证结果
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException(SystemExceptionConstants.USERNAME_OR_PASSWORD_ERROR);
        }
        // 获取userId，生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        // 存入redis
        redisCache.setCacheObject(SystemConstants.REDIS_ADMIN_LOGIN_KEY + userId, loginUser);

        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseResult.okResult(map);
    }
}
