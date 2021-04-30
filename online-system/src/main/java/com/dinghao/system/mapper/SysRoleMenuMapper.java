package com.dinghao.system.mapper;


import com.dinghao.system.domain.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色与菜单关联表 数据层
 *
 * @author ruoyi
 */
public interface SysRoleMenuMapper {


    /**
     * 批量新增角色菜单信息
     *
     * @param roleMenuList 角色菜单列表
     * @return 结果
     */
    Integer batchRoleMenu(List<SysRoleMenu> roleMenuList);

    Integer deleteRoleMenuByRoleId(@Param("roleId") Long roleId);
}
