package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditTeamDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ProjectSendAuditBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamIntroductionBMapper;
import com.lhjl.tzzs.proxy.model.ProjectSendAuditB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamIntroductionB;
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

    @Override
    public CommonDto<CollectProjectAuditTeamDto> getCollectProjectAuditTeam(Integer projectId) {

        CommonDto<CollectProjectAuditTeamDto> result = new CommonDto<>();

        CollectProjectAuditTeamDto collectProjectAuditTeamDto = new CollectProjectAuditTeamDto();

        List<CollectProjectAuditTeamDto.CollectProjectAuditMemberDto> collectProjectAuditMemberDtoList = new ArrayList<>();

//        ProjectSendAuditB projectSendAuditB = new ProjectSendAuditB();
//        projectSendAuditB.setId(projectId);
        
        ProjectSendAuditB projectSendAuditB1 = projectSendAuditBMapper.selectByPrimaryKey(projectId);

        if(null == projectSendAuditB1){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }
        //取得项目的团队介绍信息
        ProjectSendTeamIntroductionB projectSendTeamIntroductionB = projectSendTeamIntroductionBMapper.selectByPrimaryKey(projectSendAuditB1.getProjectSendBId());
        //取得提交项目团队成员的列表
        List<ProjectSendTeamB> projectSendTeamBList = projectSendTeamBMapper.selectByProjectId(projectSendAuditB1.getProjectSendBId());
        //将每一个项目团队成员转换为DTO
        if(null != projectSendTeamBList && projectSendTeamBList.size() != 0){
            for(ProjectSendTeamB projectSendTeamB_i : projectSendTeamBList){
            	//设置项目审核的团队成员
                CollectProjectAuditTeamDto.CollectProjectAuditMemberDto collectProjectAuditMemberDto = new CollectProjectAuditTeamDto.CollectProjectAuditMemberDto();
                collectProjectAuditMemberDto.setMemberId(projectSendTeamB_i.getId());
                collectProjectAuditMemberDto.setMemberName(projectSendTeamB_i.getMemberName());
                collectProjectAuditMemberDto.setPosition(projectSendTeamB_i.getCompanyDuties());
                collectProjectAuditMemberDto.setStockPer(projectSendTeamB_i.getStockRatio());
                collectProjectAuditMemberDto.setKernelDesc(projectSendTeamB_i.getMemberDesc());
                collectProjectAuditMemberDtoList.add(collectProjectAuditMemberDto);
            }
        }

        //对审核项目成员进行权重排序
        Collections.sort(collectProjectAuditMemberDtoList);
        //设置前端显示的排序字段
        List<CollectProjectAuditTeamDto.CollectProjectAuditMemberDto> collectProjectAuditMemberDtoList1 = new ArrayList<>();
        for (int i=0; i < collectProjectAuditMemberDtoList.size(); i++){
            CollectProjectAuditTeamDto.CollectProjectAuditMemberDto collectProjectAuditMemberDto = new CollectProjectAuditTeamDto.CollectProjectAuditMemberDto();
            collectProjectAuditMemberDto = collectProjectAuditMemberDtoList.get(i);
            collectProjectAuditMemberDto.setSortId(++i);
            collectProjectAuditMemberDtoList1.add(collectProjectAuditMemberDto);
        }
        
        collectProjectAuditTeamDto.setProjectId(projectId);
        if(projectSendTeamIntroductionB != null) {
        	collectProjectAuditTeamDto.setTeamIntroduction(projectSendTeamIntroductionB.getTeamIntroduction());
        }
        
        collectProjectAuditTeamDto.setCollectProjectAuditMemberDtoList(collectProjectAuditMemberDtoList1);
        
        result.setStatus(200);
        result.setMessage("success");
        result.setData(collectProjectAuditTeamDto);
        return result;
    }
}
