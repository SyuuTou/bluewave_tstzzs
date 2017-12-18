package com.lhjl.tzzs.proxy.dto.bluewave;

import java.util.Date;
import java.util.List;

public class ReportReqBody {
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题，摘要
     */
    private String subTitle;

    /**
     * 主文
     */
    private String content;

    /**
     * 点评
     */
    private String comments;

    /**
     * 封面地址
     */
    private String coverUrl;

    /**
     * 转载地址
     */
    private String fromRul;

    /**
     * 原文链接
     */
    private String sourceTextUrl;

    /**
     * 权重
     */
    private Integer weightingFactor;

    /**
     * 当前状态：0，保存，1，发布
     */
    private Integer status;

    /**
     * 是否有效：1:有效，0:无效
     */
    private Integer yn;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建者
     */
    private String creater;

    private List<Integer> columns;
    private List<Integer> segmentations;
    private List<String> reportLabels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getFromRul() {
        return fromRul;
    }

    public void setFromRul(String fromRul) {
        this.fromRul = fromRul;
    }

    public String getSourceTextUrl() {
        return sourceTextUrl;
    }

    public void setSourceTextUrl(String sourceTextUrl) {
        this.sourceTextUrl = sourceTextUrl;
    }

    public Integer getWeightingFactor() {
        return weightingFactor;
    }

    public void setWeightingFactor(Integer weightingFactor) {
        this.weightingFactor = weightingFactor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public List<Integer> getColumns() {
        return columns;
    }

    public void setColumns(List<Integer> columns) {
        this.columns = columns;
    }

    public List<Integer> getSegmentations() {
        return segmentations;
    }

    public void setSegmentations(List<Integer> segmentations) {
        this.segmentations = segmentations;
    }

    public List<String> getReportLabels() {
        return reportLabels;
    }

    public void setReportLabels(List<String> reportLabels) {
        this.reportLabels = reportLabels;
    }
}