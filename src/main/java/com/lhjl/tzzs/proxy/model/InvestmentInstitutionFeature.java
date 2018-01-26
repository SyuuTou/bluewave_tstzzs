package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "investment_institution_feature")
public class InvestmentInstitutionFeature {
    /**
     * 公司Id
     */
    @Id
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 投资理念
     */
    @Column(name = "investment_philosophy")
    private String investmentPhilosophy;

    /**
     * 投资要求
     */
    @Column(name = "investment_requirement")
    private String investmentRequirement;

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
     * 获取投资理念
     *
     * @return investment_philosophy - 投资理念
     */
    public String getInvestmentPhilosophy() {
        return investmentPhilosophy;
    }

    /**
     * 设置投资理念
     *
     * @param investmentPhilosophy 投资理念
     */
    public void setInvestmentPhilosophy(String investmentPhilosophy) {
        this.investmentPhilosophy = investmentPhilosophy;
    }

    /**
     * 获取投资要求
     *
     * @return investment_requirement - 投资要求
     */
    public String getInvestmentRequirement() {
        return investmentRequirement;
    }

    /**
     * 设置投资要求
     *
     * @param investmentRequirement 投资要求
     */
    public void setInvestmentRequirement(String investmentRequirement) {
        this.investmentRequirement = investmentRequirement;
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