package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectPreferDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionFeatureMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionPreferSegmentationMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionPreferStageMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionSingleAmountMapper;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.*;
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
 * Created by lanhaijulang on 2018/1/24.
 */
@Service
public class ProjectAdminPreferServiceImpl implements ProjectAdminPreferService {

    @Resource
    private InvestmentInstitutionFeatureService investmentInstitutionFeatureService;

    @Resource
    private InvestmentInstitutionSingleAmountService investmentInstitutionSingleAmountService;

    @Resource
    private InvestmentInstitutionPreferStageService investmentInstitutionPreferStageService;

    @Autowired
    private InvestmentInstitutionPreferSegmentationService investmentInstitutionPreferSegmentationService;
    
    @Autowired
    private InvestmentInstitutionFeatureMapper investmentInstitutionFeatureMapper;
    
    @Autowired
    private InvestmentInstitutionPreferStageMapper investmentInstitutionPreferStageMapper;
    
    @Autowired
    private InvestmentInstitutionPreferSegmentationMapper investmentInstitutionPreferSegmentationMapper;


    public CommonDto<ProjectPreferDto> getProjectprefer(Integer projectId,Integer subjectType) {
        CommonDto<ProjectPreferDto> result = new CommonDto<>();
        ProjectPreferDto projectPreferDto = new ProjectPreferDto();

        if(projectId == null){
            result.setStatus(500);
            result.setMessage("请输入主体id");
            result.setData(null);
            return result;
        }
        if(subjectType == null){
            result.setStatus(500);
            result.setMessage("请输入主体类型");
            result.setData(null);
            return result;
        }
        if(Integer.valueOf(1).equals(subjectType)) {
        	//TODO 项目投资理念和偏好设置
        }else if(Integer.valueOf(2).equals(subjectType)) {
        	InvestmentInstitutionFeature investmentInstitutionFeature = investmentInstitutionFeatureService.selectByPrimaryKey(projectId);
            if(investmentInstitutionFeature !=null) {
            	projectPreferDto.setProjectId(investmentInstitutionFeature.getCompanyId());
            	//投资理念
                projectPreferDto.setInvestmengRequirement(investmentInstitutionFeature.getInvestmentRequirement());
                //投资要求
                projectPreferDto.setInvestmentPhilosophy(investmentInstitutionFeature.getInvestmentPhilosophy());
                
                //投资偏好的投资领域
                InvestmentInstitutionPreferSegmentation investmentInstitutionPreferSegmentation = new InvestmentInstitutionPreferSegmentation();
                investmentInstitutionPreferSegmentation.setCompanyId(projectId);
                List<InvestmentInstitutionPreferSegmentation> segmentations = investmentInstitutionPreferSegmentationService.select(investmentInstitutionPreferSegmentation);
                
                if(null != segmentations && segmentations.size() !=0){
                    List<Integer> segs = new ArrayList<>();
                    segmentations.forEach( e -> {
                    	segs.add(e.getSegmentationPreferId());
                    });
                    projectPreferDto.setSegmentationPreferIds(segs);
                }


                InvestmentInstitutionPreferStage investmentInstitutionPreferStage = new InvestmentInstitutionPreferStage();
                investmentInstitutionPreferStage.setCompanyId(projectId);
                List<InvestmentInstitutionPreferStage> investmentInstitutionPreferStages = investmentInstitutionPreferStageService.select(investmentInstitutionPreferStage);
                //投资偏好的投资阶段
                if (null != investmentInstitutionPreferStages && investmentInstitutionPreferStages.size() != 0){
                    List<Integer> stages = new ArrayList<>();
                    investmentInstitutionPreferStages.forEach( e -> {
                    	stages.add(e.getStageId());
                    });
                    projectPreferDto.setStageId(stages);
                }
                /**
                 * 投资机构单笔投资金额
                 * 0人民币
                 * 1美元
                 */
                InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount = new InvestmentInstitutionSingleAmount();
                investmentInstitutionSingleAmount.setCompanyId(projectId);
                List<InvestmentInstitutionSingleAmount> singleAmounts = investmentInstitutionSingleAmountService.select(investmentInstitutionSingleAmount);

                for(InvestmentInstitutionSingleAmount obj : singleAmounts){
                    if(obj.getCurrencyId() == 0){
                        projectPreferDto.setInvestmentAmountSingleLowRmb(obj.getInvestmentAmountSingleLow());
                        projectPreferDto.setInvestmentAmountSingleHighRmb(obj.getInvestmentAmountSingleHigh());
                    }else if(obj.getCurrencyId() == 1){
                        projectPreferDto.setInvestmentAmountSingleLowDollar(obj.getInvestmentAmountSingleLow());
                        projectPreferDto.setInvestmentAmountSingleHighDollar(obj.getInvestmentAmountSingleHigh());
                    }else{
//                        continue;
                    }
                }
            }
        }

        result.setStatus(200);
        result.setMessage("success");
        result.setData(projectPreferDto);
        return result;
    }
    
