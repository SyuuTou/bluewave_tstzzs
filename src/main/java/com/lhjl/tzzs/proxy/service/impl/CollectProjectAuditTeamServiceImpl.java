package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditTeamDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ProjectSendAuditBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamIntroductionBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamMemberEducationBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamMemberWorkBMapper;
import com.lhjl.tzzs.proxy.model.ProjectSendAuditB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamIntroductionB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberEducationB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberWorkB;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public CommonDto<CollectProjectAuditTeamDto> getCollectProjectAuditTeam(Integer projectId) {

        CommonDto<CollectProjectAuditTeamDto> result = new CommonDto<>();

        CollectProjectAuditTeamDto collectProjectAuditTeamDto = new CollectProjectAuditTeamDto();

        List<CollectProjectAuditTeamDto.CollectProjectAuditMemberDto> collectProjectAuditMemberDtoList = new ArrayList<>();

        ProjectSendAuditB projectSendAuditB1 = projectSendAuditBMapper.selectByPrimaryKey(projectId);

        if(null == projectSendAuditB1){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }
        //取得项目的团队介绍信息实体
        ProjectSendTeamIntroductionB projectSendTeamIntroductionB = projectSendTeamIntroductionBMapper.selectByPrimaryKey(projectSendAuditB1.getProjectSendBId());
        //取得提交项目团队成员的列表
        List<ProjectSendTeamB> projectSendTeamBList = projectSendTeamBMapper.selectByProjectId(projectSendAuditB1.getProjectSendBId());
        //将每一个项目团队成员转换为DTO
        if(null != projectSendTeamBList && projectSendTeamBList.size() != 0){
            for(ProjectSendTeamB projectSendTeamB_i : projectSendTeamBList){
            	//设置采集项目的团队成员
                CollectProjectAuditTeamDto.CollectProjectAuditMemberDto collectProjectAuditMemberDto = new CollectProjectAuditTeamDto.CollectProjectAuditMemberDto();
                //团队成员id
                collectProjectAuditMemberDto.setMemberId(projectSendTeamB_i.getId());
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
                
                //TODO 团队成员的权重逻辑是否需要进一步变更  
                //设置团队成员的权重,如果权重为null的话，默认设置该权重为0
                collectProjectAuditMemberDto.setWeight(projectSendTeamB_i.getWeight()==null ? 0 : projectSendTeamB_i.getWeight());
                
                collectProjectAuditMemberDtoList.add(collectProjectAuditMemberDto);
            }
        }
        //对审核项目成员进行权重排序，当排序字段为null的时候会产生异常
        Collections.sort(collectProjectAuditMemberDtoList);
        //设置前端显示的排序字段
        List<CollectProjectAuditTeamDto.CollectProjectAuditMemberDto> collectProjectAuditMemberDtoList1 = new ArrayList<>();
        for (int i=0; i < collectProjectAuditMemberDtoList.size(); i++){
            CollectProjectAuditTeamDto.CollectProjectAuditMemberDto collectProjectAuditMemberDto = new CollectProjectAuditTeamDto.CollectProjectAuditMemberDto();
            collectProjectAuditMemberDto = collectProjectAuditMemberDtoList.get(i);
            collectProjectAuditMemberDto.setSortId(i+1);
            collectProjectAuditMemberDtoList1.add(collectProjectAuditMemberDto);
        }
        collectProjectAuditTeamDto.setCollectProjectAuditMemberDtoList(collectProjectAuditMemberDtoList1);
        
        //设置提交项目的团队介绍
        collectProjectAuditTeamDto.setProjectId(projectId);
        if(projectSendTeamIntroductionB != null) {
        	collectProjectAuditTeamDto.setTeamIntroduction(projectSendTeamIntroductionB.getTeamIntroduction());
        }
        
        result.setStatus(200);
        result.setMessage("success");
        result.setData(collectProjectAuditTeamDto);
        return result;
    }
}
