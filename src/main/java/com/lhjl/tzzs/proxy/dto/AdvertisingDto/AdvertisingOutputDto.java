package com.lhjl.tzzs.proxy.dto.AdvertisingDto;

public class AdvertisingOutputDto {

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
}
