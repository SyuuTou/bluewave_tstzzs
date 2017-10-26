package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_feedback")
public class UserFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 反馈意见
     */
    private String feedback;

    /**
     * 反馈时间
     */
    @Column(name = "creat_time")
    private Date creatTime;

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
     * @return userid - 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取反馈意见
     *
     * @return feedback - 反馈意见
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * 设置反馈意见
     *
     * @param feedback 反馈意见
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    /**
     * 获取反馈时间
     *
     * @return creat_time - 反馈时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 设置反馈时间
     *
     * @param creatTime 反馈时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}