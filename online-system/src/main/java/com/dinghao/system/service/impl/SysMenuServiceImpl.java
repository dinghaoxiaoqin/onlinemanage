package com.dinghao.system.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.dinghao.common.constant.Constants;
import com.dinghao.common.constant.UserConstants;
import com.dinghao.common.core.domain.TreeSelect;
import com.dinghao.common.core.domain.entity.SysMenu;
import com.dinghao.common.core.domain.entity.SysUser;
import com.dinghao.common.utils.SecurityUtils;
import com.dinghao.common.utils.StringUtils;
import com.dinghao.common.utils.ToolUtil;
import com.dinghao.system.domain.vo.MetaVo;
import com.dinghao.system.domain.vo.RouterVo;
import com.dinghao.system.mapper.SysMenuMapper;
import com.dinghao.system.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单 业务层处理
 *
 * @author dinghao
 */
@Service
@Slf4j
public class SysMenuServiceImpl implements ISysMenuService {
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private SysMenuMapper menuMapper;


    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer addMenu(SysMenu menu) {
        String result = "";
        //校验菜单是否重复
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        SysMenu sysMenu = menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(sysMenu) && !ToolUtil.equals(sysMenu.getMenuId(), menuId)) {
            result = UserConstants.NOT_UNIQUE;
        } else {
            result = UserConstants.UNIQUE;
        }
        if (ToolUtil.equals(UserConstants.NOT_UNIQUE, result)) {
            return -1;
        } else if (ToolUtil.equals(UserConstants.YES_FRAME, menu.getIsFrame()) && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
            return -2;
        }
        menu.setCreateBy(SecurityUtils.getUsername());
        return menuMapper.insertMenu(menu);
    }

    /**
     * 获取菜单列表
     *
     * @param menu
     * @return
     */
    @Override
    public List<SysMenu> getMenuList(Long userId, SysMenu menu) {
        List<SysMenu> menuList = null;
        // 管理员显示所有菜单信息
        if (SysUser.isAdmin(userId)) {
            menuList = menuMapper.selectMenuList(menu);
        } else {
            menu.getParams().put("userId", userId);
            menuList = menuMapper.selectMenuListByUserId(menu);
        }
        return menuList;
    }

    /**
     * 获取菜单树形列表
     *
     * @param userId
     * @param menu
     * @return
     */
    @Override
    public List<TreeSelect> treeselect(Long userId, SysMenu menu) {
        List<SysMenu> menuList = null;
        // 管理员显示所有菜单信息
        if (SysUser.isAdmin(userId)) {
            menuList = menuMapper.selectMenuList(menu);
        } else {
            menu.getParams().put("userId", userId);
            menuList = menuMapper.selectMenuListByUserId(menu);
        }
        List<SysMenu> menuTrees = buildMenuTree(menuList);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 获取角色对应的菜单
     *
     * @param userId
     * @param roleId
     * @return
     */
    @Override
    public Map<String, Object> roleMenuTreeselect(Long userId, Long roleId) {
        HashMap<String, Object> result = new HashMap<>();
        List<SysMenu> menuList = getMenuList(userId, new SysMenu());
        //角色已经选中的菜单menuId
        result.put("checkedKeys", menuMapper.selectMenuListByRoleId(roleId));
        //菜单树形列表
        List<TreeSelect> list = buildMenuTreeSelect(menuList);
        result.put("menus", list);
        return result;
    }

    /**
     * 获取菜单详情
     * @param menuId
     * @return
     */
    @Override
    public SysMenu selectMenuById(Long menuId) {
        return menuMapper.selectMenuById(menuId);
    }
    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户名称
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus = null;
        if (SecurityUtils.isAdmin(userId))
        {
            menus = menuMapper.selectMenuTreeAll();
        }
        else
        {
            menus = menuMapper.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menus, 0);
    }

    /**
     * 构建前端路由所需要的菜单
     * @param menus  菜单列表
     * @return  路由列表
     */
    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenu menu : menus)
        {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            //获取路由名称
            router.setName(getRouteName(menu));
            //获取路由地址
            router.setPath(getRouterPath(menu));
            //获取组件信息
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
            List<SysMenu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType()))
            {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            }
            else if (isMeunFrame(menu))
            {
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 是否存在菜单子节点
     * @param menuId
     * @return
     */
    @Override
    public Integer hasChildByMenuId(Long menuId) {
        return  menuMapper.hasChildByMenuId(menuId);
    }

    /**
     * 是否已经被分配角色
     * @param menuId
     * @return
     */
    @Override
    public Integer checkMenuExistRole(Long menuId) {
        return menuMapper.checkMenuExistRole(menuId);
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @Override
    public Integer deleteMenuById(Long menuId) {
        return menuMapper.deleteMenuById(menuId);
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu)
    {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame()))
        {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMeunFrame(menu))
        {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenu menu)
    {
        String component = UserConstants.LAYOUT;
        //不是菜单的跳转
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMeunFrame(menu))
        {
            component = menu.getComponent();
        }
        return component;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenu menu)
    {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMeunFrame(menu))
        {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMeunFrame(SysMenu menu)
    {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame().equals(UserConstants.NO_FRAME);
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId)
    {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (SysMenu menu: list)
        {
            //SysMenu t = (SysMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (menu.getParentId() == parentId)
            {
                recursionFn(list, menu);
                returnList.add(menu);
            }
        }
        return returnList;
    }

    private List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menuList) {
        List<SysMenu> menuTrees = buildMenuTree(menuList);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */

    public List<SysMenu> buildMenuTree(List<SysMenu> menus) {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (SysMenu menu : menus) {
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (menu.getParentId() == 0) {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if (CollUtil.isEmpty(returnList)) {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 递归菜单列表
     *
     * @param menus
     * @param menu
     */
    private void recursionFn(List<SysMenu> menus, SysMenu menu) {
        //获取该菜单的所有子节点
        List<SysMenu> childList = getChildList(menus, menu);
        menu.setChildren(childList);
        for (SysMenu child : childList) {
            //判断该节点是否还有子节点
            if (hasChild(menus, child)) {
                for (SysMenu sysMenu : childList) {
                    //还有子节点
                    recursionFn(menus, sysMenu);
                }
            }
        }

    }

    /**
     * 得到子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        List<SysMenu> tlist = new ArrayList<SysMenu>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext()) {
            SysMenu n = (SysMenu) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }


    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
