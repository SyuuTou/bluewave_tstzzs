package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.lhjl.tzzs.proxy.mapper.ReportSegmentationMapper;
import com.lhjl.tzzs.proxy.model.ReportSegmentation;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.ReportSegmentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportSegmentationServiceImpl extends GenericService implements ReportSegmentationService {

    @Autowired
    private ReportSegmentationMapper segmentationMapper;

    @Override
    public void save(ReportSegmentation reportSegmentation) {
        segmentationMapper.insert(reportSegmentation);
    }

    @Override
    public void deleteAll(Integer reportId) {
        ReportSegmentation segmentation = new ReportSegmentation();
        segmentation.setReportId(reportId);

        segmentationMapper.delete(segmentation);
    }
}
