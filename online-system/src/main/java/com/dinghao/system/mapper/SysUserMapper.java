package com.dinghao.system.mapper;


import com.dinghao.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 用户表 数据层
 * 
 * @author ruoyi
 */
public interface SysUserMapper
{


    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);


    Integer checkUserNameUnique(@Param("userName") String userName);

    SysUser checkPhoneUnique(@Param("phonenumber") String phonenumber);

    SysUser checkEmailUnique(@Param("email") String email);

    Integer insertUser(SysUser sysUser);

    List<SysUser> selectUserList(SysUser user);

    SysUser selectUserById(@Param("userId") Long userId);
}
