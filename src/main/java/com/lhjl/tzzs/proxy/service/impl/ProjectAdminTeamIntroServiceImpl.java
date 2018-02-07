package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
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

        ProjectTeamInfo projectTeamInfo = new ProjectTeamInfo();
        projectTeamInfo.setProjectId(body.getProjectId());
        ProjectTeamInfo projectTeamInfo1 = projectTeamInfoMapper.selectByPrimaryKey(projectTeamInfo);
        Integer projectTeamInfoInsertResult  = -1;
        if(null == projectTeamInfo1){
            projectTeamInfo.setTeamIntroduction(body.getTeamIntroduction());
            projectTeamInfoInsertResult = projectTeamInfoMapper.insert(projectTeamInfo);
        }else{
            projectTeamInfo.setTeamIntroduction(body.getTeamIntroduction());
            projectTeamInfoInsertResult = projectTeamInfoMapper.updateByPrimaryKeySelective(projectTeamInfo);
        }
        if(projectTeamInfoInsertResult > 0){
            result.setData("保存成功");
            result.setStatus(200);
            result.setMessage("success");
            return result;
        }

        result.setData("保存失败");
        result.setStatus(300);
        result.setMessage("failed");
        return result;

    }

    @Override
    public CommonDto<Map<String, Object>> getPojectTeamIntro(Integer projectId) {

        CommonDto<Map<String, Object>> result = new CommonDto<>();
        Map map = new HashMap();

        if( null == projectId ){
            map.put("projectId", projectId);
            map.put("data", "没有该项目");
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(map);
            return result;
        }
        ProjectTeamInfo projectTeamInfo = new ProjectTeamInfo();
        projectTeamInfo.setProjectId(projectId);
        ProjectTeamInfo projectTeamInfo1 = projectTeamInfoMapper.selectByPrimaryKey(projectTeamInfo);



        map.put("projectId", projectId);
        if(null == projectTeamInfo1){
            map.put("data",null);
        }else {
            map.put("data", projectTeamInfo1.getTeamIntroduction());
        }

        result.setData(map);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }
}
