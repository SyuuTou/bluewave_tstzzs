package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_diploma")
public class MetaDiploma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学历名称
     */
    @Column(name = "diploma_name")
    private String diplomaName;

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
     * 获取学历名称
     *
     * @return diploma_name - 学历名称
     */
    public String getDiplomaName() {
        return diplomaName;
    }

    /**
     * 设置学历名称
     *
     * @param diplomaName 学历名称
     */
    public void setDiplomaName(String diplomaName) {
        this.diplomaName = diplomaName;
    }
}