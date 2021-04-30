package com.dinghao.security.controller.system;

import com.dinghao.common.annotation.Log;
import com.dinghao.common.core.domain.controller.BaseController;
import com.dinghao.common.core.domain.entity.SysDictData;
import com.dinghao.common.enums.BusinessType;
import com.dinghao.common.exception.ResultBody;
import com.dinghao.common.utils.SecurityUtils;
import com.dinghao.system.service.ISysDictDataService;
import com.dinghao.system.service.ISysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典信息
 */
@RestController
@RequestMapping(value = "/system/dict/data")
@Slf4j
@CrossOrigin
public class SysDictDataController extends BaseController {


    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;


    /**
     * 新增字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping(value = "/add")
    public ResultBody add(@Validated @RequestBody SysDictData dict) {
        dict.setCreateBy(SecurityUtils.getUsername());
        Integer result = dictDataService.addDict(dict);
        return result > 0 ? ResultBody.success("新增字典类型成功") : ResultBody.error("901", "新增字典类型失败");
    }

    /**
     * 字典类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping(value = "/list")
    public ResultBody list(SysDictData dictData){
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return ResultBody.success(getDataTable(list));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public ResultBody dictType(@PathVariable(value = "dictType") String dictType){
        return ResultBody.success(dictTypeService.selectDictDataByType(dictType));
    }


    /**
     * 查询字典数据详细
     */
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{dictCode}")
    public ResultBody getInfo(@PathVariable Long dictCode)
    {
        return ResultBody.success(dictDataService.selectDictDataById(dictCode));
    }


    /**
     * 修改保存字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultBody edit(@Validated @RequestBody SysDictData dict)
    {
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictCodes}")
    public ResultBody remove(@PathVariable Long[] dictCodes)
    {
        return toAjax(dictDataService.deleteDictDataByIds(dictCodes));
    }
}