    @Transactional
    @Override
    public CommonDto<String> addOrUpdatePrefer(ProjectPreferDto body) {
        CommonDto<String> result = new CommonDto<>();
        
        if(null == body.getProjectId()){
            result.setStatus(500);
            result.setMessage("请输入公司id");
            result.setData(null);
            return result;
        }
        if(null == body.getSubjectType()){
            result.setStatus(500);
            result.setMessage("请输入主体类型");
            result.setData(null);
            return result;
        }
        
        if(Integer.valueOf(1).equals(body.getSubjectType())) {//项目
        	//TODO 项目投资理念和偏好的设置
        }else if(Integer.valueOf(2).equals(body.getSubjectType())) {//机构
        	InvestmentInstitutionFeature investmentInstitutionFeature = new InvestmentInstitutionFeature();
            investmentInstitutionFeature.setCompanyId(body.getProjectId());
            investmentInstitutionFeature.setInvestmentPhilosophy(body.getInvestmentPhilosophy());
            investmentInstitutionFeature.setInvestmentRequirement(body.getInvestmengRequirement());
            investmentInstitutionFeature.setCreator(body.getToken());

            //主体信息的更新
            if(investmentInstitutionFeatureMapper.existsWithPrimaryKey(body.getProjectId())) {
            	investmentInstitutionFeature.setUpdateTime(new Date());
                investmentInstitutionFeatureService.updateByPrimaryKey(investmentInstitutionFeature);
            }else {//新增
            	investmentInstitutionFeature.setCreateTime(new Date());
                investmentInstitutionFeatureService.save(investmentInstitutionFeature);
            }
            
            //投资阶段
            investmentInstitutionPreferStageService.deleteAll(body.getProjectId());
            
            if(null != body.getStageId() && body.getStageId().size() != 0){
                for(Integer e : body.getStageId()){
                    InvestmentInstitutionPreferStage stage  = new InvestmentInstitutionPreferStage();
                    stage.setStageId(e);
                    stage.setCompanyId(body.getProjectId());
                    
                    investmentInstitutionPreferStageMapper.insertSelective(stage);
                }
            }
            
            //投资领域
            investmentInstitutionPreferSegmentationService.deleteAll(body.getProjectId());
            
            if(null != body.getSegmentationPreferIds() && body.getSegmentationPreferIds().size() != 0){
                for(Integer e : body.getSegmentationPreferIds()){
                    InvestmentInstitutionPreferSegmentation seg  = new InvestmentInstitutionPreferSegmentation();
                    seg.setSegmentationPreferId(e);
                    seg.setCompanyId(body.getProjectId());
                    
                    investmentInstitutionPreferSegmentationMapper.insertSelective(seg);
                }
            }
            //单笔投资金额
            investmentInstitutionSingleAmountService.deleteAll(body.getProjectId());
            
            InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount = new InvestmentInstitutionSingleAmount();
            investmentInstitutionSingleAmount.setCompanyId(body.getProjectId());
            //人民币
            investmentInstitutionSingleAmount.setCurrencyId(0);  
            investmentInstitutionSingleAmount.setInvestmentAmountSingleLow(body.getInvestmentAmountSingleLowRmb());
            investmentInstitutionSingleAmount.setInvestmentAmountSingleHigh(body.getInvestmentAmountSingleHighRmb());
            investmentInstitutionSingleAmountService.save(investmentInstitutionSingleAmount);

            InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount1 = new InvestmentInstitutionSingleAmount();
            investmentInstitutionSingleAmount1.setCompanyId(body.getProjectId());
            //美元
            investmentInstitutionSingleAmount1.setCurrencyId(1);
            investmentInstitutionSingleAmount1.setInvestmentAmountSingleLow(body.getInvestmentAmountSingleLowDollar());
            investmentInstitutionSingleAmount1.setInvestmentAmountSingleHigh(body.getInvestmentAmountSingleHighDollar());
            investmentInstitutionSingleAmountService.save(investmentInstitutionSingleAmount1);
        }
        
        result.setStatus(200);
        result.setMessage("success");
        result.setData("保存成功");
        
        return result;
    }
}
