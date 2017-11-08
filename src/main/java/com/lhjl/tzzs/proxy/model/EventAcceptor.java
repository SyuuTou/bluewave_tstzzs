package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "event_acceptor")
public class EventAcceptor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 事件表自定义id
     */
    @Column(name = "event_customize_id")
    private String eventCustomizeId;

    /**
     * 接受者id
     */
    @Column(name = "acceptor_id")
    private Integer acceptorId;

    /**
     * 消息接受者类型：0表示接受者是个人，接收对应acceptor_id对应用户表id；1表示接受者是投资机构，接收对应acceptor_id对应用户表id；2表示接受者是平台内用户，接收对应acceptor_id对应指定的管理员id;
     */
    @Column(name = "acceptor_type")
    private String acceptorType;

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
     * 获取事件表自定义id
     *
     * @return event_customize_id - 事件表自定义id
     */
    public String getEventCustomizeId() {
        return eventCustomizeId;
    }

    /**
     * 设置事件表自定义id
     *
     * @param eventCustomizeId 事件表自定义id
     */
    public void setEventCustomizeId(String eventCustomizeId) {
        this.eventCustomizeId = eventCustomizeId;
    }

    /**
     * 获取接受者id
     *
     * @return acceptor_id - 接受者id
     */
    public Integer getAcceptorId() {
        return acceptorId;
    }

    /**
     * 设置接受者id
     *
     * @param acceptorId 接受者id
     */
    public void setAcceptorId(Integer acceptorId) {
        this.acceptorId = acceptorId;
    }

    /**
     * 获取消息接受者类型：0表示接受者是个人，接收对应acceptor_id对应用户表id；1表示接受者是投资机构，接收对应acceptor_id对应用户表id；2表示接受者是平台内用户，接收对应acceptor_id对应指定的管理员id;
     *
     * @return acceptor_type - 消息接受者类型：0表示接受者是个人，接收对应acceptor_id对应用户表id；1表示接受者是投资机构，接收对应acceptor_id对应用户表id；2表示接受者是平台内用户，接收对应acceptor_id对应指定的管理员id;
     */
    public String getAcceptorType() {
        return acceptorType;
    }

    /**
     * 设置消息接受者类型：0表示接受者是个人，接收对应acceptor_id对应用户表id；1表示接受者是投资机构，接收对应acceptor_id对应用户表id；2表示接受者是平台内用户，接收对应acceptor_id对应指定的管理员id;
     *
     * @param acceptorType 消息接受者类型：0表示接受者是个人，接收对应acceptor_id对应用户表id；1表示接受者是投资机构，接收对应acceptor_id对应用户表id；2表示接受者是平台内用户，接收对应acceptor_id对应指定的管理员id;
     */
    public void setAcceptorType(String acceptorType) {
        this.acceptorType = acceptorType;
    }
}