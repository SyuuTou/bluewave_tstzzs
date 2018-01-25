package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_currency_type")
public class MetaCurrencyType {
    /**
     * 币种类型ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 币种名称
     */
    @Column(name = "currency_type")
    private String currencyType;

    /**
     * 获取币种类型ID
     *
     * @return id - 币种类型ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置币种类型ID
     *
     * @param id 币种类型ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取币种名称
     *
     * @return currency_type - 币种名称
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * 设置币种名称
     *
     * @param currencyType 币种名称
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }
}