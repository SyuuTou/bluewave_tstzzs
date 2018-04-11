package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto.ProjectTeamMemberInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto.ProjectTeamMemberOutputDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.*;
import com.lhjl.tzzs.proxy.utils.DateUtils;

import tk.mybatis.mapper.entity.Example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lanhaijulang on 2018/1/18.
 */
@Service
public class ProjectAdminTeamServiceImpl implements ProjectAdminTeamService {

    @Autowired
    private ProjectsMapper projectsMapper;

    @Autowired
    private ProjectTeamMemberMapper projectTeamMemberMapper;
    
    @Autowired
    private ProjectTeamMemberSelfteamMapper projectTeamMemberSelfteamMapper;
    
    @Autowired
    private ProjectTeamMemberSegmentationMapper projectTeamMemberSegmentationMapper;
    
    @Autowired
    private ProjectTeamMemberWorkMapper projectTeamMemberWorkMapper;

    @Resource
    private ProjectTeamMemberWorkService projectTeamMemberWorkService;

    @Resource
    private ProjectTeamMemberEducationService projectTeamMemberEducationService;

    @Resource
    private ProjectTeamMemberSelfteamService projectTeamMemberSelfteamService;

    @Resource
    private ProjectTeamMemberSelfcityMapper projectTeamMemberSelfcityMapper;

    @Resource
    private ProjectTeamMemberCityService projectTeamMemberCityService;

    @Resource
    private ProjectTeamMemberSegmentationService projectTeamMemberSegmentationService;

    @Resource
    private ProjectTeamMemberStageService projectTeamMemberStageService;
    
    @Autowired
    private ProjectTeamMemberEducationMapper projectTeamMemberEducationMapper;

    @Autowired
    private ProjectTeamMemberBusinessService projectTeamMemberBusinessService;

    @Autowired
    private MetaFinancingMapper financingMapper;

    @Autowired
    private MetaRegionMapper metaRegionMapper;

    @Autowired
    private UsersMapper usersMapper;
    
    @Autowired
    private InvestmentInstitutionTeamMapper investmentInstitutionTeamMapper;
    
    @Autowired
    private InvestmentInstitutionsMemberEducationMapper investmentInstitutionsMemberEducationMapper;
    @Autowired
    private InvestmentInstitutionsMemberWorkMapper investmentInstitutionsMemberWorkMapper;
    
    @Autowired
    private ProjectTeamMemberBusinessMapper projectTeamMemberBusinessMapper;
    
    @Autowired
    private ProjectTeamMemberCityMapper projectTeamMemberCityMapper;
    
    @Autowired
    private ProjectTeamMemberStageMapper projectTeamMemberStageMapper;

    @Override
    public CommonDto<Map<String, List<LabelList>>> queryHotCity() {
        CommonDto<Map<String, List<LabelList>>> result = new CommonDto<Map<String, List<LabelList>>>();
        Map<String, List<LabelList>> dataMap = new HashMap<String, List<LabelList>>();
        List<LabelList> labelLists = new ArrayList<LabelList>();
        // 热点城市
        labelLists = financingMapper.queryHotCity();
        dataMap.put("cityKey",labelLists);
        result.setData(dataMap);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }

