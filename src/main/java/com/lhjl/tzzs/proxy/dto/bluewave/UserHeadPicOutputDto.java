package com.lhjl.tzzs.proxy.dto.bluewave;

public class UserHeadPicOutputDto {
    /**
     * 头像
     */
    private String headpic;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 投资人类型
     */
    private String investorType;
    /**
     * 身份类型
     */
    private String identityType;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 职位
     */
    private String companyDuties;

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInvestorType() {
        return investorType;
    }

    public void setInvestorType(String investorType) {
        this.investorType = investorType;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
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
}
