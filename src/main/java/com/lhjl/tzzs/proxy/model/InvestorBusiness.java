package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investor_business")
public class InvestorBusiness {
    /**
     * 投资人id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 投资人创业经历
     */
    private String business;

    /**
     * 获取投资人id
     *
     * @return id - 投资人id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置投资人id
     *
     * @param id 投资人id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取投资人创业经历
     *
     * @return business - 投资人创业经历
     */
    public String getBusiness() {
        return business;
    }

    /**
     * 设置投资人创业经历
     *
     * @param business 投资人创业经历
     */
    public void setBusiness(String business) {
        this.business = business;
    }
}