package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "red_envelope_log")
public class RedEnvelopeLog {
    /**
     * 红包ID
     */
    @Id
    @Column(name = "red_envelope_id")
    private Long redEnvelopeId;

    /**
     * 领取人token
     */
    @Id
    private String token;

    /**
     * 领取时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 领取金额
     */
    private BigDecimal amount;

    @Column(name = "app_id")
    private Integer appId;

    /**
     * 被领取人
     */
    @Column(name = "from_token")
    private String fromToken;

    /**
     * 红包发送群唯一key
     */
    @Column(name = "union_key")
    private String unionKey;

    /**
     * 币种；0：人民币，1: 令牌
     */
    private Integer currency;

    /**
     * 付款状态  0，未付款，1，已付款，2，付款失败
     */
    @Column(name = "pay_status")
    private Integer payStatus;

    @Column(name = "pay_trade_no")
    private String payTradeNo;

    @Column(name = "pay_time")
    private String payTime;

    /**
     * 获取红包ID
     *
     * @return red_envelope_id - 红包ID
     */
    public Long getRedEnvelopeId() {
        return redEnvelopeId;
    }

    /**
     * 设置红包ID
     *
     * @param redEnvelopeId 红包ID
     */
    public void setRedEnvelopeId(Long redEnvelopeId) {
        this.redEnvelopeId = redEnvelopeId;
    }

    /**
     * 获取领取人token
     *
     * @return token - 领取人token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置领取人token
     *
     * @param token 领取人token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取领取时间
     *
     * @return create_time - 领取时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置领取时间
     *
     * @param createTime 领取时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取领取金额
     *
     * @return amount - 领取金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置领取金额
     *
     * @param amount 领取金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return app_id
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 获取被领取人
     *
     * @return from_token - 被领取人
     */
    public String getFromToken() {
        return fromToken;
    }

    /**
     * 设置被领取人
     *
     * @param fromToken 被领取人
     */
    public void setFromToken(String fromToken) {
        this.fromToken = fromToken;
    }

    /**
     * 获取红包发送群唯一key
     *
     * @return union_key - 红包发送群唯一key
     */
    public String getUnionKey() {
        return unionKey;
    }

    /**
     * 设置红包发送群唯一key
     *
     * @param unionKey 红包发送群唯一key
     */
    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey;
    }

    /**
     * 获取币种；0：人民币，1: 令牌
     *
     * @return currency - 币种；0：人民币，1: 令牌
     */
    public Integer getCurrency() {
        return currency;
    }

    /**
     * 设置币种；0：人民币，1: 令牌
     *
     * @param currency 币种；0：人民币，1: 令牌
     */
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    /**
     * 获取付款状态  0，未付款，1，已付款，2，付款失败
     *
     * @return pay_status - 付款状态  0，未付款，1，已付款，2，付款失败
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置付款状态  0，未付款，1，已付款，2，付款失败
     *
     * @param payStatus 付款状态  0，未付款，1，已付款，2，付款失败
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * @return pay_trade_no
     */
    public String getPayTradeNo() {
        return payTradeNo;
    }

    /**
     * @param payTradeNo
     */
    public void setPayTradeNo(String payTradeNo) {
        this.payTradeNo = payTradeNo;
    }

    /**
     * @return pay_time
     */
    public String getPayTime() {
        return payTime;
    }

    /**
     * @param payTime
     */
    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}