package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institution_prefer_segmentation")
public class InvestmentInstitutionPreferSegmentation {
    /**
     * 公司偏好领域ID
     */
    @Column(name = "segmentation_prefer_id")
    private Integer segmentationPreferId;

    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 获取公司偏好领域ID
     *
     * @return segmentation_prefer_id - 公司偏好领域ID
     */
    public Integer getSegmentationPreferId() {
        return segmentationPreferId;
    }

    /**
     * 设置公司偏好领域ID
     *
     * @param segmentationPreferId 公司偏好领域ID
     */
    public void setSegmentationPreferId(Integer segmentationPreferId) {
        this.segmentationPreferId = segmentationPreferId;
    }

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
}