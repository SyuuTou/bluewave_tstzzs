package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "elegant_service_cooperation")
public class ElegantServiceCooperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 合作方名称
     */
    @Column(name = "cooperation_name")
    private String cooperationName;

    /**
     * 合作方描述
     */
    @Column(name = "coorperation_description")
    private String coorperationDescription;

    /**
     * 精选服务id
     */
    @Column(name = "elegant_service_id")
    private Integer elegantServiceId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否有效，0代表已被删除，1代表未被删除
     */
    private Integer yn;

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
     * 获取合作方名称
     *
     * @return cooperation_name - 合作方名称
     */
    public String getCooperationName() {
        return cooperationName;
    }

    /**
     * 设置合作方名称
     *
     * @param cooperationName 合作方名称
     */
    public void setCooperationName(String cooperationName) {
        this.cooperationName = cooperationName;
    }

    /**
     * 获取合作方描述
     *
     * @return coorperation_description - 合作方描述
     */
    public String getCoorperationDescription() {
        return coorperationDescription;
    }

    /**
     * 设置合作方描述
     *
     * @param coorperationDescription 合作方描述
     */
    public void setCoorperationDescription(String coorperationDescription) {
        this.coorperationDescription = coorperationDescription;
    }

    /**
     * 获取精选服务id
     *
     * @return elegant_service_id - 精选服务id
     */
    public Integer getElegantServiceId() {
        return elegantServiceId;
    }

    /**
     * 设置精选服务id
     *
     * @param elegantServiceId 精选服务id
     */
    public void setElegantServiceId(Integer elegantServiceId) {
        this.elegantServiceId = elegantServiceId;
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

    /**
     * 获取是否有效，0代表已被删除，1代表未被删除
     *
     * @return yn - 是否有效，0代表已被删除，1代表未被删除
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效，0代表已被删除，1代表未被删除
     *
     * @param yn 是否有效，0代表已被删除，1代表未被删除
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}