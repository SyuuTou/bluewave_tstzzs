package com.lhjl.tzzs.proxy.dto;

public class UserYnDto {
    int phonenumberyn;
    int passwordyn;
    int useryn;
    boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getPhonenumberyn() {
        return phonenumberyn;
    }

    public void setPhonenumberyn(int phonenumberyn) {
        this.phonenumberyn = phonenumberyn;
    }

    public int getPasswordyn() {
        return passwordyn;
    }

    public void setPasswordyn(int passwordyn) {
        this.passwordyn = passwordyn;
    }

    public int getUseryn() {
        return useryn;
    }

    public void setUseryn(int useryn) {
        this.useryn = useryn;
    }
}
