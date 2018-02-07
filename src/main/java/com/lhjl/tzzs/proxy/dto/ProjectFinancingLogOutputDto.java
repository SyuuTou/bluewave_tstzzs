package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;

public class ProjectFinancingLogOutputDto {

	 /**投资事件主键id*/
    private Integer id;
    
    /**投资事件编号*/
    private Integer serialNumber;

    /**来源类型*/
    private String typeName;

    /**投资机构id*/
    private Integer investmentInstitutionId;

    /**投资机构简称*/
    private String institutionName;

    /**项目id*/
    private Integer projectId;

    /**项目简称*/
    private String projectName;

    /**领域名称*/
    private String segmentationName;

    /**轮次*/
    private String stage;

    /**投资金额*/
    private BigDecimal amount;

    /**币种*/
    private Integer currency;

    /**所占股份*/
    private String shareDivest;

    /**本轮估值*/
    private BigDecimal valuation;

    /**本轮总融资金额*/
    private BigDecimal totalAmount;

    /**PR总融资金额*/
    private BigDecimal prAmount;

    /**融资时间*/
    private String financingTime;

    /**相关投资机构简称*/
    private String InvestmentInstitutionsList;

    /**相关投资机构说明*/
    private String proportionList;

    /**提交时间*/
    private String createTime;

    /**更新时间*/
    private String updateTime;

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getInvestmentInstitutionId() {
        return investmentInstitutionId;
    }

    public void setInvestmentInstitutionId(Integer investmentInstitutionId) {
        this.investmentInstitutionId = investmentInstitutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSegmentationName() {
        return segmentationName;
    }

    public void setSegmentationName(String segmentationName) {
        this.segmentationName = segmentationName;
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

    public String getShareDivest() {
        return shareDivest;
    }

    public void setShareDivest(String shareDivest) {
        this.shareDivest = shareDivest;
    }

    public BigDecimal getValuation() {
        return valuation;
    }

    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPrAmount() {
        return prAmount;
    }

    public void setPrAmount(BigDecimal prAmount) {
        this.prAmount = prAmount;
    }

    public String getFinancingTime() {
        return financingTime;
    }

    public void setFinancingTime(String financingTime) {
        this.financingTime = financingTime;
    }

    public String getInvestmentInstitutionsList() {
        return InvestmentInstitutionsList;
    }

    public void setInvestmentInstitutionsList(String investmentInstitutionsList) {
        InvestmentInstitutionsList = investmentInstitutionsList;
    }

    public String getProportionList() {
        return proportionList;
    }

    public void setProportionList(String proportionList) {
        this.proportionList = proportionList;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ProjectFinancingLogOutputDto [id=" + id + ", serialNumber=" + serialNumber + ", typeName=" + typeName
				+ ", investmentInstitutionId=" + investmentInstitutionId + ", institutionName=" + institutionName
				+ ", projectId=" + projectId + ", projectName=" + projectName + ", segmentationName=" + segmentationName
				+ ", stage=" + stage + ", amount=" + amount + ", currency=" + currency + ", shareDivest=" + shareDivest
				+ ", valuation=" + valuation + ", totalAmount=" + totalAmount + ", prAmount=" + prAmount
				+ ", financingTime=" + financingTime + ", InvestmentInstitutionsList=" + InvestmentInstitutionsList
				+ ", proportionList=" + proportionList + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
    
}
