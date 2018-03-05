package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "original_data_classify")
public class OriginalDataClassify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分类名称
     */
    @Column(name = "classify_name")
    private String classifyName;

    /**
     * 导入数据id
     */
    @Column(name = "original_data_id")
    private Integer originalDataId;

    /**
     * 机构分类id
     */
    @Column(name = "meta_invest_type_id")
    private Integer metaInvestTypeId;

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
     * 获取分类名称
     *
     * @return classify_name - 分类名称
     */
    public String getClassifyName() {
        return classifyName;
    }

    /**
     * 设置分类名称
     *
     * @param classifyName 分类名称
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
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
     * 获取机构分类id
     *
     * @return meta_invest_type_id - 机构分类id
     */
    public Integer getMetaInvestTypeId() {
        return metaInvestTypeId;
    }

    /**
     * 设置机构分类id
     *
     * @param metaInvestTypeId 机构分类id
     */
    public void setMetaInvestTypeId(Integer metaInvestTypeId) {
        this.metaInvestTypeId = metaInvestTypeId;
    }
}