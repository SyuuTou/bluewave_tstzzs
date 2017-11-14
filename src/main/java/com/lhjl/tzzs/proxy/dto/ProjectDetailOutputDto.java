package com.lhjl.tzzs.proxy.dto;

/**
 * 项目详情输出范式
 */
public class ProjectDetailOutputDto {
    /**
     * 项目简称
     */
    private String shortName;
    /**
     * 项目全称
     */
    private String fullName;
    /**
     * 一句话介绍
     */
    private String kernelDesc;
    /**
     * 项目简介
     */
    private String commet;
    /**
     * 项目官网
     */
    private String url;
    /**
     * 项目地域
     */
    private String territory;
    /**
     * 项目logo
     */
    private String projectLogo;
    /**
     * 项目亮点
     */
    private String projectInvestmentHighlights;
    /**
     * 项目融资阶段
     */
    private String financingStage;

    /**
     * 项目领域
     */
    private String field;

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

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getProjectLogo() {
        return projectLogo;
    }

    public void setProjectLogo(String projectLogo) {
        this.projectLogo = projectLogo;
    }

    public String getProjectInvestmentHighlights() {
        return projectInvestmentHighlights;
    }

    public void setProjectInvestmentHighlights(String projectInvestmentHighlights) {
        this.projectInvestmentHighlights = projectInvestmentHighlights;
    }

    public String getFinancingStage() {
        return financingStage;
    }

    public void setFinancingStage(String financingStage) {
        this.financingStage = financingStage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
