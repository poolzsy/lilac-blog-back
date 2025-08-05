package com.lilac.constants;

public class SystemConstants {
    // 文章是草稿
    public static final String ARTICLE_STATUS_DRAFT = "1";
    // 文章是正常发布
    public static final String ARTICLE_STATUS_NORMAL = "0";
    // 最多显示10条
    public static final int MAX_PAGE_SIZE = 10;
    // 正常状态分类
    public static final String STATUS_NORMAL = "0";
    // 友链审核通过
    public static final String LINK_STATUS_NORMAL = "0";

    public static final String REDIS_USER_LOGIN_KEY = "bloglogin:";

    public static final String REDIS_ADMIN_LOGIN_KEY = "adminlogin:";

    public static final String COMMENT_ROOT_ID = "-1";

    public static final String ARTICLE_COMMENT = "0";

    public static final String LINK_COMMENT = "1";

    public static final String REDIS_ARTICLE_VIEW_COUNT = "article:viewCount";
    // 菜单类型
    public static final String MENU_TYPE = "C";
    // 按钮类型
    public static final String BUTTON_TYPE = "F";

    public static final String NORMAL = "0";

    public static final String ADMAIN = "1";
}
