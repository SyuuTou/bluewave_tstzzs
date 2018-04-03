package com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditdetaildto;

/**
 * Created by lanhaijulang on 2018/4/2.
 */
public class InvestorAuditOutputDto {

    /**
     * 投资人id
     */
    private Integer investorId;

    /**
     * 审核状态;0:审核未通过；1：审核通过；2：待审核
     */
    private Integer approvalStatus;


    public Integer getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Integer investorId) {
        this.investorId = investorId;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
