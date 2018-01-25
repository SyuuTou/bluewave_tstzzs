package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "investment_institution_single_amount")
public class InvestmentInstitutionSingleAmount {
    /**
     * 公司ID
     */
    @Id
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 货币Id
     */
    @Column(name = "currency_id")
    private Integer currencyId;

    /**
     * 单笔投资金额下限
     */
    @Column(name = "investment_amount_single_low")
    private BigDecimal investmentAmountSingleLow;

    /**
     * 单笔投资金额上限
     */
    @Column(name = "investment_amount_single_high")
    private BigDecimal investmentAmountSingleHigh;

    /**
     * 获取公司ID
     *
     * @return company_id - 公司ID
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司ID
     *
     * @param companyId 公司ID
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取货币Id
     *
     * @return currency_id - 货币Id
     */
    public Integer getCurrencyId() {
        return currencyId;
    }

    /**
     * 设置货币Id
     *
     * @param currencyId 货币Id
     */
    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * 获取单笔投资金额下限
     *
     * @return investment_amount_single_low - 单笔投资金额下限
     */
    public BigDecimal getInvestmentAmountSingleLow() {
        return investmentAmountSingleLow;
    }

    /**
     * 设置单笔投资金额下限
     *
     * @param investmentAmountSingleLow 单笔投资金额下限
     */
    public void setInvestmentAmountSingleLow(BigDecimal investmentAmountSingleLow) {
        this.investmentAmountSingleLow = investmentAmountSingleLow;
    }

    /**
     * 获取单笔投资金额上限
     *
     * @return investment_amount_single_high - 单笔投资金额上限
     */
    public BigDecimal getInvestmentAmountSingleHigh() {
        return investmentAmountSingleHigh;
    }

    /**
     * 设置单笔投资金额上限
     *
     * @param investmentAmountSingleHigh 单笔投资金额上限
     */
    public void setInvestmentAmountSingleHigh(BigDecimal investmentAmountSingleHigh) {
        this.investmentAmountSingleHigh = investmentAmountSingleHigh;
    }
}