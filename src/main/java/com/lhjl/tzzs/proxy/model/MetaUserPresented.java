package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_user_presented")
public class MetaUserPresented {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 身份类型，organizing_data: 完善资料，Institutional_Investor: 机构投资人， VIP_Investor: VIP投资人
     */
    @Column(name = "id_type")
    private String idType;

    @Column(name = "user_level")
    private Integer userLevel;

    /**
     * 赠送周期
     */
    private Integer period;

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
     * 获取身份类型，organizing_data: 完善资料，Institutional_Investor: 机构投资人， VIP_Investor: VIP投资人
     *
     * @return id_type - 身份类型，organizing_data: 完善资料，Institutional_Investor: 机构投资人， VIP_Investor: VIP投资人
     */
    public String getIdType() {
        return idType;
    }

    /**
     * 设置身份类型，organizing_data: 完善资料，Institutional_Investor: 机构投资人， VIP_Investor: VIP投资人
     *
     * @param idType 身份类型，organizing_data: 完善资料，Institutional_Investor: 机构投资人， VIP_Investor: VIP投资人
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * @return user_level
     */
    public Integer getUserLevel() {
        return userLevel;
    }

    /**
     * @param userLevel
     */
    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    /**
     * 获取赠送周期
     *
     * @return period - 赠送周期
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * 设置赠送周期
     *
     * @param period 赠送周期
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }
}