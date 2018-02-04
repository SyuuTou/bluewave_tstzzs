package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "red_envelope_limit")
public class RedEnvelopeLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 唯一key
     */
    @Column(name = "`key`")
    private String key;

    /**
     * 数量限制
     */
    @Column(name = "limit_value")
    private BigDecimal limitValue;

    /**
     * 实际发放的数量
     */
    @Column(name = "grant_value")
    private BigDecimal grantValue;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "app_id")
    private Integer appId;

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
     * 获取规则名称
     *
     * @return name - 规则名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置规则名称
     *
     * @param name 规则名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取唯一key
     *
     * @return key - 唯一key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置唯一key
     *
     * @param key 唯一key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取数量限制
     *
     * @return limit_value - 数量限制
     */
    public BigDecimal getLimitValue() {
        return limitValue;
    }

    /**
     * 设置数量限制
     *
     * @param limitValue 数量限制
     */
    public void setLimitValue(BigDecimal limitValue) {
        this.limitValue = limitValue;
    }

    /**
     * 获取实际发放的数量
     *
     * @return grant_value - 实际发放的数量
     */
    public BigDecimal getGrantValue() {
        return grantValue;
    }

    /**
     * 设置实际发放的数量
     *
     * @param grantValue 实际发放的数量
     */
    public void setGrantValue(BigDecimal grantValue) {
        this.grantValue = grantValue;
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
     * 获取创建者
     *
     * @return creator - 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return app_id
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }
}