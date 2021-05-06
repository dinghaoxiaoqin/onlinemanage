package com.dinghao.dms.controller;


import com.dinghao.common.annotation.Log;
import com.dinghao.common.core.domain.controller.BaseController;
import com.dinghao.common.core.page.TableDataInfo;
import com.dinghao.common.enums.BusinessType;
import com.dinghao.common.exception.ResultBody;
import com.dinghao.common.utils.poi.ExcelUtil;
import com.dinghao.dis.common.utils.BeanCopierUtil;
import com.dinghao.dis.common.vo.dms.DmsCaseHistoryVO;
import com.dinghao.dms.domain.DmsCaseHistory;
import com.dinghao.dms.service.IDmsCaseHistoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author dinghao
 * @date 2021-04-29
 */
@Api(tags = "【请填写功能名称】")
@RestController
@RequestMapping("/dms/case_history")
public class DmsCaseHistoryController extends BaseController {
    @Autowired
    private IDmsCaseHistoryService dmsCaseHistoryService;

/**
 * 查询【请填写功能名称】列表
 *
 */
@PreAuthorize("@ss.hasPermi('dms:case_history:list')")
@GetMapping("/list")
@ApiOperation(value = "查询【请填写功能名称】" , notes = "查询所有【请填写功能名称】" ,
        code = 200, produces = "application/json" , protocols = "Http" ,
        response = TableDataInfo.class, httpMethod = "GET")
@ApiResponses({
        @ApiResponse(code = 204, message = "操作已经执行成功，但是没有返回数据"),
        @ApiResponse(code = 303, message = "重定向"),
        @ApiResponse(code = 400, message = "参数列表错误（缺少，格式不匹配）"),
        @ApiResponse(code = 500, message = "系统内部错误"),
        @ApiResponse(code = 404, message = "资源，服务未找到"),
        @ApiResponse(code = 200, message = "操作成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "访问受限，授权过期")
})
@ApiParam(name = "DmsCaseHistoryVO对象" , type = "DmsCaseHistoryVO")
        public TableDataInfo list(DmsCaseHistoryVO dmsCaseHistoryVO) {
        //将Vo转化为实体
        DmsCaseHistory dmsCaseHistory= BeanCopierUtil.copy(dmsCaseHistoryVO,DmsCaseHistory. class);
        startPage();
        List<DmsCaseHistory> list = dmsCaseHistoryService.selectDmsCaseHistoryList(dmsCaseHistory);
        TableDataInfo tableDataInfo = getDataTable(list);
        //替换集合
        tableDataInfo.setRows(BeanCopierUtil.copy(tableDataInfo.getRows(), DmsCaseHistoryVO.class));
        return tableDataInfo;
    }
    
    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('dms:case_history:export')")
    @Log(title = "【请填写功能名称】" , businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ApiOperation(value = "导出【请填写功能名称】表" , notes = "导出所有【请填写功能名称】" ,
            code = 200, produces = "application/json" , protocols = "Http" ,
            response = TableDataInfo.class, httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 204, message = "操作已经执行成功，但是没有返回数据"),
            @ApiResponse(code = 303, message = "重定向"),
            @ApiResponse(code = 400, message = "参数列表错误（缺少，格式不匹配）"),
            @ApiResponse(code = 500, message = "系统内部错误"),
            @ApiResponse(code = 404, message = "资源，服务未找到"),
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "访问受限，授权过期")
    })
    @ApiParam(name = "DmsCaseHistoryVO对象" , type = "DmsCaseHistoryVO")
    public ResultBody export(DmsCaseHistoryVO dmsCaseHistoryVO) {
        //将VO转化为实体
        DmsCaseHistory dmsCaseHistory=BeanCopierUtil.copy(dmsCaseHistoryVO,DmsCaseHistory. class);
        List<DmsCaseHistoryVO> list = BeanCopierUtil.copy(dmsCaseHistoryService.selectDmsCaseHistoryList(dmsCaseHistory),DmsCaseHistoryVO.class);
        ExcelUtil<DmsCaseHistoryVO> util = new ExcelUtil<DmsCaseHistoryVO>(DmsCaseHistoryVO.class);
        return util.exportExcel(list, "case_history");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('dms:case_history:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取【请填写功能名称】详细信息" ,
            notes = "根据【请填写功能名称】id获取科室信息" , code = 200,
            produces = "application/json" , protocols = "Http" , response = ResultBody.class,
            httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 204, message = "操作已经执行成功，但是没有返回数据"),
            @ApiResponse(code = 303, message = "重定向"),
            @ApiResponse(code = 400, message = "参数列表错误（缺少，格式不匹配）"),
            @ApiResponse(code = 500, message = "系统内部错误"),
            @ApiResponse(code = 404, message = "资源，服务未找到"),
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "访问受限，授权过期")
    })


    @ApiParam(name = "dmsCaseHistory.id" , type = "Long")
    public ResultBody getInfo(@PathVariable("id") Long id) {
        return ResultBody.success(BeanCopierUtil.copy(dmsCaseHistoryService.selectDmsCaseHistoryById(id),DmsCaseHistoryVO.class));
    }




    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('dms:case_history:add')")
    @Log(title = "新增【请填写功能名称】" , businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增【请填写功能名称】信息" , notes = "新增【请填写功能名称】信息" , code = 200,
            produces = "application/json" , protocols = "Http" , response = ResultBody.class,
            httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 204, message = "操作已经执行成功，但是没有返回数据"),
            @ApiResponse(code = 303, message = "重定向"),
            @ApiResponse(code = 400, message = "参数列表错误（缺少，格式不匹配）"),
            @ApiResponse(code = 500, message = "系统内部错误"),
            @ApiResponse(code = 404, message = "资源，服务未找到"),
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "访问受限，授权过期")
    })
    @ApiParam(name = "DmsCaseHistoryVO对象" , type = "DmsCaseHistoryVO")
    public ResultBody add(@RequestBody DmsCaseHistoryVO dmsCaseHistoryVO) {
        return toAjax(dmsCaseHistoryService.insertDmsCaseHistory(BeanCopierUtil.copy(dmsCaseHistoryVO,DmsCaseHistory. class)));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('dms:case_history:edit')")
    @Log(title = "【请填写功能名称】" , businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改【请填写功能名称】信息" , notes = "修改【请填写功能名称】信息" , code = 200,
            produces = "application/json" , protocols = "Http" , response = ResultBody.class,
            httpMethod = "PUT")
    @ApiResponses({
            @ApiResponse(code = 204, message = "操作已经执行成功，但是没有返回数据"),
            @ApiResponse(code = 303, message = "重定向"),
            @ApiResponse(code = 400, message = "参数列表错误（缺少，格式不匹配）"),
            @ApiResponse(code = 500, message = "系统内部错误"),
            @ApiResponse(code = 404, message = "资源，服务未找到"),
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "访问受限，授权过期")
    })
    @ApiParam(name = "DmsCaseHistoryVO对象" , type = "DmsCaseHistoryVO")
    public ResultBody edit(@RequestBody DmsCaseHistoryVO dmsCaseHistoryVO) {
        return toAjax(dmsCaseHistoryService.updateDmsCaseHistory(BeanCopierUtil.copy(dmsCaseHistoryVO,DmsCaseHistory. class)));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('dms:case_history:remove')")
    @Log(title = "【请填写功能名称】" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除【请填写功能名称】信息" , notes = "删除【请填写功能名称】信息" , code = 200,
            produces = "application/json" , protocols = "Http" , response = ResultBody.class,
            httpMethod = "DELETE")
    @ApiResponses({
            @ApiResponse(code = 204, message = "操作已经执行成功，但是没有返回数据"),
            @ApiResponse(code = 303, message = "重定向"),
            @ApiResponse(code = 400, message = "参数列表错误（缺少，格式不匹配）"),
            @ApiResponse(code = 500, message = "系统内部错误"),
            @ApiResponse(code = 404, message = "资源，服务未找到"),
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "访问受限，授权过期")
    })
    @ApiParam(name = "dmsCaseHistory.id" , type = "Long[]")
    public ResultBody remove(@PathVariable Long[] ids) {
        return toAjax(dmsCaseHistoryService.deleteDmsCaseHistoryByIds(ids));
    }
}
