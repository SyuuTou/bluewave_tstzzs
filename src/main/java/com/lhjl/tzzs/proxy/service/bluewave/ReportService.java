package com.lhjl.tzzs.proxy.service.bluewave;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.bluewave.ReportReqBody;
import com.lhjl.tzzs.proxy.model.Report;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ReportService {
    CommonDto<List<Map<String,Object>>> queryReport(Integer appId, ReportReqBody reqBody);

    CommonDto<Map<String,Object>> getReportById(Integer appId, Integer id);

    CommonDto<String> saveOrUpdate(Integer appId, ReportReqBody reqBody);

    InputStream generateReportShareImage(Integer reportId);
}
