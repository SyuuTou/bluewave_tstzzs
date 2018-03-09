package com.lhjl.tzzs.proxy.dto.ElegantServiceDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
@JsonIgnoreProperties
public class ElegantServiceInputDto {
    /**
     * 产品名称
     */
    private String serviceName;
    /**
     * 合作方
     */
    private String cooperationName;
    /**
     * 会员价（原价）
     */
    private String originalPrice;
    /**
     * vip会员价
     */
    private String vipPrice;
    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 币种单位
     */
    private Integer priceUnit;
    /**
     * 限制单位
     */
    private String unit;
    /**
     * 封面图片
     */
    private String backgroundPicture;
    /**
     * 是否隐藏
     */
    private Integer onOff = 1;
    /**
     * 是否推荐
     */
    private Integer recommendYn = 0;
    /**
     * 排序
     */
    private Integer sort = 0;
    /**
     * 是否切换原网页
     */
    private Integer webSwitch = 0;
    /**
     * 简介
     */
    private String description;
    /**
     * 详细简介
     */
    private String detailDescription;
    /**
     * 上架时间
     */
    private String beginTime;
    /**
     * 下架时间
     */
    private String endTime;
    /**
     * 针对的身份类型
     */
    private String identityType;
    /**
     * 服务类型
     */
    private String serviceType;
    /**
     * 服务id
     */
    private Integer elegantServiceId;

    /**
     * 投资人落地页
     */
    private String investorLandingPage;

    /**
     * 创始人落地页
     */
    private String entrepreneurLandingPage;

    /**
     * 其它身份类型落地页
     */
    private String orthorLandingPage;

    /**
     * 发布方抽成佣金
     */
    private BigDecimal commissionPublish;

    /**
     * 接收方抽成佣金
     */
    private BigDecimal commissionReceiver;

    /**
     * 发布方固定抽成佣金
     */
    private BigDecimal commissionPublishFixed;

    /**
     * 接收方固定佣金
     */
    private BigDecimal commissionReceiverFixed;
    /** 认证类型 */
    private List<Integer> approveTypes;
    /** 会员类型 */
    private List<Integer> memberTypes;
    /**每页显示数量*/
    private Integer pageSize;
    /** 是否悬赏 */
    private Integer isReward;
    /** 是否领投 */
    private Integer isLeadInvestor;
    /** 自定义按钮名称 */
    private String customButtonLabel;

    /** 详情图片列表 */
    private List<String> detailUrls;

    private Integer projectId;
    private String projectShortName;

    /** 创建者 */
    private String creator;


    public Integer getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(Integer priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectShortName() {
        return projectShortName;
    }

    public void setProjectShortName(String projectShortName) {
        this.projectShortName = projectShortName;
    }

    public List<String> getDetailUrls() {
        return detailUrls;
    }

    public void setDetailUrls(List<String> detailUrls) {
        this.detailUrls = detailUrls;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCustomButtonLabel() {
        return customButtonLabel;
    }

    public void setCustomButtonLabel(String customButtonLabel) {
        this.customButtonLabel = customButtonLabel;
    }

    public String getInvestorLandingPage() {
        return investorLandingPage;
    }

    public void setInvestorLandingPage(String investorLandingPage) {
        this.investorLandingPage = investorLandingPage;
    }

    public String getEntrepreneurLandingPage() {
        return entrepreneurLandingPage;
    }

    public void setEntrepreneurLandingPage(String entrepreneurLandingPage) {
        this.entrepreneurLandingPage = entrepreneurLandingPage;
    }

    public String getOrthorLandingPage() {
        return orthorLandingPage;
    }

    public void setOrthorLandingPage(String orthorLandingPage) {
        this.orthorLandingPage = orthorLandingPage;
    }

    public BigDecimal getCommissionPublish() {
        return commissionPublish;
    }

    public void setCommissionPublish(BigDecimal commissionPublish) {
        this.commissionPublish = commissionPublish;
    }

    public BigDecimal getCommissionReceiver() {
        return commissionReceiver;
    }

    public void setCommissionReceiver(BigDecimal commissionReceiver) {
        this.commissionReceiver = commissionReceiver;
    }

    public BigDecimal getCommissionPublishFixed() {
        return commissionPublishFixed;
    }

    public void setCommissionPublishFixed(BigDecimal commissionPublishFixed) {
        this.commissionPublishFixed = commissionPublishFixed;
    }

    public BigDecimal getCommissionReceiverFixed() {
        return commissionReceiverFixed;
    }

    public void setCommissionReceiverFixed(BigDecimal commissionReceiverFixed) {
        this.commissionReceiverFixed = commissionReceiverFixed;
    }

    public List<Integer> getApproveTypes() {
        return approveTypes;
    }

    public void setApproveTypes(List<Integer> approveTypes) {
        this.approveTypes = approveTypes;
    }

    public List<Integer> getMemberTypes() {
        return memberTypes;
    }

    public void setMemberTypes(List<Integer> memberTypes) {
        this.memberTypes = memberTypes;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getIsReward() {
        return isReward;
    }

    public void setIsReward(Integer isReward) {
        this.isReward = isReward;
    }

    public Integer getIsLeadInvestor() {
        return isLeadInvestor;
    }

    public void setIsLeadInvestor(Integer isLeadInvestor) {
        this.isLeadInvestor = isLeadInvestor;
    }

    public Integer getElegantServiceId() {
        return elegantServiceId;
    }

    public void setElegantServiceId(Integer elegantServiceId) {
        this.elegantServiceId = elegantServiceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCooperationName() {
        return cooperationName;
    }

    public void setCooperationName(String cooperationName) {
        this.cooperationName = cooperationName;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(String vipPrice) {
        this.vipPrice = vipPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture;
    }

    public Integer getOnOff() {
        return onOff;
    }

    public void setOnOff(Integer onOff) {
        this.onOff = onOff;
    }

    public Integer getRecommendYn() {
        return recommendYn;
    }

    public void setRecommendYn(Integer recommendYn) {
        this.recommendYn = recommendYn;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getWebSwitch() {
        return webSwitch;
    }

    public void setWebSwitch(Integer webSwitch) {
        this.webSwitch = webSwitch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public String getBeginTime() {

        if (StringUtils.isEmpty(this.beginTime)){
            return DateTime.now().toString("yyyy-MM-dd");
        }else {
            return beginTime;
        }
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

}
