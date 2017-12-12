package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_identity_type")
public class MetaIdentityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 身份类型
     */
    @Column(name = "type_name")
    private String typeName;

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
     * 获取身份类型
     *
     * @return type_name - 身份类型
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置身份类型
     *
     * @param typeName 身份类型
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}