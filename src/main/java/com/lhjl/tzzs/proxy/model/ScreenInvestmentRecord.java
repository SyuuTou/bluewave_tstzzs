package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "screen_investment_record")
public class ScreenInvestmentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户的id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 机构的类型0：非50,1:50机构
     */
    @Column(name = "investment_type")
    private Integer investmentType;

    /**
     * 选择的轮次
     */
    private String stage;

    /**
     * 领域
     */
    private String domain;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取用户的id
     *
     * @return user_id - 用户的id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户的id
     *
     * @param userId 用户的id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取机构的类型0：非50,1:50机构
     *
     * @return investment_type - 机构的类型0：非50,1:50机构
     */
    public Integer getInvestmentType() {
        return investmentType;
    }

    /**
     * 设置机构的类型0：非50,1:50机构
     *
     * @param investmentType 机构的类型0：非50,1:50机构
     */
    public void setInvestmentType(Integer investmentType) {
        this.investmentType = investmentType;
    }

    /**
     * 获取选择的轮次
     *
     * @return stage - 选择的轮次
     */
    public String getStage() {
        return stage;
    }

    /**
     * 设置选择的轮次
     *
     * @param stage 选择的轮次
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * 获取领域
     *
     * @return domain - 领域
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 设置领域
     *
     * @param domain 领域
     */
    public void setDomain(String domain) {
        this.domain = domain;
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
}