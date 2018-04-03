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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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
        investorDemand.setDemand(body.getPreferDesc());
        
        if(null == body.getInvestorDemandId()){
            investorDemand.setCreatTime(new Date());
            investorDemandMapper.insertSelective(investorDemand);
        }else{
            investorDemand.setUpdateTime(new Date());
            investorDemandMapper.updateByPrimaryKeySelective(investorDemand);
        }
        
        //关注领域
        List<InvestorDemandSegmentation> demandSegmentations = new ArrayList<>();
        investorDemandSegmentationService.deleteAll(investorDemand.getId());
        
        if(null != body.getFocusSegmentations() && body.getFocusSegmentations().size()!=0){
            for (String e : body.getFocusSegmentations()){
                InvestorDemandSegmentation investorDemandSegmentation = new InvestorDemandSegmentation();
                investorDemandSegmentation.setInvestorDemandId(investorDemand.getId());
                investorDemandSegmentation.setSegmentation(e);
                demandSegmentations.add(investorDemandSegmentation);
            }
        }
        investorDemandSegmentationService.insertList(demandSegmentations);
        
        //投资阶段
        List<InvestorDemandStage> investorDemandStageList = new ArrayList<>();
        investorDemandStageService.deleteAll(investorDemand.getId());
        
        if(null != body.getFocusStages()&&body.getFocusStages().size()!=0){
            for (Integer e : body.getFocusStages()){
                InvestorDemandStage investorDemandStage = new InvestorDemandStage();
                investorDemandStage.setInvestorDemandId(investorDemand.getId());
                investorDemandStage.setMetaProjectStageId(e);
                investorDemandStageList.add(investorDemandStage);
            }
        }
        investorDemandStageService.insertList(investorDemandStageList);
        //地域偏好
        List<InvestorDemandArea> investorDemandAreaList = new ArrayList<>();
        investorDemandAreaService.deleteAll(investorDemand.getId());
        
        if(null != body.getPreferCitys()&&body.getPreferCitys().size() != 0){
            for (String e : body.getPreferCitys()){
                InvestorDemandArea investorDemandArea = new InvestorDemandArea();
                investorDemandArea.setInvestorDemandId(investorDemand.getId());
                investorDemandArea.setCity(e);
                investorDemandAreaList.add(investorDemandArea);
            }
        }
        investorDemandAreaService.insertList(investorDemandAreaList);
        //最近关注细分赛道
        List<InvestorDemandSpeedway> investorDemandSpeedwayList = new ArrayList<>();
        investorDemandSpeedwayService.deleteAll(investorDemand.getId());
        
        if(null != body.getFocusSpeedway() && body.getFocusSpeedway().size()!=0){
            for (String e : body.getFocusSpeedway()){
                InvestorDemandSpeedway investorDemandSpeedway1 = new InvestorDemandSpeedway();
                investorDemandSpeedway1.setInvestorDemandId(investorDemand.getId());
                investorDemandSpeedway1.setSpeedway(e);
                investorDemandSpeedwayList.add(investorDemandSpeedway1);

            }
        }
        investorDemandSpeedwayService.insertList(investorDemandSpeedwayList);
        //关注的创始人特质
        List<InvestorDemandCharacter> investorDemandCharacterList = new ArrayList<>();
        investorDemandCharacterService.deleteAll(investorDemand.getId());
        
        if(null != body.getFocusCharacters() && body.getFocusCharacters().size() !=0){
            for (String e : body.getFocusCharacters()){
                InvestorDemandCharacter investorDemandCharacter1 = new InvestorDemandCharacter();
                investorDemandCharacter1.setInvestorDemandId(investorDemand.getId());
                investorDemandCharacter1.setCharacter(e);
                investorDemandCharacterList.add(investorDemandCharacter1);
            }
        }
        investorDemandCharacterService.insertList(investorDemandCharacterList);

        result.setMessage("success");
        result.setStatus(200);
        result.setData("保存成功");
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
            investorInvestInfoDto.setPreferDesc(investorDemand.getDemand());
            investorInvestInfoDto.setInvestAmountHighDollar(investorDemand.getInvestmentAmountHighDollars());
            investorInvestInfoDto.setInvestAmountLowDollar(investorDemand.getInvestmentAmountLowDollars());
            investorInvestInfoDto.setInvestAmountLowRmb(investorDemand.getInvestmentAmountLow());
            investorInvestInfoDto.setInvestAmountHighRmb(investorDemand.getInvestmentAmountHigh());
            //关注领域
            InvestorDemandSegmentation investorDemandSegmentation = new InvestorDemandSegmentation();
            investorDemandSegmentation.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandSegmentation> demandSegmentations = investorDemandSegmentationService.select(investorDemandSegmentation);
            
            if (null != demandSegmentations &&demandSegmentations.size()!=0){
                List<String> demandSegmentationNames = new ArrayList<>();
                demandSegmentations.forEach(e -> {
                	demandSegmentationNames.add(e.getSegmentation());
                });
                investorInvestInfoDto.setFocusSegmentations(demandSegmentationNames);
            }
            //投资阶段
            InvestorDemandStage investorDemandStage = new InvestorDemandStage();
            investorDemandStage.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandStage> investorDemandStagesList = investorDemandStageService.select(investorDemandStage);
            
            if (null != investorDemandStagesList && investorDemandStagesList.size()!=0 ){
                List<Integer> investorDemandStages = new ArrayList<>();
                investorDemandStagesList.forEach(e -> {
                    investorDemandStages.add(e.getMetaProjectStageId());
                });
                investorInvestInfoDto.setFocusStages(investorDemandStages);
            }
            //地域偏好
            InvestorDemandArea investorDemandArea = new InvestorDemandArea();
            investorDemandArea.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandArea> InvestorDemandAreaList = investorDemandAreaService.select(investorDemandArea);
            
            if (null != InvestorDemandAreaList && investorDemandStagesList.size()!=0 ){
                List<String> investorDemandCitys = new ArrayList<>();
                InvestorDemandAreaList.forEach(e -> {
                    investorDemandCitys.add(e.getCity());
                });
                investorInvestInfoDto.setPreferCitys(investorDemandCitys);
            }
            
            InvestorDemandSpeedway investorDemandSpeedway = new InvestorDemandSpeedway();
            investorDemandSpeedway.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandSpeedway> investorDemandSpeedwayList = investorDemandSpeedwayService.select(investorDemandSpeedway);
            
            if (null != investorDemandSpeedwayList && investorDemandStagesList.size()!=0 ){
                List<String> investorDemandSpeedways = new ArrayList<>();
                investorDemandSpeedwayList.forEach(e -> {
                    investorDemandSpeedways.add(e.getSpeedway());
                });
                investorInvestInfoDto.setFocusSpeedway(investorDemandSpeedways);
            }

            InvestorDemandCharacter investorDemandCharacter = new InvestorDemandCharacter();
            investorDemandCharacter.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandCharacter> investorDemandCharacterList = investorDemandCharacterService.select(investorDemandCharacter);
            
            if (null != investorDemandCharacterList && investorDemandStagesList.size()!=0){
                List<String> investorDemandCharacters = new ArrayList<>();
                investorDemandCharacterList.forEach(e -> {
                    investorDemandCharacters.add(e.getCharacter());
                });
                investorInvestInfoDto.setFocusCharacters(investorDemandCharacters);
            }
        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData(investorInvestInfoDto);
        return result;
    }
}
