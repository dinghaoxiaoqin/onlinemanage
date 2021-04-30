package com.dinghao.system.service;


import com.dinghao.common.core.domain.entity.SysDictData;

import java.util.List;

/**
 * 字典 业务层
 * 
 * @author dinghao
 */
public interface ISysDictDataService
{

    Integer addDict(SysDictData dict);

    List<SysDictData> selectDictDataList(SysDictData dictData);


    SysDictData selectDictDataById(Long dictCode);

    Integer updateDictData(SysDictData dict);

    Integer deleteDictDataByIds(Long[] dictCodes);
}
