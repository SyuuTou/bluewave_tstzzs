package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.FundDto.FundInputDto;
import com.lhjl.tzzs.proxy.dto.FundDto.FundOutputDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsFundsMapper;
import com.lhjl.tzzs.proxy.mapper.MetaProjectStageMapper;
import com.lhjl.tzzs.proxy.mapper.MetaSegmentationMapper;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsFundsLabelService;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsFundsSegmentationService;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsFundsStagesService;
import com.lhjl.tzzs.proxy.service.ProjectAdminFundService;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/19.
 */
@Service
public class ProjectAdminFundServiceImpl implements ProjectAdminFundService {

    @Autowired
    private InvestmentInstitutionsFundsMapper investmentInstitutionsFundsMapper;

    @Autowired
    private MetaProjectStageMapper metaProjectStageMapper;

    @Autowired
    private MetaSegmentationMapper metaSegmentationMapper;

    @Resource
    private InvestmentInstitutionsFundsLabelService investmentInstitutionsFundsLabelService;

    @Resource
    private InvestmentInstitutionsFundsSegmentationService investmentInstitutionsFundsSegmentationService;

    @Resource
    private InvestmentInstitutionsFundsStagesService investmentInstitutionsFundsStagesService;

    @Override
    public CommonDto<List<FundOutputDto>> getFundList(Integer subjectId,Integer subjectType) {
        CommonDto<List<FundOutputDto>> result = new CommonDto<>();
        List<FundOutputDto> fundOutputDtoList = new ArrayList<>();
        if(Integer.valueOf(1).equals(subjectType)){ //项目
        	
        }else if(Integer.valueOf(2).equals(subjectType)) {//机构
        	List<InvestmentInstitutionsFunds> investmentInstitutionsFundsList = investmentInstitutionsFundsMapper.getFundList(subjectId);
            if(null != investmentInstitutionsFundsList && investmentInstitutionsFundsList.size() != 0){
                for(InvestmentInstitutionsFunds e: investmentInstitutionsFundsList){
                    FundOutputDto fundOutputDto = new FundOutputDto();
                    
                    fundOutputDto.setFundId(e.getId());
                    fundOutputDto.setShortName(e.getName());
                    fundOutputDto.setFullName(e.getFullName());
                    fundOutputDto.setEstablishedTime(DateUtils.format(e.getCreateTime()));
                    fundOutputDto.setSurvivalPeriod(e.getDuration());
                    fundOutputDto.setCurrencyType(e.getCurrency());
                    fundOutputDto.setFundManageScale(e.getScale());
                    fundOutputDto.setInvestmentAmountLow(e.getInvestmentAmountBegin());
                    fundOutputDto.setInvestmentAmountHigh(e.getInvestmentAmountEnd());
                    
                    //投资阶段设置
                    InvestmentInstitutionsFundsStages investmentInstitutionsFundsStages = new InvestmentInstitutionsFundsStages();
                    investmentInstitutionsFundsStages.setInvestmentInstitutionsFundsId(e.getId());
                    List<InvestmentInstitutionsFundsStages> investmentInstitutionsFundsStagesList = investmentInstitutionsFundsStagesService.select(investmentInstitutionsFundsStages);
                    	
                    List<Integer> investmentInstitutionsFundsStagess = new ArrayList<>();
                    List<String> investmentInstitutionsFundsStageNames = new ArrayList<>();
                    String[] investmentInstitutionsFundsStagesArr = null;
                    
                    if(investmentInstitutionsFundsStagesList != null && investmentInstitutionsFundsStagesList.size() != 0){
                        investmentInstitutionsFundsStagesList.forEach(obj -> {
                            investmentInstitutionsFundsStagess.add(obj.getStageId());
                        });
                        Integer[] StageIds = new Integer[investmentInstitutionsFundsStagess.size()];
                        investmentInstitutionsFundsStagess.toArray(StageIds);
                        List<MetaProjectStage> metaProjectStageList = metaProjectStageMapper.selectByStageIds(StageIds);
                        metaProjectStageList.forEach( obj -> {
                            investmentInstitutionsFundsStageNames.add(obj.getName());
                        });
                        investmentInstitutionsFundsStagesArr = new String[investmentInstitutionsFundsStagesList.size()];
                        investmentInstitutionsFundsStageNames.toArray(investmentInstitutionsFundsStagesArr);
                        fundOutputDto.setInvestStages(investmentInstitutionsFundsStagesArr);
                    }
                    //投资领域设置
                    InvestmentInstitutionsFundsSegmentation investmentInstitutionsFundsSegmentation = new InvestmentInstitutionsFundsSegmentation();
                    investmentInstitutionsFundsSegmentation.setInvestmentInstitutionsFundsId(e.getId());
                    List<InvestmentInstitutionsFundsSegmentation> investmentInstitutionsFundsSegmentationList = investmentInstitutionsFundsSegmentationService.select(investmentInstitutionsFundsSegmentation);
                    List<Integer> investmentInstitutionsFundsSegmentations = new ArrayList<>();
                    List<String> investmentInstitutionsFundsSegmentationNames = new ArrayList<>();
                    String[] investmentInstitutionsFundsSegmentationArr = null;
                    if(investmentInstitutionsFundsSegmentationList != null && investmentInstitutionsFundsSegmentationList.size() != 0){
                        investmentInstitutionsFundsSegmentationList.forEach(investmentInstitutionsFundsSegmentation_i -> {
                            investmentInstitutionsFundsSegmentations.add(investmentInstitutionsFundsSegmentation_i.getSegmentationId());
                        });
                        Integer[] SegmentationIds = new Integer[investmentInstitutionsFundsSegmentations.size()];
                        investmentInstitutionsFundsSegmentations.toArray(SegmentationIds);
                        List<MetaSegmentation> metaSegmentationList = metaSegmentationMapper.selectBySegmentationIds(SegmentationIds);
                        metaSegmentationList.forEach(metaSegmentation -> {
                            investmentInstitutionsFundsSegmentationNames.add(metaSegmentation.getName());
                        });
                        investmentInstitutionsFundsSegmentationArr = new String[metaSegmentationList.size()];
                        investmentInstitutionsFundsSegmentationNames.toArray(investmentInstitutionsFundsSegmentationArr);
                        fundOutputDto.setFocusDomains(investmentInstitutionsFundsSegmentationArr);
                    }
                    
                    fundOutputDtoList.add(fundOutputDto);
                }
            }
        }
        
        
        result.setData(fundOutputDtoList);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    @Override
    public CommonDto<String> addOrUpdateFund(FundInputDto body) {
        CommonDto<String> result = new CommonDto<>();
        if(null == body ){
            result.setMessage("failed");
            result.setStatus(500);
            result.setData("请输入基金相关信息");
            return result;
        }
        if(null == body.getSubjectType() ){
            result.setMessage("failed");
            result.setStatus(500);
            result.setData("请输入主体类型");
            return result;
        }
        
        if(Integer.valueOf(1).equals(body.getSubjectType())){//项目
        	//TODO 基金和项目之间的关系有待完善，目前以机构为主
        }else if (Integer.valueOf(2).equals(body.getSubjectType())) {//机构
        	InvestmentInstitutionsFunds investmentInstitutionsFunds = new InvestmentInstitutionsFunds();
            investmentInstitutionsFunds.setInvestmentInstitutionsId(body.getProjectId());
            investmentInstitutionsFunds.setId(body.getFundId());
            
            investmentInstitutionsFunds.setCreateTime(DateUtils.parse(body.getEstablishedTime()));
            investmentInstitutionsFunds.setCreator(body.getCreator());
            investmentInstitutionsFunds.setCurrency(body.getCurrencyType());
            investmentInstitutionsFunds.setInvestmentAmountBegin(body.getInvestmentAmountLow());
            investmentInstitutionsFunds.setInvestmentAmountEnd(body.getInvestmentAmountHigh());
            investmentInstitutionsFunds.setDuration(body.getSurvivalPeriod());
            investmentInstitutionsFunds.setName(body.getShortName());
            investmentInstitutionsFunds.setFullName(body.getFullName());
            
            investmentInstitutionsFunds.setYn(0);

            //更新主体信息
            //获取增长后的id
            Integer autoIncreId= body.getFundId();
            if(null == body.getFundId()){
                investmentInstitutionsFundsMapper.insertSelective(investmentInstitutionsFunds);
                autoIncreId=investmentInstitutionsFunds.getId();
            }else{
                investmentInstitutionsFundsMapper.updateByPrimaryKeySelective(investmentInstitutionsFunds);
            }
            
            //更新基金下的投资阶段
            List<InvestmentInstitutionsFundsStages> investmentInstitutionsFundsStagesList = new ArrayList<>();
            investmentInstitutionsFundsStagesService.deleteAll(autoIncreId);
            if(body.getInvestStages().length != 0 && body.getInvestStages() != null){
                for(int i = 0; i<body.getInvestStages().length; i++){
                    InvestmentInstitutionsFundsStages investmentInstitutionsFundsStages = new InvestmentInstitutionsFundsStages();
                    investmentInstitutionsFundsStages.setInvestmentInstitutionsFundsId(autoIncreId);
                    investmentInstitutionsFundsStages.setStageId(body.getInvestStages()[i]);
                    investmentInstitutionsFundsStagesList.add(investmentInstitutionsFundsStages);
                }
            }
            investmentInstitutionsFundsStagesService.insertList(investmentInstitutionsFundsStagesList);

            //更新基金下的投资领域
            List<InvestmentInstitutionsFundsSegmentation> investmentInstitutionsFundsSegmentationList = new ArrayList<>();
            investmentInstitutionsFundsSegmentationService.deleteAll(autoIncreId);
            if(body.getFocusDomains().length != 0 && body.getFocusDomains() != null){
                for(int i = 0; i<body.getFocusDomains().length; i++){
                    InvestmentInstitutionsFundsSegmentation investmentInstitutionsFundsSegmentation = new InvestmentInstitutionsFundsSegmentation();
                    investmentInstitutionsFundsSegmentation.setInvestmentInstitutionsFundsId(autoIncreId);
                    investmentInstitutionsFundsSegmentation.setSegmentationId(body.getFocusDomains()[i]);
                    investmentInstitutionsFundsSegmentationList.add(investmentInstitutionsFundsSegmentation);
                }
            }
            investmentInstitutionsFundsSegmentationService.insertList(investmentInstitutionsFundsSegmentationList);
        }

        result.setData("保存成功");
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }
    
    @Transactional
    @Override
    public CommonDto<String> deleteFund(Integer fundId,Integer subjectType) {
        CommonDto<String> result = new CommonDto<>();
        if(Integer.valueOf(1).equals(subjectType)) {//项目
        	//TODO 删除项目的某一个基金
        }else if(Integer.valueOf(2).equals(subjectType)) {//机构
        	InvestmentInstitutionsFunds e =new InvestmentInstitutionsFunds(); 
        	e.setId(fundId);
        	e.setYn(1);
            investmentInstitutionsFundsMapper.updateByPrimaryKeySelective(e);
        }
        
        result.setMessage("success");
        result.setStatus(200);
        result.setData("删除成功");
        return result;
    }
}
