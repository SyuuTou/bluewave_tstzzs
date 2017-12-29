package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "project_send_financing_history_b")
public class ProjectSendFinancingHistoryB {
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
     * 融资金额单位，投资币种（0人民币/1美元）
     */
    private Integer currency;

    /**
     * 估值金额
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 估值金额单位，投资币种（0人民币/1美元）
     */
    @Column(name = "total_amount_currency")
    private Integer totalAmountCurrency;

    /**
     * 应用id
     */
    private String appid;

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
     * 获取融资金额单位，投资币种（0人民币/1美元）
     *
     * @return currency - 融资金额单位，投资币种（0人民币/1美元）
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * 设置融资金额单位，投资币种（0人民币/1美元）
     *
     * @param currency 融资金额单位，投资币种（0人民币/1美元）
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    /**
     * 获取估值金额
     *
     * @return total_amount - 估值金额
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置估值金额
     *
     * @param totalAmount 估值金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取估值金额单位，投资币种（0人民币/1美元）
     *
     * @return total_amount_currency - 估值金额单位，投资币种（0人民币/1美元）
     */
    public Integer getTotalAmountCurrency() {
        return totalAmountCurrency;
    }

    /**
     * 设置估值金额单位，投资币种（0人民币/1美元）
     *
     * @param totalAmountCurrency 估值金额单位，投资币种（0人民币/1美元）
     */
    public void setTotalAmountCurrency(Integer totalAmountCurrency) {
        this.totalAmountCurrency = totalAmountCurrency;
    }

    /**
     * 获取应用id
     *
     * @return appid - 应用id
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 设置应用id
     *
     * @param appid 应用id
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }
}