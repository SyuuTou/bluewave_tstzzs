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

    /**
     * 源数据id
     */
    private Integer sourceId;

    /**
     * 源数据类型,1表示项目,2表示机构
     */
    private Integer sourceType;

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

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }
}
