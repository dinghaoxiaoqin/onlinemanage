package com.dinghao.pms.controller;


import com.dinghao.common.annotation.Log;
import com.dinghao.common.core.domain.controller.BaseController;
import com.dinghao.common.core.page.TableDataInfo;
import com.dinghao.common.enums.BusinessType;
import com.dinghao.common.exception.ResultBody;
import com.dinghao.common.utils.poi.ExcelUtil;
import com.dinghao.dis.common.utils.BeanCopierUtil;

import com.dinghao.dis.common.vo.pms.PmsPatientVO;
import com.dinghao.pms.domain.PmsPatient;
import com.dinghao.pms.service.IPmsPatientService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author dinghao
 * @date 2021-04-17
 */
@Api(tags = "【请填写功能名称】")
@RestController
@RequestMapping("/pms/patient")
public class PmsPatientController extends BaseController {
    @Autowired
    private IPmsPatientService pmsPatientService;

/**
 * 查询【请填写功能名称】列表
 *
 */
@PreAuthorize("@ss.hasPermi('pms:patient:list')")
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
@ApiParam(name = "PmsPatientVO对象" , type = "PmsPatientVO")
        public TableDataInfo list(PmsPatientVO pmsPatientVO) {
        //将Vo转化为实体
        PmsPatient pmsPatient=BeanCopierUtil.copy(pmsPatientVO,PmsPatient. class);
        startPage();
        List<PmsPatient> list = pmsPatientService.selectPmsPatientList(pmsPatient);
        TableDataInfo tableDataInfo = getDataTable(list);
        //替换集合
        tableDataInfo.setRows(BeanCopierUtil.copy(tableDataInfo.getRows(), PmsPatientVO.class));
        return tableDataInfo;
    }
    
    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('pms:patient:export')")
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
    @ApiParam(name = "PmsPatientVO对象" , type = "PmsPatientVO")
    public ResultBody export(PmsPatientVO pmsPatientVO) {
        //将VO转化为实体
        PmsPatient pmsPatient=BeanCopierUtil.copy(pmsPatientVO,PmsPatient. class);
        List<PmsPatientVO> list = BeanCopierUtil.copy(pmsPatientService.selectPmsPatientList(pmsPatient),PmsPatientVO.class);
        ExcelUtil<PmsPatientVO> util = new ExcelUtil<PmsPatientVO>(PmsPatientVO.class);
        return util.exportExcel(list, "patient");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('pms:patient:query')")
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
    @ApiParam(name = "pmsPatient.id" , type = "Long")
    public ResultBody getInfo(@PathVariable("id") Long id) {
        return ResultBody.success(BeanCopierUtil.copy(pmsPatientService.selectPmsPatientById(id),PmsPatientVO.class));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('pms:patient:add')")
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
    @ApiParam(name = "PmsPatientVO对象" , type = "PmsPatientVO")
    public ResultBody add(@RequestBody PmsPatientVO pmsPatientVO) {
        return toAjax(pmsPatientService.insertPmsPatient(BeanCopierUtil.copy(pmsPatientVO,PmsPatient. class)));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('pms:patient:edit')")
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
    @ApiParam(name = "PmsPatientVO对象" , type = "PmsPatientVO")
    public ResultBody edit(@RequestBody PmsPatientVO pmsPatientVO) {
        return toAjax(pmsPatientService.updatePmsPatient(BeanCopierUtil.copy(pmsPatientVO,PmsPatient. class)));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('pms:patient:remove')")
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
    @ApiParam(name = "pmsPatient.id" , type = "Long[]")
    public ResultBody remove(@PathVariable Long[] ids) {
        return toAjax(pmsPatientService.deletePmsPatientByIds(ids));
    }
}
