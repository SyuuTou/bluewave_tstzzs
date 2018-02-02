package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorOperateLogDto;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public interface InvestorProcessTrackingService {

    CommonDto<String> addorupdateOperateLog(InvestorOperateLogDto body);

    CommonDto<List<InvestorOperateLogDto>> getInvestorOperateLogList(Integer investorId);

    CommonDto<String> deleteInvestorOperateLog(Integer logId);
}
