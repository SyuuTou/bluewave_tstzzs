package com.lhjl.tzzs.proxy.dto;

import java.io.Serializable;

public class ResetPasswordReqBody implements Serializable {

    private String mobile;
    private String password;
    private String passworda;
    private String securitycode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassworda() {
        return passworda;
    }

    public void setPassworda(String passworda) {
        this.passworda = passworda;
    }

    public String getSecuritycode() {
        return securitycode;
    }

    public void setSecuritycode(String securitycode) {
        this.securitycode = securitycode;
    }
}
