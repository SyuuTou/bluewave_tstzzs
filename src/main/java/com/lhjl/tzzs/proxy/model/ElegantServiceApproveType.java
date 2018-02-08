package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "elegant_service_approve_type")
public class ElegantServiceApproveType {
    /**
     * 服务ID
     */
    @Column(name = "elegant_service_id")
    private Integer elegantServiceId;

    /**
     * 认证类型，0:个人投资人，1:机构投资人，2:创始人
     */
    @Column(name = "approve_type")
    private Integer approveType;

    /**
     * 获取服务ID
     *
     * @return elegant_service_id - 服务ID
     */
    public Integer getElegantServiceId() {
        return elegantServiceId;
    }

    /**
     * 设置服务ID
     *
     * @param elegantServiceId 服务ID
     */
    public void setElegantServiceId(Integer elegantServiceId) {
        this.elegantServiceId = elegantServiceId;
    }

    /**
     * 获取认证类型，0:个人投资人，1:机构投资人，2:创始人
     *
     * @return approve_type - 认证类型，0:个人投资人，1:机构投资人，2:创始人
     */
    public Integer getApproveType() {
        return approveType;
    }

    /**
     * 设置认证类型，0:个人投资人，1:机构投资人，2:创始人
     *
     * @param approveType 认证类型，0:个人投资人，1:机构投资人，2:创始人
     */
    public void setApproveType(Integer approveType) {
        this.approveType = approveType;
    }
}