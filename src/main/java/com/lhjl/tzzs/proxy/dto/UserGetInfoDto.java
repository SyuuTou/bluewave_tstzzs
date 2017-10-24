package com.lhjl.tzzs.proxy.dto;

public class UserGetInfoDto {
    private boolean success;
    private String tips;
    private boolean hasphonenumber;
    private boolean haspassword;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public boolean isHasphonenumber() {
        return hasphonenumber;
    }

    public void setHasphonenumber(boolean hasphonenumber) {
        this.hasphonenumber = hasphonenumber;
    }

    public boolean isHaspassword() {
        return haspassword;
    }

    public void setHaspassword(boolean haspassword) {
        this.haspassword = haspassword;
    }
}
