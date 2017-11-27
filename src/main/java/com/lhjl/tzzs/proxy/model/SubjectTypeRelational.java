package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "subject_type_relational")
public class SubjectTypeRelational {
    /**
     * 主体表id
     */
    @Id
    @Column(name = "subject_id")
    private Integer subjectId;

    /**
     * 主体类型表id
     */
    @Id
    @Column(name = "subject_type_id")
    private Integer subjectTypeId;

    /**
     * 获取主体表id
     *
     * @return subject_id - 主体表id
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * 设置主体表id
     *
     * @param subjectId 主体表id
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 获取主体类型表id
     *
     * @return subject_type_id - 主体类型表id
     */
    public Integer getSubjectTypeId() {
        return subjectTypeId;
    }

    /**
     * 设置主体类型表id
     *
     * @param subjectTypeId 主体类型表id
     */
    public void setSubjectTypeId(Integer subjectTypeId) {
        this.subjectTypeId = subjectTypeId;
    }
}