package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investor_investment_case")
public class InvestorInvestmentCase {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 投资人id
     */
    @Column(name = "investor_id")
    private Integer investorId;

    /**
     * 投资案例
     */
    @Column(name = "investment_case")
    private String investmentCase;

    /**
     * @return ID
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
     * 获取投资人id
     *
     * @return investor_id - 投资人id
     */
    public Integer getInvestorId() {
        return investorId;
    }

    /**
     * 设置投资人id
     *
     * @param investorId 投资人id
     */
    public void setInvestorId(Integer investorId) {
        this.investorId = investorId;
    }

    /**
     * 获取投资案例
     *
     * @return investment_case - 投资案例
     */
    public String getInvestmentCase() {
        return investmentCase;
    }

    /**
     * 设置投资案例
     *
     * @param investmentCase 投资案例
     */
    public void setInvestmentCase(String investmentCase) {
        this.investmentCase = investmentCase;
    }
}