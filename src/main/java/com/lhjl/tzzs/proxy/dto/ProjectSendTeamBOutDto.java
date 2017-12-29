package com.lhjl.tzzs.proxy.dto;

public class ProjectSendTeamBOutDto {
    /**成员姓名*/
    private String memberName;

    /**职务*/
    private String companyDuties;

    /**团队成员id*/
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
