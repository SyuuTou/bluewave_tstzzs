package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_basic_data_type")
public class MetaBasicDataType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 基础数据类型名称
     */
    private String name;

    /**
     * 关键字
     */
    private String keys;

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
     * 获取基础数据类型名称
     *
     * @return name - 基础数据类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置基础数据类型名称
     *
     * @param name 基础数据类型名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取关键字
     *
     * @return keys - 关键字
     */
    public String getKeys() {
        return keys;
    }

    /**
     * 设置关键字
     *
     * @param keys 关键字
     */
    public void setKeys(String keys) {
        this.keys = keys;
    }
}