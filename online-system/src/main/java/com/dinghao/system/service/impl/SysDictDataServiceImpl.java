package com.dinghao.system.service.impl;



import com.dinghao.common.core.domain.entity.SysDictData;
import com.dinghao.common.utils.DictUtils;
import com.dinghao.system.mapper.SysDictDataMapper;
import com.dinghao.system.service.ISysDictDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 字典 业务层处理
 * 
 * @author dinghao
 */
@Service
@Slf4j
public class SysDictDataServiceImpl implements ISysDictDataService
{


    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     *新增字典类型
     * @param dict
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer addDict(SysDictData dict) {
        int row = dictDataMapper.insertDictData(dict);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 获取字典类型列表
     * @param dictData
     * @return
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData) {
        List<SysDictData> list =   dictDataMapper.selectDictDataList(dictData);
        return list;
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode) {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    /**
     * 修改字典
     * @param dict
     * @return
     */
    @Override
    public Integer updateDictData(SysDictData dict) {
        Integer row = dictDataMapper.updateDictData(dict);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 删除字典
     * @param dictCodes
     * @return
     */
    @Override
    public Integer deleteDictDataByIds(Long[] dictCodes) {
        Integer row = dictDataMapper.deleteDictDataByIds(dictCodes);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }
}
