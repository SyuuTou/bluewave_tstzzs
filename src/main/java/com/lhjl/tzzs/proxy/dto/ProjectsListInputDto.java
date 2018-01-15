package com.lhjl.tzzs.proxy.dto;

public class ProjectsListInputDto {
	/**
	 * 排序的字段
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

	@Override
	public String toString() {
		return "ProjectsListInputDto [column=" + column + ", order=" + order + ", keyword=" + keyword + ", currentPage="
				+ currentPage + ", pageSize=" + pageSize + ", start=" + start + "]";
	}
}
