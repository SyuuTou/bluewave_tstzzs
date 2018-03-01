package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorCertificationDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorsMapper;
import com.lhjl.tzzs.proxy.model.InvestorDemandCharacter;
import com.lhjl.tzzs.proxy.model.InvestorInvestmentCase;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.InvestorCertificationInfoService;
import com.lhjl.tzzs.proxy.service.InvestorInvestmentCaseService;
import com.lhjl.tzzs.proxy.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/31.
 */
@Service
public class InvestorCertificationInfoServiceImpl extends GenericService implements InvestorCertificationInfoService {

    @Autowired
    private InvestorsMapper investorsMapper;

    @Autowired
    private InvestorInvestmentCaseService investorInvestmentCaseService;

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Transactional
    @Override
    public CommonDto<Boolean> addOrUpdateInvestorCertification(InvestorCertificationDto body) {
        CommonDto<Boolean> result = new CommonDto<>();
        Boolean flag = false;
        try {
            flag = CommonUtils.isAllFieldNull(body);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(null == body || flag == true){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(false);
            return result;
        }
        Investors investors = new Investors();
        investors.setId(body.getInvestorId());
        investors.setInvestorsType(body.getInvestorType());
        investors.setCertificationInstructions(body.getCertificationDesc());
        investors.setBusinessCard(body.getBusinessCard());
        investors.setBusinessCardOpposite(body.getBusinessCardOpposite());
        Integer investorCertificationInsertResult = -1;
        if(null == body.getInvestorId()){
        	this.LOGGER.info("-->insert opration ");
            investorCertificationInsertResult = investorsMapper.insert(investors);
        }else{
        	//该板块由于属于投资人的版块，一般来说肯定会执行更新操作
        	this.LOGGER.info("-->update opration ");
            investorCertificationInsertResult = investorsMapper.updateByPrimaryKeySelective(investors);
        }

        Integer investorCaseInsertResult = -1;
        List<InvestorInvestmentCase> investorInvestmentCaseList = new ArrayList<>();
        //删除所有的投资案例
        investorInvestmentCaseService.deleteAll(body.getInvestorId());
        //投资案例不为null的时候
        if(null != body.getInvestCase() && body.getInvestCase().length != 0){
//            InvestorInvestmentCase investorInvestmentCase = new InvestorInvestmentCase();
//            investorInvestmentCase.setInvestorId(body.getInvestorId());
//            investorInvestmentCase.setInvestmentCase(null);
//            investorInvestmentCaseList.add(investorInvestmentCase);
            for (String investorInvestmentCase : body.getInvestCase()){
                InvestorInvestmentCase investorInvestmentCase1 = new InvestorInvestmentCase();
                investorInvestmentCase1.setInvestorId(body.getInvestorId());
                investorInvestmentCase1.setInvestmentCase(investorInvestmentCase);
                investorInvestmentCaseList.add(investorInvestmentCase1);
            }
            investorCaseInsertResult = investorInvestmentCaseService.insertList(investorInvestmentCaseList);
        }/*else{
            for (String investorInvestmentCase : body.getInvestCase()){
                InvestorInvestmentCase investorInvestmentCase1 = new InvestorInvestmentCase();
                investorInvestmentCase1.setInvestorId(body.getInvestorId());
                investorInvestmentCase1.setInvestmentCase(investorInvestmentCase);
                investorInvestmentCaseList.add(investorInvestmentCase1);
            }
        }*/

        /*if(investorCertificationInsertResult > 0 && investorCaseInsertResult>0){
            result.setStatus(200);
            result.setMessage("success");
            result.setData("保存成功");
            return result;
        }*/

        result.setStatus(200);
        result.setMessage("success");
        result.setData(true);
        return result;
    }

    @Override
    public CommonDto<InvestorCertificationDto> getInvestorCertification(Integer investorId) {

        CommonDto<InvestorCertificationDto> result = new CommonDto<>();
        InvestorCertificationDto investorCertificationDto = new InvestorCertificationDto();

        Investors investors = investorsMapper.selectByPrimaryKey(investorId);
        if(null == investors){
            result.setData(null);
            result.setMessage("没有该投资人");
            result.setStatus(300);
            return result;
        }

        String companyName = investmentInstitutionsMapper.selectById(investors.getInvestmentInstitutionsId());
        investorCertificationDto.setCompanyName(companyName);
        investorCertificationDto.setPosition(investors.getPosition());
        investorCertificationDto.setInvestorType(investors.getIdentityType());
        investorCertificationDto.setInvestorId(investors.getId());
        investorCertificationDto.setBusinessCard(investors.getBusinessCard());
        investorCertificationDto.setBusinessCardOpposite(investors.getBusinessCardOpposite());
        investorCertificationDto.setCertificationDesc(investors.getCertificationInstructions());

        InvestorInvestmentCase investorInvestmentCase = new InvestorInvestmentCase();
        investorInvestmentCase.setInvestorId(investorId);
        List<InvestorInvestmentCase> investorInvestmentCaseList = investorInvestmentCaseService.select(investorInvestmentCase);
        String[] investorInvestmentCaseArr = null;
        if(null == investorInvestmentCaseList){
            investorCertificationDto.setInvestCase(investorInvestmentCaseArr);
        }else{
            List<String> investorInvestmentCases = new ArrayList<>();
            investorInvestmentCaseList.forEach(investorInvestmentCase_i -> {
                investorInvestmentCases.add(investorInvestmentCase_i.getInvestmentCase());
            });
            investorInvestmentCaseArr = new String[investorInvestmentCases.size()];
            investorInvestmentCases.toArray(investorInvestmentCaseArr);
            investorCertificationDto.setInvestCase(investorInvestmentCaseArr);
        }

        result.setData(investorCertificationDto);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }

}
