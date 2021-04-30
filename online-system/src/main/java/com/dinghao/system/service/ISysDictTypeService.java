package com.dinghao.system.service;


import com.dinghao.common.core.domain.entity.SysDictData;
import com.dinghao.common.core.domain.entity.SysDictType;

import java.util.List;

/**
 * 字典 业务层
 * 
 * @author dinghao
 */
public interface ISysDictTypeService
{

    List<SysDictData> selectDictDataByType(String dictType);

    List<SysDictType> selectDictTypeList(SysDictType dictType);

    SysDictType selectDictTypeById(Long dictId);

    /**
     * 校验字典类型称是否唯一
     * @param dict
     * @return
     */
    String checkDictTypeUnique(SysDictType dict);

    Integer insertDictType(SysDictType dict);

    Integer updateDictType(SysDictType dict);
    /**
     * 删除字典类型
     */
    Integer deleteDictTypeByIds(Long[] dictIds);

    /**
     * 清除缓存
     */
    void clearCache();

    /**
     * 根据所有字典类型
     * @return
     */
    List<SysDictType> selectDictTypeAll();
}
