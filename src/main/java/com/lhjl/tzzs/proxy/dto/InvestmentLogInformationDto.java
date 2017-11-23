package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;

/**
 * Created by lmy on 2017/11/23.
 */
public class InvestmentLogInformationDto {

    // 投递项目信息列表的dto
    private String token;
    private String logo;
    private String shortName;
    private String fullName;
    private String oneIntroduction;
    private String jianjie;
    private String investementIdea;
    private String website;
    private String city;
    private String stage;
    private String domanin;
    private String representative;
    private BigDecimal renAmount;
    private BigDecimal meiAmount;
    private BigDecimal renAmountMin;
    private BigDecimal renAmountMax;
    private BigDecimal meiAmountMin;
    private BigDecimal meiAmountMax;
    private Integer logId;
    private Integer jigouId;

    public Integer getJigouId() {
        return jigouId;
    }

    public void setJigouId(Integer jigouId) {
        this.jigouId = jigouId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getOneIntroduction() {
        return oneIntroduction;
    }

    public void setOneIntroduction(String oneIntroduction) {
        this.oneIntroduction = oneIntroduction;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getInvestementIdea() {
        return investementIdea;
    }

    public void setInvestementIdea(String investementIdea) {
        this.investementIdea = investementIdea;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDomanin() {
        return domanin;
    }

    public void setDomanin(String domanin) {
        this.domanin = domanin;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public BigDecimal getRenAmount() {
        return renAmount;
    }

    public void setRenAmount(BigDecimal renAmount) {
        this.renAmount = renAmount;
    }

    public BigDecimal getMeiAmount() {
        return meiAmount;
    }

    public void setMeiAmount(BigDecimal meiAmount) {
        this.meiAmount = meiAmount;
    }

    public BigDecimal getRenAmountMin() {
        return renAmountMin;
    }

    public void setRenAmountMin(BigDecimal renAmountMin) {
        this.renAmountMin = renAmountMin;
    }

    public BigDecimal getRenAmountMax() {
        return renAmountMax;
    }

    public void setRenAmountMax(BigDecimal renAmountMax) {
        this.renAmountMax = renAmountMax;
    }

    public BigDecimal getMeiAmountMin() {
        return meiAmountMin;
    }

    public void setMeiAmountMin(BigDecimal meiAmountMin) {
        this.meiAmountMin = meiAmountMin;
    }

    public BigDecimal getMeiAmountMax() {
        return meiAmountMax;
    }

    public void setMeiAmountMax(BigDecimal meiAmountMax) {
        this.meiAmountMax = meiAmountMax;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }
}
