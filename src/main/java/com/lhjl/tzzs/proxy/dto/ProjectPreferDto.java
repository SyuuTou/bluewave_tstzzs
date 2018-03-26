package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;

/**
 * Created by lanhaijulang on 2018/1/23.
 */
public class ProjectPreferDto {

    private String token;

    private Integer projectId;

    private Integer[] stageId;

    private Integer[] segmentationPreferIds;

    private String investmentPhilosophy;

    private String investmengRequirement;

    private BigDecimal investmentAmountSingleLowRmb;

    private BigDecimal investmentAmountSingleHighRmb;

    private BigDecimal investmentAmountSingleLowDollar;

    private BigDecimal investmentAmountSingleHighDollar;
    /**
     * 主体类型
     */
    private Integer subjectType;
    

    public Integer getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}

	public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer[] getStageId() {
        return stageId;
    }

    public void setStageId(Integer[] stageId) {
        this.stageId = stageId;
    }

    public Integer[] getSegmentationPreferIds() {
        return segmentationPreferIds;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setSegmentationPreferIds(Integer[] segmentationPreferIds) {
        this.segmentationPreferIds = segmentationPreferIds;
    }

    public String getInvestmentPhilosophy() {
        return investmentPhilosophy;
    }

    public void setInvestmentPhilosophy(String investmentPhilosophy) {
        this.investmentPhilosophy = investmentPhilosophy;
    }

    public String getInvestmengRequirement() {
        return investmengRequirement;
    }

    public void setInvestmengRequirement(String investmengRequirement) {
        this.investmengRequirement = investmengRequirement;
    }

    public BigDecimal getInvestmentAmountSingleLowRmb() {
        return investmentAmountSingleLowRmb;
    }

    public void setInvestmentAmountSingleLowRmb(BigDecimal investmentAmountSingleLowRmb) {
        this.investmentAmountSingleLowRmb = investmentAmountSingleLowRmb;
    }

    public BigDecimal getInvestmentAmountSingleHighRmb() {
        return investmentAmountSingleHighRmb;
    }

    public void setInvestmentAmountSingleHighRmb(BigDecimal investmentAmountSingleHighRmb) {
        this.investmentAmountSingleHighRmb = investmentAmountSingleHighRmb;
    }

    public BigDecimal getInvestmentAmountSingleLowDollar() {
        return investmentAmountSingleLowDollar;
    }

    public void setInvestmentAmountSingleLowDollar(BigDecimal investmentAmountSingleLowDollar) {
        this.investmentAmountSingleLowDollar = investmentAmountSingleLowDollar;
    }

    public BigDecimal getInvestmentAmountSingleHighDollar() {
        return investmentAmountSingleHighDollar;
    }

    public void setInvestmentAmountSingleHighDollar(BigDecimal investmentAmountSingleHighDollar) {
        this.investmentAmountSingleHighDollar = investmentAmountSingleHighDollar;
    }
}
