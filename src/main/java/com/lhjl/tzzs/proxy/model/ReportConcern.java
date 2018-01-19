package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "report_concern")
public class ReportConcern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户token
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
     * 关注时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否有效关注
     */
    private Integer yn;

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
     * 获取关注时间
     *
     * @return create_time - 关注时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置关注时间
     *
     * @param createTime 关注时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否有效关注
     *
     * @return yn - 是否有效关注
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效关注
     *
     * @param yn 是否有效关注
     */
    public void setYn(Integer yn) {
        this.yn = yn;
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