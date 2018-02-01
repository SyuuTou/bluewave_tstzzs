package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditBasicInfoDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
public interface CollectProjectAuditBasicInfoService {

    CommonDto<CollectProjectAuditBasicInfoDto> getCollectProjectAuditBasicInfo(Integer projectId);
}
