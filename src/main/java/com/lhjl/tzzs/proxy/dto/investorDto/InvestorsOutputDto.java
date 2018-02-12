package com.lhjl.tzzs.proxy.dto.investorDto;

import java.util.Date;

import javax.persistence.Transient;

public class InvestorsOutputDto {
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 投资人id
	 */
	private Integer userId;
	/**
	 * 投资人姓名
	 */
	private String name;
	/**
	 * 所在机构
	 */
	private String shortName;
	/**
	 * 担任职位
	 */
	private String position;
	/**
	 * 手机号
	 */
	private String phonenumber;
	/**
	 * 群号
	 */
	private String weChatGroupId;
	/**
	 * 负责人
	 */
	private String irPrincipal;
	/**
	 * 来源类型
	 */
	private String typeName;
	/**
	 * 合作等级
	 */
	private String cooperativeRelationship;
	/**
	 * 机构分类
	 */
	private String type;
	
	/**
	 * 基金币种
	 */
	private String currency;
	
	/**
	 * 投资领域
	 */
	private String segmentations;
	/**
	 * 关注细分领域(赛道)
	 */
	private String speedways;
	/**
	 * 投资阶段
	 */
	private String stages;
	/**
	 * 单笔投资金额人民币最低
	 */
	private Double investmentAmountLow;
	/**
	 * 单笔投资金额人民币最高
	 */
	private Double investmentAmountHigh;
	/**
	 * 单笔投资金额美元最低
	 */
	private Double investmentAmountLowDollars;
	/**
	 * 单笔投资金额美元最高
	 */
	private Double investmentAmountHighDollars;
	/**
	 * 偏好描述
	 */
	private String demand;
	/**
	 * 所在城市
	 */
	private String citys;
	/**
	 * 所在城市
	 */
	private Date createTime;
	/**
	 * 所在城市
	 */
	private Date updateTime;
	/**
	 * 提交者token--供审核列表使用
	 */
	private String submitter;
	/**
	 * 采集编号--供审核列表使用
	 */
	private String serialNumber;
	/**
	 * 审核结果--供审核列表使用
	 */
	private Integer approvalStatus;
	/**
	 * 审核时间--供审核列表使用
	 */
	private Date checkTime;
	/**
	 * 审核时间输出字符串--供审核列表使用
	 */
	@Transient
	private String checkTimeOutputStr;
	/**
	 * 创建时间出输出字符串
	 */
	@Transient
	private String createTimeStr;
	/**
	 * 更新时间输出字符串
	 */
	@Transient
	private String updateTimeStr;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getWeChatGroupId() {
		return weChatGroupId;
	}
	public void setWeChatGroupId(String weChatGroupId) {
		this.weChatGroupId = weChatGroupId;
	}
	public String getIrPrincipal() {
		return irPrincipal;
	}
	public void setIrPrincipal(String irPrincipal) {
		this.irPrincipal = irPrincipal;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getCooperativeRelationship() {
		return cooperativeRelationship;
	}
	public void setCooperativeRelationship(String cooperativeRelationship) {
		this.cooperativeRelationship = cooperativeRelationship;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSegmentations() {
		return segmentations;
	}
	public void setSegmentations(String segmentations) {
		this.segmentations = segmentations;
	}
	public String getSpeedways() {
		return speedways;
	}
	public void setSpeedways(String speedways) {
		this.speedways = speedways;
	}
	public String getStages() {
		return stages;
	}
	public void setStages(String stages) {
		this.stages = stages;
	}
	public Double getInvestmentAmountLow() {
		return investmentAmountLow;
	}
	public void setInvestmentAmountLow(Double investmentAmountLow) {
		this.investmentAmountLow = investmentAmountLow;
	}
	public Double getInvestmentAmountHigh() {
		return investmentAmountHigh;
	}
	public void setInvestmentAmountHigh(Double investmentAmountHigh) {
		this.investmentAmountHigh = investmentAmountHigh;
	}
	public Double getInvestmentAmountLowDollars() {
		return investmentAmountLowDollars;
	}
	public void setInvestmentAmountLowDollars(Double investmentAmountLowDollars) {
		this.investmentAmountLowDollars = investmentAmountLowDollars;
	}
	public Double getInvestmentAmountHighDollars() {
		return investmentAmountHighDollars;
	}
	public void setInvestmentAmountHighDollars(Double investmentAmountHighDollars) {
		this.investmentAmountHighDollars = investmentAmountHighDollars;
	}
	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
	}
	public String getCitys() {
		return citys;
	}
	public void setCitys(String citys) {
		this.citys = citys;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Integer getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getCheckTimeOutputStr() {
		return checkTimeOutputStr;
	}
	public void setCheckTimeOutputStr(String checkTimeOutputStr) {
		this.checkTimeOutputStr = checkTimeOutputStr;
	}
	@Override
	public String toString() {
		return "InvestorsOutputDto [id=" + id + ", userId=" + userId + ", name=" + name + ", shortName=" + shortName
				+ ", position=" + position + ", phonenumber=" + phonenumber + ", weChatGroupId=" + weChatGroupId
				+ ", irPrincipal=" + irPrincipal + ", typeName=" + typeName + ", cooperativeRelationship="
				+ cooperativeRelationship + ", type=" + type + ", currency=" + currency + ", segmentations="
				+ segmentations + ", speedways=" + speedways + ", stages=" + stages + ", investmentAmountLow="
				+ investmentAmountLow + ", investmentAmountHigh=" + investmentAmountHigh
				+ ", investmentAmountLowDollars=" + investmentAmountLowDollars + ", investmentAmountHighDollars="
				+ investmentAmountHighDollars + ", demand=" + demand + ", citys=" + citys + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", submitter=" + submitter + ", serialNumber=" + serialNumber
				+ ", approvalStatus=" + approvalStatus + ", checkTime=" + checkTime + ", checkTimeOutputStr="
				+ checkTimeOutputStr + ", createTimeStr=" + createTimeStr + ", updateTimeStr=" + updateTimeStr + "]";
	}
	
}
