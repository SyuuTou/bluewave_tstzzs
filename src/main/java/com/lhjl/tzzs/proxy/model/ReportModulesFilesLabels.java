package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "report_modules_files_labels")
public class ReportModulesFilesLabels {
    /**
     * 文章/新闻模块文件列表ID
     */
    @Column(name = "report_modules_files_id")
    private Integer reportModulesFilesId;

    /**
     * 领域标签
     */
    private String segmentation;

    /**
     * 获取文章/新闻模块文件列表ID
     *
     * @return report_modules_files_id - 文章/新闻模块文件列表ID
     */
    public Integer getReportModulesFilesId() {
        return reportModulesFilesId;
    }

    /**
     * 设置文章/新闻模块文件列表ID
     *
     * @param reportModulesFilesId 文章/新闻模块文件列表ID
     */
    public void setReportModulesFilesId(Integer reportModulesFilesId) {
        this.reportModulesFilesId = reportModulesFilesId;
    }

    /**
     * 获取领域标签
     *
     * @return segmentation - 领域标签
     */
    public String getSegmentation() {
        return segmentation;
    }

    /**
     * 设置领域标签
     *
     * @param segmentation 领域标签
     */
    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }
}