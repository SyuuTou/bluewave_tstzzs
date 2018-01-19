package com.lhjl.tzzs.proxy.model;

import javax.persistence.*;

@Table(name = "report_company_label")
public class ReportCompanyLabel {
    /**
     * 报告ID
     */
    @Column(name = "report_id")
    private Integer reportId;

    /**
     * 关联公司的标签
     */
    @Column(name = "company_name")
    private String companyName;

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
     * 获取关联公司的标签
     *
     * @return company_name - 关联公司的标签
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置关联公司的标签
     *
     * @param companyName 关联公司的标签
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

	@Override
	public String toString() {
		return "ReportCompanyLabel [reportId=" + reportId + ", companyName=" + companyName + "]";
	}
    
}