package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "investor_segmentation")
public class InvestorSegmentation {
    /**
     * 投资人id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 领域id
     */
    @Column(name = "segmentation_id")
    private Integer segmentationId;

    /**
     * 获取投资人id
     *
     * @return id - 投资人id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置投资人id
     *
     * @param id 投资人id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取领域id
     *
     * @return segmentation_id - 领域id
     */
    public Integer getSegmentationId() {
        return segmentationId;
    }

    /**
     * 设置领域id
     *
     * @param segmentationId 领域id
     */
    public void setSegmentationId(Integer segmentationId) {
        this.segmentationId = segmentationId;
    }
}