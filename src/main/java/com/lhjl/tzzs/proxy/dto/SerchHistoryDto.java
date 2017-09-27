package com.lhjl.tzzs.proxy.dto;

import java.util.Date;

public class SerchHistoryDto {
    private String search;
    private String date;
    private  Integer id;
    private  Integer user_id;
    private Date search_time;
    private Integer yn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getSearch_time() {
        return search_time;
    }

    public void setSearch_time(Date search_time) {
        this.search_time = search_time;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
