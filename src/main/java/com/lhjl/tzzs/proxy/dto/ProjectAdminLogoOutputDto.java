package com.lhjl.tzzs.proxy.dto;

public class ProjectAdminLogoOutputDto {
    /**项目id*/
    private Integer id;

    /**项目logo*/
    private String logo;

    /**项目简称*/
    private String shortName;

    /**项目类型*/
    private String type;

    /**认领状态*/
    private String claimStatus;

    /**项目等级*/
    private String stage;

    /**跟进状态*/
    private String followStatus;

    /**项目管理员*/
    private String projectAdmin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(String followStatus) {
        this.followStatus = followStatus;
    }

    public String getProjectAdmin() {
        return projectAdmin;
    }

    public void setProjectAdmin(String projectAdmin) {
        this.projectAdmin = projectAdmin;
    }

	@Override
	public String toString() {
		return "ProjectAdminLogoOutputDto [id=" + id + ", logo=" + logo + ", shortName=" + shortName + ", type=" + type
				+ ", claimStatus=" + claimStatus + ", stage=" + stage + ", followStatus=" + followStatus
				+ ", projectAdmin=" + projectAdmin + "]";
	}
    
}
