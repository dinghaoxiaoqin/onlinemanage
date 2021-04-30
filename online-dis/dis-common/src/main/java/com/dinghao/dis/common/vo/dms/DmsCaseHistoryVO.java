package com.dinghao.dis.common.vo.dms;


import com.dinghao.common.annotation.Excel;
import com.dinghao.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【请填写功能名称】对象 dms_case_history
 *
 * @author dinghao
 * @date 2021-04-29
 */
@ApiModel(description = "【请填写功能名称】" , value = "【请填写功能名称】对象VO")
public class DmsCaseHistoryVO extends BaseEntity {

                        /** 主键id */
            @ApiModelProperty(value = "主键id" , example = "")
                            private Long id;

                                /** 主诉 */
            @ApiModelProperty(value = "主诉" , example = "")
                                                                                                @Excel(name = "主诉")
                                                private String chiefComplaint;

                                /** 现病史 */
            @ApiModelProperty(value = "现病史" , example = "")
                                                                                                @Excel(name = "现病史")
                                                private String historyOfPresentIllness;

                                /** 现治疗情况 */
            @ApiModelProperty(value = "现治疗情况" , example = "")
                                                                                                @Excel(name = "现治疗情况")
                                                private String historyOfTreatment;

                                /** 既往史 */
            @ApiModelProperty(value = "既往史" , example = "")
                                                                                                @Excel(name = "既往史")
                                                private String pastHistory;

                                /** 过敏史 */
            @ApiModelProperty(value = "过敏史" , example = "")
                                                                                                @Excel(name = "过敏史")
                                                private String allergies;

                                /** 体格检查 */
            @ApiModelProperty(value = "体格检查" , example = "")
                                                                                                @Excel(name = "体格检查")
                                                private String healthCheckup;

                                /** 门诊id */
            @ApiModelProperty(value = "门诊id" , example = "")
                                                                                                @Excel(name = "门诊id")
                                                private Long registrationId;

                                /** 初诊结果str串 */
            @ApiModelProperty(value = "初诊结果str串" , example = "")
                                                                                                @Excel(name = "初诊结果str串")
                                                private String priliminaryDiseStrList;

                                /** 发病时间 */
            @ApiModelProperty(value = "发病时间" , example = "")
                                                                                                @JsonFormat(pattern = "yyyy-MM-dd")
                    @Excel(name = "发病时间" , width = 30, dateFormat = "yyyy-MM-dd")
                                                private Date startDate;

                                /** 病人姓名 */
            @ApiModelProperty(value = "病人姓名" , example = "")
                                                                                                @Excel(name = "病人姓名")
                                                private String name;

                                /** 0男，1女 */
            @ApiModelProperty(value = "0男，1女" , example = "")
                                                                                                @Excel(name = "0男，1女")
                                                private Integer gender;

                                /** 年龄 */
            @ApiModelProperty(value = "年龄" , example = "")
                                                                                                @Excel(name = "年龄")
                                                private String ageStr;

                                /** 检查部位字符串列表 */
            @ApiModelProperty(value = "检查部位字符串列表" , example = "")
                                                                                                @Excel(name = "检查部位字符串列表")
                                                private String checkStrList;

                                /** 处置字符串列表 */
            @ApiModelProperty(value = "处置字符串列表" , example = "")
                                                                                                @Excel(name = "处置字符串列表")
                                                private String dispositionStrList;

                                /** 草药处方列表 */
            @ApiModelProperty(value = "草药处方列表" , example = "")
                                                                                                @Excel(name = "草药处方列表")
                                                private String herbalPrescriptionStrList;

                                /** 确诊诊断串 */
            @ApiModelProperty(value = "确诊诊断串" , example = "")
                                                                                                @Excel(name = "确诊诊断串")
                                                private String definiteDiseStrList;

                                /** 病人id */
            @ApiModelProperty(value = "病人id" , example = "")
                                                                                                @Excel(name = "病人id")
                                                private Long patientId;

                                /** 检验结果字符串 */
            @ApiModelProperty(value = "检验结果字符串" , example = "")
                                                                                                @Excel(name = "检验结果字符串")
                                                private String testStrList;

                                /** 西药处方 */
            @ApiModelProperty(value = "西药处方" , example = "")
                                                                                                @Excel(name = "西药处方")
                                                private String medicinePrescriptionStrList;

                                /** 状态 */
            @ApiModelProperty(value = "状态" , example = "")
                                                                                                @Excel(name = "状态")
                                                private Long status;

                                /** 初诊疾病id列表 */
            @ApiModelProperty(value = "初诊疾病id列表" , example = "")
                                                                                                @Excel(name = "初诊疾病id列表")
                                                private String priliminaryDiseIdList;

                                /** 检查结果 */
            @ApiModelProperty(value = "检查结果" , example = "")
                                                                                                @Excel(name = "检查结果")
                                                private String checkResult;

                                /** 检验结果 */
            @ApiModelProperty(value = "检验结果" , example = "")
                                                                                                @Excel(name = "检验结果")
                                                private String testResult;

                                                                        public void setId(Long id) {
                this.id = id;
            }

            public Long getId() {
                return id;
            }
                                                        public void setChiefComplaint(String chiefComplaint) {
                this.chiefComplaint = chiefComplaint;
            }

            public String getChiefComplaint() {
                return chiefComplaint;
            }
                                                        public void setHistoryOfPresentIllness(String historyOfPresentIllness) {
                this.historyOfPresentIllness = historyOfPresentIllness;
            }

