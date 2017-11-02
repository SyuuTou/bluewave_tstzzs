package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "data_log_domain")
public class DataLogDomain {
    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提交数据记录id
     */
    @Column(name = "log_id")
    private Integer logId;

    /**
     * 领域名字
     */
    @Column(name = "domain_name")
    private String domainName;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取表id
     *
     * @return id - 表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置表id
     *
     * @param id 表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取提交数据记录id
     *
     * @return log_id - 提交数据记录id
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * 设置提交数据记录id
     *
     * @param logId 提交数据记录id
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     * 获取领域名字
     *
     * @return domain_name - 领域名字
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * 设置领域名字
     *
     * @param domainName 领域名字
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
}