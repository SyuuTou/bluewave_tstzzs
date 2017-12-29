package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "project_send_team_b")
public class ProjectSendTeamB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目id
     */
    @Column(name = "project_send_b_id")
    private Integer projectSendBId;

    /**
     * 成员姓名
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 职务
     */
    @Column(name = "company_duties")
    private String companyDuties;

    /**
     * 股份占比
     */
    @Column(name = "stock_ratio")
    private BigDecimal stockRatio;

    /**
     * 简介
     */
    @Column(name = "member_desc")
    private String memberDesc;

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
     * 获取成员姓名
     *
     * @return member_name - 成员姓名
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 设置成员姓名
     *
     * @param memberName 成员姓名
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * 获取职务
     *
     * @return company_duties - 职务
     */
    public String getCompanyDuties() {
        return companyDuties;
    }

    /**
     * 设置职务
     *
     * @param companyDuties 职务
     */
    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    /**
     * 获取股份占比
     *
     * @return stock_ratio - 股份占比
     */
    public BigDecimal getStockRatio() {
        return stockRatio;
    }

    /**
     * 设置股份占比
     *
     * @param stockRatio 股份占比
     */
    public void setStockRatio(BigDecimal stockRatio) {
        this.stockRatio = stockRatio;
    }

    /**
     * 获取简介
     *
     * @return member_desc - 简介
     */
    public String getMemberDesc() {
        return memberDesc;
    }

    /**
     * 设置简介
     *
     * @param memberDesc 简介
     */
    public void setMemberDesc(String memberDesc) {
        this.memberDesc = memberDesc;
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