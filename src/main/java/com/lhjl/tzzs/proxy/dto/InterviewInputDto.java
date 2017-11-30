package com.lhjl.tzzs.proxy.dto;

public class InterviewInputDto {
    /**
     * 用户token
     */
    private String token;

    /**
     * 事件名称
     */
    private String eventName;

    /**
     * 场景
     */
    private String scence;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getScence() {
        return scence;
    }

    public void setScence(String scence) {
        this.scence = scence;
    }
}
