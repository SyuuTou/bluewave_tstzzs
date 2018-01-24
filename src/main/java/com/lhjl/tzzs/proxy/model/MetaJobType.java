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
    private String description;

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
     * @return description - 类型描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置类型描述
     *
     * @param description 类型描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}