package com.lhjl.tzzs.proxy.dto.FinancingLogDto;

import java.math.BigDecimal;

/**
 * Created by lanhaijulang on 2018/1/20.
 */
public class FinancingLogInputDto {
	
    private Integer id;

    private Integer projectId;

    private String stage;

    private BigDecimal amount;

    private Integer currencyType;

    private String shareDivest;
    /**
     * 融资需求
     */
    private String financingApplication;
    /**
     * 主体类型 
     */
    private Integer subjectType;
    
    public Integer getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getFinancingApplication() {
        return financingApplication;
    }

    public void setFinancingApplication(String financingApplication) {
        this.financingApplication = financingApplication;
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

    public Integer getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(Integer currencyType) {
        this.currencyType = currencyType;
    }

    public String getShareDivest() {
        return shareDivest;
    }

    public void setShareDivest(String shareDivest) {
        this.shareDivest = shareDivest;
    }
}
