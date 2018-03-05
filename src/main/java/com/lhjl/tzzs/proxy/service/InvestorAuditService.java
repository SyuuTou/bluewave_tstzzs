package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorInvestInfoDto;
import com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditdetaildto.*;
import com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditlistdto.InvestorAuditListInputDto;

import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/7.
 */
public interface InvestorAuditService {

    CommonDto<String> addOrUpdateInvestorAuditDemand(InvestorAuditDemandInputDto body);

    CommonDto<InvestorAuditDemandOutputDto> getInvestorAuditDemand(Integer investorId);

    CommonDto<String> addOrUpdateInvestorAuditBasicInfo(InvestorAuditBasicInfoInputDto body);

    CommonDto<InvestorAuditBasicInfoOutputDto> getInvestorAuditBasicInfo(Integer investorId);

    CommonDto<InvestorAuditIntroductionOutputDto> getInvestorAuditIntroduction(Integer investorId);

    CommonDto<String> addOrUpdateInvestorAuditIntroduction(InvestorAuditIntroductionInputDto body);

    CommonDto<String> addOrUpdateInvestorAuditInfo(InvestorAuditInfoInputDto body);

    CommonDto<InvestorAuditInfoOutputDto> getInvestorAuditInfo(Integer investorId);

    CommonDto<Map<String, Object>> getMatchInvestor(String investorName);

//    CommonDto<Map<String, Object>> listAuditInvestorsInfos(Integer appid, InvestorAuditListInputDto body);
}
