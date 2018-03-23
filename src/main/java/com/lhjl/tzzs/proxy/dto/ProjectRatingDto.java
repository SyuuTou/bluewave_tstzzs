package com.lhjl.tzzs.proxy.dto;

public class ProjectRatingDto {
    /**
     * 主体id
     */
    private Integer subjectId;
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
    
    /**
     * 主体类型
     * 1 项目
     * 2 机构
     */
    private Integer subjectType;
    

    public Integer getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}

    public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
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
