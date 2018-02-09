package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectManageDto;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.*;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

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

    @Override
    public CommonDto<ProjectManageDto> getProjectMange(Integer companyId) {
        CommonDto<ProjectManageDto> result = new CommonDto<>();
        ProjectManageDto projectManageDto = new ProjectManageDto();

        if(companyId == null){
            result.setStatus(300);
            result.setMessage("项目ID");
            result.setData(null);
            return result;
        }
        InvestmentInstitutionFundManage investmentInstitutionFundManage = investmentInstitutionFundManageService.selectByPrimaryKey(companyId);
        if(null == investmentInstitutionFundManage){
            result.setStatus(200);
            result.setMessage("success");
            result.setData(null);
            return result;
        }
        projectManageDto.setBpEmail(investmentInstitutionFundManage.getBpEmail());
        projectManageDto.setCompanyId(investmentInstitutionFundManage.getCompanyId());
        projectManageDto.setDollarAmount(investmentInstitutionFundManage.getDollarAmount());
        projectManageDto.setRmbAmount(investmentInstitutionFundManage.getRmbAmount());
        projectManageDto.setTotalAmount(investmentInstitutionFundManage.getTotalAmount());
        projectManageDto.setInteriorOrganization(investmentInstitutionFundManage.getInteriorOrganization());
        projectManageDto.setInvestmentDecisionProcess(investmentInstitutionFundManage.getInvestmentDecisionProcess());

        InvestmentInstitutionsType investmentInstitutionsType = new InvestmentInstitutionsType();
        investmentInstitutionsType.setInvestmentInstitutionsId(companyId);
        List<InvestmentInstitutionsType> investmentInstitutionsTypeList = investmentInstitutionsTypeService.select(investmentInstitutionsType);
        String[] investmentInstitutionsTypeArr = null;
        if (null != investmentInstitutionsTypeList && investmentInstitutionsTypeList.size()!=0) {
            investmentInstitutionsTypeArr = new String[investmentInstitutionsTypeList.size()];
            List<String> investmentInstitutionsTypes = new ArrayList<>();
            investmentInstitutionsTypeList.forEach( investmentInstitutionInvestType_i ->{
                investmentInstitutionsTypes.add(investmentInstitutionInvestType_i.getType());
            });
            investmentInstitutionsTypes.toArray(investmentInstitutionsTypeArr);
        }
        projectManageDto.setInvestTypes(investmentInstitutionsTypeArr);

        InvestmentInstitutionClassicCase investmentInstitutionClassicCase = new InvestmentInstitutionClassicCase();
        investmentInstitutionClassicCase.setCompanyId(companyId);
        List<InvestmentInstitutionClassicCase> investmentInstitutionClassicCaseList = investmentInstitutionClassicCaseService.select(investmentInstitutionClassicCase);
        String[] investmentInstitutionClassicCaseArr = null;
        if (null != investmentInstitutionClassicCaseList && investmentInstitutionClassicCaseList.size() != 0) {
            investmentInstitutionClassicCaseArr = new String[investmentInstitutionClassicCaseList.size()];
            List<String> investmentInstitutionClassicCases = new ArrayList<>();
            investmentInstitutionClassicCaseList.forEach(investmentInstitutionClassicCase_i ->{
                investmentInstitutionClassicCases.add(investmentInstitutionClassicCase_i.getClassicCase());
            });
            investmentInstitutionClassicCases.toArray(investmentInstitutionClassicCaseArr);
        }
        projectManageDto.setClassicCases(investmentInstitutionClassicCaseArr);

        result.setStatus(200);
        result.setMessage("success");
        result.setData(projectManageDto);
        return result;
    }

    @Override
    public CommonDto<String> addOrUpdateManage(ProjectManageDto body) {
        CommonDto<String> result = new CommonDto<>();
        if(null == body){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData("请输入相关信息");
            return result;
        }

        InvestmentInstitutionFundManage investmentInstitutionFundManage = new InvestmentInstitutionFundManage();
        investmentInstitutionFundManage.setCompanyId(body.getCompanyId());
        investmentInstitutionFundManage.setBpEmail(body.getBpEmail());
        investmentInstitutionFundManage.setTotalAmount(body.getTotalAmount());
        investmentInstitutionFundManage.setCreator(body.getToken());
        investmentInstitutionFundManage.setDollarAmount(body.getDollarAmount());
        investmentInstitutionFundManage.setRmbAmount(body.getRmbAmount());
        investmentInstitutionFundManage.setInteriorOrganization(body.getInteriorOrganization());
        investmentInstitutionFundManage.setInvestmentDecisionProcess(body.getInvestmentDecisionProcess());

        Integer investmentInstitutionFundManageInsertResult = -1;
        long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = null;
        try {
            createTime = sdf.format(new Date(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        InvestmentInstitutionFundManage investmentInstitutionFundManage1 = investmentInstitutionFundManageService.selectByPrimaryKey(body.getCompanyId());
        if(null == investmentInstitutionFundManage1){
            investmentInstitutionFundManage.setCreateTime(DateUtils.parse(createTime));
            investmentInstitutionFundManageInsertResult = investmentInstitutionFundManageService.save(investmentInstitutionFundManage);
        }else{
            investmentInstitutionFundManage.setUpdateTime(DateUtils.parse(createTime));
            investmentInstitutionFundManageInsertResult = investmentInstitutionFundManageService.updateByPrimaryKey(investmentInstitutionFundManage);
        }

        List<InvestmentInstitutionsType> investmentInstitutionsTypeList = new ArrayList<>();
        investmentInstitutionsTypeService.deleteAll(body.getCompanyId());
        if(null == body.getInvestTypes() || body.getInvestTypes().length == 0){
            InvestmentInstitutionsType investmentInstitutionsType = new InvestmentInstitutionsType();
            investmentInstitutionsType.setInvestmentInstitutionsId(body.getCompanyId());
            investmentInstitutionsType.setType(null);
            investmentInstitutionsTypeList.add(investmentInstitutionsType);
        }else{
            for(String investType : body.getInvestTypes()){
                InvestmentInstitutionsType investmentInstitutionsType1  = new InvestmentInstitutionsType();
                investmentInstitutionsType1.setInvestmentInstitutionsId(body.getCompanyId());
                investmentInstitutionsType1.setType(investType);
                investmentInstitutionsTypeList.add(investmentInstitutionsType1);
            }
        }

        Integer investmentInstitutionInvestTypeInsertResult = investmentInstitutionsTypeService.insertList(investmentInstitutionsTypeList);


        List<InvestmentInstitutionClassicCase> investmentInstitutionClassicCaseList = new ArrayList<>();
        investmentInstitutionClassicCaseService.deleteAll(body.getCompanyId());
        if(null == body.getClassicCases() || body.getClassicCases().length == 0){
            InvestmentInstitutionClassicCase investmentInstitutionClassicCase = new InvestmentInstitutionClassicCase();
            investmentInstitutionClassicCase.setCompanyId(body.getCompanyId());
            investmentInstitutionClassicCase.setClassicCase("");
            investmentInstitutionClassicCaseList.add(investmentInstitutionClassicCase);
        }else{
            for(String classicCase : body.getClassicCases()){
                InvestmentInstitutionClassicCase investmentInstitutionClassicCase1  = new InvestmentInstitutionClassicCase();
                investmentInstitutionClassicCase1.setClassicCase(classicCase);
                investmentInstitutionClassicCase1.setCompanyId(body.getCompanyId());
                investmentInstitutionClassicCaseList.add(investmentInstitutionClassicCase1);
            }
        }

        Integer investmentInstitutionClassicCaseInsertResult = investmentInstitutionClassicCaseService.insertList(investmentInstitutionClassicCaseList);

        if(investmentInstitutionFundManageInsertResult > 0 && investmentInstitutionInvestTypeInsertResult > 0 &&
                investmentInstitutionClassicCaseInsertResult > 0){
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