    @Override
    public CommonDto<List<ProjectTeamMemberOutputDto>> getProjectTeamMemberList(Integer subjectId,Integer subjectType) {
    	CommonDto<List<ProjectTeamMemberOutputDto>> result = new CommonDto<>();
        
        if(subjectId==null) {
        	result.setData(null);
            result.setStatus(500);
            result.setMessage("请输入主体id");
            return result;
        }
        if(subjectType==null) {
        	result.setData(null);
            result.setStatus(500);
            result.setMessage("请输入主体类型");
            return result;
        }
        
        List<ProjectTeamMemberOutputDto> projectTeamMemberOutputDtoList = new ArrayList<>();
        if(Integer.valueOf(1).equals(subjectType)) {//项目
        	Projects projects1 = projectsMapper.selectByPrimaryKey(subjectId);
            //如果项目存在的话
        	if(null != projects1) {
            	List<ProjectTeamMember> projectTeamMembers = new ArrayList<>();
                projectTeamMembers = projectTeamMemberMapper.getProjectMemberList(subjectId);
                if (null != projectTeamMembers && projectTeamMembers.size() != 0) {
                    for(ProjectTeamMember e: projectTeamMembers){
                        ProjectTeamMemberOutputDto output = new ProjectTeamMemberOutputDto();
                        //设置项目id
                        output.setProjectId(subjectId);
                        //设置成员id
                        output.setMemberId(e.getId());
                        output.setWeight(e.getWeight());
                        output.setName(e.getMumberName());
                        output.setJobTitle(e.getMumberDuties());
                        output.setMemberDesc(e.getMumberDesc());
                        output.setPhone(e.getPhone());

                        output.setBirthDay(DateUtils.format1(e.getBirthDay()));
                        output.setTenureTime(DateUtils.format1(e.getTenureTime()));
                        output.setSex(e.getSex());
                        output.setDiploma(e.getDiploma());
                        output.setNationality(e.getNationality());
                        
                        
                        //获取每个成员的工作经历
                        ProjectTeamMemberWork projectTeamMemberWork = new ProjectTeamMemberWork();
                        projectTeamMemberWork.setProjectTeamMemberId(e.getId());
                        List<ProjectTeamMemberWork> projectTeamMemberWorks = projectTeamMemberWorkService.select(projectTeamMemberWork);
                        
                        List<String> projectTeamMemberWorkList = new ArrayList<>();
                        if(null != projectTeamMemberWorks && projectTeamMemberWorks.size() != 0){
                            projectTeamMemberWorks.forEach(obj -> {
                                projectTeamMemberWorkList.add(obj.getWorkExperience());
                            });
                        }
                        output.setWorkExperiences(projectTeamMemberWorkList);
                        
                        //获取每个成员的教育经历
                        ProjectTeamMemberEducation projectTeamMemberEducation = new ProjectTeamMemberEducation();
                        projectTeamMemberEducation.setProjectTeamMemberId(e.getId());
                        List<ProjectTeamMemberEducation> projectTeamMemberEducations = projectTeamMemberEducationService.select(projectTeamMemberEducation);
                        
                        List<String> projectTeamMemberEducationList = new ArrayList<>();
                        if(null != projectTeamMemberEducations && projectTeamMemberEducations.size() != 0){
                            projectTeamMemberEducations.forEach(obj -> {
                                projectTeamMemberEducationList.add(obj.getEducationExperience());
                            });
                            output.setEducationExperience(projectTeamMemberEducationList);
                        }
                        //设置基本信息
                        output.setIsOnJob(e.getIsonjob());
                        output.setHeadPicture(e.getHeadPicture());
                        output.setPicture(e.getPicture());
                        output.setEmail(e.getEmail());
                        output.setWeiChat(e.getWeichat());
                        output.setTeamId(e.getTeamId());
                        output.setStockPer(e.getShareRatio());
                        
                        //自定义团队
                        ProjectTeamMemberSelfteam projectTeamMemberSelfteam_i = new ProjectTeamMemberSelfteam();
                        projectTeamMemberSelfteam_i.setMemberId(e.getId());
                        List<ProjectTeamMemberSelfteam> projectTeamMemberSelfteamList = projectTeamMemberSelfteamService.select(projectTeamMemberSelfteam_i);
                        if(projectTeamMemberSelfteamList != null && projectTeamMemberSelfteamList.size() != 0){
                        	StringBuffer sb=new StringBuffer();
                        	for(ProjectTeamMemberSelfteam obj:projectTeamMemberSelfteamList) {
                        		sb.append(obj.getTeamName()).append("、");
                        	}
                            output.setSelfDefTeam(sb.toString().substring(0 , sb.toString().length()-1));
                        }
                        //设置团队成员关注领域  
                        ProjectTeamMemberSegmentation projectTeamMemberSegmentation = new ProjectTeamMemberSegmentation();
                        projectTeamMemberSegmentation.setMemberId(e.getId());
                        List<ProjectTeamMemberSegmentation> projectTeamMemberSegmentationList = projectTeamMemberSegmentationService.select(projectTeamMemberSegmentation);
                        
                        List<Integer> projectTeamMemberSegmentations = new ArrayList<>();
                        if(projectTeamMemberSegmentationList != null && projectTeamMemberSegmentationList.size() != 0){
                            projectTeamMemberSegmentationList.forEach(obj -> {
                                projectTeamMemberSegmentations.add(obj.getSegmentationId());
                            });
                            output.setFocusDomain(projectTeamMemberSegmentations);
                        }
                        //设置团队成员投资阶段
                        ProjectTeamMemberStage projectTeamMemberStage = new ProjectTeamMemberStage();
                        projectTeamMemberStage.setMemberId(e.getId());
                        List<ProjectTeamMemberStage> projectTeamMemberStageList = projectTeamMemberStageService.select(projectTeamMemberStage);
                        
                        List<Integer> projectTeamMemberStages = new ArrayList<>();
                        if(null != projectTeamMemberStageList && projectTeamMemberStageList.size() != 0){
                            projectTeamMemberStageList.forEach(obj -> {
                                projectTeamMemberStages.add(obj.getStageId());
                            });
                            output.setInvestStages(projectTeamMemberStages);
                        }
                        //所在城市
                        ProjectTeamMemberCity projectTeamMemberCity = new ProjectTeamMemberCity();
                        projectTeamMemberCity.setMemberId(e.getId());
                        List<ProjectTeamMemberCity> projectTeamMemberCityList = projectTeamMemberCityService.select(projectTeamMemberCity);
                        
                        List<String> projectTeamMemberCitys = new ArrayList<>();
                        if (projectTeamMemberCityList != null && projectTeamMemberCityList.size() != 0){
                            projectTeamMemberCityList.forEach(obj -> {
                                projectTeamMemberCitys.add(obj.getCityName());
                            });
                            output.setCitys(projectTeamMemberCitys);
                        }

                        //自定义城市
                        ProjectTeamMemberSelfcity projectTeamMemberSelfcity = new ProjectTeamMemberSelfcity();
                        projectTeamMemberSelfcity.setMemberId(e.getId());
                        List<ProjectTeamMemberSelfcity> projectTeamMemberSelfcityList = projectTeamMemberSelfcityMapper.select(projectTeamMemberSelfcity);
                        List<String> projectTeamMemberSelfcitys = new ArrayList<>();
                        if (null != projectTeamMemberSelfcityList && projectTeamMemberSelfcityList.size() != 0){
                            projectTeamMemberSelfcityList.forEach(obj -> {
                                projectTeamMemberSelfcitys.add(obj.getCity());
                            });
                            output.setSelfcitys(projectTeamMemberSelfcitys);
                        }
                        //创业经历
                        ProjectTeamMemberBusiness projectTeamMemberBusiness = new ProjectTeamMemberBusiness();
                        projectTeamMemberBusiness.setMemberId(e.getId());
                        List<ProjectTeamMemberBusiness> projectTeamMemberBusinessList = projectTeamMemberBusinessService.select(projectTeamMemberBusiness);
                        
                        List<String> projectTeamMemberBusinesss = new ArrayList<>();
                        if (projectTeamMemberBusinessList != null && projectTeamMemberBusinessList.size() !=0){
                            projectTeamMemberBusinessList.forEach(obj -> {
                                projectTeamMemberBusinesss.add(obj.getCompanyName());
                            });
                            output.setBusinesses(projectTeamMemberBusinesss);
                        }

                        output.setBusinessDesc(e.getBusinessExperienceDesc());
                        output.setWorkDesc(e.getWorkExperienceDesc());
                        output.setEducationDesc(e.getEducationExperienceDesc());
                        output.setIsHide(e.getIshide());
                        projectTeamMemberOutputDtoList.add(output);
                    };
                }
        	}
        }else if(Integer.valueOf(2).equals(subjectType)) {//机构
        	InvestmentInstitutionTeam iiTeam =new InvestmentInstitutionTeam();
        	iiTeam.setInvestmentInstitutionId(subjectId);
        	List<InvestmentInstitutionTeam> iiteams = investmentInstitutionTeamMapper.select(iiTeam);
        	
        	if(iiteams!=null && iiteams.size()>0) {
        		for(InvestmentInstitutionTeam e:iiteams) {
        			ProjectTeamMemberOutputDto output = new ProjectTeamMemberOutputDto();
        			
        			//TODO 机构团队缺失字段
        			/*output.setBirthDay(DateUtils.format1(e.getBirthDay()));
                    output.setTenureTime(DateUtils.format1(e.getTenureTime()));
                    output.setSex(e.getSex());
                    output.setDiploma(e.getDiploma());
                    output.setNationality(e.getNationality());
                    output.setHeadPicture(e.getHeadPicture());
                    
                    output.setStockPer(e.getShareRatio());*/
                    
        			output.setMemberId(e.getId());
        			output.setProjectId(e.getInvestmentInstitutionId());
        			//高清头像
        			output.setName(e.getActualName());
        			output.setPicture(e.getPicture());
        			output.setWeight(e.getWeight());
        			//是否隐藏
        			output.setIsDelete(e.getHideYn());
        			output.setJobTitle(e.getCompanyDuties());
        			output.setMemberDesc(e.getMemberDesc());
        			output.setPhone(e.getPhonenumber());
        			//是否在职
        			output.setIsOnJob(e.getWorkYn());
        			output.setEmail(e.getEmail());
                    output.setWeiChat(e.getWechat());
                    //设置所属团队ID
                    output.setTeamId(e.getMetaIitTypeId());
                    
                    //设置机构团队成员的工作经历
                    InvestmentInstitutionsMemberWork work=new InvestmentInstitutionsMemberWork();
                    work.setMemberId(e.getId());
                    List<InvestmentInstitutionsMemberWork> works = investmentInstitutionsMemberWorkMapper.select(work);
                    
                    List<String> workNames=new ArrayList<>();
                    if(works!=null && works.size()>0) {
                    	for(InvestmentInstitutionsMemberWork obj:works) {
                    		workNames.add(obj.getWorkExperience());
                        }	
                    	output.setWorkExperiences(workNames);
                    }
                    
                    //设置机构团队成员的教育经历
                    InvestmentInstitutionsMemberEducation edu =new InvestmentInstitutionsMemberEducation();
                    edu.setMemberId(e.getId());
                    List<InvestmentInstitutionsMemberEducation> edus = investmentInstitutionsMemberEducationMapper.select(edu);
                    
                    List<String> eduNames=new ArrayList<>();
                    if(edus!=null && edus.size()>0) {
                    	for(InvestmentInstitutionsMemberEducation obj:edus) {
                    		eduNames.add(obj.getEducationExperience());
                        }	
                    	output.setEducationExperience(eduNames);
                    }
                    
        			projectTeamMemberOutputDtoList.add(output);
        		}
        	}
        }

        result.setData(projectTeamMemberOutputDtoList);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }



