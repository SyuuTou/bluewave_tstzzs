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
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectAuditBServiceImpl implements ProjectAuditBService{

    @Value("${pageNum}")
    private Integer defalutPageNum;

    @Value("${pageSize}")
    private Integer defalutPageSize;

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

    /**
     * 读取项目审核列表接口
     * @return
     */
    @Override
    public CommonDto<Map<String, Object>> getProjectSendList(ProjectSendBAdminListInputDto body,Integer appid) {
        CommonDto<Map<String,Object>> result =  new CommonDto<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
        Map<String,Object> map = new HashMap<>();
        List<ProjectSendBAdminListOutputDto> list = new ArrayList<>();

        if (body.getSearchWord() == null){
            body.setSearchWord("");
        }

        if (body.getPageNum() == null){
            body.setPageNum(defalutPageNum);
        }
        if (body.getPageSize() == null){
            body.setPageSize(defalutPageSize);
        }

        if (body.getCreatTimeOrder() == null && body.getAuditTimeOrder() == null){
            body.setCreatTimeOrder(1);
            body.setCreatTimeOrderDesc(1);
        }

        Integer startPage = (body.getPageNum() -1 )*body.getPageSize();

        List<Map<String,Object>> projectSendList = projectSendAuditBMapper.adminGetProjectSendList(body.getSearchWord(),body.getBegainTime(),
                body.getEndTime(),body.getProjetcSource(),body.getCreatTimeOrder(),body.getCreatTimeOrderDesc(),body.getAuditTimeOrder(),
                body.getAuditTimeOrderDesc(),startPage,body.getPageSize());
        Integer totalCount = projectSendAuditBMapper.adminGetProjectSendListCount(body.getSearchWord(),body.getBegainTime(),
                body.getEndTime(),body.getProjetcSource(),body.getCreatTimeOrder(),body.getCreatTimeOrderDesc(),body.getAuditTimeOrder(),
                body.getAuditTimeOrderDesc(),startPage,body.getPageSize());

        if (projectSendList.size() > 0){
            for (Map<String,Object> m:projectSendList){
                ProjectSendBAdminListOutputDto projectSendBAdminListOutputDto = new ProjectSendBAdminListOutputDto();
                projectSendBAdminListOutputDto.setId((Integer) m.get("id"));
                String projectSoruce = "";
                Integer projectSoruceInt = 1;
                if (m.get("project_source") != null){
                    projectSoruceInt = (Integer)m.get("project_source");
                }
                switch (projectSoruceInt){
                    case 1:projectSoruce = "创业者提交";
                    break;
                }
                projectSendBAdminListOutputDto.setProjectSource(projectSoruce);
                String projectShortName = "";
                if (m.get("short_name") != null){
                    projectShortName = (String) m.get("short_name");
                }
                projectSendBAdminListOutputDto.setProjectShortName(projectShortName);
                String projectFullName = "";
                if (m.get("full_name") != null){
                    projectFullName = (String)m.get("full_name");
                }
                projectSendBAdminListOutputDto.setProjectFullName(projectFullName);
                String kernelDesc = "";
                if (m.get("kernel_desc") != null){
                    kernelDesc = (String)m.get("kernel_desc");
                }
                projectSendBAdminListOutputDto.setKernelDesc(kernelDesc);
                String segmentation = "";
                if (m.get("segmentation_name") != null){
                    segmentation = (String)m.get("segmentation_name");
                }
                projectSendBAdminListOutputDto.setSegmentationName(segmentation);
                String city = "";
                if (m.get("city") != null){
                    city = (String) m.get("city");
                }
                projectSendBAdminListOutputDto.setCity(city);
                String userName = "";
                if (m.get("actual_name") != null){
                    userName = (String)m.get("actual_name");
                }
                projectSendBAdminListOutputDto.setUserName(userName);
                String companyName ="";
                if (m.get("company_name") != null){
                    companyName = (String)m.get("company_name");
                }
                projectSendBAdminListOutputDto.setCompanyName(companyName);
                String companyDuties = "";
                if (m.get("company_duties") != null){
                    companyDuties = (String)m.get("company_duties");
                }
                projectSendBAdminListOutputDto.setCompanyDuties(companyDuties);
                String phoneNum = "";
                if (m.get("phonenumber") != null){
                    phoneNum = (String)m.get("phonenumber");
                }
                projectSendBAdminListOutputDto.setPhonenumber(phoneNum);
                String projectLevel = "";
                Integer projectLevelInt = 111;
                if (m.get("rating_stage") != null){
                    projectLevelInt = (Integer)m.get("rating_stage");
                }
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
                projectSendBAdminListOutputDto.setProjectLevel(projectLevel);
                String createTime = "";
                if (m.get("create_time") != null){
                    Date createTimeD = (Date)m.get("create_time");
                    createTime = sdf.format(createTimeD);
                }
                projectSendBAdminListOutputDto.setCreatTime(createTime);
                String auditStatus = "";
                Integer auditStatusInt = 0;
                if (m.get("audit_status") != null){
                    auditStatusInt = (Integer)m.get("audit_status");
                }
                switch (auditStatusInt){
                    case 0:auditStatus = "待审核";
                    break;
                    case 1:auditStatus = "审核通过";
                    break;
                    case 2:auditStatus = "审核未通过";
                    break;
                }
                projectSendBAdminListOutputDto.setAuditStatus(auditStatus);
                String auditTime = "";
                if (m.get("audit_time") != null){
                    Date auditTimeD = (Date)m.get("audit_time");
                    auditTime = sdf.format(auditTimeD);
                }
                projectSendBAdminListOutputDto.setAuditTime(auditTime);

                list.add(projectSendBAdminListOutputDto);
            }
        }



        map.put("list",list);
        map.put("currentPage",body.getPageNum());
        map.put("total",totalCount);
        map.put("pageSize",body.getPageSize());

        result.setData(map);
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

        //判断项目是否已经存在
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
            projects.setKernelDesc(body.getFullName());
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
        BigDecimal stockRight = BigDecimal.ZERO;
        if (body.getShareDivest() != null){
            stockRight = new BigDecimal(body.getShareDivest());
        }

        projectFinancingLog.setStockRight(stockRight);
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
        financingExample.and().andIsNotNull("financingTime").andEqualTo("projectId",projectId);

        List<ProjectFinancingLog> projectFinancingLogList = projectFinancingLogMapper.selectByExample(financingExample);
        if (projectFinancingLogList.size() > 0){
            for (ProjectFinancingLog pfl:projectFinancingLogList){
                InvestmentInstitutionsProject investmentInstitutionsProject = new InvestmentInstitutionsProject();
                investmentInstitutionsProject.setProjectId(pfl.getId());

                investmentInstitutionsProjectMapper.delete(investmentInstitutionsProject);
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
                        investmentInstitutionsProject.setInvestmentInstitutionsId(psi.getInvestmentInstitutionId());
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
     * 审核团队成员
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
                ProjectTeamMember projectTeamMember = new ProjectTeamMember();

                projectTeamMember.setYn(0);
                projectTeamMember.setProjectId(projectId);
                projectTeamMember.setShareRatio(pstb.getStockRatio());
                projectTeamMember.setMumberName(pstb.getMemberName());
                projectTeamMember.setMumberDuties(pstb.getCompanyDuties());
                projectTeamMember.setMumberDesc(pstb.getMemberDesc());
                projectTeamMember.setCreateTime(now);

                projectTeamMemberMapper.insertSelective(projectTeamMember);
                Integer projectTeamId = projectTeamMember.getId();
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
