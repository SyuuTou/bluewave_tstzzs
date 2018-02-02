package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorBasicInfoDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorIntroductionDto;
import com.lhjl.tzzs.proxy.model.MetaDiploma;
import com.lhjl.tzzs.proxy.model.MetaRegion;

import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
public interface InvestorBasicinfoService {

    CommonDto<String> addOrUpdateInvestorBasicInfo(InvestorBasicInfoDto body);

    CommonDto<InvestorBasicInfoDto> getInvestorBasicInfo(Integer investorId);

    CommonDto<InvestorIntroductionDto> getInvestorIntroduction(Integer investorId);

    CommonDto<String> addOrUpdateInvestorIntroduction(InvestorIntroductionDto body);

    CommonDto<List<MetaRegion>> getAllContry();

    CommonDto<List<MetaDiploma>> getAllDiploma();
}
