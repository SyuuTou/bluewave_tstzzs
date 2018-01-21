package com.lhjl.tzzs.proxy.dto;

import java.util.List;

public class ProjectAdminBaseInfoDto {
    /**项目全称*/
    private String fullName;

    /**一句话介绍*/
    private String kernelDesc;

    /**官网*/
    private String url;

    /**项目标签*/
    private List<String> itemLabel;

    /**项目竞品*/
    private List<String> projectCompetitiveProducts;

    /**项目领域*/
    private List<String> projectSegmentation;

    /**创建时间*/
    private String establishedTime;

    /**公司邮箱*/
    private String companyEmail;

    /**城市*/
    private String city;

    /**公司hr邮箱*/
    private String companyHrEmail;

    /**公司详细地址*/
    private String address;

    /**公司总部*/
    private String territory;

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

    public List<String> getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(List<String> itemLabel) {
        this.itemLabel = itemLabel;
    }

    public List<String> getProjectCompetitiveProducts() {
        return projectCompetitiveProducts;
    }

    public void setProjectCompetitiveProducts(List<String> projectCompetitiveProducts) {
        this.projectCompetitiveProducts = projectCompetitiveProducts;
    }

    public List<String> getProjectSegmentation() {
        return projectSegmentation;
    }

    public void setProjectSegmentation(List<String> projectSegmentation) {
        this.projectSegmentation = projectSegmentation;
    }

    public String getEstablishedTime() {
        return establishedTime;
    }

    public void setEstablishedTime(String establishedTime) {
        this.establishedTime = establishedTime;
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

    public String getCompanyHrEmail() {
        return companyHrEmail;
    }

    public void setCompanyHrEmail(String companyHrEmail) {
        this.companyHrEmail = companyHrEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getForeignInvestmentYn() {
        return foreignInvestmentYn;
    }

    public void setForeignInvestmentYn(String foreignInvestmentYn) {
        this.foreignInvestmentYn = foreignInvestmentYn;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }
}
