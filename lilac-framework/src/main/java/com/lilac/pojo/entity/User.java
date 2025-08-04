package com.lilac.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2025-08-04 17:34:34
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //主键
    private Integer id;
    //用户名
    private String userName;
    //密码
    private String password;
    //用户昵称
    private String nickName;
    //性别（0女，1男）
    private String sex;
    //邮箱
    private String email;
    //手机号
    private String phoneNumber;
    //头像
    private String avatar;
    //用户状态（0正常，1异常）
    private String status;
    //用户类型（0普通用户，1管理员）
    private String type;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;
    //逻辑删除
    private Integer delFlag;

}

