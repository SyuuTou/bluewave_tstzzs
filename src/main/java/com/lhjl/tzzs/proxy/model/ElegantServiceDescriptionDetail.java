package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "elegant_service_description_detail")
public class ElegantServiceDescriptionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 精选服务id
     */
    @Column(name = "elegant_service_id")
    private Integer elegantServiceId;

    /**
     * 详细描述
     */
    @Column(name = "detail_description")
    private String detailDescription;

    /**
     * 详情类型，0表示wap_view类型详情，1表示卡片详情
     */
    @Column(name = "description_type")
    private Integer descriptionType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否有效，0表示无效，1表示有效
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
     * 获取详细描述
     *
     * @return detail_description - 详细描述
     */
    public String getDetailDescription() {
        return detailDescription;
    }

    /**
     * 设置详细描述
     *
     * @param detailDescription 详细描述
     */
    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    /**
     * 获取详情类型，0表示wap_view类型详情，1表示卡片详情
     *
     * @return description_type - 详情类型，0表示wap_view类型详情，1表示卡片详情
     */
    public Integer getDescriptionType() {
        return descriptionType;
    }

    /**
     * 设置详情类型，0表示wap_view类型详情，1表示卡片详情
     *
     * @param descriptionType 详情类型，0表示wap_view类型详情，1表示卡片详情
     */
    public void setDescriptionType(Integer descriptionType) {
        this.descriptionType = descriptionType;
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
     * 获取是否有效，0表示无效，1表示有效
     *
     * @return yn - 是否有效，0表示无效，1表示有效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效，0表示无效，1表示有效
     *
     * @param yn 是否有效，0表示无效，1表示有效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}