            public String getHistoryOfPresentIllness() {
                return historyOfPresentIllness;
            }
                                                        public void setHistoryOfTreatment(String historyOfTreatment) {
                this.historyOfTreatment = historyOfTreatment;
            }

            public String getHistoryOfTreatment() {
                return historyOfTreatment;
            }
                                                        public void setPastHistory(String pastHistory) {
                this.pastHistory = pastHistory;
            }

            public String getPastHistory() {
                return pastHistory;
            }
                                                        public void setAllergies(String allergies) {
                this.allergies = allergies;
            }

            public String getAllergies() {
                return allergies;
            }
                                                        public void setHealthCheckup(String healthCheckup) {
                this.healthCheckup = healthCheckup;
            }

            public String getHealthCheckup() {
                return healthCheckup;
            }
                                                        public void setRegistrationId(Long registrationId) {
                this.registrationId = registrationId;
            }

            public Long getRegistrationId() {
                return registrationId;
            }
                                                        public void setPriliminaryDiseStrList(String priliminaryDiseStrList) {
                this.priliminaryDiseStrList = priliminaryDiseStrList;
            }

            public String getPriliminaryDiseStrList() {
                return priliminaryDiseStrList;
            }
                                                        public void setStartDate(Date startDate) {
                this.startDate = startDate;
            }

            public Date getStartDate() {
                return startDate;
            }
                                                        public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }
                                                        public void setGender(Integer gender) {
                this.gender = gender;
            }

            public Integer getGender() {
                return gender;
            }
                                                        public void setAgeStr(String ageStr) {
                this.ageStr = ageStr;
            }

            public String getAgeStr() {
                return ageStr;
            }
                                                        public void setCheckStrList(String checkStrList) {
                this.checkStrList = checkStrList;
            }

            public String getCheckStrList() {
                return checkStrList;
            }
                                                        public void setDispositionStrList(String dispositionStrList) {
                this.dispositionStrList = dispositionStrList;
            }

            public String getDispositionStrList() {
                return dispositionStrList;
            }
                                                        public void setHerbalPrescriptionStrList(String herbalPrescriptionStrList) {
                this.herbalPrescriptionStrList = herbalPrescriptionStrList;
            }

            public String getHerbalPrescriptionStrList() {
                return herbalPrescriptionStrList;
            }
                                                        public void setDefiniteDiseStrList(String definiteDiseStrList) {
                this.definiteDiseStrList = definiteDiseStrList;
            }

            public String getDefiniteDiseStrList() {
                return definiteDiseStrList;
            }
                                                        public void setPatientId(Long patientId) {
                this.patientId = patientId;
            }

            public Long getPatientId() {
                return patientId;
            }
                                                        public void setTestStrList(String testStrList) {
                this.testStrList = testStrList;
            }

            public String getTestStrList() {
                return testStrList;
            }
                                                        public void setMedicinePrescriptionStrList(String medicinePrescriptionStrList) {
                this.medicinePrescriptionStrList = medicinePrescriptionStrList;
            }

            public String getMedicinePrescriptionStrList() {
                return medicinePrescriptionStrList;
            }
                                                        public void setStatus(Long status) {
                this.status = status;
            }

            public Long getStatus() {
                return status;
            }
                                                        public void setPriliminaryDiseIdList(String priliminaryDiseIdList) {
                this.priliminaryDiseIdList = priliminaryDiseIdList;
            }

            public String getPriliminaryDiseIdList() {
                return priliminaryDiseIdList;
            }
                                                        public void setCheckResult(String checkResult) {
                this.checkResult = checkResult;
            }

            public String getCheckResult() {
                return checkResult;
            }
                                                        public void setTestResult(String testResult) {
                this.testResult = testResult;
            }

            public String getTestResult() {
                return testResult;
            }
                        
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                                                                .append("id" , getId())
                                                                        .append("chiefComplaint" , getChiefComplaint())
                                                                        .append("historyOfPresentIllness" , getHistoryOfPresentIllness())
                                                                        .append("historyOfTreatment" , getHistoryOfTreatment())
                                                                        .append("pastHistory" , getPastHistory())
                                                                        .append("allergies" , getAllergies())
                                                                        .append("healthCheckup" , getHealthCheckup())
                                                                        .append("registrationId" , getRegistrationId())
                                                                        .append("priliminaryDiseStrList" , getPriliminaryDiseStrList())
                                                                        .append("startDate" , getStartDate())
                                                                        .append("name" , getName())
                                                                        .append("gender" , getGender())
                                                                        .append("ageStr" , getAgeStr())
                                                                        .append("checkStrList" , getCheckStrList())
                                                                        .append("dispositionStrList" , getDispositionStrList())
                                                                        .append("herbalPrescriptionStrList" , getHerbalPrescriptionStrList())
                                                                        .append("definiteDiseStrList" , getDefiniteDiseStrList())
                                                                        .append("patientId" , getPatientId())
                                                                        .append("testStrList" , getTestStrList())
                                                                        .append("medicinePrescriptionStrList" , getMedicinePrescriptionStrList())
                                                                        .append("status" , getStatus())
                                                                        .append("priliminaryDiseIdList" , getPriliminaryDiseIdList())
                                                                        .append("checkResult" , getCheckResult())
                                                                        .append("testResult" , getTestResult())
                                                                        .append("createTime" , getCreateTime())
                                    .toString();
    }
}
