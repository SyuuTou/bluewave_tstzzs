package com.lhjl.tzzs.proxy.dto;

import java.util.Date;

public class ProjectsListOutputDto {
	/**
	 * 项目的唯一标识id
	 */
	private Integer id;
	/**
	 * 项目编号
	 */
	private Integer serialNumber;
	/**
	 * 项目简称
	 */
	private String shortName;
	/**
	 * 一句话介绍
	 */
	private String kernelDesc;
	/**
	 * 所在城市
	 */
	private String city;
	/**
	 * 项目来源
	 * 0表示创始人提交，1表示投资人提交，2,3,4等等有多个项目来源
	 */
	private Integer projectSource;
	
	/**
	 * 项目等级
	 * 0表示D级，1表示C级，2表示B级，3表示A级，4表示S级'
	 */
	private Integer ratingStage;
	/**
	 * 融资状态
	 * 改值返回为null字段的时候表示正在融A轮
	 */
	private String stage;
	/**
	 * 关注量
	 */
	private Integer focusCount;
	/**
	 * 约谈量 
	 */
	private Integer interviewCount;
	/**
	 * 浏览量 暂时无**********
	 */
	private Integer viewCount;
	
	/**
	 * 跟进状态 暂时无**********
	 */
	private Integer followStatus;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getKernelDesc() {
		return kernelDesc;
	}

	public void setKernelDesc(String kernelDesc) {
		this.kernelDesc = kernelDesc;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getProjectSource() {
		return projectSource;
	}

	public void setProjectSource(Integer projectSource) {
		this.projectSource = projectSource;
	}

	public Integer getRatingStage() {
		return ratingStage;
	}

	public void setRatingStage(Integer ratingStage) {
		this.ratingStage = ratingStage;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public Integer getFocusCount() {
		return focusCount;
	}

	public void setFocusCount(Integer focusCount) {
		this.focusCount = focusCount;
	}

	public Integer getInterviewCount() {
		return interviewCount;
	}

	public void setInterviewCount(Integer interviewCount) {
		this.interviewCount = interviewCount;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getFollowStatus() {
		return followStatus;
	}

	public void setFollowStatus(Integer followStatus) {
		this.followStatus = followStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "ProjectsListOutputDto [id=" + id + ", serialNumber=" + serialNumber + ", shortName=" + shortName
				+ ", kernelDesc=" + kernelDesc + ", city=" + city + ", projectSource=" + projectSource
				+ ", ratingStage=" + ratingStage + ", stage=" + stage + ", focusCount=" + focusCount
				+ ", interviewCount=" + interviewCount + ", viewCount=" + viewCount + ", followStatus=" + followStatus
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
	
}
