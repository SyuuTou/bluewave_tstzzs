package com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditlistdto;

/**
 * Created by lanhaijulang on 2018/2/8.
 */
public class InvestorAuditListOutputDto {

    /**
     * 采集编号
     */
    private Integer serialNumber;

    /**
     * 来源类型
     */
    private Integer investorSourceType;

    /**
     * 投资人Id
     */
    private Integer investorId;

    /**
     * 投资人姓名
     */
    private String name;

    /**
     * 所在公司
     */
    private String companyName;

    /**
     * 职务
     */
    private String position;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 一句话介绍
     */
    private String kernelDescription;

    /**
     * 投资领域
     */
    private String segmetation;

    /**
     * 投资阶段
     */
    private String stage;

    /**
     * 投资人姓名
     */
    private String submitter;

    /**
     * 提交时间
     */
    private String createTime;

    /**
     * 审核结果
     */
    private String approvalStatus;

    /**
     * 更新时间
     */
    private String updateTime;

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getInvestorSourceType() {
        return investorSourceType;
    }

    public void setInvestorSourceType(Integer investorSourceType) {
        this.investorSourceType = investorSourceType;
    }

    public Integer getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Integer investorId) {
        this.investorId = investorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKernelDescription() {
        return kernelDescription;
    }

    public void setKernelDescription(String kernelDescription) {
        this.kernelDescription = kernelDescription;
    }

    public String getSegmetation() {
        return segmetation;
    }

    public void setSegmetation(String segmetation) {
        this.segmetation = segmetation;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
