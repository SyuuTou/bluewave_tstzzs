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

    @Transactional
    @Override
    public CommonDto<List<FundOutputDto>> getFundList(Integer projectId) {
        CommonDto<List<FundOutputDto>> result = new CommonDto<>();
        List<FundOutputDto> fundOutputDtoList = new ArrayList<>();
        List<InvestmentInstitutionsFunds> investmentInstitutionsFundsList = investmentInstitutionsFundsMapper.getFundList(projectId);

        if(null != investmentInstitutionsFundsList && investmentInstitutionsFundsList.size() != 0){
            for(InvestmentInstitutionsFunds investmentInstitutionsFunds_i: investmentInstitutionsFundsList){
                FundOutputDto fundOutputDto = new FundOutputDto();
                fundOutputDto.setFundId(investmentInstitutionsFunds_i.getId());
                fundOutputDto.setShortName(investmentInstitutionsFunds_i.getName());
                fundOutputDto.setFullName(investmentInstitutionsFunds_i.getFullName());
                fundOutputDto.setEstablishedTime(String.valueOf(investmentInstitutionsFunds_i.getCreateTime()));
                fundOutputDto.setSurvivalPeriod(investmentInstitutionsFunds_i.getDuration());
                fundOutputDto.setCurrencyType(investmentInstitutionsFunds_i.getCurrency());
                fundOutputDto.setFundManageScale(investmentInstitutionsFunds_i.getScale());
                fundOutputDto.setInvestmentAmountLow(investmentInstitutionsFunds_i.getInvestmentAmountBegin());
                fundOutputDto.setInvestmentAmountHigh(investmentInstitutionsFunds_i.getInvestmentAmountEnd());

                InvestmentInstitutionsFundsStages investmentInstitutionsFundsStages = new InvestmentInstitutionsFundsStages();
                investmentInstitutionsFundsStages.setInvestmentInstitutionsFundsId(investmentInstitutionsFunds_i.getId());
                List<InvestmentInstitutionsFundsStages> investmentInstitutionsFundsStagesList = investmentInstitutionsFundsStagesService.select(investmentInstitutionsFundsStages);
                List<Integer> investmentInstitutionsFundsStagess = new ArrayList<>();
                List<String> investmentInstitutionsFundsStageNames = new ArrayList<>();
                String[] investmentInstitutionsFundsStagesArr = null;
                if(investmentInstitutionsFundsStagesList == null || investmentInstitutionsFundsStagesList.size() == 0){
                    fundOutputDto.setInvestStages(investmentInstitutionsFundsStagesArr);
                }else{
                    investmentInstitutionsFundsStagesList.forEach(investmentInstitutionsFundsStages_i -> {
                        investmentInstitutionsFundsStagess.add(investmentInstitutionsFundsStages_i.getStageId());
                    });
                    Integer[] StageIds = new Integer[investmentInstitutionsFundsStagess.size()];
                    investmentInstitutionsFundsStagess.toArray(StageIds);
                    List<MetaProjectStage> metaProjectStageList = metaProjectStageMapper.selectByStageIds(StageIds);
                    metaProjectStageList.forEach( metaProjectStage -> {
                        investmentInstitutionsFundsStageNames.add(metaProjectStage.getName());
                    });
                    investmentInstitutionsFundsStagesArr = new String[investmentInstitutionsFundsStagesList.size()];
                    investmentInstitutionsFundsStageNames.toArray(investmentInstitutionsFundsStagesArr);
                    fundOutputDto.setInvestStages(investmentInstitutionsFundsStagesArr);
                }

                InvestmentInstitutionsFundsSegmentation investmentInstitutionsFundsSegmentation = new InvestmentInstitutionsFundsSegmentation();
                investmentInstitutionsFundsSegmentation.setInvestmentInstitutionsFundsId(investmentInstitutionsFunds_i.getId());
                List<InvestmentInstitutionsFundsSegmentation> investmentInstitutionsFundsSegmentationList = investmentInstitutionsFundsSegmentationService.select(investmentInstitutionsFundsSegmentation);
                List<Integer> investmentInstitutionsFundsSegmentations = new ArrayList<>();
                List<String> investmentInstitutionsFundsSegmentationNames = new ArrayList<>();
                String[] investmentInstitutionsFundsSegmentationArr = null;
                if(investmentInstitutionsFundsSegmentationList == null || investmentInstitutionsFundsSegmentationList.size() == 0){
                    fundOutputDto.setInvestStages(investmentInstitutionsFundsSegmentationArr);
                }else{
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
        result.setData(fundOutputDtoList);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    @Override
    public CommonDto<String> addOrUpdateFund(FundInputDto body) {
        CommonDto<String> result = new CommonDto<>();
        if(null == body || body.toString() == ""){
            result.setMessage("success");
            result.setStatus(200);
            result.setData("请输入基金相关信息");
            return result;
        }

        InvestmentInstitutionsFunds investmentInstitutionsFunds = new InvestmentInstitutionsFunds();
        investmentInstitutionsFunds.setInvestmentInstitutionsId(body.getProjectId());
        investmentInstitutionsFunds.setYn(0);
        investmentInstitutionsFunds.setCreateTime(DateUtils.parse(body.getEstablishedTime()));
        investmentInstitutionsFunds.setCreator(body.getCreator());
        investmentInstitutionsFunds.setCurrency(body.getCurrencyType());
        investmentInstitutionsFunds.setInvestmentAmountBegin(body.getInvestmentAmountLow());
        investmentInstitutionsFunds.setInvestmentAmountEnd(body.getInvestmentAmountHigh());
        investmentInstitutionsFunds.setDuration(body.getSurvivalPeriod());
        investmentInstitutionsFunds.setName(body.getShortName());
        investmentInstitutionsFunds.setFullName(body.getFullName());

        Integer fundInsertResult = -1;

        if(null == body.getFundId()){
            fundInsertResult = investmentInstitutionsFundsMapper.insert(investmentInstitutionsFunds);
        }else{
            fundInsertResult = investmentInstitutionsFundsMapper.updateByPrimaryKey(investmentInstitutionsFunds);
        }

        Integer fundStageInsertResult = -1;
        List<InvestmentInstitutionsFundsStages> investmentInstitutionsFundsStagesList = new ArrayList<>();
        investmentInstitutionsFundsStagesService.deleteAll(investmentInstitutionsFunds.getId());
        if(body.getInvestStages().length == 0 || body.getInvestStages() == null){
            InvestmentInstitutionsFundsStages investmentInstitutionsFundsStages = new InvestmentInstitutionsFundsStages();
            investmentInstitutionsFundsStages.setInvestmentInstitutionsFundsId(investmentInstitutionsFunds.getId());
            investmentInstitutionsFundsStages.setStageId(null);
            investmentInstitutionsFundsStagesList.add(investmentInstitutionsFundsStages);
        }else{
            for(int i = 0; i<body.getInvestStages().length; i++){
                InvestmentInstitutionsFundsStages investmentInstitutionsFundsStages = new InvestmentInstitutionsFundsStages();
                investmentInstitutionsFundsStages.setInvestmentInstitutionsFundsId(investmentInstitutionsFunds.getId());
                investmentInstitutionsFundsStages.setStageId(body.getInvestStages()[i]);
                investmentInstitutionsFundsStagesList.add(investmentInstitutionsFundsStages);
            }
        }

        fundStageInsertResult = investmentInstitutionsFundsStagesService.insertList(investmentInstitutionsFundsStagesList);

        Integer fundDomainInsertResult = -1;

        List<InvestmentInstitutionsFundsSegmentation> investmentInstitutionsFundsSegmentationList = new ArrayList<>();
        investmentInstitutionsFundsSegmentationService.deleteAll(investmentInstitutionsFunds.getId());
        if(body.getFocusDomains().length == 0 || body.getFocusDomains() == null){
            InvestmentInstitutionsFundsSegmentation investmentInstitutionsFundsSegmentation = new InvestmentInstitutionsFundsSegmentation();
            investmentInstitutionsFundsSegmentation.setInvestmentInstitutionsFundsId(investmentInstitutionsFunds.getId());
            investmentInstitutionsFundsSegmentation.setSegmentationId(null);
            investmentInstitutionsFundsSegmentationList.add(investmentInstitutionsFundsSegmentation);
        }else{
            for(int i = 0; i<body.getFocusDomains().length; i++){
                InvestmentInstitutionsFundsSegmentation investmentInstitutionsFundsSegmentation = new InvestmentInstitutionsFundsSegmentation();
                investmentInstitutionsFundsSegmentation.setInvestmentInstitutionsFundsId(investmentInstitutionsFunds.getId());
                investmentInstitutionsFundsSegmentation.setSegmentationId(body.getFocusDomains()[i]);
                investmentInstitutionsFundsSegmentationList.add(investmentInstitutionsFundsSegmentation);
            }
        }
        fundStageInsertResult = investmentInstitutionsFundsSegmentationService.insertList(investmentInstitutionsFundsSegmentationList);

        //TODO 基金和项目之间的关系，插入基金是否要插入项目

        result.setData("保存成功");
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }

    @Override
    public CommonDto<String> deleteFund(Integer fundId) {

        CommonDto<String> result = new CommonDto<>();

        InvestmentInstitutionsFunds investmentInstitutionsFund = investmentInstitutionsFundsMapper.selectByPrimaryKey(fundId);
        if(null == investmentInstitutionsFund){
            result.setMessage("failed");
            result.setStatus(200);
            result.setData("删除失败");
            return result;
        }
        investmentInstitutionsFund.setYn(1);
        Integer investmentInstitutionsFundInsertResult = investmentInstitutionsFundsMapper.updateByPrimaryKey(investmentInstitutionsFund);
        if(investmentInstitutionsFundInsertResult > 0) {
            result.setMessage("success");
            result.setStatus(200);
            result.setData("删除成功");
            return result;
        }
        result.setMessage("failed");
        result.setStatus(200);
        result.setData("删除失败");
        return result;
    }
}
