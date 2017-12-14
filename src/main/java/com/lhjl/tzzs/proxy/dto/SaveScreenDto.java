package com.lhjl.tzzs.proxy.dto;

/**
 * Created by lmy on 2017/11/21.
 */
public class SaveScreenDto {
    private String token;
    /**
     * 指数类型，50传1，非50传“”（空）
     */
    private String investmentType;
    private  String stage;
    private String domain;
    private  Integer pageNum;
    private  Integer pageSize;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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
