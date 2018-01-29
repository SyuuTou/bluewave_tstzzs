package com.lhjl.tzzs.proxy.dto.ProjectSendBAuditDto;

public class ProjectLogoInfoOutputDto {
    /**项目logo*/
    private String projectLogo;

    /**项目id*/
    private Integer id;

    /**项目简称*/
    private String shortName;

    /**项目来源id*/
    private Integer projectSourceId;

    /**用户名*/
    private String userName;

    /**用户id*/
    private Integer userId;

    /**审核状态*/
    private Integer aduitStatus;

    /**项目等级*/
    private Integer projectLevel;

    public String getProjectLogo() {
        return projectLogo;
    }

    public void setProjectLogo(String projectLogo) {
        this.projectLogo = projectLogo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getProjectSourceId() {
        return projectSourceId;
    }

    public void setProjectSourceId(Integer projectSourceId) {
        this.projectSourceId = projectSourceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(Integer aduitStatus) {
        this.aduitStatus = aduitStatus;
    }

    public Integer getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(Integer projectLevel) {
        this.projectLevel = projectLevel;
    }
}
