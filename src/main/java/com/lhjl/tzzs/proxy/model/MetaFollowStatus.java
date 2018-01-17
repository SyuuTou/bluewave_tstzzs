package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_follow_status")
public class MetaFollowStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 跟进状态名称
     */
    @Column(name = "status_name")
    private String statusName;

    /**
     * 排序条件
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
     * 获取跟进状态名称
     *
     * @return status_name - 跟进状态名称
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * 设置跟进状态名称
     *
     * @param statusName 跟进状态名称
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * 获取排序条件
     *
     * @return sort - 排序条件
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序条件
     *
     * @param sort 排序条件
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}