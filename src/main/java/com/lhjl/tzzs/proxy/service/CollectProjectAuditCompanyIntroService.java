package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditCompanyIntroDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
public interface CollectProjectAuditCompanyIntroService {
    CommonDto<CollectProjectAuditCompanyIntroDto> getCollectProjectAuditCompanyIntro(Integer projectId);
}
