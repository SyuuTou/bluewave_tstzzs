package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

public class Investors {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 投资机构ID
     */
    @Column(name = "investment_institutions_id")
    private Integer investmentInstitutionsId;

    /**
     * 用户表ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 是否有效；1: 有效，0:无效
     */
    private Integer yn;

    /**
     * 岗位
     */
    private String position;

    /**
     * 审核状态，0表示审核未通过，1表示审核通过，默认0
     */
    @Column(name = "approval_status")
    private Integer approvalStatus;

    /**
     * 审核时间，审核时存，其他时候为空
     */
    @Column(name = "approval_time")
    private Date approvalTime;

    /**
     * 认证类型，0代表个人投资人，1代表机构投资人，2代表VIP投资人；
     */
    @Column(name = "investors_type")
    private Integer investorsType;

    /**
     * 投资人来源类型，meta_data_source_type表中的元数据id
     */
    @Column(name = "investor_source_type")
    private Integer investorSourceType;

    /**
     * 所属团队id
     */
    @Column(name = "team_id")
    private Integer teamId;

    /**
     * 自定义团队名称
     */
    @Column(name = "self_def_team")
    private String selfDefTeam;

    /**
     * 手机电话
     */
    private String phone;

    /**
     * 一句话介绍
     */
    @Column(name = "kernel_description")
    private String kernelDescription;

    /**
     * 身份类型
     */
    @Column(name = "identity_type")
    private Integer identityType;

    /**
     * 微信号
     */
    private String weichat;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 出生年月
     */
    @Column(name = "birth_day")
    private Date birthDay;

    /**
     * 性别，0：男；1：女
     */
    private Integer sex;

    /**
     * 学历
     */
    private Integer diploma;

    /**
     * 国籍，通过元数据表配置
     */
    private Integer nationality;

    /**
     * 任职时间
     */
    @Column(name = "tenure_time")
    private Date tenureTime;

    /**
     * 工作名片
     */
    @Column(name = "business_card")
    private String businessCard;

    /**
     * 高清图片
     */
    private String picture;

    /**
     * 创业经历描述
     */
    @Column(name = "business_description")
    private String businessDescription;

    /**
     * 教育经历描述
     */
    @Column(name = "education_description")
    private String educationDescription;

    /**
     * 工作经历描述
     */
    @Column(name = "work_description")
    private String workDescription;

    /**
     * 个人荣誉或社会资质
     */
    private String honor;

    /**
     * 个人简介
     */
    @Column(name = "personal_introduction")
    private String personalIntroduction;

    /**
     * 公司简介
     */
    @Column(name = "company_introduction")
    private String companyIntroduction;

    /**
     * 认证说明
     */
    @Column(name = "certification_instructions")
    private String certificationInstructions;

    /**
     * 工作名片反面
     */
    @Column(name = "business_card_opposite")
    private String businessCardOpposite;

    /**
     * 投资人头像
     */
    @Column(name = "head_picture")
    private String headPicture;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 领投资格,张扬阳
     */
    @Column(name = "leader_yn")
    private Integer leaderYn;

    /**
     * 采集编号（曹传桂2018-2-11 14:02:58）
     */
    @Column(name = "serial_number")
    private Integer serialNumber;

    /**
     * 提交人（曹传桂2018-2-11 14:03:08）
     */
    private String submitter;

    /**
     * 投资案例描述（曹传桂2018-2-12 15:48:19）
     */
    @Column(name = "invest_case_description")
    private String investCaseDescription;

    /**
     * 单笔投资金额（曹传桂2018-2-12 15:49:18）
     */
    @Column(name = "single_invest_amount")
    private String singleInvestAmount;

    /**
     * @return ID
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
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取投资机构ID
     *
     * @return investment_institutions_id - 投资机构ID
     */
    public Integer getInvestmentInstitutionsId() {
        return investmentInstitutionsId;
    }

    /**
     * 设置投资机构ID
     *
     * @param investmentInstitutionsId 投资机构ID
     */
    public void setInvestmentInstitutionsId(Integer investmentInstitutionsId) {
        this.investmentInstitutionsId = investmentInstitutionsId;
    }

    /**
     * 获取用户表ID
     *
     * @return user_id - 用户表ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户表ID
     *
     * @param userId 用户表ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取是否有效；1: 有效，0:无效
     *
     * @return yn - 是否有效；1: 有效，0:无效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效；1: 有效，0:无效
     *
     * @param yn 是否有效；1: 有效，0:无效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取岗位
     *
     * @return position - 岗位
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置岗位
     *
     * @param position 岗位
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 获取审核状态，0表示审核未通过，1表示审核通过，默认0
     *
     * @return approval_status - 审核状态，0表示审核未通过，1表示审核通过，默认0
     */
    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * 设置审核状态，0表示审核未通过，1表示审核通过，默认0
     *
     * @param approvalStatus 审核状态，0表示审核未通过，1表示审核通过，默认0
     */
    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    /**
     * 获取审核时间，审核时存，其他时候为空
     *
     * @return approval_time - 审核时间，审核时存，其他时候为空
     */
    public Date getApprovalTime() {
        return approvalTime;
    }

