package com.lhjl.tzzs.proxy.dto;

/**
 * 审核项目输入范式
 */
public class ProjectAuditInputDto {
    /**
     * 项目源id
     */
   private Integer projectSourceId;
    /**
     * 审核状态
     */
   private Integer auditStatus;
    /**
     * 项目源类型
     */
   private Integer projctSourceType;
    /**
     * 审核描述
     */
   private String  auditDescription;
    /**
     * 审核人名称
     */
   private String  auditAdminName;

    public Integer getProjectSourceId() {
        return projectSourceId;
    }

    public void setProjectSourceId(Integer projectSourceId) {
        this.projectSourceId = projectSourceId;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getProjctSourceType() {
        return projctSourceType;
    }

    public void setProjctSourceType(Integer projctSourceType) {
        this.projctSourceType = projctSourceType;
    }

    public String getAuditDescription() {
        return auditDescription;
    }

    public void setAuditDescription(String auditDescription) {
        this.auditDescription = auditDescription;
    }

    public String getAuditAdminName() {
        return auditAdminName;
    }

    public void setAuditAdminName(String auditAdminName) {
        this.auditAdminName = auditAdminName;
    }
}
