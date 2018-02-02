package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorCertificationDto;
import com.lhjl.tzzs.proxy.mapper.InvestorsMapper;
import com.lhjl.tzzs.proxy.model.InvestorDemandCharacter;
import com.lhjl.tzzs.proxy.model.InvestorInvestmentCase;
import com.lhjl.tzzs.proxy.model.Investors;
import com.lhjl.tzzs.proxy.service.InvestorCertificationInfoService;
import com.lhjl.tzzs.proxy.service.InvestorInvestmentCaseService;
import com.lhjl.tzzs.proxy.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/31.
 */
@Service
public class InvestorCertificationInfoServiceImpl implements InvestorCertificationInfoService {

    @Autowired
    private InvestorsMapper investorsMapper;

    @Autowired
    private InvestorInvestmentCaseService investorInvestmentCaseService;

    @Override
    public CommonDto<String> addOrUpdateInvestorCertification(InvestorCertificationDto body) {
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
        investors.setInvestorsType(body.getInvestorType());
        investors.setCertificationInstructions(body.getCertificationDesc());
        investors.setBusinessCard(body.getBusinessCard());
        investors.setBusinessCardOpposite(body.getBusinessCardOpposite());
        Integer investorCertificationInsertResult = -1;
        if(null == body.getInvestorId()){
            investorCertificationInsertResult = investorsMapper.insert(investors);
        }else{
            investorCertificationInsertResult = investorsMapper.updateByPrimaryKeySelective(investors);
        }

        Integer investorCaseInsertResult = -1;
        List<InvestorInvestmentCase> investorInvestmentCaseList = new ArrayList<>();
        investorInvestmentCaseService.deleteAll(body.getInvestorId());
        if(null == body.getInvestCase()){
            InvestorInvestmentCase investorInvestmentCase = new InvestorInvestmentCase();
            investorInvestmentCase.setInvestorId(body.getInvestorId());
            investorInvestmentCase.setInvestmentCase(null);
            investorInvestmentCaseList.add(investorInvestmentCase);
        }else{
            for (String investorInvestmentCase : body.getInvestCase()){
                InvestorInvestmentCase investorInvestmentCase1 = new InvestorInvestmentCase();
                investorInvestmentCase1.setInvestorId(body.getInvestorId());
                investorInvestmentCase1.setInvestmentCase(investorInvestmentCase);
                investorInvestmentCaseList.add(investorInvestmentCase1);
            }
        }
        investorCaseInsertResult = investorInvestmentCaseService.insertList(investorInvestmentCaseList);


        if(investorCertificationInsertResult > 0 && investorCaseInsertResult>0){
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
