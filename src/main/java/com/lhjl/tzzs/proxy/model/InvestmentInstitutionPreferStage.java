package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institution_prefer_stage")
public class InvestmentInstitutionPreferStage {
    /**
     * 公司Id
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 投资阶段Id
     */
    @Column(name = "stage_id")
    private Integer stageId;

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
     * 获取投资阶段Id
     *
     * @return stage_id - 投资阶段Id
     */
    public Integer getStageId() {
        return stageId;
    }

    /**
     * 设置投资阶段Id
     *
     * @param stageId 投资阶段Id
     */
    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }
}