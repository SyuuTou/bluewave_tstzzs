package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditHistoryFinancingDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
public interface CollectProjectAuditHistoryFinancingService {
    CommonDto<List<CollectProjectAuditHistoryFinancingDto>> getCollectProjectAuditHistoryFinancing(Integer projectId);

}
