package com.lhjl.tzzs.proxy.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "report_modules")
public class ReportModules {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章、新闻主键ID
     */
    @Column(name = "report_id")
    private Integer reportId;

    /**
     * 板块标题
     */
    @Column(name = "title_name")
    private String titleName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 文件数量
     */
    private Integer num;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 文件列表
     */
    @Transient
    private List<ReportModulesFiles> reportModulesFilesList;

    public List<ReportModulesFiles> getReportModulesFilesList() {
        return reportModulesFilesList;
    }

    public void setReportModulesFilesList(List<ReportModulesFiles> reportModulesFilesList) {
        this.reportModulesFilesList = reportModulesFilesList;
    }

    /**
     * @return ID
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
     * 获取文章、新闻主键ID
     *
     * @return report_id - 文章、新闻主键ID
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * 设置文章、新闻主键ID
     *
     * @param reportId 文章、新闻主键ID
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * 获取板块标题
     *
     * @return title_name - 板块标题
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * 设置板块标题
     *
     * @param titleName 板块标题
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName;
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
     * 获取文件数量
     *
     * @return num - 文件数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置文件数量
     *
     * @param num 文件数量
     */
    public void setNum(Integer num) {
        this.num = num;
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
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
}