package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.FundDto.FundInputDto;
import com.lhjl.tzzs.proxy.dto.FundDto.FundOutputDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsFundsMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsFundsSegmentationMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsFundsStagesMapper;
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
    
    @Autowired
    private InvestmentInstitutionsFundsStagesMapper investmentInstitutionsFundsStagesMapper;
    
    @Autowired
    private InvestmentInstitutionsFundsSegmentationMapper investmentInstitutionsFundsSegmentationMapper;

    @Override
    public CommonDto<List<FundOutputDto>> getFundList(Integer subjectId,Integer subjectType) {
        CommonDto<List<FundOutputDto>> result = new CommonDto<>();
        List<FundOutputDto> fundOutputDtoList = new ArrayList<>();
        if(Integer.valueOf(1).equals(subjectType)){ //项目
        	
        }else if(Integer.valueOf(2).equals(subjectType)) {//机构
        	List<InvestmentInstitutionsFunds> fundsList = investmentInstitutionsFundsMapper.getFundList(subjectId);
            if(null != fundsList && fundsList.size() != 0){
                for(InvestmentInstitutionsFunds e: fundsList){
                    FundOutputDto fundOutputDto = new FundOutputDto();
                    //基金id
                    fundOutputDto.setFundId(e.getId());
                    fundOutputDto.setShortName(e.getName());
                    fundOutputDto.setFullName(e.getFullName());
                	fundOutputDto.setEstablishedTime(e.getCreateTime());
                    fundOutputDto.setSurvivalPeriod(e.getDuration());
                    fundOutputDto.setCurrencyType(e.getCurrency());
                    fundOutputDto.setFundManageScale(e.getScale());
                    fundOutputDto.setInvestmentAmountLow(e.getInvestmentAmountBegin());
                    fundOutputDto.setInvestmentAmountHigh(e.getInvestmentAmountEnd());
                    
                    //投资阶段设置
                    InvestmentInstitutionsFundsStages investmentInstitutionsFundsStages = new InvestmentInstitutionsFundsStages();
                    investmentInstitutionsFundsStages.setInvestmentInstitutionsFundsId(e.getId());
                    List<InvestmentInstitutionsFundsStages> fundsStages = investmentInstitutionsFundsStagesService.select(investmentInstitutionsFundsStages);
                    	
                    if(fundsStages != null && fundsStages.size() != 0){
                    	List<Integer> fundsStageIds = new ArrayList<>();
                        List<String> fundsStageNames = new ArrayList<>();
                        
                        fundsStages.forEach(obj -> {
                            fundsStageIds.add(obj.getStageId());
                        });
                        List<MetaProjectStage> metaProjectStageList = metaProjectStageMapper.selectByStageIds(fundsStageIds);
                        
                        if(metaProjectStageList!=null && metaProjectStageList.size()>0) {
                        	metaProjectStageList.forEach( obj -> {
                                fundsStageNames.add(obj.getName());
                            });
                        }
                        
                        fundOutputDto.setInvestStages(fundsStageNames);
                    }
                    //投资领域设置  
                    InvestmentInstitutionsFundsSegmentation investmentInstitutionsFundsSegmentation = new InvestmentInstitutionsFundsSegmentation();
                    investmentInstitutionsFundsSegmentation.setInvestmentInstitutionsFundsId(e.getId());
                    List<InvestmentInstitutionsFundsSegmentation> fundsSegmentations = investmentInstitutionsFundsSegmentationService.select(investmentInstitutionsFundsSegmentation);
                    
                    if(fundsSegmentations != null && fundsSegmentations.size() != 0){
                    	List<Integer> fundsSegmentationIds = new ArrayList<>();
                        List<String> fundsSegmentationNames = new ArrayList<>();
                        
                        fundsSegmentations.forEach(obj -> {
                        	fundsSegmentationIds.add(obj.getSegmentationId());
                        });
                        List<MetaSegmentation> metaSegmentationList = metaSegmentationMapper.selectBySegmentationIds(fundsSegmentationIds);
                          
                        if(metaSegmentationList!=null && metaSegmentationList.size()!=0) {
                        	metaSegmentationList.forEach(obj -> {
                                fundsSegmentationNames.add(obj.getName());
                            });
                        }
                           
                        fundOutputDto.setFocusDomains(fundsSegmentationNames);
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
        if(null == body.getProjectId() ){
            result.setMessage("请输入机构id");
            result.setStatus(500);
            result.setData(null);
            return result;
        }
        if(null == body.getSubjectType() ){
            result.setMessage("请输入主体类型");
            result.setStatus(500);
            result.setData(null);
            return result;
        }
        
        if(Integer.valueOf(1).equals(body.getSubjectType())){//项目
        	//TODO 基金和项目之间的关系有待完善，目前以机构为主
        }else if (Integer.valueOf(2).equals(body.getSubjectType())) {//机构
        	InvestmentInstitutionsFunds investmentInstitutionsFunds = new InvestmentInstitutionsFunds();
        	
        	investmentInstitutionsFunds.setId(body.getFundId());
            investmentInstitutionsFunds.setInvestmentInstitutionsId(body.getProjectId());
            //成立时间
            investmentInstitutionsFunds.setCreateTime(body.getEstablishedTime());
            investmentInstitutionsFunds.setCreator(body.getCreator());
            investmentInstitutionsFunds.setCurrency(body.getCurrencyType());
            investmentInstitutionsFunds.setInvestmentAmountBegin(body.getInvestmentAmountLow());
            investmentInstitutionsFunds.setInvestmentAmountEnd(body.getInvestmentAmountHigh());
            investmentInstitutionsFunds.setDuration(body.getSurvivalPeriod());
            investmentInstitutionsFunds.setName(body.getShortName());
            investmentInstitutionsFunds.setFullName(body.getFullName());
            //设置有效标志
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
            investmentInstitutionsFundsStagesService.deleteAll(autoIncreId);
            if(body.getInvestStages().size() != 0 && body.getInvestStages() != null){
                for(Integer e : body.getInvestStages()){
                    InvestmentInstitutionsFundsStages stage = new InvestmentInstitutionsFundsStages();
                    stage.setInvestmentInstitutionsFundsId(autoIncreId);
                    stage.setStageId(e);
                    
                    investmentInstitutionsFundsStagesMapper.insertSelective(stage);
                }
            }
            
            //更新基金下的投资领域
            investmentInstitutionsFundsSegmentationService.deleteAll(autoIncreId);
            
            if(body.getFocusDomains().size() != 0 && body.getFocusDomains() != null){
                for(Integer e:body.getFocusDomains()){
                    InvestmentInstitutionsFundsSegmentation seg = new InvestmentInstitutionsFundsSegmentation();
                    seg.setInvestmentInstitutionsFundsId(autoIncreId);
                    seg.setSegmentationId(e);
                    
                    investmentInstitutionsFundsSegmentationMapper.insertSelective(seg);
                }
            }
            
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