    @Override
    public CommonDto<String> addOrUpdatePojectTeamMember(ProjectTeamMemberInputDto body) {
        CommonDto<String> result = new CommonDto<>();
        
        if(null == body.getProjectId()){
            result.setMessage("failed");
            result.setStatus(500);
            result.setData("请输入主体id,已确定该成员属于某个主体");
            return result;
        }
        if(null == body.getSubjectType()){
            result.setMessage("failed");
            result.setStatus(500);
            result.setData("请输入主体类型");
            return result;
        }
        
        if(Integer.valueOf(1).equals(body.getSubjectType())) {//项目
            ProjectTeamMember projectTeamMember = new ProjectTeamMember();
            
            projectTeamMember.setId(body.getMemberId());
            //设置关联的主体id
            projectTeamMember.setProjectId(body.getProjectId());
            projectTeamMember.setWeight( body.getWeight());
            projectTeamMember.setMumberName(body.getName());
            projectTeamMember.setMumberDuties(body.getJobTitle());
            projectTeamMember.setMumberDesc(body.getMemberDesc());
            projectTeamMember.setPhone(body.getPhone());
            projectTeamMember.setIsonjob(body.getIsOnJob());
            projectTeamMember.setHeadPicture(body.getHeadPicture());
            projectTeamMember.setPicture(body.getPicture());
            projectTeamMember.setEmail(body.getEmail());
            projectTeamMember.setBirthDay(body.getBirthDay());
            projectTeamMember.setTenureTime(body.getTenureTime());
            projectTeamMember.setSex(body.getSex());
            projectTeamMember.setDiploma(body.getDiploma());
            projectTeamMember.setNationality(body.getNationality());
            projectTeamMember.setShareRatio(body.getStockPer());
            projectTeamMember.setWeichat(body.getWeiChat());
            projectTeamMember.setIshide(body.getIsHide());
            projectTeamMember.setWorkExperienceDesc(body.getWorkDesc());
            projectTeamMember.setEducationExperienceDesc(body.getEducationDesc());
            projectTeamMember.setBusinessExperienceDesc(body.getBusinessDesc());
            projectTeamMember.setTeamId(body.getTeamId());
            projectTeamMember.setYn(0);
            projectTeamMember.setCreateTime(new Date());
            
            //获取更新或插入后的增长id
            if(null == body.getMemberId()){
                projectTeamMemberMapper.insertSelective(projectTeamMember);
            }else{
                projectTeamMemberMapper.updateByPrimaryKey(projectTeamMember);
            }
            
            //自定义创业经历
            ProjectTeamMemberBusiness projectTeamMemberBusiness = new ProjectTeamMemberBusiness();
            projectTeamMemberBusiness.setMemberId(projectTeamMember.getId());
            projectTeamMemberBusinessService.delete(projectTeamMemberBusiness);
            
            List<ProjectTeamMemberBusiness> projectTeamMemberBusinessList = new ArrayList<>();
            if(body.getBusinesses() != null && body.getBusinesses().size() != 0 ){
                for(String e : body.getBusinesses()){
                    ProjectTeamMemberBusiness obj = new ProjectTeamMemberBusiness();
                    obj.setMemberId(projectTeamMember.getId());
                    obj.setCompanyName(e);
                    
                    projectTeamMemberBusinessMapper.insertSelective(obj);
                }
            }
            
            //自定义城市
            ProjectTeamMemberSelfcity projectTeamMemberSelfcity = new ProjectTeamMemberSelfcity();
            projectTeamMemberSelfcity.setMemberId(projectTeamMember.getId());
            projectTeamMemberSelfcityMapper.delete(projectTeamMemberSelfcity);
            
            if(body.getSelfcitys() != null && body.getSelfcitys().size() != 0 ){
                for(String e : body.getSelfcitys()){
                    ProjectTeamMemberSelfcity obj = new ProjectTeamMemberSelfcity();
                    obj.setMemberId(projectTeamMember.getId());
                    obj.setCity(e);
                    
                    projectTeamMemberSelfcityMapper.insertSelective(obj);
                }
            }
            //团队成员所在城市
            ProjectTeamMemberCity projectTeamMemberCity = new ProjectTeamMemberCity();
            projectTeamMemberCity.setMemberId(projectTeamMember.getId());
            projectTeamMemberCityService.delete(projectTeamMemberCity);
            
            if(body.getCitys() != null && body.getCitys().size() != 0){
                for(String e : body.getCitys()){
                    ProjectTeamMemberCity obj = new ProjectTeamMemberCity();
                    obj.setMemberId(projectTeamMember.getId());
                    obj.setCityName(e);
                    
                    projectTeamMemberCityMapper.insertSelective(obj);
                }
            }
            
            //团队成员投资阶段
            ProjectTeamMemberStage projectTeamMemberStage = new ProjectTeamMemberStage();
            projectTeamMemberStage.setMemberId(projectTeamMember.getId());
            projectTeamMemberStageService.delete(projectTeamMemberStage);
            
            if(body.getInvestStages() != null && body.getInvestStages().size() != 0 ){
                for(Integer e : body.getInvestStages()){
                    ProjectTeamMemberStage obj = new ProjectTeamMemberStage();
                    obj.setMemberId(projectTeamMember.getId());
                    obj.setStageId(e);
                    
                    projectTeamMemberStageMapper.insertSelective(obj);
                }
            }
            
            //教育经历
            ProjectTeamMemberEducation projectTeamMemberEducation = new ProjectTeamMemberEducation();
            projectTeamMemberEducation.setProjectTeamMemberId(projectTeamMember.getId());
            projectTeamMemberEducationService.delete(projectTeamMemberEducation);
            
            if(body.getEducationExperience() != null && body.getEducationExperience().size() != 0){
                for(String e : body.getEducationExperience()){
                    ProjectTeamMemberEducation obj = new ProjectTeamMemberEducation();
                    obj.setProjectTeamMemberId(projectTeamMember.getId());
                    obj.setEducationExperience(e);
                    
                    projectTeamMemberEducationMapper.insertSelective(obj);
                }
            }
            
            //工作经历
            ProjectTeamMemberWork projectTeamMemberWork = new ProjectTeamMemberWork();
            projectTeamMemberWork.setProjectTeamMemberId(projectTeamMember.getId());
            projectTeamMemberWorkService.delete(projectTeamMemberWork);
            
            if(body.getWorkExperiences() != null && body.getWorkExperiences().size() != 0){
                for(String e : body.getWorkExperiences()){
                    ProjectTeamMemberWork obj = new ProjectTeamMemberWork();
                    obj.setProjectTeamMemberId(projectTeamMember.getId());
                    obj.setWorkExperience(e);
                    
                    projectTeamMemberWorkMapper.insertSelective(obj);
                }
            }
            
            //领域
            ProjectTeamMemberSegmentation projectTeamMemberSegmentation = new ProjectTeamMemberSegmentation();
            projectTeamMemberSegmentation.setMemberId(projectTeamMember.getId());
            projectTeamMemberSegmentationService.delete(projectTeamMemberSegmentation);
            
            if(body.getFocusDomain() != null && body.getFocusDomain().size() != 0){
                for(Integer e : body.getFocusDomain()){
                    ProjectTeamMemberSegmentation obj = new ProjectTeamMemberSegmentation();
                    obj.setMemberId(projectTeamMember.getId());
                    obj.setSegmentationId(e);
                    
                    projectTeamMemberSegmentationMapper.insertSelective(obj);
                }
            }
            
            //自定义团队
            ProjectTeamMemberSelfteam projectTeamMemberSelfteam = new ProjectTeamMemberSelfteam();
            projectTeamMemberSelfteam.setMemberId(projectTeamMember.getId());
            projectTeamMemberSelfteamService.delete(projectTeamMemberSelfteam);
            //一个团队成员只属于一个自定义团队
            if(body.getSelfDefTeam() != null && body.getSelfDefTeam().toString() != ""){
            	
            	ProjectTeamMemberSelfteam obj = new ProjectTeamMemberSelfteam();
                obj.setMemberId(projectTeamMember.getId());
                obj.setTeamName(body.getSelfDefTeam());
                
                projectTeamMemberSelfteamMapper.insertSelective(obj);                
                
            }
        }else if(Integer.valueOf(2).equals(body.getSubjectType())) {//机构
        	
        	InvestmentInstitutionTeam team=new InvestmentInstitutionTeam();
        	team.setId(body.getMemberId());
            //设置关联的主体id
        	team.setInvestmentInstitutionId(body.getProjectId());
            team.setWeight( body.getWeight());
            team.setActualName(body.getName());
            team.setCompanyDuties(body.getJobTitle());
            team.setMemberDesc(body.getMemberDesc());
            team.setPhonenumber(body.getPhone());
            team.setWorkYn(body.getIsOnJob());
            team.setPicture(body.getPicture());
            team.setEmail(body.getEmail());

            team.setWechat(body.getWeiChat());
            team.setHideYn(body.getIsHide());
            team.setMetaIitTypeId(body.getTeamId());
            team.setCreateTime(new Date());
            
            if(null == body.getMemberId()){
            	investmentInstitutionTeamMapper.insertSelective(team);
            }else{
            	investmentInstitutionTeamMapper.updateByPrimaryKey(team);
            }
            
            //教育经历
            InvestmentInstitutionsMemberEducation edu = new InvestmentInstitutionsMemberEducation();
            edu.setMemberId(team.getId());
            investmentInstitutionsMemberEducationMapper.delete(edu);
            
            if(body.getEducationExperience() != null && body.getEducationExperience().size() != 0){
                for(String e : body.getEducationExperience()){
                	InvestmentInstitutionsMemberEducation obj = new InvestmentInstitutionsMemberEducation();
                    obj.setMemberId(team.getId());
                    obj.setEducationExperience(e);
                    
                    investmentInstitutionsMemberEducationMapper.insertSelective(obj);
                }
            }
            
            //工作经历
            InvestmentInstitutionsMemberWork work = new InvestmentInstitutionsMemberWork();
            work.setMemberId(team.getId());
            investmentInstitutionsMemberWorkMapper.delete(work);
            
            if(body.getWorkExperiences() != null && body.getWorkExperiences().size() != 0){
                for(String e : body.getWorkExperiences()){
                	InvestmentInstitutionsMemberWork obj = new InvestmentInstitutionsMemberWork();
                	obj.setMemberId(team.getId());
                    obj.setWorkExperience(e);
                    
                    investmentInstitutionsMemberWorkMapper.insertSelective(obj);
                }
            }
        }
        
        result.setMessage("success");
        result.setStatus(200);
        result.setData("更新成功");
        return result;
    }

