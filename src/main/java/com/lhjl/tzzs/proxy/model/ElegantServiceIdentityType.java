package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "elegant_service_identity_type")
public class ElegantServiceIdentityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 服务表id
     */
    @Column(name = "elegant_service_id")
    private Integer elegantServiceId;

    /**
     * 身份类型表id
     */
    @Column(name = "meta_identity_type_id")
    private Integer metaIdentityTypeId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取身份类型表id
     *
     * @return meta_identity_type_id - 身份类型表id
     */
    public Integer getMetaIdentityTypeId() {
        return metaIdentityTypeId;
    }

    /**
     * 设置身份类型表id
     *
     * @param metaIdentityTypeId 身份类型表id
     */
    public void setMetaIdentityTypeId(Integer metaIdentityTypeId) {
        this.metaIdentityTypeId = metaIdentityTypeId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}