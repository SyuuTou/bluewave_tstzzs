package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "project_financing_log")
public class ProjectFinancingLog {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目ID
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 投／融资时间
     */
    @Column(name = "financing_time")
    private Date financingTime;

    /**
     * 轮次
     */
    private String stage;

    /**
     * 投资金额（单位：万）
     */
    private BigDecimal amount;

    /**
     * 投资币种（人民币/美元）
     */
    private Integer currency;

    /**
     * 股权占比
     */
    @Column(name = "stock_right")
    private BigDecimal stockRight;

    /**
     * PR总投资金额（单位：万）
     */
    @Column(name = "pr_amount")
    private BigDecimal prAmount;

    /**
     * 本轮总投资金额（单位：万）
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 本轮总出让股份
     */
    @Column(name = "share_divest")
    private String shareDivest;

    /**
     * 投后估值（单位：万）
     */
    private BigDecimal valuation;

    /**
     * 相关投资机构简称（本轮投资机构）
     */
    @Column(name = "Investment_institutions_list")
    private String investmentInstitutionsList;

    /**
     * 相关投资机构说明（金额、占比）
     */
    @Column(name = "proportion_list")
    private String proportionList;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 状态，0，未审核，1，已审核（进行中），2，已完成，3，作废
     */
    private Integer status;

    /**
     * 审核状态
     */
    @Column(name = "approval_status")
    private Integer approvalStatus;

    /**
     * 审核时间
     */
    @Column(name = "approval_time")
    private Date approvalTime;
    @Column(name = "calculation_amount_status")
    private Integer calculationAmountStatus;

    private Integer totalAmountStatus;

    private Integer amountStatus;

    public Integer getTotalAmountStatus() {
        return totalAmountStatus;
    }

    public void setTotalAmountStatus(Integer totalAmountStatus) {
        this.totalAmountStatus = totalAmountStatus;
    }

    public Integer getAmountStatus() {
        return amountStatus;
    }

    public void setAmountStatus(Integer amountStatus) {
        this.amountStatus = amountStatus;
    }

    public Integer getCalculationAmountStatus() {
        return calculationAmountStatus;
    }

    public void setCalculationAmountStatus(Integer calculationAmountStatus) {
        this.calculationAmountStatus = calculationAmountStatus;
    }

    //
    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

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
     * 获取项目ID
     *
     * @return project_id - 项目ID
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置项目ID
     *
     * @param projectId 项目ID
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取投／融资时间
     *
     * @return financing_time - 投／融资时间
     */
    public Date getFinancingTime() {
        return financingTime;
    }

    /**
     * 设置投／融资时间
     *
     * @param financingTime 投／融资时间
     */
    public void setFinancingTime(Date financingTime) {
        this.financingTime = financingTime;
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
     * 获取投资金额（单位：万）
     *
     * @return amount - 投资金额（单位：万）
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置投资金额（单位：万）
     *
     * @param amount 投资金额（单位：万）
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取投资币种（人民币/美元）
     *
     * @return currency - 投资币种（人民币/美元）
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * 设置投资币种（人民币/美元）
     *
     * @param currency 投资币种（人民币/美元）
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    /**
     * 获取股权占比
     *
     * @return stock_right - 股权占比
     */
    public BigDecimal getStockRight() {
        return stockRight;
    }

    /**
     * 设置股权占比
     *
     * @param stockRight 股权占比
     */
    public void setStockRight(BigDecimal stockRight) {
        this.stockRight = stockRight;
    }

    /**
     * 获取PR总投资金额（单位：万）
     *
     * @return pr_amount - PR总投资金额（单位：万）
     */
    public BigDecimal getPrAmount() {
        return prAmount;
    }

    /**
     * 设置PR总投资金额（单位：万）
     *
     * @param prAmount PR总投资金额（单位：万）
     */
    public void setPrAmount(BigDecimal prAmount) {
        this.prAmount = prAmount;
    }

    /**
     * 获取本轮总投资金额（单位：万）
     *
     * @return total_amount - 本轮总投资金额（单位：万）
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置本轮总投资金额（单位：万）
     *
     * @param totalAmount 本轮总投资金额（单位：万）
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取本轮总出让股份
     *
     * @return share_divest - 本轮总出让股份
     */
    public String getShareDivest() {
        return shareDivest;
    }

    /**
     * 设置本轮总出让股份
     *
     * @param shareDivest 本轮总出让股份
     */
    public void setShareDivest(String shareDivest) {
        this.shareDivest = shareDivest;
    }

    /**
     * 获取投后估值（单位：万）
     *
     * @return valuation - 投后估值（单位：万）
     */
    public BigDecimal getValuation() {
        return valuation;
    }

    /**
     * 设置投后估值（单位：万）
     *
     * @param valuation 投后估值（单位：万）
     */
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    /**
     * 获取相关投资机构简称（本轮投资机构）
     *
     * @return Investment_institutions_list - 相关投资机构简称（本轮投资机构）
     */
    public String getInvestmentInstitutionsList() {
        return investmentInstitutionsList;
    }

    /**
     * 设置相关投资机构简称（本轮投资机构）
     *
     * @param investmentInstitutionsList 相关投资机构简称（本轮投资机构）
     */
    public void setInvestmentInstitutionsList(String investmentInstitutionsList) {
        this.investmentInstitutionsList = investmentInstitutionsList;
    }

    /**
     * 获取相关投资机构说明（金额、占比）
     *
     * @return proportion_list - 相关投资机构说明（金额、占比）
     */
    public String getProportionList() {
        return proportionList;
    }

    /**
     * 设置相关投资机构说明（金额、占比）
     *
     * @param proportionList 相关投资机构说明（金额、占比）
     */
    public void setProportionList(String proportionList) {
        this.proportionList = proportionList;
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
     * 获取状态，0，未审核，1，已审核（进行中），2，已完成，3，作废
     *
     * @return status - 状态，0，未审核，1，已审核（进行中），2，已完成，3，作废
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态，0，未审核，1，已审核（进行中），2，已完成，3，作废
     *
     * @param status 状态，0，未审核，1，已审核（进行中），2，已完成，3，作废
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}