package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "report_column")
public class ReportColumn {
    @Column(name = "report_id")
    private Integer reportId;

    @Column(name = "column_id")
    private Integer columnId;

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
     * @return column_id
     */
    public Integer getColumnId() {
        return columnId;
    }

    /**
     * @param columnId
     */
    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }
}