package com.dinghao.system.mapper;



import com.dinghao.common.core.domain.entity.SysDictType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 字典表 数据层
 * 
 * @author dinghao
 */
@Mapper
public interface SysDictTypeMapper
{

    List<SysDictType> selectDictTypeList(SysDictType dictType);

    SysDictType selectDictTypeById(@Param("dictId") Long dictId);

    SysDictType checkDictTypeUnique(@Param("dictType") String dictType);

    Integer insertDictType(SysDictType dict);

    Integer updateDictType(SysDictType dictType);

    Integer deleteDictTypeByIds(@Param("dictIds") Long[] dictIds);

    List<SysDictType> selectDictTypeAll();
}
