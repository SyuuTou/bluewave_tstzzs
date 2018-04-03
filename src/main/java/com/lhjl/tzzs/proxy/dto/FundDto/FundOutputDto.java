package com.lhjl.tzzs.proxy.dto.FundDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public class FundOutputDto {

    private Integer fundId;

    private String shortName;

    private String fullName;

    private String establishedTime;

    private Integer survivalPeriod;

    private String currencyType;

    private BigDecimal fundManageScale;

    private BigDecimal investmentAmountLow;

    private BigDecimal investmentAmountHigh;

    private List<String> investStages;

    private List<String> focusDomains;


    public List<String> getInvestStages() {
		return investStages;
	}

	public void setInvestStages(List<String> investStages) {
		this.investStages = investStages;
	}

	public List<String> getFocusDomains() {
		return focusDomains;
	}

	public void setFocusDomains(List<String> focusDomains) {
		this.focusDomains = focusDomains;
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

    public String getEstablishedTime() {
        return establishedTime;
    }

    public void setEstablishedTime(String establishedTime) {
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
}
