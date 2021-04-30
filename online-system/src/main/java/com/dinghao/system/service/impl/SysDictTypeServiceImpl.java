package com.dinghao.system.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.dinghao.common.constant.UserConstants;
import com.dinghao.common.core.domain.entity.SysDictData;
import com.dinghao.common.core.domain.entity.SysDictType;
import com.dinghao.common.exception.CustomException;
import com.dinghao.common.utils.DictUtils;
import com.dinghao.common.utils.StringUtils;
import com.dinghao.system.mapper.SysDictDataMapper;
import com.dinghao.system.mapper.SysDictTypeMapper;
import com.dinghao.system.service.ISysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典 业务层处理
 * 
 * @author dinghao
 */
@Service
@Slf4j
public class SysDictTypeServiceImpl implements ISysDictTypeService
{

    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 根据字典类型获取字典数据
     * @param dictType
     * @return
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        List<SysDictData> dictDatas = DictUtils.getDictCache(dictType);
        if (CollUtil.isNotEmpty(dictDatas))
        {
            return dictDatas;
        }
        dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (CollUtil.isNotEmpty(dictDatas))
        {
            DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return new ArrayList<SysDictData>();
    }

    /**
     * 获取字典列表
     * @param dictType
     * @return
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    /**
     * 根据id来查询字典详情
     * @param dictId
     * @return
     */
    @Override
    public SysDictType selectDictTypeById(Long dictId) {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    /**
     * 校验字典类型称是否唯一
     * @param dict
     * @return
     */
    @Override
    public String checkDictTypeUnique(SysDictType dict) {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增字典类型
     * @param dict
     * @return
     */
    @Override
    public Integer insertDictType(SysDictType dict) {
        Integer row = dictTypeMapper.insertDictType(dict);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 修改字典类型
     * @param dictType
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateDictType(SysDictType dictType) {
        SysDictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getDictId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        int row = dictTypeMapper.updateDictType(dictType);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 删除字典类型
     * @param dictIds
     * @return
     */
    @Override
    public Integer deleteDictTypeByIds(Long[] dictIds) {
        for (Long dictId : dictIds)
        {
            SysDictType dictType = selectDictTypeById(dictId);
            if (dictDataMapper.countDictDataByType(dictType.getDictType()) > 0)
            {
                throw new CustomException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
        }
        int count = dictTypeMapper.deleteDictTypeByIds(dictIds);
        if (count > 0)
        {
            DictUtils.clearDictCache();
        }
        return count;
    }

    /**
     * 清空缓存
     */
    @Override
    public void clearCache() {
        DictUtils.clearDictCache();
    }
    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }
}
