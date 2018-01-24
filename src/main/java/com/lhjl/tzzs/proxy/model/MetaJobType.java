package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_job_type")
public class MetaJobType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型描述
     */
    private String describe;

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
     * 获取类型描述
     *
     * @return describe - 类型描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置类型描述
     *
     * @param describe 类型描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }
}