package com.lhjl.tzzs.proxy.dto;

public class ProjectRatingDto {
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 评级等级
     */
    private Integer ratingStage;
    /**
     * 评级描述
     */
    private  String ratingDiscription;
    /**
     * 评级管理员姓名
     */
    private String ratingAdminName;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getRatingStage() {
        return ratingStage;
    }

    public void setRatingStage(Integer ratingStage) {
        this.ratingStage = ratingStage;
    }

    public String getRatingDiscription() {
        return ratingDiscription;
    }

    public void setRatingDiscription(String ratingDiscription) {
        this.ratingDiscription = ratingDiscription;
    }

    public String getRatingAdminName() {
        return ratingAdminName;
    }

    public void setRatingAdminName(String ratingAdminName) {
        this.ratingAdminName = ratingAdminName;
    }
}
