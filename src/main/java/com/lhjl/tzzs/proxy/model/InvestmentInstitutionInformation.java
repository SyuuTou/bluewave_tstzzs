package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "investment_institution_information")
public class InvestmentInstitutionInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 机构logo
     */
    private String logo;

    /**
     * 机构简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 机构全称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 一句话介绍
     */
    private String oneintroduction;

    /**
     * 简介
     */
    private String summary;

    /**
     * 投资理念
     */
    @Column(name = "investment_idea")
    private String investmentIdea;

    /**
     * 官网
     */
    private String website;

    /**
     * 城市
     */
    private String city;

    /**
     * 轮次
     */
    private String stage;

    /**
     * 领域
     */
    private String domain;

    /**
     * 人民币
     */
    @Column(name = "ren_amount")
    private BigDecimal renAmount;

    /**
     * 美元
     */
    @Column(name = "mei_amount")
    private BigDecimal meiAmount;

    /**
     * 人民币区间开始
     */
    @Column(name = "ren_amount_mix")
    private BigDecimal renAmountMix;

    /**
     * 人民币区间结束
     */
    @Column(name = "ren_amount_max")
    private BigDecimal renAmountMax;

    /**
     * 美元区间开始
     */
    @Column(name = "mei_amount_mix")
    private BigDecimal meiAmountMix;

    /**
     * 美元区间结束
     */
    @Column(name = "mei_amount_max")
    private BigDecimal meiAmountMax;

    /**
     * 投资代表
     */
    private String representative;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 记录的id
     */
    @Column(name = "log_id")
    private Integer logId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取机构logo
     *
     * @return logo - 机构logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 设置机构logo
     *
     * @param logo 机构logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 获取机构简称
     *
     * @return short_name - 机构简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置机构简称
     *
     * @param shortName 机构简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取机构全称
     *
     * @return full_name - 机构全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置机构全称
     *
     * @param fullName 机构全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取一句话介绍
     *
     * @return oneintroduction - 一句话介绍
     */
    public String getOneintroduction() {
        return oneintroduction;
    }

    /**
     * 设置一句话介绍
     *
     * @param oneintroduction 一句话介绍
     */
    public void setOneintroduction(String oneintroduction) {
        this.oneintroduction = oneintroduction;
    }

    /**
     * 获取简介
     *
     * @return summary - 简介
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置简介
     *
     * @param summary 简介
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取投资理念
     *
     * @return investment_idea - 投资理念
     */
    public String getInvestmentIdea() {
        return investmentIdea;
    }

    /**
     * 设置投资理念
     *
     * @param investmentIdea 投资理念
     */
    public void setInvestmentIdea(String investmentIdea) {
        this.investmentIdea = investmentIdea;
    }

    /**
     * 获取官网
     *
     * @return website - 官网
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 设置官网
     *
     * @param website 官网
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取轮次
     *
     * @return stage - 轮次
     */
    public String getStage() {
        return stage;
    }

    /**
     * 设置轮次
     *
     * @param stage 轮次
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * 获取领域
     *
     * @return domain - 领域
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 设置领域
     *
     * @param domain 领域
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 获取人民币
     *
     * @return ren_amount - 人民币
     */
    public BigDecimal getRenAmount() {
        return renAmount;
    }

    /**
     * 设置人民币
     *
     * @param renAmount 人民币
     */
    public void setRenAmount(BigDecimal renAmount) {
        this.renAmount = renAmount;
    }

    /**
     * 获取美元
     *
     * @return mei_amount - 美元
     */
    public BigDecimal getMeiAmount() {
        return meiAmount;
    }

    /**
     * 设置美元
     *
     * @param meiAmount 美元
     */
    public void setMeiAmount(BigDecimal meiAmount) {
        this.meiAmount = meiAmount;
    }

    /**
     * 获取人民币区间开始
     *
     * @return ren_amount_mix - 人民币区间开始
     */
    public BigDecimal getRenAmountMix() {
        return renAmountMix;
    }

    /**
     * 设置人民币区间开始
     *
     * @param renAmountMix 人民币区间开始
     */
    public void setRenAmountMix(BigDecimal renAmountMix) {
        this.renAmountMix = renAmountMix;
    }

    /**
     * 获取人民币区间结束
     *
     * @return ren_amount_max - 人民币区间结束
     */
    public BigDecimal getRenAmountMax() {
        return renAmountMax;
    }

    /**
     * 设置人民币区间结束
     *
     * @param renAmountMax 人民币区间结束
     */
    public void setRenAmountMax(BigDecimal renAmountMax) {
        this.renAmountMax = renAmountMax;
    }

    /**
     * 获取美元区间开始
     *
     * @return mei_amount_mix - 美元区间开始
     */
    public BigDecimal getMeiAmountMix() {
        return meiAmountMix;
    }

    /**
     * 设置美元区间开始
     *
     * @param meiAmountMix 美元区间开始
     */
    public void setMeiAmountMix(BigDecimal meiAmountMix) {
        this.meiAmountMix = meiAmountMix;
    }

    /**
     * 获取美元区间结束
     *
     * @return mei_amount_max - 美元区间结束
     */
    public BigDecimal getMeiAmountMax() {
        return meiAmountMax;
    }

    /**
     * 设置美元区间结束
     *
     * @param meiAmountMax 美元区间结束
     */
    public void setMeiAmountMax(BigDecimal meiAmountMax) {
        this.meiAmountMax = meiAmountMax;
    }

    /**
     * 获取投资代表
     *
     * @return representative - 投资代表
     */
    public String getRepresentative() {
        return representative;
    }

    /**
     * 设置投资代表
     *
     * @param representative 投资代表
     */
    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取记录的id
     *
     * @return log_id - 记录的id
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * 设置记录的id
     *
     * @param logId 记录的id
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }
}