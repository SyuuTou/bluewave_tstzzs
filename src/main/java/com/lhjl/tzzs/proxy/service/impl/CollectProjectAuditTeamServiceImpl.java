package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditTeamDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ProjectSendAuditBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamIntroductionBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamMemberBusinessBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamMemberCityBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamMemberEducationBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamMemberSegmentationBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamMemberSelfcityBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamMemberSelfteamBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamMemberStageBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamMemberWorkBMapper;
import com.lhjl.tzzs.proxy.model.ProjectSendAuditB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamIntroductionB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberBusinessB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberCityB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberEducationB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberSegmentationB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberSelfcityB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberSelfteamB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberStageB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberWorkB;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@Service
public class CollectProjectAuditTeamServiceImpl implements CollectProjectAuditTeamService {

    @Autowired
    private ProjectSendTeamBMapper projectSendTeamBMapper;

    @Autowired
    private ProjectSendAuditBMapper projectSendAuditBMapper;
    @Autowired
    private ProjectSendTeamIntroductionBMapper projectSendTeamIntroductionBMapper;
    @Autowired
    private ProjectSendTeamMemberEducationBMapper projectSendTeamMemberEducationBMapper;
    @Autowired
    private ProjectSendTeamMemberWorkBMapper projectSendTeamMemberWorkBMapper;
    
  //关注领域
    @Autowired
    private ProjectSendTeamMemberSegmentationBMapper projectSendTeamMemberSegmentationBMapper;
    //投资阶段
    @Autowired
    private ProjectSendTeamMemberStageBMapper projectSendTeamMemberStageBMapper;
    //所在城市
    @Autowired
    private ProjectSendTeamMemberCityBMapper projectSendTeamMemberCityBMapper;
    //自定义城市
    @Autowired
    private ProjectSendTeamMemberSelfcityBMapper projectSendTeamMemberSelfcityBMapper;
    //创业经历
    @Autowired
    private ProjectSendTeamMemberBusinessBMapper projectSendTeamMemberBusinessBMapper;
    
