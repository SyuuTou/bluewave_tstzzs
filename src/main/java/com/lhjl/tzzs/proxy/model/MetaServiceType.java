package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_service_type")
public class MetaServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 服务类型名称
     */
    @Column(name = "service_type_name")
    private String serviceTypeName;

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
     * 获取服务类型名称
     *
     * @return service_type_name - 服务类型名称
     */
    public String getServiceTypeName() {
        return serviceTypeName;
    }

    /**
     * 设置服务类型名称
     *
     * @param serviceTypeName 服务类型名称
     */
    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }
}