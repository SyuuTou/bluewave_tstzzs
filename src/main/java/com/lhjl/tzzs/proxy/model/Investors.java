package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

public class Investors {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 投资机构ID
     */
    @Column(name = "investment_institutions_id")
    private Integer investmentInstitutionsId;

    /**
     * 用户表ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否有效；1: 有效，0:无效
     */
    private Integer yn;

    /**
     * 岗位
     */
    private String position;

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
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取投资机构ID
     *
     * @return investment_institutions_id - 投资机构ID
     */
    public Integer getInvestmentInstitutionsId() {
        return investmentInstitutionsId;
    }

    /**
     * 设置投资机构ID
     *
     * @param investmentInstitutionsId 投资机构ID
     */
    public void setInvestmentInstitutionsId(Integer investmentInstitutionsId) {
        this.investmentInstitutionsId = investmentInstitutionsId;
    }

    /**
     * 获取用户表ID
     *
     * @return user_id - 用户表ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户表ID
     *
     * @param userId 用户表ID
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
     * 获取是否有效；1: 有效，0:无效
     *
     * @return yn - 是否有效；1: 有效，0:无效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效；1: 有效，0:无效
     *
     * @param yn 是否有效；1: 有效，0:无效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取岗位
     *
     * @return position - 岗位
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置岗位
     *
     * @param position 岗位
     */
    public void setPosition(String position) {
        this.position = position;
    }
}