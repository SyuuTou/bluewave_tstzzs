package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investor_selfdef_city")
public class InvestorSelfdefCity {
    /**
     * 投资人id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 自定义所在城市
     */
    @Column(name = "self_def_city")
    private String selfDefCity;

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
     * 获取自定义所在城市
     *
     * @return self_def_city - 自定义所在城市
     */
    public String getSelfDefCity() {
        return selfDefCity;
    }

    /**
     * 设置自定义所在城市
     *
     * @param selfDefCity 自定义所在城市
     */
    public void setSelfDefCity(String selfDefCity) {
        this.selfDefCity = selfDefCity;
    }
}