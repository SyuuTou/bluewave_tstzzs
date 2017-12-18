package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.lhjl.tzzs.proxy.mapper.ReportColumnMapper;
import com.lhjl.tzzs.proxy.model.ReportColumn;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.ReportColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportColumnServiceImpl extends GenericService implements ReportColumnService {

    @Autowired
    private ReportColumnMapper columnMapper;

    @Override
    public void save(ReportColumn metaColumn) {
            columnMapper.insert(metaColumn);
    }

    @Override
    public void deleteAll(Integer reportId) {
        ReportColumn column = new ReportColumn();
        column.setReportId(reportId);
        columnMapper.delete(column);
    }
}
