package com.lilac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lilac.pojo.entity.Role;

import java.util.List;

/**
 * 角色表(Role)表服务接口
 *
 * @author makejava
 * @since 2025-08-05 17:59:37
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户id查询角色权限
     *
     * @param id 用户id
     * @return 角色权限列表
     */
    List<String> selectRoleKeyByUserId(Integer id);
}
