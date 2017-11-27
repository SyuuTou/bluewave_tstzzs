package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "subject_type")
public class SubjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 类型：1表示项目，2表示机构，3表示产业公司，4表示投资人，5表示创业者，6表示媒体，7表示政府机构，8表示服务机构
     */
    private Integer types;

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
     * 获取类型名称
     *
     * @return type_name - 类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置类型名称
     *
     * @param typeName 类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取类型：1表示项目，2表示机构，3表示产业公司，4表示投资人，5表示创业者，6表示媒体，7表示政府机构，8表示服务机构
     *
     * @return types - 类型：1表示项目，2表示机构，3表示产业公司，4表示投资人，5表示创业者，6表示媒体，7表示政府机构，8表示服务机构
     */
    public Integer getTypes() {
        return types;
    }

    /**
     * 设置类型：1表示项目，2表示机构，3表示产业公司，4表示投资人，5表示创业者，6表示媒体，7表示政府机构，8表示服务机构
     *
     * @param types 类型：1表示项目，2表示机构，3表示产业公司，4表示投资人，5表示创业者，6表示媒体，7表示政府机构，8表示服务机构
     */
    public void setTypes(Integer types) {
        this.types = types;
    }
}