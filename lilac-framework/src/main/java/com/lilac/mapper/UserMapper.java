package com.lilac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lilac.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2025-08-04 17:33:58
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
