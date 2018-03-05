package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "original_data_city")
public class OriginalDataCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "original_data_id")
    private Integer originalDataId;

    @Column(name = "city_name")
    private String cityName;

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
     * @return original_data_id
     */
    public Integer getOriginalDataId() {
        return originalDataId;
    }

    /**
     * @param originalDataId
     */
    public void setOriginalDataId(Integer originalDataId) {
        this.originalDataId = originalDataId;
    }

    /**
     * @return city_name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}