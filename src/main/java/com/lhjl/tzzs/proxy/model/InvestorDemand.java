package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "investor_demand")
public class InvestorDemand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 投资人需求表
     */
    private Integer investorid;

    /**
     * 是否领投，0代表不是，1代表是；默认0
     */
    private Integer leadership;

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
     * 获取投资人需求表
     *
     * @return investorid - 投资人需求表
     */
    public Integer getInvestorid() {
        return investorid;
    }

    /**
     * 设置投资人需求表
     *
     * @param investorid 投资人需求表
     */
    public void setInvestorid(Integer investorid) {
        this.investorid = investorid;
    }

    /**
     * 获取是否领投，0代表不是，1代表是；默认0
     *
     * @return leadership - 是否领投，0代表不是，1代表是；默认0
     */
    public Integer getLeadership() {
        return leadership;
    }

    /**
     * 设置是否领投，0代表不是，1代表是；默认0
     *
     * @param leadership 是否领投，0代表不是，1代表是；默认0
     */
    public void setLeadership(Integer leadership) {
        this.leadership = leadership;
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
}