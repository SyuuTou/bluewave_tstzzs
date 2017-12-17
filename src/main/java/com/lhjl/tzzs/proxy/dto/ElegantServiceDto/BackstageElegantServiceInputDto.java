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
}
