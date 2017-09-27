package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "meta_hot_search_word")
public class MetaHotSearchWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 热门搜索
     */
    @Column(name = "hot_word")
    private String hotWord;

    /**
     * 热度/数量，作为排序条件
     */
    private Integer amount;

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
     * 获取热门搜索
     *
     * @return hot_word - 热门搜索
     */
    public String getHotWord() {
        return hotWord;
    }

    /**
     * 设置热门搜索
     *
     * @param hotWord 热门搜索
     */
    public void setHotWord(String hotWord) {
        this.hotWord = hotWord;
    }

    /**
     * 获取热度/数量，作为排序条件
     *
     * @return amount - 热度/数量，作为排序条件
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置热度/数量，作为排序条件
     *
     * @param amount 热度/数量，作为排序条件
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}