package com.lhjl.tzzs.proxy.dto;

public class ProjectInvestmentHighlightsInputDto {
	/**
	 * 项目id
	 */
	private Integer projectId;
	/**
	 * 项目的投资亮点
	 */
	private String projectInvestmentHighlights;
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectInvestmentHighlights() {
		return projectInvestmentHighlights;
	}
	public void setProjectInvestmentHighlights(String projectInvestmentHighlights) {
		this.projectInvestmentHighlights = projectInvestmentHighlights;
	}
	@Override
	public String toString() {
		return "ProjectInvestmentHighlightsInputDto [projectId=" + projectId + ", projectInvestmentHighlights="
				+ projectInvestmentHighlights + "]";
	}
	
}
