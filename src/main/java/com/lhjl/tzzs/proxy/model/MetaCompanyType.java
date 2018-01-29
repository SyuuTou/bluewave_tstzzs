package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_company_type")
public class MetaCompanyType {
    /**
     * 公司Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * appId
     */
    @Column(name = "app_id")
    private Integer appId;

    /**
     * 公司类型
     */
    @Column(name = "company_type")
    private String companyType;

    /**
     * 获取公司Id
     *
     * @return id - 公司Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置公司Id
     *
     * @param id 公司Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取appId
     *
     * @return app_id - appId
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * 设置appId
     *
     * @param appId appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 获取公司类型
     *
     * @return company_type - 公司类型
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * 设置公司类型
     *
     * @param companyType 公司类型
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
}