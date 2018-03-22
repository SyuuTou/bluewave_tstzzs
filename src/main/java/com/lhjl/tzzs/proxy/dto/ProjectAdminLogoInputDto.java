package com.lhjl.tzzs.proxy.dto;

public class ProjectAdminLogoInputDto {
    /**
     * 主体id
     * 联合主体类型判断是属于项目还是机构
     */
    private Integer projectId;

    /**项目logo*/
    private String projectLogo;

    /**项目简称*/
    private String shortName;

    /**
     * 用户id
     * 机构或者项目管理员的id
     */
    private Integer userId;

    /**
     * 项目的五个类别
     */
    private Integer projectType;
    /**
     * 主体类别
     * 用于确定属于对项目还是对机构的操作
     * 1 项目
     * 2 机构
     */
    private Integer subjectType;
    
//    private 
    

    public Integer getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}

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
