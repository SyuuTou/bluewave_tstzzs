package com.lhjl.tzzs.proxy.dto;

public class UserInfoElegantOutputDto {

    /**用户id*/
    private Integer id;

    /**用户名*/
    private String userName;

    /**公司职位*/
    private String companyDuties;

    /**公司名称*/
    private String comanyName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyDuties() {
        return companyDuties;
    }

    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    public String getComanyName() {
        return comanyName;
    }

    public void setComanyName(String comanyName) {
        this.comanyName = comanyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
