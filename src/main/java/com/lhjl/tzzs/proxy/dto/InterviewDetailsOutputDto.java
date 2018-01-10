package com.lhjl.tzzs.proxy.dto;

import java.util.List;

public class InterviewDetailsOutputDto {
	/**
	 * 约谈id
	 */
	private Integer id;
	
	private String  actualName;
	
	private String companyName;
	
	private String phonenumber;
	
	private String companyDuties;
	
	private String wechat;
	
	private String email;
	
	private String city;
	
	//行业领域
	private String industry;
	//教育经历
	private List<String> educationExperience;
	//身份类型
	private Integer identityType;
	//工作经历 
	private List<String> workExperience;
	//个人简介
	private String userDesc;
	//公司简介
	private String companyDesc;
	//当前需求
	private String demand;
	//工作名片
	private String workCard;
	//项目简称
	private String shortName;
	//项目简介
	private String commet;
	//约谈内容
	private String interviewDesc;
	//批注
	private String comment;
	//跟进状态
	private Integer followStatus;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getActualName() {
		return actualName;
	}
	public void setActualName(String actualName) {
		this.actualName = actualName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getCompanyDuties() {
		return companyDuties;
	}
	public void setCompanyDuties(String companyDuties) {
		this.companyDuties = companyDuties;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public List<String> getEducationExperience() {
		return educationExperience;
	}
	public void setEducationExperience(List<String> educationExperience) {
		this.educationExperience = educationExperience;
	}
	public Integer getIdentityType() {
		return identityType;
	}
	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}
	public List<String> getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(List<String> workExperience) {
		this.workExperience = workExperience;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getCompanyDesc() {
		return companyDesc;
	}
	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}
	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
	}
	public String getWorkCard() {
		return workCard;
	}
	public void setWorkCard(String workCard) {
		this.workCard = workCard;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCommet() {
		return commet;
	}
	public void setCommet(String commet) {
		this.commet = commet;
	}
	public String getInterviewDesc() {
		return interviewDesc;
	}
	public void setInterviewDesc(String interviewDesc) {
		this.interviewDesc = interviewDesc;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getFollowStatus() {
		return followStatus;
	}
	public void setFollowStatus(Integer followStatus) {
		this.followStatus = followStatus;
	}
	@Override
	public String toString() {
		return "InterviewDetailsOutputDto [id=" + id + ", actualName=" + actualName + ", companyName=" + companyName
				+ ", phonenumber=" + phonenumber + ", companyDuties=" + companyDuties + ", wechat=" + wechat
				+ ", email=" + email + ", city=" + city + ", industry=" + industry + ", educationExperience="
				+ educationExperience + ", identityType=" + identityType + ", workExperience=" + workExperience
				+ ", userDesc=" + userDesc + ", companyDesc=" + companyDesc + ", demand=" + demand + ", workCard="
				+ workCard + ", shortName=" + shortName + ", commet=" + commet + ", interviewDesc=" + interviewDesc
				+ ", comment=" + comment + ", followStatus=" + followStatus + "]";
	}

	
	
	
	
}
