package com.lilac.controller;

import com.lilac.enums.AppHttpCodeEnum;
import com.lilac.exception.SystemException;
import com.lilac.pojo.VO.AdminUserInfoVO;
import com.lilac.pojo.VO.RouterVO;
import com.lilac.pojo.VO.UserInfoVO;
import com.lilac.pojo.entity.LoginUser;
import com.lilac.pojo.entity.Menu;
import com.lilac.pojo.entity.User;
import com.lilac.pojo.result.ResponseResult;
import com.lilac.service.AdminLoginService;
import com.lilac.service.MenuService;
import com.lilac.service.RoleService;
import com.lilac.utils.BeanCopyUtils;
import com.lilac.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private AdminLoginService adminLoginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    /**
     * 登录
     */
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        log.info("用户登录：{}", user);
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/user/getInfo")
    public ResponseResult<AdminUserInfoVO> getInfo(){
        log.info("获取用户信息");
        // 获取当前登录用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 获取用户信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        // 获取角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        // 封装数据
        User user = loginUser.getUser();
        UserInfoVO userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVO.class);
        // 封装数据返回
        AdminUserInfoVO adminUserInfoVo = new AdminUserInfoVO(perms, roleKeyList, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    /**
     * 获取用户路由
     * @return
     */
    @GetMapping("/user/getRouters")
    public ResponseResult<RouterVO> getRouters(){
        log.info("获取用户路由");
        // 获取当前登录用户
        Integer userId = SecurityUtils.getUserId();
        // 获取用户路由
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        return ResponseResult.okResult(new RouterVO(menus));
    }
}
