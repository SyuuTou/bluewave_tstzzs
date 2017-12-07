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
     * 是否删除，0表示删除，1表示未删除
     */
    private Integer yn;

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
     * 获取是否删除，0表示删除，1表示未删除
     *
     * @return yn - 是否删除，0表示删除，1表示未删除
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否删除，0表示删除，1表示未删除
     *
     * @param yn 是否删除，0表示删除，1表示未删除
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}