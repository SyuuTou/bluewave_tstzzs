package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investor_demand_character")
public class InvestorDemandCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 特质
     */
    @Column(name = "`character`")
    private String character;

    /**
     * 投资需求表id
     */
    @Column(name = "investor_demand_id")
    private Integer investorDemandId;

    /**
     * 应用id
     */
    private Integer appid;

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
     * 获取特质
     *
     * @return character - 特质
     */
    public String getCharacter() {
        return character;
    }

    /**
     * 设置特质
     *
     * @param character 特质
     */
    public void setCharacter(String character) {
        this.character = character;
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

    /**
     * 获取应用id
     *
     * @return appid - 应用id
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * 设置应用id
     *
     * @param appid 应用id
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }
}