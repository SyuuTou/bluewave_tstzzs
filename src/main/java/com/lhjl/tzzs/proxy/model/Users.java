package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

public class Users {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * MD5唯一ID
     */
    private String uuid;

    /**
     * 注册时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 会员开通状态
     */
    private Integer status;

    /**
     * 会员级别
     */
    private Integer level;

    /**
     * 会员生效时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 会员实效时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * @return ID
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
     * 获取MD5唯一ID
     *
     * @return uuid - MD5唯一ID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置MD5唯一ID
     *
     * @param uuid MD5唯一ID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取注册时间
     *
     * @return create_time - 注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册时间
     *
     * @param createTime 注册时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取会员开通状态
     *
     * @return status - 会员开通状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置会员开通状态
     *
     * @param status 会员开通状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取会员级别
     *
     * @return level - 会员级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置会员级别
     *
     * @param level 会员级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取会员生效时间
     *
     * @return begin_time - 会员生效时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置会员生效时间
     *
     * @param beginTime 会员生效时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取会员实效时间
     *
     * @return end_time - 会员实效时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置会员实效时间
     *
     * @param endTime 会员实效时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}