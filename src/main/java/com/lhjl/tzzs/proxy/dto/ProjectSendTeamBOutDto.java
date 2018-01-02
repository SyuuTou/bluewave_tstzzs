package com.lhjl.tzzs.proxy.dto;

public class ProjectSendTeamBOutDto {
    /**成员姓名*/
    private String memberName;

    /**职务*/
    private String companyDuties;

    /**团队成员id*/
    private Integer id;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCompanyDuties() {
        return companyDuties;
    }

    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
