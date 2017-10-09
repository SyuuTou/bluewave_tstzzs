package com.lhjl.tzzs.proxy.dto;

/**
 * 通用分页查询信息
 * Created by 蓝海巨浪 on 2017/10/9.
 */
public class PageInfoDto {
    private Integer pageNum;
    private Integer pageSize;

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
