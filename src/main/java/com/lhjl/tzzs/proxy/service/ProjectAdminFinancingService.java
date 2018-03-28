package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.FinancingLogDto.FinancingLogInputDto;
import com.lhjl.tzzs.proxy.dto.FinancingLogDto.FinancingLogOutputDto;

/**
 * Created by lanhaijulang on 2018/1/20.
 */
public interface ProjectAdminFinancingService {

    CommonDto<FinancingLogOutputDto> getFinancingLog(Integer subjectId,Integer subjectType);

    CommonDto<String> addOrUpdateFinancingLog(FinancingLogInputDto body);
}
