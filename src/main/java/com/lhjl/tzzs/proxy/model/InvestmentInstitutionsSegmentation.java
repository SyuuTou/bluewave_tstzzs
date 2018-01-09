package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investment_institutions_segmentation")
public class InvestmentInstitutionsSegmentation {
    /**
     * 领域元数据id
     */
    @Id
    @Column(name = "meta_segmentation_id")
    private Integer metaSegmentationId;

    /**
     * 机构id
     */
    @Id
    @Column(name = "investment_institutions_id")
    private Integer investmentInstitutionsId;

    /**
     * 获取领域元数据id
     *
     * @return meta_segmentation_id - 领域元数据id
     */
    public Integer getMetaSegmentationId() {
        return metaSegmentationId;
    }

    /**
     * 设置领域元数据id
     *
     * @param metaSegmentationId 领域元数据id
     */
    public void setMetaSegmentationId(Integer metaSegmentationId) {
        this.metaSegmentationId = metaSegmentationId;
    }

    /**
     * 获取机构id
     *
     * @return investment_institutions_id - 机构id
     */
    public Integer getInvestmentInstitutionsId() {
        return investmentInstitutionsId;
    }

    /**
     * 设置机构id
     *
     * @param investmentInstitutionsId 机构id
     */
    public void setInvestmentInstitutionsId(Integer investmentInstitutionsId) {
        this.investmentInstitutionsId = investmentInstitutionsId;
    }

	@Override
	public String toString() {
		return "InvestmentInstitutionsSegmentation [metaSegmentationId=" + metaSegmentationId
				+ ", investmentInstitutionsId=" + investmentInstitutionsId + "]";
	}
    
    
}