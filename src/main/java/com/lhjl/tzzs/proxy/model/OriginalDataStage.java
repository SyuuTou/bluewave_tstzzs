package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "original_data_stage")
public class OriginalDataStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 导入数据表id
     */
    @Column(name = "original_data_id")
    private Integer originalDataId;

    /**
     * 阶段元数据表id

     */
    @Column(name = "meta_stage_id")
    private Integer metaStageId;

    /**
     * 阶段名称
     */
    @Column(name = "stage_name")
    private String stageName;

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
     * 获取导入数据表id
     *
     * @return original_data_id - 导入数据表id
     */
    public Integer getOriginalDataId() {
        return originalDataId;
    }

    /**
     * 设置导入数据表id
     *
     * @param originalDataId 导入数据表id
     */
    public void setOriginalDataId(Integer originalDataId) {
        this.originalDataId = originalDataId;
    }

    /**
     * 获取阶段元数据表id

     *
     * @return meta_stage_id - 阶段元数据表id

     */
    public Integer getMetaStageId() {
        return metaStageId;
    }

    /**
     * 设置阶段元数据表id

     *
     * @param metaStageId 阶段元数据表id

     */
    public void setMetaStageId(Integer metaStageId) {
        this.metaStageId = metaStageId;
    }

    /**
     * 获取阶段名称
     *
     * @return stage_name - 阶段名称
     */
    public String getStageName() {
        return stageName;
    }

    /**
     * 设置阶段名称
     *
     * @param stageName 阶段名称
     */
    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
}