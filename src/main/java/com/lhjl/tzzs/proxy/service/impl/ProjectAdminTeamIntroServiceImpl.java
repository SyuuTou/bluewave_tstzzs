package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.TeamIntroductionDto;
import com.lhjl.tzzs.proxy.dto.ProjectTeamMemberDto.ProjectTeamIntroInputDto;
import com.lhjl.tzzs.proxy.mapper.ProjectTeamInfoMapper;
import com.lhjl.tzzs.proxy.model.ProjectTeamInfo;
import com.lhjl.tzzs.proxy.model.ProjectTeamMember;
import com.lhjl.tzzs.proxy.service.ProjectAdminTeamIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/6.
 */
@Service
public class ProjectAdminTeamIntroServiceImpl implements ProjectAdminTeamIntroService {

    @Autowired
    private ProjectTeamInfoMapper projectTeamInfoMapper;

    @Override
    public CommonDto<String> addOrUpdatePojectTeamIntro(ProjectTeamIntroInputDto body) {

        CommonDto<String> result = new CommonDto<>();
        
        if(body.getProjectId()==null) {
        	result.setData(null);
            result.setStatus(500);
            result.setMessage("请输入主体id");
            return result;
        }
        if(body.getSubjectType()==null) {
        	result.setData(null);
            result.setStatus(500);
            result.setMessage("请输入主体类型");
            return result;
        }
        
        if(Integer.valueOf(1).equals(body.getSubjectType())) {//项目
        	ProjectTeamInfo projectTeamInfo = new ProjectTeamInfo();
            projectTeamInfo.setProjectId(body.getProjectId());
            projectTeamInfo.setTeamIntroduction(body.getTeamIntroduction());
            
            projectTeamInfoMapper.deleteByPrimaryKey(body.getProjectId());
            projectTeamInfoMapper.insertSelective(projectTeamInfo);
            
        }else if(Integer.valueOf(2).equals(body.getSubjectType())) {//机构
        	//TODO 机构的团队信息保存,后台数据待完善
        }
        
        
        result.setData("保存成功");
        result.setStatus(200);
        result.setMessage("success");
        return result;

    }

    @Override
    public CommonDto<TeamIntroductionDto> getPojectTeamIntro(Integer projectId,Integer subjectType) {

        CommonDto<TeamIntroductionDto> result = new CommonDto<>();
        TeamIntroductionDto intro = new TeamIntroductionDto();

        if( null == projectId ){
            result.setStatus(500);
            result.setMessage("请输入项目id");
            result.setData(null);
            return result;
        }
        if( null == subjectType ){
            result.setStatus(500);
            result.setMessage("请输入主体类型");
            result.setData(null);
            return result;
        }
        
        //设置主体id
        intro.setProjectId(projectId);
        if(Integer.valueOf(1).equals(subjectType)) {//项目
        	ProjectTeamInfo info = projectTeamInfoMapper.selectByPrimaryKey(projectId);
            if(null != info){
            	intro.setData(info.getTeamIntroduction());
            }
        }else if(Integer.valueOf(2).equals(subjectType) ){//机构
        	//TODO 机构的团队介绍
        }

        result.setData(intro);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }
}
