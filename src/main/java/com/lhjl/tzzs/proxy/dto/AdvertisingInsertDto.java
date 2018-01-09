package com.lhjl.tzzs.proxy.dto;

public class AdvertisingInsertDto {
	/**
	 * 小程序id
	 */
	private Integer appid;
	/**
	 * 广告位id
	 */
	private Integer advertisingPosistionId;
	/**
	 * 广告标题
	 */
	private String title;
	/**
	 * 封面
	 */
	private String picture;
	/**
	 * 开始时间
	 */
	private String beginTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 链接地址
	 */
	private String url;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 是否隐藏，0表示隐藏，1表示未隐藏'
	 */
	private Integer hides;
	
	public Integer getAppid() {
		return appid;
	}
	public void setAppid(Integer appid) {
		this.appid = appid;
	}
	public Integer getAdvertisingPosistionId() {
		return advertisingPosistionId;
	}
	public void setAdvertisingPosistionId(Integer advertisingPosistionId) {
		this.advertisingPosistionId = advertisingPosistionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getHides() {
		return hides;
	}
	public void setHides(Integer hides) {
		this.hides = hides;
	}
	@Override
	public String toString() {
		return "AdvertisingInsertDto [advertisingPosistionId=" + advertisingPosistionId + ", title=" + title
				+ ", picture=" + picture + ", beginTime=" + beginTime + ", endTime=" + endTime + ", url=" + url
				+ ", sort=" + sort + ", hides=" + hides + "]";
	}
	
}
