package com.dinghao.security.controller.system;


import com.dinghao.common.annotation.Log;
import com.dinghao.common.core.domain.controller.BaseController;
import com.dinghao.common.core.page.TableDataInfo;
import com.dinghao.common.enums.BusinessType;
import com.dinghao.common.exception.ResultBody;
import com.dinghao.common.utils.SecurityUtils;
import com.dinghao.system.domain.SysNotice;
import com.dinghao.system.service.ISysNoticeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 公告 信息操作处理
 *
 * @author dinghao
 * @date 2021-04-10
 */
@RestController
@RequestMapping(value = "/system/notice")
@Slf4j
@CrossOrigin
public class SysNoticeController extends BaseController {
    @Autowired
    private ISysNoticeService sysNoticeService;


    /**
     * 获取公告通知列表
     */
    @PreAuthorize("@ss.hasPermi('system:notice:list')")
    @GetMapping(value = "/list")
    public ResultBody list(SysNotice sysNotice){
        List<SysNotice> list =  sysNoticeService.getSysNotices(sysNotice);
        TableDataInfo dataInfo = getDataTable(list);
        return ResultBody.success(dataInfo);
    }


    /**
     * 根据通知公告编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public ResultBody getInfo(@PathVariable Long noticeId)
    {
        return ResultBody.success(sysNoticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:add')")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultBody add(@Validated @RequestBody SysNotice notice)
    {
        notice.setCreateBy(SecurityUtils.getUsername());
        return toAjax(sysNoticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:edit')")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultBody edit(@Validated @RequestBody SysNotice notice)
    {
        notice.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(sysNoticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:remove')")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public ResultBody remove(@PathVariable Long[] noticeIds)
    {
        return toAjax(sysNoticeService.deleteNoticeByIds(noticeIds));
    }


}
