package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectManageDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionFundManageMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.*;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import io.swagger.models.auth.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author caochuangui
 * @date 2018-1-24 16:49:31
 */
@Service
public class ProjectAdminMangeServiceImpl implements ProjectAdminManageService {

    @Resource
    private InvestmentInstitutionFundManageService investmentInstitutionFundManageService;

    @Resource
    private InvestmentInstitutionsTypeService investmentInstitutionsTypeService;

    @Resource
    private InvestmentInstitutionClassicCaseService investmentInstitutionClassicCaseService;

    @Resource
    private MetaInvestTypeService metaInvestTypeService;

    @Resource
    private ProjectsMapper projectsMapper;
    
    @Autowired
    private InvestmentInstitutionFundManageMapper investmentInstitutionFundManageMapper;

    @Override
    public CommonDto<ProjectManageDto> getProjectMange(Integer subjectId,Integer subjectType) {
        CommonDto<ProjectManageDto> result = new CommonDto<>();
        ProjectManageDto projectManageDto = new ProjectManageDto();

        if(subjectId == null){
            result.setStatus(500);
            result.setMessage("请输入主体ID");
            result.setData(null);
            return result;
        }
        if(subjectType == null){
            result.setStatus(500);
            result.setMessage("请输入主体类型");
            result.setData(null);
            return result;
        }
        
        if(Integer.valueOf(1).equals(subjectType) ) {//项目
        	//TODO 对外投资属性为否的项目的基金管理信息待完善
        	
        }else if(Integer.valueOf(2).equals(subjectType)) {//机构
        	InvestmentInstitutionFundManage investmentInstitutionFundManage = investmentInstitutionFundManageService.selectByPrimaryKey(subjectId);
            
            if(investmentInstitutionFundManage !=null) {
    	        projectManageDto.setBpEmail(investmentInstitutionFundManage.getBpEmail());
    	        projectManageDto.setCompanyId(investmentInstitutionFundManage.getCompanyId());
    	        projectManageDto.setDollarAmount(investmentInstitutionFundManage.getDollarAmount());
    	        projectManageDto.setRmbAmount(investmentInstitutionFundManage.getRmbAmount());
    	        projectManageDto.setTotalAmount(investmentInstitutionFundManage.getTotalAmount());
    	        projectManageDto.setInteriorOrganization(investmentInstitutionFundManage.getInteriorOrganization());
    	        projectManageDto.setInvestmentDecisionProcess(investmentInstitutionFundManage.getInvestmentDecisionProcess());
            }
            
            /**
             * 设置投资类型
             */
            InvestmentInstitutionsType investmentInstitutionsType = new InvestmentInstitutionsType();
            investmentInstitutionsType.setInvestmentInstitutionsId(subjectId);
            List<InvestmentInstitutionsType> investmentInstitutionsTypeList = investmentInstitutionsTypeService.select(investmentInstitutionsType);
            
            if (null != investmentInstitutionsTypeList && investmentInstitutionsTypeList.size()!=0) {
                List<String> typeList = new ArrayList<>();
                investmentInstitutionsTypeList.forEach( e ->{
                	typeList.add(e.getType());
                });
                projectManageDto.setInvestTypes(typeList);
            }
            /**
             * 经典案例
             */
            InvestmentInstitutionClassicCase investmentInstitutionClassicCase = new InvestmentInstitutionClassicCase();
            investmentInstitutionClassicCase.setCompanyId(subjectId);
            List<InvestmentInstitutionClassicCase> investmentInstitutionClassicCaseList = investmentInstitutionClassicCaseService.select(investmentInstitutionClassicCase);
            
            if (null != investmentInstitutionClassicCaseList && investmentInstitutionClassicCaseList.size() != 0) {
                List<String> classicCasesList = new ArrayList<>();
                investmentInstitutionClassicCaseList.forEach(e ->{
                    classicCasesList.add(e.getClassicCase());
                });
                projectManageDto.setClassicCases(classicCasesList);
            }
        }
        
        
        result.setStatus(200);
        result.setMessage("success");
        result.setData(projectManageDto);
        return result;
    }
    
