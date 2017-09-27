package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

public class Interview {
    /**
     * 约谈id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 约谈状态：0未读，1已读
     */
    @Column(name ="`status`")
    private Integer status;

    /**
     * 项目的id
     */
    @Column(name = "projects_id")
    private Integer projectsId;

    /**
     * 发起人id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 约谈内容
     */
    @Column(name ="`desc`")
    private String desc;

    /**
     * 约谈时间
     */
    @Column(name = "create_time")
    private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getProjectsId() {
		return projectsId;
	}

	public void setProjectsId(Integer projectsId) {
		this.projectsId = projectsId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}