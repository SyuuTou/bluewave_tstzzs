package com.lhjl.tzzs.proxy.dto;

/**
 * Created by zyy on 2017/11/20.
 */
public class ScreenDto {
    private String shortName; //输入机构的名称
    private Integer pageNum;  //页码数
    private Integer pageSize; //每页数量
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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
