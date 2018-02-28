package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "investor_operation_log")
public class InvestorOperationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 投资人id
     */
    @Column(name = "investor_id")
    private Integer investorId;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 操作内容
     */
    @Column(name = "operate_content")
    private String operateContent;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    /**
     * 更新时间输出字符串
     */
//    @Transient
//    private Date updateTimeOutputStr;
    /**
     * 是否删除。0:未删除；1：删除
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
     * 获取投资人id
     *
     * @return investor_id - 投资人id
     */
    public Integer getInvestorId() {
        return investorId;
    }

    /**
     * 设置投资人id
     *
     * @param investorId 投资人id
     */
    public void setInvestorId(Integer investorId) {
        this.investorId = investorId;
    }

    /**
     * 获取操作员
     *
     * @return operator - 操作员
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作员
     *
     * @param operator 操作员
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取操作内容
     *
     * @return operate_content - 操作内容
     */
    public String getOperateContent() {
        return operateContent;
    }

    /**
     * 设置操作内容
     *
     * @param operateContent 操作内容
     */
    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取是否删除。0:未删除；1：删除
     *
     * @return yn - 是否删除。0:未删除；1：删除
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否删除。0:未删除；1：删除
     *
     * @param yn 是否删除。0:未删除；1：删除
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}