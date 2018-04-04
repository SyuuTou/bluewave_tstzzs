package com.lhjl.tzzs.proxy.dto;

public class ProjectSendBAdminListInputDto {
    /**搜索词*/
    private String searchWord;

    /**搜索开始时间*/
    private String begainTime;

    /**搜索结束时间*/
    private String endTime;

    /**项目来源*/
    private Integer projetcSource;

    /**提交时间排序*/
    private Integer creatTimeOrder;

    /**提交时间降序*/
    private Integer creatTimeOrderDesc;

    /**审核时间排序*/
    private Integer auditTimeOrder;

    /**审核时间降序*/
    private Integer auditTimeOrderDesc;

    /**当前页码*/
//    private Integer pageNum;
    
    /**
     * 当前页码
     */
    private Integer currentPage;

    /**每页显示数量*/ 
    private Integer pageSize;
    /**
     * 开始索引
     */
    private Long start;
    
    
    
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

	public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getBegainTime() {
        return begainTime;
    }

    public void setBegainTime(String begainTime) {
        this.begainTime = begainTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getProjetcSource() {
        return projetcSource;
    }

    public void setProjetcSource(Integer projetcSource) {
        this.projetcSource = projetcSource;
    }

    public Integer getCreatTimeOrder() {
        return creatTimeOrder;
    }

    public void setCreatTimeOrder(Integer creatTimeOrder) {
        this.creatTimeOrder = creatTimeOrder;
    }

    public Integer getCreatTimeOrderDesc() {
        return creatTimeOrderDesc;
    }

    public void setCreatTimeOrderDesc(Integer creatTimeOrderDesc) {
        this.creatTimeOrderDesc = creatTimeOrderDesc;
    }

    public Integer getAuditTimeOrder() {
        return auditTimeOrder;
    }

    public void setAuditTimeOrder(Integer auditTimeOrder) {
        this.auditTimeOrder = auditTimeOrder;
    }

    public Integer getAuditTimeOrderDesc() {
        return auditTimeOrderDesc;
    }

    public void setAuditTimeOrderDesc(Integer auditTimeOrderDesc) {
        this.auditTimeOrderDesc = auditTimeOrderDesc;
    }

/*    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }*/

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
