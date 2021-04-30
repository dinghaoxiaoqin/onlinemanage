package com.dinghao.system.service;


import com.dinghao.common.core.domain.entity.SysUser;

import java.util.List;


/**
 * 用户 业务层
 * 
 * @author dinghao
 */
public interface ISysUserService
{


    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);

    /**
     * 检查用户名称是否唯一
     * @param userName
     * @return
     */
    String checkUserNameUnique(String userName);

    /**
     * 校验手机号是否唯一
     * @param sysUser
     * @return
     */
    String checkPhoneUnique(SysUser sysUser);

    /**
     * 检查油箱是否唯一
     * @param sysUser
     * @return
     */
    String checkEmailUnique(SysUser sysUser);

    Integer insertUser(SysUser sysUser);

    /**
     * 获取用户列表
     * @param user
     * @return
     */
    List<SysUser> selectUserList(SysUser user);
    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    SysUser selectUserById(Long userId);
}
