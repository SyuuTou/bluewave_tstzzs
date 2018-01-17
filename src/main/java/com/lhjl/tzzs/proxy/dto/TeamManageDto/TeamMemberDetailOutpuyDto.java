package com.lhjl.tzzs.proxy.dto.TeamManageDto;

import java.math.BigDecimal;

/**
 * Created by lanhaijulang on 2018/1/17.
 */
public class TeamMemberDetailOutpuyDto {
    /**
     * 成员ID
     * */
    private Integer memberId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 职务
     */
    private String jobTitle;

    /**
     * 机构简称
     */
    private String institutionName;

    /**
     * 机构地址
     */
    private String institutionAddress;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 照片
     */
    private String picture;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 所属团队ID
     */
    private Integer teamId;

    /**
     * 个人简介
     */
    private String introduction;

    /**
     * 机构简介
     */
    private String institutionIntro;

    /**
     * 关注领域
     */
    private String[] focusDomains;

    /**
     * 关注投资阶段
     */
    private String[] focusInvestStage;


    /**
     * 投资案例
     */
    private String investmentCase;

    /**
     * 投资阶段
     */
    private String investmentStage;

    /**
     * 投资偏好领域
     */
    private String investmentPerferDomain;

    /**
     * 工作经历
     */
    private String[] workExperience;

    /**
     * 工作经历
     */
    private String[] educationExperience;

    public String getInvestmentStage() {
        return investmentStage;
    }

    public void setInvestmentStage(String investmentStage) {
        this.investmentStage = investmentStage;
    }

    public String getInvestmentPerferDomain() {
        return investmentPerferDomain;
    }

    public void setInvestmentPerferDomain(String investmentPerferDomain) {
        this.investmentPerferDomain = investmentPerferDomain;
    }

    public String getInvestmentCase() {
        return investmentCase;
    }

    public void setInvestmentCase(String investmentCase) {
        this.investmentCase = investmentCase;
    }

    public String getInstitutionAddress() {
        return institutionAddress;
    }

    public void setInstitutionAddress(String institutionAddress) {
        this.institutionAddress = institutionAddress;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getInstitutionIntro() {
        return institutionIntro;
    }

    public void setInstitutionIntro(String institutionIntro) {
        this.institutionIntro = institutionIntro;
    }

    public String[] getFocusDomains() {
        return focusDomains;
    }

    public void setFocusDomains(String[] focusDomains) {
        this.focusDomains = focusDomains;
    }

    public String[] getFocusInvestStage() {
        return focusInvestStage;
    }

    public void setFocusInvestStage(String[] focusInvestStage) {
        this.focusInvestStage = focusInvestStage;
    }

    public String[] getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String[] workExperience) {
        this.workExperience = workExperience;
    }

    public String[] getEducationExperience() {
        return educationExperience;
    }

    public void setEducationExperience(String[] educationExperience) {
        this.educationExperience = educationExperience;
    }
}
