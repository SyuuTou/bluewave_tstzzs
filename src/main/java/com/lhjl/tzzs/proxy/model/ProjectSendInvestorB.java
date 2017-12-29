package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "project_send_investor_b")
public class ProjectSendInvestorB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目历史融资表id
     */
    @Column(name = "ps_financing_history_b_id")
    private Integer psFinancingHistoryBId;

    /**
     * 投资方名称
     */
    @Column(name = "investor_name")
    private String investorName;

    /**
     * 股份占比
     */
    @Column(name = "stock_ratio")
    private BigDecimal stockRatio;

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
     * 获取提交项目历史融资表id
     *
     * @return ps_financing_history_b_id - 提交项目历史融资表id
     */
    public Integer getPsFinancingHistoryBId() {
        return psFinancingHistoryBId;
    }

    /**
     * 设置提交项目历史融资表id
     *
     * @param psFinancingHistoryBId 提交项目历史融资表id
     */
    public void setPsFinancingHistoryBId(Integer psFinancingHistoryBId) {
        this.psFinancingHistoryBId = psFinancingHistoryBId;
    }

    /**
     * 获取投资方名称
     *
     * @return investor_name - 投资方名称
     */
    public String getInvestorName() {
        return investorName;
    }

    /**
     * 设置投资方名称
     *
     * @param investorName 投资方名称
     */
    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    /**
     * 获取股份占比
     *
     * @return stock_ratio - 股份占比
     */
    public BigDecimal getStockRatio() {
        return stockRatio;
    }

    /**
     * 设置股份占比
     *
     * @param stockRatio 股份占比
     */
    public void setStockRatio(BigDecimal stockRatio) {
        this.stockRatio = stockRatio;
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