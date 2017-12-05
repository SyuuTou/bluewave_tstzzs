package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institutions_stage")
public class InvestmentInstitutionsStage {
    /**
     * 阶段元数据id
     */
    @Id
    @Column(name = "meta_project_stage_id")
    private Integer metaProjectStageId;

    /**
     * 机构id
     */
    @Id
    @Column(name = "investment_institution_id")
    private Integer investmentInstitutionId;

    /**
     * 获取阶段元数据id
     *
     * @return meta_project_stage_id - 阶段元数据id
     */
    public Integer getMetaProjectStageId() {
        return metaProjectStageId;
    }

    /**
     * 设置阶段元数据id
     *
     * @param metaProjectStageId 阶段元数据id
     */
    public void setMetaProjectStageId(Integer metaProjectStageId) {
        this.metaProjectStageId = metaProjectStageId;
    }

    /**
     * 获取机构id
     *
     * @return investment_institution_id - 机构id
     */
    public Integer getInvestmentInstitutionId() {
        return investmentInstitutionId;
    }

    /**
     * 设置机构id
     *
     * @param investmentInstitutionId 机构id
     */
    public void setInvestmentInstitutionId(Integer investmentInstitutionId) {
        this.investmentInstitutionId = investmentInstitutionId;
    }
}