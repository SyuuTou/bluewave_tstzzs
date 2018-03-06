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
        Investors investors = new Investors();
        investors.setId(body.getInvestorId());
        Integer identityType = metaIdentityTypeMapper.findIdByIdentityName(body.getIdentityType());
        investors.setIdentityType(identityType);
        investors.setWeichat(body.getWeiChat());
        investors.setEmail(body.getEmail());
        if(body.getBirthDay()!=null) {
        	investors.setBirthDay(DateUtils.parse1(body.getBirthDay()));
        }
        investors.setSex(body.getSex());
        Integer diplomaId = metaDiplomaMapper.findDiplomaIdBydiplomaName(body.getDiploma());
        investors.setDiploma(diplomaId);
        Integer nationalityId = metaRegionMapper.findNationalityIdByCountry(body.getNationality());
        investors.setNationality(nationalityId);
        if(body.getTenureTime()!=null) {
        	investors.setTenureTime(DateUtils.parse1(body.getTenureTime()));
        }
        investors.setCompanyIntroduction(body.getCompanyIntro());
        //设置工作名片正面
        investors.setBusinessCard(body.getBusinessCard());
        //设置工作名片反面
        investors.setBusinessCardOpposite(body.getBusinessCardOpposite());
        //设置高清图片
        investors.setPicture(body.getPicture());
        //设置创业经历描述
        investors.setBusinessDescription(body.getBussiness());
        //设置教育经历描述
        investors.setEducationDescription(body.getEducationExperience());
        //设置工作经历描述
        investors.setWorkDescription(body.getWorkExperience());
        investors.setHonor(body.getHonor());
        
        //TODO 
        if(body.getInvestorId() == null){
            investors.setCreateTime(new Date());
            investorsMapper.insert(investors);
        }else{
        	//肯定会执行以下代码，investorId的传输是必须的，必须建立在已有投资人的基础上
            investors.setUpdateTime(new Date());
            investorsMapper.updateByPrimaryKeySelective(investors);
        }
        
        //所在领域
        Integer investorSegmentationInsertResult = -1;
        List<InvestorSegmentation> investorSegmentationList = new ArrayList<>();
        investorSegmentationService.deleteAll(body.getInvestorId());
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
      //投资人所在领域的插入-zd
        if(investorSegmentationList !=null && investorSegmentationList.size()>0) {
        	investorSegmentationList.forEach((e)->{
        		if(e!=null) {
        			investorSegmentationMapper.insert(e);
        		}
        	});
        }
//        以下方法不满足通用Mapper的list增加条件-caochuangui
//        investorSegmentationInsertResult = investorSegmentationService.insertList(investorSegmentationList);
        //所在城市
        Integer investorCityInsertResult = -1;
        List<InvestorCity> investorCityList = new ArrayList<>();
        investorCityService.deleteAll(body.getInvestorId());
        if(null != body.getCitys() && body.getCitys().length != 0){
            for (String investorCityName : body.getCitys()){
                InvestorCity investorCity = new InvestorCity();
                investorCity.setId(body.getInvestorId());
                investorCity.setCity(investorCityName);
                investorCityList.add(investorCity);
            }
        }
        //重新执行插入-zd
        if(investorCityList !=null && investorCityList.size()>0) {
        	investorCityList.forEach((e)->{
        		if(e!=null) {
        			investorCityMapper.insert(e);
        		}
        	});
        }
//        以下方法不满足通用Mapper的list增加条件-caochuangui
//        investorCityInsertResult = investorCityService.insertList(investorCityList);
        //自定义城市
        Integer investorSelfDefCityInsertResult = -1;
        List<InvestorSelfdefCity> investorSelfdefCityList = new ArrayList<>();
        investorSelfdefCityService.deleteAll(body.getInvestorId());
        if(null != body.getSelfDefCity() && body.getSelfDefCity().length != 0){
            for (String investorSelfDefCityName : body.getSelfDefCity()){
                InvestorSelfdefCity investorSelfdefCity = new InvestorSelfdefCity();
                investorSelfdefCity.setId(body.getInvestorId());
                investorSelfdefCity.setSelfDefCity(investorSelfDefCityName);
                investorSelfdefCityList.add(investorSelfdefCity);
            }
        }
      //重新执行插入-zd
        if(investorSelfdefCityList !=null && investorSelfdefCityList.size()>0) {
        	investorSelfdefCityList.forEach((e)->{
        		if(e!=null) {
        			investorSelfdefCityMapper.insert(e);
        		}
        	});
        }
