package com.lhjl.tzzs.proxy.service.bluewave;

import com.lhjl.tzzs.proxy.model.ReportLabel;

public interface ReportLabelService {
    void save(ReportLabel reportLabel);

    void deleteAll(Integer reportId);
}
