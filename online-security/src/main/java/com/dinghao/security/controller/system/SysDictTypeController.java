package com.dinghao.security.controller.system;

import com.dinghao.common.annotation.Log;
import com.dinghao.common.constant.UserConstants;
import com.dinghao.common.core.domain.controller.BaseController;
import com.dinghao.common.core.domain.entity.SysDictType;
import com.dinghao.common.core.page.TableDataInfo;
import com.dinghao.common.enums.BusinessType;
import com.dinghao.common.exception.CommonEnum;
import com.dinghao.common.exception.ResultBody;
import com.dinghao.common.utils.SecurityUtils;
import com.dinghao.system.service.ISysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典类型信息
 */
@RestController
@RequestMapping(value = "/system/dict/type")
@Slf4j
@CrossOrigin
public class SysDictTypeController extends BaseController {

    @Autowired
    private ISysDictTypeService dictTypeService;

    /**
     * 获取字典列表数据
     * @param dictType
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping("/list")
    public ResultBody list(SysDictType dictType)
    {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        TableDataInfo dataInfo = getDataTable(list);
        return ResultBody.success(dataInfo);
    }



    /**
     * 查询字典类型详细
     */
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{dictId}")
    public ResultBody getInfo(@PathVariable Long dictId)
    {
        return ResultBody.success(dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping
    public ResultBody add(@Validated @RequestBody SysDictType dict)
    {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return ResultBody.error(CommonEnum.ADD_DICT_ERROR.getResultCode(),"新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultBody edit(@Validated @RequestBody SysDictType dict)
    {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict)))
        {
            return ResultBody.error(CommonEnum.ADD_DICT_ERROR.getResultCode(),"修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictTypeService.updateDictType(dict));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictIds}")
    public ResultBody remove(@PathVariable Long[] dictIds)
    {
        return toAjax(dictTypeService.deleteDictTypeByIds(dictIds));
    }

    /**
     * 清空缓存
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clearCache")
    public ResultBody clearCache()
    {
        dictTypeService.clearCache();
        return ResultBody.success();
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public ResultBody optionselect()
    {
        List<SysDictType> dictTypes = dictTypeService.selectDictTypeAll();
        return ResultBody.success(dictTypes);
    }
}
