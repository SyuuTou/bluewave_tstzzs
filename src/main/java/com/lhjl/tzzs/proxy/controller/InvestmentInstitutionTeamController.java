package com.lhjl.tzzs.proxy.controller;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionTeamDto;
import com.lhjl.tzzs.proxy.dto.TeamManageDto.TeamMemberDetailOutpuyDto;
import com.lhjl.tzzs.proxy.model.MetaInvestmentInstitutionTeamType;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionTeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class InvestmentInstitutionTeamController extends GenericService{

    @Resource
    private InvestmentInstitutionTeamService investmentInstitutionTeamService;

    @GetMapping("institution/team/list")
    public CommonDto<List<InvestmentInstitutionTeamDto>> getInstitutionTeamList(Integer institutionId){
        CommonDto<List<InvestmentInstitutionTeamDto>> result = new CommonDto<>();
        try {
            result = investmentInstitutionTeamService.getInvestmentInstitutionById(institutionId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setMessage("服务器端发生错误");
        }

        return result;
    }

    /**
     * 获取指定成员的详细信息
     * @param memberId
     * @return
     */
    @GetMapping("getmemberinfo")
    public CommonDto<TeamMemberDetailOutpuyDto> getTeamMemberInfo(Integer memberId) {
        CommonDto<TeamMemberDetailOutpuyDto> result = new CommonDto<>();
        try {
            result=investmentInstitutionTeamService.getInfoByMemberId(memberId);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }
        return result;
    }


    @GetMapping("/teamytpe")
    public CommonDto<List<MetaInvestmentInstitutionTeamType>> getTeamType() {
        CommonDto<List<MetaInvestmentInstitutionTeamType>> result = new CommonDto<>();
        try {
            result=investmentInstitutionTeamService.getTeamType();
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }
        return result;
    }
}
