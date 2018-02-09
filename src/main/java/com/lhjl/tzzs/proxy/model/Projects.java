package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Projects {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 项目编号
     */
    @Column(name = "serial_number")
    private Integer serialNumber;

    /**
     * 项目简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 工商注册全称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 一句话介绍
     */
    @Column(name = "kernel_desc")
    private String kernelDesc;

    /**
     * 项目简介（150字以内）
     */
    private String commet;

    /**
     * 官网url
     */
    private String url;

    @Column(name = "established_time")
    private Date establishedTime;

    /**
     * 细分领域

     */
    private String segmentation;

    /**
     * 项目标签
     */
    @Column(name = "item_label")
    private String itemLabel;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 地域
     */
    private String territory;

    /**
     * 公司成立时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 洲
     */
    private String continent;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String region;

    /**
     * 投资机构ID
     */
    @Column(name = "investment_institutions_id")
    private Integer investmentInstitutionsId;

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

    private Integer yn;

    /**
     * 用户id
     */
    private Integer userid;

    @Column(name = "kernel_desc_status")
    private Integer kernelDescStatus;

    @Column(name = "city_count")
    private Integer cityCount;

    @Column(name = "evaluation_recommend")
    private Double evaluationRecommend;

    /**
     * 用来排除，国外机构投资的国外项目
     */
    @Column(name = "project_type")
    private Integer projectType;

    /**
     * 项目来源，0表示创始人提交，1表示投资人提交
     */
    @Column(name = "project_source")
    private Integer projectSource;

    /**
     * 项目logo
     */
    @Column(name = "project_logo")
    private String projectLogo;

    /**
     * 项目投资亮点
     */
    @Column(name = "project_investment_highlights")
    private String projectInvestmentHighlights;

    /**
     * ALTER TABLE `idatavc`.`projects`
CHANGE COLUMN `project_type` `project_type` INT(11) NULL DEFAULT NULL COMMENT '用来排除，国外机构投资的国外项目' ;
     */
    @Column(name = "city_count_max")
    private Integer cityCountMax;

    @Column(name = "city_count_min")
    private Integer cityCountMin;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 公司邮箱
     */
    @Column(name = "company_email")
    private String companyEmail;

    /**
     * 公司hr邮箱
     */
    @Column(name = "company_hr_email")
    private String companyHrEmail;

    /**
     * 是否对外投资，1表示是，0表示不是
     */
    @Column(name = "foreign_investment_yn")
    private Integer foreignInvestmentYn;

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
     * 获取项目编号
     *
     * @return serial_number - 项目编号
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }

    /**
     * 设置项目编号
     *
     * @param serialNumber 项目编号
     */
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 获取项目简称
     *
     * @return short_name - 项目简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置项目简称
     *
     * @param shortName 项目简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取工商注册全称
     *
     * @return full_name - 工商注册全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置工商注册全称
     *
     * @param fullName 工商注册全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取一句话介绍
     *
     * @return kernel_desc - 一句话介绍
     */
    public String getKernelDesc() {
        return kernelDesc;
    }

    /**
     * 设置一句话介绍
     *
     * @param kernelDesc 一句话介绍
     */
    public void setKernelDesc(String kernelDesc) {
        this.kernelDesc = kernelDesc;
    }

    /**
     * 获取项目简介（150字以内）
     *
     * @return commet - 项目简介（150字以内）
     */
    public String getCommet() {
        return commet;
    }

    /**
     * 设置项目简介（150字以内）
     *
     * @param commet 项目简介（150字以内）
     */
    public void setCommet(String commet) {
        this.commet = commet;
    }

    /**
     * 获取官网url
     *
     * @return url - 官网url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置官网url
     *
     * @param url 官网url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return established_time
     */
    public Date getEstablishedTime() {
        return establishedTime;
    }

    /**
     * @param establishedTime
     */
    public void setEstablishedTime(Date establishedTime) {
        this.establishedTime = establishedTime;
    }

    /**
     * 获取细分领域

     *
     * @return segmentation - 细分领域

     */
    public String getSegmentation() {
        return segmentation;
    }

    /**
     * 设置细分领域

     *
     * @param segmentation 细分领域

     */
    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }

    /**
     * 获取项目标签
     *
     * @return item_label - 项目标签
     */
    public String getItemLabel() {
        return itemLabel;
    }

    /**
     * 设置项目标签
     *
     * @param itemLabel 项目标签
     */
    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    /**
     * 获取公司地址
     *
     * @return address - 公司地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置公司地址
     *
     * @param address 公司地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取地域
     *
     * @return territory - 地域
     */
    public String getTerritory() {
        return territory;
    }

    /**
     * 设置地域
     *
     * @param territory 地域
     */
    public void setTerritory(String territory) {
        this.territory = territory;
    }

    /**
     * 获取公司成立时间
     *
     * @return create_time - 公司成立时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置公司成立时间
     *
     * @param createTime 公司成立时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取洲
     *
     * @return continent - 洲
     */
    public String getContinent() {
        return continent;
    }

    /**
     * 设置洲
     *
     * @param continent 洲
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区县
     *
     * @return region - 区县
     */
    public String getRegion() {
        return region;
    }

    /**
     * 设置区县
     *
     * @param region 区县
     */
    public void setRegion(String region) {
        this.region = region;
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
     * @return yn
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * @param yn
     */
    public void setYn(Integer yn) {
        this.yn = yn;
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
     * @return kernel_desc_status
     */
    public Integer getKernelDescStatus() {
        return kernelDescStatus;
    }

    /**
     * @param kernelDescStatus
     */
    public void setKernelDescStatus(Integer kernelDescStatus) {
        this.kernelDescStatus = kernelDescStatus;
    }

    /**
     * @return city_count
     */
    public Integer getCityCount() {
        return cityCount;
    }

    /**
     * @param cityCount
     */
    public void setCityCount(Integer cityCount) {
        this.cityCount = cityCount;
    }

    /**
     * @return evaluation_recommend
     */
    public Double getEvaluationRecommend() {
        return evaluationRecommend;
    }

    /**
     * @param evaluationRecommend
     */
    public void setEvaluationRecommend(Double evaluationRecommend) {
        this.evaluationRecommend = evaluationRecommend;
    }

    /**
     * 获取用来排除，国外机构投资的国外项目
     *
     * @return project_type - 用来排除，国外机构投资的国外项目
     */
    public Integer getProjectType() {
        return projectType;
    }

    /**
     * 设置用来排除，国外机构投资的国外项目
     *
     * @param projectType 用来排除，国外机构投资的国外项目
     */
    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    /**
     * 获取项目来源，0表示创始人提交，1表示投资人提交
     *
     * @return project_source - 项目来源，0表示创始人提交，1表示投资人提交
     */
    public Integer getProjectSource() {
        return projectSource;
    }

    /**
     * 设置项目来源，0表示创始人提交，1表示投资人提交
     *
     * @param projectSource 项目来源，0表示创始人提交，1表示投资人提交
     */
    public void setProjectSource(Integer projectSource) {
        this.projectSource = projectSource;
    }

    /**
     * 获取项目logo
     *
     * @return project_logo - 项目logo
     */
    public String getProjectLogo() {
        return projectLogo;
    }

    /**
     * 设置项目logo
     *
     * @param projectLogo 项目logo
     */
    public void setProjectLogo(String projectLogo) {
        this.projectLogo = projectLogo;
    }

    /**
     * 获取项目投资亮点
     *
     * @return project_investment_highlights - 项目投资亮点
     */
    public String getProjectInvestmentHighlights() {
        return projectInvestmentHighlights;
    }

    /**
     * 设置项目投资亮点
     *
     * @param projectInvestmentHighlights 项目投资亮点
     */
    public void setProjectInvestmentHighlights(String projectInvestmentHighlights) {
        this.projectInvestmentHighlights = projectInvestmentHighlights;
    }

    /**
     * 获取ALTER TABLE `idatavc`.`projects`
CHANGE COLUMN `project_type` `project_type` INT(11) NULL DEFAULT NULL COMMENT '用来排除，国外机构投资的国外项目' ;
     *
     * @return city_count_max - ALTER TABLE `idatavc`.`projects`
CHANGE COLUMN `project_type` `project_type` INT(11) NULL DEFAULT NULL COMMENT '用来排除，国外机构投资的国外项目' ;
     */
    public Integer getCityCountMax() {
        return cityCountMax;
    }

    /**
     * 设置ALTER TABLE `idatavc`.`projects`
CHANGE COLUMN `project_type` `project_type` INT(11) NULL DEFAULT NULL COMMENT '用来排除，国外机构投资的国外项目' ;
     *
     * @param cityCountMax ALTER TABLE `idatavc`.`projects`
CHANGE COLUMN `project_type` `project_type` INT(11) NULL DEFAULT NULL COMMENT '用来排除，国外机构投资的国外项目' ;
     */
    public void setCityCountMax(Integer cityCountMax) {
        this.cityCountMax = cityCountMax;
    }

    /**
     * @return city_count_min
     */
    public Integer getCityCountMin() {
        return cityCountMin;
    }

    /**
     * @param cityCountMin
     */
    public void setCityCountMin(Integer cityCountMin) {
        this.cityCountMin = cityCountMin;
    }

    /**
     * @return total_amount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
     * 获取公司邮箱
     *
     * @return company_email - 公司邮箱
     */
    public String getCompanyEmail() {
        return companyEmail;
    }

    /**
     * 设置公司邮箱
     *
     * @param companyEmail 公司邮箱
     */
    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    /**
     * 获取公司hr邮箱
     *
     * @return company_hr_email - 公司hr邮箱
     */
    public String getCompanyHrEmail() {
        return companyHrEmail;
    }

    /**
     * 设置公司hr邮箱
     *
     * @param companyHrEmail 公司hr邮箱
     */
    public void setCompanyHrEmail(String companyHrEmail) {
        this.companyHrEmail = companyHrEmail;
    }

    /**
     * 获取是否对外投资，1表示是，0表示不是
     *
     * @return foreign_investment_yn - 是否对外投资，1表示是，0表示不是
     */
    public Integer getForeignInvestmentYn() {
        return foreignInvestmentYn;
    }

    /**
     * 设置是否对外投资，1表示是，0表示不是
     *
     * @param foreignInvestmentYn 是否对外投资，1表示是，0表示不是
     */
    public void setForeignInvestmentYn(Integer foreignInvestmentYn) {
        this.foreignInvestmentYn = foreignInvestmentYn;
    }

	@Override
	public String toString() {
		return "Projects [id=" + id + ", serialNumber=" + serialNumber + ", shortName=" + shortName + ", fullName="
				+ fullName + ", kernelDesc=" + kernelDesc + ", commet=" + commet + ", url=" + url + ", establishedTime="
				+ establishedTime + ", segmentation=" + segmentation + ", itemLabel=" + itemLabel + ", address="
				+ address + ", territory=" + territory + ", createTime=" + createTime + ", continent=" + continent
				+ ", country=" + country + ", province=" + province + ", city=" + city + ", region=" + region
				+ ", investmentInstitutionsId=" + investmentInstitutionsId + ", approvalStatus=" + approvalStatus
				+ ", approvalTime=" + approvalTime + ", yn=" + yn + ", userid=" + userid + ", kernelDescStatus="
				+ kernelDescStatus + ", cityCount=" + cityCount + ", evaluationRecommend=" + evaluationRecommend
				+ ", projectType=" + projectType + ", projectSource=" + projectSource + ", projectLogo=" + projectLogo
				+ ", projectInvestmentHighlights=" + projectInvestmentHighlights + ", cityCountMax=" + cityCountMax
				+ ", cityCountMin=" + cityCountMin + ", totalAmount=" + totalAmount + ", updateTime=" + updateTime
				+ ", companyEmail=" + companyEmail + ", companyHrEmail=" + companyHrEmail + ", foreignInvestmentYn="
				+ foreignInvestmentYn + "]";
	}
    
}