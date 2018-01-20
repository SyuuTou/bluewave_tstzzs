package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto.ProjectTeamMemberInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto.ProjectTeamMemberOutputDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.*;
import com.lhjl.tzzs.proxy.utils.DateUtils;
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
    private ProjectTeamMemberBusinessService projectTeamMemberBusinessService;

    @Autowired
    private MetaFinancingMapper financingMapper;

    @Autowired
    private MetaRegionMapper metaRegionMapper;

    @Autowired
    private UsersMapper usersMapper;

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
    public CommonDto<ProjectTeamMemberOutputDto> getProjectTeamMemberList(Integer projectId) {
        CommonDto<ProjectTeamMemberOutputDto> result = new CommonDto<>();
        ProjectTeamMemberOutputDto projectTeamMemberOutputDto = new ProjectTeamMemberOutputDto();

        List<ProjectTeamMemberOutputDto.ProjectTeamMemberDetailDto> projectTeamMemberDetailDtos = new ArrayList<>();

        Projects projects = new Projects();
        projects.setId(projectId);

        Projects projects1 = projectsMapper.selectByPrimaryKey(projects);
        List<ProjectTeamMember> projectTeamMembers = new ArrayList<>();
        if(null != projects1) {
            projectTeamMemberOutputDto.setTeamDesc(projects1.getCommet());

            projectTeamMembers = projectTeamMemberMapper.getProjectMemberList(projectId);

            if (null != projectTeamMembers && projectTeamMembers.size() != 0) {
                for(ProjectTeamMember projectTeamMember_i: projectTeamMembers){
                    ProjectTeamMemberOutputDto.ProjectTeamMemberDetailDto projectTeamMemberDetailDto = new ProjectTeamMemberOutputDto.ProjectTeamMemberDetailDto();
                    projectTeamMemberDetailDto.setMemberId(projectTeamMember_i.getId());
                    projectTeamMemberDetailDto.setWeight(projectTeamMember_i.getWeight());
                    projectTeamMemberDetailDto.setName(projectTeamMember_i.getMumberName());
                    projectTeamMemberDetailDto.setJobTitle(projectTeamMember_i.getMumberDuties());
                    projectTeamMemberDetailDto.setMemberDesc(projectTeamMember_i.getMumberDesc());
                    projectTeamMemberDetailDto.setPhone(projectTeamMember_i.getPhone());

                    //获取每个成员的工作经历
                    ProjectTeamMemberWork projectTeamMemberWork = new ProjectTeamMemberWork();
                    projectTeamMemberWork.setId(projectTeamMember_i.getId());
                    List<ProjectTeamMemberWork> projectTeamMemberWorks = projectTeamMemberWorkService.select(projectTeamMemberWork);
                    String[] projectTeamMemberWorkArr = null;
                    List<String> projectTeamMemberWorkList = new ArrayList<>();
                    if(null == projectTeamMemberWorks || projectTeamMemberWorks.size() == 0){
                        projectTeamMemberDetailDto.setWorkExperiences(null);
                    }else{
                        projectTeamMemberWorks.forEach(projectTeamMemberWork_i -> {
                            projectTeamMemberWorkList.add(projectTeamMemberWork_i.getWorkExperience());
                        });
                        projectTeamMemberWorkArr = new String[projectTeamMemberWorks.size()];
                        projectTeamMemberWorkList.toArray(projectTeamMemberWorkArr);
                        projectTeamMemberDetailDto.setWorkExperiences(projectTeamMemberWorkArr);
                    }



                    ProjectTeamMemberEducation projectTeamMemberEducation = new ProjectTeamMemberEducation();
                    projectTeamMemberEducation.setId(projectTeamMember_i.getId());
                    List<ProjectTeamMemberEducation> projectTeamMemberEducations = projectTeamMemberEducationService.select(projectTeamMemberEducation);
                    List<String> projectTeamMemberEducationList = new ArrayList<>();
                    String[] projectTeamMemberEducationArr = null;
                    if(null == projectTeamMemberEducations || projectTeamMemberEducations.size() == 0){
                        projectTeamMemberDetailDto.setEducationExperience(projectTeamMemberEducationArr);
                    }else{
                        projectTeamMemberEducations.forEach(projectTeamMemberEducation_i -> {
                            projectTeamMemberEducationList.add(projectTeamMemberEducation_i.getEducationExperience());
                        });
                        projectTeamMemberEducationArr = new String[projectTeamMemberEducations.size()];
                        projectTeamMemberEducationList.toArray(projectTeamMemberEducationArr);
                        projectTeamMemberDetailDto.setEducationExperience(projectTeamMemberEducationArr);
                    }

                    projectTeamMemberDetailDto.setIsOnJob(projectTeamMember_i.getIsonjob());
                    projectTeamMemberDetailDto.setHeadPicture(projectTeamMember_i.getHeadPicture());
                    projectTeamMemberDetailDto.setPicture(projectTeamMember_i.getPicture());
                    projectTeamMemberDetailDto.setEmail(projectTeamMember_i.getEmail());
                    projectTeamMemberDetailDto.setWeiChat(projectTeamMember_i.getWeichat());
                    projectTeamMemberDetailDto.setTeamId(projectTeamMember_i.getTeamId());

                    ProjectTeamMemberSelfteam projectTeamMemberSelfteam_i = new ProjectTeamMemberSelfteam();
                    projectTeamMemberSelfteam_i.setMemberId(projectTeamMember_i.getId());
                    List<ProjectTeamMemberSelfteam> projectTeamMemberSelfteamList = projectTeamMemberSelfteamService.select(projectTeamMemberSelfteam_i);
                    if(projectTeamMemberSelfteamList == null || projectTeamMemberSelfteamList.size() == 0){
                        projectTeamMemberDetailDto.setSelfDefTeam("");
                    }else{
                        projectTeamMemberDetailDto.setSelfDefTeam(projectTeamMemberSelfteamList.get(0).getTeamName());
                    }

                    ProjectTeamMemberSegmentation projectTeamMemberSegmentation = new ProjectTeamMemberSegmentation();
                    projectTeamMemberSegmentation.setMemberId(projectTeamMember_i.getId());
                    List<ProjectTeamMemberSegmentation> projectTeamMemberSegmentationList = projectTeamMemberSegmentationService.select(projectTeamMemberSegmentation);
                    List<Integer> projectTeamMemberSegmentations = new ArrayList<>();
                    Integer[] projectTeamMemberSegmentationArr = null;
                    if(projectTeamMemberSegmentationList == null || projectTeamMemberSegmentationList.size() == 0){
                        projectTeamMemberDetailDto.setFocusDomain(projectTeamMemberSegmentationArr);
                    }else{
                        projectTeamMemberSegmentationList.forEach(projectTeamMemberSegmentation_i -> {
                            projectTeamMemberSegmentations.add(projectTeamMemberSegmentation_i.getSegmentationId());
                        });
                        projectTeamMemberSegmentationArr = new Integer[projectTeamMemberSegmentationList.size()];
                        projectTeamMemberSegmentations.toArray(projectTeamMemberSegmentationArr);
                        projectTeamMemberDetailDto.setFocusDomain(projectTeamMemberSegmentationArr);
                    }

                    ProjectTeamMemberStage projectTeamMemberStage = new ProjectTeamMemberStage();
                    projectTeamMemberStage.setMemberId(projectTeamMember_i.getId());
                    List<ProjectTeamMemberStage> projectTeamMemberStageList = projectTeamMemberStageService.select(projectTeamMemberStage);
                    List<Integer> projectTeamMemberStages = new ArrayList<>();
                    Integer[] projectTeamMemberStageArr = null;
                    if(null == projectTeamMemberStageList || projectTeamMemberStageList.size() == 0){
                        projectTeamMemberDetailDto.setInvestStages(projectTeamMemberStageArr);
                    }else{
                        projectTeamMemberStageList.forEach(projectTeamMemberStage_i -> {
                            projectTeamMemberStages.add(projectTeamMemberStage_i.getStageId());
                        });
                        projectTeamMemberStageArr = new Integer[projectTeamMemberStageList.size()];
                        projectTeamMemberStages.toArray(projectTeamMemberStageArr);
                        projectTeamMemberDetailDto.setInvestStages(projectTeamMemberStageArr);
                    }
                    projectTeamMemberDetailDto.setStockPer(projectTeamMember_i.getShareRatio());

                    ProjectTeamMemberCity projectTeamMemberCity = new ProjectTeamMemberCity();
                    projectTeamMemberCity.setMemberId(projectTeamMember_i.getId());
                    List<ProjectTeamMemberCity> projectTeamMemberCityList = projectTeamMemberCityService.select(projectTeamMemberCity);
                    List<String> projectTeamMemberCitys = new ArrayList<>();
                    String[] projectTeamMemberCityArr = null;
                    if (projectTeamMemberCityList == null || projectTeamMemberCityList.size() == 0){
                        projectTeamMemberDetailDto.setCitys(projectTeamMemberCityArr);
                    }else{
                        projectTeamMemberCityList.forEach(projectTeamMemberCity_i -> {
                            projectTeamMemberCitys.add(projectTeamMemberCity_i.getCityName());
                        });
                        projectTeamMemberCityArr = new String[projectTeamMemberCityList.size()];
                        projectTeamMemberDetailDto.setCitys(projectTeamMemberCityArr);
                    }


                    ProjectTeamMemberSelfcity projectTeamMemberSelfcity = new ProjectTeamMemberSelfcity();
                    projectTeamMemberSelfcity.setMemberId(projectTeamMember_i.getId());
                    List<ProjectTeamMemberSelfcity> projectTeamMemberSelfcityList = projectTeamMemberSelfcityMapper.select(projectTeamMemberSelfcity);
                    List<String> projectTeamMemberSelfcitys = new ArrayList<>();
                    String[] projectTeamMemberSelfcityArr = null;
                    if (null == projectTeamMemberSelfcityList || projectTeamMemberSelfcityList.size() == 0){
                        projectTeamMemberDetailDto.setSelfcitys(projectTeamMemberSelfcityArr);
                    }else{
                        projectTeamMemberSelfcityList.forEach(projectTeamMemberSelfcity_i -> {
                            projectTeamMemberSelfcitys.add(projectTeamMemberSelfcity_i.getCity());
                        });
                        projectTeamMemberSelfcityArr = new String[projectTeamMemberSelfcityList.size()];
                        projectTeamMemberSelfcitys.toArray(projectTeamMemberSelfcityArr);
                        projectTeamMemberDetailDto.setSelfcitys(projectTeamMemberSelfcityArr);
                    }

                    ProjectTeamMemberBusiness projectTeamMemberBusiness = new ProjectTeamMemberBusiness();
                    projectTeamMemberBusiness.setMemberId(projectTeamMember_i.getId());
                    List<ProjectTeamMemberBusiness> projectTeamMemberBusinessList = projectTeamMemberBusinessService.select(projectTeamMemberBusiness);
                    List<String> projectTeamMemberBusinesss = new ArrayList<>();
                    String[] projectTeamMemberBusinessArr = null;
                    if (projectTeamMemberBusinessList == null || projectTeamMemberBusinessList.size() ==0){
                        projectTeamMemberDetailDto.setBusinesses(projectTeamMemberBusinessArr);
                    }else{
                        projectTeamMemberBusinessList.forEach(projectTeamMemberBusiness_i -> {
                            projectTeamMemberBusinesss.add(projectTeamMemberBusiness_i.getCompanyName());
                        });
                        projectTeamMemberBusinessArr = new String[projectTeamMemberBusinessList.size()];
                        projectTeamMemberBusinesss.toArray(projectTeamMemberBusinessArr);
                        projectTeamMemberDetailDto.setBusinesses(projectTeamMemberBusinessArr);
                    }

                    projectTeamMemberDetailDto.setBusinessDesc(projectTeamMember_i.getBusinessExperienceDesc());
                    projectTeamMemberDetailDto.setWorkDesc(projectTeamMember_i.getWorkExperienceDesc());
                    projectTeamMemberDetailDto.setEducationDesc(projectTeamMember_i.getEducationExperienceDesc());
                    projectTeamMemberDetailDto.setIsHide(projectTeamMember_i.getIshide());
                    projectTeamMemberDetailDtos.add(projectTeamMemberDetailDto);
                };
            }
            projectTeamMemberOutputDto.setProjectTeamMemberDetailDtoList(projectTeamMemberDetailDtos);
        }

        result.setData(projectTeamMemberOutputDto);
        result.setMessage("success");
        result.setStatus(200);
        return result;
    }



    @Override
    public CommonDto<String> addOrUpdatePojectTeamMember(Integer projectId, ProjectTeamMemberInputDto body) {
        CommonDto<String> result = new CommonDto<>();
        if(null == body || body.toString() == ""){
            result.setMessage("success");
            result.setStatus(200);
            result.setData("请输入成员相关信息");
            return result;
        }

        Integer projectInsertResult = -1;

        ProjectTeamMember projectTeamMember = new ProjectTeamMember();
        projectTeamMember.setProjectId(projectId);
        projectTeamMember.setId(body.getMemberId());
        projectTeamMember.setWeight( body.getWeight());
        projectTeamMember.setMumberName(body.getName());
        projectTeamMember.setMumberDuties(body.getJobTitle());
        projectTeamMember.setMumberDesc(body.getMemberDesc());
        projectTeamMember.setPhone(body.getPhone());
        projectTeamMember.setIsonjob(body.getIsOnJob());
        projectTeamMember.setHeadPicture(body.getHeadPicture());
        projectTeamMember.setPicture(body.getPicture());
        projectTeamMember.setEmail(body.getEmail());
        projectTeamMember.setShareRatio(body.getStockPer());
        projectTeamMember.setWeichat(body.getWeiChat());
        projectTeamMember.setIshide(body.getIsHide());
        projectTeamMember.setWorkExperienceDesc(body.getWorkDesc());
        projectTeamMember.setEducationExperienceDesc(body.getEducationDesc());
        projectTeamMember.setBusinessExperienceDesc(body.getBusinessDesc());
        projectTeamMember.setTeamId(body.getTeamId());
        projectTeamMember.setYn(0);

        long now = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = null;
        try {
            createTime = sdf.format(new Date(now));
        } catch (Exception e) {
            e.printStackTrace();
        }
        projectTeamMember.setCreateTime(DateUtils.parse(createTime));

        Integer projectTeamMemberInsertOrUpdateResult = -1;
        Integer projectTeamMemberBusinessInsertResult = -1;
        Integer projectTeamMemberWorkInsertResult = -1;
        Integer projectTeamMemberEducationInsertResult = -1;
        Integer projectTeamMemberCitysInsertResult = -1;
        Integer projectTeamMemberSelfCitysInsertResult = -1;
        Integer projectTeamMemberSelfTeamInsertResult = -1;
        Integer projectTeamMemberSegmentationInsertResult = -1;
        Integer projectTeamMemberStageInsertResult = -1;

        if(null == body.getMemberId()){
            projectTeamMemberInsertOrUpdateResult = projectTeamMemberMapper.insert(projectTeamMember);
        }else{
            projectTeamMemberInsertOrUpdateResult = projectTeamMemberMapper.updateByPrimaryKey(projectTeamMember);
        }

        ProjectTeamMemberBusiness projectTeamMemberBusiness = new ProjectTeamMemberBusiness();
        projectTeamMemberBusiness.setMemberId(projectTeamMember.getId());
        projectTeamMemberBusinessService.delete(projectTeamMemberBusiness);
        List<ProjectTeamMemberBusiness> projectTeamMemberBusinessList = new ArrayList<>();
        if(body.getBusinesses().length == 0 || body.getBusinesses() == null){
            ProjectTeamMemberBusiness projectTeamMemberBusiness1 = new ProjectTeamMemberBusiness();
            projectTeamMemberBusiness1.setMemberId(projectTeamMember.getId());
            projectTeamMemberBusiness1.setCompanyName("");
            projectTeamMemberBusinessList.add(projectTeamMemberBusiness1);
        }else{
            for(int i = 0; i<body.getBusinesses().length; i++){
                ProjectTeamMemberBusiness projectTeamMemberBusiness1 = new ProjectTeamMemberBusiness();
                projectTeamMemberBusiness1.setMemberId(projectTeamMember.getId());
                projectTeamMemberBusiness1.setCompanyName(body.getBusinesses()[i]);
                projectTeamMemberBusinessList.add(projectTeamMemberBusiness1);
            }
        }
        projectTeamMemberBusinessInsertResult = projectTeamMemberBusinessService.insertList(projectTeamMemberBusinessList);

        ProjectTeamMemberSelfcity projectTeamMemberSelfcity = new ProjectTeamMemberSelfcity();
        projectTeamMemberSelfcity.setMemberId(projectTeamMember.getId());
        projectTeamMemberSelfcityMapper.delete(projectTeamMemberSelfcity);
        List<ProjectTeamMemberSelfcity> projectTeamMemberSelfcityList = new ArrayList<>();
        if(body.getSelfcitys().length == 0 || body.getSelfcitys() == null){
            ProjectTeamMemberSelfcity projectTeamMemberSelfcity1 = new ProjectTeamMemberSelfcity();
            projectTeamMemberSelfcity1.setMemberId(projectTeamMember.getId());
            projectTeamMemberSelfcity1.setCity("");
            projectTeamMemberSelfcityList.add(projectTeamMemberSelfcity1);
        }else{
            for(int i = 0; i < body.getSelfcitys().length; i++){
                ProjectTeamMemberSelfcity projectTeamMemberSelfcity1 = new ProjectTeamMemberSelfcity();
                projectTeamMemberSelfcity1.setMemberId(projectTeamMember.getId());
                projectTeamMemberSelfcity1.setCity(body.getBusinesses()[i]);
                projectTeamMemberSelfcityList.add(projectTeamMemberSelfcity1);
            }
        }
        projectTeamMemberSelfCitysInsertResult = projectTeamMemberSelfcityMapper.insertList(projectTeamMemberSelfcityList);

        ProjectTeamMemberCity projectTeamMemberCity = new ProjectTeamMemberCity();
        projectTeamMemberCity.setMemberId(projectTeamMember.getId());
        projectTeamMemberCityService.delete(projectTeamMemberCity);
        List<ProjectTeamMemberCity> projectTeamMemberCityList = new ArrayList<>();
        if(body.getCitys().length == 0 || body.getCitys() == null){
            ProjectTeamMemberCity projectTeamMemberCity1 = new ProjectTeamMemberCity();
            projectTeamMemberCity1.setMemberId(projectTeamMember.getId());
            projectTeamMemberCity1.setCityName("");
            projectTeamMemberCityList.add(projectTeamMemberCity1);
        }else{
            for(int i = 0; i < body.getCitys().length; i++){
                ProjectTeamMemberCity projectTeamMemberCity1 = new ProjectTeamMemberCity();
                projectTeamMemberCity1.setMemberId(projectTeamMember.getId());
                projectTeamMemberCity1.setCityName(body.getCitys()[i]);
                projectTeamMemberCityList.add(projectTeamMemberCity1);
            }
        }
        projectTeamMemberCitysInsertResult = projectTeamMemberCityService.insertList(projectTeamMemberCityList);

        ProjectTeamMemberStage projectTeamMemberStage = new ProjectTeamMemberStage();
        projectTeamMemberStage.setMemberId(projectTeamMember.getId());
        projectTeamMemberStageService.delete(projectTeamMemberStage);
        List<ProjectTeamMemberStage> projectTeamMemberStageList = new ArrayList<>();
        if(body.getInvestStages().length == 0 || body.getInvestStages() == null){
            ProjectTeamMemberStage projectTeamMemberStage1 = new ProjectTeamMemberStage();
            projectTeamMemberStage1.setMemberId(projectTeamMember.getId());
            projectTeamMemberStage1.setStageId(null);
            projectTeamMemberStageList.add(projectTeamMemberStage1);
        }else{
            for(int i = 0; i < body.getInvestStages().length; i++){
                ProjectTeamMemberStage projectTeamMemberStage1 = new ProjectTeamMemberStage();
                projectTeamMemberStage1.setMemberId(projectTeamMember.getId());
                projectTeamMemberStage1.setStageId(body.getInvestStages()[i]);
                projectTeamMemberStageList.add(projectTeamMemberStage1);
            }
        }
        projectTeamMemberStageInsertResult = projectTeamMemberStageService.insertList(projectTeamMemberStageList);

        ProjectTeamMemberEducation projectTeamMemberEducation = new ProjectTeamMemberEducation();
        projectTeamMemberEducation.setProjectTeamMemberId(projectTeamMember.getId());
        projectTeamMemberEducationService.delete(projectTeamMemberEducation);
        List<ProjectTeamMemberEducation> projectTeamMemberEducationList = new ArrayList<>();
        if(body.getEducationExperience().length == 0 || body.getEducationExperience() == null){
            ProjectTeamMemberEducation projectTeamMemberEducation1 = new ProjectTeamMemberEducation();
            projectTeamMemberEducation1.setProjectTeamMemberId(projectTeamMember.getId());
            projectTeamMemberEducation1.setEducationExperience("");
            projectTeamMemberEducationList.add(projectTeamMemberEducation1);
        }else{
            for(int i = 0; i < body.getInvestStages().length; i++){
                ProjectTeamMemberEducation projectTeamMemberEducation1 = new ProjectTeamMemberEducation();
                projectTeamMemberEducation1.setProjectTeamMemberId(projectTeamMember.getId());
                projectTeamMemberEducation1.setEducationExperience(body.getEducationExperience()[i]);
                projectTeamMemberEducationList.add(projectTeamMemberEducation1);
            }
        }
        projectTeamMemberEducationInsertResult = projectTeamMemberEducationService.insertList(projectTeamMemberEducationList);

        ProjectTeamMemberWork projectTeamMemberWork = new ProjectTeamMemberWork();
        projectTeamMemberWork.setProjectTeamMemberId(projectTeamMember.getId());
        projectTeamMemberWorkService.delete(projectTeamMemberWork);
        List<ProjectTeamMemberWork> projectTeamMemberWorkList = new ArrayList<>();
        if(body.getWorkExperiences().length == 0 || body.getWorkExperiences() == null){
            ProjectTeamMemberWork projectTeamMemberWork1 = new ProjectTeamMemberWork();
            projectTeamMemberWork1.setProjectTeamMemberId(projectTeamMember.getId());
            projectTeamMemberWork1.setWorkExperience("");
            projectTeamMemberWorkList.add(projectTeamMemberWork1);
        }else{
            for(int i = 0; i < body.getWorkExperiences().length; i++){
                ProjectTeamMemberWork projectTeamMemberWork1 = new ProjectTeamMemberWork();
                projectTeamMemberWork1.setProjectTeamMemberId(projectTeamMember.getId());
                projectTeamMemberWork1.setWorkExperience(body.getWorkExperiences()[i]);
                projectTeamMemberWorkList.add(projectTeamMemberWork1);
            }
        }
        projectTeamMemberWorkInsertResult = projectTeamMemberWorkService.insertList(projectTeamMemberWorkList);

        ProjectTeamMemberSegmentation projectTeamMemberSegmentation = new ProjectTeamMemberSegmentation();
        projectTeamMemberSegmentation.setMemberId(projectTeamMember.getId());
        List<ProjectTeamMemberSegmentation> projectTeamMemberSegmentationList = new ArrayList<>();
        projectTeamMemberSegmentationService.delete(projectTeamMemberSegmentation);
        if(body.getFocusDomain().length == 0 || body.getFocusDomain() == null){
            ProjectTeamMemberSegmentation projectTeamMemberSegmentation1 = new ProjectTeamMemberSegmentation();
            projectTeamMemberSegmentation1.setMemberId(projectTeamMember.getId());
            projectTeamMemberSegmentation1.setSegmentationId(null);
            projectTeamMemberSegmentationList.add(projectTeamMemberSegmentation1);
        }else{
            for(int i = 0; i < body.getFocusDomain().length; i++){
                ProjectTeamMemberSegmentation projectTeamMemberSegmentation1 = new ProjectTeamMemberSegmentation();
                projectTeamMemberSegmentation1.setMemberId(projectTeamMember.getId());
                projectTeamMemberSegmentation1.setSegmentationId(body.getFocusDomain()[i]);
                projectTeamMemberSegmentationList.add(projectTeamMemberSegmentation1);
            }
        }
        projectTeamMemberSegmentationInsertResult = projectTeamMemberSegmentationService.insertList(projectTeamMemberSegmentationList);

        ProjectTeamMemberSelfteam projectTeamMemberSelfteam = new ProjectTeamMemberSelfteam();
        projectTeamMemberSelfteam.setMemberId(projectTeamMember.getId());
        projectTeamMemberSelfteamService.delete(projectTeamMemberSelfteam);
        List<ProjectTeamMemberSelfteam> projectTeamMemberSelfteamsList = new ArrayList<>();
        if(body.getSelfDefTeam() == null || body.getSelfDefTeam().toString() == ""){
            ProjectTeamMemberSelfteam projectTeamMemberSelfteam1 = new ProjectTeamMemberSelfteam();
            projectTeamMemberSelfteam1.setMemberId(projectTeamMember.getId());
            projectTeamMemberSelfteam1.setTeamName("");
            projectTeamMemberSelfteamsList.add(projectTeamMemberSelfteam1);
        }else{
            ProjectTeamMemberSelfteam projectTeamMemberSelfteam1 = new ProjectTeamMemberSelfteam();
            projectTeamMemberSelfteam1.setMemberId(projectTeamMember.getId());
            projectTeamMemberSelfteam1.setTeamName(body.getSelfDefTeam());
            projectTeamMemberSelfteamsList.add(projectTeamMemberSelfteam1);
        }
        projectTeamMemberSelfTeamInsertResult = projectTeamMemberSelfteamService.insertList(projectTeamMemberSelfteamsList);

        result.setMessage("success");
        result.setStatus(200);
        result.setData("保存成功");
        return result;
    }

    @Override
    public CommonDto<String> deleteProjectTeamMember(Integer memberId) {
        CommonDto<String> result = new CommonDto<>();
        ProjectTeamMember projectTeamMember1 = projectTeamMemberMapper.selectByPrimaryKey(memberId);
        if(null == projectTeamMember1){
            result.setMessage("failed");
            result.setStatus(200);
            result.setData("删除失败");
            return result;
        }
        projectTeamMember1.setYn(1);
        Integer projectTeamMemberInsertResult = projectTeamMemberMapper.updateByPrimaryKey(projectTeamMember1);
        if(projectTeamMemberInsertResult > 0) {
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
