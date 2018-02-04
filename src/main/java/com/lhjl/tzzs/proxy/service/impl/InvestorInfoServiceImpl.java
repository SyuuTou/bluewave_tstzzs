package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorKernelInfoDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorsMapper;
import com.lhjl.tzzs.proxy.mapper.MetaInvestmentInstitutionTeamTypeMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.model.MetaInvestmentInstitutionTeamType;
import com.lhjl.tzzs.proxy.service.InvestorInfoService;
import com.lhjl.tzzs.proxy.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
@Service
public class InvestorInfoServiceImpl implements InvestorInfoService {

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Autowired
    private InvestorsMapper investorsMapper;

    @Autowired
    private MetaInvestmentInstitutionTeamTypeMapper metaInvestmentInstitutionTeamTypeMapper;

    @Override
    public CommonDto<String> addOrUpdateInvestorInfo(InvestorKernelInfoDto body) {
        CommonDto<String> result = new CommonDto<>();
        Boolean flag = false;
        try {
            flag = CommonUtils.isAllFieldNull(body);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(null == body || flag == true){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData("请输入新信息");
            return result;
        }

        Investors investors = new Investors();
        investors.setId(body.getInvestorId());
        investors.setName(body.getName());
        investors.setUserId(body.getUserId());
        Integer investmentInstitutionsId = null;
        if(null != body.getCompanyName() && body.getCompanyName() != ""){
            investmentInstitutionsId = investmentInstitutionsMapper.selectByCompanyName(body.getCompanyName());
            if(null == investmentInstitutionsId){
                InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
                investmentInstitutions.setShortName(body.getCompanyName());
                investmentInstitutionsMapper.insert(investmentInstitutions);
            }
            investmentInstitutionsId = investmentInstitutionsMapper.selectByCompanyName(body.getCompanyName());
        }
        investors.setInvestmentInstitutionsId(investmentInstitutionsId);
        investors.setPosition(body.getCompanyDuties());
        investors.setHeadPicture(body.getHeadPicture());

        Integer teamId = metaInvestmentInstitutionTeamTypeMapper.findTeamIdByName(body.getTeamName());
        investors.setTeamId(teamId);
        investors.setSelfDefTeam(body.getSelfDefTeam());
        investors.setPhone(body.getPhone());
        investors.setKernelDescription(body.getKernelDesc());
        Integer investorInsertResult = -1;
        if(null == body.getInvestorId()){
            investorInsertResult = investorsMapper.insert(investors);
        }else{
            investorInsertResult = investorsMapper.updateByPrimaryKeySelective(investors);
        }

        if(investorInsertResult > 0){
            result.setStatus(200);
            result.setMessage("success");
            result.setData("保存成功");
            return result;
        }

        result.setStatus(300);
        result.setMessage("failed");
        result.setData("保存失败");
        return result;
    }

    @Override
    public CommonDto<InvestorKernelInfoDto> getInvestorInfo(Integer investorId) {
        CommonDto<InvestorKernelInfoDto> result = new CommonDto<>();
        InvestorKernelInfoDto investorKernelInfoDto = new InvestorKernelInfoDto();
        Investors investors = investorsMapper.selectByPrimaryKey(investorId);
        if(null == investors){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }

        investorKernelInfoDto.setName(investors.getName());
        String companyName = investmentInstitutionsMapper.selectById(investors.getInvestmentInstitutionsId());
        investorKernelInfoDto.setCompanyName(companyName);
        investorKernelInfoDto.setCompanyDuties(investors.getPosition());
        investorKernelInfoDto.setHeadPicture(investors.getHeadPicture());
        String teamName = metaInvestmentInstitutionTeamTypeMapper.findTeamNameById(investors.getTeamId());
        investorKernelInfoDto.setTeamName(teamName);
        investorKernelInfoDto.setSelfDefTeam(investors.getSelfDefTeam());
        investorKernelInfoDto.setPhone(investors.getPhone());
        investorKernelInfoDto.setKernelDesc(investors.getKernelDescription());
        investorKernelInfoDto.setUserId(investors.getUserId());

        result.setStatus(200);
        result.setMessage("success");
        result.setData(investorKernelInfoDto);
        return result;
    }


}
