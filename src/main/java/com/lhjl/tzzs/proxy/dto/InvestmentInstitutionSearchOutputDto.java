package com.lhjl.tzzs.proxy.dto;

public class InvestmentInstitutionSearchOutputDto {
    /**
     * 机构简称
     */
    private String institutionShortName;
    /**
     * 机构全称
     */
    private String institutionFullName;
    /**
     * 机构id
     */
    private Integer institutionId;

    public String getInstitutionShortName() {
        return institutionShortName;
    }

    public void setInstitutionShortName(String institutionShortName) {
        this.institutionShortName = institutionShortName;
    }

    public String getInstitutionFullName() {
        return institutionFullName;
    }

    public void setInstitutionFullName(String institutionFullName) {
        this.institutionFullName = institutionFullName;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }
}
