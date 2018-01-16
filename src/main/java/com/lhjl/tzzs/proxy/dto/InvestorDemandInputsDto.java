package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;
import java.util.List;

public class InvestorDemandInputsDto {
    /**领域*/
    private List<String> segmentation;

    /**赛道*/
    private List<String> speedway;

    /**阶段*/
    private List<Integer> stage;

    /**单笔投资金额下限人民币*/
    private BigDecimal investmentAmountLow;

    /**单笔投资金额上限人民币*/
    private BigDecimal investmentAmountHigh;

    /**单笔投资金额下限美元*/
    private BigDecimal investmentAmountLowDollars;

    /**单笔投资金额上限美元*/
    private BigDecimal investmentAmountHighDollars;

    /**特质*/
    private List<String> character;

    /**2018展望*/
    private String future;

    /**用户token*/
    private String token;

    public List<String> getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(List<String> segmentation) {
        this.segmentation = segmentation;
    }

    public List<String> getSpeedway() {
        return speedway;
    }

    public void setSpeedway(List<String> speedway) {
        this.speedway = speedway;
    }

    public List<Integer> getStage() {
        return stage;
    }

    public void setStage(List<Integer> stage) {
        this.stage = stage;
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

    public BigDecimal getInvestmentAmountLowDollars() {
        return investmentAmountLowDollars;
    }

    public void setInvestmentAmountLowDollars(BigDecimal investmentAmountLowDollars) {
        this.investmentAmountLowDollars = investmentAmountLowDollars;
    }

    public BigDecimal getInvestmentAmountHighDollars() {
        return investmentAmountHighDollars;
    }

    public void setInvestmentAmountHighDollars(BigDecimal investmentAmountHighDollars) {
        this.investmentAmountHighDollars = investmentAmountHighDollars;
    }

    public List<String> getCharacter() {
        return character;
    }

    public void setCharacter(List<String> character) {
        this.character = character;
    }

    public String getFuture() {
        return future;
    }

    public void setFuture(String future) {
        this.future = future;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
