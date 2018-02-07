package com.lhjl.tzzs.proxy.dto.projectfinancinglog;

public class ProjectFinancingLogHeadOutputDto {
    /**投资事件编号*/
    private Integer serialNumber;

    /**来源类型*/
    private String typeName;
    
    /**项目简称*/
    private String projectName;
    /**
     * 跟进状态
     */
    private Integer followStatus;
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getFollowStatus() {
		return followStatus;
	}
	public void setFollowStatus(Integer followStatus) {
		this.followStatus = followStatus;
	}
	@Override
	public String toString() {
		return "ProjectFinancingLogHeadOutputDto [serialNumber=" + serialNumber + ", typeName=" + typeName
				+ ", projectName=" + projectName + ", followStatus=" + followStatus + "]";
	}
    
}
