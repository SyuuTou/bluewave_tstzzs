package com.lhjl.tzzs.proxy.dto;

import java.util.List;

public class InvestorDemandListInputDto {

    /**页码*/
    private Integer pageNum;

    /**每页显示数量*/
    private Integer pageSize;

    /**状态*/
    private List<Integer> status;

    /** 用户Token */
    private String token;
    /**是否是后台请求*/
    private Integer isAdmin;

    /** 数据类型，Featured：精选，Latest：最新，Mine：我的 */
    private String dataType;

    public String getDataType() {
        return dataType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public List<Integer> getStatus() {
        return status;
    }

    public void setStatus(List<Integer> status) {
        this.status = status;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }
}
