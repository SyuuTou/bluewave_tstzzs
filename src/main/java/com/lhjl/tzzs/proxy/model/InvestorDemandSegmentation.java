package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investor_demand_segmentation")
public class InvestorDemandSegmentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 领域
     */
    private String segmentation;

    /**
     * 融资需求表id
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
     * 获取领域
     *
     * @return segmentation - 领域
     */
    public String getSegmentation() {
        return segmentation;
    }

    /**
     * 设置领域
     *
     * @param segmentation 领域
     */
    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }

    /**
     * 获取融资需求表id
     *
     * @return investor_demand_id - 融资需求表id
     */
    public Integer getInvestorDemandId() {
        return investorDemandId;
    }

    /**
     * 设置融资需求表id
     *
     * @param investorDemandId 融资需求表id
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