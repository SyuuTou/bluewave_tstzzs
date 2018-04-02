package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_level_relation")
public class UserLevelRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 会员ID
     */
    @Column(name = "level_id")
    private Integer levelId;

    /**
     * 开通会员时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 会员到期时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否有效
0: 无效
1：有效
     */
    private Integer yn;

    /**
     * 0: 未支付
1: 支付完成
2: 取消支付
3: 支付失败
     */
    private Integer status;
    
	/**
	 * 会员开始时间输入字符串
	 */
    @Transient
	private String beginTimeStr;
	/**
	 * 会员到期时间输入字符串
	 */
    @Transient
	private String EndTimeStr;

    @Column(name = "app_id")
    private Integer appId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getYn() {
		return yn;
	}

	public void setYn(Integer yn) {
		this.yn = yn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public String getEndTimeStr() {
		return EndTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		EndTimeStr = endTimeStr;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	@Override
	public String toString() {
		return "UserLevelRelation [id=" + id + ", userId=" + userId + ", levelId=" + levelId + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", createTime=" + createTime + ", yn=" + yn + ", status="
				+ status + ", beginTimeStr=" + beginTimeStr + ", EndTimeStr=" + EndTimeStr + ", appId=" + appId + "]";
	}


}