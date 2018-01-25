package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institution_invest_type")
public class InvestmentInstitutionInvestType {
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 投资类型Id
     */
    @Column(name = "invest_type_id")
    private Integer investTypeId;

    /**
     * @return company_id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取投资类型Id
     *
     * @return invest_type_id - 投资类型Id
     */
    public Integer getInvestTypeId() {
        return investTypeId;
    }

    /**
     * 设置投资类型Id
     *
     * @param investTypeId 投资类型Id
     */
    public void setInvestTypeId(Integer investTypeId) {
        this.investTypeId = investTypeId;
    }
}