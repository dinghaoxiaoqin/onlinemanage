package com.dinghao.pms.service.impl;


import com.dinghao.pms.domain.PmsPatient;
import com.dinghao.pms.mapper.PmsPatientMapper;
import com.dinghao.pms.service.IPmsPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author dinghao
 * @date 2021-04-17
 */
@Service
public class PmsPatientServiceImpl implements IPmsPatientService
{
    @Autowired
    private PmsPatientMapper pmsPatientMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public PmsPatient selectPmsPatientById(Long id)
    {
        return pmsPatientMapper.selectPmsPatientById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param pmsPatient 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<PmsPatient> selectPmsPatientList(PmsPatient pmsPatient)
    {
        return pmsPatientMapper.selectPmsPatientList(pmsPatient);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param pmsPatient 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertPmsPatient(PmsPatient pmsPatient)
    {
        return pmsPatientMapper.insertPmsPatient(pmsPatient);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param pmsPatient 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updatePmsPatient(PmsPatient pmsPatient)
    {
        return pmsPatientMapper.updatePmsPatient(pmsPatient);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deletePmsPatientByIds(Long[] ids)
    {
        return pmsPatientMapper.deletePmsPatientByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deletePmsPatientById(Long id)
    {
        return pmsPatientMapper.deletePmsPatientById(id);
    }
}
