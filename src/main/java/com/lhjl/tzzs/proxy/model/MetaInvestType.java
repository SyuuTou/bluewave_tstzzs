package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_invest_type")
public class MetaInvestType {
    @Id
    @Column(name = "invest_type_id")
    private Integer investTypeId;

    /**
     * 投资类型
     */
    @Column(name = "invest_type")
    private String investType;

    /**
     * @return invest_type_id
     */
    public Integer getInvestTypeId() {
        return investTypeId;
    }

    /**
     * @param investTypeId
     */
    public void setInvestTypeId(Integer investTypeId) {
        this.investTypeId = investTypeId;
    }

    /**
     * 获取投资类型
     *
     * @return invest_type - 投资类型
     */
    public String getInvestType() {
        return investType;
    }

    /**
     * 设置投资类型
     *
     * @param investType 投资类型
     */
    public void setInvestType(String investType) {
        this.investType = investType;
    }
}