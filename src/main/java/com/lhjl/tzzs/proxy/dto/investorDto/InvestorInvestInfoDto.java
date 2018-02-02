package com.lhjl.tzzs.proxy.dto.investorDto;

import java.math.BigDecimal;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public class InvestorInvestInfoDto {

    private Integer investorId;

    private Integer investorDemandId;

    private String[] focusSegmentations;

    private Integer[]  focusStages;

    private String[] preferCitys;

    private String preferDesc;

    private BigDecimal investAmountLowRmb;

    private BigDecimal investAmountHighRmb;

    private BigDecimal investAmountLowDollar;

    private BigDecimal investAmountHighDollar;

    private String[] focusSpeedway;

    private String[] focusCharacters;

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

    public String[] getFocusSegmentations() {
        return focusSegmentations;
    }

    public void setFocusSegmentations(String[] focusSegmentations) {
        this.focusSegmentations = focusSegmentations;
    }

    public Integer[] getFocusStages() {
        return focusStages;
    }

    public void setFocusStages(Integer[] focusStages) {
        this.focusStages = focusStages;
    }

    public String[] getPreferCitys() {
        return preferCitys;
    }

    public void setPreferCitys(String[] preferCitys) {
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

    public String[] getFocusSpeedway() {
        return focusSpeedway;
    }

    public void setFocusSpeedway(String[] focusSpeedway) {
        this.focusSpeedway = focusSpeedway;
    }

    public String[] getFocusCharacters() {
        return focusCharacters;
    }

    public void setFocusCharacters(String[] focusCharacters) {
        this.focusCharacters = focusCharacters;
    }
}
