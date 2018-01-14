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
     * 投资金额是否是计算值，1:为计算值，0:为原始值
     */
    @Column(name = "calculation_amount_status")
    private Integer calculationAmountStatus;

    /**
     * 投资币种（0人民币/1美元）
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

    private BigDecimal rate;

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
     * 审核状态，0表示审核未通过，1表示审核通过，默认0
     */
    @Column(name = "approval_status")
    private Integer approvalStatus;

    /**
     * 审核时间，审核时存，其他时候为空
     */
    @Column(name = "approval_time")
    private Date approvalTime;

    @Column(name = "amount_status")
    private Integer amountStatus;

    @Column(name = "total_amount_status")
    private Integer totalAmountStatus;

    /**
     * 保存年份的冗余字段
     */
    @Column(name = "financing_time_year")
    private String financingTimeYear;

    @Column(name = "project_financing_useful")
    private String projectFinancingUseful;

    /**
     * 编号，流水号
     */
    @Column(name = "serial_number")
    private Integer serialNumber;

    /**
     * 数据来源类型表id
     */
    @Column(name = "data_soruce_type_id")
    private Integer dataSoruceTypeId;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取投资金额是否是计算值，1:为计算值，0:为原始值
     *
     * @return calculation_amount_status - 投资金额是否是计算值，1:为计算值，0:为原始值
     */
    public Integer getCalculationAmountStatus() {
        return calculationAmountStatus;
    }

    /**
     * 设置投资金额是否是计算值，1:为计算值，0:为原始值
     *
     * @param calculationAmountStatus 投资金额是否是计算值，1:为计算值，0:为原始值
     */
    public void setCalculationAmountStatus(Integer calculationAmountStatus) {
        this.calculationAmountStatus = calculationAmountStatus;
    }

    /**
     * 获取投资币种（0人民币/1美元）
     *
     * @return currency - 投资币种（0人民币/1美元）
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * 设置投资币种（0人民币/1美元）
     *
     * @param currency 投资币种（0人民币/1美元）
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
     * @return rate
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * @param rate
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
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
     * @return amount_status
     */
    public Integer getAmountStatus() {
        return amountStatus;
    }

    /**
     * @param amountStatus
     */
    public void setAmountStatus(Integer amountStatus) {
        this.amountStatus = amountStatus;
    }

    /**
     * @return total_amount_status
     */
    public Integer getTotalAmountStatus() {
        return totalAmountStatus;
    }

    /**
     * @param totalAmountStatus
     */
    public void setTotalAmountStatus(Integer totalAmountStatus) {
        this.totalAmountStatus = totalAmountStatus;
    }

    /**
     * 获取保存年份的冗余字段
     *
     * @return financing_time_year - 保存年份的冗余字段
     */
    public String getFinancingTimeYear() {
        return financingTimeYear;
    }

    /**
     * 设置保存年份的冗余字段
     *
     * @param financingTimeYear 保存年份的冗余字段
     */
    public void setFinancingTimeYear(String financingTimeYear) {
        this.financingTimeYear = financingTimeYear;
    }

    /**
     * @return project_financing_useful
     */
    public String getProjectFinancingUseful() {
        return projectFinancingUseful;
    }

    /**
     * @param projectFinancingUseful
     */
    public void setProjectFinancingUseful(String projectFinancingUseful) {
        this.projectFinancingUseful = projectFinancingUseful;
    }

    /**
     * 获取编号，流水号
     *
     * @return serial_number - 编号，流水号
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }

    /**
     * 设置编号，流水号
     *
     * @param serialNumber 编号，流水号
     */
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 获取数据来源类型表id
     *
     * @return data_soruce_type_id - 数据来源类型表id
     */
    public Integer getDataSoruceTypeId() {
        return dataSoruceTypeId;
    }

    /**
     * 设置数据来源类型表id
     *
     * @param dataSoruceTypeId 数据来源类型表id
     */
    public void setDataSoruceTypeId(Integer dataSoruceTypeId) {
        this.dataSoruceTypeId = dataSoruceTypeId;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}