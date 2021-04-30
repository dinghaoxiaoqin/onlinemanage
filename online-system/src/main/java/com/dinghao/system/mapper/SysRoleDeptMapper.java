package com.dinghao.system.mapper;


import com.dinghao.system.domain.SysRoleDept;

import java.util.List;

/**
 * 角色与部门关联表 数据层
 * 
 * @author ruoyi
 */
public interface SysRoleDeptMapper
{

    Integer deleteRoleDeptByRoleId(Long roleId);

    Integer batchRoleDept(List<SysRoleDept> list);
}