    /**
     * 设置审核时间，审核时存，其他时候为空
     *
     * @param approvalTime 审核时间，审核时存，其他时候为空
     */
    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    /**
     * 获取认证类型，0代表个人投资人，1代表机构投资人，2代表VIP投资人；
     *
     * @return investors_type - 认证类型，0代表个人投资人，1代表机构投资人，2代表VIP投资人；
     */
    public Integer getInvestorsType() {
        return investorsType;
    }

    /**
     * 设置认证类型，0代表个人投资人，1代表机构投资人，2代表VIP投资人；
     *
     * @param investorsType 认证类型，0代表个人投资人，1代表机构投资人，2代表VIP投资人；
     */
    public void setInvestorsType(Integer investorsType) {
        this.investorsType = investorsType;
    }

    /**
     * 获取投资人来源类型，meta_data_source_type表中的元数据id
     *
     * @return investor_source_type - 投资人来源类型，meta_data_source_type表中的元数据id
     */
    public Integer getInvestorSourceType() {
        return investorSourceType;
    }

    /**
     * 设置投资人来源类型，meta_data_source_type表中的元数据id
     *
     * @param investorSourceType 投资人来源类型，meta_data_source_type表中的元数据id
     */
    public void setInvestorSourceType(Integer investorSourceType) {
        this.investorSourceType = investorSourceType;
    }

    /**
     * 获取所属团队id
     *
     * @return team_id - 所属团队id
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * 设置所属团队id
     *
     * @param teamId 所属团队id
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * 获取自定义团队名称
     *
     * @return self_def_team - 自定义团队名称
     */
    public String getSelfDefTeam() {
        return selfDefTeam;
    }

    /**
     * 设置自定义团队名称
     *
     * @param selfDefTeam 自定义团队名称
     */
    public void setSelfDefTeam(String selfDefTeam) {
        this.selfDefTeam = selfDefTeam;
    }

    /**
     * 获取手机电话
     *
     * @return phone - 手机电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机电话
     *
     * @param phone 手机电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取一句话介绍
     *
     * @return kernel_description - 一句话介绍
     */
    public String getKernelDescription() {
        return kernelDescription;
    }

    /**
     * 设置一句话介绍
     *
     * @param kernelDescription 一句话介绍
     */
    public void setKernelDescription(String kernelDescription) {
        this.kernelDescription = kernelDescription;
    }

    /**
     * 获取身份类型
     *
     * @return identity_type - 身份类型
     */
    public Integer getIdentityType() {
        return identityType;
    }

    /**
     * 设置身份类型
     *
     * @param identityType 身份类型
     */
    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    /**
     * 获取微信号
     *
     * @return weichat - 微信号
     */
    public String getWeichat() {
        return weichat;
    }

