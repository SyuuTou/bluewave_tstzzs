package com.lhjl.tzzs.proxy.dto.investorDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public class InvestorInvestInfoDto {

    private Integer investorId;

    private Integer investorDemandId;
    //关注领域
    private List<String> focusSegmentations;
    //投资阶段
    private List<Integer>  focusStages;
    //地域偏好
    private List<String> preferCitys;
    //最近关注细分赛道
    private List<String> focusSpeedway;
    //关注的创始人特质
    private List<String> focusCharacters;

    private String preferDesc;

    private BigDecimal investAmountLowRmb;

    private BigDecimal investAmountHighRmb;

    private BigDecimal investAmountLowDollar;

    private BigDecimal investAmountHighDollar;



	public Integer getInvestorId() {
		return investorId;
	}

	public void setInvestorId(Integer investorId) {
		this.investorId = investorId;
	}

	public Integer getInvestorDemandId() {
		return investorDemandId;
	}

	public void setInvestorDemandId(Integer investorDemandId) {
		this.investorDemandId = investorDemandId;
	}

	public List<String> getFocusSegmentations() {
		return focusSegmentations;
	}

	public void setFocusSegmentations(List<String> focusSegmentations) {
		this.focusSegmentations = focusSegmentations;
	}

	public List<Integer> getFocusStages() {
		return focusStages;
	}

	public void setFocusStages(List<Integer> focusStages) {
		this.focusStages = focusStages;
	}

	public List<String> getPreferCitys() {
		return preferCitys;
	}

	public void setPreferCitys(List<String> preferCitys) {
		this.preferCitys = preferCitys;
	}

	public String getPreferDesc() {
		return preferDesc;
	}

	public void setPreferDesc(String preferDesc) {
		this.preferDesc = preferDesc;
	}

	public BigDecimal getInvestAmountLowRmb() {
		return investAmountLowRmb;
	}

	public void setInvestAmountLowRmb(BigDecimal investAmountLowRmb) {
		this.investAmountLowRmb = investAmountLowRmb;
	}

	public BigDecimal getInvestAmountHighRmb() {
		return investAmountHighRmb;
	}

	public void setInvestAmountHighRmb(BigDecimal investAmountHighRmb) {
		this.investAmountHighRmb = investAmountHighRmb;
	}

	public BigDecimal getInvestAmountLowDollar() {
		return investAmountLowDollar;
	}

	public void setInvestAmountLowDollar(BigDecimal investAmountLowDollar) {
		this.investAmountLowDollar = investAmountLowDollar;
	}

	public BigDecimal getInvestAmountHighDollar() {
		return investAmountHighDollar;
	}

	public void setInvestAmountHighDollar(BigDecimal investAmountHighDollar) {
		this.investAmountHighDollar = investAmountHighDollar;
	}

	public List<String> getFocusSpeedway() {
		return focusSpeedway;
	}

	public void setFocusSpeedway(List<String> focusSpeedway) {
		this.focusSpeedway = focusSpeedway;
	}

	public List<String> getFocusCharacters() {
		return focusCharacters;
	}

	public void setFocusCharacters(List<String> focusCharacters) {
		this.focusCharacters = focusCharacters;
	}

    
}
