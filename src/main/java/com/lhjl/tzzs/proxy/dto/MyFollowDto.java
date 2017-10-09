package com.lhjl.tzzs.proxy.dto;

/**
 * 我的关注列表页查询数据
 * Created by 蓝海巨浪 on 2017/10/9.
 */
public class MyFollowDto {
    private String userId;
    private Integer pageNum;
    private Integer pageSize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public void setPageSize(Integer pageSizel) {
        this.pageSize = pageSizel;
    }
}
