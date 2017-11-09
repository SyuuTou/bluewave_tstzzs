package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "event_type")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 事件的key
     */
    @Column(name = "event_key")
    private String eventKey;

    /**
     * 事件的描述
     */
    @Column(name = "event_description")
    private String eventDescription;

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
     * 获取事件的key
     *
     * @return event_key - 事件的key
     */
    public String getEventKey() {
        return eventKey;
    }

    /**
     * 设置事件的key
     *
     * @param eventKey 事件的key
     */
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    /**
     * 获取事件的描述
     *
     * @return event_description - 事件的描述
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * 设置事件的描述
     *
     * @param eventDescription 事件的描述
     */
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}