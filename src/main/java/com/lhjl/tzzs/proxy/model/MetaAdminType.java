package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_admin_type")
public class MetaAdminType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 管理员类型说明
     */
    private String name;

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
     * 获取管理员类型说明
     *
     * @return name - 管理员类型说明
     */
    public String getName() {
        return name;
    }

    /**
     * 设置管理员类型说明
     *
     * @param name 管理员类型说明
     */
    public void setName(String name) {
        this.name = name;
    }
}