package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionTeamDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionTeamMemberDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionTeamMapper;
import com.lhjl.tzzs.proxy.mapper.MetaInvestmentInstitutionTeamTypeMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionTeam;
import com.lhjl.tzzs.proxy.model.MetaInvestmentInstitutionTeamType;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvestmentInstitutionTeamServiceImpl implements InvestmentInstitutionTeamService {

    @Autowired
    private MetaInvestmentInstitutionTeamTypeMapper metaInvestmentInstitutionTeamTypeMapper;

    @Autowired
    private InvestmentInstitutionTeamMapper investmentInstitutionTeamMapper;


    /**
     * 获取机构团队成员的接口
     * @param institutionId 机构id
     * @return
     */
    @Override
    public CommonDto<List<InvestmentInstitutionTeamDto>> getInvestmentInstitutionById(Integer institutionId) {
        CommonDto<List<InvestmentInstitutionTeamDto>> result  = new CommonDto<>();
        List<InvestmentInstitutionTeamDto> list = new ArrayList<>();

        if (institutionId == null){
            result.setStatus(502);
            result.setMessage("机构id不能为空");
            result.setData(null);

            return result;
        }

        List<MetaInvestmentInstitutionTeamType> metaInvestmentInstitutionTeamTypeList = metaInvestmentInstitutionTeamTypeMapper.selectTeamTypeByInstitutionId(institutionId);
        if (metaInvestmentInstitutionTeamTypeList.size() > 0){
            for (MetaInvestmentInstitutionTeamType miitt:metaInvestmentInstitutionTeamTypeList){
                InvestmentInstitutionTeamDto investmentInstitutionTeamDto = new InvestmentInstitutionTeamDto();
                investmentInstitutionTeamDto.setTemaType(miitt.getTypeName());
                List<InvestmentInstitutionTeamMemberDto> list1 = new ArrayList<>();

                Integer metaType = miitt.getId();
                List<InvestmentInstitutionTeam> investmentInstitutionTeamList = investmentInstitutionTeamMapper.getTeamMemberListByType(institutionId,metaType);
                if (investmentInstitutionTeamList.size() > 0){
                    for (InvestmentInstitutionTeam iit:investmentInstitutionTeamList){
                        InvestmentInstitutionTeamMemberDto investmentInstitutionTeamMemberDto = new InvestmentInstitutionTeamMemberDto();
                        if (iit.getActualName() == null){
                            iit.setActualName("");
                        }
                        investmentInstitutionTeamMemberDto.setActualName(iit.getActualName());

                        investmentInstitutionTeamMemberDto.setId(iit.getId());
                        if (iit.getCompanyDuties() == null){
                            iit.setCompanyDuties("");
                        }
                        investmentInstitutionTeamMemberDto.setCompanyDuties(iit.getCompanyDuties());
                        if (iit.getPicture() == null){
                            iit.setPicture("");
                        }
                        investmentInstitutionTeamMemberDto.setPicture(iit.getPicture());

                        list1.add(investmentInstitutionTeamMemberDto);
                    }
                }
                investmentInstitutionTeamDto.setTeamMember(list1);

                list.add(investmentInstitutionTeamDto);

            }

        }

        result.setMessage("success");
        result.setData(list);
        result.setStatus(200);

        return result;
    }
}