//        以下方法不满足通用Mapper的list增加条件-caochuangui
//        investorSelfDefCityInsertResult = investorSelfdefCityService.insertList(investorSelfdefCityList);

        //工作经历
        Integer investorWorkExperienceInsertResult = -1;
        List<InvestorWorkExperience> investorWorkExperienceList = new ArrayList<>();
        investorWorkExperienceService.deleteAll(body.getInvestorId());
        if(null != body.getWorkExperiences() && body.getWorkExperiences().length != 0){
            for (String investorWorkExperience_i : body.getWorkExperiences()){
                InvestorWorkExperience investorWorkExperience = new InvestorWorkExperience();
                investorWorkExperience.setId(body.getInvestorId());
                investorWorkExperience.setWorkExperience(investorWorkExperience_i);
                investorWorkExperienceList.add(investorWorkExperience);
            }
        }
      //重新执行投资人工作经历插入-zd
        if(investorWorkExperienceList !=null && investorWorkExperienceList.size()>0) {
        	investorWorkExperienceList.forEach((e)->{
        		if(e!=null) {
        			investorWorkExperienceMapper.insert(e);
        		}
        	});
        }
        //此处无法使用同于mapper执行list的增加
//        investorWorkExperienceInsertResult = investorWorkExperienceService.insertList(investorWorkExperienceList);

        //教育经历
        Integer investorEducationExperienceInsertResult = -1;
        List<InvestorEducationExperience> investorEducationExperienceList = new ArrayList<>();
        investorEducationExperienceService.deleteAll(body.getInvestorId());
        if(null != body.getEducationExperiences() && body.getEducationExperiences().length != 0){
            for (String investorEducationExperience_i : body.getEducationExperiences()){
                InvestorEducationExperience investorEducationExperience = new InvestorEducationExperience();
                investorEducationExperience.setId(body.getInvestorId());
                investorEducationExperience.setEducationExperience(investorEducationExperience_i);
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
      //此处无法使用同于mapper执行list的增加
//        investorEducationExperienceInsertResult = investorEducationExperienceService.insertList(investorEducationExperienceList);

        //创业经历
        Integer investorBusinessesInsertResult = -1;
        List<InvestorBusiness> investorBusinessList = new ArrayList<>();
        investorBusinessService.deleteAll(body.getInvestorId());
        if(null != body.getBusinesses() && body.getBusinesses().length != 0){
            for (String investorBusiness_i : body.getBusinesses()){
                InvestorBusiness investorBusiness = new InvestorBusiness();
                investorBusiness.setId(body.getInvestorId());
                investorBusiness.setBusiness(investorBusiness_i);
                investorBusinessList.add(investorBusiness);
            }
        }
        //投资人创业经历的重新增加-zd
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

        Investors investors = investorsMapper.selectByPrimaryKey(investorId);
        if(null == investors){
            result.setData(null);
            result.setMessage("没有该投资人");
            result.setStatus(300);
            return result;
        }
        investorBasicInfoOutputDto.setInvestorId(investors.getId());
        String identityName = metaIdentityTypeMapper.findIdentityNameById(investors.getIdentityType());
        investorBasicInfoOutputDto.setIdentityType(identityName);
        investorBasicInfoOutputDto.setWeiChat(investors.getWeichat());
        investorBasicInfoOutputDto.setEmail(investors.getEmail());
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        if(null != investors.getBirthDay()){
            investorBasicInfoOutputDto.setBirthDay(sdf.format(investors.getBirthDay()));
        }
        investorBasicInfoOutputDto.setSex(investors.getSex());
        //学历
        if(investors.getDiploma() != null) {
        	 String diploma = metaDiplomaMapper.selectByDiplomaId(investors.getDiploma());
             investorBasicInfoOutputDto.setDiploma(diploma);
        }
        //国籍
        if(investors.getNationality() != null) {
        	String countryName = metaRegionMapper.selectByRegionId(investors.getNationality());
            investorBasicInfoOutputDto.setNationality(countryName);
        }
        //任职时间
        if(null != investors.getTenureTime()) {
            investorBasicInfoOutputDto.setTenureTime(sdf.format(investors.getTenureTime()));
        }
        
        investorBasicInfoOutputDto.setCompanyIntro(investors.getCompanyIntroduction());
        
        investorBasicInfoOutputDto.setBusinessCard(investors.getBusinessCard());
        investorBasicInfoOutputDto.setBusinessCardOpposite(investors.getBusinessCardOpposite());
        investorBasicInfoOutputDto.setPicture(investors.getPicture());
        
        investorBasicInfoOutputDto.setBussiness(investors.getBusinessDescription());
        investorBasicInfoOutputDto.setWorkExperience(investors.getWorkDescription());
        investorBasicInfoOutputDto.setEducationExperience(investors.getEducationDescription());
        
        investorBasicInfoOutputDto.setHonor(investors.getHonor());

        InvestorSegmentation investorSegmentation = new InvestorSegmentation();
        investorSegmentation.setId(investorId);
        List<InvestorSegmentation> investorSegmentationList = investorSegmentationService.select(investorSegmentation);
        String[] investorSegmentationArr = null;
        if(null == investorSegmentationList || investorSegmentationList.size() == 0){
            investorBasicInfoOutputDto.setSegmentations(investorSegmentationArr);
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
            investorBasicInfoOutputDto.setSegmentations(investorSegmentationArr);
        }

        
        //城市为选定城市以及自定义城市的总和
        List<String> citysResult=new ArrayList<>();
        
        InvestorCity investorCity = new InvestorCity();
        investorCity.setId(investorId);
        List<InvestorCity> investorCityLists = investorCityService.select(investorCity);
        if(investorCityLists!=null && investorCityLists.size()!=0) {
        	investorCityLists.forEach((e)->{
        		citysResult.add(e.getCity());
        	});
        }
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
        //数组--曹传桂
        /*String[] investorCityArr = null;
        if(null == investorCityList || investorCityList.size()==0){
            investorBasicInfoOutputDto.setCitys(investorCityArr);
        }else{
            List<String> investorCitys = new ArrayList<>();
            investorCityList.forEach(investorCity_i -> {
                investorCitys.add(investorCity_i.getCity());
            });
            investorCityArr = new String[investorCitys.size()];
            investorCitys.toArray(investorCityArr);
            investorBasicInfoOutputDto.setCitys(investorCityArr);
        }*/
        //自当以城市--曹传桂
        /*InvestorSelfdefCity investorSelfdefCity = new InvestorSelfdefCity();
        investorSelfdefCity.setId(investorId);
        List<InvestorSelfdefCity> investorSelfdefCityList = investorSelfdefCityService.select(investorSelfdefCity);
        String[] investorSelfdefCityArr = null;
        if(null == investorSelfdefCityList || investorSelfdefCityList.size() == 0){
            investorBasicInfoOutputDto.setCitys(investorSelfdefCityArr);
        }else{
            List<String> investorSelfdefCitys = new ArrayList<>();
            investorSelfdefCityList.forEach(investorSelfdefCity_i -> {
                investorSelfdefCitys.add(investorSelfdefCity_i.getSelfDefCity());
            });
            investorSelfdefCityArr = new String[investorSelfdefCitys.size()];
            investorSelfdefCitys.toArray(investorSelfdefCityArr);
            investorBasicInfoOutputDto.setSelfDefCity(investorSelfdefCityArr);
        }*/

        InvestorBusiness investorBusiness = new InvestorBusiness();
        investorBusiness.setId(investorId);
        List<InvestorBusiness> investorBusinessesList = investorBusinessService.select(investorBusiness);
        String[] investorBusinessArr = null;
        if(null == investorBusinessesList || investorBusinessesList.size() == 0){
            investorBasicInfoOutputDto.setBusinesses(investorBusinessArr);
        }else{
            List<String> investorBusinessess = new ArrayList<>();
            investorBusinessesList.forEach(investorSelfdefCity_i -> {
                investorBusinessess.add(investorSelfdefCity_i.getBusiness());
            });
            investorBusinessArr = new String[investorBusinessess.size()];
            investorBusinessess.toArray(investorBusinessArr);
            investorBasicInfoOutputDto.setBusinesses(investorBusinessArr);  
        }

        InvestorWorkExperience investorWorkExperience = new InvestorWorkExperience();
        investorWorkExperience.setId(investorId);
        List<InvestorWorkExperience> investorWorkExperienceList = investorWorkExperienceService.select(investorWorkExperience);
        String[] investorWorkExperienceArr = null;
        if(null == investorWorkExperienceList || investorWorkExperienceList.size() == 0){
            investorBasicInfoOutputDto.setWorkExperiences(investorWorkExperienceArr);
        }else{
            List<String> investorWorkExperiences = new ArrayList<>();
            investorWorkExperienceList.forEach(investorWorkExperience_i -> {
                investorWorkExperiences.add(investorWorkExperience_i.getWorkExperience());
            });
            investorWorkExperienceArr = new String[investorWorkExperiences.size()];
            investorWorkExperiences.toArray(investorWorkExperienceArr);
            investorBasicInfoOutputDto.setWorkExperiences(investorWorkExperienceArr);
        }

        InvestorEducationExperience investorEducationExperience = new InvestorEducationExperience();
        investorEducationExperience.setId(investorId);
        List<InvestorEducationExperience> investorEducationExperienceList = investorEducationExperienceService.select(investorEducationExperience);
        String[] investorEducationExperienceArr = null;
        if(null == investorEducationExperienceList || investorEducationExperienceList.size() == 0){
            investorBasicInfoOutputDto.setEducationExperiences(investorEducationExperienceArr);
        }else{
            List<String> investorEducationExperiences = new ArrayList<>();
            investorEducationExperienceList.forEach(investorEducationExperience_i -> {
                investorEducationExperiences.add(investorEducationExperience_i.getEducationExperience());
            });
            investorEducationExperienceArr = new String[investorEducationExperiences.size()];
            investorEducationExperiences.toArray(investorEducationExperienceArr);
            //转换为数组
            investorBasicInfoOutputDto.setEducationExperiences(investorEducationExperienceArr);
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
