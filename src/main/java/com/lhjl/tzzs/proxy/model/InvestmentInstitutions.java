package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "investment_institutions")
public class InvestmentInstitutions {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 机构简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 机构备注
     */
    private String commet;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 机构类型：1: 50机构，0: 非50机构
     */
    @Column(name = "'type'")
    private Integer type;

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
     * 获取机构简称
     *
     * @return short_name - 机构简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置机构简称
     *
     * @param shortName 机构简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取机构备注
     *
     * @return commet - 机构备注
     */
    public String getCommet() {
        return commet;
    }

    /**
     * 设置机构备注
     *
     * @param commet 机构备注
     */
    public void setCommet(String commet) {
        this.commet = commet;
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
     * 获取机构类型：1: 50机构，0: 非50机构
     *
     * @return type - 机构类型：1: 50机构，0: 非50机构
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置机构类型：1: 50机构，0: 非50机构
     *
     * @param type 机构类型：1: 50机构，0: 非50机构
     */
    public void setType(Integer type) {
        this.type = type;
    }
}