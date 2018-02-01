package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditFinancingDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
public interface CollectProjectAuditFinancingService {
    CommonDto<CollectProjectAuditFinancingDto> getCollectProjectAuditFinancing(Integer projectId);
}
