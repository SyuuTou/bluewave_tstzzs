package com.lhjl.tzzs.proxy.dto;

public class ProjectsListInputDto {
	/**
	 * 需要进行排序的列的字段
	 * 关注量：focuscount
	 * 约谈量：interviewcount
	 * 浏览量：viewcount
	 */
	private String column;
	/**  
	 * 排序的方式
	 * asc  desc
	 */
	private String order;  
	
	private String keyword;
	
	private Integer currentPage;
	
	private Integer pageSize;
	
	private Long start;
	
	/**
	 * 城市的查询条件
	 */
//	private String city;
	/**
	 * 项目来源的查询条件
	 */
	private String projectSource;
	/**
	 * 项目等级的查询条件
	 * 0表示D级，1表示C级，2表示B级，3表示A级，4表示S级'
	 */
	private Integer ratingStage;
	/**
	 * 融资状态的查询条件
	 * stage 传给的值为on的时候表示正在融
	 */
	private String stage;
	/**
	 * 跟进状态的查询条件
	 */
	private String followStatus;
	/**
	 * 主体类型
	 * 1 项目
	 * 2 机构
	 */
	private Integer subjectTypeId;
	
	public Integer getSubjectTypeId() {
		return subjectTypeId;
	}
	public void setSubjectTypeId(Integer subjectTypeId) {
		this.subjectTypeId = subjectTypeId;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
//	public String getCity() {
//		return city;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}
	public String getProjectSource() {
		return projectSource;
	}
	public void setProjectSource(String projectSource) {
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
	public String getFollowStatus() {
		return followStatus;
	}
	public void setFollowStatus(String followStatus) {
		this.followStatus = followStatus;
	}
	@Override
	public String toString() {
		return "ProjectsListInputDto [column=" + column + ", order=" + order + ", keyword=" + keyword + ", currentPage="
				+ currentPage + ", pageSize=" + pageSize + ", start=" + start + ", projectSource=" + projectSource
				+ ", ratingStage=" + ratingStage + ", stage=" + stage + ", followStatus=" + followStatus
				+ ", subjectTypeId=" + subjectTypeId + "]";
	}
}

