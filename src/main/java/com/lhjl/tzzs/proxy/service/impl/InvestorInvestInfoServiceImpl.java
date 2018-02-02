package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorInvestInfoDto;
import com.lhjl.tzzs.proxy.mapper.InvestorDemandMapper;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.*;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
@Service
public class InvestorInvestInfoServiceImpl implements InvestorInvestInfoService {

    @Autowired
    private InvestorDemandMapper investorDemandMapper;

    @Resource
    private InvestorDemandCharacterService investorDemandCharacterService;

    @Resource
    private InvestorDemandSpeedwayService investorDemandSpeedwayService;

    @Resource
    private InvestorDemandStageService investorDemandStageService;

    @Resource
    private InvestorDemandSegmentationService investorDemandSegmentationService;

    @Resource
    private InvestorDemandAreaService investorDemandAreaService;

    @Override
    public CommonDto<String> addOrUpdateInvestorInvestInfo(InvestorInvestInfoDto body) {
        CommonDto<String> result = new CommonDto<>();
        if(null == body.getInvestorId()){
            result.setMessage("failed");
            result.setStatus(300);
            result.setData("没有该投资人");
            return result;
        }

        InvestorDemand investorDemand = new InvestorDemand();
        investorDemand.setId(body.getInvestorDemandId());
        investorDemand.setInvestorId(body.getInvestorId());
        investorDemand.setInvestmentAmountLow(body.getInvestAmountLowRmb());
        investorDemand.setInvestmentAmountHigh(body.getInvestAmountHighRmb());
        investorDemand.setInvestmentAmountLowDollars(body.getInvestAmountLowDollar());
        investorDemand.setInvestmentAmountHighDollars(body.getInvestAmountHighDollar());
        investorDemand.setFuture(body.getPreferDesc());
        long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = null;
        try {
            createTime = sdf.format(new Date(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer investorDemandInsertResult = -1;
        if(null == body.getInvestorDemandId()){
            investorDemand.setCreatTime(DateUtils.parse(createTime));
            investorDemandInsertResult = investorDemandMapper.insert(investorDemand);
        }else{
            investorDemand.setUpdateTime(DateUtils.parse(createTime));
            investorDemandInsertResult = investorDemandMapper.insert(investorDemand);
        }

        Integer investorDemandSegmentationInsertResult = -1;
        List<InvestorDemandSegmentation> investorDemandSegmentationList = new ArrayList<>();
        investorDemandSegmentationService.deleteAll(investorDemand.getId());
        if(null == body.getFocusSegmentations()){
            InvestorDemandSegmentation investorDemandSegmentation = new InvestorDemandSegmentation();
            investorDemandSegmentation.setInvestorDemandId(investorDemand.getId());
            investorDemandSegmentation.setSegmentation(null);
            investorDemandSegmentationList.add(investorDemandSegmentation);
        }else{
            for (String investorDemandSegmentation : body.getFocusSegmentations()){
                InvestorDemandSegmentation investorDemandSegmentation1 = new InvestorDemandSegmentation();
                investorDemandSegmentation1.setInvestorDemandId(investorDemand.getId());
                investorDemandSegmentation1.setSegmentation(investorDemandSegmentation);
                investorDemandSegmentationList.add(investorDemandSegmentation1);
            }
        }
        investorDemandSegmentationInsertResult = investorDemandSegmentationService.insertList(investorDemandSegmentationList);

        Integer investorDemandStageInsertResult = -1;
        List<InvestorDemandStage> investorDemandStageList = new ArrayList<>();
        investorDemandStageService.deleteAll(investorDemand.getId());
        if(null == body.getFocusStages()){
            InvestorDemandStage investorDemandStage = new InvestorDemandStage();
            investorDemandStage.setInvestorDemandId(investorDemand.getId());
            investorDemandStage.setMetaProjectStageId(null);
            investorDemandStageList.add(investorDemandStage);
        }else{
            for (Integer investorDemandStageId : body.getFocusStages()){
                InvestorDemandStage investorDemandStage = new InvestorDemandStage();
                investorDemandStage.setInvestorDemandId(investorDemand.getId());
                investorDemandStage.setMetaProjectStageId(investorDemandStageId);
                investorDemandStageList.add(investorDemandStage);
            }
        }
        investorDemandStageInsertResult = investorDemandStageService.insertList(investorDemandStageList);

        Integer investorDemandAreaInsertResult = -1;
        List<InvestorDemandArea> investorDemandAreaList = new ArrayList<>();
        investorDemandAreaService.deleteAll(investorDemand.getId());
        if(null == body.getPreferCitys()){
            InvestorDemandArea investorDemandArea = new InvestorDemandArea();
            investorDemandArea.setInvestorDemandId(investorDemand.getId());
            investorDemandArea.setCity(null);
            investorDemandAreaList.add(investorDemandArea);
        }else{
            for (String investorDemandArea : body.getPreferCitys()){
                InvestorDemandArea investorDemandArea1 = new InvestorDemandArea();
                investorDemandArea1.setInvestorDemandId(investorDemand.getId());
                investorDemandArea1.setCity(investorDemandArea);
                investorDemandAreaList.add(investorDemandArea1);
            }
        }
        investorDemandAreaInsertResult = investorDemandAreaService.insertList(investorDemandAreaList);


        Integer investorDemandSpeedwayInsertResult = -1;
        List<InvestorDemandSpeedway> investorDemandSpeedwayList = new ArrayList<>();
        investorDemandSpeedwayService.deleteAll(investorDemand.getId());
        if(null == body.getFocusSpeedway()){
            InvestorDemandSpeedway investorDemandSpeedway = new InvestorDemandSpeedway();
            investorDemandSpeedway.setInvestorDemandId(investorDemand.getId());
            investorDemandSpeedway.setSpeedway(null);
            investorDemandSpeedwayList.add(investorDemandSpeedway);
        }else{
            for (String investorDemandSpeedway : body.getFocusSpeedway()){
                InvestorDemandSpeedway investorDemandSpeedway1 = new InvestorDemandSpeedway();
                investorDemandSpeedway1.setInvestorDemandId(investorDemand.getId());
                investorDemandSpeedway1.setSpeedway(investorDemandSpeedway);
                investorDemandSpeedwayList.add(investorDemandSpeedway1);

            }
        }
        investorDemandSpeedwayInsertResult = investorDemandSpeedwayService.insertList(investorDemandSpeedwayList);

        Integer investorDemandCharacterInsertResult = -1;
        List<InvestorDemandCharacter> investorDemandCharacterList = new ArrayList<>();
        investorDemandCharacterService.deleteAll(investorDemand.getId());
        if(null == body.getFocusCharacters()){
            InvestorDemandCharacter investorDemandCharacter = new InvestorDemandCharacter();
            investorDemandCharacter.setInvestorDemandId(investorDemand.getId());
            investorDemandCharacter.setCharacter(null);
            investorDemandCharacterList.add(investorDemandCharacter);
        }else{
            for (String investorDemandCharacter : body.getPreferCitys()){
                InvestorDemandCharacter investorDemandCharacter1 = new InvestorDemandCharacter();
                investorDemandCharacter1.setInvestorDemandId(investorDemand.getId());
                investorDemandCharacter1.setCharacter(investorDemandCharacter);
                investorDemandCharacterList.add(investorDemandCharacter1);
            }
        }
        investorDemandCharacterInsertResult = investorDemandCharacterService.insertList(investorDemandCharacterList);

        if(investorDemandInsertResult > 0 && investorDemandCharacterInsertResult > 0 &&
                investorDemandSpeedwayInsertResult > 0 && investorDemandAreaInsertResult > 0 &&
                investorDemandStageInsertResult > 0 && investorDemandSegmentationInsertResult > 0){
            result.setMessage("success");
            result.setStatus(200);
            result.setData("保存成功");
            return result;
        }

        result.setMessage("failed");
        result.setStatus(300);
        result.setData("保存失败");
        return result;
    }

    @Override
    public CommonDto<InvestorInvestInfoDto> getInvestorInvestInfo(Integer investorId) {
        CommonDto<InvestorInvestInfoDto> result = new CommonDto<>();
        InvestorInvestInfoDto investorInvestInfoDto = new InvestorInvestInfoDto();
        InvestorDemand investorDemand = investorDemandMapper.selectByInvestorId(investorId);
        if(null != investorDemand) {
            investorInvestInfoDto.setInvestorId(investorId);
            investorInvestInfoDto.setInvestorDemandId(investorDemand.getId());
            investorInvestInfoDto.setPreferDesc(investorDemand.getFuture());
            investorInvestInfoDto.setInvestAmountHighDollar(investorDemand.getInvestmentAmountHighDollars());
            investorInvestInfoDto.setInvestAmountLowDollar(investorDemand.getInvestmentAmountLowDollars());
            investorInvestInfoDto.setInvestAmountLowRmb(investorDemand.getInvestmentAmountLow());
            investorInvestInfoDto.setInvestAmountHighRmb(investorDemand.getInvestmentAmountHigh());

            InvestorDemandSegmentation investorDemandSegmentation = new InvestorDemandSegmentation();
            investorDemandSegmentation.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandSegmentation> investorDemandSegmentationList = investorDemandSegmentationService.select(investorDemandSegmentation);
            String[] investorDemandSegmentationArr = null;
            if (null == investorDemandSegmentationList) {
                investorInvestInfoDto.setFocusSegmentations(investorDemandSegmentationArr);
            } else {
                List<String> investorDemandSegmentations = new ArrayList<>();
                investorDemandSegmentationList.forEach(investorDemandSegmentation_i -> {
                    investorDemandSegmentations.add(investorDemandSegmentation_i.getSegmentation());
                });
                investorDemandSegmentationArr = new String[investorDemandSegmentations.size()];
                investorDemandSegmentations.toArray(investorDemandSegmentationArr);
                investorInvestInfoDto.setFocusSegmentations(investorDemandSegmentationArr);
            }

            InvestorDemandStage investorDemandStage = new InvestorDemandStage();
            investorDemandStage.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandStage> investorDemandStagesList = investorDemandStageService.select(investorDemandStage);
            Integer[] investorDemandStageArr = null;
            if (null == investorDemandStagesList) {
                investorInvestInfoDto.setFocusStages(investorDemandStageArr);
            } else {
                List<Integer> investorDemandStages = new ArrayList<>();
                investorDemandStagesList.forEach(investorDemandStage_i -> {
                    investorDemandStages.add(investorDemandStage_i.getMetaProjectStageId());
                });
                investorDemandStageArr = new Integer[investorDemandStages.size()];
                investorDemandStages.toArray(investorDemandStageArr);
                investorInvestInfoDto.setFocusStages(investorDemandStageArr);
            }

            InvestorDemandArea investorDemandArea = new InvestorDemandArea();
            investorDemandArea.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandArea> InvestorDemandAreaList = investorDemandAreaService.select(investorDemandArea);
            String[] investorDemandCityArr = null;
            if (null == InvestorDemandAreaList) {
                investorInvestInfoDto.setPreferCitys(investorDemandCityArr);
            } else {
                List<String> investorDemandCitys = new ArrayList<>();
                InvestorDemandAreaList.forEach(investorDemandCity_i -> {
                    investorDemandCitys.add(investorDemandCity_i.getCity());
                });
                investorDemandCityArr = new String[investorDemandCitys.size()];
                investorDemandCitys.toArray(investorDemandCityArr);
                investorInvestInfoDto.setPreferCitys(investorDemandCityArr);
            }

            InvestorDemandSpeedway investorDemandSpeedway = new InvestorDemandSpeedway();
            investorDemandSpeedway.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandSpeedway> investorDemandSpeedwayList = investorDemandSpeedwayService.select(investorDemandSpeedway);
            String[] investorDemandSpeedwayArr = null;
            if (null == investorDemandSpeedwayList) {
                investorInvestInfoDto.setFocusSpeedway(investorDemandSpeedwayArr);
            } else {
                List<String> investorDemandSpeedways = new ArrayList<>();
                investorDemandSpeedwayList.forEach(investorDemandSpeedway_i -> {
                    investorDemandSpeedways.add(investorDemandSpeedway_i.getSpeedway());
                });
                investorDemandSpeedwayArr = new String[investorDemandSpeedways.size()];
                investorDemandSpeedways.toArray(investorDemandSegmentationArr);
                investorInvestInfoDto.setFocusSpeedway(investorDemandSpeedwayArr);
            }

            InvestorDemandCharacter investorDemandCharacter = new InvestorDemandCharacter();
            investorDemandCharacter.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandCharacter> investorDemandCharacterList = investorDemandCharacterService.select(investorDemandCharacter);
            String[] investorDemandCharacterArr = null;
            if (null == investorDemandCharacterList) {
                investorInvestInfoDto.setFocusCharacters(investorDemandCharacterArr);
            } else {
                List<String> investorDemandCharacters = new ArrayList<>();
                investorDemandCharacterList.forEach(investorDemandCharacter_i -> {
                    investorDemandCharacters.add(investorDemandCharacter_i.getCharacter());
                });
                investorDemandCharacterArr = new String[investorDemandCharacters.size()];
                investorDemandCharacters.toArray(investorDemandSegmentationArr);
                investorInvestInfoDto.setFocusSpeedway(investorDemandCharacterArr);
            }
        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData(investorInvestInfoDto);
        return result;
    }
}
