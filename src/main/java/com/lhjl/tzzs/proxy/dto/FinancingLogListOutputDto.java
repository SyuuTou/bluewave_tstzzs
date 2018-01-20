package com.lhjl.tzzs.proxy.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
/**
 * 融资历史的猎豹返回的历输出结构
 * @author IdataVC
 *
 */
public class FinancingLogListOutputDto {
	/**
	 * 投资的轮次
	 */
	private String stage;
	/**
	 * 投资时间
	 */
	private Date financingTime;
	/**
	 * 本轮投资总金额
	 */
	private BigDecimal total_amount;
	/**
	 * 投资币种（0人民币/1美元）
	 */
	private Integer currency;
	/**
	 * 总出让股份
	 */
	private String shareDivest;
	/**
	 * 估值
	 */
	private BigDecimal valuation;
	/**
	 * PR投资总金额
	 */
	private BigDecimal prAmount;
	/**
	 * 相关投资方说明
	 */
	private String proportionList;
	/**
	 * 关联的相关机构
	 */
	@Transient
	private List<InvestmentInstitutions> institutions;
	
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public Date getFinancingTime() {
		return financingTime;
	}
	public void setFinancingTime(Date financingTime) {
		this.financingTime = financingTime;
	}
	public BigDecimal getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
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
	public BigDecimal getPrAmount() {
		return prAmount;
	}
	public void setPrAmount(BigDecimal prAmount) {
		this.prAmount = prAmount;
	}
	public String getProportionList() {
		return proportionList;
	}
	public void setProportionList(String proportionList) {
		this.proportionList = proportionList;
	}
	
	public List<InvestmentInstitutions> getInstitutions() {
		return institutions;
	}
	public void setInstitutions(List<InvestmentInstitutions> institutions) {
		this.institutions = institutions;
	}
	@Override
	public String toString() {
		return "FinancingLogListOutputDto [stage=" + stage + ", financingTime=" + financingTime + ", total_amount="
				+ total_amount + ", currency=" + currency + ", shareDivest=" + shareDivest + ", valuation=" + valuation
				+ ", prAmount=" + prAmount + ", proportionList=" + proportionList + ", institutions=" + institutions
				+ "]";
	}
	
}
