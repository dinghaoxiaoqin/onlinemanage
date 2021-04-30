package com.dinghao.system.mapper;


import com.dinghao.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * 用户与角色关联表 数据层
 * 
 * @author ruoyi
 */
public interface SysUserRoleMapper
{


    /**
     * 批量新增用户角色信息
     * 
     * @param userRoleList 用户角色列表
     * @return 结果
     */
    public int batchUserRole(List<SysUserRole> userRoleList);


    Integer countUserRoleByRoleId(@Param("roleId") Long roleId);
}
