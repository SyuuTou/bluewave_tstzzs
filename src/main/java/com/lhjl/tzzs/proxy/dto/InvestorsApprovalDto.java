package com.lhjl.tzzs.proxy.dto;

/**
 * 投资审核
 * Created by 蓝海巨浪 on 2017/10/25.
 */
public class InvestorsApprovalDto {
    /**
     * 模糊查询输入名称
     */
    private String checkName;
    /**
     * 时间条件：0全部，1本日，2本周，3本月
     */
    private String time;
    /**
     * 分页页数
     */
    private Integer pageNum;
    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 审核ID
     */
    private Integer id;
    /**
     * 审核结果
     */
    private String approveResult;
    /**
     * 补充说明
     */
    private String explanation;
    /**
     * 审核状态
     */
    private String approvalStatus;

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
