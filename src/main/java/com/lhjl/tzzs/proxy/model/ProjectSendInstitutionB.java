package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_institution_b")
public class ProjectSendInstitutionB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目id
     */
    @Column(name = "project_send_b_id")
    private Integer projectSendBId;

    /**
     * 机构id
     */
    @Column(name = "investment_institution_id")
    private Integer investmentInstitutionId;

    /**
     * 预生成id
     */
    @Column(name = "project_send_prepareid")
    private Integer projectSendPrepareid;

    /**
     * 应用id
     */
    private Integer appid;

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
     * 获取提交项目id
     *
     * @return project_send_b_id - 提交项目id
     */
    public Integer getProjectSendBId() {
        return projectSendBId;
    }

    /**
     * 设置提交项目id
     *
     * @param projectSendBId 提交项目id
     */
    public void setProjectSendBId(Integer projectSendBId) {
        this.projectSendBId = projectSendBId;
    }

    /**
     * 获取机构id
     *
     * @return investment_institution_id - 机构id
     */
    public Integer getInvestmentInstitutionId() {
        return investmentInstitutionId;
    }

    /**
     * 设置机构id
     *
     * @param investmentInstitutionId 机构id
     */
    public void setInvestmentInstitutionId(Integer investmentInstitutionId) {
        this.investmentInstitutionId = investmentInstitutionId;
    }

    /**
     * 获取预生成id
     *
     * @return project_send_prepareid - 预生成id
     */
    public Integer getProjectSendPrepareid() {
        return projectSendPrepareid;
    }

    /**
     * 设置预生成id
     *
     * @param projectSendPrepareid 预生成id
     */
    public void setProjectSendPrepareid(Integer projectSendPrepareid) {
        this.projectSendPrepareid = projectSendPrepareid;
    }

    /**
     * 获取应用id
     *
     * @return appid - 应用id
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * 设置应用id
     *
     * @param appid 应用id
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }
}