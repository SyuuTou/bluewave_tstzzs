package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "investment_institution_fund_manage")
public class InvestmentInstitutionFundManage {
    /**
     * 公司Id
     */
    @Id
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 内部组织结构
     */
    @Column(name = "interior_organization")
    private String interiorOrganization;

    /**
     * 投资决策流程
     */
    @Column(name = "investment_decision_process")
    private String investmentDecisionProcess;

    /**
     * 总基金管理规模
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 人民币基金管理规模
     */
    @Column(name = "rmb_amount")
    private BigDecimal rmbAmount;

    /**
     * 美元基金管理规模
     */
    @Column(name = "dollar_amount")
    private BigDecimal dollarAmount;

    /**
     * BP邮箱
     */
    @Column(name = "bp_email")
    private String bpEmail;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 获取公司Id
     *
     * @return company_id - 公司Id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司Id
     *
     * @param companyId 公司Id
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取内部组织结构
     *
     * @return interior_organization - 内部组织结构
     */
    public String getInteriorOrganization() {
        return interiorOrganization;
    }

    /**
     * 设置内部组织结构
     *
     * @param interiorOrganization 内部组织结构
     */
    public void setInteriorOrganization(String interiorOrganization) {
        this.interiorOrganization = interiorOrganization;
    }

    /**
     * 获取投资决策流程
     *
     * @return investment_decision_process - 投资决策流程
     */
    public String getInvestmentDecisionProcess() {
        return investmentDecisionProcess;
    }

    /**
     * 设置投资决策流程
     *
     * @param investmentDecisionProcess 投资决策流程
     */
    public void setInvestmentDecisionProcess(String investmentDecisionProcess) {
        this.investmentDecisionProcess = investmentDecisionProcess;
    }

    /**
     * 获取总基金管理规模
     *
     * @return total_amount - 总基金管理规模
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置总基金管理规模
     *
     * @param totalAmount 总基金管理规模
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取人民币基金管理规模
     *
     * @return rmb_amount - 人民币基金管理规模
     */
    public BigDecimal getRmbAmount() {
        return rmbAmount;
    }

    /**
     * 设置人民币基金管理规模
     *
     * @param rmbAmount 人民币基金管理规模
     */
    public void setRmbAmount(BigDecimal rmbAmount) {
        this.rmbAmount = rmbAmount;
    }

    /**
     * 获取美元基金管理规模
     *
     * @return dollar_amount - 美元基金管理规模
     */
    public BigDecimal getDollarAmount() {
        return dollarAmount;
    }

    /**
     * 设置美元基金管理规模
     *
     * @param dollarAmount 美元基金管理规模
     */
    public void setDollarAmount(BigDecimal dollarAmount) {
        this.dollarAmount = dollarAmount;
    }

    /**
     * 获取BP邮箱
     *
     * @return bp_email - BP邮箱
     */
    public String getBpEmail() {
        return bpEmail;
    }

    /**
     * 设置BP邮箱
     *
     * @param bpEmail BP邮箱
     */
    public void setBpEmail(String bpEmail) {
        this.bpEmail = bpEmail;
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

    /**
     * 获取创建者
     *
     * @return creator - 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
}