package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investor_demand_speedway")
public class InvestorDemandSpeedway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 赛道名称
     */
    private String speedway;

    /**
     * 投资需求表id
     */
    @Column(name = "investor_demand_id")
    private Integer investorDemandId;

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
     * 获取赛道名称
     *
     * @return speedway - 赛道名称
     */
    public String getSpeedway() {
        return speedway;
    }

    /**
     * 设置赛道名称
     *
     * @param speedway 赛道名称
     */
    public void setSpeedway(String speedway) {
        this.speedway = speedway;
    }

    /**
     * 获取投资需求表id
     *
     * @return investor_demand_id - 投资需求表id
     */
    public Integer getInvestorDemandId() {
        return investorDemandId;
    }

    /**
     * 设置投资需求表id
     *
     * @param investorDemandId 投资需求表id
     */
    public void setInvestorDemandId(Integer investorDemandId) {
        this.investorDemandId = investorDemandId;
    }
}