package com.lhjl.tzzs.proxy.dto;

public class TeamIntroductionDto {
	/**
	 * 主体id
	 */
	private Integer projectId;
	/**
	 * 团队介绍
	 */
	private String data;
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
