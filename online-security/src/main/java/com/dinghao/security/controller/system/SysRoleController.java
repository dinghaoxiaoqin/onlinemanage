package com.dinghao.security.controller.system;

import com.dinghao.common.annotation.Log;
import com.dinghao.common.constant.UserConstants;
import com.dinghao.common.core.domain.controller.BaseController;
import com.dinghao.common.core.domain.entity.SysRole;

import com.dinghao.common.core.domain.model.LoginUser;
import com.dinghao.common.enums.BusinessType;
import com.dinghao.common.exception.CommonEnum;
import com.dinghao.common.exception.ResultBody;
import com.dinghao.common.utils.SecurityUtils;
import com.dinghao.common.utils.ServletUtils;
import com.dinghao.common.utils.StringUtils;
import com.dinghao.core.web.service.SysPermissionService;
import com.dinghao.core.web.service.TokenService;
import com.dinghao.system.service.ISysRoleService;
import com.dinghao.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色前端控制器
 * @author dinghao
 *
 */
@RestController
@RequestMapping(value = "/system/role")
@Slf4j
@CrossOrigin
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService roleService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private ISysUserService userService;


    /**
     * 角色列表
     * @param role
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:role:list')")
    @GetMapping("/list")
    public ResultBody list(SysRole role)
    {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return ResultBody.success(getDataTable(list));
    }



    /**
     * 新增角色
     */
    @PreAuthorize("@ss.hasPermi('system:role:add')")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/add")
    public ResultBody add(@Validated @RequestBody SysRole role)
    {
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
        {
            return ResultBody.error(CommonEnum.ADD_ROLE_ERROR.getResultCode(),"新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
        {
            return ResultBody.error(CommonEnum.ADD_ROLE_ERROR.getResultCode(),"新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setCreateBy(SecurityUtils.getUsername());
        Integer result = roleService.insertRole(role);
        return result > 0? ResultBody.success("新增角色成功"):ResultBody.error(CommonEnum.ADD_ROLE_ERROR.getResultCode(),"新增角色失败");

    }

    /**
     * 修改保存数据权限
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/dataScope")
    public ResultBody dataScope(@RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);
        return toAjax(roleService.authDataScope(role));
    }

    /**
     * 根据角色编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:role:query')")
    @GetMapping(value = "/{roleId}")
    public ResultBody getInfo(@PathVariable Long roleId)
    {
        return ResultBody.success(roleService.selectRoleById(roleId));
    }



    /**
     * 修改保存角色
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/edit")
    public ResultBody edit(@Validated @RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
        {
            return ResultBody.error(CommonEnum.ADD_ROLE_ERROR.getResultCode(),"修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
        {
            return ResultBody.error(CommonEnum.ADD_ROLE_ERROR.getResultCode(),"修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setUpdateBy(SecurityUtils.getUsername());
        //更新角色权限
        if (roleService.updateRole(role) > 0)
        {
            // 更新缓存用户权限
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            if (StringUtils.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin())
            {
                loginUser.setPermissions(sysPermissionService.getMenuPermission(loginUser.getUser()));
                loginUser.setUser(userService.selectUserByUserName(loginUser.getUser().getUserName()));
                tokenService.setLoginUser(loginUser);
            }
            return ResultBody.success("修改角色成功");
        }
        return ResultBody.error(CommonEnum.ADD_ROLE_ERROR.getResultCode(),"修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }


    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    public ResultBody changeStatus(@RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);
        role.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(roleService.updateRoleStatus(role));
    }

    /**
     * 删除角色
     */
    @PreAuthorize("@ss.hasPermi('system:role:remove')")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PostMapping("/{roleIds}")
    public ResultBody remove(@PathVariable Long[] roleIds)
    {
        return toAjax(roleService.deleteRoleByIds(roleIds));
    }

    /**
     * 获取角色选择框列表
     */
    @PreAuthorize("@ss.hasPermi('system:role:query')")
    @GetMapping("/optionselect")
    public ResultBody optionselect()
    {
        return ResultBody.success(roleService.selectRoleAll());
    }
}
