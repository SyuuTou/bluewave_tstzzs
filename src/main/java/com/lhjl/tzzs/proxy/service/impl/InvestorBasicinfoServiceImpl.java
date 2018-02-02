package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorBasicInfoDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorIntroductionDto;
import com.lhjl.tzzs.proxy.mapper.*;
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
 * Created by lanhaijulang on 2018/1/30.
 */
@Service
public class InvestorBasicinfoServiceImpl implements InvestorBasicinfoService{

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

    @Override
    public CommonDto<String> addOrUpdateInvestorBasicInfo(InvestorBasicInfoDto body) {
        CommonDto<String> result = new CommonDto<>();

        Investors investors = new Investors();
        investors.setId(body.getInvestorId());
        investors.setIdentityType(body.getIdentityType());
        investors.setWeichat(body.getWeiChat());
        investors.setEmail(body.getEmail());
        investors.setBirthDay(DateUtils.parse(body.getBirthDay()));
        investors.setSex(body.getSex());
        investors.setDiploma(body.getDiploma());
        investors.setNationality(body.getNationality());
        investors.setTenureTime(DateUtils.parse(body.getTenureTime()));
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
            for (Integer investorSegmentationId : body.getSegmentations()){
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
    public CommonDto<InvestorBasicInfoDto> getInvestorBasicInfo(Integer investorId) {
        CommonDto<InvestorBasicInfoDto> result = new CommonDto<>();
        InvestorBasicInfoDto investorBasicInfoDto = new InvestorBasicInfoDto();

        Investors investors = investorsMapper.selectByPrimaryKey(investorId);
        if(null == investors){
            result.setData(null);
            result.setMessage("没有该投资人");
            result.setStatus(300);
            return result;
        }
        investorBasicInfoDto.setIdentityType(investors.getIdentityType());
        investorBasicInfoDto.setWeiChat(investors.getWeichat());
        investorBasicInfoDto.setEmail(investors.getEmail());
        investorBasicInfoDto.setBirthDay(String.valueOf(investors.getBirthDay()));
        investorBasicInfoDto.setSex(investors.getSex());
        investorBasicInfoDto.setDiploma(investors.getDiploma());
        investorBasicInfoDto.setNationality(investors.getNationality());
        investorBasicInfoDto.setTenureTime(String.valueOf(investors.getTenureTime()));
        investorBasicInfoDto.setCompanyIntro(investors.getCompanyIntroduction());
        investorBasicInfoDto.setBusinessCard(investors.getBusinessCard());
        investorBasicInfoDto.setPicture(investors.getPicture());
        investorBasicInfoDto.setBussiness(investors.getBusinessDescription());
        investorBasicInfoDto.setWorkExperience(investors.getWorkDescription());
        investorBasicInfoDto.setEducationExperience(investors.getEducationDescription());
        investorBasicInfoDto.setHonor(investors.getHonor());

        InvestorSegmentation investorSegmentation = new InvestorSegmentation();
        investorSegmentation.setId(investorId);
        List<InvestorSegmentation> investorSegmentationList = investorSegmentationService.select(investorSegmentation);
        Integer[] investorSegmentationArr = null;
        if(null == investorSegmentationList){
            investorBasicInfoDto.setSegmentations(investorSegmentationArr);
        }else{
            List<Integer> investorSegmentationIds = new ArrayList<>();
            investorSegmentationList.forEach(investorSegmentation_i -> {
                investorSegmentationIds.add(investorSegmentation_i.getSegmentationId());
            });
            investorSegmentationArr = new Integer[investorSegmentationIds.size()];
            investorSegmentationIds.toArray(investorSegmentationArr);
            investorBasicInfoDto.setSegmentations(investorSegmentationArr);
        }

        InvestorCity investorCity = new InvestorCity();
        investorCity.setId(investorId);
        List<InvestorCity> investorCityList = investorCityService.select(investorCity);
        String[] investorCityArr = null;
        if(null == investorCityList){
            investorBasicInfoDto.setCitys(investorCityArr);
        }else{
            List<String> investorCitys = new ArrayList<>();
            investorCityList.forEach(investorCity_i -> {
                investorCitys.add(investorCity_i.getCity());
            });
            investorCityArr = new String[investorCitys.size()];
            investorCitys.toArray(investorCityArr);
            investorBasicInfoDto.setCitys(investorCityArr);
        }

        InvestorSelfdefCity investorSelfdefCity = new InvestorSelfdefCity();
        investorSelfdefCity.setId(investorId);
        List<InvestorSelfdefCity> investorSelfdefCityList = investorSelfdefCityService.select(investorSelfdefCity);
        String[] investorSelfdefCityArr = null;
        if(null == investorSelfdefCityList){
            investorBasicInfoDto.setCitys(investorSelfdefCityArr);
        }else{
            List<String> investorSelfdefCitys = new ArrayList<>();
            investorSelfdefCityList.forEach(investorSelfdefCity_i -> {
                investorSelfdefCitys.add(investorSelfdefCity_i.getSelfDefCity());
            });
            investorSelfdefCityArr = new String[investorSelfdefCitys.size()];
            investorSelfdefCitys.toArray(investorSelfdefCityArr);
            investorBasicInfoDto.setSelfDefCity(investorSelfdefCityArr);
        }

        InvestorBusiness investorBusiness = new InvestorBusiness();
        investorBusiness.setId(investorId);
        List<InvestorBusiness> investorBusinessesList = investorBusinessService.select(investorBusiness);
        String[] investorBusinessArr = null;
        if(null == investorBusinessesList){
            investorBasicInfoDto.setCitys(investorBusinessArr);
        }else{
            List<String> investorBusinessess = new ArrayList<>();
            investorBusinessesList.forEach(investorSelfdefCity_i -> {
                investorBusinessess.add(investorSelfdefCity_i.getBusiness());
            });
            investorBusinessArr = new String[investorBusinessess.size()];
            investorBusinessess.toArray(investorBusinessArr);
            investorBasicInfoDto.setBusinesses(investorBusinessArr);
        }

        InvestorWorkExperience investorWorkExperience = new InvestorWorkExperience();
        investorWorkExperience.setId(investorId);
        List<InvestorWorkExperience> investorWorkExperienceList = investorWorkExperienceService.select(investorWorkExperience);
        String[] investorWorkExperienceArr = null;
        if(null == investorWorkExperienceList){
            investorBasicInfoDto.setWorkExperiences(investorWorkExperienceArr);
        }else{
            List<String> investorWorkExperiences = new ArrayList<>();
            investorWorkExperienceList.forEach(investorWorkExperience_i -> {
                investorWorkExperiences.add(investorWorkExperience_i.getWorkExperience());
            });
            investorWorkExperienceArr = new String[investorWorkExperiences.size()];
            investorWorkExperiences.toArray(investorWorkExperienceArr);
            investorBasicInfoDto.setWorkExperiences(investorWorkExperienceArr);
        }

        InvestorEducationExperience investorEducationExperience = new InvestorEducationExperience();
        investorEducationExperience.setId(investorId);
        List<InvestorEducationExperience> investorEducationExperienceList = investorEducationExperienceService.select(investorEducationExperience);
        String[] investorEducationExperienceArr = null;
        if(null == investorEducationExperienceList){
            investorBasicInfoDto.setEducationExperiences(investorEducationExperienceArr);
        }else{
            List<String> investorEducationExperiences = new ArrayList<>();
            investorEducationExperienceList.forEach(investorEducationExperience_i -> {
                investorEducationExperiences.add(investorEducationExperience_i.getEducationExperience());
            });
            investorEducationExperienceArr = new String[investorEducationExperiences.size()];
            investorEducationExperiences.toArray(investorEducationExperienceArr);
            investorBasicInfoDto.setEducationExperiences(investorEducationExperienceArr);
        }

        result.setData(investorBasicInfoDto);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }

    @Override
    public CommonDto<InvestorIntroductionDto> getInvestorIntroduction(Integer investorId) {

        CommonDto<InvestorIntroductionDto> result = new CommonDto<>();
        InvestorIntroductionDto investorIntroductionDto = new InvestorIntroductionDto();

        Investors investors = investorsMapper.selectByPrimaryKey(investorId);

        if(null == investors){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }
        investorIntroductionDto.setInvestorId(investorId);
        investorIntroductionDto.setInvestorIntroduction(investors.getPersonalIntroduction());
        result.setStatus(200);
        result.setMessage("success");
        result.setData(investorIntroductionDto);

        return result;
    }

    @Override
    public CommonDto<String> addOrUpdateInvestorIntroduction(InvestorIntroductionDto body) {
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
    public CommonDto<List<MetaRegion>> getAllContry() {

        CommonDto<List<MetaRegion>> result = new CommonDto<>();
        List<MetaRegion> metaNationalityList = new ArrayList<>();

        metaNationalityList = metaRegionMapper.selectAllCountry();

        result.setStatus(200);
        result.setMessage("success");
        result.setData(metaNationalityList);
        return result;
    }

    @Override
    public CommonDto<List<MetaDiploma>> getAllDiploma() {
        CommonDto<List<MetaDiploma>> result = new CommonDto<>();
        List<MetaDiploma> metaDiplomaList = new ArrayList<>();
        metaDiplomaList = metaDiplomaMapper.selectAll();
        result.setStatus(200);
        result.setMessage("success");
        result.setData(metaDiplomaList);
        return result;
    }
}
