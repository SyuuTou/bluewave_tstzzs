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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取会员ID
     *
     * @return level_id - 会员ID
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * 设置会员ID
     *
     * @param levelId 会员ID
     */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * 获取开通会员时间
     *
     * @return begin_time - 开通会员时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置开通会员时间
     *
     * @param beginTime 开通会员时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取会员到期时间
     *
     * @return end_time - 会员到期时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置会员到期时间
     *
     * @param endTime 会员到期时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
     * 获取是否有效
0: 无效
1：有效
     *
     * @return yn - 是否有效
0: 无效
1：有效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效
0: 无效
1：有效
     *
     * @param yn 是否有效
0: 无效
1：有效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取0: 未支付
1: 支付完成
2: 取消支付
3: 支付失败
     *
     * @return status - 0: 未支付
1: 支付完成
2: 取消支付
3: 支付失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0: 未支付
1: 支付完成
2: 取消支付
3: 支付失败
     *
     * @param status 0: 未支付
1: 支付完成
2: 取消支付
3: 支付失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "UserLevelRelation [id=" + id + ", userId=" + userId + ", levelId=" + levelId + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", createTime=" + createTime + ", yn=" + yn + ", status="
				+ status + ", beginTimeStr=" + beginTimeStr + ", EndTimeStr=" + EndTimeStr + "]";
	}
    
}