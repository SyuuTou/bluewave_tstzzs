package com.lhjl.tzzs.proxy.dto.investorDto;

import java.math.BigDecimal;
import java.util.Date;

public class InvestorListInputDto {
	/**
	 * userId
	 */
	private Integer userId;
	/**
	 * 类型
	 */
	private Integer adminType;
	/**
	 * 类型
	 */
	private String adminName;
	/**
     * 模糊搜索关键字
     */
    private String keyWords;
    /**
     * 开始时间字符串
     */
    private String startTimeStr;
    /**
     * 开始时间
     * 用于接受开始时间字符串的转换
     */
    private Date startTime;
    /**
     * 结束时间字符串
     */
    private String endTimeStr;
    /**
     * 结束时间
     * 用于结束时间字符串的转换
     */
    private Date endTime;
    /**
     * 单笔投资金额最低值（人民币）
     */
    private BigDecimal amountBeginRmb;
    /**
     * 单笔投资金额最高值（人民币）
     */
    private BigDecimal amountEndRmb;
    /**
     * 单笔投资金额最低值（美元）
     */
    private BigDecimal amountBeginDollar;
    /**
     * 单笔投资金额最高值（美元）
     */
    private BigDecimal amountEndDollar;
    
  //以下是筛选字段
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
     * 默认按照更新时间进行排序
     * 此处根据实际情况可考虑将列值传递过来
     * 但是应该考虑到一个问题，就是如何将该列和排序的标志位结合起来
     * 此处传递的是 create_time,update_time这两个值
     */
    private String column;

    /**
     * 排序方式：升序asc;降序desc
     * 如果单纯的传递这一个值的话 那肯定是根据某列进行排序，则Mapper.xml直接将排序字段写死即可
     * 
     */
    private String order;
    
    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 每页显示数量
     */
    private Integer pageSize;
    /**
     * 起始索引
     */
    private Long start;
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getStartTimeStr() {
		return startTimeStr;
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getEndTimeStr() {
		return endTimeStr;
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public BigDecimal getAmountBeginRmb() {
		return amountBeginRmb;
	}
	public void setAmountBeginRmb(BigDecimal amountBeginRmb) {
		this.amountBeginRmb = amountBeginRmb;
	}
	public BigDecimal getAmountEndRmb() {
		return amountEndRmb;
	}
	public void setAmountEndRmb(BigDecimal amountEndRmb) {
		this.amountEndRmb = amountEndRmb;
	}
	public BigDecimal getAmountBeginDollar() {
		return amountBeginDollar;
	}
	public void setAmountBeginDollar(BigDecimal amountBeginDollar) {
		this.amountBeginDollar = amountBeginDollar;
	}
	public BigDecimal getAmountEndDollar() {
		return amountEndDollar;
	}
	public void setAmountEndDollar(BigDecimal amountEndDollar) {
		this.amountEndDollar = amountEndDollar;
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
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getAdminType() {
		return adminType;
	}
	public void setAdminType(Integer adminType) {
		this.adminType = adminType;
	}
	
	
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	@Override
	public String toString() {
		return "InvestorListInputDto [userId=" + userId + ", adminType=" + adminType + ", adminName=" + adminName
				+ ", keyWords=" + keyWords + ", startTimeStr=" + startTimeStr + ", startTime=" + startTime
				+ ", endTimeStr=" + endTimeStr + ", endTime=" + endTime + ", amountBeginRmb=" + amountBeginRmb
				+ ", amountEndRmb=" + amountEndRmb + ", amountBeginDollar=" + amountBeginDollar + ", amountEndDollar="
				+ amountEndDollar + ", weChatGroupId=" + weChatGroupId + ", irPrincipal=" + irPrincipal + ", typeName="
				+ typeName + ", cooperativeRelationship=" + cooperativeRelationship + ", type=" + type + ", currency="
				+ currency + ", column=" + column + ", order=" + order + ", currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", start=" + start + "]";
	}
	


	
}
