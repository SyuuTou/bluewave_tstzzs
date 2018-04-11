package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/23.
 */
public class ProjectPreferDto {

    private String token;

    private Integer projectId;
    /**
     * 投资阶段
     */
    private List<Integer> stageId;
    /**
     * 投资领域
     */
    private List<Integer> segmentationPreferIds;
    /**
     * 投资理念
     */
    private String investmentPhilosophy;
    /**
     * 项目需求
     */
    private String investmengRequirement;
    /**
     * 人民币单笔投资金额下限
     */
    private BigDecimal investmentAmountSingleLowRmb;
    /**
     * 人民币单笔投资金额上限
     */
    private BigDecimal investmentAmountSingleHighRmb;
    /**
     * 美元单笔投资金额下限
     */
    private BigDecimal investmentAmountSingleLowDollar;
    /**
     * 美元单笔投资金额上限
     */
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


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
    public List<Integer> getStageId() {
		return stageId;
	}

	public void setStageId(List<Integer> stageId) {
		this.stageId = stageId;
	}

	public List<Integer> getSegmentationPreferIds() {
		return segmentationPreferIds;
	}

	public void setSegmentationPreferIds(List<Integer> segmentationPreferIds) {
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
