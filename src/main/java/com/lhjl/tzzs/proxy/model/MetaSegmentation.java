package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "meta_segmentation")
public class MetaSegmentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 领域logo
     */
    @Column(name = "segmentation_logo")
    private String segmentationLogo;

    /**
     * 是否有效
     */
    private Integer yn;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建者
     */
    private String creater;

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
     * 获取领域logo
     *
     * @return segmentation_logo - 领域logo
     */
    public String getSegmentationLogo() {
        return segmentationLogo;
    }

    /**
     * 设置领域logo
     *
     * @param segmentationLogo 领域logo
     */
    public void setSegmentationLogo(String segmentationLogo) {
        this.segmentationLogo = segmentationLogo;
    }

    /**
     * 获取是否有效
     *
     * @return yn - 是否有效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效
     *
     * @param yn 是否有效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
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
     * 获取创建者
     *
     * @return creater - 创建者
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 设置创建者
     *
     * @param creater 创建者
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }
}