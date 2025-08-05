package com.lilac.utils;

import com.lilac.pojo.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("用户未登录");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUser) {
            return (LoginUser) principal;
        } else {
            throw new RuntimeException("登录用户类型错误: " + principal.getClass().getName());
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin() {
        Integer id = getLoginUser().getUser().getId();
        return id != null && id.equals(1L);
    }

    public static Integer getUserId() {
        try {
            LoginUser loginUser = getLoginUser();
            return loginUser.getUser().getId();
        } catch (Exception e) {
            return -1; // 或配置默认系统ID
        }
    }
}