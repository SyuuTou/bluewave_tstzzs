package com.lhjl.tzzs.proxy.dto;

import java.util.Date;

public class InterviewListOutputDto {
	/**
	 * 约谈id
	 */
	private Integer id;
	/**
	 * 真实姓名
	 */
	private String actualName;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 公司职位
	 */
	private String companyDuties;
	/**
	 * 约谈项目
	 */
	private String shortName;
	/**
	 * 约谈内容
	 */
	private String desc;
	/**
	 * 约谈时间
	 */
	private Date createTime;
	/**
	 * 跟进状态
	 */
	private Integer followStatus;
	/**
	 * 批注
	 */
	private String comment;
	
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
	public String getCompanyDuties() {
		return companyDuties;
	}
	public void setCompanyDuties(String companyDuties) {
		this.companyDuties = companyDuties;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getFollowStatus() {
		return followStatus;
	}
	public void setFollowStatus(Integer followStatus) {
		this.followStatus = followStatus;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "InterviewListOutputDto [id=" + id + ", actualName=" + actualName + ", companyName=" + companyName
				+ ", companyDuties=" + companyDuties + ", shortName=" + shortName + ", desc=" + desc + ", createTime="
				+ createTime + ", followStatus=" + followStatus + ", comment=" + comment + "]";
	}
	
	
	
}
