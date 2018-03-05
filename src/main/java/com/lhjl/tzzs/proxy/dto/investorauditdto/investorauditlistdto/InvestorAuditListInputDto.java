package com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditlistdto;

/**
 * Created by lanhaijulang on 2018/2/8.
 */
public class InvestorAuditListInputDto {

    /**
     * token
     */
    private String token;

    /**
     * 搜索关键词
     */
    private String searchWord;

    /**
     * 搜索时间下限
     */
    private String dateLow;

    /**
     * 搜索时间上限
     */
    private String dateHigh;

    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 每页显示数量
     */
    private Integer pageSize;

    /**
     * 来源类型
     */
    private String investorSourceType;

    /**
     * 领域
     */
    private String segmentation;

    /**
     * 阶段
     */
    private String stage;

    /**
     * 审核结果
     */
    private String approvalStatus;

    /**
     * 起始页
     */
    private Long start;

    /**
     * 默认按照更新时间进行排序
     * 此处根据实际情况可考虑将列值传递过来
     * 但是应该考虑到一个问题，就是如何将该列和排序的标志位结合起来
     * 此处传递的是 create_time,update_time这两个值
     */
    private String column;

    /**
     * 排序方式：升序asc;降序desc
     * 如果单纯的传递这一个值的话 那肯定是根据某列进行排序，则Mapper.xml直接将排序字段写死即可
     *
     */
    private String order;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getDateLow() {
        return dateLow;
    }

    public void setDateLow(String dateLow) {
        this.dateLow = dateLow;
    }

    public String getDateHigh() {
        return dateHigh;
    }

    public void setDateHigh(String dateHigh) {
        this.dateHigh = dateHigh;
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

    public String getInvestorSourceType() {
        return investorSourceType;
    }

    public void setInvestorSourceType(String investorSourceType) {
        this.investorSourceType = investorSourceType;
    }

    public String getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
