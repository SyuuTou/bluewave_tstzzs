package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_project_stage")
public class MetaProjectStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "weighting_factor")
    private Integer weightingFactor;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return weighting_factor
     */
    public Integer getWeightingFactor() {
        return weightingFactor;
    }

    /**
     * @param weightingFactor
     */
    public void setWeightingFactor(Integer weightingFactor) {
        this.weightingFactor = weightingFactor;
    }
}