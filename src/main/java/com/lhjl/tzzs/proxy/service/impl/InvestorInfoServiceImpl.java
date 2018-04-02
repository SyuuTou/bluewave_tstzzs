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

import java.util.Date;

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
    	this.LOGGER.info("++++>>>+"+body);
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
        try {
        	if(null != body.getCompanyName() &&  !("".equals(body.getCompanyName()))){
            	//存在该投资机构
                investmentInstitutionsId = investmentInstitutionsMapper.selectByCompanyName(body.getCompanyName());
                //不存在该投资机构，则执行插入之后，取得自增长id
                if(null == investmentInstitutionsId){
                    InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
                    investmentInstitutions.setShortName(body.getCompanyName());
                    //设置新增机构的有效位
                    investmentInstitutions.setYn(1);
                    //设置新增机构的来源类型为运营人员后台添加，标志位为3
                    investmentInstitutions.setDataSourceType(3);
                    //设置创建时间(该创建时间指的是该条记录的创建时间)
                    investmentInstitutions.setCreateTime(new Date());
                    investmentInstitutionsMapper.insert(investmentInstitutions);
                } 
                investmentInstitutionsId = investmentInstitutionsMapper.selectByCompanyName(body.getCompanyName());
            }
        }catch(Exception e ) {
        	result.setData(null);
        	result.setMessage("DB中该项目简称不唯一");
        	result.setStatus(500);
        	return result;
        }
        //设置投资机构id
        investors.setInvestmentInstitutionsId(investmentInstitutionsId);
        
        investors.setPosition(body.getCompanyDuties());
        investors.setHeadPicture(body.getHeadPicture());  

        Integer teamId = metaInvestmentInstitutionTeamTypeMapper.findTeamIdByName(body.getTeamName());
        investors.setTeamId(teamId);
        investors.setSelfDefTeam(body.getSelfDefTeam());
        investors.setPhone(body.getPhone());  
        investors.setKernelDescription(body.getKernelDesc());
        
        //增加或者更新之后的投资人主键id
        Integer updateAfterId=0;   
        if(null == body.getInvestorId()){
        	this.LOGGER.info("****insert opration****");
        	
        	//设置为有效数据 1
            investors.setYn(1);
            //设置数据来源类型
            investors.setInvestorSourceType(3);
            //设置创建时间,提交时间
            investors.setCreateTime(new Date());
            investorsMapper.insert(investors);
            updateAfterId=investors.getId();
            
            result.setStatus(200);
            result.setMessage("数据新增成功");
            result.setData(updateAfterId);
            return result;
        }else{
        	this.LOGGER.info("****update opration****");
        	
        	//设置更新时间
        	investors.setUpdateTime(new Date());
        	investorsMapper.updateByPrimaryKeySelective(investors);
        	updateAfterId=investors.getId();
        	
        	 result.setStatus(200);
             result.setMessage("数据更新成功");
             result.setData(updateAfterId);
             return result;
        }

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
