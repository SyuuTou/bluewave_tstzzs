package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project_financing_investment_institution_relationship")
public class ProjectFinancingInvestmentInstitutionRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目id
     */
    @Column(name = "project_send_log_id")
    private Integer projectSendLogId;

    /**
     * 机构id
     */
    @Column(name = "investment_institution_id")
    private Integer investmentInstitutionId;

    /**
     * 关联时间
     */
    @Column(name = "associated_time")
    private Date associatedTime;

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
     * @return project_send_log_id - 提交项目id
     */
    public Integer getProjectSendLogId() {
        return projectSendLogId;
    }

    /**
     * 设置提交项目id
     *
     * @param projectSendLogId 提交项目id
     */
    public void setProjectSendLogId(Integer projectSendLogId) {
        this.projectSendLogId = projectSendLogId;
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
     * 获取关联时间
     *
     * @return associated_time - 关联时间
     */
    public Date getAssociatedTime() {
        return associatedTime;
    }

    /**
     * 设置关联时间
     *
     * @param associatedTime 关联时间
     */
    public void setAssociatedTime(Date associatedTime) {
        this.associatedTime = associatedTime;
    }
}