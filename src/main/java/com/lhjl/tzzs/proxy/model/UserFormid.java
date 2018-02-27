package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_formid")
public class UserFormid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "users_id")
    private Integer usersId;

    /**
     * formid内容
     */
    private String formid;

    @Column(name = "formid_type")
    private Integer formidType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否使用，0表示未使用，1表示已使用；使用的时候，按照时间排序用最老的那个
     */
    private Integer yn;

    /**
     * 使用时间
     */
    @Column(name = "user_time")
    private Date userTime;

    /**
     * @return id
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
     * 获取用户id
     *
     * @return users_id - 用户id
     */
    public Integer getUsersId() {
        return usersId;
    }

    /**
     * 设置用户id
     *
     * @param usersId 用户id
     */
    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    /**
     * 获取formid内容
     *
     * @return formid - formid内容
     */
    public String getFormid() {
        return formid;
    }

    /**
     * 设置formid内容
     *
     * @param formid formid内容
     */
    public void setFormid(String formid) {
        this.formid = formid;
    }

    /**
     * @return formid_type
     */
    public Integer getFormidType() {
        return formidType;
    }

    /**
     * @param formidType
     */
    public void setFormidType(Integer formidType) {
        this.formidType = formidType;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否使用，0表示未使用，1表示已使用；使用的时候，按照时间排序用最老的那个
     *
     * @return yn - 是否使用，0表示未使用，1表示已使用；使用的时候，按照时间排序用最老的那个
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否使用，0表示未使用，1表示已使用；使用的时候，按照时间排序用最老的那个
     *
     * @param yn 是否使用，0表示未使用，1表示已使用；使用的时候，按照时间排序用最老的那个
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取使用时间
     *
     * @return user_time - 使用时间
     */
    public Date getUserTime() {
        return userTime;
    }

    /**
     * 设置使用时间
     *
     * @param userTime 使用时间
     */
    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }
}