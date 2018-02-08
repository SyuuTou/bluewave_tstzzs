package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "red_envelope")
public class RedEnvelope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    /**
     * 总金额
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 单个金额
     */
    private BigDecimal amount;

    /**
     * 可领取数量
     */
    private Integer quantity;

    /**
     * 领取数量
     */
    @Column(name = "receive_quantity")
    private Integer receiveQuantity;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 红包状态，0:新建, 1:领取中，2:领取完成
     */
    private Integer status;

    @Column(name = "app_id")
    private Integer appId;

    /**
     * 佣金
     */
    private BigDecimal commission;

    /**
     * 红包描述
     */
    private String message;

    /**
     * 替换ID在网络传输
     */
    @Column(name = "union_key")
    private String unionKey;

    /**
     * 红包类型；0: 等额分配；1:随机分配
     */
    @Column(name = "red_envelope_type")
    private Integer redEnvelopeType;

    /**
     * 被领取金额 
     */
    @Column(name = "receive_amount")
    private BigDecimal receiveAmount;



    /**
     * 红包类型描述；INVITATIONED：邀请红包 ，QUOTAED：定额红包，RANDOMED：随机红包
     */
    private String description;

    /**
     * 微信群ID
     */
    private String wechatGroupId;

    public String getWechatGroupId() {
        return wechatGroupId;
    }

    public void setWechatGroupId(String wechatGroupId) {
        this.wechatGroupId = wechatGroupId;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取总金额
     *
     * @return total_amount - 总金额
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置总金额
     *
     * @param totalAmount 总金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取单个金额
     *
     * @return amount - 单个金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置单个金额
     *
     * @param amount 单个金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取可领取数量
     *
     * @return quantity - 可领取数量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 设置可领取数量
     *
     * @param quantity 可领取数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取领取数量
     *
     * @return receive_quantity - 领取数量
     */
    public Integer getReceiveQuantity() {
        return receiveQuantity;
    }

    /**
     * 设置领取数量
     *
     * @param receiveQuantity 领取数量
     */
    public void setReceiveQuantity(Integer receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
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
     * 获取红包状态，0:新建, 1:领取中，2:领取完成
     *
     * @return status - 红包状态，0:新建, 1:领取中，2:领取完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置红包状态，0:新建, 1:领取中，2:领取完成
     *
     * @param status 红包状态，0:新建, 1:领取中，2:领取完成
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 获取佣金
     *
     * @return commission - 佣金
     */
    public BigDecimal getCommission() {
        return commission;
    }

    /**
     * 设置佣金
     *
     * @param commission 佣金
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * 获取红包描述
     *
     * @return message - 红包描述
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置红包描述
     *
     * @param message 红包描述
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取替换ID在网络传输
     *
     * @return union_key - 替换ID在网络传输
     */
    public String getUnionKey() {
        return unionKey;
    }

    /**
     * 设置替换ID在网络传输
     *
     * @param unionKey 替换ID在网络传输
     */
    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey;
    }

    /**
     * 获取红包类型；0: 等额分配；1:随机分配
     *
     * @return red_envelope_type - 红包类型；0: 等额分配；1:随机分配
     */
    public Integer getRedEnvelopeType() {
        return redEnvelopeType;
    }

    /**
     * 设置红包类型；0: 等额分配；1:随机分配
     *
     * @param redEnvelopeType 红包类型；0: 等额分配；1:随机分配
     */
    public void setRedEnvelopeType(Integer redEnvelopeType) {
        this.redEnvelopeType = redEnvelopeType;
    }

    /**
     * 获取被领取金额 
     *
     * @return receive_amount - 被领取金额 
     */
    public BigDecimal getReceiveAmount() {
        return receiveAmount;
    }

    /**
     * 设置被领取金额 
     *
     * @param receiveAmount 被领取金额 
     */
    public void setReceiveAmount(BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    /**
     * 获取红包类型描述；INVITATIONED：邀请红包 ，QUOTAED：定额红包，RANDOMED：随机红包
     *
     * @return description - 红包类型描述；INVITATIONED：邀请红包 ，QUOTAED：定额红包，RANDOMED：随机红包
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置红包类型描述；INVITATIONED：邀请红包 ，QUOTAED：定额红包，RANDOMED：随机红包
     *
     * @param description 红包类型描述；INVITATIONED：邀请红包 ，QUOTAED：定额红包，RANDOMED：随机红包
     */
    public void setDescription(String description) {
        this.description = description;
    }
}