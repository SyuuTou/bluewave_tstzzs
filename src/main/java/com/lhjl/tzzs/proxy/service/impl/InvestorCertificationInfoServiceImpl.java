package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorCertificationDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorInvestmentCaseMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorsMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
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
    
    @Autowired
    private InvestorInvestmentCaseMapper investorInvestmentCaseMapper;

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
        //担任职务
        investors.setPosition(body.getPosition());
		if(body.getCompanyName() != null) {
			Integer investmentInstitutionsId;
			InvestmentInstitutions ii=new InvestmentInstitutions();
			ii.setShortName(body.getCompanyName());
			try {
				ii = investmentInstitutionsMapper.selectOne(ii);
				
			}catch(Exception e) {
				result.setStatus(500);
		        result.setMessage("数据库数据投资机构简称不唯一，导致selectOne出现冗余数据");
		        result.setData(false);
		        return result;
			}
			
			if(ii==null) {//不存在该名称对应的投资机构，执行插入操作
				InvestmentInstitutions insertEntity=new InvestmentInstitutions();
				insertEntity.setShortName(body.getCompanyName());
				//需要设置该投资机构的审核状态吗
//				insertEntity.setApprovalStatus(0);
				insertEntity.setYn(1);
				//设置来源类型为运营人员添加
				insertEntity.setDataSourceType(3);
				investmentInstitutionsMapper.insertSelective(insertEntity);
				
				//设置关联的机构id
				investmentInstitutionsId=insertEntity.getId();  
			}else {
				//设置关联的机构id
				investmentInstitutionsId=ii.getId();
			}
			
			//设置关联的投资机构id
			investors.setInvestmentInstitutionsId(investmentInstitutionsId);
		}
		
        if(null == body.getInvestorId()){
        	//执行增加操作，由于该板块属于投资人的级联信息，必须关联投资人id
        	this.LOGGER.info("-->insert opration ");
            investorsMapper.insert(investors);
        }else{
        	//该板块由于属于投资人的版块，一般来说肯定会执行更新操作
        	this.LOGGER.info("-->update opration ");
            investorsMapper.updateByPrimaryKeySelective(investors);
        }
        
     	//删除所有的投资案例
        investorInvestmentCaseService.deleteAll(body.getInvestorId());
        //投资案例不为null的时候
        if(null != body.getInvestCase() && body.getInvestCase().size() != 0){
            for (String e : body.getInvestCase()){
                InvestorInvestmentCase investmentCase = new InvestorInvestmentCase();
                investmentCase.setInvestorId(body.getInvestorId());
                investmentCase.setInvestmentCase(e);
                
                investorInvestmentCaseMapper.insertSelective(investmentCase);
            }
        }

        result.setStatus(200);
        result.setMessage("success");
        result.setData(true);
        return result;
    }

    @Transactional
    @Override
    public CommonDto<InvestorCertificationDto> getInvestorCertification(Integer investorId) {

        CommonDto<InvestorCertificationDto> result = new CommonDto<>();
        InvestorCertificationDto investorCertificationDto = new InvestorCertificationDto();

        Investors investors = investorsMapper.selectByPrimaryKey(investorId);
        this.LOGGER.info("--》investors--》"+investors);
        if(null == investors){
            result.setData(null);
            result.setMessage("没有该投资人");
            result.setStatus(500);
            return result;
        }

        String companyName = investmentInstitutionsMapper.selectById(investors.getInvestmentInstitutionsId());
        investorCertificationDto.setCompanyName(companyName);
        investorCertificationDto.setPosition(investors.getPosition());
        investorCertificationDto.setInvestorType(investors.getInvestorsType());
        investorCertificationDto.setInvestorId(investors.getId());
        investorCertificationDto.setBusinessCard(investors.getBusinessCard());
        investorCertificationDto.setBusinessCardOpposite(investors.getBusinessCardOpposite());
        investorCertificationDto.setCertificationDesc(investors.getCertificationInstructions());

        InvestorInvestmentCase investorInvestmentCase = new InvestorInvestmentCase();
        investorInvestmentCase.setInvestorId(investorId);
        List<InvestorInvestmentCase> list = investorInvestmentCaseService.select(investorInvestmentCase);
        if(null != list){
            List<String> cases = new ArrayList<>();
            list.forEach(e -> {
            	cases.add(e.getInvestmentCase());
            });
            investorCertificationDto.setInvestCase(cases);
        }

        result.setData(investorCertificationDto);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }

}
