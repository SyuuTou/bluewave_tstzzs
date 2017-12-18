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
    private Integer actonType;
    /**
     * 联系类型
     */
    private Integer contactStatus;

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

    public Integer getActonType() {
        return actonType;
    }

    public void setActonType(Integer actonType) {
        this.actonType = actonType;
    }

    public Integer getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(Integer contactStatus) {
        this.contactStatus = contactStatus;
    }
}
