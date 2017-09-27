package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "meta_user_level")
public class MetaUserLevel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 会员名称
     */
    private String name;

    /**
     * 有效周期
     */
    private Integer period;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否有效
     */
    private Integer yn;

    /**
     * 金额
     */
    private BigDecimal amount;

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
     * 获取会员名称
     *
     * @return name - 会员名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置会员名称
     *
     * @param name 会员名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取有效周期
     *
     * @return period - 有效周期
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置有效周期
     *
     * @param period 有效周期
     */
    public void setPeriod(Integer period) {
        this.period = period;
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
     *
     * @return yn - 是否有效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效
     *
     * @param yn 是否有效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取金额
     *
     * @return amount - 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置金额
     *
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}