package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "elegant_service_member_type")
public class ElegantServiceMemberType {
    @Id
    @Column(name = "elegant_service_id")
    private Integer elegantServiceId;

    /**
     * 会员类型ID，1:普通会员，2:高级会员，3:VIP会员，4:VIP投资人
     */
    @Id
    @Column(name = "member_type_id")
    private Integer memberTypeId;

    /**
     * @return elegant_service_id
     */
    public Integer getElegantServiceId() {
        return elegantServiceId;
    }

    /**
     * @param elegantServiceId
     */
    public void setElegantServiceId(Integer elegantServiceId) {
        this.elegantServiceId = elegantServiceId;
    }

    /**
     * 获取会员类型ID，1:普通会员，2:高级会员，3:VIP会员，4:VIP投资人
     *
     * @return member_type_id - 会员类型ID，1:普通会员，2:高级会员，3:VIP会员，4:VIP投资人
     */
    public Integer getMemberTypeId() {
        return memberTypeId;
    }

    /**
     * 设置会员类型ID，1:普通会员，2:高级会员，3:VIP会员，4:VIP投资人
     *
     * @param memberTypeId 会员类型ID，1:普通会员，2:高级会员，3:VIP会员，4:VIP投资人
     */
    public void setMemberTypeId(Integer memberTypeId) {
        this.memberTypeId = memberTypeId;
    }
}