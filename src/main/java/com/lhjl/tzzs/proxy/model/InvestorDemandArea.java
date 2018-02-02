package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investor_demand_area")
public class InvestorDemandArea {
    /**
     * 投资偏好表Id
     */
    @Column(name = "investor_demand_id")
    private Integer investorDemandId;

    /**
     * 城市名称
     */
    private String city;

    @Column(name = "app_id")
    private Integer appId;

    /**
     * 获取投资偏好表Id
     *
     * @return investor_demand_id - 投资偏好表Id
     */
    public Integer getInvestorDemandId() {
        return investorDemandId;
    }

    /**
     * 设置投资偏好表Id
     *
     * @param investorDemandId 投资偏好表Id
     */
    public void setInvestorDemandId(Integer investorDemandId) {
        this.investorDemandId = investorDemandId;
    }

    /**
     * 获取城市名称
     *
     * @return city - 城市名称
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市名称
     *
     * @param city 城市名称
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return app_id
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }
}