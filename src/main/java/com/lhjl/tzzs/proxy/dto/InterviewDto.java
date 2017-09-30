package com.lhjl.tzzs.proxy.dto;

import org.springframework.web.bind.annotation.PathVariable;

public class InterviewDto {
	private  Integer projectsId;
	private  String userId;
    private  String desc;
    private  Integer yn;
	public Integer getYn() {
		return yn;
	}
	public void setYn(Integer yn) {
		this.yn = yn;
	}
	public Integer getProjectsId() {
		return projectsId;
	}
	public void setProjectsId(Integer projectsId) {
		this.projectsId = projectsId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
