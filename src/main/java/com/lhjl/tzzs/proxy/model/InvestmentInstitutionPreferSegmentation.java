package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institution_prefer_segmentation")
public class InvestmentInstitutionPreferSegmentation {
    /**
     * 公司ID
     */
    @Id
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 公司偏好领域ID
     */
    @Column(name = "segmentation_prefer_id")
    private Integer segmentationPreferId;

    /**
     * 获取公司ID
     *
     * @return company_id - 公司ID
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司ID
     *
     * @param companyId 公司ID
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

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
}