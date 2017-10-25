package com.lhjl.tzzs.proxy.dto;

/**
 * 投资审核操作
 * Created by 蓝海巨浪 on 2017/10/25.
 */
public class InvestorsApprovalActionDto {
    /**
     * 审核ID
     */
    private Integer id;
    /**
     * 审核结果
     */
    private String approveResult;
    /**
     * 补充说明
     */
    private String explanation;
    /**
     * 审核状态
     */
    private String approvalStatus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
