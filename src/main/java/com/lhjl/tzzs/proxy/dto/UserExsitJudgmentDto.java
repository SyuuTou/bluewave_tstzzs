package com.lhjl.tzzs.proxy.dto;

public class UserExsitJudgmentDto {
    private int hasphonenumber;
    private int haspassword;
    private String token;
    private Boolean success;
    private int yhid;

    public int getHasphonenumber() {
        return hasphonenumber;
    }

    public void setHasphonenumber(int hasphonenumber) {
        this.hasphonenumber = hasphonenumber;
    }

    public int getHaspassword() {
        return haspassword;
    }

    public void setHaspassword(int haspassword) {
        this.haspassword = haspassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getYhid() {
        return yhid;
    }

    public void setYhid(int yhid) {
        this.yhid = yhid;
    }
}
