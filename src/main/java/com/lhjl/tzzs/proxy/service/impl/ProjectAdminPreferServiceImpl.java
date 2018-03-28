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
                projectPreferDto.setInvestmengRequirement(investmentInstitutionFeature.getInvestmentRequirement());
                projectPreferDto.setInvestmentPhilosophy(investmentInstitutionFeature.getInvestmentPhilosophy());

                InvestmentInstitutionPreferSegmentation investmentInstitutionPreferSegmentation = new InvestmentInstitutionPreferSegmentation();
                investmentInstitutionPreferSegmentation.setCompanyId(projectId);
                List<InvestmentInstitutionPreferSegmentation> investmentInstitutionPreferSegmentations = investmentInstitutionPreferSegmentationService.select(investmentInstitutionPreferSegmentation);
                Integer[] investmentInstitutionPreferSegmentationArr = null;
                if(null != investmentInstitutionPreferSegmentations && investmentInstitutionPreferSegmentations.size() !=0){
                    investmentInstitutionPreferSegmentationArr = new Integer[investmentInstitutionPreferSegmentations.size()];
                    List<Integer> investmentInstitutionPreferSegmentationIds = new ArrayList<>();
                    investmentInstitutionPreferSegmentations.forEach( investmentInstitutionPreferSegmentation_i -> {
                        investmentInstitutionPreferSegmentationIds.add(investmentInstitutionPreferSegmentation_i.getSegmentationPreferId());
                    });
                    investmentInstitutionPreferSegmentationIds.toArray(investmentInstitutionPreferSegmentationArr);
                }
                projectPreferDto.setSegmentationPreferIds(investmentInstitutionPreferSegmentationArr);


                InvestmentInstitutionPreferStage investmentInstitutionPreferStage = new InvestmentInstitutionPreferStage();
                investmentInstitutionPreferStage.setCompanyId(projectId);
                List<InvestmentInstitutionPreferStage> investmentInstitutionPreferStages = investmentInstitutionPreferStageService.select(investmentInstitutionPreferStage);
                Integer[] investmentInstitutionPreferStageArr = null;
                if (null != investmentInstitutionPreferStages && investmentInstitutionPreferStages.size() != 0){
                    List<Integer> investmentInstitutionPreferStageIds = new ArrayList<>();
                    investmentInstitutionPreferStages.forEach( investmentInstitutionPreferStage_i -> {
                        investmentInstitutionPreferStageIds.add(investmentInstitutionPreferStage_i.getStageId());
                    });
                    investmentInstitutionPreferStageArr = new Integer[investmentInstitutionPreferStages.size()];
                    investmentInstitutionPreferStageIds.toArray(investmentInstitutionPreferStageArr);
                }
                projectPreferDto.setStageId(investmentInstitutionPreferStageArr);
                /**
                 * 投资机构单笔投资金额
                 * 0人民币
                 * 1美元
                 */
                InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount = new InvestmentInstitutionSingleAmount();
                investmentInstitutionSingleAmount.setCompanyId(projectId);
                List<InvestmentInstitutionSingleAmount> investmentInstitutionSingleAmounts = investmentInstitutionSingleAmountService.select(investmentInstitutionSingleAmount);

                for(InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount_i : investmentInstitutionSingleAmounts){
                    if(investmentInstitutionSingleAmount_i.getCurrencyId() == 0){
                        projectPreferDto.setInvestmentAmountSingleLowRmb(investmentInstitutionSingleAmount_i.getInvestmentAmountSingleLow());
                        projectPreferDto.setInvestmentAmountSingleHighRmb(investmentInstitutionSingleAmount_i.getInvestmentAmountSingleHigh());
                    }else if(investmentInstitutionSingleAmount_i.getCurrencyId() == 1){
                        projectPreferDto.setInvestmentAmountSingleLowDollar(investmentInstitutionSingleAmount_i.getInvestmentAmountSingleLow());
                        projectPreferDto.setInvestmentAmountSingleHighDollar(investmentInstitutionSingleAmount_i.getInvestmentAmountSingleHigh());
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
        if(null == body){
            result.setStatus(500);
            result.setMessage("failed");
            result.setData("请输入相关信息");
            return result;
        }
        if(null == body.getSubjectType()){
            result.setStatus(500);
            result.setMessage("failed");
            result.setData("请输入主体id");
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
            Integer autoIncreId=body.getProjectId();
            if(null == body.getProjectId()){//新增
                investmentInstitutionFeature.setCreateTime(new Date());
                investmentInstitutionFeatureService.save(investmentInstitutionFeature);
                autoIncreId=investmentInstitutionFeature.getCompanyId();
            }else{
                investmentInstitutionFeature.setUpdateTime(new Date());
                investmentInstitutionFeatureService.updateByPrimaryKey(investmentInstitutionFeature);
            }
            //投资阶段
            List<InvestmentInstitutionPreferStage> investmentInstitutionPreferStageList = new ArrayList<>();
            investmentInstitutionPreferStageService.deleteAll(autoIncreId);
            if(null != body.getStageId() && body.getStageId().length != 0){
                for(Integer stageId : body.getStageId()){
                    InvestmentInstitutionPreferStage investmentInstitutionPreferStage1  = new InvestmentInstitutionPreferStage();
                    investmentInstitutionPreferStage1.setStageId(stageId);
                    investmentInstitutionPreferStage1.setCompanyId(autoIncreId);
                    investmentInstitutionPreferStageList.add(investmentInstitutionPreferStage1);
                }
            }
            investmentInstitutionPreferStageService.insertList(investmentInstitutionPreferStageList);
            //投资领域
            List<InvestmentInstitutionPreferSegmentation> investmentInstitutionPreferSegmentationList = new ArrayList<>();
            investmentInstitutionPreferSegmentationService.deleteAll(autoIncreId);
            if(null != body.getSegmentationPreferIds() && body.getSegmentationPreferIds().length != 0){
                for(Integer segmentationId : body.getSegmentationPreferIds()){
                    InvestmentInstitutionPreferSegmentation investmentInstitutionPreferSegmentation1  = new InvestmentInstitutionPreferSegmentation();
                    investmentInstitutionPreferSegmentation1.setSegmentationPreferId(segmentationId);
                    investmentInstitutionPreferSegmentation1.setCompanyId(autoIncreId);
                    investmentInstitutionPreferSegmentationList.add(investmentInstitutionPreferSegmentation1);
                }
            }
            investmentInstitutionPreferSegmentationService.insertList(investmentInstitutionPreferSegmentationList);
            //单笔投资金额
            InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount = new InvestmentInstitutionSingleAmount();
            InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount1 = new InvestmentInstitutionSingleAmount();
            investmentInstitutionSingleAmountService.deleteAll(autoIncreId);
            
            investmentInstitutionSingleAmount.setCompanyId(autoIncreId);
            //人民币
            investmentInstitutionSingleAmount.setCurrencyId(0);
            investmentInstitutionSingleAmount.setInvestmentAmountSingleLow(body.getInvestmentAmountSingleLowRmb());
            investmentInstitutionSingleAmount.setInvestmentAmountSingleHigh(body.getInvestmentAmountSingleHighRmb());
            investmentInstitutionSingleAmountService.save(investmentInstitutionSingleAmount);

            investmentInstitutionSingleAmount1.setCompanyId(autoIncreId);
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
