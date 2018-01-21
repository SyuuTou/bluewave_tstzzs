package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "report_institution_relation")
public class ReportInstitutionRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章id
     */
    @Column(name = "report_id")
    private Integer reportId;

    /**
     * 机构id
     */
    @Column(name = "institution_id")
    private Integer institutionId;

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
     * 获取文章id
     *
     * @return report_id - 文章id
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * 设置文章id
     *
     * @param reportId 文章id
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * 获取机构id
     *
     * @return institution_id - 机构id
     */
    public Integer getInstitutionId() {
        return institutionId;
    }

    /**
     * 设置机构id
     *
     * @param institutionId 机构id
     */
    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
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