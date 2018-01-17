package com.lhjl.tzzs.proxy.dto.TeamManageDto;

/**
 * Created by lanhaijulang on 2018/1/17.
 */
public class TeamManageInputDto {

    /**成员ID*/
    private Integer memberId;

    /**成员照片*/
    private String picture;

    /**成员所属团队Id*/
    private Integer teamId;

    /**自定义团队名称*/
    private String selfDefTeam;

    /**团队成员名字*/
    private String name;

    /**t团队成员职务*/
    private String jobTitle;

    /**团队成员个人简介*/
    private String introduction;

    /**团队成员关注领域ID*/
    private Integer[] focusDomain;

    /**团队成员自定义领域名称*/
    private String[] selfDefDomain;

    /**团队成员关注投资阶段*/
    private Integer[] investStageId;

    /**投资案例*/
    private String[] investCase;

    /**工作经历*/
    private String[] workExperience;

    /**教育经历*/
    private String[] educationExperience;

    /**荣誉或社会资质*/
    private String honor;

    /**常驻城市*/
    private Integer[] residentCity;

    /**自定义常驻城市*/
    private String[] selfDefResidentCity;

    /**手机*/
    private String phone;

    /**邮箱*/
    private String email;

    /**微信*/
    private String weichat;

    /**是否在职*/
    private Integer incumbency;

    /**权重*/
    private Integer weight;

    /**是否隐藏*/
    private Integer isHide;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer[] getFocusDomain() {
        return focusDomain;
    }

    public void setFocusDomain(Integer[] focusDomain) {
        this.focusDomain = focusDomain;
    }

    public String[] getSelfDefDomain() {
        return selfDefDomain;
    }

    public void setSelfDefDomain(String[] selfDefDomain) {
        this.selfDefDomain = selfDefDomain;
    }

    public Integer[] getInvestStageId() {
        return investStageId;
    }

    public void setInvestStageId(Integer[] investStageId) {
        this.investStageId = investStageId;
    }

    public String[] getInvestCase() {
        return investCase;
    }

    public void setInvestCase(String[] investCase) {
        this.investCase = investCase;
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

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public Integer[] getResidentCity() {
        return residentCity;
    }

    public void setResidentCity(Integer[] residentCity) {
        this.residentCity = residentCity;
    }

    public String[] getSelfDefResidentCity() {
        return selfDefResidentCity;
    }

    public void setSelfDefResidentCity(String[] selfDefResidentCity) {
        this.selfDefResidentCity = selfDefResidentCity;
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

    public String getWeichat() {
        return weichat;
    }

    public void setWeichat(String weichat) {
        this.weichat = weichat;
    }

    public Integer getIncumbency() {
        return incumbency;
    }

    public void setIncumbency(Integer incumbency) {
        this.incumbency = incumbency;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }
}
