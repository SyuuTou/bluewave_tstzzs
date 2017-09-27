package com.lhjl.tzzs.proxy.dto;

/**
 * Description:
 * User: 罗智文
 * Date: 2017-08-25
 * Time: 11:46
 */
public class ZhuceReqBody {

    private String userName;
    private String password;
    private String tel;
    private String securitycode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSecuritycode() {
        return securitycode;
    }

    public void setSecuritycode(String securitycode) {
        this.securitycode = securitycode;
    }
}
