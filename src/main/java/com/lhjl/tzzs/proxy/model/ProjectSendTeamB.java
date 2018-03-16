package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "project_send_team_b")
public class ProjectSendTeamB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目id
     */
    @Column(name = "project_send_b_id")
    private Integer projectSendBId;

    /**
     * 成员姓名
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 职务
     */
    @Column(name = "company_duties")
    private String companyDuties;

    /**
     * 股份占比
     */
    @Column(name = "stock_ratio")
    private BigDecimal stockRatio;

    /**
     * 简介
     */
    @Column(name = "member_desc")
    private String memberDesc;

    /**
     * 应用id
     */
    private Integer appid;

    /**
     * 是否删除，0表示未被删，1表是已被删除
     */
    private Integer yn;

    /**
     * 团队成员权重-周栋
     */
    private Integer weight;

    /**
     * 是否在职，0表示离职，1表示在职-周栋
     */
    @Column(name = "job_status")
    private Integer jobStatus;

    /**
     * 手机号-周栋
     */
    private String phone;

    /**
     * 是否隐藏:0 显示 1 隐藏-周栋
     */
    @Column(name = "is_hide")
    private Integer isHide;

    /**
     * 头像（周栋）
     */
    @Column(name = "head_picture")
    private String headPicture;

    /**
     * 高清照片（周栋）
     */
    private String picture;

    /**
     * 邮箱（周栋）
     */
    private String email;

    /**
     * 微信
     */
    @Column(name = "weiChat")
    private String weichat;

    /**
     * 所属团队ID（周栋）
     */
    @Column(name = "team_id")
    private Integer teamId;

    /**
     * 出生日期（周栋）
     */
    @Column(name = "birth_day")
    private Date birthDay;

    /**
     * 任职时间（周栋）
     */
    @Column(name = "tenure_time")
    private Date tenureTime;

    /**
     * 性别:0男1女（周栋）
     */
    private Integer sex;

    /**
     * 学历ID(周栋)
     */
    private Integer diploma;

    /**
     * 国籍(周栋)
     */
    private Integer nationality;

    /**
     * 创业经历描述（周栋）
     */
    @Column(name = "business_experience_desc")
    private String businessExperienceDesc;

    /**
     * 工作经历描述（周栋）
     */
    @Column(name = "work_experience_desc")
    private String workExperienceDesc;

    /**
     * 教育经历描述（周栋）
     */
    @Column(name = "education_experience_desc")
    private String educationExperienceDesc;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取提交项目id
     *
     * @return project_send_b_id - 提交项目id
     */
    public Integer getProjectSendBId() {
        return projectSendBId;
    }

    /**
     * 设置提交项目id
     *
     * @param projectSendBId 提交项目id
     */
    public void setProjectSendBId(Integer projectSendBId) {
        this.projectSendBId = projectSendBId;
    }

    /**
     * 获取成员姓名
     *
     * @return member_name - 成员姓名
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 设置成员姓名
     *
     * @param memberName 成员姓名
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * 获取职务
     *
     * @return company_duties - 职务
     */
    public String getCompanyDuties() {
        return companyDuties;
    }

    /**
     * 设置职务
     *
     * @param companyDuties 职务
     */
    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    /**
     * 获取股份占比
     *
     * @return stock_ratio - 股份占比
     */
    public BigDecimal getStockRatio() {
        return stockRatio;
    }

    /**
     * 设置股份占比
     *
     * @param stockRatio 股份占比
     */
    public void setStockRatio(BigDecimal stockRatio) {
        this.stockRatio = stockRatio;
    }

    /**
     * 获取简介
     *
     * @return member_desc - 简介
     */
    public String getMemberDesc() {
        return memberDesc;
    }

    /**
     * 设置简介
     *
     * @param memberDesc 简介
     */
    public void setMemberDesc(String memberDesc) {
        this.memberDesc = memberDesc;
    }

    /**
     * 获取应用id
     *
     * @return appid - 应用id
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * 设置应用id
     *
     * @param appid 应用id
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    /**
     * 获取是否删除，0表示未被删，1表是已被删除
     *
     * @return yn - 是否删除，0表示未被删，1表是已被删除
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否删除，0表示未被删，1表是已被删除
     *
     * @param yn 是否删除，0表示未被删，1表是已被删除
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取团队成员权重-周栋
     *
     * @return weight - 团队成员权重-周栋
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置团队成员权重-周栋
     *
     * @param weight 团队成员权重-周栋
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获取是否在职，0表示离职，1表示在职-周栋
     *
     * @return job_status - 是否在职，0表示离职，1表示在职-周栋
     */
    public Integer getJobStatus() {
        return jobStatus;
    }

    /**
     * 设置是否在职，0表示离职，1表示在职-周栋
     *
     * @param jobStatus 是否在职，0表示离职，1表示在职-周栋
     */
    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    /**
     * 获取手机号-周栋
     *
     * @return phone - 手机号-周栋
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号-周栋
     *
     * @param phone 手机号-周栋
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取是否隐藏:0 显示 1 隐藏-周栋
     *
     * @return is_hide - 是否隐藏:0 显示 1 隐藏-周栋
     */
    public Integer getIsHide() {
        return isHide;
    }

    /**
     * 设置是否隐藏:0 显示 1 隐藏-周栋
     *
     * @param isHide 是否隐藏:0 显示 1 隐藏-周栋
     */
    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    /**
     * 获取头像（周栋）
     *
     * @return head_picture - 头像（周栋）
     */
    public String getHeadPicture() {
        return headPicture;
    }

    /**
     * 设置头像（周栋）
     *
     * @param headPicture 头像（周栋）
     */
    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    /**
     * 获取高清照片（周栋）
     *
     * @return picture - 高清照片（周栋）
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置高清照片（周栋）
     *
     * @param picture 高清照片（周栋）
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取邮箱（周栋）
     *
     * @return email - 邮箱（周栋）
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱（周栋）
     *
     * @param email 邮箱（周栋）
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取微信
     *
     * @return weiChat - 微信
     */
    public String getWeichat() {
        return weichat;
    }

    /**
     * 设置微信
     *
     * @param weichat 微信
     */
    public void setWeichat(String weichat) {
        this.weichat = weichat;
    }

    /**
     * 获取所属团队ID（周栋）
     *
     * @return team_id - 所属团队ID（周栋）
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * 设置所属团队ID（周栋）
     *
     * @param teamId 所属团队ID（周栋）
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * 获取出生日期（周栋）
     *
     * @return birth_day - 出生日期（周栋）
     */
    public Date getBirthDay() {
        return birthDay;
    }

    /**
     * 设置出生日期（周栋）
     *
     * @param birthDay 出生日期（周栋）
     */
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * 获取任职时间（周栋）
     *
     * @return tenure_time - 任职时间（周栋）
     */
    public Date getTenureTime() {
        return tenureTime;
    }

    /**
     * 设置任职时间（周栋）
     *
     * @param tenureTime 任职时间（周栋）
     */
    public void setTenureTime(Date tenureTime) {
        this.tenureTime = tenureTime;
    }

    /**
     * 获取性别:0男1女（周栋）
     *
     * @return sex - 性别:0男1女（周栋）
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别:0男1女（周栋）
     *
     * @param sex 性别:0男1女（周栋）
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取学历ID(周栋)
     *
     * @return diploma - 学历ID(周栋)
     */
    public Integer getDiploma() {
        return diploma;
    }

    /**
     * 设置学历ID(周栋)
     *
     * @param diploma 学历ID(周栋)
     */
    public void setDiploma(Integer diploma) {
        this.diploma = diploma;
    }

    /**
     * 获取国籍(周栋)
     *
     * @return nationality - 国籍(周栋)
     */
    public Integer getNationality() {
        return nationality;
    }

    /**
     * 设置国籍(周栋)
     *
     * @param nationality 国籍(周栋)
     */
    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    /**
     * 获取创业经历描述（周栋）
     *
     * @return business_experience_desc - 创业经历描述（周栋）
     */
    public String getBusinessExperienceDesc() {
        return businessExperienceDesc;
    }

    /**
     * 设置创业经历描述（周栋）
     *
     * @param businessExperienceDesc 创业经历描述（周栋）
     */
    public void setBusinessExperienceDesc(String businessExperienceDesc) {
        this.businessExperienceDesc = businessExperienceDesc;
    }

    /**
     * 获取工作经历描述（周栋）
     *
     * @return work_experience_desc - 工作经历描述（周栋）
     */
    public String getWorkExperienceDesc() {
        return workExperienceDesc;
    }

    /**
     * 设置工作经历描述（周栋）
     *
     * @param workExperienceDesc 工作经历描述（周栋）
     */
    public void setWorkExperienceDesc(String workExperienceDesc) {
        this.workExperienceDesc = workExperienceDesc;
    }

    /**
     * 获取教育经历描述（周栋）
     *
     * @return education_experience_desc - 教育经历描述（周栋）
     */
    public String getEducationExperienceDesc() {
        return educationExperienceDesc;
    }

    /**
     * 设置教育经历描述（周栋）
     *
     * @param educationExperienceDesc 教育经历描述（周栋）
     */
    public void setEducationExperienceDesc(String educationExperienceDesc) {
        this.educationExperienceDesc = educationExperienceDesc;
    }
}