package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "investor_demand")
public class InvestorDemand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 地域偏好
     */
    @Column(name = "city_preference")
    private String cityPreference;

    /**
     * 需求
     */
    private String demand;

    /**
     * 投资金额下限
     */
    @Column(name = "investment_amount_low")
    private BigDecimal investmentAmountLow;

    /**
     * 投资金额上限
     */
    @Column(name = "investment_amount_high")
    private BigDecimal investmentAmountHigh;

    @Column(name = "investment_amount_low_dollars")
    private BigDecimal investmentAmountLowDollars;

    @Column(name = "investment_amount_high_dollars")
    private BigDecimal investmentAmountHighDollars;

    /**
     * 最近关注细分赛道
     */
    @Column(name = "recently_concerned_subdivision_circuit")
    private String recentlyConcernedSubdivisionCircuit;

    /**
     * 关注的创始人特质
     */
    @Column(name = "concerned_founders_characteristic")
    private String concernedFoundersCharacteristic;

    /**
     * 行业领域
     */
    private String industry;

    /**
     * 融资阶段
     */
    @Column(name = "financing_stage")
    private String financingStage;

    /**
     * 创建时间
     */
    @Column(name = "creat_time")
    private Date creatTime;

    /**
     * 2018展望
     */
    private String future;

    /**
     * 权重
     */
    private Integer weights;

    /**
     * 是否隐藏
     */
    @Column(name = "hide_yn")
    private Integer hideYn;

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
     * 获取用户id
     *
     * @return userid - 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取地域偏好
     *
     * @return city_preference - 地域偏好
     */
    public String getCityPreference() {
        return cityPreference;
    }

    /**
     * 设置地域偏好
     *
     * @param cityPreference 地域偏好
     */
    public void setCityPreference(String cityPreference) {
        this.cityPreference = cityPreference;
    }

    /**
     * 获取需求
     *
     * @return demand - 需求
     */
    public String getDemand() {
        return demand;
    }

    /**
     * 设置需求
     *
     * @param demand 需求
     */
    public void setDemand(String demand) {
        this.demand = demand;
    }

    /**
     * 获取投资金额下限
     *
     * @return investment_amount_low - 投资金额下限
     */
    public BigDecimal getInvestmentAmountLow() {
        return investmentAmountLow;
    }

    /**
     * 设置投资金额下限
     *
     * @param investmentAmountLow 投资金额下限
     */
    public void setInvestmentAmountLow(BigDecimal investmentAmountLow) {
        this.investmentAmountLow = investmentAmountLow;
    }

    /**
     * 获取投资金额上限
     *
     * @return investment_amount_high - 投资金额上限
     */
    public BigDecimal getInvestmentAmountHigh() {
        return investmentAmountHigh;
    }

    /**
     * 设置投资金额上限
     *
     * @param investmentAmountHigh 投资金额上限
     */
    public void setInvestmentAmountHigh(BigDecimal investmentAmountHigh) {
        this.investmentAmountHigh = investmentAmountHigh;
    }

    /**
     * @return investment_amount_low_dollars
     */
    public BigDecimal getInvestmentAmountLowDollars() {
        return investmentAmountLowDollars;
    }

    /**
     * @param investmentAmountLowDollars
     */
    public void setInvestmentAmountLowDollars(BigDecimal investmentAmountLowDollars) {
        this.investmentAmountLowDollars = investmentAmountLowDollars;
    }

    /**
     * @return investment_amount_high_dollars
     */
    public BigDecimal getInvestmentAmountHighDollars() {
        return investmentAmountHighDollars;
    }

    /**
     * @param investmentAmountHighDollars
     */
    public void setInvestmentAmountHighDollars(BigDecimal investmentAmountHighDollars) {
        this.investmentAmountHighDollars = investmentAmountHighDollars;
    }

    /**
     * 获取最近关注细分赛道
     *
     * @return recently_concerned_subdivision_circuit - 最近关注细分赛道
     */
    public String getRecentlyConcernedSubdivisionCircuit() {
        return recentlyConcernedSubdivisionCircuit;
    }

    /**
     * 设置最近关注细分赛道
     *
     * @param recentlyConcernedSubdivisionCircuit 最近关注细分赛道
     */
    public void setRecentlyConcernedSubdivisionCircuit(String recentlyConcernedSubdivisionCircuit) {
        this.recentlyConcernedSubdivisionCircuit = recentlyConcernedSubdivisionCircuit;
    }

    /**
     * 获取关注的创始人特质
     *
     * @return concerned_founders_characteristic - 关注的创始人特质
     */
    public String getConcernedFoundersCharacteristic() {
        return concernedFoundersCharacteristic;
    }

    /**
     * 设置关注的创始人特质
     *
     * @param concernedFoundersCharacteristic 关注的创始人特质
     */
    public void setConcernedFoundersCharacteristic(String concernedFoundersCharacteristic) {
        this.concernedFoundersCharacteristic = concernedFoundersCharacteristic;
    }

    /**
     * 获取行业领域
     *
     * @return industry - 行业领域
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置行业领域
     *
     * @param industry 行业领域
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * 获取融资阶段
     *
     * @return financing_stage - 融资阶段
     */
    public String getFinancingStage() {
        return financingStage;
    }

    /**
     * 设置融资阶段
     *
     * @param financingStage 融资阶段
     */
    public void setFinancingStage(String financingStage) {
        this.financingStage = financingStage;
    }

    /**
     * 获取创建时间
     *
     * @return creat_time - 创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 设置创建时间
     *
     * @param creatTime 创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * 获取2018展望
     *
     * @return future - 2018展望
     */
    public String getFuture() {
        return future;
    }

    /**
     * 设置2018展望
     *
     * @param future 2018展望
     */
    public void setFuture(String future) {
        this.future = future;
    }

    /**
     * 获取权重
     *
     * @return weights - 权重
     */
    public Integer getWeights() {
        return weights;
    }

    /**
     * 设置权重
     *
     * @param weights 权重
     */
    public void setWeights(Integer weights) {
        this.weights = weights;
    }

    /**
     * 获取是否隐藏
     *
     * @return hide_yn - 是否隐藏
     */
    public Integer getHideYn() {
        return hideYn;
    }

    /**
     * 设置是否隐藏
     *
     * @param hideYn 是否隐藏
     */
    public void setHideYn(Integer hideYn) {
        this.hideYn = hideYn;
    }
}