package com.lhjl.tzzs.proxy.dto.ReportCommentDto;

import javax.naming.ldap.PagedResultsControl;

/**
 * Created by lanhaijulang on 2018/1/20.
 */
public class ReportCommentOutputDto {

    private long id;

    private String token;

    private Integer columnId;

    private Integer reportId;

    private String message;

    private Integer num;

    /**
     * 用户头像
     */
    private String userHeadpic;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 公司职位
     */
    private String userCompanyDuties;

    private long createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getUserHeadpic() {
        return userHeadpic;
    }

    public void setUserHeadpic(String userHeadpic) {
        this.userHeadpic = userHeadpic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCompanyDuties() {
        return userCompanyDuties;
    }

    public void setUserCompanyDuties(String userCompanyDuties) {
        this.userCompanyDuties = userCompanyDuties;
    }
}
