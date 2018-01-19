package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "report_comment")
public class ReportComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户token
     */
    private String token;

    /**
     * 栏目
     */
    @Column(name = "column_id")
    private Integer columnId;

    /**
     * 报告ID
     */
    @Column(name = "report_id")
    private Integer reportId;

    /**
     * 评论内容
     */
    private String message;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "app_id")
    private Integer appId;

    private Integer num;

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
     * 获取用户token
     *
     * @return token - 用户token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置用户token
     *
     * @param token 用户token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取栏目
     *
     * @return column_id - 栏目
     */
    public Integer getColumnId() {
        return columnId;
    }

    /**
     * 设置栏目
     *
     * @param columnId 栏目
     */
    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    /**
     * 获取报告ID
     *
     * @return report_id - 报告ID
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * 设置报告ID
     *
     * @param reportId 报告ID
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * 获取评论内容
     *
     * @return message - 评论内容
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置评论内容
     *
     * @param message 评论内容
     */
    public void setMessage(String message) {
        this.message = message;
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

    /**
     * @return num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * @param num
     */
    public void setNum(Integer num) {
        this.num = num;
    }
}