package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "investment_institution_admin")
public class InvestmentInstitutionAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 机构id
     */
    @Column(name = "investment_institution_id")
    private Integer investmentInstitutionId;

    /**
     * 身份
     */
    @Column(name = "identity_type")
    private String identityType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否删除，1表示有效，0表示无效
     */
    private Integer yn;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * 获取身份
     *
     * @return identity_type - 身份
     */
    public String getIdentityType() {
        return identityType;
    }

    /**
     * 设置身份
     *
     * @param identityType 身份
     */
    public void setIdentityType(String identityType) {
        this.identityType = identityType;
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
     * 获取是否删除，1表示有效，0表示无效
     *
     * @return yn - 是否删除，1表示有效，0表示无效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否删除，1表示有效，0表示无效
     *
     * @param yn 是否删除，1表示有效，0表示无效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}