    /**
     * 设置微信号
     *
     * @param weichat 微信号
     */
    public void setWeichat(String weichat) {
        this.weichat = weichat;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取出生年月
     *
     * @return birth_day - 出生年月
     */
    public Date getBirthDay() {
        return birthDay;
    }

    /**
     * 设置出生年月
     *
     * @param birthDay 出生年月
     */
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * 获取性别，0：男；1：女
     *
     * @return sex - 性别，0：男；1：女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别，0：男；1：女
     *
     * @param sex 性别，0：男；1：女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取学历
     *
     * @return diploma - 学历
     */
    public Integer getDiploma() {
        return diploma;
    }

    /**
     * 设置学历
     *
     * @param diploma 学历
     */
    public void setDiploma(Integer diploma) {
        this.diploma = diploma;
    }

    /**
     * 获取国籍，通过元数据表配置
     *
     * @return nationality - 国籍，通过元数据表配置
     */
    public Integer getNationality() {
        return nationality;
    }

    /**
     * 设置国籍，通过元数据表配置
     *
     * @param nationality 国籍，通过元数据表配置
     */
    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    /**
     * 获取任职时间
     *
     * @return tenure_time - 任职时间
     */
    public Date getTenureTime() {
        return tenureTime;
    }

    /**
     * 设置任职时间
     *
     * @param tenureTime 任职时间
     */
    public void setTenureTime(Date tenureTime) {
        this.tenureTime = tenureTime;
    }

    /**
     * 获取工作名片
     *
     * @return business_card - 工作名片
     */
    public String getBusinessCard() {
        return businessCard;
    }

    /**
     * 设置工作名片
     *
     * @param businessCard 工作名片
     */
    public void setBusinessCard(String businessCard) {
        this.businessCard = businessCard;
    }

    /**
     * 获取高清图片
     *
     * @return picture - 高清图片
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置高清图片
     *
     * @param picture 高清图片
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取创业经历描述
     *
     * @return business_description - 创业经历描述
     */
    public String getBusinessDescription() {
        return businessDescription;
    }

    /**
     * 设置创业经历描述
     *
     * @param businessDescription 创业经历描述
     */
    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    /**
     * 获取教育经历描述
     *
     * @return education_description - 教育经历描述
     */
    public String getEducationDescription() {
        return educationDescription;
    }

    /**
     * 设置教育经历描述
     *
     * @param educationDescription 教育经历描述
     */
    public void setEducationDescription(String educationDescription) {
        this.educationDescription = educationDescription;
    }

    /**
     * 获取工作经历描述
     *
     * @return work_description - 工作经历描述
     */
    public String getWorkDescription() {
        return workDescription;
    }

    /**
     * 设置工作经历描述
     *
     * @param workDescription 工作经历描述
     */
    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    /**
     * 获取个人荣誉或社会资质
     *
     * @return honor - 个人荣誉或社会资质
     */
    public String getHonor() {
        return honor;
    }

    /**
     * 设置个人荣誉或社会资质
     *
     * @param honor 个人荣誉或社会资质
     */
    public void setHonor(String honor) {
        this.honor = honor;
    }

    /**
     * 获取个人简介
     *
     * @return personal_introduction - 个人简介
     */
    public String getPersonalIntroduction() {
        return personalIntroduction;
    }

    /**
     * 设置个人简介
     *
     * @param personalIntroduction 个人简介
     */
    public void setPersonalIntroduction(String personalIntroduction) {
        this.personalIntroduction = personalIntroduction;
    }

    /**
     * 获取公司简介
     *
     * @return company_introduction - 公司简介
     */
    public String getCompanyIntroduction() {
        return companyIntroduction;
    }

    /**
     * 设置公司简介
     *
     * @param companyIntroduction 公司简介
     */
    public void setCompanyIntroduction(String companyIntroduction) {
        this.companyIntroduction = companyIntroduction;
    }

    /**
     * 获取认证说明
     *
     * @return certification_instructions - 认证说明
     */
    public String getCertificationInstructions() {
        return certificationInstructions;
    }

    /**
     * 设置认证说明
     *
     * @param certificationInstructions 认证说明
     */
    public void setCertificationInstructions(String certificationInstructions) {
        this.certificationInstructions = certificationInstructions;
    }

    /**
     * 获取工作名片反面
     *
     * @return business_card_opposite - 工作名片反面
     */
    public String getBusinessCardOpposite() {
        return businessCardOpposite;
    }

    /**
     * 设置工作名片反面
     *
     * @param businessCardOpposite 工作名片反面
     */
    public void setBusinessCardOpposite(String businessCardOpposite) {
        this.businessCardOpposite = businessCardOpposite;
    }

    /**
     * 获取投资人头像
     *
     * @return head_picture - 投资人头像
     */
    public String getHeadPicture() {
        return headPicture;
    }

    /**
     * 设置投资人头像
     *
     * @param headPicture 投资人头像
     */
    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取领投资格,张扬阳
     *
     * @return leader_yn - 领投资格,张扬阳
     */
    public Integer getLeaderYn() {
        return leaderYn;
    }

    /**
     * 设置领投资格,张扬阳
     *
     * @param leaderYn 领投资格,张扬阳
     */
    public void setLeaderYn(Integer leaderYn) {
        this.leaderYn = leaderYn;
    }

    /**
     * 获取采集编号（曹传桂2018-2-11 14:02:58）
     *
     * @return serial_number - 采集编号（曹传桂2018-2-11 14:02:58）
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }

    /**
     * 设置采集编号（曹传桂2018-2-11 14:02:58）
     *
     * @param serialNumber 采集编号（曹传桂2018-2-11 14:02:58）
     */
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 获取提交人（曹传桂2018-2-11 14:03:08）
     *
     * @return submitter - 提交人（曹传桂2018-2-11 14:03:08）
     */
    public String getSubmitter() {
        return submitter;
    }

    /**
     * 设置提交人（曹传桂2018-2-11 14:03:08）
     *
     * @param submitter 提交人（曹传桂2018-2-11 14:03:08）
     */
    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    /**
     * 获取投资案例描述（曹传桂2018-2-12 15:48:19）
     *
     * @return invest_case_description - 投资案例描述（曹传桂2018-2-12 15:48:19）
     */
    public String getInvestCaseDescription() {
        return investCaseDescription;
    }

    /**
     * 设置投资案例描述（曹传桂2018-2-12 15:48:19）
     *
     * @param investCaseDescription 投资案例描述（曹传桂2018-2-12 15:48:19）
     */
    public void setInvestCaseDescription(String investCaseDescription) {
        this.investCaseDescription = investCaseDescription;
    }

    /**
     * 获取单笔投资金额（曹传桂2018-2-12 15:49:18）
     *
     * @return single_invest_amount - 单笔投资金额（曹传桂2018-2-12 15:49:18）
     */
    public String getSingleInvestAmount() {
        return singleInvestAmount;
    }

    /**
     * 设置单笔投资金额（曹传桂2018-2-12 15:49:18）
     *
     * @param singleInvestAmount 单笔投资金额（曹传桂2018-2-12 15:49:18）
     */
    public void setSingleInvestAmount(String singleInvestAmount) {
        this.singleInvestAmount = singleInvestAmount;
    }
}