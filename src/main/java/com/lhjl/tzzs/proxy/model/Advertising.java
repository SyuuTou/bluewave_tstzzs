package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

public class Advertising {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 广告位表的id
     */
    @Column(name = "advertising_posistion_id")
    private Integer advertisingPosistionId;

    /**
     * 广告标题
     */
    private String title;

    /**
     * 广告图
     */
    private String picture;

    /**
     * 开始时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否隐藏，0表示隐藏，1表示未隐藏
     */
    private Integer hides;

    /**
     * 编辑状态，0表示正在编辑，1表示编辑完成
     */
    @Column(name = "edit_status")
    private Integer editStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否删除，0表示已被删除，1表示未被删除
     */
    private Integer yn;

    /**
     * 应用（app）id
     */
    private Integer appid;

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
     * 获取广告位表的id
     *
     * @return advertising_posistion_id - 广告位表的id
     */
    public Integer getAdvertisingPosistionId() {
        return advertisingPosistionId;
    }

    /**
     * 设置广告位表的id
     *
     * @param advertisingPosistionId 广告位表的id
     */
    public void setAdvertisingPosistionId(Integer advertisingPosistionId) {
        this.advertisingPosistionId = advertisingPosistionId;
    }

    /**
     * 获取广告标题
     *
     * @return title - 广告标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置广告标题
     *
     * @param title 广告标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取广告图
     *
     * @return picture - 广告图
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置广告图
     *
     * @param picture 广告图
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取开始时间
     *
     * @return begin_time - 开始时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置开始时间
     *
     * @param beginTime 开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取链接地址
     *
     * @return url - 链接地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置链接地址
     *
     * @param url 链接地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取是否隐藏，0表示隐藏，1表示未隐藏
     *
     * @return hides - 是否隐藏，0表示隐藏，1表示未隐藏
     */
    public Integer getHides() {
        return hides;
    }

    /**
     * 设置是否隐藏，0表示隐藏，1表示未隐藏
     *
     * @param hides 是否隐藏，0表示隐藏，1表示未隐藏
     */
    public void setHides(Integer hides) {
        this.hides = hides;
    }

    /**
     * 获取编辑状态，0表示正在编辑，1表示编辑完成
     *
     * @return edit_status - 编辑状态，0表示正在编辑，1表示编辑完成
     */
    public Integer getEditStatus() {
        return editStatus;
    }

    /**
     * 设置编辑状态，0表示正在编辑，1表示编辑完成
     *
     * @param editStatus 编辑状态，0表示正在编辑，1表示编辑完成
     */
    public void setEditStatus(Integer editStatus) {
        this.editStatus = editStatus;
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
     * 获取是否删除，0表示已被删除，1表示未被删除
     *
     * @return yn - 是否删除，0表示已被删除，1表示未被删除
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否删除，0表示已被删除，1表示未被删除
     *
     * @param yn 是否删除，0表示已被删除，1表示未被删除
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取应用（app）id
     *
     * @return appid - 应用（app）id
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * 设置应用（app）id
     *
     * @param appid 应用（app）id
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }

	@Override
	public String toString() {
		return "Advertising [id=" + id + ", advertisingPosistionId=" + advertisingPosistionId + ", title=" + title
				+ ", picture=" + picture + ", beginTime=" + beginTime + ", endTime=" + endTime + ", url=" + url
				+ ", sort=" + sort + ", hides=" + hides + ", editStatus=" + editStatus + ", createTime=" + createTime
				+ ", yn=" + yn + ", appid=" + appid + "]";
	}
    
}