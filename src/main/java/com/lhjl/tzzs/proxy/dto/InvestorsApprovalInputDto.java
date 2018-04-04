package com.lhjl.tzzs.proxy.dto;

import java.util.List;

public class InvestorsApprovalInputDto {

    /**搜索内容*/
    private String searchWord;

    /**认证类型*/
    private List<Integer> investorType;

    /**审核状态*/
    private List<Integer> aduitStatus;
    
    /**认证时间排序*/
    private Integer approvalTimeOrder;

    /**认证时间降序*/
    private Integer approvalTimeOrderDesc;

    /**每页显示数量*/
    private Integer pageSize;

    /**页码*/
//    private Integer pageNum;
    private Integer currentPage;
    
    /**
     * 起始索引
     */
    private Long start;

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public List<Integer> getInvestorType() {
		return investorType;
	}

	public void setInvestorType(List<Integer> investorType) {
		this.investorType = investorType;
	}

	public List<Integer> getAduitStatus() {
		return aduitStatus;
	}

	public void setAduitStatus(List<Integer> aduitStatus) {
		this.aduitStatus = aduitStatus;
	}

	public Integer getApprovalTimeOrder() {
		return approvalTimeOrder;
	}

	public void setApprovalTimeOrder(Integer approvalTimeOrder) {
		this.approvalTimeOrder = approvalTimeOrder;
	}

	public Integer getApprovalTimeOrderDesc() {
		return approvalTimeOrderDesc;
	}

	public void setApprovalTimeOrderDesc(Integer approvalTimeOrderDesc) {
		this.approvalTimeOrderDesc = approvalTimeOrderDesc;
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

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

    
    
}
