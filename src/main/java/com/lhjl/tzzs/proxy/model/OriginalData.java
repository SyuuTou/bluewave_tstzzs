package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "original_data")
public class OriginalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 偏好及注意事项
     */
    @Column(name = "preference_and_precautions")
    private String preferenceAndPrecautions;

    /**
     * 建群日期
     */
    @Column(name = "create_wechat_date")
    private String createWechatDate;

    /**
     * 群号
     */
    @Column(name = "wechat_number")
    private String wechatNumber;

    /**
     * 机构名称
     */
    @Column(name = "institution_name")
    private String institutionName;

    /**
     * 负责人
     */
    @Column(name = "charge_people")
    private String chargePeople;

    /**
     * 机构简介
     */
    @Column(name = "institution_desc")
    private String institutionDesc;

    /**
     * 基金规模
     */
    @Column(name = "fund_size")
    private String fundSize;

    /**
     * 机构等级
     */
    @Column(name = "institution_level")
    private String institutionLevel;

    /**
     * 机构分类
     */
    @Column(name = "institution_classify")
    private String institutionClassify;

    /**
     * 机构内部架构
     */
    @Column(name = "institution_internal_organization")
    private String institutionInternalOrganization;

    /**
     * 机构来源
     */
    @Column(name = "institution_source")
    private String institutionSource;

    /**
     * 资料是否完整
     */
    @Column(name = "data_full_yn")
    private Integer dataFullYn;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 职务
     */
    @Column(name = "company_duties")
    private String companyDuties;

    /**
     * 名片
     */
    @Column(name = "work_card")
    private String workCard;

    /**
     * 人工智能是否关注
     */
    @Column(name = "rgzl_attention")
    private String rgzlAttention;

    /**
     * 人工智能详细描述
     */
    @Column(name = "rgzl_detail")
    private String rgzlDetail;

    /**
     * 智能制造是否关注
     */
    @Column(name = "znzz_attention")
    private String znzzAttention;

    /**
     * 智能制造详细说明
     */
    @Column(name = "znzz_detail")
    private String znzzDetail;

    /**
     * 汽车交通和物流关注
     */
    @Column(name = "qcjthwl_attention")
    private String qcjthwlAttention;

    /**
     * 汽车交通和物流详细说明
     */
    @Column(name = "qcjthwl_detail")
    private String qcjthwlDetail;

    /**
     * 安全科技是否关注
     */
    @Column(name = "aqkj_attention")
    private String aqkjAttention;

    /**
     * 安全科技详细说明
     */
    @Column(name = "aqkj_detail")
    private String aqkjDetail;

    /**
     * 芯片半导体是否关注
     */
    @Column(name = "xpbdt_attention")
    private String xpbdtAttention;

    /**
     * 芯片半导体详细说明
     */
    @Column(name = "xpbdt_detail")
    private String xpbdtDetail;

    /**
     * 金融科技是否关注
     */
    @Column(name = "jrkj_attention")
    private String jrkjAttention;

    /**
     * 金融科技详细说明
     */
    @Column(name = "jrkj_detail")
    private String jrkjDetail;

    /**
     * 教育科技是否关注
     */
    @Column(name = "jykj_attention")
    private String jykjAttention;

    /**
     * 教育关注详细说明
     */
    @Column(name = "jykj_detail")
    private String jykjDetail;

    /**
     * 精准医疗是否关注
     */
    @Column(name = "jzyl_attention")
    private String jzylAttention;

    /**
     * 精准医疗详细说明
     */
    @Column(name = "jzyl_detail")
    private String jzylDetail;

    /**
     * 企业服务是否关注
     */
    @Column(name = "qyfw_attention")
    private String qyfwAttention;

    /**
     * 企业服务详细说明
     */
    @Column(name = "qyfw_detail")
    private String qyfwDetail;

    /**
     * 消费升级是否关注
     */
    @Column(name = "xfsj_attention")
    private String xfsjAttention;

    /**
     * 消费升级详细说明
     */
    @Column(name = "xfsj_detail")
    private String xfsjDetail;

    /**
     * 文娱传媒是否关注
     */
    @Column(name = "wycm_attention")
    private String wycmAttention;

    /**
     * 文娱传媒详情
     */
    @Column(name = "wycm_detail")
    private String wycmDetail;

    /**
     * TMT是否关注
     */
    @Column(name = "tmt_attention")
    private String tmtAttention;

    /**
     * 新材料是否关注
     */
    @Column(name = "xcl_attenton")
    private String xclAttenton;

    /**
     * 新能源是否关注
     */
    @Column(name = "xny_attention")
    private String xnyAttention;

    /**
     * 物联网是否关注
     */
    @Column(name = "wlw_attention")
    private String wlwAttention;

    /**
     * 区块链是否关注
     */
    @Column(name = "qkl_attention")
    private String qklAttention;

    /**
     * 互联网是否关注
     */
    @Column(name = "hlw_attention")
    private String hlwAttention;

    /**
     * 互联网详细说明
     */
    @Column(name = "hlw_detail")
    private String hlwDetail;

    /**
     * ar/vr是否关注
     */
    @Column(name = "ar_vr_attention")
    private String arVrAttention;

    /**
     * 其他
     */
    private String other;

    /**
     * 天使轮
     */
    @Column(name = "anger_round")
    private String angerRound;

    /**
     * preA
     */
    @Column(name = "pre_a_round")
    private String preARound;

    /**
     * a
     */
    @Column(name = "a_round")
    private String aRound;

    /**
     * b
     */
    @Column(name = "b_round")
    private String bRound;

    /**
     * c
     */
    @Column(name = "c_round")
    private String cRound;

    /**
     * 并购
     */
    @Column(name = "binggo_round")
    private String binggoRound;

    /**
     * D后
     */
    @Column(name = "d_round")
    private String dRound;

    /**
     * Pre-IPO
     */
    @Column(name = "pre_ipo_round")
    private String preIpoRound;

    /**
     * 常驻城市
     */
    private String city;

    /**
     * 单笔投资额度
     */
    @Column(name = "one_investment_count")
    private String oneInvestmentCount;

    /**
     * 计划年投资数量
     */
    @Column(name = "investment_num_future")
    private String investmentNumFuture;

    /**
     * 投资偏好及过往投资案例简介
     */
    @Column(name = "investment_prepare")
    private String investmentPrepare;

    /**
     * 机构领域详细描述存放字段
     */
    @Column(name = "segmentation_other_description")
    private String segmentationOtherDescription;

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
     * 获取偏好及注意事项
     *
     * @return preference_and_precautions - 偏好及注意事项
     */
    public String getPreferenceAndPrecautions() {
        return preferenceAndPrecautions;
    }

    /**
     * 设置偏好及注意事项
     *
     * @param preferenceAndPrecautions 偏好及注意事项
     */
    public void setPreferenceAndPrecautions(String preferenceAndPrecautions) {
        this.preferenceAndPrecautions = preferenceAndPrecautions;
    }

    /**
     * 获取建群日期
     *
     * @return create_wechat_date - 建群日期
     */
    public String getCreateWechatDate() {
        return createWechatDate;
    }

    /**
     * 设置建群日期
     *
     * @param createWechatDate 建群日期
     */
    public void setCreateWechatDate(String createWechatDate) {
        this.createWechatDate = createWechatDate;
    }

    /**
     * 获取群号
     *
     * @return wechat_number - 群号
     */
    public String getWechatNumber() {
        return wechatNumber;
    }

    /**
     * 设置群号
     *
     * @param wechatNumber 群号
     */
    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }

    /**
     * 获取机构名称
     *
     * @return institution_name - 机构名称
     */
    public String getInstitutionName() {
        return institutionName;
    }

    /**
     * 设置机构名称
     *
     * @param institutionName 机构名称
     */
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    /**
     * 获取负责人
     *
     * @return charge_people - 负责人
     */
    public String getChargePeople() {
        return chargePeople;
    }

    /**
     * 设置负责人
     *
     * @param chargePeople 负责人
     */
    public void setChargePeople(String chargePeople) {
        this.chargePeople = chargePeople;
    }

    /**
     * 获取机构简介
     *
     * @return institution_desc - 机构简介
     */
    public String getInstitutionDesc() {
        return institutionDesc;
    }

    /**
     * 设置机构简介
     *
     * @param institutionDesc 机构简介
     */
    public void setInstitutionDesc(String institutionDesc) {
        this.institutionDesc = institutionDesc;
    }

    /**
     * 获取基金规模
     *
     * @return fund_size - 基金规模
     */
    public String getFundSize() {
        return fundSize;
    }

    /**
     * 设置基金规模
     *
     * @param fundSize 基金规模
     */
    public void setFundSize(String fundSize) {
        this.fundSize = fundSize;
    }

    /**
     * 获取机构等级
     *
     * @return institution_level - 机构等级
     */
    public String getInstitutionLevel() {
        return institutionLevel;
    }

    /**
     * 设置机构等级
     *
     * @param institutionLevel 机构等级
     */
    public void setInstitutionLevel(String institutionLevel) {
        this.institutionLevel = institutionLevel;
    }

    /**
     * 获取机构分类
     *
     * @return institution_classify - 机构分类
     */
    public String getInstitutionClassify() {
        return institutionClassify;
    }

    /**
     * 设置机构分类
     *
     * @param institutionClassify 机构分类
     */
    public void setInstitutionClassify(String institutionClassify) {
        this.institutionClassify = institutionClassify;
    }

    /**
     * 获取机构内部架构
     *
     * @return institution_internal_organization - 机构内部架构
     */
    public String getInstitutionInternalOrganization() {
        return institutionInternalOrganization;
    }

    /**
     * 设置机构内部架构
     *
     * @param institutionInternalOrganization 机构内部架构
     */
    public void setInstitutionInternalOrganization(String institutionInternalOrganization) {
        this.institutionInternalOrganization = institutionInternalOrganization;
    }

    /**
     * 获取机构来源
     *
     * @return institution_source - 机构来源
     */
    public String getInstitutionSource() {
        return institutionSource;
    }

    /**
     * 设置机构来源
     *
     * @param institutionSource 机构来源
     */
    public void setInstitutionSource(String institutionSource) {
        this.institutionSource = institutionSource;
    }

    /**
     * 获取资料是否完整
     *
     * @return data_full_yn - 资料是否完整
     */
    public Integer getDataFullYn() {
        return dataFullYn;
    }

    /**
     * 设置资料是否完整
     *
     * @param dataFullYn 资料是否完整
     */
    public void setDataFullYn(Integer dataFullYn) {
        this.dataFullYn = dataFullYn;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取职务
     *
     * @return company_duties - 职务
     */
    public String getCompanyDuties() {
        return companyDuties;
    }

    /**
     * 设置职务
     *
     * @param companyDuties 职务
     */
    public void setCompanyDuties(String companyDuties) {
        this.companyDuties = companyDuties;
    }

    /**
     * 获取名片
     *
     * @return work_card - 名片
     */
    public String getWorkCard() {
        return workCard;
    }

    /**
     * 设置名片
     *
     * @param workCard 名片
     */
    public void setWorkCard(String workCard) {
        this.workCard = workCard;
    }

    /**
     * 获取人工智能是否关注
     *
     * @return rgzl_attention - 人工智能是否关注
     */
    public String getRgzlAttention() {
        return rgzlAttention;
    }

    /**
     * 设置人工智能是否关注
     *
     * @param rgzlAttention 人工智能是否关注
     */
    public void setRgzlAttention(String rgzlAttention) {
        this.rgzlAttention = rgzlAttention;
    }

    /**
     * 获取人工智能详细描述
     *
     * @return rgzl_detail - 人工智能详细描述
     */
    public String getRgzlDetail() {
        return rgzlDetail;
    }

    /**
     * 设置人工智能详细描述
     *
     * @param rgzlDetail 人工智能详细描述
     */
    public void setRgzlDetail(String rgzlDetail) {
        this.rgzlDetail = rgzlDetail;
    }

    /**
     * 获取智能制造是否关注
     *
     * @return znzz_attention - 智能制造是否关注
     */
    public String getZnzzAttention() {
        return znzzAttention;
    }

    /**
     * 设置智能制造是否关注
     *
     * @param znzzAttention 智能制造是否关注
     */
    public void setZnzzAttention(String znzzAttention) {
        this.znzzAttention = znzzAttention;
    }

    /**
     * 获取智能制造详细说明
     *
     * @return znzz_detail - 智能制造详细说明
     */
    public String getZnzzDetail() {
        return znzzDetail;
    }

    /**
     * 设置智能制造详细说明
     *
     * @param znzzDetail 智能制造详细说明
     */
    public void setZnzzDetail(String znzzDetail) {
        this.znzzDetail = znzzDetail;
    }

    /**
     * 获取汽车交通和物流关注
     *
     * @return qcjthwl_attention - 汽车交通和物流关注
     */
    public String getQcjthwlAttention() {
        return qcjthwlAttention;
    }

    /**
     * 设置汽车交通和物流关注
     *
     * @param qcjthwlAttention 汽车交通和物流关注
     */
    public void setQcjthwlAttention(String qcjthwlAttention) {
        this.qcjthwlAttention = qcjthwlAttention;
    }

    /**
     * 获取汽车交通和物流详细说明
     *
     * @return qcjthwl_detail - 汽车交通和物流详细说明
     */
    public String getQcjthwlDetail() {
        return qcjthwlDetail;
    }

    /**
     * 设置汽车交通和物流详细说明
     *
     * @param qcjthwlDetail 汽车交通和物流详细说明
     */
    public void setQcjthwlDetail(String qcjthwlDetail) {
        this.qcjthwlDetail = qcjthwlDetail;
    }

    /**
     * 获取安全科技是否关注
     *
     * @return aqkj_attention - 安全科技是否关注
     */
    public String getAqkjAttention() {
        return aqkjAttention;
    }

    /**
     * 设置安全科技是否关注
     *
     * @param aqkjAttention 安全科技是否关注
     */
    public void setAqkjAttention(String aqkjAttention) {
        this.aqkjAttention = aqkjAttention;
    }

    /**
     * 获取安全科技详细说明
     *
     * @return aqkj_detail - 安全科技详细说明
     */
    public String getAqkjDetail() {
        return aqkjDetail;
    }

    /**
     * 设置安全科技详细说明
     *
     * @param aqkjDetail 安全科技详细说明
     */
    public void setAqkjDetail(String aqkjDetail) {
        this.aqkjDetail = aqkjDetail;
    }

    /**
     * 获取芯片半导体是否关注
     *
     * @return xpbdt_attention - 芯片半导体是否关注
     */
    public String getXpbdtAttention() {
        return xpbdtAttention;
    }

    /**
     * 设置芯片半导体是否关注
     *
     * @param xpbdtAttention 芯片半导体是否关注
     */
    public void setXpbdtAttention(String xpbdtAttention) {
        this.xpbdtAttention = xpbdtAttention;
    }

    /**
     * 获取芯片半导体详细说明
     *
     * @return xpbdt_detail - 芯片半导体详细说明
     */
    public String getXpbdtDetail() {
        return xpbdtDetail;
    }

    /**
     * 设置芯片半导体详细说明
     *
     * @param xpbdtDetail 芯片半导体详细说明
     */
    public void setXpbdtDetail(String xpbdtDetail) {
        this.xpbdtDetail = xpbdtDetail;
    }

    /**
     * 获取金融科技是否关注
     *
     * @return jrkj_attention - 金融科技是否关注
     */
    public String getJrkjAttention() {
        return jrkjAttention;
    }

    /**
     * 设置金融科技是否关注
     *
     * @param jrkjAttention 金融科技是否关注
     */
    public void setJrkjAttention(String jrkjAttention) {
        this.jrkjAttention = jrkjAttention;
    }

    /**
     * 获取金融科技详细说明
     *
     * @return jrkj_detail - 金融科技详细说明
     */
    public String getJrkjDetail() {
        return jrkjDetail;
    }

    /**
     * 设置金融科技详细说明
     *
     * @param jrkjDetail 金融科技详细说明
     */
    public void setJrkjDetail(String jrkjDetail) {
        this.jrkjDetail = jrkjDetail;
    }

    /**
     * 获取教育科技是否关注
     *
     * @return jykj_attention - 教育科技是否关注
     */
    public String getJykjAttention() {
        return jykjAttention;
    }

    /**
     * 设置教育科技是否关注
     *
     * @param jykjAttention 教育科技是否关注
     */
    public void setJykjAttention(String jykjAttention) {
        this.jykjAttention = jykjAttention;
    }

    /**
     * 获取教育关注详细说明
     *
     * @return jykj_detail - 教育关注详细说明
     */
    public String getJykjDetail() {
        return jykjDetail;
    }

    /**
     * 设置教育关注详细说明
     *
     * @param jykjDetail 教育关注详细说明
     */
    public void setJykjDetail(String jykjDetail) {
        this.jykjDetail = jykjDetail;
    }

    /**
     * 获取精准医疗是否关注
     *
     * @return jzyl_attention - 精准医疗是否关注
     */
    public String getJzylAttention() {
        return jzylAttention;
    }

    /**
     * 设置精准医疗是否关注
     *
     * @param jzylAttention 精准医疗是否关注
     */
    public void setJzylAttention(String jzylAttention) {
        this.jzylAttention = jzylAttention;
    }

    /**
     * 获取精准医疗详细说明
     *
     * @return jzyl_detail - 精准医疗详细说明
     */
    public String getJzylDetail() {
        return jzylDetail;
    }

    /**
     * 设置精准医疗详细说明
     *
     * @param jzylDetail 精准医疗详细说明
     */
    public void setJzylDetail(String jzylDetail) {
        this.jzylDetail = jzylDetail;
    }

    /**
     * 获取企业服务是否关注
     *
     * @return qyfw_attention - 企业服务是否关注
     */
    public String getQyfwAttention() {
        return qyfwAttention;
    }

    /**
     * 设置企业服务是否关注
     *
     * @param qyfwAttention 企业服务是否关注
     */
    public void setQyfwAttention(String qyfwAttention) {
        this.qyfwAttention = qyfwAttention;
    }

    /**
     * 获取企业服务详细说明
     *
     * @return qyfw_detail - 企业服务详细说明
     */
    public String getQyfwDetail() {
        return qyfwDetail;
    }

    /**
     * 设置企业服务详细说明
     *
     * @param qyfwDetail 企业服务详细说明
     */
    public void setQyfwDetail(String qyfwDetail) {
        this.qyfwDetail = qyfwDetail;
    }

    /**
     * 获取消费升级是否关注
     *
     * @return xfsj_attention - 消费升级是否关注
     */
    public String getXfsjAttention() {
        return xfsjAttention;
    }

    /**
     * 设置消费升级是否关注
     *
     * @param xfsjAttention 消费升级是否关注
     */
    public void setXfsjAttention(String xfsjAttention) {
        this.xfsjAttention = xfsjAttention;
    }

    /**
     * 获取消费升级详细说明
     *
     * @return xfsj_detail - 消费升级详细说明
     */
    public String getXfsjDetail() {
        return xfsjDetail;
    }

    /**
     * 设置消费升级详细说明
     *
     * @param xfsjDetail 消费升级详细说明
     */
    public void setXfsjDetail(String xfsjDetail) {
        this.xfsjDetail = xfsjDetail;
    }

    /**
     * 获取文娱传媒是否关注
     *
     * @return wycm_attention - 文娱传媒是否关注
     */
    public String getWycmAttention() {
        return wycmAttention;
    }

    /**
     * 设置文娱传媒是否关注
     *
     * @param wycmAttention 文娱传媒是否关注
     */
    public void setWycmAttention(String wycmAttention) {
        this.wycmAttention = wycmAttention;
    }

    /**
     * 获取文娱传媒详情
     *
     * @return wycm_detail - 文娱传媒详情
     */
    public String getWycmDetail() {
        return wycmDetail;
    }

    /**
     * 设置文娱传媒详情
     *
     * @param wycmDetail 文娱传媒详情
     */
    public void setWycmDetail(String wycmDetail) {
        this.wycmDetail = wycmDetail;
    }

    /**
     * 获取TMT是否关注
     *
     * @return tmt_attention - TMT是否关注
     */
    public String getTmtAttention() {
        return tmtAttention;
    }

    /**
     * 设置TMT是否关注
     *
     * @param tmtAttention TMT是否关注
     */
    public void setTmtAttention(String tmtAttention) {
        this.tmtAttention = tmtAttention;
    }

    /**
     * 获取新材料是否关注
     *
     * @return xcl_attenton - 新材料是否关注
     */
    public String getXclAttenton() {
        return xclAttenton;
    }

    /**
     * 设置新材料是否关注
     *
     * @param xclAttenton 新材料是否关注
     */
    public void setXclAttenton(String xclAttenton) {
        this.xclAttenton = xclAttenton;
    }

    /**
     * 获取新能源是否关注
     *
     * @return xny_attention - 新能源是否关注
     */
    public String getXnyAttention() {
        return xnyAttention;
    }

    /**
     * 设置新能源是否关注
     *
     * @param xnyAttention 新能源是否关注
     */
    public void setXnyAttention(String xnyAttention) {
        this.xnyAttention = xnyAttention;
    }

    /**
     * 获取物联网是否关注
     *
     * @return wlw_attention - 物联网是否关注
     */
    public String getWlwAttention() {
        return wlwAttention;
    }

    /**
     * 设置物联网是否关注
     *
     * @param wlwAttention 物联网是否关注
     */
    public void setWlwAttention(String wlwAttention) {
        this.wlwAttention = wlwAttention;
    }

    /**
     * 获取区块链是否关注
     *
     * @return qkl_attention - 区块链是否关注
     */
    public String getQklAttention() {
        return qklAttention;
    }

    /**
     * 设置区块链是否关注
     *
     * @param qklAttention 区块链是否关注
     */
    public void setQklAttention(String qklAttention) {
        this.qklAttention = qklAttention;
    }

    /**
     * 获取互联网是否关注
     *
     * @return hlw_attention - 互联网是否关注
     */
    public String getHlwAttention() {
        return hlwAttention;
    }

    /**
     * 设置互联网是否关注
     *
     * @param hlwAttention 互联网是否关注
     */
    public void setHlwAttention(String hlwAttention) {
        this.hlwAttention = hlwAttention;
    }

    /**
     * 获取互联网详细说明
     *
     * @return hlw_detail - 互联网详细说明
     */
    public String getHlwDetail() {
        return hlwDetail;
    }

    /**
     * 设置互联网详细说明
     *
     * @param hlwDetail 互联网详细说明
     */
    public void setHlwDetail(String hlwDetail) {
        this.hlwDetail = hlwDetail;
    }

    /**
     * 获取ar/vr是否关注
     *
     * @return ar_vr_attention - ar/vr是否关注
     */
    public String getArVrAttention() {
        return arVrAttention;
    }

    /**
     * 设置ar/vr是否关注
     *
     * @param arVrAttention ar/vr是否关注
     */
    public void setArVrAttention(String arVrAttention) {
        this.arVrAttention = arVrAttention;
    }

    /**
     * 获取其他
     *
     * @return other - 其他
     */
    public String getOther() {
        return other;
    }

    /**
     * 设置其他
     *
     * @param other 其他
     */
    public void setOther(String other) {
        this.other = other;
    }

    /**
     * 获取天使轮
     *
     * @return anger_round - 天使轮
     */
    public String getAngerRound() {
        return angerRound;
    }

    /**
     * 设置天使轮
     *
     * @param angerRound 天使轮
     */
    public void setAngerRound(String angerRound) {
        this.angerRound = angerRound;
    }

    /**
     * 获取preA
     *
     * @return pre_a_round - preA
     */
    public String getPreARound() {
        return preARound;
    }

    /**
     * 设置preA
     *
     * @param preARound preA
     */
    public void setPreARound(String preARound) {
        this.preARound = preARound;
    }

    /**
     * 获取a
     *
     * @return a_round - a
     */
    public String getaRound() {
        return aRound;
    }

    /**
     * 设置a
     *
     * @param aRound a
     */
    public void setaRound(String aRound) {
        this.aRound = aRound;
    }

    /**
     * 获取b
     *
     * @return b_round - b
     */
    public String getbRound() {
        return bRound;
    }

    /**
     * 设置b
     *
     * @param bRound b
     */
    public void setbRound(String bRound) {
        this.bRound = bRound;
    }

    /**
     * 获取c
     *
     * @return c_round - c
     */
    public String getcRound() {
        return cRound;
    }

    /**
     * 设置c
     *
     * @param cRound c
     */
    public void setcRound(String cRound) {
        this.cRound = cRound;
    }

    /**
     * 获取并购
     *
     * @return binggo_round - 并购
     */
    public String getBinggoRound() {
        return binggoRound;
    }

    /**
     * 设置并购
     *
     * @param binggoRound 并购
     */
    public void setBinggoRound(String binggoRound) {
        this.binggoRound = binggoRound;
    }

    /**
     * 获取D后
     *
     * @return d_round - D后
     */
    public String getdRound() {
        return dRound;
    }

    /**
     * 设置D后
     *
     * @param dRound D后
     */
    public void setdRound(String dRound) {
        this.dRound = dRound;
    }

    /**
     * 获取Pre-IPO
     *
     * @return pre_ipo_round - Pre-IPO
     */
    public String getPreIpoRound() {
        return preIpoRound;
    }

    /**
     * 设置Pre-IPO
     *
     * @param preIpoRound Pre-IPO
     */
    public void setPreIpoRound(String preIpoRound) {
        this.preIpoRound = preIpoRound;
    }

    /**
     * 获取常驻城市
     *
     * @return city - 常驻城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置常驻城市
     *
     * @param city 常驻城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取单笔投资额度
     *
     * @return one_investment_count - 单笔投资额度
     */
    public String getOneInvestmentCount() {
        return oneInvestmentCount;
    }

    /**
     * 设置单笔投资额度
     *
     * @param oneInvestmentCount 单笔投资额度
     */
    public void setOneInvestmentCount(String oneInvestmentCount) {
        this.oneInvestmentCount = oneInvestmentCount;
    }

    /**
     * 获取计划年投资数量
     *
     * @return investment_num_future - 计划年投资数量
     */
    public String getInvestmentNumFuture() {
        return investmentNumFuture;
    }

    /**
     * 设置计划年投资数量
     *
     * @param investmentNumFuture 计划年投资数量
     */
    public void setInvestmentNumFuture(String investmentNumFuture) {
        this.investmentNumFuture = investmentNumFuture;
    }

    /**
     * 获取投资偏好及过往投资案例简介
     *
     * @return investment_prepare - 投资偏好及过往投资案例简介
     */
    public String getInvestmentPrepare() {
        return investmentPrepare;
    }

    /**
     * 设置投资偏好及过往投资案例简介
     *
     * @param investmentPrepare 投资偏好及过往投资案例简介
     */
    public void setInvestmentPrepare(String investmentPrepare) {
        this.investmentPrepare = investmentPrepare;
    }

    /**
     * 获取机构领域详细描述存放字段
     *
     * @return segmentation_other_description - 机构领域详细描述存放字段
     */
    public String getSegmentationOtherDescription() {
        return segmentationOtherDescription;
    }

    /**
     * 设置机构领域详细描述存放字段
     *
     * @param segmentationOtherDescription 机构领域详细描述存放字段
     */
    public void setSegmentationOtherDescription(String segmentationOtherDescription) {
        this.segmentationOtherDescription = segmentationOtherDescription;
    }
}