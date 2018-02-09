package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project_financial_log_follow_status")
public class ProjectFinancialLogFollowStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目融资历史阶段的主键id,project_financial_log主键id
     */
    @Column(name = "project_financial_log_id")
    private Integer projectFinancialLogId;

    /**
     * 跟进状态元数据表id
     */
    @Column(name = "meta_follow_status_id")
    private Integer metaFollowStatusId;

    /**
     * 备注
     */
    private String description;

    /**
     * 提交者token
     */
    @Column(name = "submitor_token")
    private String submitorToken;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取项目融资历史阶段的主键id,project_financial_log主键id
     *
     * @return project_financial_log_id - 项目融资历史阶段的主键id,project_financial_log主键id
     */
    public Integer getProjectFinancialLogId() {
        return projectFinancialLogId;
    }

    /**
     * 设置项目融资历史阶段的主键id,project_financial_log主键id
     *
     * @param projectFinancialLogId 项目融资历史阶段的主键id,project_financial_log主键id
     */
    public void setProjectFinancialLogId(Integer projectFinancialLogId) {
        this.projectFinancialLogId = projectFinancialLogId;
    }

    /**
     * 获取跟进状态元数据表id
     *
     * @return meta_follow_status_id - 跟进状态元数据表id
     */
    public Integer getMetaFollowStatusId() {
        return metaFollowStatusId;
    }

    /**
     * 设置跟进状态元数据表id
     *
     * @param metaFollowStatusId 跟进状态元数据表id
     */
    public void setMetaFollowStatusId(Integer metaFollowStatusId) {
        this.metaFollowStatusId = metaFollowStatusId;
    }

    /**
     * 获取备注
     *
     * @return description - 备注
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置备注
     *
     * @param description 备注
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取提交者token
     *
     * @return submitor_token - 提交者token
     */
    public String getSubmitorToken() {
        return submitorToken;
    }

    /**
     * 设置提交者token
     *
     * @param submitorToken 提交者token
     */
    public void setSubmitorToken(String submitorToken) {
        this.submitorToken = submitorToken;
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
}