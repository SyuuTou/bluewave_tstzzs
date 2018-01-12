package com.lhjl.tzzs.proxy.dto;

import java.util.Date;

public class AdminUserListInputDto {
    /**搜索词*/
    private String searchWord;

    /**搜索开始时间*/
    private String begainTime;

    /**搜索结束时间*/
    private String endTime;

    /**身份类型筛选条件*/
    private Integer identityType;

    /**认证类型*/
    private Integer investorType;

    /**用户会员类型*/
    private Integer userLevelType;

    /**是否是根据注册时间排序*/
    private Integer registerTimeOrder;

    /**是否按注册时间降序*/
    private Integer registerTimeOrderDesc;

    /**是否是根据更新时间排序*/
    private Integer updateTimeOrder;

    /**是否按更新时间排序降序*/
    private Integer updateTimeOrderDesc;

    /**会员截止时间排序*/
    private Integer userLevelEndTimeOrder;

    /**会员截止时间排序降序*/
    private Integer userLevelEndTimeOrderDesc;

    /**页码*/
    private Integer pageNum;

    /**一页显示数量*/
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

    public Integer getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    public Integer getInvestorType() {
        return investorType;
    }

    public void setInvestorType(Integer investorType) {
        this.investorType = investorType;
    }

    public Integer getUserLevelType() {
        return userLevelType;
    }

    public void setUserLevelType(Integer userLevelType) {
        this.userLevelType = userLevelType;
    }

    public Integer getRegisterTimeOrder() {
        return registerTimeOrder;
    }

    public void setRegisterTimeOrder(Integer registerTimeOrder) {
        this.registerTimeOrder = registerTimeOrder;
    }

    public Integer getRegisterTimeOrderDesc() {
        return registerTimeOrderDesc;
    }

    public void setRegisterTimeOrderDesc(Integer registerTimeOrderDesc) {
        this.registerTimeOrderDesc = registerTimeOrderDesc;
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

    public Integer getUserLevelEndTimeOrder() {
        return userLevelEndTimeOrder;
    }

    public void setUserLevelEndTimeOrder(Integer userLevelEndTimeOrder) {
        this.userLevelEndTimeOrder = userLevelEndTimeOrder;
    }

    public Integer getUserLevelEndTimeOrderDesc() {
        return userLevelEndTimeOrderDesc;
    }

    public void setUserLevelEndTimeOrderDesc(Integer userLevelEndTimeOrderDesc) {
        this.userLevelEndTimeOrderDesc = userLevelEndTimeOrderDesc;
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
