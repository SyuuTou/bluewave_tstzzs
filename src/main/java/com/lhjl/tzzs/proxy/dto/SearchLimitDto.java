package com.lhjl.tzzs.proxy.dto;

public class SearchLimitDto {
    String user_id;
    Integer limits;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getLimits() {
        return limits;
    }

    public void setLimits(Integer limits) {
        this.limits = limits;
    }
}
