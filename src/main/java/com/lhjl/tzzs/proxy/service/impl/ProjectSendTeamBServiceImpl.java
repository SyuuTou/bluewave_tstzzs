package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendTeamBDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendTeamBOutDto;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamMemberEducationBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendTeamMemberWorkBMapper;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberEducation;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberEducationB;
import com.lhjl.tzzs.proxy.model.ProjectSendTeamMemberWorkB;
import com.lhjl.tzzs.proxy.service.ProjectSendTeamBService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectSendTeamBServiceImpl implements ProjectSendTeamBService{
    @Autowired
    private ProjectSendTeamBMapper projectSendTeamBMapper;

    @Autowired
    private ProjectSendTeamMemberWorkBMapper projectSendTeamMemberWorkBMapper;

    @Autowired
    private ProjectSendTeamMemberEducationBMapper projectSendTeamMemberEducationBMapper;

    /**
     *  创建/更新提交项目成员信息
     * @param body
     * @param appid
     * @return
     */
    @Override
    public CommonDto<String> createTeamMember(ProjectSendTeamBDto body, Integer appid) {
        CommonDto<String> result  = new CommonDto<>();
        if (body.getProjectSendBId() == null){
            result.setStatus(502);
            result.setMessage("提交项目id不能为空");
            result.setData(null);

            return result;
        }

        if (StringUtils.isAnyBlank(body.getMemberName(),body.getCompanyDuties(),body.getMemberDesc())){
            result.setMessage("必填项中包含未填项，请检查");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (body.getStockRatio() == null){
            result.setMessage("请输入股份占比");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (body.getWorkExperience() == null){
            result.setMessage("请输入工作经历");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (body.getEducationExperience() == null){
            result.setMessage("请输入教育经历");
            result.setStatus(502);
            result.setData(null);

            return result;
        }


        ProjectSendTeamB projectSendTeamB = new ProjectSendTeamB();
        projectSendTeamB.setAppid(appid);
        projectSendTeamB.setMemberName(body.getMemberName());
        projectSendTeamB.setCompanyDuties(body.getCompanyDuties());
        projectSendTeamB.setMemberDesc(body.getMemberDesc());
        projectSendTeamB.setProjectSendBId(body.getProjectSendBId());
        projectSendTeamB.setStockRatio(body.getStockRatio());
        projectSendTeamB.setYn(0);

        Integer teamMemberId = null;
        if (body.getProjectSendMemberId() != null){

            projectSendTeamB.setId(body.getProjectSendMemberId());
            projectSendTeamBMapper.updateByPrimaryKeySelective(projectSendTeamB);

            teamMemberId = body.getProjectSendMemberId();
        }else {
            projectSendTeamBMapper.insertSelective(projectSendTeamB);
            teamMemberId = projectSendTeamB.getId();
        }

        createProjectSendBTeamEducation(body,appid,teamMemberId);
        createProjectSendBTeamWork(body,appid,teamMemberId);

        result.setData(null);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    @Override
    public CommonDto<ProjectSendTeamBDto> readTeamMemberById(Integer projectSendMemberId, Integer appid) {
        CommonDto<ProjectSendTeamBDto> result = new CommonDto<>();
        if (projectSendMemberId == null){
            result.setMessage("提交项目成员id不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }
        //todo
        ProjectSendTeamB projectSendTeamB = projectSendTeamBMapper.selectByPrimaryKey(projectSendMemberId);

        return result;
    }

    @Override
    public CommonDto<List<ProjectSendTeamBOutDto>> readTeamMemberList(Integer projectSendBId, Integer appid) {
        //todo
        return null;
    }

    @Override
    public CommonDto<String> deleteTeamMemberById(Integer projectSendMemberId, Integer appid) {
        //todo
        return null;
    }

    /**
     * 创建提交项目团队成员教育经历方法
     * @param body
     * @param appid
     * @param teamMmberId
     */
    @Transactional
    public void createProjectSendBTeamEducation(ProjectSendTeamBDto body, Integer appid,Integer teamMmberId){
        if (teamMmberId == null){
            return;
        }
        if (body.getProjectSendMemberId() != null){
            ProjectSendTeamMemberEducationB projectSendTeamMemberEducationBForDelete = new ProjectSendTeamMemberEducationB();
            projectSendTeamMemberEducationBForDelete.setPsTeamBId(body.getProjectSendMemberId());

            projectSendTeamMemberEducationBMapper.delete(projectSendTeamMemberEducationBForDelete);
        }

        if (body.getEducationExperience() != null){
            if (body.getEducationExperience().size() > 0){
                for (String s:body.getEducationExperience()){
                    ProjectSendTeamMemberEducationB projectSendTeamMemberEducationB = new ProjectSendTeamMemberEducationB();
                    projectSendTeamMemberEducationB.setPsTeamBId(teamMmberId);
                    projectSendTeamMemberEducationB.setEducationExperience(s);

                    projectSendTeamMemberEducationBMapper.insertSelective(projectSendTeamMemberEducationB);
                }
            }
        }
    }

    /**
     * 创建提交项目团队成员工作经历方法
     * @param body
     * @param appid
     * @param teamMmberId
     */
    @Transactional
    public void createProjectSendBTeamWork(ProjectSendTeamBDto body, Integer appid,Integer teamMmberId){
        if (teamMmberId == null){
            return;
        }
        if (body.getProjectSendMemberId() != null){
            ProjectSendTeamMemberWorkB projectSendTeamMemberWorkB = new ProjectSendTeamMemberWorkB();
            projectSendTeamMemberWorkB.setPsTeamBId(body.getProjectSendMemberId());

            projectSendTeamMemberWorkBMapper.delete(projectSendTeamMemberWorkB);
        }

        if (body.getWorkExperience() != null){
            if (body.getWorkExperience().size() > 0){
                for (String s:body.getWorkExperience()){
                    ProjectSendTeamMemberWorkB projectSendTeamMemberWorkB = new ProjectSendTeamMemberWorkB();

                    projectSendTeamMemberWorkB.setPsTeamBId(teamMmberId);
                    projectSendTeamMemberWorkB.setWorkExperience(s);

                    projectSendTeamMemberWorkBMapper.insertSelective(projectSendTeamMemberWorkB);
                }
            }
        }
    }
}
