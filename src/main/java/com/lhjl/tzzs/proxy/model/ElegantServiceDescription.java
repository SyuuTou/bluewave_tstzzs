package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "elegant_service_description")
public class ElegantServiceDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 精选服务id
     */
    @Column(name = "elegant_service_id")
    private Integer elegantServiceId;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否有效，0表示无效，1表示有效
     */
    private String yn;

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
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
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
    public String getYn() {
        return yn;
    }

    /**
     * 设置是否有效，0表示无效，1表示有效
     *
     * @param yn 是否有效，0表示无效，1表示有效
     */
    public void setYn(String yn) {
        this.yn = yn;
    }
}