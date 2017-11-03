package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "configuration_table")
public class ConfigurationTable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 配置类型，1表示支付开关
     */
    @Column(name = "configuration_type")
    private Integer configurationType;

    /**
     * 配置内容
     */
    @Column(name = "configuration_value")
    private String configurationValue;

    /**
     * @return ID
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
     * 获取配置类型，1表示支付开关
     *
     * @return configuration_type - 配置类型，1表示支付开关
     */
    public Integer getConfigurationType() {
        return configurationType;
    }

    /**
     * 设置配置类型，1表示支付开关
     *
     * @param configurationType 配置类型，1表示支付开关
     */
    public void setConfigurationType(Integer configurationType) {
        this.configurationType = configurationType;
    }

    /**
     * 获取配置内容
     *
     * @return configuration_value - 配置内容
     */
    public String getConfigurationValue() {
        return configurationValue;
    }

    /**
     * 设置配置内容
     *
     * @param configurationValue 配置内容
     */
    public void setConfigurationValue(String configurationValue) {
        this.configurationValue = configurationValue;
    }
}