package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

public class Users {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * MD5唯一ID
     */
    private String uuid;

    /**
     * 注册时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 会员开通状态
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 会员级别
     */
    @Column(name = "`level`")
    private Integer level;

    /**
     * 会员生效时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 会员实效时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 头像
     */
    private String headpic;

    /**
     * 真实姓名
     */
    @Column(name = "actual_name")
    private String actualName;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 简介
     */
    @Column(name = "`desc`")
    private String desc;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 手机号
     */
    private String phonenumber;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 身份类型：0表示投资人,1表示创业者,2表示产业公司,3表示媒体,4表示研究机构;
     */
    @Column(name = "identity_type")
    private Integer identityType;

    @Column(name = "company_name")
    private String companyName;

    /**
     * 公司职位
     */
    @Column(name = "company_duties")
    private String companyDuties;

    /**
     * 公司简介
     */
    @Column(name = "company_desc")
    private String companyDesc;

    /**
     * 需求，对应个人资料的需求
     */
    private String demand;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取MD5唯一ID
     *
     * @return uuid - MD5唯一ID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置MD5唯一ID
     *
     * @param uuid MD5唯一ID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取注册时间
     *
     * @return create_time - 注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册时间
     *
     * @param createTime 注册时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取会员开通状态
     *
     * @return status - 会员开通状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置会员开通状态
     *
     * @param status 会员开通状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取会员级别
     *
     * @return level - 会员级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置会员级别
     *
     * @param level 会员级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取会员生效时间
     *
     * @return begin_time - 会员生效时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置会员生效时间
     *
     * @param beginTime 会员生效时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取会员实效时间
     *
     * @return end_time - 会员实效时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置会员实效时间
     *
     * @param endTime 会员实效时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取头像
     *
     * @return headpic - 头像
     */
    public String getHeadpic() {
        return headpic;
    }

    /**
     * 设置头像
     *
     * @param headpic 头像
     */
    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    /**
     * 获取真实姓名
     *
     * @return actual_name - 真实姓名
     */
    public String getActualName() {
        return actualName;
    }

    /**
     * 设置真实姓名
     *
     * @param actualName 真实姓名
     */
    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    /**
     * 获取所在城市
     *
     * @return city - 所在城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置所在城市
     *
     * @param city 所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取简介
     *
     * @return desc - 简介
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置简介
     *
     * @param desc 简介
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取微信号
     *
     * @return wechat - 微信号
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * 设置微信号
     *
     * @param wechat 微信号
     */
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    /**
     * 获取手机号
     *
     * @return phonenumber - 手机号
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * 设置手机号
     *
     * @param phonenumber 手机号
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取身份类型：0表示投资人,1表示创业者,2表示产业公司,3表示媒体,4表示研究机构;
     *
     * @return identity_type - 身份类型：0表示投资人,1表示创业者,2表示产业公司,3表示媒体,4表示研究机构;
     */
    public Integer getIdentityType() {
        return identityType;
    }

    /**
     * 设置身份类型：0表示投资人,1表示创业者,2表示产业公司,3表示媒体,4表示研究机构;
     *
     * @param identityType 身份类型：0表示投资人,1表示创业者,2表示产业公司,3表示媒体,4表示研究机构;
     */
    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    /**
     * @return company_name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取公司职位
     *
     * @return company_duties - 公司职位
     */
    public String getCompanyDuties() {
        return companyDuties;
    }

    /**
     * 设置公司职位
     *
     * @param companyDuties 公司职位
     */
    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    /**
     * 获取公司简介
     *
     * @return company_desc - 公司简介
     */
    public String getCompanyDesc() {
        return companyDesc;
    }

    /**
     * 设置公司简介
     *
     * @param companyDesc 公司简介
     */
    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    /**
     * 获取需求，对应个人资料的需求
     *
     * @return demand - 需求，对应个人资料的需求
     */
    public String getDemand() {
        return demand;
    }

    /**
     * 设置需求，对应个人资料的需求
     *
     * @param demand 需求，对应个人资料的需求
     */
    public void setDemand(String demand) {
        this.demand = demand;
    }
}