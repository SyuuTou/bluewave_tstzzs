package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "investment_data_log")
public class InvestmentDataLog {
    /**
     * 记录表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 机构名称
     */
    @Column(name = "Institutional_name")
    private String institutionalName;

    /**
     * 项目简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 公司全称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 一句话介绍
     */
    @Column(name = "word_introduction")
    private String wordIntroduction;

    /**
     * 轮次
     */
    private String stage;

    /**
     * 城市
     */
    private String city;

    /**
     * 金额
     */
    private BigDecimal amont;

    /**
     * 股权占比
     */
    @Column(name = "stock_right")
    private BigDecimal stockRight;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 投资时间
     */
    @Column(name = "finan_time")
    private Date finanTime;

    /**
     * 创始人名字
     */
    @Column(name = "create_name")
    private String createName;

    /**
     * 0：人民币，1：美元
     */
    private Integer yn;

    /**
     * 0:审核中，1：审核完成，2：未审核
     */
    @Column(name = "audit_yn")
    private Integer auditYn;

    /**
     * 审核时间
     */
    @Column(name = "audit_time")
    private Date auditTime;

    /**
     * 获取记录表id
     *
     * @return id - 记录表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置记录表id
     *
     * @param id 记录表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取机构名称
     *
     * @return Institutional_name - 机构名称
     */
    public String getInstitutionalName() {
        return institutionalName;
    }

    /**
     * 设置机构名称
     *
     * @param institutionalName 机构名称
     */
    public void setInstitutionalName(String institutionalName) {
        this.institutionalName = institutionalName;
    }

    /**
     * 获取项目简称
     *
     * @return short_name - 项目简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置项目简称
     *
     * @param shortName 项目简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取公司全称
     *
     * @return company_name - 公司全称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司全称
     *
     * @param companyName 公司全称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取一句话介绍
     *
     * @return word_introduction - 一句话介绍
     */
    public String getWordIntroduction() {
        return wordIntroduction;
    }

    /**
     * 设置一句话介绍
     *
     * @param wordIntroduction 一句话介绍
     */
    public void setWordIntroduction(String wordIntroduction) {
        this.wordIntroduction = wordIntroduction;
    }

    /**
     * 获取轮次
     *
     * @return stage - 轮次
     */
    public String getStage() {
        return stage;
    }

    /**
     * 设置轮次
     *
     * @param stage 轮次
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取金额
     *
     * @return amont - 金额
     */
    public BigDecimal getAmont() {
        return amont;
    }

    /**
     * 设置金额
     *
     * @param amont 金额
     */
    public void setAmont(BigDecimal amont) {
        this.amont = amont;
    }

    /**
     * 获取股权占比
     *
     * @return stock_right - 股权占比
     */
    public BigDecimal getStockRight() {
        return stockRight;
    }

    /**
     * 设置股权占比
     *
     * @param stockRight 股权占比
     */
    public void setStockRight(BigDecimal stockRight) {
        this.stockRight = stockRight;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取投资时间
     *
     * @return finan_time - 投资时间
     */
    public Date getFinanTime() {
        return finanTime;
    }

    /**
     * 设置投资时间
     *
     * @param finanTime 投资时间
     */
    public void setFinanTime(Date finanTime) {
        this.finanTime = finanTime;
    }

    /**
     * 获取创始人名字
     *
     * @return create_name - 创始人名字
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 设置创始人名字
     *
     * @param createName 创始人名字
     */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * 获取0：人民币，1：美元
     *
     * @return yn - 0：人民币，1：美元
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置0：人民币，1：美元
     *
     * @param yn 0：人民币，1：美元
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取0:审核中，1：审核完成，2：未审核
     *
     * @return audit_yn - 0:审核中，1：审核完成，2：未审核
     */
    public Integer getAuditYn() {
        return auditYn;
    }

    /**
     * 设置0:审核中，1：审核完成，2：未审核
     *
     * @param auditYn 0:审核中，1：审核完成，2：未审核
     */
    public void setAuditYn(Integer auditYn) {
        this.auditYn = auditYn;
    }

    /**
     * 获取审核时间
     *
     * @return audit_time - 审核时间
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * 设置审核时间
     *
     * @param auditTime 审核时间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}