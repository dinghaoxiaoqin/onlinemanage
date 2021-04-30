package com.dinghao.system.mapper;


import com.dinghao.common.core.domain.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表 数据层
 * 
 * @author ruoyi
 */
public interface SysRoleMapper
{


    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    public List<SysRole> selectRolePermissionByUserId(Long userId);


    List<SysRole> selectRoleList(SysRole role);

    List<Integer> selectRoleListByUserId(@Param("userId") Long userId);

    SysRole checkRoleNameUnique(@Param("roleName") String roleName);

    SysRole checkRoleKeyUnique(@Param("roleKey") String roleKey);
    /**
     * 新增角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
     Integer insertRole(SysRole role);

    SysRole selectRoleById(@Param("roleId") Long roleId);

    Integer updateRole(SysRole role);

    Integer deleteRoleByIds(@Param("roleIds") Long[] roleIds);
}