    @Transactional
    @Override
    public CommonDto<String> addOrUpdateManage(ProjectManageDto body) {
        CommonDto<String> result = new CommonDto<>();
        
        if(null == body){
            result.setStatus(500);
            result.setMessage("failed");
            result.setData("请输入相关信息");
            return result;
        }
        if(body.getSubjectType() == null){
            result.setStatus(500);
            result.setMessage("failed");
            result.setData("请输入主体类型");
            return result;
        }
        if(Integer.valueOf(1).equals(body.getSubjectType())) {//项目
        	//TODO 项目基金管理信息的更新
        	
        }else if(Integer.valueOf(2).equals(body.getSubjectType())) {//机构
        	InvestmentInstitutionFundManage investmentInstitutionFundManage = new InvestmentInstitutionFundManage();
            investmentInstitutionFundManage.setCompanyId(body.getCompanyId());
            System.err.println(body.getBpEmail()+"**");
            investmentInstitutionFundManage.setBpEmail(body.getBpEmail());
            investmentInstitutionFundManage.setTotalAmount(body.getTotalAmount());
            investmentInstitutionFundManage.setCreator(body.getToken());
            investmentInstitutionFundManage.setDollarAmount(body.getDollarAmount());
            investmentInstitutionFundManage.setRmbAmount(body.getRmbAmount());
            investmentInstitutionFundManage.setInteriorOrganization(body.getInteriorOrganization());
            investmentInstitutionFundManage.setInvestmentDecisionProcess(body.getInvestmentDecisionProcess());

            //接收自增长id
            Integer autoIncreasedId = body.getCompanyId();
            if(null == body.getCompanyId() ){
                investmentInstitutionFundManage.setCreateTime(new Date());
                investmentInstitutionFundManageMapper.insertSelective(investmentInstitutionFundManage);
                autoIncreasedId=investmentInstitutionFundManage.getCompanyId();
            }else{
                investmentInstitutionFundManage.setUpdateTime(new Date());
                investmentInstitutionFundManageMapper.updateByPrimaryKeySelective(investmentInstitutionFundManage);
            }
            
            //机构投资类型
            List<InvestmentInstitutionsType> investmentInstitutionsTypeList = new ArrayList<>();
            investmentInstitutionsTypeService.deleteAll(autoIncreasedId);
            if(null != body.getInvestTypes() && body.getInvestTypes().size() != 0){
                for(String investType : body.getInvestTypes()){
                    InvestmentInstitutionsType e  = new InvestmentInstitutionsType();
                    e.setInvestmentInstitutionsId(autoIncreasedId);
                    e.setType(investType);
                    investmentInstitutionsTypeList.add(e);
                }
            }
            investmentInstitutionsTypeService.insertList(investmentInstitutionsTypeList);
            
            //经典案例更新
            List<InvestmentInstitutionClassicCase> investmentInstitutionClassicCaseList = new ArrayList<>();
            investmentInstitutionClassicCaseService.deleteAll(autoIncreasedId);
            if(null != body.getClassicCases() && body.getClassicCases().size() != 0){
                for(String classicCase : body.getClassicCases()){
                    InvestmentInstitutionClassicCase e  = new InvestmentInstitutionClassicCase();
                    e.setClassicCase(classicCase);
                    e.setCompanyId(autoIncreasedId);
                    investmentInstitutionClassicCaseList.add(e);
                }
            }
            investmentInstitutionClassicCaseService.insertList(investmentInstitutionClassicCaseList);
        }
        

        result.setStatus(200);
        result.setMessage("success");
        result.setData("更新成功");
        return result;
    }


    @Override
    public CommonDto<List<MetaInvestType>> getInvestType() {
        CommonDto<List<MetaInvestType>> result = new CommonDto<>();
        List<MetaInvestType> metaInvestTypeList = new ArrayList<>();
        metaInvestTypeList = metaInvestTypeService.selectAll();
        result.setStatus(200);
        result.setMessage("success");
        result.setData(metaInvestTypeList);
        return result;
    }


    @Override
    public CommonDto<List<String>> getClassicCase(String inputWord) {
        CommonDto<List<String>> result = new CommonDto<>();
        List<String> matchList = new ArrayList<>();
        if(inputWord == null || inputWord == ""){
            result.setData(null);
            result.setStatus(200);
            result.setMessage("success");
            return result;
        }
        List<Projects> projectsList = projectsMapper.selectByCaseName(inputWord);
        projectsList.forEach( projects -> {
            matchList.add(projects.getShortName());
        });
        result.setStatus(200);
        result.setMessage("success");
        result.setData(matchList);
        return result;
    }
}
