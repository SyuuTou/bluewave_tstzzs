package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "event_interaction")
public class EventInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 事件id
     */
    @Column(name = "event_id")
    private Integer eventId;

    /**
     * 发起交互的人id
     */
    @Column(name = "users_id")
    private Integer usersId;

    /**
     * 交互类型，0表示点赞，1表示评论
     */
    @Column(name = "interaction_type")
    private Integer interactionType;

    /**
     * 交互内容
     */
    @Column(name = "interaction_content")
    private String interactionContent;

    /**
     * 交互事件
     */
    @Column(name = "interaction_time")
    private Date interactionTime;

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
     * 获取事件id
     *
     * @return event_id - 事件id
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * 设置事件id
     *
     * @param eventId 事件id
     */
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * 获取发起交互的人id
     *
     * @return users_id - 发起交互的人id
     */
    public Integer getUsersId() {
        return usersId;
    }

    /**
     * 设置发起交互的人id
     *
     * @param usersId 发起交互的人id
     */
    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    /**
     * 获取交互类型，0表示点赞，1表示评论
     *
     * @return interaction_type - 交互类型，0表示点赞，1表示评论
     */
    public Integer getInteractionType() {
        return interactionType;
    }

    /**
     * 设置交互类型，0表示点赞，1表示评论
     *
     * @param interactionType 交互类型，0表示点赞，1表示评论
     */
    public void setInteractionType(Integer interactionType) {
        this.interactionType = interactionType;
    }

    /**
     * 获取交互内容
     *
     * @return interaction_content - 交互内容
     */
    public String getInteractionContent() {
        return interactionContent;
    }

    /**
     * 设置交互内容
     *
     * @param interactionContent 交互内容
     */
    public void setInteractionContent(String interactionContent) {
        this.interactionContent = interactionContent;
    }

    /**
     * 获取交互事件
     *
     * @return interaction_time - 交互事件
     */
    public Date getInteractionTime() {
        return interactionTime;
    }

    /**
     * 设置交互事件
     *
     * @param interactionTime 交互事件
     */
    public void setInteractionTime(Date interactionTime) {
        this.interactionTime = interactionTime;
    }
}