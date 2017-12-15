package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题，摘要
     */
    @Column(name = "sub_title")
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
    @Column(name = "cover_url")
    private String coverUrl;

    /**
     * 转载地址
     */
    @Column(name = "from_rul")
    private String fromRul;

    /**
     * 原文链接
     */
    @Column(name = "source_text_url")
    private String sourceTextUrl;

    /**
     * 权重
     */
    @Column(name = "weighting_factor")
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
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建者
     */
    private String creater;

    @Transient
    private List<MetaColumn> columns;
    @Transient
    private List<MetaSegmentation> segmentations;
    @Transient
    private List<ReportLabel> reportLabels;

    public List<MetaColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<MetaColumn> columns) {
        this.columns = columns;
    }

    public List<MetaSegmentation> getSegmentations() {
        return segmentations;
    }

    public void setSegmentations(List<MetaSegmentation> segmentations) {
        this.segmentations = segmentations;
    }

    public List<ReportLabel> getReportLabels() {
        return reportLabels;
    }

    public void setReportLabels(List<ReportLabel> reportLabels) {
        this.reportLabels = reportLabels;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取副标题，摘要
     *
     * @return sub_title - 副标题，摘要
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * 设置副标题，摘要
     *
     * @param subTitle 副标题，摘要
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * 获取主文
     *
     * @return content - 主文
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置主文
     *
     * @param content 主文
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取点评
     *
     * @return comments - 点评
     */
    public String getComments() {
        return comments;
    }

    /**
     * 设置点评
     *
     * @param comments 点评
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * 获取封面地址
     *
     * @return cover_url - 封面地址
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * 设置封面地址
     *
     * @param coverUrl 封面地址
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    /**
     * 获取转载地址
     *
     * @return from_rul - 转载地址
     */
    public String getFromRul() {
        return fromRul;
    }

    /**
     * 设置转载地址
     *
     * @param fromRul 转载地址
     */
    public void setFromRul(String fromRul) {
        this.fromRul = fromRul;
    }

    /**
     * 获取原文链接
     *
     * @return source_text_url - 原文链接
     */
    public String getSourceTextUrl() {
        return sourceTextUrl;
    }

    /**
     * 设置原文链接
     *
     * @param sourceTextUrl 原文链接
     */
    public void setSourceTextUrl(String sourceTextUrl) {
        this.sourceTextUrl = sourceTextUrl;
    }

    /**
     * 获取权重
     *
     * @return weighting_factor - 权重
     */
    public Integer getWeightingFactor() {
        return weightingFactor;
    }

    /**
     * 设置权重
     *
     * @param weightingFactor 权重
     */
    public void setWeightingFactor(Integer weightingFactor) {
        this.weightingFactor = weightingFactor;
    }

    /**
     * 获取当前状态：0，保存，1，发布
     *
     * @return status - 当前状态：0，保存，1，发布
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置当前状态：0，保存，1，发布
     *
     * @param status 当前状态：0，保存，1，发布
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取是否有效：1:有效，0:无效
     *
     * @return yn - 是否有效：1:有效，0:无效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效：1:有效，0:无效
     *
     * @param yn 是否有效：1:有效，0:无效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建者
     *
     * @return creater - 创建者
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 设置创建者
     *
     * @param creater 创建者
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }
}