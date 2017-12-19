package com.lhjl.tzzs.proxy.service.bluewave;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.bluewave.ReportReqBody;
import com.lhjl.tzzs.proxy.model.Report;

import java.util.List;

public interface ReportService {
    CommonDto<List<Report>> queryReport(ReportReqBody reqBody);

    CommonDto<Report> getReportById(Integer id);

    CommonDto<String> saveOrUpdate(ReportReqBody reqBody);
}
