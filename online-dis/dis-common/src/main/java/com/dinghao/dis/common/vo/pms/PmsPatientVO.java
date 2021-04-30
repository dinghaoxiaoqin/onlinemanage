package com.dinghao.dis.common.vo.pms;


import com.dinghao.common.annotation.Excel;
import com.dinghao.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【请填写功能名称】对象 pms_patient
 *
 * @author dinghao
 * @date 2021-04-17
 */
@ApiModel(description = "【请填写功能名称】", value = "【请填写功能名称】对象VO")
public class PmsPatientVO extends BaseEntity {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", example = "")
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", example = "")
    @Excel(name = "姓名")
    private String name;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateOfBirth;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", example = "")
    @Excel(name = "身份证号")
    private String identificationNo;

    /**
     * 家庭住址
     */
    @ApiModelProperty(value = "家庭住址", example = "")
    @Excel(name = "家庭住址")
    private String homeAddress;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", example = "")
    @Excel(name = "手机号")
    private String phoneNo;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", example = "")
    @Excel(name = "性别")
    private Integer gender;

    /**
     * 病历号
     */
    @ApiModelProperty(value = "病历号", example = "")
    @Excel(name = "病历号")
    private String medicalRecordNo;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄", example = "")
    @Excel(name = "年龄")
    private String patientAgeStr;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setIdentificationNo(String identificationNo) {
        this.identificationNo = identificationNo;
    }

    public String getIdentificationNo() {
        return identificationNo;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getGender() {
        return gender;
    }

    public void setMedicalRecordNo(String medicalRecordNo) {
        this.medicalRecordNo = medicalRecordNo;
    }

    public String getMedicalRecordNo() {
        return medicalRecordNo;
    }

    public void setPatientAgeStr(String patientAgeStr) {
        this.patientAgeStr = patientAgeStr;
    }

    public String getPatientAgeStr() {
        return patientAgeStr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("dateOfBirth", getDateOfBirth())
                .append("identificationNo", getIdentificationNo())
                .append("homeAddress", getHomeAddress())
                .append("phoneNo", getPhoneNo())
                .append("gender", getGender())
                .append("medicalRecordNo", getMedicalRecordNo())
                .append("patientAgeStr", getPatientAgeStr())
                .toString();
    }
}
