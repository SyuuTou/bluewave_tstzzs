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
    private String action_type;
    /**
     * 联系类型
     */
    private String contact_status;

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

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public String getContact_status() {
        return contact_status;
    }

    public void setContact_status(String contact_status) {
        this.contact_status = contact_status;
    }
}
