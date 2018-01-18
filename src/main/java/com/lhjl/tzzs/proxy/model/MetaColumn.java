package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "meta_column")
public class MetaColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 栏目名称
     */
    private String name;

    @Column(name = "event_key")
    private String eventKey;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否有效
     */
    private Integer yn;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建者
     */
    private String creater;

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
     * 获取栏目名称
     *
     * @return name - 栏目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置栏目名称
     *
     * @param name 栏目名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return event_key
     */
    public String getEventKey() {
        return eventKey;
    }

    /**
     * @param eventKey
     */
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
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
     * 获取创建者
     *
     * @return creater - 创建者
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 设置创建者
     *
     * @param creater 创建者
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

	@Override
	public String toString() {
		return "MetaColumn [id=" + id + ", name=" + name + ", eventKey=" + eventKey + ", sort=" + sort + ", yn=" + yn
				+ ", createTime=" + createTime + ", creater=" + creater + "]";
	}
    
}