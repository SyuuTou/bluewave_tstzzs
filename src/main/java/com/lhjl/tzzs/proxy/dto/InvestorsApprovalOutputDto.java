package com.lhjl.tzzs.proxy.dto;

import java.util.List;

public class InvestorsApprovalOutputDto {

    /**申请记录id*/
    private Integer id;

    /**用户id*/
    private Integer userId;

    /**用户名*/
    private String userName;

    /**所在公司*/
    private String company;

    /**担任职务*/
    private String companyDuties;

    /**手机号码*/
    private String phoneNum;

    /**认证类型*/
    private String investorType;

    /**认证说明*/
    private String investorDescription;

    /**工作名片*/
    private String workCard;

    /**投资案例*/
    private String investCase;

    /**认证时间*/
    private String approvalTime;

    /**审核状态*/
    private String aduitStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyDuties() {
        return companyDuties;
    }

    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getInvestorType() {
        return investorType;
    }

    public void setInvestorType(String investorType) {
        this.investorType = investorType;
    }

    public String getInvestorDescription() {
        return investorDescription;
    }

    public void setInvestorDescription(String investorDescription) {
        this.investorDescription = investorDescription;
    }

    public String getWorkCard() {
        return workCard;
    }

    public void setWorkCard(String workCard) {
        this.workCard = workCard;
    }

    public String getInvestCase() {
        return investCase;
    }

    public void setInvestCase(String investCase) {
        this.investCase = investCase;
    }

    public String getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(String aduitStatus) {
        this.aduitStatus = aduitStatus;
    }
}
