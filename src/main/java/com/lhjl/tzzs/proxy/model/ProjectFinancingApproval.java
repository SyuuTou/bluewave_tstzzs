package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "project_financing_approval")
public class ProjectFinancingApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目记录id
     */
    @Column(name = "project_send_log_id")
    private Integer projectSendLogId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 融资轮次
     */
    @Column(name = "financing_rounds")
    private String financingRounds;

    /**
     * 融资金额
     */
    @Column(name = "financing_amount")
    private BigDecimal financingAmount;

    /**
     * 融资金额币种，0代表人民币，1代表美元
     */
    @Column(name = "financing_currency")
    private Integer financingCurrency;

    /**
     * 出让股份
     */
    @Column(name = "transfer_shares")
    private BigDecimal transferShares;

    /**
     * 当前需求
     */
    @Column(name = "current_demand")
    private String currentDemand;

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
     * 获取提交项目记录id
     *
     * @return project_send_log_id - 提交项目记录id
     */
    public Integer getProjectSendLogId() {
        return projectSendLogId;
    }

    /**
     * 设置提交项目记录id
     *
     * @param projectSendLogId 提交项目记录id
     */
    public void setProjectSendLogId(Integer projectSendLogId) {
        this.projectSendLogId = projectSendLogId;
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
     * 获取融资轮次
     *
     * @return financing_rounds - 融资轮次
     */
    public String getFinancingRounds() {
        return financingRounds;
    }

    /**
     * 设置融资轮次
     *
     * @param financingRounds 融资轮次
     */
    public void setFinancingRounds(String financingRounds) {
        this.financingRounds = financingRounds;
    }

    /**
     * 获取融资金额
     *
     * @return financing_amount - 融资金额
     */
    public BigDecimal getFinancingAmount() {
        return financingAmount;
    }

    /**
     * 设置融资金额
     *
     * @param financingAmount 融资金额
     */
    public void setFinancingAmount(BigDecimal financingAmount) {
        this.financingAmount = financingAmount;
    }

    /**
     * 获取融资金额币种，0代表人民币，1代表美元
     *
     * @return financing_currency - 融资金额币种，0代表人民币，1代表美元
     */
    public Integer getFinancingCurrency() {
        return financingCurrency;
    }

    /**
     * 设置融资金额币种，0代表人民币，1代表美元
     *
     * @param financingCurrency 融资金额币种，0代表人民币，1代表美元
     */
    public void setFinancingCurrency(Integer financingCurrency) {
        this.financingCurrency = financingCurrency;
    }

    /**
     * 获取出让股份
     *
     * @return transfer_shares - 出让股份
     */
    public BigDecimal getTransferShares() {
        return transferShares;
    }

    /**
     * 设置出让股份
     *
     * @param transferShares 出让股份
     */
    public void setTransferShares(BigDecimal transferShares) {
        this.transferShares = transferShares;
    }

    /**
     * 获取当前需求
     *
     * @return current_demand - 当前需求
     */
    public String getCurrentDemand() {
        return currentDemand;
    }

    /**
     * 设置当前需求
     *
     * @param currentDemand 当前需求
     */
    public void setCurrentDemand(String currentDemand) {
        this.currentDemand = currentDemand;
    }
}