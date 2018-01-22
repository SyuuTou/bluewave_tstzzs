package com.lhjl.tzzs.proxy.dto;

public class ProjectCommentInputDto {
	/**
	 * 项目id
	 */
	private Integer projectId;
	/**
	 * 项目简介
	 */
	private String Comments;
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getComments() {
		return Comments;
	}
	public void setComments(String comments) {
		Comments = comments;
	}
	@Override
	public String toString() {
		return "ProjectCommentInputDto [projectId=" + projectId + ", Comments=" + Comments + "]";
	}
	
}
