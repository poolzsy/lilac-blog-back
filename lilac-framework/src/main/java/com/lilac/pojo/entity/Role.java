package com.lilac.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 角色表(Role)表实体类
 *
 * @author makejava
 * @since 2025-08-05 17:59:37
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private Integer id;
    //角色名称
    private String roleName;
    //角色权限字符串
    private String roleKey;
    //状态（0正常，1异常）
    private String status;
    //排序
    private Integer sort;
    //备注
    private String remark;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String delFlag;

}

