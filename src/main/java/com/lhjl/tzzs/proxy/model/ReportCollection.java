package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "report_collection")
public class ReportCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户Token
     */
    private String token;

    /**
     * 栏目ID
     */
    @Column(name = "column_id")
    private Integer columnId;

    /**
     * 新闻活动ID
     */
    @Column(name = "report_id")
    private Integer reportId;

    /**
     * 收藏时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "app_id")
    private Integer appId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户Token
     *
     * @return token - 用户Token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置用户Token
     *
     * @param token 用户Token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取栏目ID
     *
     * @return column_id - 栏目ID
     */
    public Integer getColumnId() {
        return columnId;
    }

    /**
     * 设置栏目ID
     *
     * @param columnId 栏目ID
     */
    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    /**
     * 获取新闻活动ID
     *
     * @return report_id - 新闻活动ID
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * 设置新闻活动ID
     *
     * @param reportId 新闻活动ID
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * 获取收藏时间
     *
     * @return create_time - 收藏时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置收藏时间
     *
     * @param createTime 收藏时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return app_id
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }
}