    @Autowired
    private ProjectSendTeamMemberSelfteamBMapper projectSendTeamMemberSelfteamBMapper;
    
    
    @Override
    public CommonDto<CollectProjectAuditTeamDto> getCollectProjectAuditTeam(Integer projectId) {

        CommonDto<CollectProjectAuditTeamDto> result = new CommonDto<>();

        CollectProjectAuditTeamDto collectProjectAuditTeamDto = new CollectProjectAuditTeamDto();

        List<CollectProjectAuditTeamDto.CollectProjectAuditMemberDto> collectProjectAuditMemberDtoList = new ArrayList<>();

        ProjectSendAuditB projectSendAuditB1 = projectSendAuditBMapper.selectByPrimaryKey(projectId);

        if(null == projectSendAuditB1){
            result.setStatus(500);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }
        
        //设置提交项目的团队介绍
        ProjectSendTeamIntroductionB projectSendTeamIntroductionB = projectSendTeamIntroductionBMapper.selectByPrimaryKey(projectSendAuditB1.getProjectSendBId());
        
        collectProjectAuditTeamDto.setProjectId(projectId);
        if(projectSendTeamIntroductionB != null) {
        	collectProjectAuditTeamDto.setTeamIntroduction(projectSendTeamIntroductionB.getTeamIntroduction());
        }
        
        //取得提交项目团队成员的列表
        List<ProjectSendTeamB> projectSendTeamBList = projectSendTeamBMapper.selectByProjectId(projectSendAuditB1.getProjectSendBId());
        //将每一个项目团队成员转换为DTO
        if(null != projectSendTeamBList && projectSendTeamBList.size() != 0){
            for(ProjectSendTeamB projectSendTeamB_i : projectSendTeamBList){
            	Integer teamMemberId = projectSendTeamB_i.getId();
            	//设置采集项目的团队成员
                CollectProjectAuditTeamDto.CollectProjectAuditMemberDto collectProjectAuditMemberDto = new CollectProjectAuditTeamDto.CollectProjectAuditMemberDto();
                //团队成员id
                collectProjectAuditMemberDto.setMemberId(teamMemberId);
                //成员姓名
                collectProjectAuditMemberDto.setMemberName(projectSendTeamB_i.getMemberName());
                //成员职务
                collectProjectAuditMemberDto.setPosition(projectSendTeamB_i.getCompanyDuties());
                //股份占比
                collectProjectAuditMemberDto.setStockPer(projectSendTeamB_i.getStockRatio());
                //简介
                collectProjectAuditMemberDto.setKernelDesc(projectSendTeamB_i.getMemberDesc());
                
                //获取团队成员的教育经历
                ProjectSendTeamMemberEducationB educationQuery=new ProjectSendTeamMemberEducationB();
                educationQuery.setPsTeamBId(projectSendTeamB_i.getId());
                List<ProjectSendTeamMemberEducationB> educations = projectSendTeamMemberEducationBMapper.select(educationQuery);
                
                //获取团队成员的工作经历
                ProjectSendTeamMemberWorkB workQuery=new ProjectSendTeamMemberWorkB();
                workQuery.setPsTeamBId(projectSendTeamB_i.getId());
                List<ProjectSendTeamMemberWorkB> works = projectSendTeamMemberWorkBMapper.select(workQuery);
                //接受教育经历以及工作经历的字符串
                List<String> educationsStrs=new ArrayList<>();
                List<String> worksStrs=new ArrayList<>();
                
                if(educations!=null&&educations.size()!=0) {
                	educations.forEach((e)->{
                		educationsStrs.add(e.getEducationExperience());
                	});
                }
                
                if(works!=null&&works.size()!=0) {
                	works.forEach((e)->{
                		worksStrs.add(e.getWorkExperience());
                	});
                }
                
                //设置工作经历以及教育经历
                collectProjectAuditMemberDto.setWorkExperiences(worksStrs);
                collectProjectAuditMemberDto.setEducationExperience(educationsStrs);
                //设置在职状态
                collectProjectAuditMemberDto.setIsOnJob(projectSendTeamB_i.getJobStatus());
                
                //TODO 团队成员的权重逻辑是否需要进一步变更  
                //设置团队成员的权重,如果权重为null的话，默认设置该权重为0
                collectProjectAuditMemberDto.setWeight(projectSendTeamB_i.getWeight()==null ? 0 : projectSendTeamB_i.getWeight());
                /**
                 * 以上属于列表显示内容
                 * =========================================
                 * 以下属于团队成员详细信息内容
                 */
                //设置头像
                collectProjectAuditMemberDto.setHeadPicture(projectSendTeamB_i.getHeadPicture());
                //设置高清图片
                collectProjectAuditMemberDto.setPicture(projectSendTeamB_i.getPicture());
                collectProjectAuditMemberDto.setPhone(projectSendTeamB_i.getPhone());
                collectProjectAuditMemberDto.setEmail(projectSendTeamB_i.getEmail());
                collectProjectAuditMemberDto.setTeamId(projectSendTeamB_i.getTeamId());
                
                collectProjectAuditMemberDto.setWeiChat(projectSendTeamB_i.getWeichat());
                //设置出生年月
                if(projectSendTeamB_i.getBirthDay() !=null) {
                	collectProjectAuditMemberDto.setBirthDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(projectSendTeamB_i.getBirthDay()));
                }
                //任职时间
                if(projectSendTeamB_i.getTenureTime() !=null) {
                	collectProjectAuditMemberDto.setTenureTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(projectSendTeamB_i.getTenureTime()));
                }
                //性别
                collectProjectAuditMemberDto.setSex(projectSendTeamB_i.getSex());
                //学历
                collectProjectAuditMemberDto.setDiploma(projectSendTeamB_i.getDiploma());
                //国籍
                collectProjectAuditMemberDto.setNationality(projectSendTeamB_i.getNationality());
                //股份占比
                collectProjectAuditMemberDto.setStockPer(projectSendTeamB_i.getStockRatio());
                //创业经历详细描述
                collectProjectAuditMemberDto.setBusinessDesc(projectSendTeamB_i.getBusinessExperienceDesc());
                //教育背景详细描述
                collectProjectAuditMemberDto.setEducationExperienceDesc(projectSendTeamB_i.getEducationExperienceDesc());
                //工作背景详细描述
                collectProjectAuditMemberDto.setWorkExperienceDesc(projectSendTeamB_i.getWorkExperienceDesc());
                
                ProjectSendTeamMemberSelfteamB projectSendTeamMemberSelfteamB =new ProjectSendTeamMemberSelfteamB();
                projectSendTeamMemberSelfteamB.setPsTeamBId(teamMemberId);
                List<ProjectSendTeamMemberSelfteamB> projectSendTeamMemberSelfteamBs = projectSendTeamMemberSelfteamBMapper.select(projectSendTeamMemberSelfteamB);
                //用于接受自定义团队
                List<String> selfTeams=null;
                if(projectSendTeamMemberSelfteamBs != null && projectSendTeamMemberSelfteamBs.size() != 0) {
                	selfTeams=new ArrayList<>();
                	for(ProjectSendTeamMemberSelfteamB e: projectSendTeamMemberSelfteamBs){
                		selfTeams.add(e.getTeamName());
                	};
                }
                collectProjectAuditMemberDto.setSelfDefTeam(selfTeams);
                
                //关注领域--ok
                ProjectSendTeamMemberSegmentationB projectSendTeamMemberSegmentationB =new ProjectSendTeamMemberSegmentationB();
                projectSendTeamMemberSegmentationB.setPsTeamBId(teamMemberId);
                List<ProjectSendTeamMemberSegmentationB> projectSendTeamMemberSegmentationBs = projectSendTeamMemberSegmentationBMapper.select(projectSendTeamMemberSegmentationB);
                //用于接受领域id
                List<Integer> segmentations=null;
                if(projectSendTeamMemberSegmentationBs!=null && projectSendTeamMemberSegmentationBs.size()!=0) {
                	segmentations=new ArrayList<>();
                	for(ProjectSendTeamMemberSegmentationB e: projectSendTeamMemberSegmentationBs){
                		segmentations.add(e.getSegmentationId());
                	};
                }
                collectProjectAuditMemberDto.setSegmentaionIds(segmentations);
                
                //投资阶段--ok
                ProjectSendTeamMemberStageB projectSendTeamMemberStageB =new ProjectSendTeamMemberStageB();
                projectSendTeamMemberStageB.setPsTeamBId(teamMemberId);
                List<ProjectSendTeamMemberStageB> projectSendTeamMemberStageBs = projectSendTeamMemberStageBMapper.select(projectSendTeamMemberStageB);
                
                //用于接受投资阶段id
                List<Integer> stages=null;
                if(projectSendTeamMemberStageBs!=null && projectSendTeamMemberStageBs.size()!=0) {
                	stages=new ArrayList<>();
                	for(ProjectSendTeamMemberStageB e: projectSendTeamMemberStageBs){
                		stages.add(e.getStageId());
                	};
                }
                collectProjectAuditMemberDto.setInvestStages(stages);
                //所在城市--ok
                ProjectSendTeamMemberCityB projectSendTeamMemberCityB =new ProjectSendTeamMemberCityB();
                projectSendTeamMemberCityB.setPsTeamBId(teamMemberId);
                List<ProjectSendTeamMemberCityB> projectSendTeamMemberCityBs = projectSendTeamMemberCityBMapper.select(projectSendTeamMemberCityB);
                
              //用于接受成员所在城市
                List<String> citys=null;
                if(projectSendTeamMemberCityBs!=null && projectSendTeamMemberCityBs.size()!=0) {
                	citys=new ArrayList<>();
                	for(ProjectSendTeamMemberCityB e: projectSendTeamMemberCityBs){
                		citys.add(e.getCityName());
                	};
                }
                collectProjectAuditMemberDto.setCitys(citys);
                
                //自定义城市
                ProjectSendTeamMemberSelfcityB projectSendTeamMemberSelfcityB =new ProjectSendTeamMemberSelfcityB();
                projectSendTeamMemberSelfcityB.setPsTeamBId(teamMemberId);
                List<ProjectSendTeamMemberSelfcityB> projectSendTeamMemberSelfcityBs = projectSendTeamMemberSelfcityBMapper.select(projectSendTeamMemberSelfcityB);
                
              //用于接受成员所在自定义城市
                List<String> selfCitys=null;
                if(projectSendTeamMemberSelfcityBs!=null && projectSendTeamMemberSelfcityBs.size()!=0) {
                	selfCitys=new ArrayList<>();
                	for(ProjectSendTeamMemberSelfcityB e: projectSendTeamMemberSelfcityBs){
                		selfCitys.add(e.getCity());
                	};
                }
                collectProjectAuditMemberDto.setSelfDefCitys(selfCitys);
                
                //创业经历
                ProjectSendTeamMemberBusinessB projectSendTeamMemberBusinessB =new ProjectSendTeamMemberBusinessB();
                projectSendTeamMemberBusinessB.setPsTeamBId(teamMemberId);
                List<ProjectSendTeamMemberBusinessB> projectSendTeamMemberBusinessBs = projectSendTeamMemberBusinessBMapper.select(projectSendTeamMemberBusinessB);
                
              //用于接受成员创业经历
                List<String> business=null;
                if(projectSendTeamMemberBusinessBs!=null && projectSendTeamMemberBusinessBs.size()!=0) {
                	business=new ArrayList<>();
                	for(ProjectSendTeamMemberBusinessB e: projectSendTeamMemberBusinessBs){
                		business.add(e.getCompanyName());
                	};
                }
                collectProjectAuditMemberDto.setBusinesses(business);
                
                //设置是否隐藏
                collectProjectAuditMemberDto.setIsHide(projectSendTeamB_i.getIsHide());
                
                collectProjectAuditMemberDtoList.add(collectProjectAuditMemberDto);
            }
        }
        //对审核项目成员进行权重排序，当排序字段为null的时候会产生异常
        Collections.sort(collectProjectAuditMemberDtoList);
        
        //对集合进行设置前端显示的排序字段
        List<CollectProjectAuditTeamDto.CollectProjectAuditMemberDto> collectProjectAuditMemberDtoList1 = new ArrayList<>();
        for (int i=0; i < collectProjectAuditMemberDtoList.size(); i++){
            CollectProjectAuditTeamDto.CollectProjectAuditMemberDto collectProjectAuditMemberDto = new CollectProjectAuditTeamDto.CollectProjectAuditMemberDto();
            collectProjectAuditMemberDto = collectProjectAuditMemberDtoList.get(i);
            collectProjectAuditMemberDto.setSortId(i+1);
            collectProjectAuditMemberDtoList1.add(collectProjectAuditMemberDto);
        }
        
        collectProjectAuditTeamDto.setCollectProjectAuditMemberDtoList(collectProjectAuditMemberDtoList1);
        
        //设置团队成员的详细信息
//        CollectProjectAuditTeamDto.CollectProjectAuditMemberDetailsDto memberDetails =new CollectProjectAuditTeamDto().new CollectProjectAuditMemberDetailsDto();
//        memberDetails.setIsHide(isHide);
//        memberDetails.setPhone(phone);
//        memberDetails.setWeight(phone);
//        collectProjectAuditTeamDto.setCollectProjectAuditMemberDetailsDto(memberDetails);
        
        result.setStatus(200);
        result.setMessage("success");
        result.setData(collectProjectAuditTeamDto);
        return result;
    }
}
