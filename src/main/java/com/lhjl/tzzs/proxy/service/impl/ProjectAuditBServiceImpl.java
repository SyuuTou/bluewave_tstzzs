package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.ProjectAuditBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectAuditBServiceImpl implements ProjectAuditBService{

    @Value("${pageNum}")
    private Integer defalutPageNum;

    @Value("${pageSize}")
    private Integer defaultPageSize;

    @Autowired
    private ProjectSendAuditBMapper projectSendAuditBMapper;

    @Autowired
    private ProjectSendBMapper projectSendBMapper;

    @Autowired
    private ProjectsMapper projectsMapper;

    @Autowired
    private ProjectSendSegmentationBMapper projectSendSegmentationBMapper;

    @Autowired
    private ProjectSegmentationMapper projectSegmentationMapper;

    @Autowired
    private ProjectSendTagsBMapper projectSendTagsBMapper;

    @Autowired
    private ProjectCompetitiveProductsMapper projectCompetitiveProductsMapper;

    @Autowired
    private ProjectFinancingLogMapper projectFinancingLogMapper;

    @Autowired
    private InvestmentInstitutionsProjectMapper investmentInstitutionsProjectMapper;

    @Autowired
    private ProjectTeamMemberMapper projectTeamMemberMapper;

    @Autowired
    private ProjectTeamMemberEducationMapper projectTeamMemberEducationMapper;

    @Autowired
    private ProjectTeamMemberWorkMapper projectTeamMemberWorkMapper;

    @Autowired
    private ProjectSendPrepareidBMapper projectSendPrepareidBMapper;

    @Autowired
    private AdminProjectApprovalLogMapper adminProjectApprovalLogMapper;

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;
    
    @Autowired
    private ProjectTeamMemberSelfteamMapper projectTeamMemberSelfteamMapper;
    
    @Autowired
    private ProjectTeamMemberSegmentationMapper projectTeamMemberSegmentationMapper;
    
    @Autowired
    private ProjectTeamMemberSelfcityMapper projectTeamMemberSelfcityMapper;
    
    @Autowired
    private ProjectTeamMemberCityMapper ProjectTeamMemberCityMapper;
    
    @Autowired
    private ProjectTeamMemberStageMapper projectTeamMemberStageMapper;
    
    @Autowired
    private ProjectTeamMemberBusinessMapper projectTeamMemberBusinessMapper;

    /**
     * 读取项目审核列表接口
     * @return
     */
    @Override
    public CommonDto<PagingOutputDto<ProjectSendBAdminListOutputDto>> getProjectSendList(ProjectSendBAdminListInputDto body,Integer appid) {
        CommonDto<PagingOutputDto<ProjectSendBAdminListOutputDto>> result =  new CommonDto<>();
        
        PagingOutputDto<ProjectSendBAdminListOutputDto> pod=new PagingOutputDto<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
        
        List<ProjectSendBAdminListOutputDto> list = new ArrayList<>();

        if (body.getCurrentPage() == null){
            body.setCurrentPage(defalutPageNum);
        }
        if (body.getPageSize() == null){
            body.setPageSize(defaultPageSize);
        }

        if (body.getCreatTimeOrder() == null && body.getAuditTimeOrder() == null){
            body.setCreatTimeOrder(1);
            body.setCreatTimeOrderDesc(1);
        }

        body.setStart( (long)(body.getCurrentPage() -1 ) * body.getPageSize() ); 

        List<Map<String,Object>> projectSendList = projectSendAuditBMapper.adminGetProjectSendList(body);
        
        Long total = projectSendAuditBMapper.adminGetProjectSendListCount(body);
        
        if (projectSendList.size() > 0){
            for (Map<String,Object> m:projectSendList){
                ProjectSendBAdminListOutputDto projectSendBAdminListOutputDto = new ProjectSendBAdminListOutputDto();
                projectSendBAdminListOutputDto.setId((Integer) m.get("id"));
                
                String projectSoruce = null;
                Integer projectSoruceInt = (Integer)m.get("project_source");
                if(projectSoruceInt != null) {
                	switch (projectSoruceInt){
	                    case 1:projectSoruce = "创业者提交";  
	                    break;
	                    default:projectSoruce="";
                	}
                }
                projectSendBAdminListOutputDto.setProjectSource(projectSoruce);
                
                projectSendBAdminListOutputDto.setProjectShortName((String) m.get("short_name"));
                projectSendBAdminListOutputDto.setProjectFullName((String)m.get("full_name"));
                projectSendBAdminListOutputDto.setKernelDesc((String)m.get("kernel_desc"));
                projectSendBAdminListOutputDto.setSegmentationName((String)m.get("segmentation_name"));
                projectSendBAdminListOutputDto.setCity((String) m.get("city"));
                projectSendBAdminListOutputDto.setUserName((String)m.get("actual_name"));
                projectSendBAdminListOutputDto.setCompanyName((String)m.get("company_name"));
                projectSendBAdminListOutputDto.setCompanyDuties((String)m.get("company_duties"));
                projectSendBAdminListOutputDto.setPhonenumber((String)m.get("phonenumber"));
                
                String projectLevel = null;
                Integer projectLevelInt = (Integer)m.get("rating_stage");
                if(projectLevelInt != null) {
                	switch (projectLevelInt){
	                    case 0:projectLevel = "D级";
	                    break;
	                    case 1:projectLevel = "C级";
	                        break;
	                    case 2:projectLevel = "B级";
	                        break;
	                    case 3:projectLevel = "A级";
	                        break;
	                    case 4:projectLevel = "S级";
	                        break;
	                    default:projectLevel = "";
                	}
                }
                
                projectSendBAdminListOutputDto.setProjectLevel(projectLevel);
                
                if (m.get("create_time") != null){
                	projectSendBAdminListOutputDto.setCreatTime(sdf.format((Date)m.get("create_time")));
                }
                
                String auditStatus = null;
                Integer auditStatusInt = (Integer)m.get("audit_status");
                if(auditStatusInt!=null) {
                	switch (auditStatusInt){
	                    case 0:auditStatus = "待审核";
	                    break;
	                    case 1:auditStatus = "审核通过";
	                    break;
	                    case 2:auditStatus = "审核未通过";
	                    break;
	                    default : auditStatus = "";
                	}
                }
                
                projectSendBAdminListOutputDto.setAuditStatus(auditStatus);
                if (m.get("audit_time") != null){
                	projectSendBAdminListOutputDto.setAuditTime(sdf.format((Date)m.get("audit_time")));
                }
                
                list.add(projectSendBAdminListOutputDto);
            }
        }



        pod.setList(list);
        pod.setCurrentPage(body.getCurrentPage());
        pod.setTotal(total);
        pod.setPageSize(body.getPageSize());

        result.setData(pod);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }

    /**
     * 审核项目信息的接口
     * @param body
     * @param appid
     * @return
     */
    @Override
    @Transactional
    public CommonDto<String> auditProjectSend(ProjectSendAuditBInputDto body, Integer appid) {
        CommonDto<String> result  = new CommonDto<>();

        if (body.getShortName() == null){
            result.setData(null);
            result.setMessage("公司名称不能为空");
            result.setStatus(502);

            return result;
        }

        if (body.getUserId() == null){
            result.setMessage("提交项目人id不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        //该项目还没有进行对比
        if (body.getComparedStatus() == 0){
            Projects projects = new Projects();
            projects.setShortName(body.getShortName());

            List<Projects> projectsList =  projectsMapper.select(projects);
            if (projectsList.size() > 0){
                result.setMessage("当前项目在平台项目库已存在,请务必对比确认");
                result.setData(null);
                result.setStatus(502);

                return result;
            }
        }else {//
        	result.setMessage("当前项目在平台项目库已存在,且已经对比完毕，请务必确认");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        // 审核项目主体信息
        CommonDto<Integer> projectIdResult = auditMainProject(body,appid);
        Integer projectId = projectIdResult.getData();

        // 项目领域信息
        CommonDto<String> result2 = auditProjectSegment(body,projectId,appid);
        if (result2.getStatus() != 200){
            return  result2;
        }
        // 项目标签信息
        CommonDto<String> result3 = auditProjectTags(body,projectId,appid);
        if (result3.getStatus() != 200){
            return result3;
        }
        // 项目竞品信息
        CommonDto<String> result4 = auditProjectCompetation(body,projectId,appid);
        if (result4.getStatus() != 200){
            return result4;
        }
        // 项目当前融资信息
        CommonDto<String> result5 = auditProjectFinancingApproval(body,projectId,appid);
        if (result5.getStatus() != 200){
            return result5;
        }
        // 项目历史融资信息
        CommonDto<String> result6 = auditProjectFinancingHistory(body,projectId,appid);
        if (result6.getStatus() != 200){
            return result6;
        }
        // 团队成员
        CommonDto<String> result7 = auditProjectTeamMember(body,projectId,appid);
        if (result7.getStatus() != 200){
            return result7;
        }
        // 提交记录修改状态
        CommonDto<String> result8 = auditProjectSendLogB(body,projectId,appid);
        if (result8.getStatus() != 200){
            return result8;
        }
        // pareid修改状态
        CommonDto<String> result9 = auditProjectPrepearId(body,projectId,appid);
        if (result9.getStatus() != 200){
            return result9;
        }
        // 创建项目审核记录表的信息
        CommonDto<String> result10 = creatAdminAuditRecord(body,projectId,appid);
        if (result10.getStatus() != 200){
            return result10;
        }

        result.setData(null);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    /**
     * 审核项目主体信息
     * @param body
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<Integer> auditMainProject(ProjectSendAuditBInputDto body, Integer appid){
        CommonDto<Integer> result  = new CommonDto<>();
        Date now = new Date();

        Projects projects = new Projects();
        if (body.getProjectLogo() != null){
            projects.setProjectLogo(body.getProjectLogo());
        }
        if (body.getShortName() != null){
            projects.setShortName(body.getShortName());
        }
        if (body.getFullName() != null){
            projects.setFullName(body.getFullName());
        }
        if (body.getKernelDesc() != null){
        	projects.setKernelDesc(body.getKernelDesc());
        }
        if (body.getProjectInvestmentHighlights() != null){
            projects.setProjectInvestmentHighlights(body.getProjectInvestmentHighlights());
        }
        if (body.getCommet() != null){
            projects.setCommet(body.getCommet());
        }
        if (body.getUrl() != null){
            projects.setUrl(body.getUrl());
        }
        if (body.getCity() != null){
            projects.setCity(body.getCity());
        }
        /**
         * 理论上来说，写入到平台库中的项目是没有采集编号的
         * 下面的代码仅仅用于暂且保留
         */
        Integer projectNumber = 1000000;
        
        List<Projects> projectsListForSerialNumber = projectsMapper.maxSerialNumber();
        if (projectsListForSerialNumber.size() > 0){
            projectNumber = projectsListForSerialNumber.get(0).getSerialNumber() + 1;
        }

        projects.setSerialNumber(projectNumber);
        projects.setCreateTime(now);
        projects.setApprovalStatus(1);
        projects.setApprovalTime(now);
        projects.setProjectSource(0);
        projects.setYn(1);
        projects.setUserid(body.getUserId());

        Integer projectId =null;
        if (body.getProjectId() == null){
            projectsMapper.insertSelective(projects);
            projectId = projects.getId();
        }else {
            projects.setId(body.getProjectId());
            projectsMapper.updateByPrimaryKeySelective(projects);
            projectId= body.getProjectId();

        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData(projectId);

        return result;
    }

    /**
     * 审核项目主体领域信息
     * @param body
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> auditProjectSegment(ProjectSendAuditBInputDto body,Integer projectId,Integer appid){
        CommonDto<String> result =  new CommonDto<>();

        if (projectId == null){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("项目id不能为空");

            return result;
        }

        if (body.getProjectSegmentation() == null){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("请选择项目领域");

            return result;
        }
        //先删除原来的领域
        ProjectSegmentation projectSegmentationForDelete = new ProjectSegmentation();
        projectSegmentationForDelete.setProjectId(projectId);

        projectSegmentationMapper.delete(projectSegmentationForDelete);

        List<String> segmentationB = body.getProjectSegmentation();
        if (segmentationB.size() > 0 ){
            for (String s: segmentationB){
                ProjectSegmentation projectSegmentation = new ProjectSegmentation();
                projectSegmentation.setProjectId(projectId);
                projectSegmentation.setSegmentationName(s);

                projectSegmentationMapper.insertSelective(projectSegmentation);
            }
        }


        result.setMessage("success");
        result.setStatus(200);
        result.setData(null);

        return result;
    }

    /**
     * 审核项目主体标签信息
     * @param body
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> auditProjectTags(ProjectSendAuditBInputDto body,Integer projectId,Integer appid){
        CommonDto<String> result = new CommonDto<>();

        if (projectId == null){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("项目id不能为空");

            return result;
        }

        List<String> projectTags = new ArrayList<>();
        if (body.getProjectTags() != null){
            projectTags = body.getProjectTags();
        }


        if (projectTags.size() > 0){
            Projects projects =new Projects();
            projects.setId(projectId);

            String tag = "";
            for (String s:projectTags){
                tag = tag + s + "、";
            }
            tag = tag.substring(0,tag.length()-1);

            projects.setItemLabel(tag);

            projectsMapper.updateByPrimaryKeySelective(projects);
        }

        result.setData(null);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    /**
     * 审核项目竞品信息
     * @param body
     * @param projectId
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> auditProjectCompetation(ProjectSendAuditBInputDto body,Integer projectId,Integer appid){
        CommonDto<String> result = new CommonDto<>();

        if (projectId == null){
            result.setMessage("项目id不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        //先删除原来的项目竞品信息
        ProjectCompetitiveProducts projectCompetitiveProducts = new ProjectCompetitiveProducts();
        projectCompetitiveProducts.setProjectId(projectId);

        projectCompetitiveProductsMapper.delete(projectCompetitiveProducts);

        List<String> projectComprtitive = new ArrayList<>();

        if (body.getProjectCompeting() != null){
            projectComprtitive = body.getProjectCompeting();
        }

        //创建
        if (projectComprtitive.size()>0){
            for (String s:projectComprtitive){
                ProjectCompetitiveProducts projectCompetitiveProducts1 = new ProjectCompetitiveProducts();
                projectCompetitiveProducts1.setProjectId(projectId);
                projectCompetitiveProducts1.setCompetitiveProductsName(s);

                projectCompetitiveProductsMapper.insertSelective(projectCompetitiveProducts1);
            }
        }

        result.setData(null);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    /**
     * 项目当前融资信息
     * @param body
     * @param projectId
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> auditProjectFinancingApproval(ProjectSendAuditBInputDto body,Integer projectId,Integer appid){
        CommonDto<String> result = new CommonDto<>();
        Date now = new Date();

        if (projectId == null){
            result.setMessage("项目id不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (body.getStage() == null){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("融资阶段不能为空");

            return result;
        }

        if (body.getAmount() == null){
            result.setMessage("融资金额不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }
        //先删除原来的融资当前融资信息
        Example projectExample = new Example(ProjectFinancingLog.class);
        projectExample.and().andIsNull("financingTime").andEqualTo("projectId",projectId);

        projectFinancingLogMapper.deleteByExample(projectExample);

        //创建融资需求
        ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();
        projectFinancingLog.setStage(body.getStage());
        projectFinancingLog.setProjectId(projectId);
        projectFinancingLog.setAmount(body.getAmount());
        projectFinancingLog.setCurrency(body.getCurrency());
//        BigDecimal stockRight = BigDecimal.ZERO;
//        if (body.getShareDivest() != null){
//            stockRight = new BigDecimal(body.getShareDivest());
//        }
//      projectFinancingLog.setStockRight(stockRight);
        
        //本轮总出让股份
        projectFinancingLog.setShareDivest(body.getShareDivest());

        if (body.getProjectFinancingUseful() != null){
            projectFinancingLog.setProjectFinancingUseful(body.getProjectFinancingUseful());
        }

        projectFinancingLog.setStatus(2);
        projectFinancingLog.setApprovalStatus(1);
        projectFinancingLog.setApprovalTime(now);

        projectFinancingLogMapper.insertSelective(projectFinancingLog);

        result.setStatus(200);
        result.setData(null);
        result.setMessage("success");

        return result;
    }

    /**
     * 项目历史融资信息审核
     * @param body
     * @param projectId
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> auditProjectFinancingHistory(ProjectSendAuditBInputDto body,Integer projectId,Integer appid){
        CommonDto<String> result = new CommonDto<>();
        Date now= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 删除原来的融资历史
        Example financingExample = new Example(ProjectFinancingLog.class);
        //融资时间不为null的属于该项目的历史融资
        financingExample.and().andIsNotNull("financingTime").andEqualTo("projectId",projectId);

        List<ProjectFinancingLog> projectFinancingLogList = projectFinancingLogMapper.selectByExample(financingExample);
        if (projectFinancingLogList.size() > 0){
            for (ProjectFinancingLog pfl:projectFinancingLogList){
                InvestmentInstitutionsProject investmentInstitutionsProject = new InvestmentInstitutionsProject();
                investmentInstitutionsProject.setProjectId(pfl.getId());
                //删除"机构和项目融资阶段关联表"中对应的机构信息
                investmentInstitutionsProjectMapper.delete(investmentInstitutionsProject);
                //删除项目的融资历史信息
                projectFinancingLogMapper.deleteByPrimaryKey(pfl.getId());
            }
        }

        // 创建新的融资历史
        if (projectId == null){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("项目id不能为空");

            return result;

        }

        List<ProjectSendFinancingHistoryBDto> projectSendHistoryList = new ArrayList<>();
        if (body.getFinancingHistory() != null){
            projectSendHistoryList = body.getFinancingHistory();
        }

        if (projectSendHistoryList.size() > 0){
            for (ProjectSendFinancingHistoryBDto psfh:projectSendHistoryList){

                if (psfh.getStage() == null){
                    result.setMessage("融资历史，融资轮次不能为空");
                    result.setData(null);
                    result.setStatus(502);

                    return result;
                }
                if (psfh.getAmount() == null){
                    result.setMessage("融资历史，融资金额");
                    result.setData(null);
                    result.setStatus(502);

                    return result;
                }

                ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();
                projectFinancingLog.setProjectId(projectId);
                projectFinancingLog.setApprovalTime(now);
                projectFinancingLog.setApprovalStatus(1);
                projectFinancingLog.setStage(psfh.getStage());
                projectFinancingLog.setAmount(body.getAmount());
                projectFinancingLog.setCurrency(psfh.getCurrency());
                projectFinancingLog.setTotalAmount(psfh.getTotalAmount());
                //曹传贵
                projectFinancingLog.setYn(0);
                
                try {
                    Date financingTime = sdf.parse(psfh.getFinancingTime());
                    projectFinancingLog.setFinancingTime(financingTime);
                }catch (Exception e){
                    result.setMessage("格式化时间失败");
                    result.setStatus(502);
                    result.setData(null);

                    return result;
                }
                projectFinancingLogMapper.insertSelective(projectFinancingLog);

                Integer projectFinancingLogId = projectFinancingLog.getId();

                if (psfh.getInvestor().size() > 0){
                    for (ProjectSendInvestorDto psi:psfh.getInvestor()){
                        InvestmentInstitutionsProject investmentInstitutionsProject = new InvestmentInstitutionsProject();
                        if (psi.getInvestmentInstitutionId() != null){
                            investmentInstitutionsProject.setInvestmentInstitutionsId(psi.getInvestmentInstitutionId());
                        }else {
                            if ( null != psi.getInvestmentInstitutionName()){
                                InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
                                investmentInstitutions.setShortName(psi.getInvestmentInstitutionName());

                                List<InvestmentInstitutions> investmentInstitutionsList = investmentInstitutionsMapper.select(investmentInstitutions);
                                 if (investmentInstitutionsList.size() > 0){
                                     investmentInstitutionsProject.setInvestmentInstitutionsId(investmentInstitutionsList.get(0).getId());
                                 }else {
                                     investmentInstitutionsMapper.insertSelective(investmentInstitutions);

                                     investmentInstitutionsProject.setInvestmentInstitutionsId(investmentInstitutions.getId());
                                 }

                            }else {
                                result.setMessage("投资机构id,投资机构名称都为空,无法保存融资历史");
                                result.setStatus(502);
                                result.setData(null);

                                return result;
                            }

                        }

                        investmentInstitutionsProject.setProjectId(projectFinancingLogId);

                        investmentInstitutionsProjectMapper.insertSelective(investmentInstitutionsProject);
                    }
                }
            }
        }

        result.setMessage("success");
        result.setData(null);
        result.setStatus(200);

        return result;
    }

    /**
     * 审核采集项目的团队成员
     * @param body
     * @param projectId
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> auditProjectTeamMember(ProjectSendAuditBInputDto body,Integer projectId,Integer appid){
        CommonDto<String> result = new CommonDto<>();
        Date now = new Date();

        if (body.getTeamMember() == null){
            result.setMessage("团队成员不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (projectId == null){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("项目id不能为空");

            return result;
        }

        if (body.getTeamMember().size() > 0){
            for (ProjectSendTeamBDto pstb:body.getTeamMember()){
            	//设置ProjectTeamMember表的主体信息
                ProjectTeamMember projectTeamMember = new ProjectTeamMember();

                //设置关联的项目id
                projectTeamMember.setProjectId(projectId);
                
                //设置团队成员的id
                projectTeamMember.setId(pstb.getProjectSendMemberId());
                
                projectTeamMember.setMumberName(pstb.getMemberName());
                projectTeamMember.setMumberDuties(pstb.getCompanyDuties());
                projectTeamMember.setShareRatio(pstb.getStockRatio());
                projectTeamMember.setMumberDesc(pstb.getMemberDesc());
                projectTeamMember.setPhone(pstb.getPhone());
                projectTeamMember.setIsonjob(pstb.getIsOnJob());
                projectTeamMember.setWeight(pstb.getWeight());
                projectTeamMember.setHeadPicture(pstb.getHeadPicture());
                projectTeamMember.setPicture(pstb.getPicture());
                projectTeamMember.setEmail(pstb.getEmail());
                projectTeamMember.setWeichat(pstb.getWeichat());
                projectTeamMember.setBusinessExperienceDesc(pstb.getBusinessExperienceDesc());
                projectTeamMember.setWorkExperienceDesc(pstb.getWorkExperienceDesc());
                projectTeamMember.setEducationExperienceDesc(pstb.getEducationExperienceDesc());
                projectTeamMember.setTeamId(pstb.getTeamId());
                //前端传递的是字符串
                try {
                	if(pstb.getBirthDay()!=null) {
						projectTeamMember.setBirthDay(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pstb.getBirthDay()));
                	}
                	if(pstb.getTenureTime()!=null) {
						projectTeamMember.setBirthDay(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pstb.getTenureTime()));
                	}
                } catch (ParseException e) {
					result.setData("ParseException");
					result.setMessage("字符串解析异常");
					result.setStatus(500);
					return result;
				}
                projectTeamMember.setSex(pstb.getSex());
                projectTeamMember.setDiploma(pstb.getDiploma());
                projectTeamMember.setNationality(pstb.getNationality());
                
                projectTeamMember.setCreateTime(now);
                projectTeamMember.setYn(0);
                
                //获取项目团队成员的成员id
                projectTeamMemberMapper.insertSelective(projectTeamMember);
                Integer projectTeamId = projectTeamMember.getId();
                
                //设置工作经历
                if (pstb.getWorkExperience() != null){
                    if (pstb.getWorkExperience().size() > 0){
                        for (String s:pstb.getWorkExperience()){
                            ProjectTeamMemberWork projectTeamMemberWork = new ProjectTeamMemberWork();
                            projectTeamMemberWork.setProjectTeamMemberId(projectTeamId);
                            projectTeamMemberWork.setWorkExperience(s);

                            projectTeamMemberWorkMapper.insertSelective(projectTeamMemberWork);
                        }
                    }
                }
                //设置教育经历
                if (pstb.getEducationExperience() != null){
                    if (pstb.getWorkExperience().size() > 0){
                        for (String s:pstb.getEducationExperience()){
                            ProjectTeamMemberEducation projectTeamMemberEducation = new ProjectTeamMemberEducation();
                            projectTeamMemberEducation.setProjectTeamMemberId(projectTeamId);
                            projectTeamMemberEducation.setEducationExperience(s);

                            projectTeamMemberEducationMapper.insertSelective(projectTeamMemberEducation);
                        }
                    }
                }
                
                //设置自定义团队
                if(pstb.getSelfDefTeam() != null && pstb.getSelfDefTeam().size() >0) {
                	for(String e:pstb.getSelfDefTeam()) {
                		ProjectTeamMemberSelfteam ProjectTeamMemberSelfteam=new ProjectTeamMemberSelfteam(); 
                		ProjectTeamMemberSelfteam.setMemberId(projectTeamId);
                		ProjectTeamMemberSelfteam.setTeamName(e);
                		
                		projectTeamMemberSelfteamMapper.insertSelective(ProjectTeamMemberSelfteam);
                	}
                }
                //设置关注领域
                if(pstb.getSegmentaionIds() != null && pstb.getSegmentaionIds().size() >0) {
                	for(Integer e:pstb.getSegmentaionIds()) {
                		ProjectTeamMemberSegmentation projectTeamMemberSegmentation=new ProjectTeamMemberSegmentation();
                		projectTeamMemberSegmentation.setMemberId(projectTeamId);
                		projectTeamMemberSegmentation.setSegmentationId(e);
                		
                		projectTeamMemberSegmentationMapper.insertSelective(projectTeamMemberSegmentation);
                	}
                }
                //设置投资阶段
                if(pstb.getInvestStages() != null && pstb.getInvestStages().size() >0) {
                	for(Integer e:pstb.getInvestStages()) {
                		ProjectTeamMemberStage projectTeamMemberStage=new ProjectTeamMemberStage();
                		projectTeamMemberStage.setMemberId(projectTeamId);
                		projectTeamMemberStage.setStageId(e);
                		
                		projectTeamMemberStageMapper.insertSelective(projectTeamMemberStage);
                	}
                }
                //设置城市
                if(pstb.getCitys() != null && pstb.getCitys().size() >0) {
                	for(String e:pstb.getCitys()) {
                		ProjectTeamMemberCity projectTeamMemberCity=new ProjectTeamMemberCity();
                		projectTeamMemberCity.setMemberId(projectTeamId);
                		projectTeamMemberCity.setCityName(e);
                		
                		ProjectTeamMemberCityMapper.insertSelective(projectTeamMemberCity);
                	}
                }
                //设置自定义城市
                if(pstb.getSelfDefCitys() != null && pstb.getSelfDefCitys().size() >0) {
                	for(String e:pstb.getSelfDefCitys()) {
                		ProjectTeamMemberSelfcity projectTeamMemberSelfcity=new ProjectTeamMemberSelfcity();
                		projectTeamMemberSelfcity.setMemberId(projectTeamId);
                		projectTeamMemberSelfcity.setCity(e);
                		
                		projectTeamMemberSelfcityMapper.insertSelective(projectTeamMemberSelfcity);
                	}
                }
                
                //设置创业经历businesses
                if(pstb.getBusinesses() != null && pstb.getBusinesses().size() >0) {
                	for(String e:pstb.getBusinesses()) {
                		ProjectTeamMemberBusiness projectTeamMemberBusiness=new ProjectTeamMemberBusiness();
                		projectTeamMemberBusiness.setMemberId(projectTeamId);
                		projectTeamMemberBusiness.setCompanyName(e);
                		
                		projectTeamMemberBusinessMapper.insertSelective(projectTeamMemberBusiness);
                	}
                }
                
                
            }
        }

        result.setMessage("success");
        result.setData(null);
        result.setStatus(200);

        return result;
    }

    /**
     * 修改项目提交记录
     * @param body
     * @param projectId
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> auditProjectSendLogB(ProjectSendAuditBInputDto body,Integer projectId,Integer appid){
        CommonDto<String> result = new CommonDto<>();
        Date now = new Date();

        if (body.getProjectSendLogId() == null){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("提交项目id不能为空");

            return result;
        }

        if (body.getStatus() == null){
            result.setMessage("审核状态不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        ProjectSendAuditB projectSendAuditB = new ProjectSendAuditB();
        projectSendAuditB.setAuditStatus(body.getStatus());
        projectSendAuditB.setAuditTime(now);
        projectSendAuditB.setAuditAdmin(body.getAdministractor());
        projectSendAuditB.setId(body.getProjectSendLogId());

        projectSendAuditBMapper.updateByPrimaryKeySelective(projectSendAuditB);

        result.setStatus(200);
        result.setData(null);
        result.setMessage("success");

        return result;
    }

    /**
     * pareid修改状态
     * @param body
     * @param projectId
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> auditProjectPrepearId(ProjectSendAuditBInputDto body,Integer projectId,Integer appid){
        CommonDto<String> result = new CommonDto<>();
        Date now = new Date();

        if (body.getProjectSendLogId() == null){
            result.setMessage("提交项目id不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        ProjectSendAuditB projectSendAuditB = projectSendAuditBMapper.selectByPrimaryKey(body.getProjectSendLogId());
        if (projectSendAuditB == null){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("没有找到提交记录");

            return result;
        }

        Integer prepareId = projectSendAuditB.getPrepareId();

        ProjectSendPrepareidB projectSendPrepareidB = new ProjectSendPrepareidB();
        projectSendPrepareidB.setAuditStatus(body.getStatus());
        projectSendPrepareidB.setAuditTime(now);
        projectSendPrepareidB.setId(prepareId);

        projectSendPrepareidBMapper.updateByPrimaryKeySelective(projectSendPrepareidB);

        result.setMessage("success");
        result.setData(null);
        result.setStatus(200);

        return result;
    }

    /**
     * 创建管理员审核记录表
     * @param body
     * @param projectId
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> creatAdminAuditRecord(ProjectSendAuditBInputDto body,Integer projectId,Integer appid){
        CommonDto<String> result = new CommonDto<>();
        Date now = new Date();

        if (projectId == null){
            result.setMessage("项目id不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (body.getProjectSendLogId() == null){
            result.setMessage("提交项目id不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        AdminProjectApprovalLog adminProjectApprovalLog = new AdminProjectApprovalLog();
        adminProjectApprovalLog.setProjectId(projectId);
        adminProjectApprovalLog.setProjectSourceId(body.getProjectSendLogId());
        adminProjectApprovalLog.setProjectSourceType(0);
        adminProjectApprovalLog.setApprovaledStatus(body.getStatus());
        adminProjectApprovalLog.setApprovaledTime(now);
        if (body.getAdministractor() != null){
            adminProjectApprovalLog.setApprovaledAdminName(body.getAdministractor());
        }

        Example apaExample = new Example(AdminProjectApprovalLog.class);
        apaExample.and().andEqualTo("projectSourceId",body.getProjectSendLogId()).andEqualTo("projectSourceId",projectId);

        List<AdminProjectApprovalLog> adminProjectApprovalLogList = adminProjectApprovalLogMapper.selectByExample(apaExample);
        if (adminProjectApprovalLogList.size()>0){
            adminProjectApprovalLog.setId(adminProjectApprovalLogList.get(0).getId());

            adminProjectApprovalLogMapper.updateByPrimaryKeySelective(adminProjectApprovalLog);
        }else {
            adminProjectApprovalLogMapper.insertSelective(adminProjectApprovalLog);
        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData(null);

        return result;
    }
}
