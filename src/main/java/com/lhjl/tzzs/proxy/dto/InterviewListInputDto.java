package com.lhjl.tzzs.proxy.dto;

public class InterviewListInputDto {
	/**
     * 搜索内容
     */
    private String keyWords;
    /**
     * 排序方式：asc;desc
     */
    private String order;
    /**
     * 跟进状态
     */
    private Integer followStatus;
    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 每页显示数量
     */
    private Integer pageSize;
    
    /**
     * 分页查询的开始索引
     * @return
     */
    private Long start;
    

	
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Integer getFollowStatus() {
		return followStatus;
	}
	public void setFollowStatus(Integer followStatus) {
		this.followStatus = followStatus;
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
		return "InterviewListInputDto [keyWords=" + keyWords + ", order=" + order + ", followStatus=" + followStatus
				+ ", currentPage=" + currentPage + ", pageSize=" + pageSize + ", start=" + start + "]";
	}

    
	
}
