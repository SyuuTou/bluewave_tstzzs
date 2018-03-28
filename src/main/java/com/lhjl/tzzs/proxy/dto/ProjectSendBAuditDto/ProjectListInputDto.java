package com.lhjl.tzzs.proxy.dto.ProjectSendBAuditDto;

/**
 * Created by lanhaijulang on 2018/3/28.
 */
public class ProjectListInputDto {

    /**
     * appid
     */
    private Integer appId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 当前页码
     */
    private Integer currentPage;

    /**
     * 每页显示数量
     */
    private Integer pageSize;

    /**
     * 起始索引
     */
    private Integer start;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
