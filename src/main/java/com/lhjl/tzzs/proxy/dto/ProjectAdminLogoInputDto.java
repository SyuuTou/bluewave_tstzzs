package com.lhjl.tzzs.proxy.dto;

public class ProjectAdminLogoInputDto {
    /**项目id*/
    private Integer projectId;

    /**项目logo*/
    private String projectLogo;

    /**项目简称*/
    private String shortName;

    /**用户id*/
    private Integer userId;

    /**项目类型*/
    private Integer projectType;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }
}
