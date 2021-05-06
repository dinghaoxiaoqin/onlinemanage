package com.dinghao.dms.service.impl;


import com.dinghao.common.utils.DateUtils;
import com.dinghao.dms.domain.DmsCaseHistory;
import com.dinghao.dms.mapper.DmsCaseHistoryMapper;
import com.dinghao.dms.service.IDmsCaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author dinghao
 * @date 2021-04-29
 */
@Service
public class DmsCaseHistoryServiceImpl implements IDmsCaseHistoryService
{
    @Autowired
    private DmsCaseHistoryMapper dmsCaseHistoryMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public DmsCaseHistory selectDmsCaseHistoryById(Long id)
    {
        return dmsCaseHistoryMapper.selectDmsCaseHistoryById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param dmsCaseHistory 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<DmsCaseHistory> selectDmsCaseHistoryList(DmsCaseHistory dmsCaseHistory)
    {
        //获取数据大幅度发
        return dmsCaseHistoryMapper.selectDmsCaseHistoryList(dmsCaseHistory);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param dmsCaseHistory 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertDmsCaseHistory(DmsCaseHistory dmsCaseHistory)
    {
        dmsCaseHistory.setCreateTime(DateUtils.getNowDate());
        return dmsCaseHistoryMapper.insertDmsCaseHistory(dmsCaseHistory);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param dmsCaseHistory 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateDmsCaseHistory(DmsCaseHistory dmsCaseHistory)
    {
        return dmsCaseHistoryMapper.updateDmsCaseHistory(dmsCaseHistory);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteDmsCaseHistoryByIds(Long[] ids)
    {
        return dmsCaseHistoryMapper.deleteDmsCaseHistoryByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteDmsCaseHistoryById(Long id)
    {
        return dmsCaseHistoryMapper.deleteDmsCaseHistoryById(id);
    }
}
