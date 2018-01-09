package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institutions_funds_label")
public class InvestmentInstitutionsFundsLabel {
    @Column(name = "investment_institutions_funds_id")
    private Integer investmentInstitutionsFundsId;

    @Column(name = "label_name")
    private String labelName;

    /**
     * @return investment_institutions_funds_id
     */
    public Integer getInvestmentInstitutionsFundsId() {
        return investmentInstitutionsFundsId;
    }

    /**
     * @param investmentInstitutionsFundsId
     */
    public void setInvestmentInstitutionsFundsId(Integer investmentInstitutionsFundsId) {
        this.investmentInstitutionsFundsId = investmentInstitutionsFundsId;
    }

    /**
     * @return label_name
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * @param labelName
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}