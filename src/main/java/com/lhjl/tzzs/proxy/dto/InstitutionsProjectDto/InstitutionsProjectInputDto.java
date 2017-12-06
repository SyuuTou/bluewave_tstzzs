package com.lhjl.tzzs.proxy.dto.InstitutionsProjectDto;

public class InstitutionsProjectInputDto {
    /**
     * 机构id
     */
    private Integer institutionId;

    /**
     * 轮次
     */
    private String stage;

    /**
     * 行业领域
     */
    private String fields;

    /**
     * 融资时间
     */
    private String financingTime;

    /**
     * 用户token
     */
    private String token;

    /**
     * 页码
     */
    private Integer startPage;

    /**
     * 每页显示数量
     */
    private Integer pageSize;

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getFinancingTime() {
        return financingTime;
    }

    public void setFinancingTime(String financingTime) {
        this.financingTime = financingTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
