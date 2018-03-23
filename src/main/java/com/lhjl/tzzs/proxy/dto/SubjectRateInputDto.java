package com.lhjl.tzzs.proxy.dto;

public class SubjectRateInputDto {
	/**
	 * 主体类型
	 * 1 项目
	 * 2 机构
	 * ...
	 */
	private Integer subjectType;
	/**
	 * 项目等级
	 */
	private Integer ratingStage;
	/**
	 * 评级说明
	 */
	private String ratingDescription;
	
	public Integer getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}
	public Integer getRatingStage() {
		return ratingStage;
	}
	public void setRatingStage(Integer ratingStage) {
		this.ratingStage = ratingStage;
	}
	public String getRatingDescription() {
		return ratingDescription;
	}
	public void setRatingDescription(String ratingDescription) {
		this.ratingDescription = ratingDescription;
	}
	@Override
	public String toString() {
		return "SubjectRateInputDto [subjectType=" + subjectType + ", ratingStage=" + ratingStage
				+ ", ratingDescription=" + ratingDescription + "]";
	}
	
}
