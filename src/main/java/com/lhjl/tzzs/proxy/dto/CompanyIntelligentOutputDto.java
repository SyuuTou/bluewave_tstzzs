package com.lhjl.tzzs.proxy.dto;

public class CompanyIntelligentOutputDto {
    /**
     * 简称
     */
    private String companyName;
    /**
     * 全称
     */
    private String companyFullName;
    /**
     * 主体id
     */
    private Integer companyId;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
