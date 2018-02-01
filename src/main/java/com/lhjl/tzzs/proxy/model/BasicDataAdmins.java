package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "basic_data_admins")
public class BasicDataAdmins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 数据类型：投资人、创始人、项目、公司、投资机构
     */
    @Column(name = "data_type")
    private String dataType;

    /**
     * 管理员ID
     */
    @Column(name = "admin_id")
    private Integer adminId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建人token
     */
    private String creator;

    /**
     * 是否有效，1: 有效，0: 无效
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
     * 获取数据类型：投资人、创始人、项目、公司、投资机构
     *
     * @return data_type - 数据类型：投资人、创始人、项目、公司、投资机构
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 设置数据类型：投资人、创始人、项目、公司、投资机构
     *
     * @param dataType 数据类型：投资人、创始人、项目、公司、投资机构
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取管理员ID
     *
     * @return admin_id - 管理员ID
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * 设置管理员ID
     *
     * @param adminId 管理员ID
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建人token
     *
     * @return creator - 创建人token
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人token
     *
     * @param creator 创建人token
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取是否有效，1: 有效，0: 无效
     *
     * @return yn - 是否有效，1: 有效，0: 无效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效，1: 有效，0: 无效
     *
     * @param yn 是否有效，1: 有效，0: 无效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}