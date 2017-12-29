package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProjectSendTeamBDto {

    /**提交项目id*/
    private Integer projectSendBId;

    /**提交项目成员id*/
    private Integer projectSendMemberId;

    /**成员姓名*/
    private String memberName;

    /**职务*/
    private String companyDuties;

    /**股份占比*/
    private BigDecimal stockRatio;

    /**简介*/
    private String memberDesc;

    /**工作经历*/
    private List<String> workExperience;

    /**教育经历*/
    private List<String> educationExperience;

    public Integer getProjectSendBId() {
        return projectSendBId;
    }

    public void setProjectSendBId(Integer projectSendBId) {
        this.projectSendBId = projectSendBId;
    }

    public Integer getProjectSendMemberId() {
        return projectSendMemberId;
    }

    public void setProjectSendMemberId(Integer projectSendMemberId) {
        this.projectSendMemberId = projectSendMemberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCompanyDuties() {
        return companyDuties;
    }

    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    public BigDecimal getStockRatio() {
        return stockRatio;
    }

    public void setStockRatio(BigDecimal stockRatio) {
        this.stockRatio = stockRatio;
    }

    public String getMemberDesc() {
        return memberDesc;
    }

    public void setMemberDesc(String memberDesc) {
        this.memberDesc = memberDesc;
    }

    public List<String> getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(List<String> workExperience) {
        this.workExperience = workExperience;
    }

    public List<String> getEducationExperience() {
        return educationExperience;
    }

    public void setEducationExperience(List<String> educationExperience) {
        this.educationExperience = educationExperience;
    }
}
