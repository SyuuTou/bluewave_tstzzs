package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_data_source_type")
public class MetaDataSourceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 来源名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 排序字段
     */
    private Integer sort;

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
     * 获取来源名称
     *
     * @return type_name - 来源名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置来源名称
     *
     * @param typeName 来源名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取排序字段
     *
     * @return sort - 排序字段
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序字段
     *
     * @param sort 排序字段
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}