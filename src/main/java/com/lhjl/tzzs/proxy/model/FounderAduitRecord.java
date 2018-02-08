package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "founder_aduit_record")
public class FounderAduitRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创始人表id
     */
    @Column(name = "founder_id")
    private Integer founderId;

    /**
     * 审核状态,0表示审核未通过，1表示审核通过，默认0
     */
    @Column(name = "aduit_status")
    private Integer aduitStatus;

    /**
     * 审核时间
     */
    @Column(name = "aduit_time")
    private Date aduitTime;

    /**
     * 管理员名称
     */
    @Column(name = "admin_name")
    private String adminName;

    /**
     * 审核描述
     */
    private String discription;

    /**
     * 应用id
     */
    private Integer appid;

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
     * 获取创始人表id
     *
     * @return founder_id - 创始人表id
     */
    public Integer getFounderId() {
        return founderId;
    }

    /**
     * 设置创始人表id
     *
     * @param founderId 创始人表id
     */
    public void setFounderId(Integer founderId) {
        this.founderId = founderId;
    }

    /**
     * 获取审核状态,0表示审核未通过，1表示审核通过，默认0
     *
     * @return aduit_status - 审核状态,0表示审核未通过，1表示审核通过，默认0
     */
    public Integer getAduitStatus() {
        return aduitStatus;
    }

    /**
     * 设置审核状态,0表示审核未通过，1表示审核通过，默认0
     *
     * @param aduitStatus 审核状态,0表示审核未通过，1表示审核通过，默认0
     */
    public void setAduitStatus(Integer aduitStatus) {
        this.aduitStatus = aduitStatus;
    }

    /**
     * 获取审核时间
     *
     * @return aduit_time - 审核时间
     */
    public Date getAduitTime() {
        return aduitTime;
    }

    /**
     * 设置审核时间
     *
     * @param aduitTime 审核时间
     */
    public void setAduitTime(Date aduitTime) {
        this.aduitTime = aduitTime;
    }

    /**
     * 获取管理员名称
     *
     * @return admin_name - 管理员名称
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * 设置管理员名称
     *
     * @param adminName 管理员名称
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * 获取审核描述
     *
     * @return discription - 审核描述
     */
    public String getDiscription() {
        return discription;
    }

    /**
     * 设置审核描述
     *
     * @param discription 审核描述
     */
    public void setDiscription(String discription) {
        this.discription = discription;
    }

    /**
     * 获取应用id
     *
     * @return appid - 应用id
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * 设置应用id
     *
     * @param appid 应用id
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }
}