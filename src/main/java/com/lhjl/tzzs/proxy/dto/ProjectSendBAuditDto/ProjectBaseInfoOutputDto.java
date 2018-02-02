package com.lhjl.tzzs.proxy.dto.ProjectSendBAuditDto;

import java.util.List;

public class ProjectBaseInfoOutputDto {
    /**公司全称*/
    private String fullName;

    /**公司一句话介绍*/
    private String kernelDesc;

    /**公司官网*/
    private String url;

    /**项目标签*/
    private List<String> projectTags;

    /**项目竞品*/
    private List<String> projectCompeting;

    /**项目领域*/
    private List<String> projectSegmentation;

    /**创建时间*/
    private String fundTime;

    /**公司邮件*/
    private String companyEmail;

    /**所在城市*/
    private String city;

    /**公司地址*/
    private String companyAddress;

    /**是否对外投资*/
    private String foreignInvestmentYn;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getProjectTags() {
        return projectTags;
    }

    public void setProjectTags(List<String> projectTags) {
        this.projectTags = projectTags;
    }

    public List<String> getProjectCompeting() {
        return projectCompeting;
    }

    public void setProjectCompeting(List<String> projectCompeting) {
        this.projectCompeting = projectCompeting;
    }

    public List<String> getProjectSegmentation() {
        return projectSegmentation;
    }

    public void setProjectSegmentation(List<String> projectSegmentation) {
        this.projectSegmentation = projectSegmentation;
    }

    public String getFundTime() {
        return fundTime;
    }

    public void setFundTime(String fundTime) {
        this.fundTime = fundTime;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getForeignInvestmentYn() {
        return foreignInvestmentYn;
    }

    public void setForeignInvestmentYn(String foreignInvestmentYn) {
        this.foreignInvestmentYn = foreignInvestmentYn;
    }
}
