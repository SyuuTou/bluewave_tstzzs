package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "users_pay")
public class UsersPay {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 场景ID，场景包括：购买会员，购买积分
     */
    @Column(name = "scene_id")
    private Integer sceneId;

    /**
     * 账单金额
     */
    private BigDecimal amount;

    /**
     * 应收金额
     */
    private BigDecimal receivable;

    /**
     * 实收金额
     */
    private BigDecimal received;

    /**
     * 用户OpenID
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 微信支付订单号
     */
    @Column(name = "wx_order_num")
    private String wxOrderNum;

    /**
     * 支付状态
     */
    @Column(name = "pay_status")
    private Integer payStatus;

    /**
     * 支付流水明细
     */
    @Column(name = "pay_detail")
    private String payDetail;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * @return ID
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
     * 获取场景ID，场景包括：购买会员，购买积分
     *
     * @return scene_id - 场景ID，场景包括：购买会员，购买积分
     */
    public Integer getSceneId() {
        return sceneId;
    }

    /**
     * 设置场景ID，场景包括：购买会员，购买积分
     *
     * @param sceneId 场景ID，场景包括：购买会员，购买积分
     */
    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    /**
     * 获取账单金额
     *
     * @return amount - 账单金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置账单金额
     *
     * @param amount 账单金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取应收金额
     *
     * @return receivable - 应收金额
     */
    public BigDecimal getReceivable() {
        return receivable;
    }

    /**
     * 设置应收金额
     *
     * @param receivable 应收金额
     */
    public void setReceivable(BigDecimal receivable) {
        this.receivable = receivable;
    }

    /**
     * 获取实收金额
     *
     * @return received - 实收金额
     */
    public BigDecimal getReceived() {
        return received;
    }

    /**
     * 设置实收金额
     *
     * @param received 实收金额
     */
    public void setReceived(BigDecimal received) {
        this.received = received;
    }

    /**
     * 获取用户OpenID
     *
     * @return open_id - 用户OpenID
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置用户OpenID
     *
     * @param openId 用户OpenID
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取微信支付订单号
     *
     * @return wx_order_num - 微信支付订单号
     */
    public String getWxOrderNum() {
        return wxOrderNum;
    }

    /**
     * 设置微信支付订单号
     *
     * @param wxOrderNum 微信支付订单号
     */
    public void setWxOrderNum(String wxOrderNum) {
        this.wxOrderNum = wxOrderNum;
    }

    /**
     * 获取支付状态
     *
     * @return pay_status - 支付状态
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态
     *
     * @param payStatus 支付状态
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取支付流水明细
     *
     * @return pay_detail - 支付流水明细
     */
    public String getPayDetail() {
        return payDetail;
    }

    /**
     * 设置支付流水明细
     *
     * @param payDetail 支付流水明细
     */
    public void setPayDetail(String payDetail) {
        this.payDetail = payDetail;
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
}