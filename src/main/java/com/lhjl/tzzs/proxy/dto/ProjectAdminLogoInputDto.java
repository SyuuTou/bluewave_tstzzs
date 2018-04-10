package com.lhjl.tzzs.proxy.dto;

public class ProjectAdminLogoInputDto {
    /**
     * 主体id
     */
    private Integer subjectId;

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
     *  1		创业公司
	 *	2		基金公司
	 *	3		产业公司
	 *	4		上市公司
	 *	5		投资机构
     */
    private Integer projectType;
    /**
     * 主体类别
     * 用于确定属于对项目还是对机构的操作
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
