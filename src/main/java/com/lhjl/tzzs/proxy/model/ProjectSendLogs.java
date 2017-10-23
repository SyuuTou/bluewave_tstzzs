package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "project_send_logs")
public class ProjectSendLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 公司简介
     */
    @Column(name = "company_desc")
    private String companyDesc;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 行业领域
     */
    private String field;

    /**
     * 项目标签
     */
    @Column(name = "project_tags")
    private String projectTags;

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
     * @return userid - 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
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
     * 获取公司简介
     *
     * @return company_desc - 公司简介
     */
    public String getCompanyDesc() {
        return companyDesc;
    }

    /**
     * 设置公司简介
     *
     * @param companyDesc 公司简介
     */
    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
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
     * 获取行业领域
     *
     * @return field - 行业领域
     */
    public String getField() {
        return field;
    }

    /**
     * 设置行业领域
     *
     * @param field 行业领域
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * 获取项目标签
     *
     * @return project_tags - 项目标签
     */
    public String getProjectTags() {
        return projectTags;
    }

    /**
     * 设置项目标签
     *
     * @param projectTags 项目标签
     */
    public void setProjectTags(String projectTags) {
        this.projectTags = projectTags;
    }
}