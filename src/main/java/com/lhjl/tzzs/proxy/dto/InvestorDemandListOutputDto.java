package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;
import java.util.List;

public class InvestorDemandListOutputDto {

    /**融资需求id*/
    private Integer id;

    /**用户头像*/
    private String headpic;

    /**用户真实姓名*/
    private String userName;

    /**公司职位*/
    private String companyDuties;

    /**公司名称*/
    private String companyName;

    /**手机号码*/
    private String phoneNum;

    /**领域*/
    private List<String> segmentation;

    /**赛道*/
    private List<String> speedWay;

    /**阶段*/
    private List<String> stage;

    /**单笔投资金额下限*/
    private BigDecimal investmentAmountLow;

    /**单笔投资金额上限*/
    private BigDecimal investmentAmountHigh;

    /**单笔投资金额下限美元*/
    private BigDecimal investmentAmountLowDollars;

    /**单笔投资金额上限美元*/
    private BigDecimal investmentAmountHighDollars;

    /**投资人特质*/
    private List<String> character;

    /**2018展望*/
    private String future;

    /**状态*/
    private String status;

    /**更新时间*/
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyDuties() {
        return companyDuties;
    }

    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<String> getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(List<String> segmentation) {
        this.segmentation = segmentation;
    }

    public List<String> getSpeedWay() {
        return speedWay;
    }

    public void setSpeedWay(List<String> speedWay) {
        this.speedWay = speedWay;
    }

    public List<String> getStage() {
        return stage;
    }

    public void setStage(List<String> stage) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
