package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorBasicInfoInputDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorBasicInfoOutputDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorIntroductionDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.*;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/1/30.
 */
@Service
public class InvestorBasicinfoServiceImpl extends GenericService implements InvestorBasicinfoService{

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
    private InvestorCityMapper investorCityMapper;
    @Autowired
    private InvestorWorkExperienceMapper investorWorkExperienceMapper;
    @Autowired
    private InvestorEducationExperienceMapper investorEducationExperienceMapper;
    @Autowired
    private InvestorBusinessMapper investorBusinessMapper;
    @Autowired
    private InvestorSegmentationMapper investorSegmentationMapper;
    @Autowired
    private InvestorSelfdefCityMapper investorSelfdefCityMapper;
    
    @Transactional
    @Override
    public CommonDto<Boolean> addOrUpdateInvestorBasicInfo(InvestorBasicInfoInputDto body) {
        CommonDto<Boolean> result = new CommonDto<>();
        this.LOGGER.info("body**"+body);
        
        Investors investor = new Investors();
        investor.setId(body.getInvestorId());
        
        Integer identityType = metaIdentityTypeMapper.findIdByIdentityName(body.getIdentityType());
        investor.setIdentityType(identityType);
        investor.setWeichat(body.getWeiChat());
        investor.setEmail(body.getEmail());
        if(body.getBirthDay()!=null) {
        	investor.setBirthDay(DateUtils.parse1(body.getBirthDay()));
        }
        investor.setSex(body.getSex());
        Integer diplomaId = metaDiplomaMapper.findDiplomaIdBydiplomaName(body.getDiploma());
        investor.setDiploma(diplomaId);
        Integer nationalityId = metaRegionMapper.findNationalityIdByCountry(body.getNationality());
        investor.setNationality(nationalityId);
        if(body.getTenureTime()!=null) {
        	investor.setTenureTime(DateUtils.parse1(body.getTenureTime()));
        }
        investor.setCompanyIntroduction(body.getCompanyIntro());
        //设置工作名片正面
        investor.setBusinessCard(body.getBusinessCard());
        //设置工作名片反面
        investor.setBusinessCardOpposite(body.getBusinessCardOpposite());
        //设置高清图片
        investor.setPicture(body.getPicture());
        //设置创业经历描述
        investor.setBusinessDescription(body.getBussiness());
        //设置教育经历描述
        investor.setEducationDescription(body.getEducationExperience());
        //设置工作经历描述
        investor.setWorkDescription(body.getWorkExperience());
        investor.setHonor(body.getHonor());
        
        if(body.getInvestorId() == null){
            investor.setCreateTime(new Date());
            investorsMapper.insert(investor);
        }else{
            investor.setUpdateTime(new Date());
            investorsMapper.updateByPrimaryKeySelective(investor);
        }
        
        //所在领域-zd
        List<InvestorSegmentation> investorSegmentationList = new ArrayList<>();
        investorSegmentationService.deleteAll(investor.getId());
        
        if(null != body.getSegmentations() && body.getSegmentations().length != 0){
        	this.LOGGER.info("body.getSegmentations()-->"+Arrays.toString(body.getSegmentations()));
        		 List<Integer> segmentationIds = metaSegmentationMapper.findMetaSegmentationBySegmentation(body.getSegmentations());
                 this.LOGGER.info("segmentationIds-->"+segmentationIds.toString());
                 for (Integer investorSegmentationId : segmentationIds){
                     InvestorSegmentation investorSegmentation = new InvestorSegmentation();
                     investorSegmentation.setId(body.getInvestorId());
                     investorSegmentation.setSegmentationId(investorSegmentationId);
                     investorSegmentationList.add(investorSegmentation);
                 }
        }
        if(investorSegmentationList !=null && investorSegmentationList.size()>0) {
        	investorSegmentationList.forEach((e)->{
        		if(e!=null) {
        			investorSegmentationMapper.insert(e);
        		}
        	});
        }
        //所在城市-zd
        List<InvestorCity> investorCityList = new ArrayList<>();
        investorCityService.deleteAll(investor.getId());
        
        if(null != body.getCitys() && body.getCitys().length != 0){
            for (String e : body.getCitys()){
                InvestorCity investorCity = new InvestorCity();
                investorCity.setId(body.getInvestorId());
                investorCity.setCity(e);
                investorCityList.add(investorCity);
            }
        }
        if(investorCityList !=null && investorCityList.size()>0) {
        	investorCityList.forEach((e)->{
        		if(e!=null) {
        			investorCityMapper.insert(e);
        		}
        	});
        }
        //自定义城市-zd
        List<InvestorSelfdefCity> investorSelfdefCityList = new ArrayList<>();
        investorSelfdefCityService.deleteAll(investor.getId());  
        
        if(null != body.getSelfDefCity() && body.getSelfDefCity().length != 0){
            for (String e : body.getSelfDefCity()){
                InvestorSelfdefCity investorSelfdefCity = new InvestorSelfdefCity();
                investorSelfdefCity.setId(body.getInvestorId());  
                investorSelfdefCity.setSelfDefCity(e);
                investorSelfdefCityList.add(investorSelfdefCity);
            }
        }
        if(investorSelfdefCityList !=null && investorSelfdefCityList.size()>0) {
        	investorSelfdefCityList.forEach((e)->{
        		if(e!=null) {
        			investorSelfdefCityMapper.insert(e);
        		}
        	});
        }
        //工作经历
        List<InvestorWorkExperience> investorWorkExperienceList = new ArrayList<>();
        investorWorkExperienceService.deleteAll(investor.getId());
        
        if(null != body.getWorkExperiences() && body.getWorkExperiences().length != 0){
            for (String e : body.getWorkExperiences()){
                InvestorWorkExperience investorWorkExperience = new InvestorWorkExperience();
                investorWorkExperience.setId(body.getInvestorId());
                investorWorkExperience.setWorkExperience(e);
                investorWorkExperienceList.add(investorWorkExperience);
            }
        }
        if(investorWorkExperienceList !=null && investorWorkExperienceList.size()>0) {
        	investorWorkExperienceList.forEach((e)->{
        		if(e!=null) {
        			investorWorkExperienceMapper.insert(e);
        		}
        	});
        }
        //教育经历-zd
        List<InvestorEducationExperience> investorEducationExperienceList = new ArrayList<>();
        investorEducationExperienceService.deleteAll(investor.getId());
        if(null != body.getEducationExperiences() && body.getEducationExperiences().length != 0){
            for (String e : body.getEducationExperiences()){
                InvestorEducationExperience investorEducationExperience = new InvestorEducationExperience();
                investorEducationExperience.setId(body.getInvestorId());
                investorEducationExperience.setEducationExperience(e);
                investorEducationExperienceList.add(investorEducationExperience);
            }
        }
        if(investorEducationExperienceList !=null && investorEducationExperienceList.size()>0) {
        	investorEducationExperienceList.forEach((e)->{
        		if(e!=null) {
        			investorEducationExperienceMapper.insert(e);
        		}
        	});
        }
        //创业经历-zd
        List<InvestorBusiness> investorBusinessList = new ArrayList<>();
        investorBusinessService.deleteAll(investor.getId());
        if(null != body.getBusinesses() && body.getBusinesses().length != 0){
            for (String e : body.getBusinesses()){
                InvestorBusiness investorBusiness = new InvestorBusiness();
                investorBusiness.setId(body.getInvestorId());
                investorBusiness.setBusiness(e);
                investorBusinessList.add(investorBusiness);
            }
        }
        if(investorBusinessList !=null && investorBusinessList.size()>0) {
        	investorBusinessList.forEach((e)->{
        		if(e!=null) {
        			investorBusinessMapper.insert(e);
        		}
        	});
        }
        
        result.setStatus(200);
        result.setMessage("success");  
        result.setData(true);

        return result;

    }

