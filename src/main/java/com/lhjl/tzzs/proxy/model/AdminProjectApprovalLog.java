package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "admin_project_approval_log")
public class AdminProjectApprovalLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目源记录id
     */
    @Column(name = "project_source_id")
    private Integer projectSourceId;

    /**
     * 项目来源类型，0表示创始人提交，1表示投资人提交
     */
    @Column(name = "project_source_type")
    private Integer projectSourceType;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 审核状态，0表示待审核，1表示审核通过，2表示审核未通过
     */
    @Column(name = "approvaled_status")
    private Integer approvaledStatus;

    /**
     * 审核时间
     */
    @Column(name = "approvaled_time")
    private Date approvaledTime;

    /**
     * 审核描述
     */
    @Column(name = "approvaled_description")
    private String approvaledDescription;

    /**
     * 审核人
     */
    @Column(name = "approvaled_admin_name")
    private String approvaledAdminName;

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
     * 获取项目源记录id
     *
     * @return project_source_id - 项目源记录id
     */
    public Integer getProjectSourceId() {
        return projectSourceId;
    }

    /**
     * 设置项目源记录id
     *
     * @param projectSourceId 项目源记录id
     */
    public void setProjectSourceId(Integer projectSourceId) {
        this.projectSourceId = projectSourceId;
    }

    /**
     * 获取项目来源类型，0表示创始人提交，1表示投资人提交
     *
     * @return project_source_type - 项目来源类型，0表示创始人提交，1表示投资人提交
     */
    public Integer getProjectSourceType() {
        return projectSourceType;
    }

    /**
     * 设置项目来源类型，0表示创始人提交，1表示投资人提交
     *
     * @param projectSourceType 项目来源类型，0表示创始人提交，1表示投资人提交
     */
    public void setProjectSourceType(Integer projectSourceType) {
        this.projectSourceType = projectSourceType;
    }

    /**
     * 获取项目id
     *
     * @return project_id - 项目id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置项目id
     *
     * @param projectId 项目id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取审核状态，0表示待审核，1表示审核通过，2表示审核未通过
     *
     * @return approvaled_status - 审核状态，0表示待审核，1表示审核通过，2表示审核未通过
     */
    public Integer getApprovaledStatus() {
        return approvaledStatus;
    }

    /**
     * 设置审核状态，0表示待审核，1表示审核通过，2表示审核未通过
     *
     * @param approvaledStatus 审核状态，0表示待审核，1表示审核通过，2表示审核未通过
     */
    public void setApprovaledStatus(Integer approvaledStatus) {
        this.approvaledStatus = approvaledStatus;
    }

    /**
     * 获取审核时间
     *
     * @return approvaled_time - 审核时间
     */
    public Date getApprovaledTime() {
        return approvaledTime;
    }

    /**
     * 设置审核时间
     *
     * @param approvaledTime 审核时间
     */
    public void setApprovaledTime(Date approvaledTime) {
        this.approvaledTime = approvaledTime;
    }

    /**
     * 获取审核描述
     *
     * @return approvaled_description - 审核描述
     */
    public String getApprovaledDescription() {
        return approvaledDescription;
    }

    /**
     * 设置审核描述
     *
     * @param approvaledDescription 审核描述
     */
    public void setApprovaledDescription(String approvaledDescription) {
        this.approvaledDescription = approvaledDescription;
    }

    /**
     * 获取审核人
     *
     * @return approvaled_admin_name - 审核人
     */
    public String getApprovaledAdminName() {
        return approvaledAdminName;
    }

    /**
     * 设置审核人
     *
     * @param approvaledAdminName 审核人
     */
    public void setApprovaledAdminName(String approvaledAdminName) {
        this.approvaledAdminName = approvaledAdminName;
    }
}