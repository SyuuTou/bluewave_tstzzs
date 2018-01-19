package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAdminBaseInfoDto;
import com.lhjl.tzzs.proxy.dto.ProjectAdminLogoInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectAdminLogoOutputDto;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.service.ProjectAdminService;
import com.lhjl.tzzs.proxy.service.ProjectAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectAdminServiceImpl implements ProjectAdminService{

    @Autowired
    private ProjectsMapper projectsMapper;

    @Resource
    private ProjectAuditService projectAuditService;

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
     * 更改项目logo和其他基本信息的接口
     * @param body
     * @return
     */
    @Override
    public CommonDto<String> updateProjectLogoAndMainInfo(ProjectAdminLogoInputDto body) {
        CommonDto<String> result = new CommonDto<>();

        if (body.getProjectId() == null){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("项目id不能为空");

            return result;
        }

        if (body.getProjectType() == null){
            result.setMessage("项目类型不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (body.getShortName() != null || body.getShortName() != ""){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("项目简称不能为空");

            return result;
        }


        Projects projects = new Projects();
        projects.setShortName(body.getShortName());

        List<Projects> projectsList = projectsMapper.select(projects);
        if (projectsList.size() > 0){
            result.setMessage("项目名称已存在");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (body.getProjectType() != null && body.getProjectType() == 1){
            //更新项目信息
            Projects projectsForInsert = new Projects();
            projectsForInsert.setShortName(body.getShortName());
            projectsForInsert.setId(body.getProjectId());
            if (body.getProjectLogo() != null && body.getProjectLogo() != ""){
                projectsForInsert.setProjectLogo(body.getProjectLogo());
            }
            projectsMapper.updateByPrimaryKeySelective(projectsForInsert);

            //添加项目管理员
            if(body.getUserId() != null){
                projectAuditService.adminAddAdministractor(body.getProjectId(),body.getUserId());
            }
        }else {
            //todo 机构逻辑的处理
        }

        result.setStatus(200);
        result.setData(null);
        result.setMessage("success");


        return result;
    }

    /**
     * 获取项目基本信息的接口
     * @param projectId
     * @param projectType
     * @return
     */
    @Override
    public CommonDto<ProjectAdminBaseInfoDto> getProjectBaseInfo(Integer projectId, Integer projectType) {
        CommonDto<ProjectAdminBaseInfoDto> result = new CommonDto<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ProjectAdminBaseInfoDto projectAdminBaseInfoDto = new ProjectAdminBaseInfoDto();

        if (projectId == null){
            result.setMessage("项目id不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (projectType == null){
            result.setMessage("项目类型不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }


        if (projectType == 1){
            Map<String,Object> projectlist = projectsMapper.getBaseInfoById(projectId);
            if (projectlist.size() > 0){
                String fullName = "";
                if (projectlist.get("full_name") != null){
                    fullName = (String)projectlist.get("full_name");
                }
                projectAdminBaseInfoDto.setFullName(fullName);
                String kernelDesc = "";
                if (projectlist.get("kernel_desc") != null){
                    kernelDesc = (String)projectlist.get("kernel_desc");
                }
                projectAdminBaseInfoDto.setKernelDesc(kernelDesc);
                String url = "";
                if (projectlist.get("url") != null){
                    url = (String) projectlist.get("url");
                }
                projectAdminBaseInfoDto.setUrl(url);
                String itemLabel = "";
                List<String> itemLabelL = new ArrayList<>();
                if (projectlist.get("item_label") != null){
                    itemLabel = (String)projectlist.get("item_label");
                    itemLabelL = Arrays.asList(itemLabel.split("、"));
                }
                projectAdminBaseInfoDto.setItemLabel(itemLabelL);
                String competitiveProductsName = "";
                List<String> competitiveProductsNameL = new ArrayList<>();
                if (projectlist.get("competitive_products_name") != null){
                    competitiveProductsName = (String) projectlist.get("competitive_products_name");
                    competitiveProductsNameL = Arrays.asList(competitiveProductsName.split(","));
                }
                projectAdminBaseInfoDto.setProjectCompetitiveProducts(competitiveProductsNameL);
                String segmentationName = "";
                List<String> segmentationNameL = new ArrayList<>();
                if (projectlist.get("segmentation_name") != null){
                    segmentationName = (String)projectlist.get("segmentation_name");
                    segmentationNameL = Arrays.asList(segmentationName.split(","));
                }
                projectAdminBaseInfoDto.setProjectSegmentation(segmentationNameL);
                String establishedTime = "";
                if (projectlist.get("established_time") != null){
                    Date et = (Date)projectlist.get("established_time");
                    establishedTime = sdf.format(et);
                }
                projectAdminBaseInfoDto.setEstablishedTime(establishedTime);
                String companyEmail = "";
                if (projectlist.get("company_email") != null){
                    companyEmail  = (String) projectlist.get("company_email");
                }
                projectAdminBaseInfoDto.setCompanyEmail(companyEmail);
                String city = "";
                if (projectlist.get("city") != null){
                    city = (String) projectlist.get("city");
                }
                projectAdminBaseInfoDto.setCity(city);
                String companyHrEmail = "";
                if (projectlist.get("company_hr_email") != null){
                    companyHrEmail = (String) projectlist.get("company_hr_email");
                }
                projectAdminBaseInfoDto.setCompanyHrEmail(companyHrEmail);
                String address = "";
                if (projectlist.get("address") != null){
                    address = (String) projectlist.get("address");
                }
                projectAdminBaseInfoDto.setAddress(address);
                String foreignInvestmentYn = "";
                if (projectlist.get("foreign_investment_yn") != null){
                    foreignInvestmentYn = (String)projectlist.get("foreign_investment_yn");
                }
                projectAdminBaseInfoDto.setForeignInvestmentYn(foreignInvestmentYn);
            }else {
               projectAdminBaseInfoDto.setFullName("");
               projectAdminBaseInfoDto.setKernelDesc("");
               projectAdminBaseInfoDto.setUrl("");
               projectAdminBaseInfoDto.setItemLabel(new ArrayList<>());
               projectAdminBaseInfoDto.setProjectCompetitiveProducts(new ArrayList<>());
               projectAdminBaseInfoDto.setProjectSegmentation(new ArrayList<>());
               projectAdminBaseInfoDto.setEstablishedTime("");
               projectAdminBaseInfoDto.setCompanyEmail("");
               projectAdminBaseInfoDto.setCity("");
               projectAdminBaseInfoDto.setCompanyHrEmail("");
               projectAdminBaseInfoDto.setAddress("");
               projectAdminBaseInfoDto.setForeignInvestmentYn("");
            }

        }else {
            //todo 不是项目的情况
        }

        result.setStatus(200);
        result.setData(projectAdminBaseInfoDto);
        result.setMessage("success");

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
