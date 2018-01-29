package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "project_progress")
public class ProjectProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目id
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 进展信息
     */
    @Column(name = "progress_info")
    private String progressInfo;

    /**
     * 操作用户
     */
    @Column(name = "operation_user")
    private Integer operationUser;

    /**
     * 操作时间
     */
    @Column(name = "operation_time")
    private Date operationTime;

    /**
     * 删除标志:0代表有效；1代表无效
     */
    private Integer yn;
    @Transient
    private String userName;
    

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
     * 获取项目id
     *
     * @return company_id - 项目id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置项目id
     *
     * @param companyId 项目id
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取进展信息
     *
     * @return progress_info - 进展信息
     */
    public String getProgressInfo() {
        return progressInfo;
    }

    /**
     * 设置进展信息
     *
     * @param progressInfo 进展信息
     */
    public void setProgressInfo(String progressInfo) {
        this.progressInfo = progressInfo;
    }

    /**
     * 获取操作用户
     *
     * @return operation_user - 操作用户
     */
    public Integer getOperationUser() {
        return operationUser;
    }

    /**
     * 设置操作用户
     *
     * @param operationUser 操作用户
     */
    public void setOperationUser(Integer operationUser) {
        this.operationUser = operationUser;
    }

    /**
     * 获取操作时间
     *
     * @return operation_time - 操作时间
     */
    public Date getOperationTime() {
        return operationTime;
    }

    /**
     * 设置操作时间
     *
     * @param operationTime 操作时间
     */
    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    /**
     * 获取删除标志:0代表有效；1代表无效
     *
     * @return yn - 删除标志:0代表有效；1代表无效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置删除标志:0代表有效；1代表无效
     *
     * @param yn 删除标志:0代表有效；1代表无效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

	@Override
	public String toString() {
		return "ProjectProgress [id=" + id + ", companyId=" + companyId + ", progressInfo=" + progressInfo
				+ ", operationUser=" + operationUser + ", operationTime=" + operationTime + ", yn=" + yn + ", userName="
				+ userName + "]";
	}
    
}