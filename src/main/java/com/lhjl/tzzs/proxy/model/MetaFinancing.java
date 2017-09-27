package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_financing")
public class MetaFinancing {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 公司品牌名称
     */
    @Column(name = "company_brand")
    private String companyBrand;

    /**
     * 公司注册全称
     */
    @Column(name = "company_all_name")
    private String companyAllName;

    /**
     * 公司简介
     */
    @Column(name = "company_intro")
    private String companyIntro;

    /**
     * 成立时间
     */
    @Column(name = "established_time")
    private String establishedTime;

    /**
     * 注册地-省
     */
    @Column(name = "register_area")
    private String registerArea;

    /**
     * 主行业领域
     */
    @Column(name = "main_Industry")
    private String mainIndustry;

    /**
     * 子行业
     */
    @Column(name = "sub_industry")
    private String subIndustry;

    /**
     * Tag标签
     */
    @Column(name = "company_tag")
    private String companyTag;

    /**
     * 公司产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 最后融资时间
     */
    @Column(name = "financing_last_time")
    private String financingLastTime;

    /**
     * 轮次
     */
    private String round;

    /**
     * 融资金额
     */
    private Integer money;

    /**
     * 币种
     */
    private String currency;

    /**
     * 出让份额
     */
    private String share;

    /**
     * 估值
     */
    @Column(name = "Valuation")
    private Integer valuation;

    /**
     * 估值币种
     */
    @Column(name = "Valuation_currency")
    private String valuationCurrency;

    /**
     * 投资方
     */
    @Column(name = "Investors")
    private String investors;

    /**
     * 教育经历
     */
    @Column(name = "education_experience")
    private String educationExperience;

    /**
     * 工作经历
     */
    @Column(name = "work_experience")
    private String workExperience;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取公司品牌名称
     *
     * @return company_brand - 公司品牌名称
     */
    public String getCompanyBrand() {
        return companyBrand;
    }

    /**
     * 设置公司品牌名称
     *
     * @param companyBrand 公司品牌名称
     */
    public void setCompanyBrand(String companyBrand) {
        this.companyBrand = companyBrand;
    }

    /**
     * 获取公司注册全称
     *
     * @return company_all_name - 公司注册全称
     */
    public String getCompanyAllName() {
        return companyAllName;
    }

    /**
     * 设置公司注册全称
     *
     * @param companyAllName 公司注册全称
     */
    public void setCompanyAllName(String companyAllName) {
        this.companyAllName = companyAllName;
    }

    /**
     * 获取公司简介
     *
     * @return company_intro - 公司简介
     */
    public String getCompanyIntro() {
        return companyIntro;
    }

    /**
     * 设置公司简介
     *
     * @param companyIntro 公司简介
     */
    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro;
    }

    /**
     * 获取成立时间
     *
     * @return established_time - 成立时间
     */
    public String getEstablishedTime() {
        return establishedTime;
    }

    /**
     * 设置成立时间
     *
     * @param establishedTime 成立时间
     */
    public void setEstablishedTime(String establishedTime) {
        this.establishedTime = establishedTime;
    }

    /**
     * 获取注册地-省
     *
     * @return register_area - 注册地-省
     */
    public String getRegisterArea() {
        return registerArea;
    }

    /**
     * 设置注册地-省
     *
     * @param registerArea 注册地-省
     */
    public void setRegisterArea(String registerArea) {
        this.registerArea = registerArea;
    }

    /**
     * 获取主行业领域
     *
     * @return main_Industry - 主行业领域
     */
    public String getMainIndustry() {
        return mainIndustry;
    }

    /**
     * 设置主行业领域
     *
     * @param mainIndustry 主行业领域
     */
    public void setMainIndustry(String mainIndustry) {
        this.mainIndustry = mainIndustry;
    }

    /**
     * 获取子行业
     *
     * @return sub_industry - 子行业
     */
    public String getSubIndustry() {
        return subIndustry;
    }

    /**
     * 设置子行业
     *
     * @param subIndustry 子行业
     */
    public void setSubIndustry(String subIndustry) {
        this.subIndustry = subIndustry;
    }

    /**
     * 获取Tag标签
     *
     * @return company_tag - Tag标签
     */
    public String getCompanyTag() {
        return companyTag;
    }

    /**
     * 设置Tag标签
     *
     * @param companyTag Tag标签
     */
    public void setCompanyTag(String companyTag) {
        this.companyTag = companyTag;
    }

    /**
     * 获取公司产品名称
     *
     * @return product_name - 公司产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置公司产品名称
     *
     * @param productName 公司产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取最后融资时间
     *
     * @return financing_last_time - 最后融资时间
     */
    public String getFinancingLastTime() {
        return financingLastTime;
    }

    /**
     * 设置最后融资时间
     *
     * @param financingLastTime 最后融资时间
     */
    public void setFinancingLastTime(String financingLastTime) {
        this.financingLastTime = financingLastTime;
    }

    /**
     * 获取轮次
     *
     * @return round - 轮次
     */
    public String getRound() {
        return round;
    }

    /**
     * 设置轮次
     *
     * @param round 轮次
     */
    public void setRound(String round) {
        this.round = round;
    }

    /**
     * 获取融资金额
     *
     * @return money - 融资金额
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * 设置融资金额
     *
     * @param money 融资金额
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * 获取币种
     *
     * @return currency - 币种
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置币种
     *
     * @param currency 币种
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 获取出让份额
     *
     * @return share - 出让份额
     */
    public String getShare() {
        return share;
    }

    /**
     * 设置出让份额
     *
     * @param share 出让份额
     */
    public void setShare(String share) {
        this.share = share;
    }

    /**
     * 获取估值
     *
     * @return Valuation - 估值
     */
    public Integer getValuation() {
        return valuation;
    }

    /**
     * 设置估值
     *
     * @param valuation 估值
     */
    public void setValuation(Integer valuation) {
        this.valuation = valuation;
    }

    /**
     * 获取估值币种
     *
     * @return Valuation_currency - 估值币种
     */
    public String getValuationCurrency() {
        return valuationCurrency;
    }

    /**
     * 设置估值币种
     *
     * @param valuationCurrency 估值币种
     */
    public void setValuationCurrency(String valuationCurrency) {
        this.valuationCurrency = valuationCurrency;
    }

    /**
     * 获取投资方
     *
     * @return Investors - 投资方
     */
    public String getInvestors() {
        return investors;
    }

    /**
     * 设置投资方
     *
     * @param investors 投资方
     */
    public void setInvestors(String investors) {
        this.investors = investors;
    }

    /**
     * 获取教育经历
     *
     * @return education_experience - 教育经历
     */
    public String getEducationExperience() {
        return educationExperience;
    }

    /**
     * 设置教育经历
     *
     * @param educationExperience 教育经历
     */
    public void setEducationExperience(String educationExperience) {
        this.educationExperience = educationExperience;
    }

    /**
     * 获取工作经历
     *
     * @return work_experience - 工作经历
     */
    public String getWorkExperience() {
        return workExperience;
    }

    /**
     * 设置工作经历
     *
     * @param workExperience 工作经历
     */
    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
}