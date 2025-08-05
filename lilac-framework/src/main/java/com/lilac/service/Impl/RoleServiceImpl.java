package com.lilac.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilac.mapper.RoleMapper;
import com.lilac.pojo.entity.Role;
import com.lilac.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色表(Role)表服务实现类
 *
 * @author makejava
 * @since 2025-08-05 17:59:37
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * 根据用户id查询角色信息
     *
     * @param id
     * @return
     */
    @Override
    public List<String> selectRoleKeyByUserId(Integer id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if(id.equals(1)){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }
}
