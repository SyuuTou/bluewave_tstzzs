package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_investment_institution_team_type")
public class MetaInvestmentInstitutionTeamType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 排序
     */
    private Integer sort;

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
     * 获取类型名称
     *
     * @return type_name - 类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置类型名称
     *
     * @param typeName 类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
}