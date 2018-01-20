package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionTeamDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionTeamMemberDto;
import com.lhjl.tzzs.proxy.dto.TeamManageDto.TeamMemberDetailOutpuyDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InvestmentInstitutionTeamServiceImpl implements InvestmentInstitutionTeamService {

    @Autowired
    private MetaInvestmentInstitutionTeamTypeMapper metaInvestmentInstitutionTeamTypeMapper;

    @Autowired
    private InvestmentInstitutionTeamMapper investmentInstitutionTeamMapper;

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Autowired
    private InvestmentInstitutionsAddressMapper investmentInstitutionsAddressMapper;

    @Autowired
    private InvestorDemandMapper investorDemandMapper;

    @Autowired
    private InvestmentInstitutionsMemberEducationMapper investmentInstitutionsMemberEducationMapper;

    @Autowired
    private InvestmentInstitutionsMemberWorkMapper investmentInstitutionsMemberWorkMapper;
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

    @Override
    public CommonDto<TeamMemberDetailOutpuyDto> getInfoByMemberId(Integer memberId) {
        CommonDto<TeamMemberDetailOutpuyDto> result = new CommonDto<>();
        TeamMemberDetailOutpuyDto teamMemberDetailOutpuyDto = new TeamMemberDetailOutpuyDto();

        InvestmentInstitutionTeam investmentInstitutionTeam = new InvestmentInstitutionTeam();
        investmentInstitutionTeam.setId(memberId);
        InvestmentInstitutionTeam investmentInstitutionTeam1 =  investmentInstitutionTeamMapper.selectByPrimaryKey(investmentInstitutionTeam);

        InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
        investmentInstitutions.setId(investmentInstitutionTeam1.getInvestmentInstitutionId());
        InvestmentInstitutions investmentInstitutions1 = investmentInstitutionsMapper.selectByPrimaryKey(investmentInstitutions);

        InvestmentInstitutionsAddress investmentInstitutionsAddress = new InvestmentInstitutionsAddress();
        investmentInstitutionsAddress.setId(investmentInstitutionTeam1.getInvestmentInstitutionId());
        InvestmentInstitutionsAddress investmentInstitutionsAddress1 = investmentInstitutionsAddressMapper.selectByPrimaryKey(investmentInstitutionsAddress);

        InvestmentInstitutionsMemberWork investmentInstitutionsMemberWork = new InvestmentInstitutionsMemberWork();
        investmentInstitutionsMemberWork.setMemberId(investmentInstitutionTeam1.getId());
        List<InvestmentInstitutionsMemberWork> investmentInstitutionsMemberWorkList = investmentInstitutionsMemberWorkMapper.select(investmentInstitutionsMemberWork);
        List<String> investmentInstitutionsMemberWorks = new ArrayList<>();
        investmentInstitutionsMemberWorkList.forEach( work -> {
            investmentInstitutionsMemberWorks.add(work.getWorkExperience());
        });

        String[] investmentInstitutionsMemberWorkArr = new String[investmentInstitutionsMemberWorks.size()];
        investmentInstitutionsMemberWorks.toArray(investmentInstitutionsMemberWorkArr);

        InvestmentInstitutionsMemberEducation investmentInstitutionsMemberEducation = new InvestmentInstitutionsMemberEducation();
        investmentInstitutionsMemberEducation.setMemberId(investmentInstitutionTeam1.getId());
        List<InvestmentInstitutionsMemberEducation> investmentInstitutionsMemberEducationList = investmentInstitutionsMemberEducationMapper.select(investmentInstitutionsMemberEducation);

        List<String> investmentInstitutionsMemberEducations = new ArrayList<>();
        investmentInstitutionsMemberEducationList.forEach( (InvestmentInstitutionsMemberEducation education) -> {
                    investmentInstitutionsMemberEducations.add(education.getEducationExperience());
        });
        String[] investmentInstitutionsMemberEducationArr = new String[investmentInstitutionsMemberEducations.size()];
        investmentInstitutionsMemberEducations.toArray(investmentInstitutionsMemberEducationArr);

        teamMemberDetailOutpuyDto.setName(investmentInstitutionTeam1.getActualName());
        teamMemberDetailOutpuyDto.setJobTitle(investmentInstitutionTeam1.getCompanyDuties());
        teamMemberDetailOutpuyDto.setEmail(investmentInstitutionTeam1.getEmail());
        teamMemberDetailOutpuyDto.setPicture(investmentInstitutionTeam1.getPicture());
        teamMemberDetailOutpuyDto.setIntroduction(investmentInstitutionTeam1.getMemberDesc());
        teamMemberDetailOutpuyDto.setInstitutionName(investmentInstitutions1.getShortName());
        teamMemberDetailOutpuyDto.setInstitutionAddress(investmentInstitutionsAddress1.getDetailAddress());
        teamMemberDetailOutpuyDto.setInvestmentCase(investmentInstitutionTeam1.getInvestmentCase());
        teamMemberDetailOutpuyDto.setInvestmentStage(investmentInstitutionTeam1.getInvestmentStage());
        teamMemberDetailOutpuyDto.setInvestmentPerferDomain(investmentInstitutionTeam1.getInvestmentSegmentation());
        teamMemberDetailOutpuyDto.setWorkExperience(investmentInstitutionsMemberWorkArr);
        teamMemberDetailOutpuyDto.setEducationExperience(investmentInstitutionsMemberEducationArr);

        result.setMessage("success");
        result.setStatus(200);
        result.setData(teamMemberDetailOutpuyDto);
        return result;
    }
}
