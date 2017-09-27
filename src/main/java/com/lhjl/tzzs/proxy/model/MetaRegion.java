package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_region")
public class MetaRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer gid;

    private Integer zid;

    private Integer shengid;

    private Integer shid;

    private Integer quid;

    private String ming;

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
     * @return gid
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * @param gid
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    /**
     * @return zid
     */
    public Integer getZid() {
        return zid;
    }

    /**
     * @param zid
     */
    public void setZid(Integer zid) {
        this.zid = zid;
    }

    /**
     * @return shengid
     */
    public Integer getShengid() {
        return shengid;
    }

    /**
     * @param shengid
     */
    public void setShengid(Integer shengid) {
        this.shengid = shengid;
    }

    /**
     * @return shid
     */
    public Integer getShid() {
        return shid;
    }

    /**
     * @param shid
     */
    public void setShid(Integer shid) {
        this.shid = shid;
    }

    /**
     * @return quid
     */
    public Integer getQuid() {
        return quid;
    }

    /**
     * @param quid
     */
    public void setQuid(Integer quid) {
        this.quid = quid;
    }

    /**
     * @return ming
     */
    public String getMing() {
        return ming;
    }

    /**
     * @param ming
     */
    public void setMing(String ming) {
        this.ming = ming;
    }
}