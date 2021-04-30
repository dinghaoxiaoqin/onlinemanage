package com.dinghao.system.service;


import com.dinghao.common.core.domain.TreeSelect;
import com.dinghao.common.core.domain.entity.SysMenu;
import com.dinghao.system.domain.vo.RouterVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单 业务层
 * 
 * @author ruoyi
 */
public interface ISysMenuService
{


    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 增加菜单
     * @param menu
     * @return
     */
    Integer addMenu(SysMenu menu);

    /**
     * 获取菜单列表
     * @param menu
     * @return
     */
    List<SysMenu> getMenuList(Long userId,SysMenu menu);

    List<TreeSelect> treeselect(Long userId, SysMenu menu);

    Map<String,Object> roleMenuTreeselect(Long userId, Long roleId);

    Object selectMenuById(Long menuId);
    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    List<RouterVo> buildMenus(List<SysMenu> menus);

    Integer hasChildByMenuId(Long menuId);

    Integer checkMenuExistRole(Long menuId);

    Integer deleteMenuById(Long menuId);
}
