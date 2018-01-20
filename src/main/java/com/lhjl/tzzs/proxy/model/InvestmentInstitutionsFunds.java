package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "investment_institutions_funds")
public class InvestmentInstitutionsFunds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 机构ID
     */
    @Column(name = "investment_institutions_id")
    private Integer investmentInstitutionsId;

    /**
     * 基金名称
     */
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 存续周期
     */
    private Integer duration;

    /**
     * 存续周期单位
     */
    private String units;

    /**
     * 币种
     */
    private String currency;

    /**
     * 管理规模
     */
    private BigDecimal scale;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建者
     */
    private String creator;

    @Column(name = "full_name")
    private String fullName;

    /**
     * 简介
     */
    private String intro;

    /**
     * 单笔起始投资金额
     */
    @Column(name = "investment_amount_begin")
    private BigDecimal investmentAmountBegin;

    /**
     * 单笔限制投资金额
     */
    @Column(name = "investment_amount_end")
    private BigDecimal investmentAmountEnd;

    /**
     * 投资理念
     */
    @Column(name = "investment_philosophy")
    private String investmentPhilosophy;

    /**
     * 基金办公地址
     */
    @Column(name = "funs_addres")
    private String funsAddres;

    /**
     * 是否删除：0未删除；1删除
     */
    private Integer yn;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取机构ID
     *
     * @return investment_institutions_id - 机构ID
     */
    public Integer getInvestmentInstitutionsId() {
        return investmentInstitutionsId;
    }

    /**
     * 设置机构ID
     *
     * @param investmentInstitutionsId 机构ID
     */
    public void setInvestmentInstitutionsId(Integer investmentInstitutionsId) {
        this.investmentInstitutionsId = investmentInstitutionsId;
    }

    /**
     * 获取基金名称
     *
     * @return name - 基金名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置基金名称
     *
     * @param name 基金名称
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取存续周期
     *
     * @return duration - 存续周期
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 设置存续周期
     *
     * @param duration 存续周期
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 获取存续周期单位
     *
     * @return units - 存续周期单位
     */
    public String getUnits() {
        return units;
    }

    /**
     * 设置存续周期单位
     *
     * @param units 存续周期单位
     */
    public void setUnits(String units) {
        this.units = units;
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
     * 获取管理规模
     *
     * @return scale - 管理规模
     */
    public BigDecimal getScale() {
        return scale;
    }

    /**
     * 设置管理规模
     *
     * @param scale 管理规模
     */
    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建者
     *
     * @return creator - 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return full_name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取简介
     *
     * @return intro - 简介
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 设置简介
     *
     * @param intro 简介
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 获取单笔起始投资金额
     *
     * @return investment_amount_begin - 单笔起始投资金额
     */
    public BigDecimal getInvestmentAmountBegin() {
        return investmentAmountBegin;
    }

    /**
     * 设置单笔起始投资金额
     *
     * @param investmentAmountBegin 单笔起始投资金额
     */
    public void setInvestmentAmountBegin(BigDecimal investmentAmountBegin) {
        this.investmentAmountBegin = investmentAmountBegin;
    }

    /**
     * 获取单笔限制投资金额
     *
     * @return investment_amount_end - 单笔限制投资金额
     */
    public BigDecimal getInvestmentAmountEnd() {
        return investmentAmountEnd;
    }

    /**
     * 设置单笔限制投资金额
     *
     * @param investmentAmountEnd 单笔限制投资金额
     */
    public void setInvestmentAmountEnd(BigDecimal investmentAmountEnd) {
        this.investmentAmountEnd = investmentAmountEnd;
    }

    /**
     * 获取投资理念
     *
     * @return investment_philosophy - 投资理念
     */
    public String getInvestmentPhilosophy() {
        return investmentPhilosophy;
    }

    /**
     * 设置投资理念
     *
     * @param investmentPhilosophy 投资理念
     */
    public void setInvestmentPhilosophy(String investmentPhilosophy) {
        this.investmentPhilosophy = investmentPhilosophy;
    }

    /**
     * 获取基金办公地址
     *
     * @return funs_addres - 基金办公地址
     */
    public String getFunsAddres() {
        return funsAddres;
    }

    /**
     * 设置基金办公地址
     *
     * @param funsAddres 基金办公地址
     */
    public void setFunsAddres(String funsAddres) {
        this.funsAddres = funsAddres;
    }

    /**
     * 获取是否删除：0未删除；1删除
     *
     * @return yn - 是否删除：0未删除；1删除
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否删除：0未删除；1删除
     *
     * @param yn 是否删除：0未删除；1删除
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}