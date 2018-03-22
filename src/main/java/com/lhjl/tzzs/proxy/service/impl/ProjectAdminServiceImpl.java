package com.lhjl.tzzs.proxy.service.impl;

import com.google.common.base.Joiner;
import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectCompetitiveProductsMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSegmentationMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.ProjectCompetitiveProducts;
import com.lhjl.tzzs.proxy.model.ProjectSegmentation;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.ProjectAdminService;
import com.lhjl.tzzs.proxy.service.ProjectAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectAdminServiceImpl extends GenericService implements ProjectAdminService {

    @Autowired
    private ProjectsMapper projectsMapper;

    @Resource
    private ProjectAuditService projectAuditService;

    @Autowired
    private ProjectSegmentationMapper projectSegmentationMapper;

    @Autowired
    private ProjectCompetitiveProductsMapper projectCompetitiveProductsMapper;
    
    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    
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
//            projectType = 1;
            result.setData(null);
            result.setMessage("请指定相关的主体信息，1：项目；2：机构...");
            result.setStatus(500);
            return result;
        }

        if (projectType == 1){ // 读项目信息
            result = readProjectLogo(projectId);
        }else if(projectType == 2){// 读机构信息
        	result = readInstitutionLogo(projectId);
        }else {
        	result.setData(null);
            result.setMessage("属于其他主体类型，业务有待进一步完善");
            result.setStatus(500);
            return result;
        }

        return result;
    }
    /**
     * 读取机构logo和一些基本信息的方法
     * @param projectId
     * @return
     */
    private CommonDto<ProjectAdminLogoOutputDto> readInstitutionLogo(Integer projectId) {
    	CommonDto<ProjectAdminLogoOutputDto> result =new CommonDto<ProjectAdminLogoOutputDto>();
    	ProjectAdminLogoOutputDto baseInfo = investmentInstitutionsMapper.getLogoAndOtherInfoById(projectId);
    	
        if (baseInfo != null){
        	if(baseInfo.getStage()!=null) {
        		switch(Integer.valueOf(baseInfo.getStage())) {
        		case 0:baseInfo.setStage("D级"); 
                break;
                case 1:baseInfo.setStage("C级") ;
                    break;
                case 2:baseInfo.setStage("B级") ;
                    break;
                case 3:baseInfo.setStage("A级");
                    break;
                case 4:baseInfo.setStage("S级") ;
                    break;
                default:baseInfo.setStage(null) ;
        		}
        	}
        	//TODO 认领状态需要根据后台的设计来提供而不是程序中控制，需要完善
        	baseInfo.setClaimStatus("未认领");
        	//TODO 机构暂时默认为投资机构，需要完善
        	baseInfo.setType("投资机构");
        	
            result.setMessage("success");
            result.setData(baseInfo);
            result.setStatus(200);

        }else {
            result.setStatus(502);
            result.setData(null);
            result.setMessage("没有找到机构信息，请检查机构id");

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

        ProjectAdminLogoOutputDto baseInfo = projectsMapper.getLogoAndOtherInfoById(projectId);
        if (baseInfo != null){
        	if(baseInfo.getStage()!=null) {
        		switch(Integer.valueOf(baseInfo.getStage())) {
        		case 0:baseInfo.setStage("D级"); 
                break;
                case 1:baseInfo.setStage("C级") ;
                    break;
                case 2:baseInfo.setStage("B级") ;
                    break;
                case 3:baseInfo.setStage("A级");
                    break;
                case 4:baseInfo.setStage("S级") ;
                    break;
                default:baseInfo.setStage(null) ;
        		}
        	}
        	//TODO 此处需要根据后台的设计来提供而不是程序中控制，需要完善
        	baseInfo.setClaimStatus("未认领");
        	//TODO 机构暂时默认为创业公司，需要完善
        	baseInfo.setType("创业公司");
        	
            result.setMessage("success");
            result.setData(baseInfo);
            result.setStatus(200);

        }else {
            result.setStatus(502);
            result.setData(null);
            result.setMessage("没有找到项目信息，请检查项目id");

        }
        return result;
    }
	/**
     * 更改项目logo和其他基本信息的接口
     * @param body
     * @return
     */
    @Transactional
    @Override
    public CommonDto<String> updateProjectLogoAndMainInfo(ProjectAdminLogoInputDto body) {
        CommonDto<String> result = new CommonDto<>();

        /*if (body.getProjectId() == null){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("项目id不能为空");

            return result;
        }*/

        if (body.getSubjectType() == null){
            result.setMessage("主体类型不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (body.getShortName() == null || body.getShortName() == ""){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("项目简称不能为空");

            return result;
        }
        
        //对项目简称的有效性进行判断
        if(body.getSubjectType()==1) {
        	Projects projects = new Projects();
            projects.setShortName(body.getShortName());
            List<Projects> projectsList = projectsMapper.select(projects);
            if (projectsList.size() > 0){//该项目简称在项目表中存在
            	//原项目id所对应的项目
                Projects projectsForCompare = new Projects();
                projectsForCompare = projectsMapper.selectByPrimaryKey(body.getProjectId());
                //如果该简称不等同于所更改项目的简称
                if ( ! projectsList.get(0).getShortName().equals(projectsForCompare.getShortName()) ){

                    result.setMessage("该项目已经存在");
                    result.setData(null);
                    result.setStatus(502);

                    return result;
                }
            }
        }else if(body.getSubjectType()==2){
        	InvestmentInstitutions ii = new InvestmentInstitutions();
            ii.setShortName(body.getShortName());
            List<InvestmentInstitutions> iiList = investmentInstitutionsMapper.select(ii);
            if (iiList.size() > 0){//该机构简称在机构表中存在
            	//原机构id所对应的项目
            	InvestmentInstitutions iiForCompare = new InvestmentInstitutions();
            	iiForCompare = investmentInstitutionsMapper.selectByPrimaryKey(body.getProjectId());
                //如果该简称不等同于所更改机构的简称
                if ( ! iiList.get(0).getShortName().equals(iiForCompare.getShortName()) ){

                    result.setMessage("该机构已经存在");
                    result.setData(null);
                    result.setStatus(502);

                    return result;
                }
            }
            
        }
        //接收自增长id用于
        Integer updatedId = body.getProjectId();
        if (body.getSubjectType() == 1){
            //更新项目信息
            Projects projects = new Projects();
            projects.setId(body.getProjectId());
            projects.setShortName(body.getShortName());
            //设置项目类型
            projects.setProjectType(body.getProjectType());
            projects.setProjectLogo(body.getProjectLogo());
            
            if(body.getProjectId()==null) {//执行新增操作
            	projectsMapper.insertSelective(projects);
            	//设置自增长id
            	updatedId=projects.getId();
            }else {//执行更新操作
            	projectsMapper.updateByPrimaryKeySelective(projects);
            }
            
            //添加项目管理员
            if(body.getUserId() != null){
                projectAuditService.adminAddAdministractor(body.getProjectId(),updatedId);
            }
            //TODO 项目评级信息的更新(单独接口)
            
            //TODO 项目跟进状态的更新(单独接口)
        }else if(body.getSubjectType() == 2){
        	InvestmentInstitutions ii=new InvestmentInstitutions();
        	ii.setId(body.getProjectId());
        	ii.setShortName(body.getShortName());
        	ii.setLogo(body.getProjectLogo());
        	//TODO 设置机构的类型
        	
        	if(body.getProjectId()==null) {//执行新增操作
        		investmentInstitutionsMapper.insertSelective(ii);
        		updatedId=ii.getId();
            }else {//执行更新操作
            	investmentInstitutionsMapper.updateByPrimaryKeySelective(ii);
//            	ii.
            }
        	
        	//TODO 添加机构管理员
        	if(body.getUserId()!=null) {
        		
        	}
        	
            //TODO 机构评级信息的更新(单独接口)
            
            //TODO 机构跟进状态的更新(单独接口)
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
                String territory = "";
                if (projectlist.get("territory") != null){
                    territory= (String)projectlist.get("territory");
                }
                projectAdminBaseInfoDto.setTerritory(territory);
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
     * 更新项目基本信息的接口
     * @param body
     * @return
     */
    @Override
    public CommonDto<String> updateProjectBaseInfo(ProjectAdminBaseInfoInputDto body) {

        CommonDto<String> result = new CommonDto<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (body.getProjectId() == null){
            result.setMessage("项目id不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (body.getProjectType() == null){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("项目类型不能空");

            return result;
        }

        if (body.getProjectType() == 1){

            //更新项目主体信息
            Projects projects = new Projects();
            projects.setId(body.getProjectId());
            projects.setFullName(body.getFullName());
            projects.setKernelDesc(body.getKernelDesc());
            projects.setUrl(body.getUrl());
            String establishString = body.getEstablishedTime();
            try {
                Date establishTime = sdf.parse(establishString);
                projects.setEstablishedTime(establishTime);
            }catch (Exception e){
                this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
                this.LOGGER.info("时间格式化出错");
            }
            projects.setCompanyEmail(body.getCompanyEmail());
            projects.setCompanyHrEmail(body.getCompanyHrEmail());
            projects.setCity(body.getCity());
            projects.setTerritory(body.getTerritory());
            projects.setAddress(body.getAddress());
            String itemLabel ="";
            if (body.getItemLabel().size() > 0){
                itemLabel = Joiner.on("、").join(body.getItemLabel());
            }
            projects.setItemLabel(itemLabel);
            projects.setForeignInvestmentYn(body.getForeignInvestmentYn());

            projectsMapper.updateByPrimaryKeySelective(projects);

            //先删除项目的领域
            ProjectSegmentation projectSegmentation = new ProjectSegmentation();
            projectSegmentation.setProjectId(body.getProjectId());

            projectSegmentationMapper.delete(projectSegmentation);

            //创建项目领域
            if (body.getProjectSegmentation().size() > 0){

                for (String s:body.getProjectSegmentation()){
                    ProjectSegmentation projectSegmentationForInsert = new ProjectSegmentation();
                    projectSegmentationForInsert.setProjectId(body.getProjectId());
                    projectSegmentationForInsert.setSegmentationName(s);

                    projectSegmentationMapper.insertSelective(projectSegmentationForInsert);
                }
            }

            //先删除原来的项目竞品
            ProjectCompetitiveProducts projectCompetitiveProducts = new ProjectCompetitiveProducts();
            projectCompetitiveProducts.setProjectId(body.getProjectId());

            projectCompetitiveProductsMapper.delete(projectCompetitiveProducts);

            //创建新的项目竞品
            if (body.getProjectCompetitiveProducts().size() > 0){
                for (String s:body.getProjectCompetitiveProducts()){
                    ProjectCompetitiveProducts projectCompetitiveProductsForInsert = new ProjectCompetitiveProducts();
                    projectCompetitiveProductsForInsert.setProjectId(body.getProjectId());
                    projectCompetitiveProductsForInsert.setCompetitiveProductsName(s);

                    projectCompetitiveProductsMapper.insertSelective(projectCompetitiveProductsForInsert);
                }
            }

        }else {
            //todo 机构信息编辑的处理
        }

        result.setMessage("success");
        result.setData(null);
        result.setStatus(200);

        return result;
    }

   
}
