package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "activity_approval_log")
public class ActivityApprovalLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付状态，0表示未支付，1表示支付成功，2表示支付失败
     */
    @Column(name = "pay_status")
    private Integer payStatus;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    @Column(name = "activity_type")
    private Integer activityType;

    /**
     * 是否有效，0表示无效，1表示有效；
     */
    private Integer yn;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
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
     * 获取支付状态，0表示未支付，1表示支付成功，2表示支付失败
     *
     * @return pay_status - 支付状态，0表示未支付，1表示支付成功，2表示支付失败
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态，0表示未支付，1表示支付成功，2表示支付失败
     *
     * @param payStatus 支付状态，0表示未支付，1表示支付成功，2表示支付失败
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * @return activity_type
     */
    public Integer getActivityType() {
        return activityType;
    }

    /**
     * @param activityType
     */
    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    /**
     * 获取是否有效，0表示无效，1表示有效；
     *
     * @return yn - 是否有效，0表示无效，1表示有效；
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效，0表示无效，1表示有效；
     *
     * @param yn 是否有效，0表示无效，1表示有效；
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}