package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditInvestHighlightDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
public interface CollectProjectAuditInvestHighlightService {
    CommonDto<CollectProjectAuditInvestHighlightDto> getCollectProjectAuditInvestHighlight(Integer projectId);
}
