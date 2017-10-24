package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "investors_approval")
public class InvestorsApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 申请人姓名
     */
    @Column(name = "approval_username")
    private String approvalUsername;

    /**
     * 认证类型，0代表个人投资，1代表机构投资，3代表vip投资人；
     */
    @Column(name = "investors_type")
    private Integer investorsType;

    /**
     * 认证说明
     */
    private String description;

    /**
     * 所在公司
     */
    private String company;

    /**
     * 公司职位
     */
    @Column(name = "company_duties")
    private String companyDuties;

    /**
     * 工作名片
     */
    @Column(name = "work_card")
    private String workCard;

    /**
     * 审核结果：0表示待审核、1表示未通过认证、2表示取消投资人认证、3表示认证为个人投资人、4表示认证为机构投资人、5表示认证为VIP投资人；
     */
    @Column(name = "approval_result")
    private Integer approvalResult;

    /**
     * 补充说明
     */
    @Column(name = "supplementary_explanation")
    private String supplementaryExplanation;

    /**
     * 审核时间
     */
    @Column(name = "review_time")
    private Date reviewTime;

    /**
     * 创建申请的时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否领投，0代表不是，1代表是；默认0
     */
    private Integer leadership;

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
     * 获取申请人姓名
     *
     * @return approval_username - 申请人姓名
     */
    public String getApprovalUsername() {
        return approvalUsername;
    }

    /**
     * 设置申请人姓名
     *
     * @param approvalUsername 申请人姓名
     */
    public void setApprovalUsername(String approvalUsername) {
        this.approvalUsername = approvalUsername;
    }

    /**
     * 获取认证类型，0代表个人投资，1代表机构投资，3代表vip投资人；
     *
     * @return investors_type - 认证类型，0代表个人投资，1代表机构投资，3代表vip投资人；
     */
    public Integer getInvestorsType() {
        return investorsType;
    }

    /**
     * 设置认证类型，0代表个人投资，1代表机构投资，3代表vip投资人；
     *
     * @param investorsType 认证类型，0代表个人投资，1代表机构投资，3代表vip投资人；
     */
    public void setInvestorsType(Integer investorsType) {
        this.investorsType = investorsType;
    }

    /**
     * 获取认证说明
     *
     * @return description - 认证说明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置认证说明
     *
     * @param description 认证说明
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取所在公司
     *
     * @return company - 所在公司
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置所在公司
     *
     * @param company 所在公司
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取公司职位
     *
     * @return company_duties - 公司职位
     */
    public String getCompanyDuties() {
        return companyDuties;
    }

    /**
     * 设置公司职位
     *
     * @param companyDuties 公司职位
     */
    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    /**
     * 获取工作名片
     *
     * @return work_card - 工作名片
     */
    public String getWorkCard() {
        return workCard;
    }

    /**
     * 设置工作名片
     *
     * @param workCard 工作名片
     */
    public void setWorkCard(String workCard) {
        this.workCard = workCard;
    }

    /**
     * 获取审核结果：0表示待审核、1表示未通过认证、2表示取消投资人认证、3表示认证为个人投资人、4表示认证为机构投资人、5表示认证为VIP投资人；
     *
     * @return approval_result - 审核结果：0表示待审核、1表示未通过认证、2表示取消投资人认证、3表示认证为个人投资人、4表示认证为机构投资人、5表示认证为VIP投资人；
     */
    public Integer getApprovalResult() {
        return approvalResult;
    }

    /**
     * 设置审核结果：0表示待审核、1表示未通过认证、2表示取消投资人认证、3表示认证为个人投资人、4表示认证为机构投资人、5表示认证为VIP投资人；
     *
     * @param approvalResult 审核结果：0表示待审核、1表示未通过认证、2表示取消投资人认证、3表示认证为个人投资人、4表示认证为机构投资人、5表示认证为VIP投资人；
     */
    public void setApprovalResult(Integer approvalResult) {
        this.approvalResult = approvalResult;
    }

    /**
     * 获取补充说明
     *
     * @return supplementary_explanation - 补充说明
     */
    public String getSupplementaryExplanation() {
        return supplementaryExplanation;
    }

    /**
     * 设置补充说明
     *
     * @param supplementaryExplanation 补充说明
     */
    public void setSupplementaryExplanation(String supplementaryExplanation) {
        this.supplementaryExplanation = supplementaryExplanation;
    }

    /**
     * 获取审核时间
     *
     * @return review_time - 审核时间
     */
    public Date getReviewTime() {
        return reviewTime;
    }

    /**
     * 设置审核时间
     *
     * @param reviewTime 审核时间
     */
    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    /**
     * 获取创建申请的时间
     *
     * @return create_time - 创建申请的时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建申请的时间
     *
     * @param createTime 创建申请的时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否领投，0代表不是，1代表是；默认0
     *
     * @return leadership - 是否领投，0代表不是，1代表是；默认0
     */
    public Integer getLeadership() {
        return leadership;
    }

    /**
     * 设置是否领投，0代表不是，1代表是；默认0
     *
     * @param leadership 是否领投，0代表不是，1代表是；默认0
     */
    public void setLeadership(Integer leadership) {
        this.leadership = leadership;
    }
}