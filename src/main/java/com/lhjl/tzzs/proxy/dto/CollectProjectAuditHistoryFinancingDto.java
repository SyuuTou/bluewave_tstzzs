package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
public class CollectProjectAuditHistoryFinancingDto {

    private Integer historyFinancingId;

    private String stage;

    private String investTime;

    private BigDecimal investAmount;

    private Integer currencyType;

    private BigDecimal shareStockPer;

    private BigDecimal assessmentAmount;

    private BigDecimal prInvestAmount;

    private String[] investors;

    private String investorsDesc;

    private List<CollectInvestorDto> collectInvestorDtoList;

    public BigDecimal getAssessmentAmount() {
        return assessmentAmount;
    }

    public void setAssessmentAmount(BigDecimal assessmentAmount) {
        this.assessmentAmount = assessmentAmount;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Integer getHistoryFinancingId() {
        return historyFinancingId;
    }

    public void setHistoryFinancingId(Integer historyFinancingId) {
        this.historyFinancingId = historyFinancingId;
    }

    public String getInvestTime() {
        return investTime;
    }

    public void setInvestTime(String investTime) {
        this.investTime = investTime;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public Integer getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(Integer currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getShareStockPer() {
        return shareStockPer;
    }

    public void setShareStockPer(BigDecimal shareStockPer) {
        this.shareStockPer = shareStockPer;
    }

    public BigDecimal getPrInvestAmount() {
        return prInvestAmount;
    }

    public void setPrInvestAmount(BigDecimal prInvestAmount) {
        this.prInvestAmount = prInvestAmount;
    }

    public String[] getInvestors() {
        return investors;
    }

    public void setInvestors(String[] investors) {
        this.investors = investors;
    }

    public String getInvestorsDesc() {
        return investorsDesc;
    }

    public void setInvestorsDesc(String investorsDesc) {
        this.investorsDesc = investorsDesc;
    }

    public List<CollectInvestorDto> getCollectInvestorDtoList() {
        return collectInvestorDtoList;
    }

    public void setCollectInvestorDtoList(List<CollectInvestorDto> collectInvestorDtoList) {
        this.collectInvestorDtoList = collectInvestorDtoList;
    }

    public static class CollectInvestorDto{

        private Integer investorId;

        private String investorShortName;

        private String investorRemark;

        /**
         * 是否是领投方
         */
        private String isLeadInvest;

        private BigDecimal investAmount;

        private Integer currency;

        private BigDecimal stockPer;

        /**
         * 到账日期
         */
        private String onAccountDate;

        public Integer getInvestorId() {
            return investorId;
        }

        public void setInvestorId(Integer investorId) {
            this.investorId = investorId;
        }

        public String getInvestorShortName() {
            return investorShortName;
        }

        public void setInvestorShortName(String investorShortName) {
            this.investorShortName = investorShortName;
        }

        public String getInvestorRemark() {
            return investorRemark;
        }

        public void setInvestorRemark(String investorRemark) {
            this.investorRemark = investorRemark;
        }

        public String getIsLeadInvest() {
            return isLeadInvest;
        }

        public void setIsLeadInvest(String isLeadInvest) {
            this.isLeadInvest = isLeadInvest;
        }

        public BigDecimal getInvestAmount() {
            return investAmount;
        }

        public void setInvestAmount(BigDecimal investAmount) {
            this.investAmount = investAmount;
        }

        public Integer getCurrency() {
            return currency;
        }

        public void setCurrency(Integer currency) {
            this.currency = currency;
        }

        public BigDecimal getStockPer() {
            return stockPer;
        }

        public void setStockPer(BigDecimal stockPer) {
            this.stockPer = stockPer;
        }

        public String getOnAccountDate() {
            return onAccountDate;
        }

        public void setOnAccountDate(String onAccountDate) {
            this.onAccountDate = onAccountDate;
        }
    }
}
