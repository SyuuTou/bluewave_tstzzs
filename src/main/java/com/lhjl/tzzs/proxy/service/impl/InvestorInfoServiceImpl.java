package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorKernelInfoDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorsMapper;
import com.lhjl.tzzs.proxy.mapper.MetaInvestmentInstitutionTeamTypeMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.model.MetaInvestmentInstitutionTeamType;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.InvestorInfoService;
import com.lhjl.tzzs.proxy.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
@Service
public class InvestorInfoServiceImpl extends GenericService implements InvestorInfoService {

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Autowired
    private InvestorsMapper investorsMapper;

    @Autowired
    private MetaInvestmentInstitutionTeamTypeMapper metaInvestmentInstitutionTeamTypeMapper;

    @Transactional
    @Override
    public CommonDto<Integer> addOrUpdateInvestorInfo(InvestorKernelInfoDto body) {
        CommonDto<Integer> result = new CommonDto<>();
        Boolean flag = false;
        try {
            flag = CommonUtils.isAllFieldNull(body);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(null == body || flag == true){
            result.setStatus(500);
            result.setMessage("failed");
            result.setData(null);
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
//        Integer investorInsertResult = -1;
        Integer updateAfterId=0;
        if(null == body.getInvestorId()){
        	this.LOGGER.info("****insert opration****");
            investorsMapper.insert(investors);
            updateAfterId=investors.getId();
            
            result.setStatus(200);
            result.setMessage("数据新增成功");
            result.setData(updateAfterId);
            return result;
        }else{
        	this.LOGGER.info("****update opration****");
        	investorsMapper.updateByPrimaryKeySelective(investors);
        	updateAfterId=investors.getId();
        	
        	 result.setStatus(200);
             result.setMessage("数据保存成功");
             result.setData(updateAfterId);
             return result;
        }

//        if(investorInsertResult > 0){
//            result.setStatus(200);
//            result.setMessage("success");
//            result.setData("保存成功");
//            return result;
//        }

       
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
