package com.lhjl.tzzs.proxy.dto;

public class UserSetPasswordInputDto {
    private String securitycode;
    private String verify;
    private String user7realname_cn;
    private String password;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecuritycode() {
        return securitycode;
    }

    public void setSecuritycode(String securitycode) {
        this.securitycode = securitycode;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getUser7realname_cn() {
        return user7realname_cn;
    }

    public void setUser7realname_cn(String user7realname_cn) {
        this.user7realname_cn = user7realname_cn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
