package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institutions_funds_segmentation")
public class InvestmentInstitutionsFundsSegmentation {
    @Column(name = "investment_institutions_funds_id")
    private Integer investmentInstitutionsFundsId;

    @Column(name = "segmentation_id")
    private Integer segmentationId;

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
     * @return segmentation_id
     */
    public Integer getSegmentationId() {
        return segmentationId;
    }

    /**
     * @param segmentationId
     */
    public void setSegmentationId(Integer segmentationId) {
        this.segmentationId = segmentationId;
    }
}