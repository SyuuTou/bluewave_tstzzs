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


    public CommonDto<ProjectPreferDto> getProjectprefer(Integer projectId) {
        CommonDto<ProjectPreferDto> result = new CommonDto<>();
        ProjectPreferDto projectPreferDto = new ProjectPreferDto();

        if(projectId == null){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }

        InvestmentInstitutionFeature investmentInstitutionFeature = investmentInstitutionFeatureService.selectByPrimaryKey(projectId);
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
                continue;
            }
        }

        result.setStatus(200);
        result.setMessage("success");
        result.setData(projectPreferDto);
        return result;
    }

    @Override
    public CommonDto<String> addOrUpdatePrefer(ProjectPreferDto body) {
        CommonDto<String> result = new CommonDto<>();
        if(null == body){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData("请输入相关信息");
            return result;
        }

        InvestmentInstitutionFeature investmentInstitutionFeature = new InvestmentInstitutionFeature();
        InvestmentInstitutionPreferSegmentation investmentInstitutionPreferSegmentation = new InvestmentInstitutionPreferSegmentation();
        InvestmentInstitutionPreferStage investmentInstitutionPreferStage = new InvestmentInstitutionPreferStage();

        investmentInstitutionFeature.setCompanyId(body.getProjectId());
        investmentInstitutionFeature.setInvestmentPhilosophy(body.getInvestmentPhilosophy());
        investmentInstitutionFeature.setInvestmentRequirement(body.getInvestmengRequirement());
        investmentInstitutionFeature.setCreator(body.getToken());

        Integer investmentInstitutionFeatureInsertResult = -1;
        long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = null;
        try {
            createTime = sdf.format(new Date(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        InvestmentInstitutionFeature investmentInstitutionFeature1 = investmentInstitutionFeatureService.selectByPrimaryKey(body.getProjectId());
        if(null == investmentInstitutionFeature1){
            investmentInstitutionFeature.setCreateTime(DateUtils.parse(createTime));
            investmentInstitutionFeatureInsertResult = investmentInstitutionFeatureService.save(investmentInstitutionFeature);
        }else{
            investmentInstitutionFeature.setUpdateTime(DateUtils.parse(createTime));
            investmentInstitutionFeatureInsertResult = investmentInstitutionFeatureService.updateByPrimaryKey(investmentInstitutionFeature);
        }

        List<InvestmentInstitutionPreferStage> investmentInstitutionPreferStageList = new ArrayList<>();
        investmentInstitutionPreferStageService.deleteAll(body.getProjectId());
        if(null == body.getStageId() || body.getStageId().length == 0){
            InvestmentInstitutionPreferStage investmentInstitutionPreferStage1 = new InvestmentInstitutionPreferStage();
            investmentInstitutionPreferStage1.setCompanyId(body.getProjectId());
            investmentInstitutionPreferStage1.setStageId(null);
            investmentInstitutionPreferStageList.add(investmentInstitutionPreferStage1);
        }else{
            for(Integer stageId : body.getStageId()){
                InvestmentInstitutionPreferStage investmentInstitutionPreferStage1  = new InvestmentInstitutionPreferStage();
                investmentInstitutionPreferStage1.setStageId(stageId);
                investmentInstitutionPreferStage1.setCompanyId(body.getProjectId());
                investmentInstitutionPreferStageList.add(investmentInstitutionPreferStage1);
            }
        }

        Integer investmentInstitutionPreferStageInsertResult = investmentInstitutionPreferStageService.insertList(investmentInstitutionPreferStageList);

        List<InvestmentInstitutionPreferSegmentation> investmentInstitutionPreferSegmentationList = new ArrayList<>();
        investmentInstitutionPreferSegmentationService.deleteAll(body.getProjectId());
        if(null == body.getSegmentationPreferIds() || body.getSegmentationPreferIds().length == 0){
            InvestmentInstitutionPreferSegmentation investmentInstitutionPreferSegmentation1 = new InvestmentInstitutionPreferSegmentation();
            investmentInstitutionPreferSegmentation1.setCompanyId(body.getProjectId());
            investmentInstitutionPreferSegmentation1.setSegmentationPreferId(null);
            investmentInstitutionPreferSegmentationList.add(investmentInstitutionPreferSegmentation1);
        }else{
            for(Integer segmentationId : body.getSegmentationPreferIds()){
                InvestmentInstitutionPreferSegmentation investmentInstitutionPreferSegmentation1  = new InvestmentInstitutionPreferSegmentation();
                investmentInstitutionPreferSegmentation1.setSegmentationPreferId(segmentationId);
                investmentInstitutionPreferSegmentation1.setCompanyId(body.getProjectId());
                investmentInstitutionPreferSegmentationList.add(investmentInstitutionPreferSegmentation1);
            }
        }

        Integer investmentInstitutionPreferSegmentationInsertResult = investmentInstitutionPreferSegmentationService.insertList(investmentInstitutionPreferSegmentationList);

        InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount = new InvestmentInstitutionSingleAmount();
        InvestmentInstitutionSingleAmount investmentInstitutionSingleAmount1 = new InvestmentInstitutionSingleAmount();
        investmentInstitutionSingleAmountService.deleteAll(body.getProjectId());
        investmentInstitutionSingleAmount.setCompanyId(body.getProjectId());
        investmentInstitutionSingleAmount.setCurrencyId(0);
        investmentInstitutionSingleAmount.setInvestmentAmountSingleLow(body.getInvestmentAmountSingleLowRmb());
        investmentInstitutionSingleAmount.setInvestmentAmountSingleHigh(body.getInvestmentAmountSingleHighRmb());

        Integer investmentInstitutionSingleAmountInsertResult = investmentInstitutionSingleAmountService.save(investmentInstitutionSingleAmount);

        investmentInstitutionSingleAmount1.setCompanyId(body.getProjectId());
        investmentInstitutionSingleAmount1.setCurrencyId(1);
        investmentInstitutionSingleAmount1.setInvestmentAmountSingleLow(body.getInvestmentAmountSingleLowDollar());
        investmentInstitutionSingleAmount1.setInvestmentAmountSingleHigh(body.getInvestmentAmountSingleHighDollar());

        Integer investmentInstitutionSingleAmount1InsertResult = investmentInstitutionSingleAmountService.save(investmentInstitutionSingleAmount1);

        if(investmentInstitutionFeatureInsertResult > 0 && investmentInstitutionPreferStageInsertResult > 0 &&
        investmentInstitutionPreferSegmentationInsertResult > 0 &&  investmentInstitutionSingleAmountInsertResult > 0 &&
                investmentInstitutionSingleAmount1InsertResult > 0){
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
}
