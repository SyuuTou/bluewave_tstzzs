package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "report_segmentation")
public class ReportSegmentation {
    @Column(name = "report_id")
    private Integer reportId;

    @Column(name = "segmentation_id")
    private Integer segmentationId;

    /**
     * @return report_id
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * @param reportId
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * @return segmentation_id
     */
    public Integer getSegmentationId() {
        return segmentationId;
    }

    /**
     * @param segmentationId
     */
    public void setSegmentationId(Integer segmentationId) {
        this.segmentationId = segmentationId;
    }
}