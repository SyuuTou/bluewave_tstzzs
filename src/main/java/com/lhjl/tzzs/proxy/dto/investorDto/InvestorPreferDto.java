package com.lhjl.tzzs.proxy.dto.investorDto;

import java.math.BigDecimal;

/**
 * Created by lanhaijulang on 2018/1/25.
 */
public class InvestorPreferDto {

    private Integer[] segmentations;

    private Integer[] stages;

    private Integer[] area;

    private BigDecimal investAmountLowRmb;

    private BigDecimal investAmountHighRmb;

    private BigDecimal investAmountLowDollar;

    private BigDecimal investAmountHighDollar;

    private String[] subSpeedWay;

    private String[] character;

    public Integer[] getSegmentations() {
        return segmentations;
    }

    public void setSegmentations(Integer[] segmentations) {
        this.segmentations = segmentations;
    }

    public Integer[] getStages() {
        return stages;
    }

    public void setStages(Integer[] stages) {
        this.stages = stages;
    }

    public Integer[] getArea() {
        return area;
    }

    public void setArea(Integer[] area) {
        this.area = area;
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

    public String[] getSubSpeedWay() {
        return subSpeedWay;
    }

    public void setSubSpeedWay(String[] subSpeedWay) {
        this.subSpeedWay = subSpeedWay;
    }

    public String[] getCharacter() {
        return character;
    }

    public void setCharacter(String[] character) {
        this.character = character;
    }
}
