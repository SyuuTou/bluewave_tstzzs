package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionTeamDto;
import com.lhjl.tzzs.proxy.dto.TeamManageDto.TeamMemberDetailOutpuyDto;

import java.util.List;

public interface InvestmentInstitutionTeamService {
    /**
     * 获取机构团队成员的接口
     * @param institutionId 机构id
     * @return
     */
    CommonDto<List<InvestmentInstitutionTeamDto>> getInvestmentInstitutionById(Integer institutionId);

    /**
     * 获取机构团队成员的详情信息
     * @param memberId
     * @return
     */
    CommonDto<TeamMemberDetailOutpuyDto> getInfoByMemberId(Integer memberId);

}
