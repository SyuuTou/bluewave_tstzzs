package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "admin_contact_log")
public class AdminContactLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_pay_id")
    private Integer userPayId;

    /**
     * 0表示未联系，1表示已联系；
     */
    @Column(name = "contact_status")
    private Integer contactStatus;

    /**
     * 联系时间
     */
    @Column(name = "concact_time")
    private Date concactTime;

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
     * @return user_pay_id
     */
    public Integer getUserPayId() {
        return userPayId;
    }

    /**
     * @param userPayId
     */
    public void setUserPayId(Integer userPayId) {
        this.userPayId = userPayId;
    }

    /**
     * 获取0表示未联系，1表示已联系；
     *
     * @return contact_status - 0表示未联系，1表示已联系；
     */
    public Integer getContactStatus() {
        return contactStatus;
    }

    /**
     * 设置0表示未联系，1表示已联系；
     *
     * @param contactStatus 0表示未联系，1表示已联系；
     */
    public void setContactStatus(Integer contactStatus) {
        this.contactStatus = contactStatus;
    }

    /**
     * 获取联系时间
     *
     * @return concact_time - 联系时间
     */
    public Date getConcactTime() {
        return concactTime;
    }

    /**
     * 设置联系时间
     *
     * @param concactTime 联系时间
     */
    public void setConcactTime(Date concactTime) {
        this.concactTime = concactTime;
    }
}