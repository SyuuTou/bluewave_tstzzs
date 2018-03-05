package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditdetaildto.*;
import com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditlistdto.InvestorAuditListInputDto;
import com.lhjl.tzzs.proxy.dto.investorauditdto.investorauditlistdto.InvestorAuditListOutputDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.*;
import com.lhjl.tzzs.proxy.utils.CommonUtils;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lanhaijulang on 2018/2/7.
 */
@Service
public class InvestorAuditServiceImpl implements InvestorAuditService {

    @Autowired
    private MetaInvestmentInstitutionTeamTypeMapper metaInvestmentInstitutionTeamTypeMapper;

    @Autowired
    private InvestorsMapper investorsMapper;

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Resource
    private InvestorSegmentationService investorSegmentationService;

    @Resource
    private InvestorCityService investorCityService;

    @Resource
    private InvestorSelfdefCityService investorSelfdefCityService;

    @Resource
    private InvestorWorkExperienceService investorWorkExperienceService;

    @Resource
    private InvestorBusinessService investorBusinessService;

    @Resource
    private InvestorEducationExperienceService investorEducationExperienceService;

    @Autowired
    private MetaDiplomaMapper metaDiplomaMapper;

    @Autowired
    private MetaRegionMapper metaRegionMapper;

    @Autowired
    private MetaSegmentationMapper metaSegmentationMapper;

    @Autowired
    private MetaIdentityTypeMapper metaIdentityTypeMapper;

    @Autowired
    private InvestorDemandMapper investorDemandMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private UsersTokenLtsMapper usersTokenLtsMapper;

    @Autowired
    private UsersMapper usersMapper;

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

    @Value("${pageNum}")
    private Integer pageNumDefault ;

    @Value("${pageSize}")
    private Integer pageSizeDefault;

