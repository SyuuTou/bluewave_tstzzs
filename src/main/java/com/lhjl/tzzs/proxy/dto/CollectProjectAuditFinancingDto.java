package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
public class CollectProjectAuditFinancingDto {

    private Integer projectId;

    private String financingStage;

    private BigDecimal financingAmount;

    private String shareStock;

    private Integer currencyType;

    private String financingUse;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getFinancingStage() {
        return financingStage;
    }

    public void setFinancingStage(String financingStage) {
        this.financingStage = financingStage;
    }

    public BigDecimal getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(BigDecimal financingAmount) {
        this.financingAmount = financingAmount;
    }

    public String getShareStock() {
        return shareStock;
    }

    public void setShareStock(String shareStock) {
        this.shareStock = shareStock;
    }

    public Integer getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(Integer currencyType) {
        this.currencyType = currencyType;
    }

    public String getFinancingUse() {
        return financingUse;
    }

    public void setFinancingUse(String financingUse) {
        this.financingUse = financingUse;
    }
}
