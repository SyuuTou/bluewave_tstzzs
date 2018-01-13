package com.lhjl.tzzs.proxy.dto;

import javax.persistence.Column;
import java.util.Date;

public class ProjectSendAuditBListDto {
    /**审核记录id*/
    private Integer id;

    /**用户id*/
    private Integer uid;

    /**用户真实姓名*/
    @Column(name = "actual_name")
    private String actualName;

    /**项目简称*/
    @Column(name = "short_name")
    private String shortName;

    /**提交项目时间*/
    @Column(name = "create_time")
    private Date createTime;

    /**审核状态*/
    @Column(name = "audit_status")
    private Integer auditStatus;

    /**prepareId*/
    @Column(name = "prepare_id")
    private Integer prepareId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getPrepareId() {
        return prepareId;
    }

    public void setPrepareId(Integer prepareId) {
        this.prepareId = prepareId;
    }
}
