package com.lhjl.tzzs.proxy.dto;

import io.swagger.models.auth.In;

public class ProjectReqDto {

    /** 用户Token */
    private String token;
    /** 机构类型 */
    private String dataVcType;
    /** 搜索关键字 */
    private String keyWords;
    /** 阶段 */
    private String stage;
    /** 领域 */
    private String segmentation;
    /** 城市/地域 */
    private String city;
    /** 教育背景 */
    private String edus;
    /** 工作背景 */
    private String works;
    /** 融资时间 */
    private String financingTime;

    private Integer pageNo;
    private Integer pageSize = 10;

    private String beginTime;
    private String endTime;

    private Integer offset;
    /**
     * 是否按照融资时间排序
     */
    private Integer financingOrder;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDataVcType() {
        return dataVcType;
    }

    public void setDataVcType(String dataVcType) {
        this.dataVcType = dataVcType;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEdus() {
        return edus;
    }

    public void setEdus(String edus) {
        this.edus = edus;
    }

    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }

    public String getFinancingTime() {
        return financingTime;
    }

    public void setFinancingTime(String financingTime) {
        this.financingTime = financingTime;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getFinancingOrder() {
        return financingOrder;
    }

    public void setFinancingOrder(Integer financingOrder) {
        this.financingOrder = financingOrder;
    }
}
