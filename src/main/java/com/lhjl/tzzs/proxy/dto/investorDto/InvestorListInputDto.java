package com.lhjl.tzzs.proxy.dto.investorDto;

import java.util.Date;

public class InvestorListInputDto {
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
    private Integer amountBeginRmb;
    /**
     * 单笔投资金额最高值（人民币）
     */
    private Integer amountEndRmb;
    /**
     * 单笔投资金额最低值（美元）
     */
    private Integer amountBeginDollar;
    /**
     * 单笔投资金额最高值（美元）
     */
    private Integer amountEndDollar;
    
  //以下是筛选字段
    /**
     * 负责人
     */
    /**
     * 来源类型
     */
    private Integer typeName;
    /**
     * 合作等级
     */
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
	public Integer getAmountBeginRmb() {
		return amountBeginRmb;
	}
	public void setAmountBeginRmb(Integer amountBeginRmb) {
		this.amountBeginRmb = amountBeginRmb;
	}
	public Integer getAmountEndRmb() {
		return amountEndRmb;
	}
	public void setAmountEndRmb(Integer amountEndRmb) {
		this.amountEndRmb = amountEndRmb;
	}
	public Integer getAmountBeginDollar() {
		return amountBeginDollar;
	}
	public void setAmountBeginDollar(Integer amountBeginDollar) {
		this.amountBeginDollar = amountBeginDollar;
	}
	public Integer getAmountEndDollar() {
		return amountEndDollar;
	}
	public void setAmountEndDollar(Integer amountEndDollar) {
		this.amountEndDollar = amountEndDollar;
	}
	public Integer getTypeName() {
		return typeName;
	}
	public void setTypeName(Integer typeName) {
		this.typeName = typeName;
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
	@Override
	public String toString() {
		return "InvestorListInputDto [keyWords=" + keyWords + ", startTimeStr=" + startTimeStr + ", startTime="
				+ startTime + ", endTimeStr=" + endTimeStr + ", endTime=" + endTime + ", amountBeginRmb="
				+ amountBeginRmb + ", amountEndRmb=" + amountEndRmb + ", amountBeginDollar=" + amountBeginDollar
				+ ", amountEndDollar=" + amountEndDollar + ", typeName=" + typeName + ", type=" + type + ", currency="
				+ currency + ", column=" + column + ", order=" + order + ", currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", start=" + start + "]";
	}
    
	
}
