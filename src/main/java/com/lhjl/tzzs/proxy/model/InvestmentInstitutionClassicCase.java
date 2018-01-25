package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institution_classic_case")
public class InvestmentInstitutionClassicCase {
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 投资经典案例
     */
    @Column(name = "classic_case")
    private String classicCase;

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
     * 获取投资经典案例
     *
     * @return classic_case - 投资经典案例
     */
    public String getClassicCase() {
        return classicCase;
    }

    /**
     * 设置投资经典案例
     *
     * @param classicCase 投资经典案例
     */
    public void setClassicCase(String classicCase) {
        this.classicCase = classicCase;
    }
}