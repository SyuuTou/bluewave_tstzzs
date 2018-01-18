package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAdminLogoOutputDto;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.service.ProjectAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProjectAdminServiceImpl implements ProjectAdminService{

    @Autowired
    private ProjectsMapper projectsMapper;

    /**
     * 读取项目logo和其他基本信息
     * @param projectId
     * @param projectType
     * @return
     */
    @Override
    public CommonDto<ProjectAdminLogoOutputDto> getProjectLogoAndMainInfo(Integer projectId, Integer projectType) {
        CommonDto<ProjectAdminLogoOutputDto> result = new CommonDto<>();

        if (projectId == null){
            result.setStatus(502);
            result.setMessage("项目id不能为空");
            result.setData(null);

            return result;
        }

        if (projectType == null){
            projectType = 1;
        }

        if (projectType == 1){
            result = readProjectLogo(projectId);
        }else {
            //todo 读机构信息
        }

        return result;
    }

    /**
     * 读取项目logo和一些基本信息的方法
     * @param projectId
     * @return
     */
    private CommonDto<ProjectAdminLogoOutputDto> readProjectLogo(Integer projectId){
        CommonDto<ProjectAdminLogoOutputDto> result = new CommonDto<>();

        Map<String,Object> baseInfo  = projectsMapper.getLogoAndOtherInfoById(projectId);
        if (baseInfo != null){
            ProjectAdminLogoOutputDto projectAdminLogoOutputDto= new ProjectAdminLogoOutputDto();
            projectAdminLogoOutputDto.setId((Integer)baseInfo.get("id"));
            String logo = "";
            if (baseInfo.get("project_logo") != null){
                logo = (String)baseInfo.get("project_logo");
            }
            projectAdminLogoOutputDto.setLogo(logo);
            String shortName = "";
            if (baseInfo.get("short_name") != null){
                shortName = (String)baseInfo.get("short_name");
            }
            projectAdminLogoOutputDto.setShortName(shortName);
            Integer ratingStage = -1;
            String ratingStageString = "";
            if (baseInfo.get("rating_stage") != null){
                ratingStage = (Integer)baseInfo.get("rating_stage");
            }
            switch (ratingStage){
                case 0:ratingStageString = "D级";
                break;
                case 1:ratingStageString = "C级";
                    break;
                case 2:ratingStageString = "B级";
                    break;
                case 3:ratingStageString = "A级";
                    break;
                case 4:ratingStageString = "S级";
                    break;
                default:ratingStageString = "";
            }
            projectAdminLogoOutputDto.setStage(ratingStageString);
            String statusName= "";
            if (baseInfo.get("status_name") != null){
                statusName = (String)baseInfo.get("status_name");
            }
            projectAdminLogoOutputDto.setFollowStatus(statusName);
            String adminName = "";
            if (baseInfo.get("admin_name") != null){
                adminName = (String)baseInfo.get("admin_name");
            }
            projectAdminLogoOutputDto.setProjectAdmin(adminName);
            projectAdminLogoOutputDto.setClaimStatus("未认领");
            projectAdminLogoOutputDto.setType("产业公司");

            result.setMessage("success");
            result.setData(projectAdminLogoOutputDto);
            result.setStatus(200);

        }else {
            result.setStatus(502);
            result.setData(null);
            result.setMessage("没有找到项目信息，请检查项目id");

        }
        return result;
    }
}
