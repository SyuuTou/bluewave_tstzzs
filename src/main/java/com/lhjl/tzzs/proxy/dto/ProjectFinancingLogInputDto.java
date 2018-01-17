package com.lhjl.tzzs.proxy.dto;

import java.util.List;

public class ProjectFinancingLogInputDto {
    /**搜索词*/
    private String searchWord;

    /**搜索开始时间*/
    private String begainTime;

    /**搜索结束时间*/
    private String endTime;

    /**数据来源*/
    private List<Integer> dataSource;

    /**轮次*/
    private List<String> stage;

    /**币种*/
    private List<Integer> currency;

    /**提交时间排序*/
    private Integer creatTimeOrder;

    /**提交时间降序*/
    private Integer creatTimeOrderDesc;

    /**更新时间排序*/
    private Integer updateTimeOrder;

    /**更新时间降序*/
    private Integer updateTimeOrderDesc;

    /**当前页码*/
    private Integer pageNum;

    /**每页显示数量*/
    private Integer pageSize;

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

    public List<Integer> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<Integer> dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> getStage() {
        return stage;
    }

    public void setStage(List<String> stage) {
        this.stage = stage;
    }

    public List<Integer> getCurrency() {
        return currency;
    }

    public void setCurrency(List<Integer> currency) {
        this.currency = currency;
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

    public Integer getUpdateTimeOrder() {
        return updateTimeOrder;
    }

    public void setUpdateTimeOrder(Integer updateTimeOrder) {
        this.updateTimeOrder = updateTimeOrder;
    }

    public Integer getUpdateTimeOrderDesc() {
        return updateTimeOrderDesc;
    }

    public void setUpdateTimeOrderDesc(Integer updateTimeOrderDesc) {
        this.updateTimeOrderDesc = updateTimeOrderDesc;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
