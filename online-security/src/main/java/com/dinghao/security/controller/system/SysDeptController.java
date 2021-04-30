package com.dinghao.security.controller.system;

import com.dinghao.common.annotation.Log;
import com.dinghao.common.constant.UserConstants;
import com.dinghao.common.core.domain.controller.BaseController;
import com.dinghao.common.core.domain.entity.SysDept;
import com.dinghao.common.enums.BusinessType;
import com.dinghao.common.exception.ResultBody;
import com.dinghao.common.utils.SecurityUtils;
import com.dinghao.system.service.ISysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门前端控制器
 * @author dinghao
 *
 */
@RestController
@RequestMapping(value = "/system/dept")
@Slf4j
@CrossOrigin
public class SysDeptController extends BaseController {


    @Autowired
    private ISysDeptService deptService;



    /**
     * 获取部门列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list")
    public ResultBody list(SysDept dept)
    {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return ResultBody.success(depts);
    }

    /**
     * 新增部门
     */

    @PreAuthorize("@ss.hasPermi('system:dept:add')")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultBody add(@Validated @RequestBody SysDept dept){
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return ResultBody.error("801","新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(SecurityUtils.getUsername());
        return toAjax(deptService.insertDept(dept));
    }


    /**
     * 获取部门下拉树列表
     */
    @GetMapping(value = "/treeselect")
    public ResultBody treeselect(SysDept dept)
    {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return ResultBody.success(deptService.buildDeptTreeSelect(depts));
    }


    /**
     * 加载对应角色部门列表树
     */
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public ResultBody roleDeptTreeselect(@PathVariable("roleId") Long roleId)
    {
        List<SysDept> depts = deptService.selectDeptList(new SysDept());
        Map<String,Object> result = new HashMap<>();
        result.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
        result.put("depts", deptService.buildDeptTreeSelect(depts));
        return ResultBody.success(result);
    }
}
