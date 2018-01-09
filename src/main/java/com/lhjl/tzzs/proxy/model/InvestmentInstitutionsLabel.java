package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institutions_label")
public class InvestmentInstitutionsLabel {
    @Column(name = "investment_institutions_id")
    private Integer investmentInstitutionsId;

    @Column(name = "label_name")
    private String labelName;

    /**
     * @return investment_institutions_id
     */
    public Integer getInvestmentInstitutionsId() {
        return investmentInstitutionsId;
    }

    /**
     * @param investmentInstitutionsId
     */
    public void setInvestmentInstitutionsId(Integer investmentInstitutionsId) {
        this.investmentInstitutionsId = investmentInstitutionsId;
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