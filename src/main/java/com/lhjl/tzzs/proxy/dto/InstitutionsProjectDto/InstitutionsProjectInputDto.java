package com.lhjl.tzzs.proxy.dto.InstitutionsProjectDto;

public class InstitutionsProjectInputDto {
    /**
     * 机构id
     */
    private Integer institutionId;

    /**
     * 搜索内容
     */
    private String searchWords;

    /**
     * 行业领域
     */
    private String fields;

    /**
     * 融资时间
     */
    private String financingTime;

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public String getSearchWords() {
        return searchWords;
    }

    public void setSearchWords(String searchWords) {
        this.searchWords = searchWords;
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
}
