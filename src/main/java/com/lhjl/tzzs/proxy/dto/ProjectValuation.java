package com.lhjl.tzzs.proxy.dto;

public class ProjectValuation {
    private Integer id;
    private Integer type;
    private String segmentation;
    private String city;
    private String edu;
    private String work;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
