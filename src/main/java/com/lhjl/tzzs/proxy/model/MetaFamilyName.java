package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_family_name")
public class MetaFamilyName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String family;

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
     * @return family
     */
    public String getFamily() {
        return family;
    }

    /**
     * @param family
     */
    public void setFamily(String family) {
        this.family = family;
    }
}