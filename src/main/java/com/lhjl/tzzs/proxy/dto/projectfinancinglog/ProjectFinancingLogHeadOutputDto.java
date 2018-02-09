package com.lhjl.tzzs.proxy.dto.projectfinancinglog;

import java.util.Date;

public class ProjectFinancingLogHeadOutputDto {
	/**投资事件主键id*/
    private Integer id;
    
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
    /**
     * 提交者token
     */
    private String submitorToken;
    /**
     * 提交时间
     */
    private Date createTime;
    
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubmitorToken() {
		return submitorToken;
	}
	public void setSubmitorToken(String submitorToken) {
		this.submitorToken = submitorToken;
	}
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
		return "ProjectFinancingLogHeadOutputDto [id=" + id + ", serialNumber=" + serialNumber + ", typeName="
				+ typeName + ", projectName=" + projectName + ", followStatus=" + followStatus + ", submitorToken="
				+ submitorToken + ", createTime=" + createTime + "]";
	}
	
    
}
