package com.lhjl.tzzs.proxy.dto.InstitutionsProjectDto;

public class InstitutionsProjectOutputDto {
    /**
     * 项目logo
     */
    private String projectLogo;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目轮次
     */
    private String projectRounds;
    /**
     * 项目领域，会整合成字符串个前端
     */
    private String projectFeild;
    /**
     * 所在城市
     */
    private String projectCity;
    /**
     * 项目融资时间
     */
    private String projectFinancingTime;
    /**
     * 项目简介
     */
    private String projectDesc;
    /**
     * 项目融资状态，0表示不在融资中，1表示正在融资
     */
    private Integer projectFinancingStatus;
    /**
     * 项目关注数量
     */
    private Integer projectFollowsNumber;
    /**
     * 当前项目是否关注，0表示未关注，1表示已关注；
     */
    private Integer isFollows;


    public String getProjectLogo() {
        return projectLogo;
    }

    public void setProjectLogo(String projectLogo) {
        this.projectLogo = projectLogo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectRounds() {
        return projectRounds;
    }

    public void setProjectRounds(String projectRounds) {
        this.projectRounds = projectRounds;
    }

    public String getProjectFeild() {
        return projectFeild;
    }

    public void setProjectFeild(String projectFeild) {
        this.projectFeild = projectFeild;
    }

    public String getProjectCity() {
        return projectCity;
    }

    public void setProjectCity(String projectCity) {
        this.projectCity = projectCity;
    }

    public String getProjectFinancingTime() {
        return projectFinancingTime;
    }

    public void setProjectFinancingTime(String projectFinancingTime) {
        this.projectFinancingTime = projectFinancingTime;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Integer getProjectFinancingStatus() {
        return projectFinancingStatus;
    }

    public void setProjectFinancingStatus(Integer projectFinancingStatus) {
        this.projectFinancingStatus = projectFinancingStatus;
    }

    public Integer getProjectFollowsNumber() {
        return projectFollowsNumber;
    }

    public void setProjectFollowsNumber(Integer projectFollowsNumber) {
        this.projectFollowsNumber = projectFollowsNumber;
    }

    public Integer getIsFollows() {
        return isFollows;
    }

    public void setIsFollows(Integer isFollows) {
        this.isFollows = isFollows;
    }
}
