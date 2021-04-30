package com.dinghao.security.controller.system;

import cn.hutool.core.collection.CollUtil;
import com.dinghao.common.annotation.Log;
import com.dinghao.common.constant.UserConstants;
import com.dinghao.common.core.domain.controller.BaseController;
import com.dinghao.common.core.page.TableDataInfo;
import com.dinghao.common.enums.BusinessType;
import com.dinghao.common.exception.CommonEnum;
import com.dinghao.common.exception.ResultBody;
import com.dinghao.common.utils.SecurityUtils;
import com.dinghao.system.domain.SysPost;
import com.dinghao.system.service.ISysPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位信息前端控制器
 */
@RestController
@RequestMapping(value = "/system/post")
@Slf4j
@CrossOrigin
public class SysPostController extends BaseController {


    @Autowired
    private ISysPostService sysPostService;


    /**
     * 获取岗位列表
     */
    @PreAuthorize("@ss.hasPermi('system:post:list')")
    @GetMapping(value = "/list")
    public ResultBody list(SysPost post){
        startPage();
        TableDataInfo dataInfo = new TableDataInfo();
        List<SysPost> list = sysPostService.selectPostList(post);
        if (CollUtil.isNotEmpty(list)) {
             dataInfo = getDataTable(list);
            return ResultBody.success(dataInfo);
        }else {
            return ResultBody.success(dataInfo);
        }
    }



    /**
     * 根据岗位编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:post:query')")
    @GetMapping(value = "/{postId}")
    public ResultBody getInfo(@PathVariable Long postId)
    {
        return ResultBody.success(sysPostService.selectPostById(postId));
    }

    /**
     * 新增岗位
     */
    @PreAuthorize("@ss.hasPermi('system:post:add')")
    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultBody add(@Validated @RequestBody SysPost post)
    {
        if (UserConstants.NOT_UNIQUE.equals(sysPostService.checkPostNameUnique(post)))
        {
            return ResultBody.error(CommonEnum.UPDATE_USER.getResultCode(),"新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(sysPostService.checkPostCodeUnique(post)))
        {
            return ResultBody.error(CommonEnum.UPDATE_USER.getResultCode(),"新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setCreateBy(SecurityUtils.getUsername());
        return toAjax(sysPostService.insertPost(post));
    }

    /**
     * 修改岗位
     */
    @PreAuthorize("@ss.hasPermi('system:post:edit')")
    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultBody edit(@Validated @RequestBody SysPost post)
    {
        if (UserConstants.NOT_UNIQUE.equals(sysPostService.checkPostNameUnique(post)))
        {
            return ResultBody.error(CommonEnum.UPDATE_USER.getResultCode(),"修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(sysPostService.checkPostCodeUnique(post)))
        {
            return ResultBody.error(CommonEnum.ADD_ROLE_ERROR.getResultCode(),"修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(sysPostService.updatePost(post));
    }

    /**
     * 删除岗位
     */
    @PreAuthorize("@ss.hasPermi('system:post:remove')")
    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{postIds}")
    public ResultBody remove(@PathVariable Long[] postIds)
    {
        return toAjax(sysPostService.deletePostByIds(postIds));
    }

    /**
     * 获取岗位选择框列表
     */
    @GetMapping("/optionselect")
    public ResultBody optionselect()
    {
        List<SysPost> posts = sysPostService.selectPostAll();
        return ResultBody.success(posts);
    }

}
