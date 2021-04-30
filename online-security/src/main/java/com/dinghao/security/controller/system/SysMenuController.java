package com.dinghao.security.controller.system;


import com.dinghao.common.annotation.Log;
import com.dinghao.common.annotation.RepeatSubmit;
import com.dinghao.common.core.domain.controller.BaseController;
import com.dinghao.common.core.domain.entity.SysMenu;
import com.dinghao.common.core.domain.model.LoginUser;
import com.dinghao.common.enums.BusinessType;
import com.dinghao.common.exception.CommonEnum;
import com.dinghao.common.exception.ResultBody;

import com.dinghao.common.utils.ServletUtils;
import com.dinghao.core.web.service.TokenService;
import com.dinghao.system.service.ISysMenuService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 菜单前端控制器
 */
@RestController
@RequestMapping(value = "/system/menu")
@Slf4j
@CrossOrigin
public class SysMenuController extends BaseController {

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private TokenService tokenService;


    /**
     * 添加菜单
     */
    @PreAuthorize("@ss.hasPermi('system:menu:add')")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/add")
    @RepeatSubmit
    public ResultBody add(@Validated @RequestBody SysMenu menu) {
        Integer result = menuService.addMenu(menu);
        if (result == -1) {
            return ResultBody.error("545", "新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (result == -2) {
            return ResultBody.error("545", "新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        return result > 0 ? ResultBody.success("新增菜单成功") : ResultBody.error(CommonEnum.ADD_MENU_ERROR.getResultCode(), CommonEnum.ADD_MENU_ERROR.getResultMsg());
    }

    /**
     * 获取菜单列表
     */
    @PreAuthorize("@ss.hasPermi('system:menu:list')")
    @GetMapping(value = "/list")
    public ResultBody list(SysMenu menu) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getUser().getUserId();
        return ResultBody.success(menuService.getMenuList(userId, menu));
    }

    /**
     * 获取菜单下拉框列表
     */
    @GetMapping(value = "/treeselect")
    public ResultBody treeselect(SysMenu menu) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getUser().getUserId();
        return ResultBody.success(menuService.treeselect(userId, menu));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public ResultBody roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Map<String, Object> result = menuService.roleMenuTreeselect(loginUser.getUser().getUserId(), roleId);
        return ResultBody.success(result);
    }

    /**
     * 根据菜单编号获取菜单详情信息
     */
    @PreAuthorize("@ss.hasPermi('system:menu:query')")
    @GetMapping(value = "/queryMenu")
    public ResultBody queryMenu(@RequestParam(value = "menuId") Long menuId){
        return ResultBody.success(menuService.selectMenuById(menuId));
    }

    /**
     * 删除菜单
     */
    @PreAuthorize("@ss.hasPermi('system:menu:remove')")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public ResultBody remove(@PathVariable("menuId") Long menuId){
        //判断是否存在子菜单
      Integer isDel =   menuService.hasChildByMenuId(menuId);
        if (isDel > 0) {
            return ResultBody.error(CommonEnum.ADD_MENU_ERROR.getResultCode(),"存在子菜单,不允许删除");
        }
        //是否已经被分配给角色
       Integer isRole =  menuService.checkMenuExistRole(menuId);
        if (isRole > 0) {
            return ResultBody.error(CommonEnum.ADD_MENU_ERROR.getResultCode(),"菜单已分配,不允许删除");
        }
        Integer result = menuService.deleteMenuById(menuId);
        return result > 0? ResultBody.success("删除菜单成功"): ResultBody.error(CommonEnum.ADD_MENU_ERROR.getResultCode(),"菜单删除失败");
    }
}
