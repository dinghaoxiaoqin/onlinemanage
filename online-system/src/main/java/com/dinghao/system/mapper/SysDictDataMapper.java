package com.dinghao.system.mapper;


import com.dinghao.common.core.domain.entity.SysDictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典表 数据层
 * 
 * @author dinghao
 */
public interface SysDictDataMapper
{

    Integer insertDictData(SysDictData dict);

    List<SysDictData> selectDictDataList(SysDictData dictData);

    List<SysDictData> selectDictDataByType(@Param("dictType") String dictType);

    void updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);

    Integer countDictDataByType(@Param("dictType") String dictType);

    SysDictData selectDictDataById(@Param("dictCode") Long dictCode);

    Integer updateDictData(SysDictData dict);

    Integer deleteDictDataByIds(@Param("dictCodes") Long[] dictCodes);
}
