package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "meta_scene")
public class MetaScene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 场景key
     */
    @Column(name = "`key`")
    private String key;

    /**
     * 场景描述
     */
    @Column(name = "`desc`")
    private String desc;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否有效
     */
    private Integer yn;

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
     * 获取场景key
     *
     * @return key - 场景key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置场景key
     *
     * @param key 场景key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取场景描述
     *
     * @return desc - 场景描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置场景描述
     *
     * @param desc 场景描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否有效
     *
     * @return yn - 是否有效
     */
    public Integer getYn() {
        return yn;
    }

    /**
     * 设置是否有效
     *
     * @param yn 是否有效
     */
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}