    @Override
    public CommonDto<String> addOrUpdateInvestorAuditDemand(InvestorAuditDemandInputDto body) {
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
            investorDemandInsertResult = investorDemandMapper.updateByPrimaryKeySelective(investorDemand);
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
            for (String investorDemandCharacter : body.getFocusCharacters()){
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
    public CommonDto<InvestorAuditDemandOutputDto> getInvestorAuditDemand(Integer investorId) {
        CommonDto<InvestorAuditDemandOutputDto> result = new CommonDto<>();
        InvestorAuditDemandOutputDto investorAuditDemandOutputDto = new InvestorAuditDemandOutputDto();
        InvestorDemand investorDemand = investorDemandMapper.selectByInvestorId(investorId);
        if(null != investorDemand) {
            investorAuditDemandOutputDto.setInvestorId(investorId);
            investorAuditDemandOutputDto.setInvestorDemandId(investorDemand.getId());
            investorAuditDemandOutputDto.setPreferDesc(investorDemand.getFuture());
            investorAuditDemandOutputDto.setInvestAmountHighDollar(investorDemand.getInvestmentAmountHighDollars());
            investorAuditDemandOutputDto.setInvestAmountLowDollar(investorDemand.getInvestmentAmountLowDollars());
            investorAuditDemandOutputDto.setInvestAmountLowRmb(investorDemand.getInvestmentAmountLow());
            investorAuditDemandOutputDto.setInvestAmountHighRmb(investorDemand.getInvestmentAmountHigh());

            InvestorDemandSegmentation investorDemandSegmentation = new InvestorDemandSegmentation();
            investorDemandSegmentation.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandSegmentation> investorDemandSegmentationList = investorDemandSegmentationService.select(investorDemandSegmentation);
            String[] investorDemandSegmentationArr = null;
            if (null == investorDemandSegmentationList) {
                investorAuditDemandOutputDto.setFocusSegmentations(investorDemandSegmentationArr);
            } else {
                List<String> investorDemandSegmentations = new ArrayList<>();
                investorDemandSegmentationList.forEach(investorDemandSegmentation_i -> {
                    investorDemandSegmentations.add(investorDemandSegmentation_i.getSegmentation());
                });
                investorDemandSegmentationArr = new String[investorDemandSegmentations.size()];
                investorDemandSegmentations.toArray(investorDemandSegmentationArr);
                investorAuditDemandOutputDto.setFocusSegmentations(investorDemandSegmentationArr);
            }

            InvestorDemandStage investorDemandStage = new InvestorDemandStage();
            investorDemandStage.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandStage> investorDemandStagesList = investorDemandStageService.select(investorDemandStage);
            Integer[] investorDemandStageArr = null;
            if (null == investorDemandStagesList) {
                investorAuditDemandOutputDto.setFocusStages(investorDemandStageArr);
            } else {
                List<Integer> investorDemandStages = new ArrayList<>();
                investorDemandStagesList.forEach(investorDemandStage_i -> {
                    investorDemandStages.add(investorDemandStage_i.getMetaProjectStageId());
                });
                investorDemandStageArr = new Integer[investorDemandStages.size()];
                investorDemandStages.toArray(investorDemandStageArr);
                investorAuditDemandOutputDto.setFocusStages(investorDemandStageArr);
            }

            InvestorDemandArea investorDemandArea = new InvestorDemandArea();
            investorDemandArea.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandArea> InvestorDemandAreaList = investorDemandAreaService.select(investorDemandArea);
            String[] investorDemandCityArr = null;
            if (null == InvestorDemandAreaList) {
                investorAuditDemandOutputDto.setPreferCitys(investorDemandCityArr);
            } else {
                List<String> investorDemandCitys = new ArrayList<>();
                InvestorDemandAreaList.forEach(investorDemandCity_i -> {
                    investorDemandCitys.add(investorDemandCity_i.getCity());
                });
                investorDemandCityArr = new String[investorDemandCitys.size()];
                investorDemandCitys.toArray(investorDemandCityArr);
                investorAuditDemandOutputDto.setPreferCitys(investorDemandCityArr);
            }

            InvestorDemandSpeedway investorDemandSpeedway = new InvestorDemandSpeedway();
            investorDemandSpeedway.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandSpeedway> investorDemandSpeedwayList = investorDemandSpeedwayService.select(investorDemandSpeedway);
            String[] investorDemandSpeedwayArr = null;
            if (null == investorDemandSpeedwayList) {
                investorAuditDemandOutputDto.setFocusSpeedway(investorDemandSpeedwayArr);
            } else {
                List<String> investorDemandSpeedways = new ArrayList<>();
                investorDemandSpeedwayList.forEach(investorDemandSpeedway_i -> {
                    investorDemandSpeedways.add(investorDemandSpeedway_i.getSpeedway());
                });
                investorDemandSpeedwayArr = new String[investorDemandSpeedways.size()];
                investorDemandSpeedways.toArray(investorDemandSpeedwayArr);
                investorAuditDemandOutputDto.setFocusSpeedway(investorDemandSpeedwayArr);
            }

            InvestorDemandCharacter investorDemandCharacter = new InvestorDemandCharacter();
            investorDemandCharacter.setInvestorDemandId(investorDemand.getId());
            List<InvestorDemandCharacter> investorDemandCharacterList = investorDemandCharacterService.select(investorDemandCharacter);
            String[] investorDemandCharacterArr = null;
            if (null == investorDemandCharacterList) {
                investorAuditDemandOutputDto.setFocusCharacters(investorDemandCharacterArr);
            } else {
                List<String> investorDemandCharacters = new ArrayList<>();
                investorDemandCharacterList.forEach(investorDemandCharacter_i -> {
                    investorDemandCharacters.add(investorDemandCharacter_i.getCharacter());
                });
                investorDemandCharacterArr = new String[investorDemandCharacters.size()];
                investorDemandCharacters.toArray(investorDemandCharacterArr);
                investorAuditDemandOutputDto.setFocusCharacters(investorDemandCharacterArr);
            }
        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData(investorAuditDemandOutputDto);
        return result;
    }

    @Override
    public CommonDto<String> addOrUpdateInvestorAuditBasicInfo(InvestorAuditBasicInfoInputDto body) {
        CommonDto<String> result = new CommonDto<>();

        Investors investors = new Investors();
        investors.setId(body.getInvestorId());
        Integer identityType = metaIdentityTypeMapper.findIdByIdentityName(body.getIdentityType());
        investors.setIdentityType(identityType);
        investors.setWeichat(body.getWeiChat());
        investors.setEmail(body.getEmail());
        investors.setBirthDay(DateUtils.parse1(body.getBirthDay()));
        investors.setSex(body.getSex());

        Integer diplomaId = metaDiplomaMapper.findDiplomaIdBydiplomaName(body.getDiploma());
        investors.setDiploma(diplomaId);

        Integer nationalityId = metaRegionMapper.findNationalityIdByCountry(body.getNationality());
        investors.setNationality(nationalityId);

        investors.setTenureTime(DateUtils.parse1(body.getTenureTime()));
        investors.setCompanyIntroduction(body.getCompanyIntro());
        investors.setBusinessCard(body.getBusinessCard());
        investors.setPicture(body.getPicture());
        investors.setBusinessDescription(body.getBussiness());
        investors.setEducationDescription(body.getEducationExperience());
        investors.setWorkDescription(body.getWorkExperience());
        investors.setHonor(body.getHonor());

        long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = null;
        try {
            createTime = sdf.format(new Date(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer investorsInsertResult = -1;
        if(body.getInvestorId() == null){
            investors.setCreateTime(DateUtils.parse(createTime));
            investorsInsertResult = investorsMapper.insert(investors);
        }else{
            investors.setUpdateTime(DateUtils.parse(createTime));
            investorsInsertResult = investorsMapper.updateByPrimaryKeySelective(investors);
        }

        //所在领域
        Integer investorSegmentationInsertResult = -1;
        List<InvestorSegmentation> investorSegmentationList = new ArrayList<>();
        investorSegmentationService.deleteAll(body.getInvestorId());
        if(null == body.getSegmentations()){
            InvestorSegmentation investorSegmentation = new InvestorSegmentation();
            investorSegmentation.setId(body.getInvestorId());
            investorSegmentation.setSegmentationId(null);
            investorSegmentationList.add(investorSegmentation);
        }else{
            List<Integer> segmentationIds = metaSegmentationMapper.findMetaSegmentationBySegmentation(body.getSegmentations());
            for (Integer investorSegmentationId : segmentationIds){
                InvestorSegmentation investorSegmentation = new InvestorSegmentation();
                investorSegmentation.setId(body.getInvestorId());
                investorSegmentation.setSegmentationId(investorSegmentationId);
                investorSegmentationList.add(investorSegmentation);
            }
        }
        investorSegmentationInsertResult = investorSegmentationService.insertList(investorSegmentationList);

        //所在城市
        Integer investorCityInsertResult = -1;
        List<InvestorCity> investorCityList = new ArrayList<>();
        investorCityService.deleteAll(body.getInvestorId());
        if(null == body.getCitys()){
            InvestorCity investorCity = new InvestorCity();
            investorCity.setId(body.getInvestorId());
            investorCity.setCity(null);
            investorCityList.add(investorCity);
        }else{
            for (String investorCityName : body.getCitys()){
                InvestorCity investorCity = new InvestorCity();
                investorCity.setId(body.getInvestorId());
                investorCity.setCity(investorCityName);
                investorCityList.add(investorCity);
            }
        }
        investorCityInsertResult = investorCityService.insertList(investorCityList);

        //自定义城市
        Integer investorSelfDefCityInsertResult = -1;
        List<InvestorSelfdefCity> investorSelfdefCityList = new ArrayList<>();
        investorSelfdefCityService.deleteAll(body.getInvestorId());
        if(null == body.getSelfDefCity()){
            InvestorSelfdefCity investorSelfdefCity = new InvestorSelfdefCity();
            investorSelfdefCity.setId(body.getInvestorId());
            investorSelfdefCity.setSelfDefCity(null);
            investorSelfdefCityList.add(investorSelfdefCity);
        }else{
            for (String investorSelfDefCityName : body.getCitys()){
                InvestorSelfdefCity investorSelfdefCity = new InvestorSelfdefCity();
                investorSelfdefCity.setId(body.getInvestorId());
                investorSelfdefCity.setSelfDefCity(investorSelfDefCityName);
                investorSelfdefCityList.add(investorSelfdefCity);
            }
        }
        investorSelfDefCityInsertResult = investorSelfdefCityService.insertList(investorSelfdefCityList);

        //工作经历
        Integer investorWorkExperienceInsertResult = -1;
        List<InvestorWorkExperience> investorWorkExperienceList = new ArrayList<>();
        investorWorkExperienceService.deleteAll(body.getInvestorId());
        if(null == body.getWorkExperiences()){
            InvestorWorkExperience investorWorkExperience = new InvestorWorkExperience();
            investorWorkExperience.setId(body.getInvestorId());
            investorWorkExperience.setWorkExperience(null);
            investorWorkExperienceList.add(investorWorkExperience);
        }else{
            for (String investorWorkExperience_i : body.getCitys()){
                InvestorWorkExperience investorWorkExperience = new InvestorWorkExperience();
                investorWorkExperience.setId(body.getInvestorId());
                investorWorkExperience.setWorkExperience(investorWorkExperience_i);
                investorWorkExperienceList.add(investorWorkExperience);
            }
        }
        investorWorkExperienceInsertResult = investorWorkExperienceService.insertList(investorWorkExperienceList);

        //教育经历
        Integer investorEducationExperienceInsertResult = -1;
        List<InvestorEducationExperience> investorEducationExperienceList = new ArrayList<>();
        investorEducationExperienceService.deleteAll(body.getInvestorId());
        if(null == body.getWorkExperiences()){
            InvestorEducationExperience investorEducationExperience = new InvestorEducationExperience();
            investorEducationExperience.setId(body.getInvestorId());
            investorEducationExperience.setEducationExperience(null);
            investorEducationExperienceList.add(investorEducationExperience);
        }else{
            for (String investorEducationExperience_i : body.getCitys()){
                InvestorEducationExperience investorEducationExperience = new InvestorEducationExperience();
                investorEducationExperience.setId(body.getInvestorId());
                investorEducationExperience.setEducationExperience(investorEducationExperience_i);
                investorEducationExperienceList.add(investorEducationExperience);
            }
        }
        investorEducationExperienceInsertResult = investorEducationExperienceService.insertList(investorEducationExperienceList);

        //创业经历
        Integer investorBusinessesInsertResult = -1;
        List<InvestorBusiness> investorBusinessList = new ArrayList<>();
        investorBusinessService.deleteAll(body.getInvestorId());
        if(null == body.getWorkExperiences()){
            InvestorBusiness investorBusiness = new InvestorBusiness();
            investorBusiness.setId(body.getInvestorId());
            investorBusiness.setBusiness(null);
            investorBusinessList.add(investorBusiness);
        }else{
            for (String investorBusiness_i : body.getCitys()){
                InvestorBusiness investorBusiness = new InvestorBusiness();
                investorBusiness.setId(body.getInvestorId());
                investorBusiness.setBusiness(investorBusiness_i);
                investorBusinessList.add(investorBusiness);
            }
        }
        investorBusinessesInsertResult = investorBusinessService.insertList(investorBusinessList);

        if(investorsInsertResult > 0 && investorBusinessesInsertResult > 0 &&
                investorEducationExperienceInsertResult > 0 && investorWorkExperienceInsertResult > 0 &&
                investorSelfDefCityInsertResult > 0 &&  investorCityInsertResult > 0 &&
                investorSegmentationInsertResult > 0){
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
    public CommonDto<InvestorAuditBasicInfoOutputDto> getInvestorAuditBasicInfo(Integer investorId) {
        CommonDto<InvestorAuditBasicInfoOutputDto> result = new CommonDto<>();
        InvestorAuditBasicInfoOutputDto investorAuditBasicInfoOutputDto = new InvestorAuditBasicInfoOutputDto();

        Investors investors = investorsMapper.selectByPrimaryKey(investorId);
        if(null == investors){
            result.setData(null);
            result.setMessage("没有该投资人");
            result.setStatus(300);
            return result;
        }
        investorAuditBasicInfoOutputDto.setInvestorId(investors.getId());
        String identityName = metaIdentityTypeMapper.findIdentityNameById(investors.getIdentityType());
        investorAuditBasicInfoOutputDto.setIdentityType(identityName);
        investorAuditBasicInfoOutputDto.setWeiChat(investors.getWeichat());
        investorAuditBasicInfoOutputDto.setEmail(investors.getEmail());
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        if(null == investors.getBirthDay() || "undefined".equals(investors.getBirthDay())){
            investorAuditBasicInfoOutputDto.setBirthDay("");
        }else{
            investorAuditBasicInfoOutputDto.setBirthDay(sdf.format(investors.getBirthDay()));
        }
        investorAuditBasicInfoOutputDto.setSex(investors.getSex());
        String diploma = metaDiplomaMapper.selectByDiplomaId(investors.getDiploma());
        investorAuditBasicInfoOutputDto.setDiploma(diploma);
        String countryName = metaRegionMapper.selectByRegionId(investors.getNationality());
        investorAuditBasicInfoOutputDto.setNationality(countryName);
        investorAuditBasicInfoOutputDto.setBusinessCardOposite(investors.getBusinessCardOpposite());
        if(null == investors.getTenureTime() || "undefined".equals(investors.getTenureTime())){
            investorAuditBasicInfoOutputDto.setTenureTime("");
        }else{
            investorAuditBasicInfoOutputDto.setTenureTime(sdf.format(investors.getTenureTime()));
        }
        investorAuditBasicInfoOutputDto.setCompanyIntro(investors.getCompanyIntroduction());
        System.out.println(investors.getBusinessCard());
        investorAuditBasicInfoOutputDto.setBusinessCard(investors.getBusinessCard());
        investorAuditBasicInfoOutputDto.setPicture(investors.getPicture());
        investorAuditBasicInfoOutputDto.setBussiness(investors.getBusinessDescription());
        investorAuditBasicInfoOutputDto.setWorkExperience(investors.getWorkDescription());
        investorAuditBasicInfoOutputDto.setEducationExperience(investors.getEducationDescription());
        investorAuditBasicInfoOutputDto.setHonor(investors.getHonor());

        InvestorSegmentation investorSegmentation = new InvestorSegmentation();
        investorSegmentation.setId(investorId);
        List<InvestorSegmentation> investorSegmentationList = investorSegmentationService.select(investorSegmentation);
        String[] investorSegmentationArr = null;
        if(null == investorSegmentationList || investorSegmentationList.size() == 0){
            investorAuditBasicInfoOutputDto.setSegmentations(investorSegmentationArr);
        }else{
            List<Integer> investorSegmentationIds = new ArrayList<>();
            investorSegmentationList.forEach(investorSegmentation_i -> {
                investorSegmentationIds.add(investorSegmentation_i.getSegmentationId());
            });
            Integer[] investorSegmentationIdArr = new Integer[investorSegmentationIds.size()];
            investorSegmentationIds.toArray(investorSegmentationIdArr);
            List<MetaSegmentation> metaSegmentationList = metaSegmentationMapper.selectBySegmentationIds(investorSegmentationIdArr);
            List<String> segmentations = new ArrayList<>();
            metaSegmentationList.forEach( metaSegmentation -> {
                segmentations.add(metaSegmentation.getName());
            });
            investorSegmentationArr = new String[segmentations.size()];
            segmentations.toArray(investorSegmentationArr);
            investorAuditBasicInfoOutputDto.setSegmentations(investorSegmentationArr);
        }

        InvestorCity investorCity = new InvestorCity();
        investorCity.setId(investorId);
        List<InvestorCity> investorCityList = investorCityService.select(investorCity);
        String[] investorCityArr = null;
        if(null == investorCityList || investorCityList.size()==0){
            investorAuditBasicInfoOutputDto.setCitys(investorCityArr);
        }else{
            List<String> investorCitys = new ArrayList<>();
            investorCityList.forEach(investorCity_i -> {
                investorCitys.add(investorCity_i.getCity());
            });
            investorCityArr = new String[investorCitys.size()];
            investorCitys.toArray(investorCityArr);
            investorAuditBasicInfoOutputDto.setCitys(investorCityArr);
        }

        InvestorSelfdefCity investorSelfdefCity = new InvestorSelfdefCity();
        investorSelfdefCity.setId(investorId);
        List<InvestorSelfdefCity> investorSelfdefCityList = investorSelfdefCityService.select(investorSelfdefCity);
        String[] investorSelfdefCityArr = null;
        if(null == investorSelfdefCityList || investorSelfdefCityList.size() == 0){
            investorAuditBasicInfoOutputDto.setCitys(investorSelfdefCityArr);
        }else{
            List<String> investorSelfdefCitys = new ArrayList<>();
            investorSelfdefCityList.forEach(investorSelfdefCity_i -> {
                investorSelfdefCitys.add(investorSelfdefCity_i.getSelfDefCity());
            });
            investorSelfdefCityArr = new String[investorSelfdefCitys.size()];
            investorSelfdefCitys.toArray(investorSelfdefCityArr);
            investorAuditBasicInfoOutputDto.setSelfDefCity(investorSelfdefCityArr);
        }

        InvestorBusiness investorBusiness = new InvestorBusiness();
        investorBusiness.setId(investorId);
        List<InvestorBusiness> investorBusinessesList = investorBusinessService.select(investorBusiness);
        String[] investorBusinessArr = null;
        if(null == investorBusinessesList || investorBusinessesList.size() == 0){
            investorAuditBasicInfoOutputDto.setCitys(investorBusinessArr);
        }else{
            List<String> investorBusinessess = new ArrayList<>();
            investorBusinessesList.forEach(investorSelfdefCity_i -> {
                investorBusinessess.add(investorSelfdefCity_i.getBusiness());
            });
            investorBusinessArr = new String[investorBusinessess.size()];
            investorBusinessess.toArray(investorBusinessArr);
            investorAuditBasicInfoOutputDto.setBusinesses(investorBusinessArr);
        }

        InvestorWorkExperience investorWorkExperience = new InvestorWorkExperience();
        investorWorkExperience.setId(investorId);
        List<InvestorWorkExperience> investorWorkExperienceList = investorWorkExperienceService.select(investorWorkExperience);
        String[] investorWorkExperienceArr = null;
        if(null == investorWorkExperienceList || investorWorkExperienceList.size() == 0){
            investorAuditBasicInfoOutputDto.setWorkExperiences(investorWorkExperienceArr);
        }else{
            List<String> investorWorkExperiences = new ArrayList<>();
            investorWorkExperienceList.forEach(investorWorkExperience_i -> {
                investorWorkExperiences.add(investorWorkExperience_i.getWorkExperience());
            });
            investorWorkExperienceArr = new String[investorWorkExperiences.size()];
            investorWorkExperiences.toArray(investorWorkExperienceArr);
            investorAuditBasicInfoOutputDto.setWorkExperiences(investorWorkExperienceArr);
        }

        InvestorEducationExperience investorEducationExperience = new InvestorEducationExperience();
        investorEducationExperience.setId(investorId);
        List<InvestorEducationExperience> investorEducationExperienceList = investorEducationExperienceService.select(investorEducationExperience);
        String[] investorEducationExperienceArr = null;
        if(null == investorEducationExperienceList || investorEducationExperienceList.size() == 0){
            investorAuditBasicInfoOutputDto.setEducationExperiences(investorEducationExperienceArr);
        }else{
            List<String> investorEducationExperiences = new ArrayList<>();
            investorEducationExperienceList.forEach(investorEducationExperience_i -> {
                investorEducationExperiences.add(investorEducationExperience_i.getEducationExperience());
            });
            investorEducationExperienceArr = new String[investorEducationExperiences.size()];
            investorEducationExperiences.toArray(investorEducationExperienceArr);
            investorAuditBasicInfoOutputDto.setEducationExperiences(investorEducationExperienceArr);
        }

        result.setData(investorAuditBasicInfoOutputDto);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }

    @Override
    public CommonDto<InvestorAuditIntroductionOutputDto> getInvestorAuditIntroduction(Integer investorId) {
        CommonDto<InvestorAuditIntroductionOutputDto> result = new CommonDto<>();
        InvestorAuditIntroductionOutputDto investorAuditIntroductionOutputDto = new InvestorAuditIntroductionOutputDto();

        Investors investors = investorsMapper.selectByPrimaryKey(investorId);

        if(null == investors){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }
        investorAuditIntroductionOutputDto.setInvestorId(investorId);
        investorAuditIntroductionOutputDto.setInvestorIntroduction(investors.getPersonalIntroduction());
        result.setStatus(200);
        result.setMessage("success");
        result.setData(investorAuditIntroductionOutputDto);

        return result;
    }

    @Override
    public CommonDto<String> addOrUpdateInvestorAuditIntroduction(InvestorAuditIntroductionInputDto body) {
        CommonDto<String> result = new CommonDto<>();
        Investors investors = investorsMapper.selectByPrimaryKey(body.getInvestorId());
        if(null == investors){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData("没有该投资人");
        }
        investors.setPersonalIntroduction(body.getInvestorIntroduction());
        Integer investorIntroductionResult = investorsMapper.updateByPrimaryKeySelective(investors);

        if(investorIntroductionResult > 0){
            result.setStatus(200);
            result.setMessage("success");
            result.setData("保存成功");
            return result;
        }
        result.setStatus(500);
        result.setMessage("服务器端错误");
        result.setData("保存失败");
        return result;
    }

    @Override
    public CommonDto<String> addOrUpdateInvestorAuditInfo(InvestorAuditInfoInputDto body) {
        CommonDto<String> result = new CommonDto<>();
        Boolean flag = false;
        try {
            flag = CommonUtils.isAllFieldNull(body);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(null == body || flag == true){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData("请输入新信息");
            return result;
        }

        Investors investors = new Investors();
        investors.setId(body.getInvestorId());
        investors.setName(body.getName());
        investors.setUserId(body.getUserId());
        Integer investmentInstitutionsId = null;
        if(null != body.getCompanyName() && body.getCompanyName() != ""){
            investmentInstitutionsId = investmentInstitutionsMapper.selectByCompanyName(body.getCompanyName());
            if(null == investmentInstitutionsId){
                InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
                investmentInstitutions.setShortName(body.getCompanyName());
                investmentInstitutionsMapper.insert(investmentInstitutions);
            }
            investmentInstitutionsId = investmentInstitutionsMapper.selectByCompanyName(body.getCompanyName());
        }
        investors.setInvestmentInstitutionsId(investmentInstitutionsId);
        investors.setPosition(body.getCompanyDuties());
        investors.setHeadPicture(body.getHeadPicture());

        Integer teamId = metaInvestmentInstitutionTeamTypeMapper.findTeamIdByName(body.getTeamName());
        investors.setTeamId(teamId);
        investors.setSelfDefTeam(body.getSelfDefTeam());
        investors.setPhone(body.getPhone());
        investors.setKernelDescription(body.getKernelDesc());
        Integer investorInsertResult = -1;
        if(null == body.getInvestorId()){
            investorInsertResult = investorsMapper.insert(investors);
        }else{
            investorInsertResult = investorsMapper.updateByPrimaryKeySelective(investors);
        }

        if(investorInsertResult > 0){
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
    public CommonDto<InvestorAuditInfoOutputDto> getInvestorAuditInfo(Integer investorId) {
        CommonDto<InvestorAuditInfoOutputDto> result = new CommonDto<>();
        InvestorAuditInfoOutputDto investorAuditInfoOutputDto = new InvestorAuditInfoOutputDto();
        Investors investors = investorsMapper.selectByPrimaryKey(investorId);
        if(null == investors){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }

        investorAuditInfoOutputDto.setName(investors.getName());
        String companyName = investmentInstitutionsMapper.selectById(investors.getInvestmentInstitutionsId());
        investorAuditInfoOutputDto.setCompanyName(companyName);
        investorAuditInfoOutputDto.setCompanyDuties(investors.getPosition());
        investorAuditInfoOutputDto.setHeadPicture(investors.getHeadPicture());
        String teamName = metaInvestmentInstitutionTeamTypeMapper.findTeamNameById(investors.getTeamId());
        investorAuditInfoOutputDto.setTeamName(teamName);
        investorAuditInfoOutputDto.setSelfDefTeam(investors.getSelfDefTeam());
        investorAuditInfoOutputDto.setPhone(investors.getPhone());
        investorAuditInfoOutputDto.setKernelDesc(investors.getKernelDescription());
        investorAuditInfoOutputDto.setUserId(investors.getUserId());

        result.setStatus(200);
        result.setMessage("success");
        result.setData(investorAuditInfoOutputDto);
        return result;
    }

    @Override
    public CommonDto<Map<String, Object>> getMatchInvestor(String investorName) {
        CommonDto<Map<String, Object>> result = new CommonDto<>();
        Map<String, Object> map = new HashMap<>();

        InvestorAuditInfoOutputDto investorAuditInfoOutputDto = new InvestorAuditInfoOutputDto();
        InvestorAuditBasicInfoOutputDto investorAuditBasicInfoOutputDto= new InvestorAuditBasicInfoOutputDto();
        InvestorAuditIntroductionOutputDto investorAuditIntroductionOutputDto = new InvestorAuditIntroductionOutputDto();
        InvestorAuditDemandOutputDto investorAuditDemandOutputDto = new InvestorAuditDemandOutputDto();

        String memberType = null;
        Boolean isInBlackList = false;

        map.put("匹配到的投资人核心信息",investorAuditInfoOutputDto);
        map.put("匹配到的投资人基本信息",investorAuditBasicInfoOutputDto);
        map.put("匹配到的投资人简介信息",investorAuditIntroductionOutputDto);
        map.put("匹配到的投资人投资偏好信息",investorAuditDemandOutputDto);
        map.put("会员类型", memberType);
        map.put("黑名单", false);
        result.setStatus(200);
        result.setMessage("success");
        result.setData(map);
        return null;
    }


//    @Override
//    public CommonDto<Map<String, Object>> listAuditInvestorsInfos(Integer appid, InvestorAuditListInputDto body) {
//
//        if (StringUtil.isEmpty(body.getToken())){
//            return new CommonDto<>(null, "Token 不能为空。", 200);
//        }
//
//        UserToken query = new UserToken();
//        query.setToken(body.getToken());
//
//        UserToken userToken = userTokenMapper.selectOne(query);
//
//        Users users = usersMapper.selectByPrimaryKey(userToken.getUserId());
//
//        CommonDto<Map<String, Object>> result = new CommonDto<>();
//
//        Map<String, Object> map = new HashMap<>();
//
//        List<InvestorAuditListOutputDto> investorAuditListOutputDtoList = new ArrayList<>();
//
//        //验证、格式化参数信息
//        //默认设置为10条记录
//        if (body.getPageSize() == null){
//            body.setPageSize(pageSizeDefault);
//        }
//        //默认设置为第一页
//        if (body.getCurrentPage() == null){
//            body.setCurrentPage(pageNumDefault);
//        }
//        //设置开始索引
//        body.setStart((long) ((body.getCurrentPage()-1) * body.getPageSize()));
//
//        AdminUser au=new AdminUser();
//        au.setMetaAppId(appid);
//        au.setUserId(userToken.getUserId());
//        //设置管理员的管理员类型
//        try {
//            au = adminUserMapper.selectOne(au);
//            if(au != null) {
//                //设置管理员的类型
//                body.setAdminType(au.getAdminType());
//                //设置当前的（管理员）负责人的名称
//                if (StringUtil.isNotEmpty(users.getActualName())) {
//                    body.setAdminName(users.getActualName());
//                }
//            }
//
//        }catch(Exception e) {
//            result.setData(null);
//            result.setStatus(500);
//            result.setMessage("数据库数据存在问题，相同的用户id存在两条数据");
//            return result;
//        }
//        //输入时间字符串转换为日期
//        try{
//            if(body.getStartTimeStr() !=null) {
//                body.setStartTime(sdf.parse(body.getStartTimeStr()));
//            }
//            if(body.getEndTimeStr() !=null) {
//                body.setEndTime(sdf.parse(body.getEndTimeStr()));
//            }
//        }catch(Exception e){
//            result.setData(null);
//            result.setStatus(200);
//            result.setMessage("日期字符串输入格式不正确");
//            return result;
//        }
//        List<InvestorsOutputDto> list = investorsMapper.listInvestorsInfos(body);
//
//        //日期转换为输出时间字符串
//        list.forEach((e)->{
//            if(e.getUpdateTime() !=null) {
//                e.setUpdateTimeStr(sdf.format(e.getUpdateTime()));
//            }
//            if(e.getCreateTime() !=null) {
//                e.setCreateTimeStr(sdf.format(e.getCreateTime()));
//            }
//        });
//        Long total = investorsMapper.getInvestorsListCount(body);
//
//
//
//        result.setStatus(200);
//        result.setMessage("success");
//        result.setData(map);
//        return result;
//    }
}
