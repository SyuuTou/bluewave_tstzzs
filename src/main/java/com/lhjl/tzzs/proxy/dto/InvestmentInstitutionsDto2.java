package com.lhjl.tzzs.proxy.dto;


import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsAddressPart;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zd on 2018/1/4.
 */
public class InvestmentInstitutionsDto2 {
	/**
     * 机构logo
     */
	private String logo;
	
    /**
     * 机构简称
     */
    private String shortName;
    /**
     * 机构全名
     */
    private String fullName;
    /**
     * 一句话介绍
     */
    private String kenelCase;
    /**
     * 详细简介
     */
    private String comment;


	/**
     * 投资阶段
     */
    private Integer[] stages;
    
    /**
     * 投资领域
     */
    private Integer[] segmentations;
    
    /**
     * 自定义领域
     */
    private String[] newSegmentations;
    
    /**
     * 官网地址
     */
    private String homeUrl;
    
    /**  以下属于基金的范畴
     * 总基金管理规模
     */
    private BigDecimal totalFundScale;
    /**
     * 人民币基金管理规模
     */
    private BigDecimal rmbFundScale;
    /**
     * 美元基金管理规模
     */
    private BigDecimal dollarFundScale;
    /**
     * 人民币单笔投资最小值
     */
    private BigDecimal rmbInvestAmountMin;
    /**
     * 人人民币单笔投资最大值
     */
    private BigDecimal rmbInvestAmountMax;
    /**
     * 美元单笔投资最小值
     */
    private BigDecimal dollarInvestAmountMin;
    /**
     * 美元单笔投资最大值
     */
    private BigDecimal dollarInvestAmountMax;
    /**
     * 投资理念
     */
    private String investmentIdea;
    /**
     * 项目需求
     */
    private String productRequirement;
    /**
     * 招聘需求
     */
    private String recruitmentRequirement;
    
    
    /** 以下属于公司总部地址信息
     * 所在城市
     */
    private String Town;
    
    /**
     * 详细地址
     */
    private String detailAddress;
    
    /**
     * 公司邮箱
     */
    private String email;
    /**
     * BP邮箱
     */
    private String bpEmail;
    /**
     * 总部经度
     */
    private BigDecimal longitude;
    
    /**
     * 总部纬度
     */
    private BigDecimal latitude;
    
    /** 
     * 以下属于公司分部List的信息
     * 
     */
    private List<InvestmentInstitutionsAddressPart> investmentInstitutionsAddressParts;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getKenelCase() {
		return kenelCase;
	}

	public void setKenelCase(String kenelCase) {
		this.kenelCase = kenelCase;
	}
	
    public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer[] getStages() {
		return stages;
	}

	public void setStages(Integer[] stages) {
		this.stages = stages;
	}

	public Integer[] getSegmentations() {
		return segmentations;
	}

	public void setSegmentations(Integer[] segmentations) {
		this.segmentations = segmentations;
	}

	public String[] getNewSegmentations() {
		return newSegmentations;
	}

	public void setNewSegmentations(String[] newSegmentations) {
		this.newSegmentations = newSegmentations;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	public BigDecimal getTotalFundScale() {
		return totalFundScale;
	}

	public void setTotalFundScale(BigDecimal totalFundScale) {
		this.totalFundScale = totalFundScale;
	}

	public BigDecimal getRmbFundScale() {
		return rmbFundScale;
	}

	public void setRmbFundScale(BigDecimal rmbFundScale) {
		this.rmbFundScale = rmbFundScale;
	}

	public BigDecimal getDollarFundScale() {
		return dollarFundScale;
	}

	public void setDollarFundScale(BigDecimal dollarFundScale) {
		this.dollarFundScale = dollarFundScale;
	}

	public BigDecimal getRmbInvestAmountMin() {
		return rmbInvestAmountMin;
	}

	public void setRmbInvestAmountMin(BigDecimal rmbInvestAmountMin) {
		this.rmbInvestAmountMin = rmbInvestAmountMin;
	}

	public BigDecimal getRmbInvestAmountMax() {
		return rmbInvestAmountMax;
	}

	public void setRmbInvestAmountMax(BigDecimal rmbInvestAmountMax) {
		this.rmbInvestAmountMax = rmbInvestAmountMax;
	}

	public BigDecimal getDollarInvestAmountMin() {
		return dollarInvestAmountMin;
	}

	public void setDollarInvestAmountMin(BigDecimal dollarInvestAmountMin) {
		this.dollarInvestAmountMin = dollarInvestAmountMin;
	}

	public BigDecimal getDollarInvestAmountMax() {
		return dollarInvestAmountMax;
	}

	public void setDollarInvestAmountMax(BigDecimal dollarInvestAmountMax) {
		this.dollarInvestAmountMax = dollarInvestAmountMax;
	}

	public String getInvestmentIdea() {
		return investmentIdea;
	}

	public void setInvestmentIdea(String investmentIdea) {
		this.investmentIdea = investmentIdea;
	}

	public String getProductRequirement() {
		return productRequirement;
	}

	public void setProductRequirement(String productRequirement) {
		this.productRequirement = productRequirement;
	}

	public String getRecruitmentRequirement() {
		return recruitmentRequirement;
	}

	public void setRecruitmentRequirement(String recruitmentRequirement) {
		this.recruitmentRequirement = recruitmentRequirement;
	}

	public String getTown() {
		return Town;
	}

	public void setTown(String town) {
		Town = town;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBpEmail() {
		return bpEmail;
	}

	public void setBpEmail(String bpEmail) {
		this.bpEmail = bpEmail;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public List<InvestmentInstitutionsAddressPart> getInvestmentInstitutionsAddressParts() {
		return investmentInstitutionsAddressParts;
	}

	public void setInvestmentInstitutionsAddressParts(
			List<InvestmentInstitutionsAddressPart> investmentInstitutionsAddressParts) {
		this.investmentInstitutionsAddressParts = investmentInstitutionsAddressParts;
	}

	@Override
	public String toString() {
		return "InvestmentInstitutionsDto2 [logo=" + logo + ", shortName=" + shortName + ", fullName=" + fullName
				+ ", kenelCase=" + kenelCase + ", stages=" + Arrays.toString(stages) + ", segmentations="
				+ Arrays.toString(segmentations) + ", newSegmentations=" + Arrays.toString(newSegmentations)
				+ ", homeUrl=" + homeUrl + ", totalFundScale=" + totalFundScale + ", rmbFundScale=" + rmbFundScale
				+ ", dollarFundScale=" + dollarFundScale + ", rmbInvestAmountMin=" + rmbInvestAmountMin
				+ ", rmbInvestAmountMax=" + rmbInvestAmountMax + ", dollarInvestAmountMin=" + dollarInvestAmountMin
				+ ", dollarInvestAmountMax=" + dollarInvestAmountMax + ", investmentIdea=" + investmentIdea
				+ ", productRequirement=" + productRequirement + ", recruitmentRequirement=" + recruitmentRequirement
				+ ", Town=" + Town + ", detailAddress=" + detailAddress + ", email=" + email + ", bpEmail=" + bpEmail
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", investmentInstitutionsAddressParts="
				+ investmentInstitutionsAddressParts + "]";
	}

	
    
}

