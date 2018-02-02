package com.lhjl.tzzs.proxy.dto;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
public class CollectProjectAuditBasicInfoDto {

    private String projectId;

    private String companyFullName;

    private String kernelDesc;

    private String url;

    private String[] companyTag;

    private String[] competitiveProduct;

    private String[] segmentation;

    private String establishedTime;

    private String companyEmail;

    private String hREmail;

    private String companyCity;

    private String companyAddress;

    private Integer isInvestforward;

    public String gethREmail() {
        return hREmail;
    }

    public void sethREmail(String hREmail) {
        this.hREmail = hREmail;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
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

    public String[] getCompanyTag() {
        return companyTag;
    }

    public void setCompanyTag(String[] companyTag) {
        this.companyTag = companyTag;
    }

    public String[] getCompetitiveProduct() {
        return competitiveProduct;
    }

    public void setCompetitiveProduct(String[] competitiveProduct) {
        this.competitiveProduct = competitiveProduct;
    }

    public String[] getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(String[] segmentation) {
        this.segmentation = segmentation;
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

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Integer getIsInvestforward() {
        return isInvestforward;
    }

    public void setIsInvestforward(Integer isInvestforward) {
        this.isInvestforward = isInvestforward;
    }
}
