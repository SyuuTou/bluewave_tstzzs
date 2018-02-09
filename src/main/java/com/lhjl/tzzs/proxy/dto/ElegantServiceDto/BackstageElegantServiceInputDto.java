package com.lhjl.tzzs.proxy.dto.ElegantServiceDto;

public class BackstageElegantServiceInputDto {
    /**
     * 搜索内容
     */
    private String searchWord;
    /**
     * 时间条件上限
     */
    private String beginTime;
    /**
     * 时间条件下限
     */
    private String endTime;
    /**
     * 每页显示数量
     */
    private Integer pageSize;
    /**
     * 当前页码
     */
    private Integer currentPage;

    /** 认证类型 */
    private Integer approveType;
    /** 会员类型 */
    private Integer memberType;
    /** 是否悬赏 */
    private Integer isReward;
    /** 是否领投 */
    private Integer isLeadInvestor;
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getApproveType() {
		return approveType;
	}
	public void setApproveType(Integer approveType) {
		this.approveType = approveType;
	}
	public Integer getMemberType() {
		return memberType;
	}
	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}
	public Integer getIsReward() {
		return isReward;
	}
	public void setIsReward(Integer isReward) {
		this.isReward = isReward;
	}
	public Integer getIsLeadInvestor() {
		return isLeadInvestor;
	}
	public void setIsLeadInvestor(Integer isLeadInvestor) {
		this.isLeadInvestor = isLeadInvestor;
	}
    
}
