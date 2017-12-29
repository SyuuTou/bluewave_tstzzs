package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "project_send_financing_approval_b")
public class ProjectSendFinancingApprovalB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目id
     */
    @Column(name = "project_send_b_id")
    private Integer projectSendBId;

    /**
     * 轮次
     */
    private String stage;

    /**
     * 融资金额
     */
    private BigDecimal amount;

    /**
     * 融资金额单位（0人民币/1美元）
     */
    private Integer currency;

    /**
     * 出让股份
     */
    @Column(name = "share_divest")
    private String shareDivest;

    /**
     * 融资用途
     */
    @Column(name = "project_financing_useful")
    private String projectFinancingUseful;

    /**
     * 申请人id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 应用id
     */
    private Integer appid;

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
     * 获取轮次
     *
     * @return stage - 轮次
     */
    public String getStage() {
        return stage;
    }

    /**
     * 设置轮次
     *
     * @param stage 轮次
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * 获取融资金额
     *
     * @return amount - 融资金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置融资金额
     *
     * @param amount 融资金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取融资金额单位（0人民币/1美元）
     *
     * @return currency - 融资金额单位（0人民币/1美元）
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * 设置融资金额单位（0人民币/1美元）
     *
     * @param currency 融资金额单位（0人民币/1美元）
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    /**
     * 获取出让股份
     *
     * @return share_divest - 出让股份
     */
    public String getShareDivest() {
        return shareDivest;
    }

    /**
     * 设置出让股份
     *
     * @param shareDivest 出让股份
     */
    public void setShareDivest(String shareDivest) {
        this.shareDivest = shareDivest;
    }

    /**
     * 获取融资用途
     *
     * @return project_financing_useful - 融资用途
     */
    public String getProjectFinancingUseful() {
        return projectFinancingUseful;
    }

    /**
     * 设置融资用途
     *
     * @param projectFinancingUseful 融资用途
     */
    public void setProjectFinancingUseful(String projectFinancingUseful) {
        this.projectFinancingUseful = projectFinancingUseful;
    }

    /**
     * 获取申请人id
     *
     * @return user_id - 申请人id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置申请人id
     *
     * @param userId 申请人id
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
}