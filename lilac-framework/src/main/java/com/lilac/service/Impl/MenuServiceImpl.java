package com.lilac.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilac.constants.SystemConstants;
import com.lilac.mapper.MenuMapper;
import com.lilac.pojo.entity.Menu;
import com.lilac.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2025-08-05 17:56:51
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据用户id查询权限
     *
     */
    @Override
    public List<String> selectPermsByUserId(Integer id) {
        //如果是超级管理员，返回所有的权限
        if(id.equals(1)){
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Menu::getType, SystemConstants.MENU_TYPE, SystemConstants.BUTTON_TYPE);
            queryWrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(queryWrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        // 获取权限信息
        return getBaseMapper().selectPermsByUserId(id);
    }

    /**
     * 获取当前用户权限信息
     *
     */
    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Integer userId) {
        List<Menu> menus = null;
        // 判断是否是超级管理员
        if(userId.equals(1)){
            // 如果是 返回所有符合要求的Menu
            menus = getBaseMapper().selectAllRouterMenu();
        }else {
            // 否则 当前用户所具有的Menu
            menus = getBaseMapper().selectRouterMenuTreeByUserId(userId);
        }
        return builderMenuTree(menus);
    }

    /**
     * 构建前端所需要树结构
     *
     */
    private List<Menu> builderMenuTree(List<Menu> menus) {
        return menus.stream()
                .filter(menu -> menu.getParentId().equals(0))
                .peek(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
    }

    /**
     * 获取子Menu信息
     *
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        return menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .peek(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
    }
}
