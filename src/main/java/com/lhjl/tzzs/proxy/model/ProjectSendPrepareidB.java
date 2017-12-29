package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project_send_prepareid_b")
public class ProjectSendPrepareidB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 审核时间
     */
    @Column(name = "audit_time")
    private Date auditTime;

    /**
     * 审核状态，0表示未审核，1表示审核通过，2表示审核未通过
     */
    @Column(name = "audit_status")
    private Integer auditStatus;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取审核时间
     *
     * @return audit_time - 审核时间
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * 设置审核时间
     *
     * @param auditTime 审核时间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * 获取审核状态，0表示未审核，1表示审核通过，2表示审核未通过
     *
     * @return audit_status - 审核状态，0表示未审核，1表示审核通过，2表示审核未通过
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置审核状态，0表示未审核，1表示审核通过，2表示审核未通过
     *
     * @param auditStatus 审核状态，0表示未审核，1表示审核通过，2表示审核未通过
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
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