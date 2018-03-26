package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.FundDto.FundInputDto;
import com.lhjl.tzzs.proxy.dto.FundDto.FundOutputDto;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
public interface ProjectAdminFundService {
    CommonDto<List<FundOutputDto>> getFundList(Integer subjectId,Integer subjectType);

    CommonDto<String> addOrUpdateFund(FundInputDto body);

    CommonDto<String> deleteFund(Integer fundId,Integer subjectType);
}
