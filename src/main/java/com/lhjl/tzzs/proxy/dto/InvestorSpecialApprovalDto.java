package com.lhjl.tzzs.proxy.dto;

public class InvestorSpecialApprovalDto {
    /**申请记录id*/
    private Integer id;

    /**用户id*/
    private Integer userId;

    /**领投人资格*/
    private Integer leader;

    /**审核状态*/
    private Integer investorType;

    /**用户等级*/
    private Integer userLevel;

    /**补充说明(200字符)*/
    private String supplementaryExplanation;

    /**用户名称*/
    private String userName;

    /**公司名称*/
    private String companyName;

    /**公司职位*/
    private String comanyDuties;

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

    public Integer getLeader() {
        return leader;
    }

    public void setLeader(Integer leader) {
        this.leader = leader;
    }

    public Integer getInvestorType() {
        return investorType;
    }

    public void setInvestorType(Integer investorType) {
        this.investorType = investorType;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getComanyDuties() {
        return comanyDuties;
    }

    public void setComanyDuties(String comanyDuties) {
        this.comanyDuties = comanyDuties;
    }

    public String getSupplementaryExplanation() {
        return supplementaryExplanation;
    }

    public void setSupplementaryExplanation(String supplementaryExplanation) {
        this.supplementaryExplanation = supplementaryExplanation;
    }
}
