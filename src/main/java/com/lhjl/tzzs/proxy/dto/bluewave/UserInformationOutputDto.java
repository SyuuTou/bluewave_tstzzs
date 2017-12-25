package com.lhjl.tzzs.proxy.dto.bluewave;

import java.util.List;

public class UserInformationOutputDto {
    /**
     * 真实姓名
     */
    private String actualName;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 职位
     */
    private String companyDuties;
    /**
     * 城市
     */
    private String city;
    /**
     * 身份类型
     */
    private Integer identityType;
    /**
     * 行业领域
     */
    private List<String> industry;
    /**
     * 个人简介
     */
    private String desc;
    /**
     * 公司简介
     */
    private String companyDesc;
    /**
     * 需求
     */
    private String demand;
    /**
     * 工作经历
     */
    private List<String> workExperience;
    /**
     * 教育经历
     */
    private List<String> educationExperience;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 微信号
     */
    private String wechat;
    /**
     * 工作名片
     */
    private String workCard;

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDuties() {
        return companyDuties;
    }

    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    public List<String> getIndustry() {
        return industry;
    }

    public void setIndustry(List<String> industry) {
        this.industry = industry;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWorkCard() {
        return workCard;
    }

    public void setWorkCard(String workCard) {
        this.workCard = workCard;
    }
}
