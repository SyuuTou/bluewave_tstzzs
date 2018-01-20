package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "project_team_member")
public class ProjectTeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 成员姓名
     */
    @Column(name = "mumber_name")
    private String mumberName;

    /**
     * 成员职务
     */
    @Column(name = "mumber_duties")
    private String mumberDuties;

    /**
     * 股份占比
     */
    @Column(name = "share_ratio")
    private BigDecimal shareRatio;

    /**
     * 成员简介
     */
    @Column(name = "mumber_desc")
    private String mumberDesc;

    /**
     * 删除状态，0表示未删除，1表示已删除
     */
    private Integer yn;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 是否在职,0:离职;1:在职
     */
    @Column(name = "isOnJob")
    private Integer isonjob;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 头像
     */
    @Column(name = "head_picture")
    private String headPicture;

    /**
     * 高清照片
     */
    private String picture;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信
     */
    @Column(name = "weiChat")
    private String weichat;

    /**
     * 是否隐藏，0:隐藏;1:不隐藏
     */
    @Column(name = "isHide")
    private Integer ishide;

    /**
     * 创业经历描述
     */
    @Column(name = "business_experience_desc")
    private String businessExperienceDesc;

    /**
     * 工作经历描述
     */
    @Column(name = "work_experience_desc")
    private String workExperienceDesc;

    /**
     * 教育经历描述
     */
    @Column(name = "education_experience_desc")
    private String educationExperienceDesc;

    /**
     * 所属团队ID
     */
    @Column(name = "team_id")
    private Integer teamId;

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
     * @return project_id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取成员姓名
     *
     * @return mumber_name - 成员姓名
     */
    public String getMumberName() {
        return mumberName;
    }

    /**
     * 设置成员姓名
     *
     * @param mumberName 成员姓名
     */
    public void setMumberName(String mumberName) {
        this.mumberName = mumberName;
    }

    /**
     * 获取成员职务
     *
     * @return mumber_duties - 成员职务
     */
    public String getMumberDuties() {
        return mumberDuties;
    }

    /**
     * 设置成员职务
     *
     * @param mumberDuties 成员职务
     */
    public void setMumberDuties(String mumberDuties) {
        this.mumberDuties = mumberDuties;
    }

    /**
     * 获取股份占比
     *
     * @return share_ratio - 股份占比
     */
    public BigDecimal getShareRatio() {
        return shareRatio;
    }

    /**
     * 设置股份占比
     *
     * @param shareRatio 股份占比
     */
    public void setShareRatio(BigDecimal shareRatio) {
        this.shareRatio = shareRatio;
    }

    /**
     * 获取成员简介
     *
     * @return mumber_desc - 成员简介
     */
    public String getMumberDesc() {
        return mumberDesc;
    }

    /**
     * 设置成员简介
     *
     * @param mumberDesc 成员简介
     */
    public void setMumberDesc(String mumberDesc) {
        this.mumberDesc = mumberDesc;
    }

    /**
     * 获取删除状态，0表示未删除，1表示已删除
     *
     * @return yn - 删除状态，0表示未删除，1表示已删除
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置删除状态，0表示未删除，1表示已删除
     *
     * @param yn 删除状态，0表示未删除，1表示已删除
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取是否在职,0:离职;1:在职
     *
     * @return isOnJob - 是否在职,0:离职;1:在职
     */
    public Integer getIsonjob() {
        return isonjob;
    }

    /**
     * 设置是否在职,0:离职;1:在职
     *
     * @param isonjob 是否在职,0:离职;1:在职
     */
    public void setIsonjob(Integer isonjob) {
        this.isonjob = isonjob;
    }

    /**
     * 获取权重
     *
     * @return weight - 权重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置权重
     *
     * @param weight 权重
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获取头像
     *
     * @return head_picture - 头像
     */
    public String getHeadPicture() {
        return headPicture;
    }

    /**
     * 设置头像
     *
     * @param headPicture 头像
     */
    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    /**
     * 获取高清照片
     *
     * @return picture - 高清照片
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置高清照片
     *
     * @param picture 高清照片
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
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
     * 获取是否隐藏，0:隐藏;1:不隐藏
     *
     * @return isHide - 是否隐藏，0:隐藏;1:不隐藏
     */
    public Integer getIshide() {
        return ishide;
    }

    /**
     * 设置是否隐藏，0:隐藏;1:不隐藏
     *
     * @param ishide 是否隐藏，0:隐藏;1:不隐藏
     */
    public void setIshide(Integer ishide) {
        this.ishide = ishide;
    }

    /**
     * 获取创业经历描述
     *
     * @return business_experience_desc - 创业经历描述
     */
    public String getBusinessExperienceDesc() {
        return businessExperienceDesc;
    }

    /**
     * 设置创业经历描述
     *
     * @param businessExperienceDesc 创业经历描述
     */
    public void setBusinessExperienceDesc(String businessExperienceDesc) {
        this.businessExperienceDesc = businessExperienceDesc;
    }

    /**
     * 获取工作经历描述
     *
     * @return work_experience_desc - 工作经历描述
     */
    public String getWorkExperienceDesc() {
        return workExperienceDesc;
    }

    /**
     * 设置工作经历描述
     *
     * @param workExperienceDesc 工作经历描述
     */
    public void setWorkExperienceDesc(String workExperienceDesc) {
        this.workExperienceDesc = workExperienceDesc;
    }

    /**
     * 获取教育经历描述
     *
     * @return education_experience_desc - 教育经历描述
     */
    public String getEducationExperienceDesc() {
        return educationExperienceDesc;
    }

    /**
     * 设置教育经历描述
     *
     * @param educationExperienceDesc 教育经历描述
     */
    public void setEducationExperienceDesc(String educationExperienceDesc) {
        this.educationExperienceDesc = educationExperienceDesc;
    }

    /**
     * 获取所属团队ID
     *
     * @return team_id - 所属团队ID
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * 设置所属团队ID
     *
     * @param teamId 所属团队ID
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}