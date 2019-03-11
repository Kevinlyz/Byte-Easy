package com.mbyte.easy.config;

/**
 * @Author： 李紫林
 * @Date： 2019/2/18
 * @Description： 用来定义一些常量
 */
public abstract class Constant {
    public static final int JOINRESULT_JOINED = 0; // 设计师已报名项目

    public static final int JOINRESULT_SUCCESS = 1; // 设计师通过报名被选中

    public static final Integer ROLE_DESIGNER = 2001; //设计师身份

    public static final Integer ROLE_PROJECTER = 2002; // 项目方身份

    public static final Integer ROLE_PROFESSOR = 2003; // 专家身份

    public static final Integer UNCHECK = 1000; // 项目方待验收状态码

    public static final Integer CHECKED = 1001; // 项目方已验收状态码

    public static final Integer CHECK_FAIL = 1002; // 项目方验收未通过状态码

    public static final Integer FINISH = 1003; // 项目方已结项状态码

    public static final String UNLOGIN = "123456"; // 尚未登录状态码

    public static final String LOGIN = "654321"; // 已登录
}
