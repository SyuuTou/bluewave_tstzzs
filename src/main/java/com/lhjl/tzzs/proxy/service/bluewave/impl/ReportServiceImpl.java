package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.bluewave.ReportPutBody;
import com.lhjl.tzzs.proxy.dto.bluewave.ReportReqBody;
import com.lhjl.tzzs.proxy.model.Report;
import com.lhjl.tzzs.proxy.service.bluewave.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);


    @Override
    public CommonDto<List<Report>> queryReport(ReportReqBody reqBody) {
        return null;
    }

    @Override
    public CommonDto<Report> getReportById(Integer id) {
        return null;
    }

    @Override
    public CommonDto<String> saveOrUpdate(ReportPutBody reqBody) {
        return null;
    }
}
