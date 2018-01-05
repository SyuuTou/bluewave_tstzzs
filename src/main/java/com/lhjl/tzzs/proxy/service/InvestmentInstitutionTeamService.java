package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionTeamDto;

import java.util.List;

public interface InvestmentInstitutionTeamService {
    /**
     * 获取机构团队成员的接口
     * @param institutionId 机构id
     * @return
     */
    CommonDto<List<InvestmentInstitutionTeamDto>> getInvestmentInstitutionById(Integer institutionId);
}