    @Override
    public CommonDto<InvestorBasicInfoOutputDto> getInvestorBasicInfo(Integer investorId) {
        CommonDto<InvestorBasicInfoOutputDto> result = new CommonDto<>();
        InvestorBasicInfoOutputDto investorBasicInfoOutputDto = new InvestorBasicInfoOutputDto();

        Investors investor = investorsMapper.selectByPrimaryKey(investorId);
        if(null == investor){
            result.setData(null);
            result.setMessage("没有该投资人");
            result.setStatus(300);
            return result;
        }
        investorBasicInfoOutputDto.setInvestorId(investor.getId());
        String identityName = metaIdentityTypeMapper.findIdentityNameById(investor.getIdentityType());
        investorBasicInfoOutputDto.setIdentityType(identityName);
        investorBasicInfoOutputDto.setWeiChat(investor.getWeichat());
        investorBasicInfoOutputDto.setEmail(investor.getEmail());
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        if(null != investor.getBirthDay()){
            investorBasicInfoOutputDto.setBirthDay(sdf.format(investor.getBirthDay()));
        }
        investorBasicInfoOutputDto.setSex(investor.getSex());
        //学历
        if(investor.getDiploma() != null) {
        	 String diploma = metaDiplomaMapper.selectByDiplomaId(investor.getDiploma());
             investorBasicInfoOutputDto.setDiploma(diploma);
        }
        //国籍
        if(investor.getNationality() != null) {
        	String countryName = metaRegionMapper.selectByRegionId(investor.getNationality());
            investorBasicInfoOutputDto.setNationality(countryName);
        }
        //任职时间
        if(null != investor.getTenureTime()) {
            investorBasicInfoOutputDto.setTenureTime(sdf.format(investor.getTenureTime()));
        }
        
        investorBasicInfoOutputDto.setCompanyIntro(investor.getCompanyIntroduction());
        
        investorBasicInfoOutputDto.setBusinessCard(investor.getBusinessCard());
        investorBasicInfoOutputDto.setBusinessCardOpposite(investor.getBusinessCardOpposite());
        investorBasicInfoOutputDto.setPicture(investor.getPicture());
        
        investorBasicInfoOutputDto.setBussiness(investor.getBusinessDescription());
        investorBasicInfoOutputDto.setWorkExperience(investor.getWorkDescription());
        investorBasicInfoOutputDto.setEducationExperience(investor.getEducationDescription());
        
        investorBasicInfoOutputDto.setHonor(investor.getHonor());

        //所在领域
        InvestorSegmentation investorSegmentation = new InvestorSegmentation();
        investorSegmentation.setId(investorId);
        List<InvestorSegmentation> investorSegmentationList = investorSegmentationService.select(investorSegmentation);
        
        if(null != investorSegmentationList && investorSegmentationList.size() != 0){
            List<Integer> segmentationIds = new ArrayList<>();
            investorSegmentationList.forEach(e -> {
            	segmentationIds.add(e.getSegmentationId());
            });
            List<MetaSegmentation> metaSegmentationList = metaSegmentationMapper.selectBySegmentationIds(segmentationIds);
            
            //设置所在领域
            if(metaSegmentationList!=null && metaSegmentationList.size()!=0) {
            	List<String> segmentations = new ArrayList<>();
            	metaSegmentationList.forEach( e -> {
                    segmentations.add(e.getName());
                });
            	investorBasicInfoOutputDto.setSegmentations(segmentations);
            }
        }
        
        //城市为选定城市以及自定义城市的总和
        List<String> citysResult=new ArrayList<>();
        //选定城市
        InvestorCity investorCity = new InvestorCity();
        investorCity.setId(investorId);
        List<InvestorCity> investorCityLists = investorCityService.select(investorCity);
        if(investorCityLists!=null && investorCityLists.size()!=0) {
        	investorCityLists.forEach((e)->{
        		citysResult.add(e.getCity());
        	});
        }
        //自定义城市
        InvestorSelfdefCity investorSelfdefCity =new InvestorSelfdefCity();
        investorSelfdefCity.setId(investorId);
        List<InvestorSelfdefCity> investorSelfdefCitys = investorSelfdefCityMapper.select(investorSelfdefCity);
        if(investorSelfdefCitys!=null && investorSelfdefCitys.size()!=0) {
        	investorSelfdefCitys.forEach((e)->{
        		citysResult.add(e.getSelfDefCity());
        	});
        }
        
        if(citysResult !=null && citysResult.size()!=0) {
        	investorBasicInfoOutputDto.setCitys(citysResult);
        }
        
        //创业经历
        InvestorBusiness investorBusiness = new InvestorBusiness();
        investorBusiness.setId(investorId);
        List<InvestorBusiness> investorBusinessesList = investorBusinessService.select(investorBusiness);
        
        if(null != investorBusinessesList && investorBusinessesList.size() != 0){
            List<String> businessess = new ArrayList<>();
            investorBusinessesList.forEach(e -> {
            	businessess.add(e.getBusiness());
            });
            investorBasicInfoOutputDto.setBusinesses(businessess);  
        }
        //工作经历
        InvestorWorkExperience investorWorkExperience = new InvestorWorkExperience();
        investorWorkExperience.setId(investorId);
        List<InvestorWorkExperience> WorkExperiences = investorWorkExperienceService.select(investorWorkExperience);
        
        if(null != WorkExperiences && WorkExperiences.size() != 0){
            List<String> investorWorkExperiences = new ArrayList<>();
            WorkExperiences.forEach(e -> {
                investorWorkExperiences.add(e.getWorkExperience());
            });
            investorBasicInfoOutputDto.setWorkExperiences(investorWorkExperiences);
        }
        //教育经历
        InvestorEducationExperience investorEducationExperience = new InvestorEducationExperience();
        investorEducationExperience.setId(investorId);
        List<InvestorEducationExperience> investorEducationExperienceList = investorEducationExperienceService.select(investorEducationExperience);
        
        if(null != investorEducationExperienceList && investorEducationExperienceList.size() != 0){
            List<String> investorEducationExperiences = new ArrayList<>();
            investorEducationExperienceList.forEach(e -> {
                investorEducationExperiences.add(e.getEducationExperience());
            });
            investorBasicInfoOutputDto.setEducationExperiences(investorEducationExperiences);
        }

        result.setData(investorBasicInfoOutputDto);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }

    @Override
    public CommonDto<InvestorIntroductionDto> getInvestorIntroduction(Integer investorId) {

        CommonDto<InvestorIntroductionDto> result = new CommonDto<>();
        InvestorIntroductionDto investorIntroductionDto = new InvestorIntroductionDto();

        Investors investor = investorsMapper.selectByPrimaryKey(investorId);

        if(null == investor){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }
        investorIntroductionDto.setInvestorId(investorId);
        investorIntroductionDto.setInvestorIntroduction(investor.getPersonalIntroduction());
        result.setStatus(200);
        result.setMessage("success");
        result.setData(investorIntroductionDto);  

        return result;
    }

    @Override
    public CommonDto<String> addOrUpdateInvestorIntroduction(InvestorIntroductionDto body) {
        CommonDto<String> result = new CommonDto<>();
        Investors investor = investorsMapper.selectByPrimaryKey(body.getInvestorId());
        if(null == investor){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData("没有该投资人");
        }
        investor.setPersonalIntroduction(body.getInvestorIntroduction());
        Integer investorIntroductionResult = investorsMapper.updateByPrimaryKeySelective(investor);

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
