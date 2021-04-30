package com.dinghao.security.controller.system;

import com.dinghao.common.annotation.Log;
import com.dinghao.common.constant.UserConstants;
import com.dinghao.common.core.domain.controller.BaseController;
import com.dinghao.common.core.domain.entity.SysRole;
import com.dinghao.common.core.domain.entity.SysUser;
import com.dinghao.common.core.page.TableDataInfo;
import com.dinghao.common.enums.BusinessType;
import com.dinghao.common.exception.ResultBody;
import com.dinghao.common.utils.SecurityUtils;
import com.dinghao.common.utils.StringUtils;
import com.dinghao.common.utils.poi.ExcelUtil;
import com.dinghao.system.service.ISysPostService;
import com.dinghao.system.service.ISysRoleService;
import com.dinghao.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统用户管理
 * @author dinghao
 */
@RestController
@RequestMapping(value = "/system/user")
@Slf4j
@CrossOrigin
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理",businessType = BusinessType.INSERT)
    @PostMapping(value = "/add")
    public ResultBody add(@Validated @RequestBody SysUser sysUser){
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(sysUser.getUserName())))
        {
            return ResultBody.error("701","新增用户'" + sysUser.getUserName() + "'失败，登录账号已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(sysUser)))
        {
            return ResultBody.error("702","新增用户'" + sysUser.getUserName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(sysUser)))
        {
            return ResultBody.error("703","新增用户'" + sysUser.getUserName() + "'失败，邮箱账号已存在");
        }
        sysUser.setCreateBy(SecurityUtils.getUsername());
        sysUser.setPassword(SecurityUtils.encryptPassword(sysUser.getPassword()));
        return toAjax(userService.insertUser(sysUser));
    }

    /**
     * 用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public ResultBody  list(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return ResultBody.success(getDataTable(list));
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = { "/", "/{userId}" })
    public ResultBody getInfo(@PathVariable(value = "userId", required = false) Long userId)
    {
        Map<String,Object> result = new HashMap<>();
        List<SysRole> roles = roleService.selectRoleAll();
        result.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        result.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId))
        {
            result.put("data", userService.selectUserById(userId));
            result.put("postIds", postService.selectPostListByUserId(userId));
            result.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ResultBody.success(result);
    }


    @GetMapping("/importTemplate")
    public ResultBody importTemplate()
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }
}
