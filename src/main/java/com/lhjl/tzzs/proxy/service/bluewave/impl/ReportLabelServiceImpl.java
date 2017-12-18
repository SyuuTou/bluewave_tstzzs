package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.lhjl.tzzs.proxy.mapper.ReportLabelMapper;
import com.lhjl.tzzs.proxy.model.ReportLabel;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.ReportLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportLabelServiceImpl extends GenericService implements ReportLabelService {

    @Autowired
    private ReportLabelMapper labelMapper;

    @Override
    public void save(ReportLabel reportLabel) {
        labelMapper.insert(reportLabel);
    }

    @Override
    public void deleteAll(Integer reportId) {
        ReportLabel reportLabel = new ReportLabel();
        reportLabel.setReportId(reportId);
        labelMapper.delete(reportLabel);
    }
}
