package com.lhjl.tzzs.proxy.dto;

public class AdminUserListOutputDto {
    /**用户id*/
    private Integer id;

    /**姓名/昵称*/
    private String name;

    /**公司名称*/
    private String companyName;

    /**公司职位*/
    private String companyDuties;

    /**手机号*/
    private String phonenumber;

    /**身份类型*/
    private String identityType;

    /**认证类型*/
    private String investorType;

    /**会员等级*/
    private String userLevelType;

    /**会员截止时间*/
    private String userLevelEndTime;

    /**注册时间*/
    private String registerTime;

    /**更新时间*/
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getInvestorType() {
        return investorType;
    }

    public void setInvestorType(String investorType) {
        this.investorType = investorType;
    }

    public String getUserLevelType() {
        return userLevelType;
    }

    public void setUserLevelType(String userLevelType) {
        this.userLevelType = userLevelType;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserLevelEndTime() {
        return userLevelEndTime;
    }

    public void setUserLevelEndTime(String userLevelEndTime) {
        this.userLevelEndTime = userLevelEndTime;
    }
}
