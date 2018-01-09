package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institutions_funds_stages")
public class InvestmentInstitutionsFundsStages {
    /**
     * 机构基金表ID
     */
    @Column(name = "investment_institutions_funds_id")
    private Integer investmentInstitutionsFundsId;

    /**
     * 阶段表ID
     */
    @Column(name = "stage_id")
    private Integer stageId;

    /**
     * 获取机构基金表ID
     *
     * @return investment_institutions_funds_id - 机构基金表ID
     */
    public Integer getInvestmentInstitutionsFundsId() {
        return investmentInstitutionsFundsId;
    }

    /**
     * 设置机构基金表ID
     *
     * @param investmentInstitutionsFundsId 机构基金表ID
     */
    public void setInvestmentInstitutionsFundsId(Integer investmentInstitutionsFundsId) {
        this.investmentInstitutionsFundsId = investmentInstitutionsFundsId;
    }

    /**
     * 获取阶段表ID
     *
     * @return stage_id - 阶段表ID
     */
    public Integer getStageId() {
        return stageId;
    }

    /**
     * 设置阶段表ID
     *
     * @param stageId 阶段表ID
     */
    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }
}