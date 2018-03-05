package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "original_data_segmentation")
public class OriginalDataSegmentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 导入数据id
     */
    @Column(name = "original_data_id")
    private Integer originalDataId;

    /**
     * 领域源数据表id
     */
    @Column(name = "meta_segmentation_id")
    private Integer metaSegmentationId;

    /**
     * 领域名称
     */
    @Column(name = "segmentation_name")
    private String segmentationName;

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
     * 获取导入数据id
     *
     * @return original_data_id - 导入数据id
     */
    public Integer getOriginalDataId() {
        return originalDataId;
    }

    /**
     * 设置导入数据id
     *
     * @param originalDataId 导入数据id
     */
    public void setOriginalDataId(Integer originalDataId) {
        this.originalDataId = originalDataId;
    }

    /**
     * 获取领域源数据表id
     *
     * @return meta_segmentation_id - 领域源数据表id
     */
    public Integer getMetaSegmentationId() {
        return metaSegmentationId;
    }

    /**
     * 设置领域源数据表id
     *
     * @param metaSegmentationId 领域源数据表id
     */
    public void setMetaSegmentationId(Integer metaSegmentationId) {
        this.metaSegmentationId = metaSegmentationId;
    }

    /**
     * 获取领域名称
     *
     * @return segmentation_name - 领域名称
     */
    public String getSegmentationName() {
        return segmentationName;
    }

    /**
     * 设置领域名称
     *
     * @param segmentationName 领域名称
     */
    public void setSegmentationName(String segmentationName) {
        this.segmentationName = segmentationName;
    }
}