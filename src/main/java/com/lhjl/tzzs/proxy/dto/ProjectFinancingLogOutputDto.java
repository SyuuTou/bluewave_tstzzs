package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectFinancingLogOutputDto {

	 /**投资事件主键id*/
    private Integer id;
    
    /**投资事件采集编号*/
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
    private Date financingTime;
    
    /**融资时间输出字符串*/
    private String financingTimeOutputStr;

    /**相关投资机构简称*/
    private String InvestmentInstitutionsList;

    /**相关投资机构说明*/
    private String proportionList;

    /**提交时间*/
    private Date createTime;
    
    /**提交时间输出字符串*/
    private String createTimeOutputStr;

    /**更新时间*/
    private Date updateTime;
    
    /**更新时间输出字符串*/
    private String updateTimeOutputStr;
    
//    以下字段供审核列表使用
    /**
     * 审核结果
     * '审核状态，0表示审核未通过，1表示审核通过，2表示待审核，默认0'
     */
    private Integer approvalStatus;
    /**
     * 审核时间
     */
    private Date approvalTime;
    /**
     * 审核时间输出字符串
     */
    private String approvalTimeOutputStr;
    /**
     * 提交人token
     */
//    private String submitter;
    /**
     * 提交人id
     */
    private Integer userId;
    /**
     * 提交人姓名
     */
    private String actualName;
    

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getActualName() {
		return actualName;
	}

	public void setActualName(String actualName) {
		this.actualName = actualName;
	}

//	public String getSubmitter() {
//		return submitter;
//	}
//
//	public void setSubmitter(String submitter) {
//		this.submitter = submitter;
//	}

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public String getApprovalTimeOutputStr() {
		return approvalTimeOutputStr;
	}

	public void setApprovalTimeOutputStr(String approvalTimeOutputStr) {
		this.approvalTimeOutputStr = approvalTimeOutputStr;
	}

	public Integer getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Date getFinancingTime() {
		return financingTime;
	}

	public void setFinancingTime(Date financingTime) {
		this.financingTime = financingTime;
	}

	public String getFinancingTimeOutputStr() {
		return financingTimeOutputStr;
	}

	public void setFinancingTimeOutputStr(String financingTimeOutputStr) {
		this.financingTimeOutputStr = financingTimeOutputStr;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeOutputStr() {
		return createTimeOutputStr;
	}

	public void setCreateTimeOutputStr(String createTimeOutputStr) {
		this.createTimeOutputStr = createTimeOutputStr;
	}


	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateTimeOutputStr() {
		return updateTimeOutputStr;
	}

	public void setUpdateTimeOutputStr(String updateTimeOutputStr) {
		this.updateTimeOutputStr = updateTimeOutputStr;
	}

	@Override
	public String toString() {
		return "ProjectFinancingLogOutputDto [id=" + id + ", serialNumber=" + serialNumber + ", typeName=" + typeName
				+ ", investmentInstitutionId=" + investmentInstitutionId + ", institutionName=" + institutionName
				+ ", projectId=" + projectId + ", projectName=" + projectName + ", segmentationName=" + segmentationName
				+ ", stage=" + stage + ", amount=" + amount + ", currency=" + currency + ", shareDivest=" + shareDivest
				+ ", valuation=" + valuation + ", totalAmount=" + totalAmount + ", prAmount=" + prAmount
				+ ", financingTime=" + financingTime + ", financingTimeOutputStr=" + financingTimeOutputStr
				+ ", InvestmentInstitutionsList=" + InvestmentInstitutionsList + ", proportionList=" + proportionList
				+ ", createTime=" + createTime + ", createTimeOutputStr=" + createTimeOutputStr + ", updateTime="
				+ updateTime + ", updateTimeOutputStr=" + updateTimeOutputStr + ", approvalStatus=" + approvalStatus
				+ ", approvalTime=" + approvalTime + ", approvalTimeOutputStr=" + approvalTimeOutputStr + ", userId="
				+ userId + ", actualName=" + actualName + "]";
	}


}
