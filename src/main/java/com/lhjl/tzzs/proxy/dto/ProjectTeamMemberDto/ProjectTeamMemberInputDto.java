package com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto;

import java.math.BigDecimal;

/**
 * Created by lanhaijulang on 2018/1/18.
 */
public class ProjectTeamMemberInputDto {
    /**
     * 成员ID
     */
    private Integer memberId;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 姓名
     */
    private String name;

    /**
     * 职务
     */
    private String jobTitle;

    /**
     * 成员介绍
     */
    private String memberDesc;

    /**
     * 工作经历
     */
    private String[] workExperiences;

    /**
     * 教育经历
     */
    private String[] educationExperience;

    /**
     * 是否在职
     */
    private Integer isOnJob;

    /**
     * 头像
     */
    private String headPicture;

    /**
     * 高清照片
     */
    private String picture;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信
     */
    private String weiChat;

    /**
     * 所属团队ID
     */
    private Integer teamId;

    /**
     * 自定义团队
     */
    private String selfDefTeam;

    /**
     * 关注领域
     */
    private Integer[] focusDomain;

    /**
     * 投资阶段
     */
    private Integer[] investStages;

    /**
     * 股票
     */
    private BigDecimal stockPer;

    /**
     * 所在城市
     */
    private String[] citys;

    /**
     * 自定义城市
     */
    private String[] selfcitys;

    /**
     * 创业经历
     */
    private String[] businesses;

    /**
     * 创业经历描述
     */
    private String businessDesc;

    /**
     * 工作经历描述
     */
    private String workDesc;

    /**
     * 教育经历描述
     */
    private String educationDesc;

    /**
     * 是否隐藏，默认不隐藏，不隐藏为1
     */
    private Integer isHide;

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeiChat() {
        return weiChat;
    }

    public void setWeiChat(String weiChat) {
        this.weiChat = weiChat;
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

    public Integer[] getFocusDomain() {
        return focusDomain;
    }

    public void setFocusDomain(Integer[] focusDomain) {
        this.focusDomain = focusDomain;
    }

    public Integer[] getInvestStages() {
        return investStages;
    }

    public void setInvestStages(Integer[] investStages) {
        this.investStages = investStages;
    }

    public BigDecimal getStockPer() {
        return stockPer;
    }

    public void setStockPer(BigDecimal stockPer) {
        this.stockPer = stockPer;
    }

    public String[] getCitys() {
        return citys;
    }

    public void setCitys(String[] citys) {
        this.citys = citys;
    }

    public String[] getSelfcitys() {
        return selfcitys;
    }

    public void setSelfcitys(String[] selfcitys) {
        this.selfcitys = selfcitys;
    }

    public String[] getBusinesses() {
        return businesses;
    }

    public void setBusinesses(String[] businesses) {
        this.businesses = businesses;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public String getEducationDesc() {
        return educationDesc;
    }

    public void setEducationDesc(String educationDesc) {
        this.educationDesc = educationDesc;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    public String getMemberDesc() {
        return memberDesc;
    }

    public void setMemberDesc(String memberDesc) {
        this.memberDesc = memberDesc;
    }

    public String[] getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(String[] workExperiences) {
        this.workExperiences = workExperiences;
    }

    public String[] getEducationExperience() {
        return educationExperience;
    }

    public void setEducationExperience(String[] educationExperience) {
        this.educationExperience = educationExperience;
    }

    public Integer getIsOnJob() {
        return isOnJob;
    }

    public void setIsOnJob(Integer isOnJob) {
        this.isOnJob = isOnJob;
    }

}
