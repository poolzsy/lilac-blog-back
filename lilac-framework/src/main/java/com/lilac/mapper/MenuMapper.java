package com.lilac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lilac.pojo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2025-08-05 17:56:50
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    // 根据用户ID查询权限
    List<String> selectPermsByUserId(Integer id);

    // 查询所有菜单
    List<Menu> selectAllRouterMenu();

    // 根据用户ID查询菜单
    List<Menu> selectRouterMenuTreeByUserId(Integer userId);
}
