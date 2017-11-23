package com.lhjl.tzzs.proxy.dto;

/**
 * Created by zyy on 2017/11/22.
 */
public class SaveInformationDto {
    private Integer pageNum;
    private Integer pageSize;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
}
