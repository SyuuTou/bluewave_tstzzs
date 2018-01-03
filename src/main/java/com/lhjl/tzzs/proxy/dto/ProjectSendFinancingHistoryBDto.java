package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProjectSendFinancingHistoryBDto {
    /**提交项目id*/
    private Integer projectSendBId;

    /**轮次*/
    private String stage;

    /**融资金额*/
    private BigDecimal amount;

    /**融资金额单位，投资币种（0人民币/1美元）*/
    private Integer currency;

    /**估值金额*/
    private BigDecimal totalAmount;

    /**估值金额单位，投资币种（0人民币/1美元）*/
    private Integer totalAmountCurrency;

    /**融资时间*/
    private String financingTime;

    /**投资方*/
    private List<ProjectSendInvestorDto> investor;

    public Integer getProjectSendBId() {
        return projectSendBId;
    }

    public void setProjectSendBId(Integer projectSendBId) {
        this.projectSendBId = projectSendBId;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalAmountCurrency() {
        return totalAmountCurrency;
    }

    public void setTotalAmountCurrency(Integer totalAmountCurrency) {
        this.totalAmountCurrency = totalAmountCurrency;
    }

    public String getFinancingTime() {
        return financingTime;
    }

    public void setFinancingTime(String financingTime) {
        this.financingTime = financingTime;
    }

    public List<ProjectSendInvestorDto> getInvestor() {
        return investor;
    }

    public void setInvestor(List<ProjectSendInvestorDto> investor) {
        this.investor = investor;
    }
}
