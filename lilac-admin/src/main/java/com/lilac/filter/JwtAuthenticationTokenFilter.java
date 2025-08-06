package com.lilac.filter;

import com.alibaba.fastjson.JSON;
import com.lilac.constants.SystemConstants;
import com.lilac.pojo.entity.LoginUser;
import com.lilac.pojo.result.ResponseResult;
import com.lilac.enums.AppHttpCodeEnum;
import com.lilac.utils.JwtUtil;
import com.lilac.utils.RedisCache;
import com.lilac.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从"Authorization" 请求头获取 token 字符串
        String authHeader = request.getHeader("Authorization");

        // 校验 "Authorization" 头是否存在且格式正确 (以 "Bearer " 开头)
        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
            // 如果没有 token 或格式不正确，直接放行，后续过滤器会处理
            filterChain.doFilter(request, response);
            return;
        }

        // 提取真正的 token ("Bearer " 后面部分)
        String token = authHeader.substring(7);

        // 解析获取userid
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            // token超时 token非法
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        String userid = claims.getSubject();
        // 从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject(SystemConstants.REDIS_ADMIN_LOGIN_KEY + userid);
        // 存入SecurityContextHolder
        if (Objects.isNull(loginUser)) {
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
