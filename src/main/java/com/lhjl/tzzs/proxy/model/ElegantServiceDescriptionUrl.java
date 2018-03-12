package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "elegant_service_description_url")
public class ElegantServiceDescriptionUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "elegant_service_id")
    private Integer elegantServiceId;

    /**
     * 详情图片地址
     */
    private String url;

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
     * @return elegant_service_id
     */
    public Integer getElegantServiceId() {
        return elegantServiceId;
    }

    /**
     * @param elegantServiceId
     */
    public void setElegantServiceId(Integer elegantServiceId) {
        this.elegantServiceId = elegantServiceId;
    }

    /**
     * 获取详情图片地址
     *
     * @return url - 详情图片地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置详情图片地址
     *
     * @param url 详情图片地址
     */
    public void setUrl(String url) {
        this.url = url;
    }
}