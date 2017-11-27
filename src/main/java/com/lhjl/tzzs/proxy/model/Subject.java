package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 全称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 图片信息（logo,头像等）
     */
    private String picture;

    /**
     * 简介
     */
    private String summary;

    /**
     * 源数据id
     */
    @Column(name = "sourceId")
    private Integer sourceid;

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
     * 获取全称
     *
     * @return full_name - 全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置全称
     *
     * @param fullName 全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取简称
     *
     * @return short_name - 简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置简称
     *
     * @param shortName 简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取图片信息（logo,头像等）
     *
     * @return picture - 图片信息（logo,头像等）
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置图片信息（logo,头像等）
     *
     * @param picture 图片信息（logo,头像等）
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取简介
     *
     * @return summary - 简介
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置简介
     *
     * @param summary 简介
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取源数据id
     *
     * @return sourceId - 源数据id
     */
    public Integer getSourceid() {
        return sourceid;
    }

    /**
     * 设置源数据id
     *
     * @param sourceid 源数据id
     */
    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }
}