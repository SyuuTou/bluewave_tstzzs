package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;

public class ProjectSendInvestorDto {
    /**融资历史表id*/
    private Integer projectFinancingHistoryId;

    /**投资人类型*/
    private String investorName;

    /**股份占比*/
    private BigDecimal shareRatio;

    public Integer getProjectFinancingHistoryId() {
        return projectFinancingHistoryId;
    }

    public void setProjectFinancingHistoryId(Integer projectFinancingHistoryId) {
        this.projectFinancingHistoryId = projectFinancingHistoryId;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public BigDecimal getShareRatio() {
        return shareRatio;
    }

    public void setShareRatio(BigDecimal shareRatio) {
        this.shareRatio = shareRatio;
    }
}
