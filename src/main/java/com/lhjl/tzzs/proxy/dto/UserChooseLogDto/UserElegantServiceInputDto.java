package com.lhjl.tzzs.proxy.dto.UserChooseLogDto;

public class UserElegantServiceInputDto {

    /**
     * 搜索内容
     */
    private String searchWord;
    /**
     * 搜索开始时间
     */
    private String beginTime;
    /**
     * 搜索结束时间
     */
    private String endTime;
    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 每页显示数量
     */
    private Integer pageSize;
    /**
     * 行为类型
     */
    private String actonType;
    /**
     * 联系类型
     */
    private String contactStatus;

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

    public String getActonType() {
        return actonType;
    }

    public void setActonType(String actonType) {
        this.actonType = actonType;
    }

    public String getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(String contactStatus) {
        this.contactStatus = contactStatus;
    }
}
