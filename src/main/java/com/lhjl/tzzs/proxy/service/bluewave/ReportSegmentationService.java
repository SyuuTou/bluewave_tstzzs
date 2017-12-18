package com.lhjl.tzzs.proxy.service.bluewave;

import com.lhjl.tzzs.proxy.model.ReportSegmentation;

public interface ReportSegmentationService {
    void save(ReportSegmentation reportSegmentation);

    void deleteAll(Integer reportId);
}
