package com.lhjl.tzzs.proxy.dto;

public class FinancingLogDelInputDto {
	/**
	 * 融资历史的记录
	 */
	private Integer logId;
	/**
	 * 删除状态：0标识有效，1标识无效
	 */
	private Integer delStatus;


	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "FinancingLogDelInputDto [logId=" + logId + ", delStatus=" + delStatus + "]";
	}
	
}
