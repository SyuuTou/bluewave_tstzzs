package com.lhjl.tzzs.proxy.dto.investorDto;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public class InvestorOperateLogDto {
    private Long id;

    private Integer investorId;

    private String operator;

    private String operateContent;

    private String operateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Integer investorId) {
        this.investorId = investorId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
}
