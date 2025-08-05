package com.lilac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lilac.pojo.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2025-08-05 17:59:37
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过用户id查询角色权限
     *
     * @param id
     * @return
     */
    List<String> selectRoleKeyByUserId(Integer id);
}
