package com.lhjl.tzzs.proxy.dto;

public class InvestorDemandListInputDto {

    /**页码*/
    private Integer pageNum;

    /**每页显示数量*/
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
