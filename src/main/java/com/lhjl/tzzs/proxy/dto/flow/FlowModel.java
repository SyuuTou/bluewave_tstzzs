package com.lhjl.tzzs.proxy.dto.flow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties
public class FlowModel {
    private String eventType;
    private String eventName;
    private User user;
    private List<User> likers;
    private List<Talk> talks;
    private String timers;
    private String message;
    private Map<String,Boolean> consumeTypes;

    public Map<String, Boolean> getConsumeType() {
        return consumeTypes;
    }

    public void setConsumeType(Map<String, Boolean> consumeTypes) {
        this.consumeTypes = consumeTypes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getTimers() {
        return timers;
    }

    public void setTimers(String timers) {
        this.timers = timers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getLikers() {
        return likers;
    }

    public void setLikers(List<User> likers) {
        this.likers = likers;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