    @Override
    public CommonDto<String> deleteProjectTeamMember(Integer memberId,Integer subjectType) {
        CommonDto<String> result = new CommonDto<>();
        
        if(Integer.valueOf(1).equals(subjectType)) {//项目
            if(projectTeamMemberMapper.existsWithPrimaryKey(memberId)){
            	ProjectTeamMember mem=new ProjectTeamMember();
            	mem.setId(memberId);
            	mem.setYn(1);
            	
                projectTeamMemberMapper.updateByPrimaryKey(mem);
            }
        }else if(Integer.valueOf(2).equals(subjectType)) {//机构
        	if(investmentInstitutionTeamMapper.existsWithPrimaryKey(memberId)){
        		InvestmentInstitutionTeam mem=new InvestmentInstitutionTeam();
        		mem.setId(memberId);
        		//TODO 删除机构的团队成员,机构团队成员表需要添加标志位
        		
//            	investmentInstitutionTeamMapper.updateByPrimaryKey(mem);
            }
        }
        
        result.setMessage("success");
        result.setStatus(200);
        result.setData("删除成功");
        return result;
    }

    /**
     * 自定义城市智能检索
     * @param searchWord
     * @return
     */
    @Override
    public CommonDto<List<String>> cityElegantSearch(String searchWord) {
        CommonDto<List<String>> result = new CommonDto<>();
        if(searchWord == null || searchWord == ""){
            result.setData(null);
            result.setStatus(200);
            result.setMessage("success");
            return result;
        }
        List<String> cityNames = new ArrayList<>();
        List<MetaRegion> metaRegionList = metaRegionMapper.selectByCityName(searchWord);
        metaRegionList.forEach( metaRegion -> {
            cityNames.add(metaRegion.getMing());
        });
        result.setData(cityNames);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    /**
     * 用户智能检索
     * @param searchWord
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> userElegantSearch(String searchWord) {
        CommonDto<List<Map<String, Object>>> result = new CommonDto<>();
        List<Map<String, Object>> list = new ArrayList<>();
        if(searchWord == null || searchWord == ""){
           result.setMessage("success");
           result.setData(null);
           result.setStatus(200);
           return result;
        }
        List<Map<String, Object>> usersList = usersMapper.selectByName(searchWord);

        result.setData(usersList);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }
}
