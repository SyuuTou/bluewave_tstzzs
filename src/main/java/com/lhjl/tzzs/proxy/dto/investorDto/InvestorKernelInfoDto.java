package com.lhjl.tzzs.proxy.dto.investorDto;

import com.lhjl.tzzs.proxy.model.MetaInvestmentInstitutionTeamType;

/**
 * Created by lanhaijulang on 2018/1/25.
 */
public class InvestorKernelInfoDto {

    /**
     * 投资人Id
     */
    private Integer investorId;

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 头像
     */
    private String headPicture;

    /**
     * 投资人姓名
     */
    private String name;

    /**
     * 所在公司名称
     */
    private String companyName;

    /**
     * 担任公司职务
     */
    private String companyDuties;

    /**
     * 团队Id
     */
    private Integer teamId;

    /**
     * 自定义团队
     */
    private String selfDefTeam;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 一句话核心描述
     */
    private String kernelDesc;

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getSelfDefTeam() {
        return selfDefTeam;
    }

    public void setSelfDefTeam(String selfDefTeam) {
        this.selfDefTeam = selfDefTeam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKernelDesc() {
        return kernelDesc;
    }

    public void setKernelDesc(String kernelDesc) {
        this.kernelDesc = kernelDesc;
    }

    public Integer getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Integer investorId) {
        this.investorId = investorId;
    }

}
