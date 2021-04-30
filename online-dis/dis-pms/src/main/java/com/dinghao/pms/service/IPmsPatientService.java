package com.dinghao.pms.service;


import com.dinghao.pms.domain.PmsPatient;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author dinghao
 * @date 2021-04-17
 */
public interface IPmsPatientService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public PmsPatient selectPmsPatientById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param pmsPatient 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<PmsPatient> selectPmsPatientList(PmsPatient pmsPatient);

    /**
     * 新增【请填写功能名称】
     * 
     * @param pmsPatient 【请填写功能名称】
     * @return 结果
     */
    public int insertPmsPatient(PmsPatient pmsPatient);

    /**
     * 修改【请填写功能名称】
     * 
     * @param pmsPatient 【请填写功能名称】
     * @return 结果
     */
    public int updatePmsPatient(PmsPatient pmsPatient);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deletePmsPatientByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deletePmsPatientById(Long id);
}
