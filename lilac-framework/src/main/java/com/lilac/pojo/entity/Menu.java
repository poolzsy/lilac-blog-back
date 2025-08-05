package com.lilac.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 菜单表(Menu)表实体类
 *
 * @author makejava
 * @since 2025-08-05 17:56:50
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    private Integer id;
    //菜单名称
    private String menuName;
    //父id（最高级为0）
    private Integer parentId;
    //排序
    private Integer sort;
    //路径
    private String path;
    //权限标识
    private String perms;
    //是否内嵌窗口（0否，1是）
    private char isFrame;
    //组件路由
    private String component;
    //菜单类型（M目录，C菜单，F按钮）
    private String type;
    //状态（0正常，1异常）
    private String status;
    //菜单图标
    private String icon;
    //备注
    private String remark;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;
    //逻辑删除
    private String delFlag;

    @TableField(exist = false)
    private List<Menu> children;

}

