package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProjectSendBOutDto {
    /**项目logo*/
    private String projectLogo;

    /**项目简称*/
    private String shortName;

    /**项目全称*/
    private String fullName;

    /**一句话介绍*/
    private String kernelDesc;

    /**项目亮点*/
    private String projectInvestmentHighlights;

    /**项目简介*/
    private String commet;

    /**官网*/
    private String url;

    /**所在城市*/
    private String city;

    /**行业领域*/
    private List<String> projectSegmentation;

    /**项目标签*/
    private List<String> projectTags;

    /**轮次*/
    private String stage;

    /**融资金额*/
    private BigDecimal amount;

    /** 融资金额单位（0人民币/1美元）*/
    private Integer currency;

    /**出让股份*/
    private String shareDivest;

    /**融资用途*/
    private String projectFinancingUseful;

    /**项目竞品*/
    private List<String> projectCompeting;

    /**真实姓名*/
    private String actualName;

    /**公司职位*/
    private String companyDuties;

    /**简介*/
    private String desc;

    /**工作经历*/
    private List<String> workExperience;

    /**教育经历*/
    private List<String> educationExperience;

    /**邮箱*/
    private String email;

    /**微信号*/
    private String wechat;

    /**用户token*/
    private String token;

    /**提交项目id*/
    private Integer projectSendId;

    /**预生成id*/
    private Integer prepareId;

    public String getProjectLogo() {
        return projectLogo;
    }

    public void setProjectLogo(String projectLogo) {
        this.projectLogo = projectLogo;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getKernelDesc() {
        return kernelDesc;
    }

    public void setKernelDesc(String kernelDesc) {
        this.kernelDesc = kernelDesc;
    }

    public String getProjectInvestmentHighlights() {
        return projectInvestmentHighlights;
    }

    public void setProjectInvestmentHighlights(String projectInvestmentHighlights) {
        this.projectInvestmentHighlights = projectInvestmentHighlights;
    }

    public String getCommet() {
        return commet;
    }

    public void setCommet(String commet) {
        this.commet = commet;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getProjectSegmentation() {
        return projectSegmentation;
    }

    public void setProjectSegmentation(List<String> projectSegmentation) {
        this.projectSegmentation = projectSegmentation;
    }

    public List<String> getProjectTags() {
        return projectTags;
    }

    public void setProjectTags(List<String> projectTags) {
        this.projectTags = projectTags;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public String getShareDivest() {
        return shareDivest;
    }

    public void setShareDivest(String shareDivest) {
        this.shareDivest = shareDivest;
    }

    public String getProjectFinancingUseful() {
        return projectFinancingUseful;
    }

    public void setProjectFinancingUseful(String projectFinancingUseful) {
        this.projectFinancingUseful = projectFinancingUseful;
    }

    public List<String> getProjectCompeting() {
        return projectCompeting;
    }

    public void setProjectCompeting(List<String> projectCompeting) {
        this.projectCompeting = projectCompeting;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public String getCompanyDuties() {
        return companyDuties;
    }

    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getProjectSendId() {
        return projectSendId;
    }

    public void setProjectSendId(Integer projectSendId) {
        this.projectSendId = projectSendId;
    }

    public Integer getPrepareId() {
        return prepareId;
    }

    public void setPrepareId(Integer prepareId) {
        this.prepareId = prepareId;
    }
}
