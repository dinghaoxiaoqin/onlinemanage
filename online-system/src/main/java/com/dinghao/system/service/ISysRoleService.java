package com.dinghao.system.service;


import com.dinghao.common.core.domain.entity.SysRole;

import java.util.List;
import java.util.Set;

/**
 * 角色管理
 */
public interface ISysRoleService {

    Set<String> selectRolePermissionByUserId(Long userId);


    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
     List<SysRole> selectRoleList(SysRole role);
    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<SysRole> selectRoleAll();


    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
     List<Integer> selectRoleListByUserId(Long userId);
    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    String checkRoleNameUnique(SysRole role);
    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    String checkRoleKeyUnique(SysRole role);

    Integer insertRole(SysRole role);


    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
     SysRole selectRoleById(Long roleId);


    /**
     * 校验角色是否允许操作
     *
     * @param role 角色信息
     */
    void checkRoleAllowed(SysRole role);

    Integer authDataScope(SysRole role);

    Integer updateRole(SysRole role);

    Integer updateRoleStatus(SysRole role);

    Integer deleteRoleByIds(Long[] roleIds);
}
