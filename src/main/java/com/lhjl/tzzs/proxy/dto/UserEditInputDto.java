package com.lhjl.tzzs.proxy.dto;

public class UserEditInputDto {
    /**
     * 用户token
     */
    private  String token;

    /**
     * 真实姓名
     */
    private String actualName;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 公司职位
     */
    private String companyDuties;
    /**
     * 手机号
     */
    private String phonenumber;
    /**
     * 身份类型
     */
    private String identityType;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDuties() {
        return companyDuties;
    }

    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }
}
