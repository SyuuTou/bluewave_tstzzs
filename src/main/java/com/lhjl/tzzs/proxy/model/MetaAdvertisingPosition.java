package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_advertising_position")
public class MetaAdvertisingPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 广告位置
     */
    @Column(name = "position_name")
    private String positionName;

    /**
     * 机构定制小程序统一为3，其他的是对应的应用id
     */
    private String appid;

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
     * 获取广告位置
     *
     * @return position_name - 广告位置
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * 设置广告位置
     *
     * @param positionName 广告位置
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    /**
     * 获取机构定制小程序统一为3，其他的是对应的应用id
     *
     * @return appid - 机构定制小程序统一为3，其他的是对应的应用id
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 设置机构定制小程序统一为3，其他的是对应的应用id
     *
     * @param appid 机构定制小程序统一为3，其他的是对应的应用id
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }
}