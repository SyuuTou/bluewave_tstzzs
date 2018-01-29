package com.lhjl.tzzs.proxy.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "elegant_service")
public class ElegantService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 服务名称
     */
    @Column(name = "service_name")
    private String serviceName;

    /**
     * 原价
     */
    @Column(name = "original_price")
    private BigDecimal originalPrice;

    /**
     * vip价格
     */
    @Column(name = "vip_price")
    private BigDecimal vipPrice;

    /**
     * 会员价前坠描述，例如：限时、特价、vip会员
     */
    @Column(name = "pre_vip_price_descript")
    private String preVipPriceDescript;

    /**
     * 价格单位，0表示人民币，1表示美元
     */
    @Column(name = "price_unit")
    private Integer priceUnit;

    /**
     * 单位，这里的单位指的是，5800/次，3800/人 中的“次”和“人”；
     */
    private String unit;

    /**
     * 背景图
     */
    @Column(name = "background_picture")
    private String backgroundPicture;

    /**
     * 开始时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 开启关闭
     */
    @Column(name = "on_off")
    private Integer onOff;

    /**
     * 是否推荐，0表示不推荐，1表示推荐
     */
    @Column(name = "recommend_yn")
    private Integer recommendYn;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 场景key
     */
    @Column(name = "scence_key")
    private String scenceKey;

    /**
     * 是否隐藏，0表示已删除，1表示未删除（是否删除）
     */
    private Integer yn;

    /**
     * 网页切换开关，0表示显示小程序页面，1表示显示网页
     */
    @Column(name = "web_switch")
    private Integer webSwitch;

    /**
     * 应用id
     */
    private Integer appid;

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
     * 获取服务名称
     *
     * @return service_name - 服务名称
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * 设置服务名称
     *
     * @param serviceName 服务名称
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * 获取原价
     *
     * @return original_price - 原价
     */
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    /**
     * 设置原价
     *
     * @param originalPrice 原价
     */
    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * 获取vip价格
     *
     * @return vip_price - vip价格
     */
    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    /**
     * 设置vip价格
     *
     * @param vipPrice vip价格
     */
    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    /**
     * 获取会员价前坠描述，例如：限时、特价、vip会员
     *
     * @return pre_vip_price_descript - 会员价前坠描述，例如：限时、特价、vip会员
     */
    public String getPreVipPriceDescript() {
        return preVipPriceDescript;
    }

    /**
     * 设置会员价前坠描述，例如：限时、特价、vip会员
     *
     * @param preVipPriceDescript 会员价前坠描述，例如：限时、特价、vip会员
     */
    public void setPreVipPriceDescript(String preVipPriceDescript) {
        this.preVipPriceDescript = preVipPriceDescript;
    }

    /**
     * 获取价格单位，0表示人民币，1表示美元
     *
     * @return price_unit - 价格单位，0表示人民币，1表示美元
     */
    public Integer getPriceUnit() {
        return priceUnit;
    }

    /**
     * 设置价格单位，0表示人民币，1表示美元
     *
     * @param priceUnit 价格单位，0表示人民币，1表示美元
     */
    public void setPriceUnit(Integer priceUnit) {
        this.priceUnit = priceUnit;
    }

    /**
     * 获取单位，这里的单位指的是，5800/次，3800/人 中的“次”和“人”；
     *
     * @return unit - 单位，这里的单位指的是，5800/次，3800/人 中的“次”和“人”；
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置单位，这里的单位指的是，5800/次，3800/人 中的“次”和“人”；
     *
     * @param unit 单位，这里的单位指的是，5800/次，3800/人 中的“次”和“人”；
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取背景图
     *
     * @return background_picture - 背景图
     */
    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    /**
     * 设置背景图
     *
     * @param backgroundPicture 背景图
     */
    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture;
    }

    /**
     * 获取开始时间
     *
     * @return begin_time - 开始时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置开始时间
     *
     * @param beginTime 开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取开启关闭
     *
     * @return on_off - 开启关闭
     */
    public Integer getOnOff() {
        return onOff;
    }

    /**
     * 设置开启关闭
     *
     * @param onOff 开启关闭
     */
    public void setOnOff(Integer onOff) {
        this.onOff = onOff;
    }

    /**
     * 获取是否推荐，0表示不推荐，1表示推荐
     *
     * @return recommend_yn - 是否推荐，0表示不推荐，1表示推荐
     */
    public Integer getRecommendYn() {
        return recommendYn;
    }

    /**
     * 设置是否推荐，0表示不推荐，1表示推荐
     *
     * @param recommendYn 是否推荐，0表示不推荐，1表示推荐
     */
    public void setRecommendYn(Integer recommendYn) {
        this.recommendYn = recommendYn;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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
     * 获取场景key
     *
     * @return scence_key - 场景key
     */
    public String getScenceKey() {
        return scenceKey;
    }

    /**
     * 设置场景key
     *
     * @param scenceKey 场景key
     */
    public void setScenceKey(String scenceKey) {
        this.scenceKey = scenceKey;
    }

    /**
     * 获取是否隐藏，0表示已删除，1表示未删除（是否删除）
     *
     * @return yn - 是否隐藏，0表示已删除，1表示未删除（是否删除）
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否隐藏，0表示已删除，1表示未删除（是否删除）
     *
     * @param yn 是否隐藏，0表示已删除，1表示未删除（是否删除）
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }

    /**
     * 获取网页切换开关，0表示显示小程序页面，1表示显示网页
     *
     * @return web_switch - 网页切换开关，0表示显示小程序页面，1表示显示网页
     */
    public Integer getWebSwitch() {
        return webSwitch;
    }

    /**
     * 设置网页切换开关，0表示显示小程序页面，1表示显示网页
     *
     * @param webSwitch 网页切换开关，0表示显示小程序页面，1表示显示网页
     */
    public void setWebSwitch(Integer webSwitch) {
        this.webSwitch = webSwitch;
    }

    /**
     * 获取应用id
     *
     * @return appid - 应用id
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * 设置应用id
     *
     * @param appid 应用id
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }
}