package com.lhjl.tzzs.proxy.dto;

public class UserSetPasswordOutputDto {
    private String token;
    private boolean success;
    private String yhid;
    private Boolean haspassword;
    private String message;

    public Boolean getHaspassword() {
        return haspassword;
    }

    public void setHaspassword(Boolean haspassword) {
        this.haspassword = haspassword;
    }

    public String getToken() {

        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getYhid() {
        return yhid;
    }

    public void setYhid(String yhid) {
        this.yhid = yhid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
