package com.lhjl.tzzs.proxy.dto.projectfinancinglog;

public class ProjectFinancingLogHeadInputDto {
	/**
	 * 投资事件id
	 */
	private Integer projectFinancingLogId;
	/**
	 * 公司简称
	 */
	private String shortName;
	/**
	 * 跟进状态
	 */
	private Integer followStatus;
	/**
	 * 备注
	 */
	private String description;
	/**
	 * 更新者状态
	 */
	private String submitorToken;
	public Integer getProjectFinancingLogId() {
		return projectFinancingLogId;
	}
	public void setProjectFinancingLogId(Integer projectFinancingLogId) {
		this.projectFinancingLogId = projectFinancingLogId;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public Integer getFollowStatus() {
		return followStatus;
	}
	public void setFollowStatus(Integer followStatus) {
		this.followStatus = followStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSubmitorToken() {
		return submitorToken;
	}
	public void setSubmitorToken(String submitorToken) {
		this.submitorToken = submitorToken;
	}
	@Override
	public String toString() {
		return "ProjectFinancingLogHeadInputDto [projectFinancingLogId=" + projectFinancingLogId + ", shortName="
				+ shortName + ", followStatus=" + followStatus + ", description=" + description + ", submitorToken="
				+ submitorToken + "]";
	}
	
	
	
}
