package com.dinghao.common.exception;

/**
 * 返回码枚举
 */
public enum CommonEnum implements BaseErrorInfoInterface{

    // 数据操作错误定义
    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400", "请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401", "请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "未知异常，请联系管理员"),
    SERVER_BUSY("503", "服务器正忙，请稍后再试!"),
    REQUEST_METHOD_SUPPORT_ERROR("40001","当前请求方法不支持"),
    USER_INPUT_ERROR("401","用户名或者密码不能为空"),
    USER_NAME_PASSWORD_ERROR("402","用户名或者密码输入错误"),
    LOGIN_FAIL("501","登录失败"),
    SUBMIT_ERROR("512","请勿重复提交"),
    ADD_USER("402","添加用户失败"),
    UPDATE_USER("526","修改用户失败"),
    LOGIN_ERROR("406","未登录，请先登录"),
    TOKEN_EXPIRE("407","账号已过期，请重新登录"),
    ACCOUNT_ERROR("409","账号不存在，请重新输入"),
    PASSWORD_ERROR("410","密码不正确，请重新输入"),
    ACCESS_ERROR("411","无权访问，请联系超级管理员"),
    ADD_MENU_ERROR("510","添加菜单失败，请联系超级管理员"),
    ADD_ORG_ERROR("522","添加组织机构失败，请联系超级管理员"),
    UPDATE_ORG_ERROR("523","修改组织机构失败，请联系超级管理员"),
    DELETE_ORG_ERROR("524","删除组织机构失败，请联系超级管理员"),
    ADD_ROLE_ERROR("513","添加角色失败，请联系超级管理员"),
    ADD_API_ERROR("510","添加菜单失败，请联系超级管理员"),
    ADD_USERROLE_ERROR("514","分配用户角色失败，请联系超级管理员"),
    ADD_ROLEAPI_ERROR("515","分配接口权限失败，请联系超级管理员"),
    ADD_ROLEMENU_ERROR("516","分配菜单权限失败，请联系超级管理员"),
    API_LEVEL_ERROR("517","请先在数据库内为API配置一个分类的根节点，level=1"),
    MENU_LEVEL_ERROR("518","请先在数据库内为菜单配置一个分类的根节点，level=1"),
    ORG_USER_ERROR("519","查询参数用户名组织id不能为空"),
    UPDATE_ERROR("520","修改操作必须带主键"),
    UPDATE_ROLE_ERROR("525","修改角色失败，请联系管理员"),
    DELETE_ERROR("521","删除操作必须带主键"),
    AUTHOR_ERROR("511","无权访问，请联系管理员"),
    ADD_DICT_ERROR("527","新增字典失败，请联系管理员"),
    UPDATE_DICT_ERROR("528","更新字典失败，请联系管理员"),
    ADD_CONFLG_ERROR("529","新增配置失败，请联系管理员"),
    UPDATE_CONFLG_ERROR("530","修改配置失败，请联系管理员"),
    DELETE_CONFLG_ERROR("531","删除配置失败，请联系管理员"),
    UPDATE_ROLE_STATUS("525","无权访问，请联系管理员")
    ;
    /**
     * 错误码
     */
    private String resultCode;
    /**
     * 错误描述
     */
    private String resultMsg;

    CommonEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
