package com.lhjl.tzzs.proxy.dto;

public class UserSetPasswordInputDto {
    private String securitycode;
    private String verify;
    private String user7realname_cn;
    private String password;
    private String token;
    private String isWeixin;
    private String idType;
    private String companyShortName;
    private String companyDuties;
    private String formId;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getCompanyDuties() {
        return companyDuties;
    }

    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIsWeixin() {
        return isWeixin;
    }

    public void setIsWeixin(String isWeixin) {
        this.isWeixin = isWeixin;
    }

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
