package com.lilac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilac.pojo.entity.Menu;

import java.util.List;

/**
 * 菜单表(Menu)表服务接口
 *
 * @author makejava
 * @since 2025-08-05 17:56:51
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据用户id查询权限
     * @param id
     * @return
     */
    List<String> selectPermsByUserId(Integer id);

    /**
     * 根据用户id查询菜单树
     * @param userId
     * @return
     */
    List<Menu> selectRouterMenuTreeByUserId(Integer userId);
}
