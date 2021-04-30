package com.dinghao.system.mapper;


import com.dinghao.common.core.domain.entity.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 部门管理 数据层
 * 
 * @author dinghao
 */
public interface SysDeptMapper
{

    /**
     * 新增部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(SysDept dept);


    SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    SysDept selectDeptById(@Param("parentId") Long parentId);

    List<SysDept> selectDeptList(SysDept dept);

    List<Integer> selectDeptListByRoleId(@Param("roleId") Long roleId);
}
