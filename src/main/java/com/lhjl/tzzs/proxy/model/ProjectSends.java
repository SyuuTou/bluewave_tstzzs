package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_sends")
public class ProjectSends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目提交记录表id
     */
    @Column(name = "project_send_log_id")
    private Integer projectSendLogId;

    /**
     * 项目logo
     */
    @Column(name = "project_logo")
    private String projectLogo;

    /**
     * 项目简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 项目全称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 一句话介绍
     */
    @Column(name = "kernel_desc")
    private String kernelDesc;

    /**
     * 项目亮点
     */
    @Column(name = "project_investment_highlights")
    private String projectInvestmentHighlights;

    /**
     * 项目简介
     */
    private String commet;

    /**
     * 官网
     */
    private String url;

    /**
     * 所在城市
     */
    private String city;

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
     * 获取项目提交记录表id
     *
     * @return project_send_log_id - 项目提交记录表id
     */
    public Integer getProjectSendLogId() {
        return projectSendLogId;
    }

    /**
     * 设置项目提交记录表id
     *
     * @param projectSendLogId 项目提交记录表id
     */
    public void setProjectSendLogId(Integer projectSendLogId) {
        this.projectSendLogId = projectSendLogId;
    }

    /**
     * 获取项目logo
     *
     * @return project_logo - 项目logo
     */
    public String getProjectLogo() {
        return projectLogo;
    }

    /**
     * 设置项目logo
     *
     * @param projectLogo 项目logo
     */
    public void setProjectLogo(String projectLogo) {
        this.projectLogo = projectLogo;
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
     * 获取项目全称
     *
     * @return full_name - 项目全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置项目全称
     *
     * @param fullName 项目全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取一句话介绍
     *
     * @return kernel_desc - 一句话介绍
     */
    public String getKernelDesc() {
        return kernelDesc;
    }

    /**
     * 设置一句话介绍
     *
     * @param kernelDesc 一句话介绍
     */
    public void setKernelDesc(String kernelDesc) {
        this.kernelDesc = kernelDesc;
    }

    /**
     * 获取项目亮点
     *
     * @return project_investment_highlights - 项目亮点
     */
    public String getProjectInvestmentHighlights() {
        return projectInvestmentHighlights;
    }

    /**
     * 设置项目亮点
     *
     * @param projectInvestmentHighlights 项目亮点
     */
    public void setProjectInvestmentHighlights(String projectInvestmentHighlights) {
        this.projectInvestmentHighlights = projectInvestmentHighlights;
    }

    /**
     * 获取项目简介
     *
     * @return commet - 项目简介
     */
    public String getCommet() {
        return commet;
    }

    /**
     * 设置项目简介
     *
     * @param commet 项目简介
     */
    public void setCommet(String commet) {
        this.commet = commet;
    }

    /**
     * 获取官网
     *
     * @return url - 官网
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置官网
     *
     * @param url 官网
     */
    public void setUrl(String url) {
        this.url = url;
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
}