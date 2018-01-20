package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.FundDto.FundInputDto;
import com.lhjl.tzzs.proxy.dto.FundDto.FundOutputDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsFundsMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFunds;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFundsSegmentation;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFundsStages;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsFundsLabelService;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsFundsSegmentationService;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsFundsStagesService;
import com.lhjl.tzzs.proxy.service.ProjectAdminFundService;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Resource
    private InvestmentInstitutionsFundsLabelService investmentInstitutionsFundsLabelService;

    @Resource
    private InvestmentInstitutionsFundsSegmentationService investmentInstitutionsFundsSegmentationService;

    @Resource
    private InvestmentInstitutionsFundsStagesService investmentInstitutionsFundsStagesService;


    @Override
    public CommonDto<List<FundOutputDto>> getFundList(Integer projectId) {
        CommonDto<List<FundOutputDto>> result = new CommonDto<>();
        List<FundOutputDto> fundOutputDtoList = new ArrayList<>();
        List<InvestmentInstitutionsFunds> investmentInstitutionsFundsList = new ArrayList<>();
        investmentInstitutionsFundsList = investmentInstitutionsFundsMapper.getFundList(projectId);

        if(null != investmentInstitutionsFundsList && investmentInstitutionsFundsList.size() != 0){
            for(InvestmentInstitutionsFunds investmentInstitutionsFunds_i: investmentInstitutionsFundsList){
                FundOutputDto fundOutputDto = new FundOutputDto();
                fundOutputDto.setFundId(investmentInstitutionsFunds_i.getId());
                fundOutputDto.setShortName(investmentInstitutionsFunds_i.getName());
                fundOutputDto.setFullName(investmentInstitutionsFunds_i.getFullName());
                fundOutputDto.setEstablishedTime(investmentInstitutionsFunds_i.getCreateTime().toString());
                fundOutputDto.setSurvivalPeriod(investmentInstitutionsFunds_i.getDuration());
                fundOutputDto.setCurrencyType(investmentInstitutionsFunds_i.getCurrency());
                fundOutputDto.setFundManageScale(investmentInstitutionsFunds_i.getScale());
                fundOutputDto.setInvestmentAmountLow(investmentInstitutionsFunds_i.getInvestmentAmountBegin());
                fundOutputDto.setInvestmentAmountHigh(investmentInstitutionsFunds_i.getInvestmentAmountEnd());

                InvestmentInstitutionsFundsStages investmentInstitutionsFundsStages = new InvestmentInstitutionsFundsStages();
                investmentInstitutionsFundsStages.setInvestmentInstitutionsFundsId(investmentInstitutionsFunds_i.getId());
                List<InvestmentInstitutionsFundsStages> investmentInstitutionsFundsStagesList = investmentInstitutionsFundsStagesService.select(investmentInstitutionsFundsStages);
                List<Integer> investmentInstitutionsFundsStagess = new ArrayList<>();
                Integer[] investmentInstitutionsFundsStagesArr = null;
                if(investmentInstitutionsFundsStagesList == null || investmentInstitutionsFundsStagesList.size() == 0){
                    fundOutputDto.setInvestStages(investmentInstitutionsFundsStagesArr);
                }else{
                    investmentInstitutionsFundsStagesList.forEach(investmentInstitutionsFundsStages_i -> {
                        investmentInstitutionsFundsStagess.add(investmentInstitutionsFundsStages_i.getStageId());
                    });
                    investmentInstitutionsFundsStagesArr = new Integer[investmentInstitutionsFundsStagesList.size()];
                    investmentInstitutionsFundsStagess.toArray(investmentInstitutionsFundsStagesArr);
                    fundOutputDto.setInvestStages(investmentInstitutionsFundsStagesArr);
                }

                InvestmentInstitutionsFundsSegmentation investmentInstitutionsFundsSegmentation = new InvestmentInstitutionsFundsSegmentation();
                investmentInstitutionsFundsSegmentation.setInvestmentInstitutionsFundsId(investmentInstitutionsFunds_i.getId());
                List<InvestmentInstitutionsFundsSegmentation> investmentInstitutionsFundsSegmentationList = investmentInstitutionsFundsSegmentationService.select(investmentInstitutionsFundsSegmentation);
                List<Integer> investmentInstitutionsFundsSegmentations = new ArrayList<>();
                Integer[] investmentInstitutionsFundsSegmentationArr = null;
                if(investmentInstitutionsFundsSegmentationList == null || investmentInstitutionsFundsSegmentationList.size() == 0){
                    fundOutputDto.setInvestStages(investmentInstitutionsFundsSegmentationArr);
                }else{
                    investmentInstitutionsFundsSegmentationList.forEach(investmentInstitutionsFundsSegmentation_i -> {
                        investmentInstitutionsFundsSegmentations.add(investmentInstitutionsFundsSegmentation_i.getSegmentationId());
                    });
                    investmentInstitutionsFundsSegmentationArr = new Integer[investmentInstitutionsFundsSegmentationList.size()];
                    investmentInstitutionsFundsSegmentations.toArray(investmentInstitutionsFundsSegmentationArr);
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
    public CommonDto<String> addOrUpdateFund(Integer projectId, FundInputDto body) {
        CommonDto<String> result = new CommonDto<>();
        if(null == body || body.toString() == ""){
            result.setMessage("success");
            result.setStatus(200);
            result.setData("请输入基金相关信息");
            return result;
        }

        InvestmentInstitutionsFunds investmentInstitutionsFunds = new InvestmentInstitutionsFunds();
        investmentInstitutionsFunds.setInvestmentInstitutionsId(projectId);
        investmentInstitutionsFunds.setYn(0);
        long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = null;
        try {
            createTime = sdf.format(new Date(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        investmentInstitutionsFunds.setCreateTime(DateUtils.parse(createTime));
        investmentInstitutionsFunds.setCreator(body.getCreator());
        investmentInstitutionsFunds.setCurrency(body.getCurrencyType());
        investmentInstitutionsFunds.setInvestmentAmountBegin(body.getInvestmentAmountLow());
        investmentInstitutionsFunds.setInvestmentAmountEnd(body.getInvestmentAmountHigh());
        investmentInstitutionsFunds.setDuration(body.getSurvivalPeriod());

        Integer fundInsertResult = -1;

        if(null == body.getFundId()){
            fundInsertResult = investmentInstitutionsFundsMapper.insert(investmentInstitutionsFunds);
        }else{
            fundInsertResult = investmentInstitutionsFundsMapper.updateByPrimaryKey(investmentInstitutionsFunds);
        }

        investmentInstitutionsFundsStagesService.deleteAll(investmentInstitutionsFunds.getId());

        Integer fundStageInsertResult = -1;
        List<InvestmentInstitutionsFundsStages> investmentInstitutionsFundsStagesList = new ArrayList<>();
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
