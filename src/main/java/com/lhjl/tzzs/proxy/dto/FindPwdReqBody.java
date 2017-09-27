package com.lhjl.tzzs.proxy.dto;

/**
 * Description:
 * User: 罗智文
 * Date: 2017-08-25
 * Time: 16:41
 */
public class FindPwdReqBody {
    private String mobile;
    private String securitycode;
    private String password;
    private String passworda;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSecuritycode() {
        return securitycode;
    }

    public void setSecuritycode(String securitycode) {
        this.securitycode = securitycode;
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
}
