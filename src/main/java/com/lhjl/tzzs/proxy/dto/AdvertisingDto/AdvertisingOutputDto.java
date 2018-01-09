package com.lhjl.tzzs.proxy.dto.AdvertisingDto;

public class AdvertisingOutputDto {

    /**广告位置*/
    private String position;

    /**排序*/
    private Integer sort;

    /**开始时间*/
    private String beginTime;

    /**结束时间*/
    private String endTime;
    /**
     * 广告标题
     */
    private String title;
    /**
     * 广告图
     */
    private String picture;
    /**
     * 广告对应的链接
     */
    private String url;

    /**是否隐藏*/
    private String hides;

    /**
     * 广告对应记录的id
     */
    private Integer id;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getHides() {
        return hides;
    }

    public void setHides(String hides) {
        this.hides = hides;
    }
}
