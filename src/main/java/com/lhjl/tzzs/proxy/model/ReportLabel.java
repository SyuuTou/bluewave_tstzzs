package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "report_label")
public class ReportLabel {
    /**
     * 报告ID
     */
    @Column(name = "report_id")
    private Integer reportId;

    /**
     * 自定义领域标签名称
     */
    private String name;

    /**
     * 获取报告ID
     *
     * @return report_id - 报告ID
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * 设置报告ID
     *
     * @param reportId 报告ID
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * 获取自定义领域标签名称
     *
     * @return name - 自定义领域标签名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置自定义领域标签名称
     *
     * @param name 自定义领域标签名称
     */
    public void setName(String name) {
        this.name = name;
    }
}