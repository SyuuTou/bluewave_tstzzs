package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "elegant_service_service_type")
public class ElegantServiceServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 服务表id
     */
    @Column(name = "elegant_service_id")
    private Integer elegantServiceId;

    /**
     * 服务类型id
     */
    @Column(name = "meta_service_type_id")
    private Integer metaServiceTypeId;

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
     * 获取服务表id
     *
     * @return elegant_service_id - 服务表id
     */
    public Integer getElegantServiceId() {
        return elegantServiceId;
    }

    /**
     * 设置服务表id
     *
     * @param elegantServiceId 服务表id
     */
    public void setElegantServiceId(Integer elegantServiceId) {
        this.elegantServiceId = elegantServiceId;
    }

    /**
     * 获取服务类型id
     *
     * @return meta_service_type_id - 服务类型id
     */
    public Integer getMetaServiceTypeId() {
        return metaServiceTypeId;
    }

    /**
     * 设置服务类型id
     *
     * @param metaServiceTypeId 服务类型id
     */
    public void setMetaServiceTypeId(Integer metaServiceTypeId) {
        this.metaServiceTypeId = metaServiceTypeId;
    }
}