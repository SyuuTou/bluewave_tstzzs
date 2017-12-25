package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_filter_log")
public class UserFilterLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 机构类型
     */
    @Column(name = "investment_institutions_type")
    private String investmentInstitutionsType;

    /**
     * 所属领域
     */
    @Column(name = "investment_institutions_field")
    private String investmentInstitutionsField;

    /**
     * 融资阶段
     */
    @Column(name = "financing_stage")
    private String financingStage;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 教育背景
     */
    private String education;

    /**
     * 工作背景
     */
    private String work;

    /**
     * 融资年份
     */
    @Column(name = "financing_year")
    private String financingYear;

    /**
     * 筛选用户id(MD5)关联原平台用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 筛选时间
     */
    private Date date;

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
     * 获取机构类型
     *
     * @return investment_institutions_type - 机构类型
     */
    public String getInvestmentInstitutionsType() {
        return investmentInstitutionsType;
    }

    /**
     * 设置机构类型
     *
     * @param investmentInstitutionsType 机构类型
     */
    public void setInvestmentInstitutionsType(String investmentInstitutionsType) {
        this.investmentInstitutionsType = investmentInstitutionsType;
    }

    /**
     * 获取所属领域
     *
     * @return investment_institutions_field - 所属领域
     */
    public String getInvestmentInstitutionsField() {
        return investmentInstitutionsField;
    }

    /**
     * 设置所属领域
     *
     * @param investmentInstitutionsField 所属领域
     */
    public void setInvestmentInstitutionsField(String investmentInstitutionsField) {
        this.investmentInstitutionsField = investmentInstitutionsField;
    }

    /**
     * 获取融资阶段
     *
     * @return financing_stage - 融资阶段
     */
    public String getFinancingStage() {
        return financingStage;
    }

    /**
     * 设置融资阶段
     *
     * @param financingStage 融资阶段
     */
    public void setFinancingStage(String financingStage) {
        this.financingStage = financingStage;
    }

    /**
     * 获取所在城市
     *
     * @return city - 所在城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置所在城市
     *
     * @param city 所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取教育背景
     *
     * @return education - 教育背景
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置教育背景
     *
     * @param education 教育背景
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取工作背景
     *
     * @return work - 工作背景
     */
    public String getWork() {
        return work;
    }

    /**
     * 设置工作背景
     *
     * @param work 工作背景
     */
    public void setWork(String work) {
        this.work = work;
    }

    /**
     * 获取融资年份
     *
     * @return financing_year - 融资年份
     */
    public String getFinancingYear() {
        return financingYear;
    }

    /**
     * 设置融资年份
     *
     * @param financingYear 融资年份
     */
    public void setFinancingYear(String financingYear) {
        this.financingYear = financingYear;
    }

    /**
     * 获取筛选用户id(MD5)关联原平台用户id
     *
     * @return user_id - 筛选用户id(MD5)关联原平台用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置筛选用户id(MD5)关联原平台用户id
     *
     * @param userId 筛选用户id(MD5)关联原平台用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取筛选时间
     *
     * @return date - 筛选时间
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置筛选时间
     *
     * @param date 筛选时间
     */
    public void setDate(Date date) {
        this.date = date;
    }
}