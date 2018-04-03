package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project_send_audit_b")
public class ProjectSendAuditB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交项目表id
     */
    @Column(name = "project_send_b_id")
    private Integer projectSendBId;

    /**
     * 审核状态，0表示待审核，1表示审核通过，2表示审核未通过
     */
    @Column(name = "audit_status")
    private Integer auditStatus;

    /**
     * 审核时间
     */
    @Column(name = "audit_time")
    private Date auditTime;

    /**
     * 审核人
     */
    @Column(name = "audit_admin")
    private String auditAdmin;

    /**
     * 创建人
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 预生成id
     */
    @Column(name = "prepare_id")
    private Integer prepareId;

    /**
     * 应用id
     */
    private Integer appid;

    /**
     * 项目来源，1表示创业者提交
     */
    @Column(name = "project_source")
    private Integer projectSource;

    @Column(name = "audit_introductions")
    private String auditIntroductions;

    public String getAuditIntroductions() {
        return auditIntroductions;
    }

    public void setAuditIntroductions(String auditIntroductions) {
        this.auditIntroductions = auditIntroductions;
    }

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
     * 获取提交项目表id
     *
     * @return project_send_b_id - 提交项目表id
     */
    public Integer getProjectSendBId() {
        return projectSendBId;
    }

    /**
     * 设置提交项目表id
     *
     * @param projectSendBId 提交项目表id
     */
    public void setProjectSendBId(Integer projectSendBId) {
        this.projectSendBId = projectSendBId;
    }

    /**
     * 获取审核状态，0表示待审核，1表示审核通过，2表示审核未通过
     *
     * @return audit_status - 审核状态，0表示待审核，1表示审核通过，2表示审核未通过
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置审核状态，0表示待审核，1表示审核通过，2表示审核未通过
     *
     * @param auditStatus 审核状态，0表示待审核，1表示审核通过，2表示审核未通过
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
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
     * 获取审核人
     *
     * @return audit_admin - 审核人
     */
    public String getAuditAdmin() {
        return auditAdmin;
    }

    /**
     * 设置审核人
     *
     * @param auditAdmin 审核人
     */
    public void setAuditAdmin(String auditAdmin) {
        this.auditAdmin = auditAdmin;
    }

    /**
     * 获取创建人
     *
     * @return user_id - 创建人
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置创建人
     *
     * @param userId 创建人
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * 获取预生成id
     *
     * @return prepare_id - 预生成id
     */
    public Integer getPrepareId() {
        return prepareId;
    }

    /**
     * 设置预生成id
     *
     * @param prepareId 预生成id
     */
    public void setPrepareId(Integer prepareId) {
        this.prepareId = prepareId;
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

    /**
     * 获取项目来源，1表示创业者提交
     *
     * @return project_source - 项目来源，1表示创业者提交
     */
    public Integer getProjectSource() {
        return projectSource;
    }

    /**
     * 设置项目来源，1表示创业者提交
     *
     * @param projectSource 项目来源，1表示创业者提交
     */
    public void setProjectSource(Integer projectSource) {
        this.projectSource = projectSource;
    }
}