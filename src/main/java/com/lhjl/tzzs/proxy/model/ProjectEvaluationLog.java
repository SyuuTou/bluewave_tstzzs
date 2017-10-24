package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project_evaluation_log")
public class ProjectEvaluationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 所属领域
     */
    private String domain;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

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
     * 工作背景
     */
    private String work;

    /**
     * 教育经历
     */
    private String education;

    /**
     *  创建时间
     */
    @Column(name = "creat_time")
    private Date creatTime;

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
     * 获取所属领域
     *
     * @return domain - 所属领域
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 设置所属领域
     *
     * @param domain 所属领域
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 获取公司名称
     *
     * @return company_name - 公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名称
     *
     * @param companyName 公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
     * 获取教育经历
     *
     * @return education - 教育经历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置教育经历
     *
     * @param education 教育经历
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取 创建时间
     *
     * @return creat_time -  创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 设置 创建时间
     *
     * @param creatTime  创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}