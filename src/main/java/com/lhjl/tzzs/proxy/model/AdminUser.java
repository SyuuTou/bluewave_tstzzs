package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "admin_user")
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 对应用户表id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 管理员类型，0表示root管理员（超级管理员）,1普通管理员, 2业务员,3FA承销,4FA承做
     */
    @Column(name = "admin_type")
    private Integer adminType;

    /**
     * 应用id
     */
    @Column(name = "meta_app_id")
    private Integer metaAppId;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 0表示无效了，1表示有效
     */
    private Integer yn;

    private String phonenumber;

    private String name;

    /**
     * 公司姓名
     */
    @Transient
    private String companyName;
    /**
     * 职务名称
     */
    @Transient
    private String dutyName;


    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
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
     * 获取对应用户表id
     *
     * @return user_id - 对应用户表id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置对应用户表id
     *
     * @param userId 对应用户表id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取管理员类型，0表示root管理员（超级管理员）,1普通管理员, 2业务员,3FA承销,4FA承做
     *
     * @return admin_type - 管理员类型，0表示root管理员（超级管理员）,1普通管理员, 2业务员,3FA承销,4FA承做
     */
    public Integer getAdminType() {
        return adminType;
    }

    /**
     * 设置管理员类型，0表示root管理员（超级管理员）,1普通管理员, 2业务员,3FA承销,4FA承做
     *
     * @param adminType 管理员类型，0表示root管理员（超级管理员）,1普通管理员, 2业务员,3FA承销,4FA承做
     */
    public void setAdminType(Integer adminType) {
        this.adminType = adminType;
    }

    /**
     * 获取应用id
     *
     * @return meta_app_id - 应用id
     */
    public Integer getMetaAppId() {
        return metaAppId;
    }

    /**
     * 设置应用id
     *
     * @param metaAppId 应用id
     */
    public void setMetaAppId(Integer metaAppId) {
        this.metaAppId = metaAppId;
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
     * 获取0表示无效了，1表示有效
     *
     * @return yn - 0表示无效了，1表示有效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置0表示无效了，1表示有效
     *
     * @param yn 0表示无效了，1表示有效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * @return phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", userId=" + userId + ", adminType=" + adminType + ", metaAppId=" + metaAppId
				+ ", password=" + password + ", createTime=" + createTime + ", yn=" + yn + ", phonenumber="
				+ phonenumber + ", name=" + name + ", companyName=" + companyName + ", dutyName=" + dutyName + "]";
	}
    
}