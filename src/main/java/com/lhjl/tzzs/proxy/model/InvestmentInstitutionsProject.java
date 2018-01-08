package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institutions_project")
public class InvestmentInstitutionsProject {
    /**
     * 项目ID
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 机构ID
     */
    @Column(name = "investment_institutions_id")
    private Integer investmentInstitutionsId;

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
     * 获取机构ID
     *
     * @return investment_institutions_id - 机构ID
     */
    public Integer getInvestmentInstitutionsId() {
        return investmentInstitutionsId;
    }

    /**
     * 设置机构ID
     *
     * @param investmentInstitutionsId 机构ID
     */
    public void setInvestmentInstitutionsId(Integer investmentInstitutionsId) {
        this.investmentInstitutionsId = investmentInstitutionsId;
    }
}