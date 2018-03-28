package com.lhjl.tzzs.proxy.dto;

public class CommentAndHighlightsInputDto {
	/**
	 * 主体id
	 */
	private Integer subjectId;
	/**
	 * 主体类型
	 * 1 项目
	 * 2 机构
	 */
	private Integer subjectType;
	/**
	 * 简介
	 */
	private String comment;
	/**
	 * 投资亮点
	 */
	private String investmentHighlights;
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getInvestmentHighlights() {
		return investmentHighlights;
	}
	public void setInvestmentHighlights(String investmentHighlights) {
		this.investmentHighlights = investmentHighlights;
	}
	
	
}
