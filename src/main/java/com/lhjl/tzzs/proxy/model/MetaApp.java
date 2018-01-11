package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "meta_app")
public class MetaApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 应用名称
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * appkey
     */
    @Column(name = "app_key")
    private String appKey;

    /**
     * app描述
     */
    @Column(name = "app_description")
    private String appDescription;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否有效，0无效，1有效
     */
    private Integer yn;

    /**
     * 机构id
     */
    @Column(name = "investment_institution_id")
    private Integer investmentInstitutionId;

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
     * 获取应用名称
     *
     * @return app_name - 应用名称
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 设置应用名称
     *
     * @param appName 应用名称
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 获取appkey
     *
     * @return app_key - appkey
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * 设置appkey
     *
     * @param appKey appkey
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * 获取app描述
     *
     * @return app_description - app描述
     */
    public String getAppDescription() {
        return appDescription;
    }

    /**
     * 设置app描述
     *
     * @param appDescription app描述
     */
    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
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
     * 获取是否有效，0无效，1有效
     *
     * @return yn - 是否有效，0无效，1有效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效，0无效，1有效
     *
     * @param yn 是否有效，0无效，1有效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
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
}