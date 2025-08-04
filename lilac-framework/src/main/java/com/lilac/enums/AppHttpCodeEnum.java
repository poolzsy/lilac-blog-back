package com.lilac.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    // 登录
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    SYSTEM_ERROR(500, "出现错误"),
    USERNAME_EXIST(501, "用户名已存在"),
    PHONENUMBER_EXIST(502, "手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505, "用户名或密码错误"),
    CONTENT_NO_NULL(506,"评论内容不能为空"),
    FILE_NOT_NULL(507, "上传文件不能为空"),
    FILE_EMPTY(508, "上传文件内容为空"),
    FILE_NAME_INVALID(509, "文件名无效"),
    FILE_UPLOAD_ERROR(510, "文件上传失败"),
    USERNAME_NOT_NULL(511, "用户名不能为空"),
    PASSWORD_NOT_NULL(512, "密码不能为空"),
    PHONENUMBER_NOT_NULL(513, "手机号不能为空"),
    EMAIL_NOT_NULL(514, "邮箱不能为空"),
    MENU_PARENT_NOT_ALLOW_SELF(515, "上级菜单不能为自身"),
    MENU_HAS_CHILDREN(516, "菜单存在子菜单，不允许删除"),
    ROLE_HAS_USER(517, "角色下存在用户"),
    DELETE_CURRENT_USER_FORBIDDEN(518, "不能删除当前登录用户"),
    USER_NOT_EXIST(519, "用户不存在"),
    PARAM_INVALID(520, "不能为空");
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}