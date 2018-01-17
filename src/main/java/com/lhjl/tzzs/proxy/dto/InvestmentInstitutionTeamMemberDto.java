package com.lhjl.tzzs.proxy.dto;

public class InvestmentInstitutionTeamMemberDto {

    /**成员id*/
    private Integer id;
    /**高清图片*/
    private String picture;

    /**真实姓名*/
    private String actualName;

    /**担任职务*/
    private String companyDuties;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public String getCompanyDuties() {
        return companyDuties;
    }

    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }
}
