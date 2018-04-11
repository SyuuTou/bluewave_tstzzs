package com.lhjl.tzzs.proxy.dto.FundDto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public class FundInputDto {
	/**
	 * 机构基金表的主键id
	 */
	private Integer fundId;
	
	private Integer projectId;

    private Integer institutionId;

    private String creator;

    private String shortName;

    private String fullName;

    private Date establishedTime;

    private Integer survivalPeriod;

    private String currencyType;

    private BigDecimal fundManageScale;

    private BigDecimal investmentAmountLow;

    private BigDecimal investmentAmountHigh;

    private List<Integer> investStages;

    private List<Integer> focusDomains;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public Date getEstablishedTime() {
		return establishedTime;
	}

	public void setEstablishedTime(Date establishedTime) {
		this.establishedTime = establishedTime;
	}

	public Integer getSurvivalPeriod() {
        return survivalPeriod;
    }

    public void setSurvivalPeriod(Integer survivalPeriod) {
        this.survivalPeriod = survivalPeriod;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getFundManageScale() {
        return fundManageScale;
    }

    public void setFundManageScale(BigDecimal fundManageScale) {
        this.fundManageScale = fundManageScale;
    }

    public BigDecimal getInvestmentAmountLow() {
        return investmentAmountLow;
    }

    public void setInvestmentAmountLow(BigDecimal investmentAmountLow) {
        this.investmentAmountLow = investmentAmountLow;
    }

    public BigDecimal getInvestmentAmountHigh() {
        return investmentAmountHigh;
    }

    public void setInvestmentAmountHigh(BigDecimal investmentAmountHigh) {
        this.investmentAmountHigh = investmentAmountHigh;
    }

	public List<Integer> getInvestStages() {
		return investStages;
	}

	public void setInvestStages(List<Integer> investStages) {
		this.investStages = investStages;
	}

	public List<Integer> getFocusDomains() {
		return focusDomains;
	}

	public void setFocusDomains(List<Integer> focusDomains) {
		this.focusDomains = focusDomains;
	}

    
}
