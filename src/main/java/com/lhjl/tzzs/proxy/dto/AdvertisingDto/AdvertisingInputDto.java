package com.lhjl.tzzs.proxy.dto.AdvertisingDto;

public class AdvertisingInputDto {
    /**
     * 位置id
     */
    private Integer positionId;
    /**
     * 应用id
     */
    private Integer appId;
    /**
     * 编辑完成状态
     */
    private Integer editStatus;
    /**
     * 隐藏状态
     */
    private String hides;
    /**
     * 是否验证时间是否符合当前时间
     */
    private Integer timeYn;
    /**
     * 开始时间上限
     */
    private String startTime;
    /**
     * 开始时间下限
     */
    private String endTime;

    /**页码*/
    private Integer pageNum;

    /**每页显示数量*/
    private Integer pageSize;

    /**是否根据beginTime字段排序。1表示是，其他不是*/
    private Integer beginTimeSort;

    /**是否根据beginTime字段降序排序。1表示是，其他不是*/
    private Integer beginTimeSortDesc;

    /**是否根据order字段排序。1表示是，其他不是*/
    private Integer orderSort;

    /**是否根据order字段降序排序。1表示是，其他不是*/
    private Integer orderSortDesc;

    /**是否根据endTime字段排序。1表示是，其他不是*/
    private Integer endTimeSort;

    /**是否根据endTime字段降序排序。1表示是，其他不是*/
    private Integer endTimeSortDesc;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(Integer editStatus) {
        this.editStatus = editStatus;
    }

    public String getHides() {
        return hides;
    }

    public void setHides(String hides) {
        this.hides = hides;
    }

    public Integer getTimeYn() {
        return timeYn;
    }

    public void setTimeYn(Integer timeYn) {
        this.timeYn = timeYn;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public Integer getBeginTimeSort() {
        return beginTimeSort;
    }

    public void setBeginTimeSort(Integer beginTimeSort) {
        this.beginTimeSort = beginTimeSort;
    }

    public Integer getBeginTimeSortDesc() {
        return beginTimeSortDesc;
    }

    public void setBeginTimeSortDesc(Integer beginTimeSortDesc) {
        this.beginTimeSortDesc = beginTimeSortDesc;
    }

    public Integer getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(Integer orderSort) {
        this.orderSort = orderSort;
    }

    public Integer getOrderSortDesc() {
        return orderSortDesc;
    }

    public void setOrderSortDesc(Integer orderSortDesc) {
        this.orderSortDesc = orderSortDesc;
    }

    public Integer getEndTimeSort() {
        return endTimeSort;
    }

    public void setEndTimeSort(Integer endTimeSort) {
        this.endTimeSort = endTimeSort;
    }

    public Integer getEndTimeSortDesc() {
        return endTimeSortDesc;
    }

    public void setEndTimeSortDesc(Integer endTimeSortDesc) {
        this.endTimeSortDesc = endTimeSortDesc;
    }
}
