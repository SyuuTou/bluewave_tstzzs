package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

public class Investors {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 投资机构ID
     */
    @Column(name = "investment_institutions_id")
    private Integer investmentInstitutionsId;

    /**
     * 用户表ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否有效；1: 有效，0:无效
     */
    private Integer yn;

    /**
     * 岗位
     */
    private String position;

    /**
     * 审核状态，0表示审核未通过，1表示审核通过，默认0
     */
    @Column(name = "approval_status")
    private Integer approvalStatus;

    /**
     * 审核时间，审核时存，其他时候为空
     */
    @Column(name = "approval_time")
    private Date approvalTime;

    /**
     * 认证类型，0代表个人投资，1代表机构投资，3代表vip投资人；
     */
    @Column(name = "investors_type")
    private Integer investorsType;

    /**
     * @return ID
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
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取投资机构ID
     *
     * @return investment_institutions_id - 投资机构ID
     */
    public Integer getInvestmentInstitutionsId() {
        return investmentInstitutionsId;
    }

    /**
     * 设置投资机构ID
     *
     * @param investmentInstitutionsId 投资机构ID
     */
    public void setInvestmentInstitutionsId(Integer investmentInstitutionsId) {
        this.investmentInstitutionsId = investmentInstitutionsId;
    }

    /**
     * 获取用户表ID
     *
     * @return user_id - 用户表ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户表ID
     *
     * @param userId 用户表ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * 获取是否有效；1: 有效，0:无效
     *
     * @return yn - 是否有效；1: 有效，0:无效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效；1: 有效，0:无效
     *
     * @param yn 是否有效；1: 有效，0:无效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取岗位
     *
     * @return position - 岗位
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置岗位
     *
     * @param position 岗位
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 获取审核状态，0表示审核未通过，1表示审核通过，默认0
     *
     * @return approval_status - 审核状态，0表示审核未通过，1表示审核通过，默认0
     */
    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * 设置审核状态，0表示审核未通过，1表示审核通过，默认0
     *
     * @param approvalStatus 审核状态，0表示审核未通过，1表示审核通过，默认0
     */
    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    /**
     * 获取审核时间，审核时存，其他时候为空
     *
     * @return approval_time - 审核时间，审核时存，其他时候为空
     */
    public Date getApprovalTime() {
        return approvalTime;
    }

    /**
     * 设置审核时间，审核时存，其他时候为空
     *
     * @param approvalTime 审核时间，审核时存，其他时候为空
     */
    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    /**
     * 获取认证类型，0代表个人投资，1代表机构投资，3代表vip投资人；
     *
     * @return investors_type - 认证类型，0代表个人投资，1代表机构投资，3代表vip投资人；
     */
    public Integer getInvestorsType() {
        return investorsType;
    }

    /**
     * 设置认证类型，0代表个人投资，1代表机构投资，3代表vip投资人；
     *
     * @param investorsType 认证类型，0代表个人投资，1代表机构投资，3代表vip投资人；
     */
    public void setInvestorsType(Integer investorsType) {
        this.investorsType = investorsType;
    }
}