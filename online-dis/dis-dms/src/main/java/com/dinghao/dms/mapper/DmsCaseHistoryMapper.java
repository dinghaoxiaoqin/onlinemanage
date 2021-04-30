package com.dinghao.dms.mapper;


import com.dinghao.dms.domain.DmsCaseHistory;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author dinghao
 * @date 2021-04-29
 */
public interface DmsCaseHistoryMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public DmsCaseHistory selectDmsCaseHistoryById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param dmsCaseHistory 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<DmsCaseHistory> selectDmsCaseHistoryList(DmsCaseHistory dmsCaseHistory);

    /**
     * 新增【请填写功能名称】
     * 
     * @param dmsCaseHistory 【请填写功能名称】
     * @return 结果
     */
    public int insertDmsCaseHistory(DmsCaseHistory dmsCaseHistory);

    /**
     * 修改【请填写功能名称】
     * 
     * @param dmsCaseHistory 【请填写功能名称】
     * @return 结果
     */
    public int updateDmsCaseHistory(DmsCaseHistory dmsCaseHistory);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteDmsCaseHistoryById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDmsCaseHistoryByIds(Long[] ids);
}
