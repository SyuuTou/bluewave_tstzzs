package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "investment_institution_team")
public class InvestmentInstitutionTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 高清图像
     */
    private String picture;

    /**
     * 机构团队类型元数据表id
     */
    @Column(name = "meta_iit_type_id")
    private Integer metaIitTypeId;

    /**
     * 真实姓名
     */
    @Column(name = "actual_name")
    private String actualName;

    /**
     * 担任职务
     */
    @Column(name = "company_duties")
    private String companyDuties;

    /**
     * 简介
     */
    @Column(name = "member_desc")
    private String memberDesc;

    /**
     * 所获荣誉
     */
    private String honor;

    /**
     * 手机号
     */
    private String phonenumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 是否在职，1表示在职，0表示不在职
     */
    @Column(name = "work_yn")
    private Integer workYn;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 是否隐藏，1表示隐藏，0表示不隐藏
     */
    @Column(name = "hide_yn")
    private Integer hideYn;

    /**
     * 机构id
     */
    @Column(name = "investment_institution_id")
    private Integer investmentInstitutionId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建者id
     */
    @Column(name = "create_id")
    private Integer createId;

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
     * 获取高清图像
     *
     * @return picture - 高清图像
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置高清图像
     *
     * @param picture 高清图像
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取机构团队类型元数据表id
     *
     * @return meta_iit_type_id - 机构团队类型元数据表id
     */
    public Integer getMetaIitTypeId() {
        return metaIitTypeId;
    }

    /**
     * 设置机构团队类型元数据表id
     *
     * @param metaIitTypeId 机构团队类型元数据表id
     */
    public void setMetaIitTypeId(Integer metaIitTypeId) {
        this.metaIitTypeId = metaIitTypeId;
    }

    /**
     * 获取真实姓名
     *
     * @return actual_name - 真实姓名
     */
    public String getActualName() {
        return actualName;
    }

    /**
     * 设置真实姓名
     *
     * @param actualName 真实姓名
     */
    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    /**
     * 获取担任职务
     *
     * @return company_duties - 担任职务
     */
    public String getCompanyDuties() {
        return companyDuties;
    }

    /**
     * 设置担任职务
     *
     * @param companyDuties 担任职务
     */
    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
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
     * 获取所获荣誉
     *
     * @return honor - 所获荣誉
     */
    public String getHonor() {
        return honor;
    }

    /**
     * 设置所获荣誉
     *
     * @param honor 所获荣誉
     */
    public void setHonor(String honor) {
        this.honor = honor;
    }

    /**
     * 获取手机号
     *
     * @return phonenumber - 手机号
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * 设置手机号
     *
     * @param phonenumber 手机号
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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
     * @return wechat - 微信
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * 设置微信
     *
     * @param wechat 微信
     */
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    /**
     * 获取是否在职，1表示在职，0表示不在职
     *
     * @return work_yn - 是否在职，1表示在职，0表示不在职
     */
    public Integer getWorkYn() {
        return workYn;
    }

    /**
     * 设置是否在职，1表示在职，0表示不在职
     *
     * @param workYn 是否在职，1表示在职，0表示不在职
     */
    public void setWorkYn(Integer workYn) {
        this.workYn = workYn;
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
     * 获取是否隐藏，1表示隐藏，0表示不隐藏
     *
     * @return hide_yn - 是否隐藏，1表示隐藏，0表示不隐藏
     */
    public Integer getHideYn() {
        return hideYn;
    }

    /**
     * 设置是否隐藏，1表示隐藏，0表示不隐藏
     *
     * @param hideYn 是否隐藏，1表示隐藏，0表示不隐藏
     */
    public void setHideYn(Integer hideYn) {
        this.hideYn = hideYn;
    }

    /**
     * 获取机构id
     *
     * @return investment_institution_id - 机构id
     */
    public Integer getInvestmentInstitutionId() {
        return investmentInstitutionId;
    }

    /**
     * 设置机构id
     *
     * @param investmentInstitutionId 机构id
     */
    public void setInvestmentInstitutionId(Integer investmentInstitutionId) {
        this.investmentInstitutionId = investmentInstitutionId;
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
     * 获取创建者id
     *
     * @return create_id - 创建者id
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * 设置创建者id
     *
     * @param createId 创建者id
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }
}