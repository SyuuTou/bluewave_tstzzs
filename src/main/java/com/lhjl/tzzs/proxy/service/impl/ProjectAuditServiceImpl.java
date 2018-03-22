package com.lhjl.tzzs.proxy.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAuditInputDto;
import com.lhjl.tzzs.proxy.dto.XiangsiDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.ProjectAuditService;
import com.lhjl.tzzs.proxy.utils.JsonUtils;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectAuditServiceImpl implements ProjectAuditService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProjectAuditServiceImpl.class);

    @Value("${pageSize}")
    private Integer pageSizeDefalut;

    @Value("${pageNum}")
    private Integer pageNum;

    @Autowired
    private ProjectSendLogsMapper projectSendLogsMapper;

    @Autowired
    private InvestmentDataLogMapper investmentDataLogMapper;

    @Autowired
    private ProjectsMapper projectsMapper;

    @Autowired
    private ProjectSegmentationMapper projectSegmentationMapper;

    @Autowired
    private ProjectCompetitiveProductsMapper projectCompetitiveProductsMapper;

    @Autowired
    private ProjectSendLogCompetingMapper projectSendLogCompetingMapper;

    @Autowired
    private ProjectFinancingApprovalMapper projectFinancingApprovalMapper;

    @Autowired
    private ProjectFinancingLogMapper projectFinancingLogMapper;

    @Autowired
    private ProjectFinancingHistoryMapper projectFinancingHistoryMapper;

    @Autowired
    private ProjectFinancingHistoryInvestorsMapper projectFinancingHistoryInvestorsMapper;

    @Autowired
    private ProjectSendTeamMemberMapper projectSendTeamMemberMapper;

    @Autowired
    private ProjectSendTeamMemberEducationMapper projectSendTeamMemberEducationMapper;

    @Autowired
    private ProjectSendTeamMemberWorkMapper projectSendTeamMemberWorkMapper;

    @Autowired
    private ProjectTeamMemberMapper projectTeamMemberMapper;

    @Autowired
    private ProjectTeamMemberEducationMapper projectTeamMemberEducationMapper;

    @Autowired
    private ProjectTeamMemberWorkMapper projectTeamMemberWorkMapper;

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Autowired
    private FoundersMapper foundersMapper;

    @Autowired
    private FoundersEducationMapper foundersEducationMapper;

    @Autowired
    private FoundersWorkMapper foundersWorkMapper;

    @Autowired
    private DataLogDomainMapper dataLogDomainMapper;

    @Autowired
    private DataLogEducationMapper dataLogEducationMapper;

    @Autowired
    private DataLogWorkMapper dataLogWorkMapper;

    @Autowired
    private AdminProjectApprovalLogMapper adminProjectApprovalLogMapper;

    @Autowired
    private ProjectAdministratorMapper projectAdministratorMapper;

    @Autowired
    private UsersMapper usersMapper;

     @Autowired
     private FollowMapper followMapper;

     @Autowired
     private  SubjectTypeRelationalMapper subjectTypeRelationalMapper;


     @Autowired
     private SubjectMapper subjectMapper;
    /**
     * 管理员审核项目接口
     * @param body
     * @return
     */
    @Transactional
    @Override
    public CommonDto<String> adminAuditProject(ProjectAuditInputDto body){
        CommonDto<String> result = new CommonDto<>();
        if (body.getProjectSourceId() == null){
            result.setStatus(50001);
            result.setData(null);
            result.setMessage("项目源id不能为空");

            return result;
        }

        if (body.getProjctSourceType() == null){
            result.setStatus(50001);
            result.setData(null);
            result.setMessage("项目源类型不能为空");

            return result;
        }

        if (body.getAuditStatus()== null){
            result.setStatus(50001);
            result.setData(null);
            result.setMessage("项目源审核结果不能为空");

            return result;
        }
        Date now = new Date();

        //先判断审核结果是否通过
        if (body.getAuditStatus() == 0){
            //未通过状态,直接改申请记录的状态
            if(body.getProjctSourceType() == 0) {
                //创始人提交的修改创始人的记录
                ProjectSendLogs projectSendLogs = projectSendLogsMapper.selectByPrimaryKey(body.getProjectSourceId());
                Projects projects = new Projects();
                projects.setShortName(projectSendLogs.getCompanyShortName());
                List<Projects> projectsListForC = projectsMapper.select(projects);
                if (projectsListForC.size() > 0) {
                    Integer checkStatusU = 0;
                    switch (body.getAuditStatus()) {
                        case 0:
                            checkStatusU = 5;
                            break;
                        case 1:
                            checkStatusU = 2;
                    }
                    //修改申请记录为已审核
                    ProjectSendLogs projectSendLogsForUpdateU = new ProjectSendLogs();
                    projectSendLogsForUpdateU.setId(body.getProjectSourceId());
                    projectSendLogsForUpdateU.setCheckStatus(checkStatusU);
                    projectSendLogsForUpdateU.setCheckTime(now);
                    projectSendLogsMapper.updateByPrimaryKeySelective(projectSendLogsForUpdateU);
                }
            }else if (body.getProjctSourceType() == 1){
                //投资人提交修改投资人提交记录
                InvestmentDataLog investmentDataLogForSearch = investmentDataLogMapper.selectByPrimaryKey(body.getProjectSourceId());
                investmentDataLogForSearch.setAuditTime(now);
                investmentDataLogForSearch.setAuditYn(2);

                investmentDataLogMapper.updateByPrimaryKeySelective(investmentDataLogForSearch);
            }else {
                result.setMessage("项目源类型不存在");
                result.setData(null);
                result.setStatus(50001);
            }


        }else if (body.getAuditStatus() == 1){
            //通过的状态
            if (body.getProjctSourceType() == 0){
                ProjectSendLogs projectSendLogs = projectSendLogsMapper.selectByPrimaryKey(body.getProjectSourceId());
                Projects projects = new Projects();
                projects.setShortName(projectSendLogs.getCompanyShortName());
                List<Projects> projectsListForC =projectsMapper.select(projects);
                if (projectsListForC.size() > 0){
                    //如果项目已经存在直接修改记录为已审核
                    Integer checkStatusU = 0;
                    switch (body.getAuditStatus()){
                        case 0:checkStatusU=5;
                            break;
                        case 1:checkStatusU=2;
                    }
                    //修改申请记录为已审核
                    ProjectSendLogs projectSendLogsForUpdateU = new ProjectSendLogs();
                    projectSendLogsForUpdateU.setId(body.getProjectSourceId());
                    projectSendLogsForUpdateU.setCheckStatus(checkStatusU);
                    projectSendLogsForUpdateU.setCheckTime(now);
                    projectSendLogsMapper.updateByPrimaryKeySelective(projectSendLogsForUpdateU);

                    String xmidS = String.valueOf(projectsListForC.get(0).getId());
                    result.setStatus(200);
                    result.setMessage(xmidS);
                    result.setData(null);
                }else{
                    result  = projectAuditOfTypeOne(body);
                }

            }else if(body.getProjctSourceType() == 1){
                InvestmentDataLog projectSendLogs = investmentDataLogMapper.selectByPrimaryKey(body.getProjectSourceId());
                Projects projects = new Projects();
                projects.setShortName(projectSendLogs.getShortName());
                List<Projects> projectsListForC =projectsMapper.select(projects);
                if (projectsListForC.size() > 0){
                    //如果项目已存在直接修改审核记录
                    Integer checkStatusU = 0;
                    switch (body.getAuditStatus()){
                        case 0:checkStatusU=5;
                            break;
                        case 1:checkStatusU=2;
                    }
                    //修改申请记录为已审核
                    InvestmentDataLog projectSendLogsForUpdateU = new InvestmentDataLog();
                    projectSendLogsForUpdateU.setId(body.getProjectSourceId());
                    projectSendLogsForUpdateU.setAuditYn(checkStatusU);
                    projectSendLogsForUpdateU.setAuditTime(now);
                    investmentDataLogMapper.updateByPrimaryKeySelective(projectSendLogsForUpdateU);

                    String xmidS = String.valueOf(projectsListForC.get(0).getId());

                    result.setStatus(200);
                    result.setMessage(xmidS);
                    result.setData(null);

                }else{
                    result = projectAuditOfTypeTwo(body);
                }
            }else {
                result.setMessage("项目源类型不存在");
                result.setData(null);
                result.setStatus(50001);
            }
        }else {
            //未知审核结果状态
            result.setMessage("审核结果类型非法");
            result.setData(null);
            result.setStatus(50001);
        }


        return result;
    }

    /**
     * 审核创始人提交项目方法
     * @param body
     * @return
     */
    private CommonDto<String> projectAuditOfTypeOne(ProjectAuditInputDto body){
        CommonDto<String> result =new CommonDto<>();

        Date now = new Date();
        ProjectSendLogs projectSendLogs = projectSendLogsMapper.selectByPrimaryKey(body.getProjectSourceId());
        if (projectSendLogs == null){
            result.setData(null);
            result.setStatus(50001);
            result.setMessage("项目源id没有对应的提交记录，请检查");

            return result;
        }

        //先创建项目信息
        Projects projects = new Projects();
        projects.setShortName(projectSendLogs.getCompanyShortName());
        projects.setFullName(projectSendLogs.getCompanyName());
        projects.setProjectLogo(projectSendLogs.getCompanyLogo());
        projects.setKernelDesc(projectSendLogs.getCompanyOneSentenceIntroduct());
        projects.setProjectInvestmentHighlights(projectSendLogs.getCompanyInvestmentHighlights());
        projects.setCommet(projectSendLogs.getCompanyDesc());
        projects.setUrl(projectSendLogs.getCompanyOfficialWebsite());
        projects.setTerritory(projectSendLogs.getCity());
        projects.setCity(projectSendLogs.getCity());
        projects.setItemLabel(projectSendLogs.getProjectTags());
        projects.setProjectSource(0);
        projects.setUserid(projectSendLogs.getUserid());
        projects.setCreateTime(now);
        projects.setApprovalStatus(1);
        projects.setApprovalTime(now);

        //给项目编号，目前是1000000-2000000之间
        Integer projectNumber = 1000000;
        List<Projects> projectsListForSerialNumber = projectsMapper.maxSerialNumber();
        if (projectsListForSerialNumber.size() > 0){
            projectNumber = projectsListForSerialNumber.get(0).getSerialNumber() + 1;
        }

        projects.setSerialNumber(projectNumber);

        projectsMapper.insertSelective(projects);

        //拿到项目id
        Integer xmid = projects.getId();

        //处理创始人的信息
        Founders founderForUpdate = new Founders();
        founderForUpdate.setUserId(projectSendLogs.getUserid());

        List<Founders> foundersList = foundersMapper.select(founderForUpdate);
        if (foundersList.size() > 0){
            Integer fdid = foundersList.get(0).getId();
            //获取用户信息
            Users usersForInfo = usersMapper.selectByPrimaryKey(projectSendLogs.getId());
            if (usersForInfo != null){
                founderForUpdate.setName(usersForInfo.getActualName());
                founderForUpdate.setIntroduction(usersForInfo.getDesc());
            }
        }
        founderForUpdate.setApprovalTime(now);
        founderForUpdate.setApprovalStatus(1);
        founderForUpdate.setProjectId(xmid);

        foundersMapper.updateByPrimaryKeySelective(founderForUpdate);


        //创建项目的默认管理员
        ProjectAdministrator projectAdministrator = new ProjectAdministrator();
        projectAdministrator.setYn(0);
        projectAdministrator.setProjectsId(xmid);
        projectAdministrator.setCreateTime(now);
        projectAdministrator.setTypes(0);
        projectAdministrator.setUserId(projectSendLogs.getUserid());

        projectAdministratorMapper.insert(projectAdministrator);


        //创建项目的行业领域
        String hangyelingyu  = projectSendLogs.getField();
        String[] hangyelingyuArry = {};

        if (hangyelingyu == null || "".equals(hangyelingyu)){

        }else {
            hangyelingyuArry = hangyelingyu.split(",");
        }

        for (String s :hangyelingyuArry){
            ProjectSegmentation projectSegmentation = new ProjectSegmentation();
            projectSegmentation.setSegmentationName(s);
            projectSegmentation.setProjectId(xmid);

            projectSegmentationMapper.insertSelective(projectSegmentation);

        }

        //创建相似竞品
         //先获取相似竞品
            ProjectSendLogCompeting projectSendLogCompetingForCompeting = new ProjectSendLogCompeting();
            projectSendLogCompetingForCompeting.setProjectSendLogsId(body.getProjectSourceId());

        List<ProjectSendLogCompeting> projectSendLogCompetingList = projectSendLogCompetingMapper.select(projectSendLogCompetingForCompeting);

        //创建
        if (projectSendLogCompetingList.size() > 0){
            for (ProjectSendLogCompeting psc:projectSendLogCompetingList){
                ProjectCompetitiveProducts projectCompetitiveProducts =new ProjectCompetitiveProducts();
                projectCompetitiveProducts.setCompetitiveProductsName(psc.getCompetingProductName());
                projectCompetitiveProducts.setProjectId(xmid);

                projectCompetitiveProductsMapper.insertSelective(projectCompetitiveProducts);
            }
        }


        //创建融资历史信息
          //创建当前记录上的融资历史
          //获取
          ProjectFinancingApproval projectFinancingApprovalForApproval = new ProjectFinancingApproval();
          projectFinancingApprovalForApproval.setProjectSendLogId(body.getProjectSourceId());
          //创建
          List<ProjectFinancingApproval> projectFinancingApprovalList= projectFinancingApprovalMapper.select(projectFinancingApprovalForApproval);
          if (projectFinancingApprovalList.size() > 0){
              ProjectFinancingApproval projectFinancingApproval = projectFinancingApprovalList.get(0);

              ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();
              projectFinancingLog.setProjectId(xmid);
              projectFinancingLog.setStage(projectFinancingApproval.getFinancingRounds());
              projectFinancingLog.setAmount(projectFinancingApproval.getFinancingAmount());
              projectFinancingLog.setCurrency(projectFinancingApproval.getFinancingCurrency());
              projectFinancingLog.setCalculationAmountStatus(0);
              projectFinancingLog.setShareDivest(String.valueOf(projectFinancingApproval.getTransferShares()));
              projectFinancingLog.setProjectFinancingUseful(projectFinancingApproval.getFinancingUseful());
              projectFinancingLog.setCreateTime(projectSendLogs.getCreatTime());
              projectFinancingLog.setStatus(1);
              projectFinancingLog.setStockRight(projectFinancingApproval.getTransferShares());
              projectFinancingLog.setApprovalTime(now);

              projectFinancingLogMapper.insertSelective(projectFinancingLog);

          }

        //创建项目历史融资信息
        //获取
        ProjectFinancingHistory projectFinancingHistoryForSearch = new ProjectFinancingHistory();
        projectFinancingHistoryForSearch.setProjectSendLogId(String.valueOf(body.getProjectSourceId()));

        List<ProjectFinancingHistory> projectFinancingHistoryList = projectFinancingHistoryMapper.select(projectFinancingHistoryForSearch);
        if (projectFinancingHistoryList.size() > 0){
            ProjectFinancingHistory projectFinancingHistory = projectFinancingHistoryList.get(0);

            //解析一下输入值
            String sourceDate = projectFinancingHistory.getHistory();
            String sourceDateChange =  "{\"a\":" + sourceDate +"}";

            Map<String,Object> obj = JsonUtils.fromJsonToObject(sourceDateChange,new TypeReference<Map<String, Object>>(){});

            List<Map<String,Object>> alist = (List<Map<String,Object>>)obj.get("a");

            //拿到所有投资历史的大数组了
            for (Map<String,Object> map:alist){

                //解析轮次
                String lunci = "";
                List<Map<String,Object>> agency = (List<Map<String,Object>>)map.get("agency");
                for (Map<String,Object> age:agency){
                    boolean lunciBoolean = (boolean)age.get("checked");
                    if (lunciBoolean){
                        lunci = (String)age.get("name");
                    }
                }

                //解析融资币种
                Integer rzbizhong = 0;
                List<Map<String,Object>> rzCurrency = (List<Map<String,Object>>)map.get("rzCurrency");
                for (Map<String,Object> rzc:rzCurrency){
                    boolean rzbizhongBoolean = (boolean)rzc.get("checked");
                    if (rzbizhongBoolean){
                        rzbizhong = (int)rzc.get("value");
                    }
                }


                //解析估值币种
                Integer gzbizhong = 0;
                List<Map<String,Object>> gzCurrency = (List<Map<String,Object>>)map.get("gzCurrency");
                for (Map<String,Object> gzc:gzCurrency){
                    boolean gzbizhongBoolean = (boolean)gzc.get("checked");
                    if (gzbizhongBoolean){
                        gzbizhong = (int)gzc.get("value");
                    }
                }

                //融资金额转化
                String rzMoney = (String)map.get("rzMoney");
                BigDecimal rzMoneyBigDecimal = new BigDecimal(rzMoney);
                rzMoneyBigDecimal = rzMoneyBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);

                //估值金额转化
                String gzMoney = (String)map.get("gzMoney");
                BigDecimal gzMoneyBigDecimal = new BigDecimal(gzMoney);
                gzMoneyBigDecimal = gzMoneyBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);

                //融资时间处理
                String dateTime = (String)map.get("financingDate");
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date financyDte = null;
                try {
                    financyDte = new SimpleDateFormat("yyyy-MM-dd").parse(dateTime);
                }catch (Exception e){
                    log.error(e.getMessage(),e.fillInStackTrace());
                }


                //解析完毕开始创建数据
                ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();
                projectFinancingLog.setProjectId(xmid);
                projectFinancingLog.setStage(lunci);
                projectFinancingLog.setAmount(rzMoneyBigDecimal);
                projectFinancingLog.setCalculationAmountStatus(0);
                projectFinancingLog.setCurrency(rzbizhong);
                projectFinancingLog.setValuation(gzMoneyBigDecimal);
                projectFinancingLog.setStatus(2);
                projectFinancingLog.setApprovalStatus(1);
                projectFinancingLog.setApprovalTime(now);
                projectFinancingLog.setFinancingTime(financyDte);

                projectFinancingLogMapper.insertSelective(projectFinancingLog);

                Integer pflid = projectFinancingLog.getId();


                //解析投资人列表,并创建投资人信息
                List<Map<String,Object>> investorList = (List<Map<String,Object>>)map.get("touzilist");
                for (Map<String,Object> il:investorList){

                    String investorName = (String)il.get("tzname");

                    String shareRatio = (String)il.get("gfzhanbi");
                    BigDecimal shareRatioBigDecimal = new BigDecimal(shareRatio);
                    shareRatioBigDecimal = shareRatioBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);

                    ProjectFinancingHistoryInvestors projectFinancingHistoryInvestors = new ProjectFinancingHistoryInvestors();
                    projectFinancingHistoryInvestors.setProjectFinancingHistoryId(pflid);
                    projectFinancingHistoryInvestors.setInvestorName(investorName);
                    projectFinancingHistoryInvestors.setShareRatio(shareRatioBigDecimal);

                    projectFinancingHistoryInvestorsMapper.insertSelective(projectFinancingHistoryInvestors);
                }

            }

        }

        //创建项目团队成员
         //先获取团队成员
         ProjectSendTeamMember projectSendTeamMemberForSearch = new ProjectSendTeamMember();
         projectSendTeamMemberForSearch.setProjectSendLogsId(body.getProjectSourceId());
         projectSendTeamMemberForSearch.setYn(0);

         List<ProjectSendTeamMember> projectSendTeamMemberList = projectSendTeamMemberMapper.select(projectSendTeamMemberForSearch);
         if (projectSendTeamMemberList.size() > 0){
             for (ProjectSendTeamMember pst:projectSendTeamMemberList){
                 ProjectTeamMember projectTeamMember = new ProjectTeamMember();
                 projectTeamMember.setCreateTime(pst.getCreateTime());
                 projectTeamMember.setMumberDesc(pst.getMemberDesc());
                 projectTeamMember.setMumberDuties(pst.getMemberDuties());
                 projectTeamMember.setMumberName(pst.getMemberName());
                 projectTeamMember.setProjectId(xmid);
                 projectTeamMember.setShareRatio(pst.getShareRatio());
                 projectTeamMember.setYn(pst.getYn());

                 projectTeamMemberMapper.insertSelective(projectTeamMember);

                 //获取到刚创建的团队成员id
                 Integer ptmid = projectTeamMember.getId();

                 //获取提交审核中的团队成员教育经历，工作经历
                 ProjectSendTeamMemberEducation projectSendTeamMemberEducationForSearch = new ProjectSendTeamMemberEducation();
                 projectSendTeamMemberEducationForSearch.setProjectSendTeamMemberId(pst.getId());

                 ProjectSendTeamMemberWork projectSendTeamMemberWorkForSearch = new ProjectSendTeamMemberWork();
                 projectSendTeamMemberWorkForSearch.setProjectSendTeamMemberId(pst.getId());

                 List<ProjectSendTeamMemberEducation> projectSendTeamMemberEducationList = projectSendTeamMemberEducationMapper.select(projectSendTeamMemberEducationForSearch);
                 List<ProjectSendTeamMemberWork> projectSendTeamMemberWorkList = projectSendTeamMemberWorkMapper.select(projectSendTeamMemberWorkForSearch);

                 //创建教育经历
                 if (projectSendTeamMemberEducationList.size() > 0){
                     for (ProjectSendTeamMemberEducation pstme:projectSendTeamMemberEducationList){
                        ProjectTeamMemberEducation projectTeamMemberEducation =new ProjectTeamMemberEducation();
                        projectTeamMemberEducation.setProjectTeamMemberId(ptmid);
                        projectTeamMemberEducation.setEducationExperience(pstme.getEducationExperience());

                        projectTeamMemberEducationMapper.insertSelective(projectTeamMemberEducation);
                     }
                 }

                 //创建工作经历
                 if (projectSendTeamMemberWorkList.size() > 0){
                     for (ProjectSendTeamMemberWork pstmw:projectSendTeamMemberWorkList){
                         ProjectTeamMemberWork projectTeamMemberWork = new ProjectTeamMemberWork();
                         projectTeamMemberWork.setProjectTeamMemberId(ptmid);
                         projectTeamMemberWork.setWorkExperience(pstmw.getWorkExperience());

                         projectTeamMemberWorkMapper.insert(projectTeamMemberWork);
                     }
                 }

             }
         }
        Integer checkStatus = 0;
         switch (body.getAuditStatus()){
             case 0:checkStatus=5;
             break;
             case 1:checkStatus=2;
         }
         //修改申请记录为已审核
        ProjectSendLogs projectSendLogsForUpdate = new ProjectSendLogs();
         projectSendLogsForUpdate.setId(body.getProjectSourceId());
         projectSendLogsForUpdate.setCheckStatus(checkStatus);
         projectSendLogsForUpdate.setCheckTime(now);

         projectSendLogsMapper.updateByPrimaryKeySelective(projectSendLogsForUpdate);

         //创建审核记录表
        AdminProjectApprovalLog adminProjectApprovalLog = new AdminProjectApprovalLog();
        adminProjectApprovalLog.setApprovaledStatus(1);
        adminProjectApprovalLog.setApprovaledTime(now);
        adminProjectApprovalLog.setProjectSourceId(body.getProjectSourceId());
        adminProjectApprovalLog.setProjectId(xmid);
        adminProjectApprovalLog.setApprovaledAdminName(body.getAuditAdminName());
        adminProjectApprovalLog.setProjectSourceType(body.getProjctSourceType());
        adminProjectApprovalLog.setApprovaledDescription(body.getAuditDescription());

        adminProjectApprovalLogMapper.insert(adminProjectApprovalLog);

        //审核通过向主体库插入数据
        Subject subject =new Subject();
        subject.setSourceid(xmid);
        List<Subject> subjects=subjectMapper.select(subject);
        if(subjects.size() >0){
            //什么也不做
        }else{
            //向主体库插入数据
            subject.setFullName(projectSendLogs.getCompanyShortName());
            subject.setShortName(projectSendLogs.getCompanyShortName());
            subject.setPicture(projectSendLogs.getCompanyLogo());
            subject.setSummary(projectSendLogs.getCompanyOneSentenceIntroduct());
            subject.setSourceid(xmid);

            subjectMapper.insertSelective(subject);
            //向类型表插入关联关系
            SubjectTypeRelational subjectTypeRelational =new SubjectTypeRelational();

            subjectTypeRelational.setSubjectId(subject.getId());
            subjectTypeRelational.setSubjectTypeId(1);
            subjectTypeRelationalMapper.insertSelective(subjectTypeRelational);

        }



        String xmids =String.valueOf(xmid);
         result.setStatus(200);
         result.setData(null);
         result.setMessage(xmids);

        return result;
    }

    /**
     * 审核投资人提交项目方法
     * @param body
     * @return
     */
    private CommonDto<String> projectAuditOfTypeTwo(ProjectAuditInputDto body){
        CommonDto<String> result =new CommonDto<>();
        Date now = new Date();
        //获取到源记录的信息
        Integer xmtjid  = body.getProjectSourceId();

        InvestmentDataLog investmentDataLog = investmentDataLogMapper.selectByPrimaryKey(xmtjid);

        //先查询机构是否存在
        String investmentName = investmentDataLog.getInstitutionalName();
        InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
        investmentInstitutions.setShortName(investmentName);

        List<InvestmentInstitutions> investmentInstitutionsList = investmentInstitutionsMapper.select(investmentInstitutions);
        Integer jgid = -1;
        if (investmentInstitutionsList.size() > 0){
            //存在拿到id
            jgid = investmentInstitutionsList.get(0).getId();
        }else {
            //不存在创建机构
            InvestmentInstitutions investmentInstitutionsForInsert = new InvestmentInstitutions();
            investmentInstitutionsForInsert.setShortName(investmentName);
            investmentInstitutionsForInsert.setApprovalStatus(1);
            investmentInstitutionsForInsert.setApprovalTime(now);
            investmentInstitutionsForInsert.setCreateTime(now);
            investmentInstitutionsForInsert.setType(0);

            investmentInstitutionsMapper.insert(investmentInstitutionsForInsert);

            jgid = investmentInstitutionsForInsert.getId();
        }

        //创建项目
        Projects projects = new Projects();

        projects.setShortName(investmentDataLog.getShortName());
        projects.setFullName(investmentDataLog.getCompanyName());
        projects.setKernelDesc(investmentDataLog.getWordIntroduction());
        projects.setCity(investmentDataLog.getCity());
        projects.setCreateTime(now);
        projects.setInvestmentInstitutionsId(jgid);
        projects.setApprovalStatus(1);
        projects.setApprovalTime(now);
        projects.setProjectSource(1);

        projectsMapper.insertSelective(projects);

        Integer xmid = projects.getId();

        //创建项目创始人信息
        Founders founders = new Founders();
        founders.setProjectId(xmid);
        founders.setName(investmentDataLog.getCreateName());
        founders.setApprovalStatus(1);
        founders.setApprovalTime(now);
        founders.setYn(0);

        foundersMapper.insertSelective(founders);

        //拿到创始人的id
        Integer fdid = founders.getId();

        //创建创始人的教育经历和工作经历
        FoundersEducation foundersEducation = new FoundersEducation();
        FoundersWork foundersWork = new FoundersWork();

        DataLogEducation dataLogEducation = new DataLogEducation();
        dataLogEducation.setLogId(investmentDataLog.getId());
        //教育经历
        List<DataLogEducation> dataLogEducationList = dataLogEducationMapper.select(dataLogEducation);
        if (dataLogEducationList.size() > 0){
            for (DataLogEducation de:dataLogEducationList) {
                FoundersEducation foundersEducationForInsert = new FoundersEducation();
                foundersEducationForInsert.setFounderId(fdid);
                foundersEducationForInsert.setEducationExperience(de.getEducationName());
                foundersEducationMapper.insertSelective(foundersEducationForInsert);
            }
        }

        DataLogWork dataLogWork = new DataLogWork();
        dataLogWork.setLogId(investmentDataLog.getId());
        //工作经历
        List<DataLogWork> dataLogWorkList = dataLogWorkMapper.select(dataLogWork);
        if (dataLogWorkList.size() > 0){
            for (DataLogWork de:dataLogWorkList) {
                FoundersWork foundersWorkForInsert = new FoundersWork();
                foundersWorkForInsert.setFounderId(fdid);
                foundersWorkForInsert.setWorkExperience(de.getWorkName());

                foundersWorkMapper.insertSelective(foundersWorkForInsert);
            }
        }
        //项目所属领域
        DataLogDomain dataLogDomain =new DataLogDomain();
        dataLogDomain.setLogId(xmid);
        List<DataLogDomain>dataLogDomainList=dataLogDomainMapper.select(dataLogDomain);
        if (dataLogDomainList.size() > 0){
            for (DataLogDomain de:dataLogDomainList) {
               ProjectSegmentation projectSegmentation =new ProjectSegmentation();
                projectSegmentation.setProjectId(xmid);
                projectSegmentation.setSegmentationName(de.getDomainName());
                projectSegmentationMapper.insertSelective(projectSegmentation);
            }
        }
        //融资需求
        ProjectFinancingLog projectFinancingLog =new ProjectFinancingLog();
        projectFinancingLog.setProjectId(xmid);
        projectFinancingLog.setStage(investmentDataLog.getStage());
        projectFinancingLog.setAmount(investmentDataLog.getAmont());
        projectFinancingLog.setCurrency(investmentDataLog.getYn());
        projectFinancingLog.setStockRight(investmentDataLog.getStockRight());
        projectFinancingLog.setFinancingTime(investmentDataLog.getFinanTime());
        projectFinancingLogMapper.insert(projectFinancingLog);

        //审核表
        AdminProjectApprovalLog adminProjectApprovalLog =new AdminProjectApprovalLog();
        adminProjectApprovalLog.setProjectId(xmid);
        adminProjectApprovalLog.setProjectSourceId(xmtjid);
        adminProjectApprovalLog.setApprovaledStatus(body.getAuditStatus());
        adminProjectApprovalLog.setProjectSourceType(1);
        adminProjectApprovalLog.setApprovaledTime(new Date());
        adminProjectApprovalLogMapper.insert(adminProjectApprovalLog);

        investmentDataLog.setAuditYn(body.getAuditStatus());
        investmentDataLog.setAuditTime(new Date());

        investmentDataLogMapper.updateByPrimaryKey(investmentDataLog);
        result.setStatus(200);
        result.setData(null);
        result.setMessage("success");
        return result;
    }
    /**
     * 相似竞品
     */
	@Override
	public CommonDto<List<Map<String, Object>>> findProject(Integer id) {
		 CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
        Projects projects =new Projects();
        projects.setId(id);
        projects  = projectsMapper.selectByPrimaryKey(id);
        String city = projects.getCity();
        String shortName = projects.getShortName();
        ProjectSegmentation projectSegmentation =new ProjectSegmentation();
        projectSegmentation.setProjectId(id);
        List<ProjectSegmentation> ps = projectSegmentationMapper.select(projectSegmentation);
        List<String> a=new LinkedList<String>();
        String [] educationArray =null;
        if(ps.size()>0){
        for(ProjectSegmentation d:ps){
            a.add(d.getSegmentationName());
            educationArray= new String[a.size()];
            educationArray=a.toArray(educationArray);
        }
        }
        ProjectFinancingLog projectFinancingLog =new  ProjectFinancingLog();
        projectFinancingLog.setProjectId(id);
        List<ProjectFinancingLog> psl = projectFinancingLogMapper.select(projectFinancingLog);
        List<String> a1=new LinkedList<String>();
        String [] pslArray =null;
        if(psl.size()>0){
        for(ProjectFinancingLog d:psl){
            a1.add(d.getStage());
            pslArray= new String[a1.size()];
            pslArray=a1.toArray(pslArray);
        }
        }
        List<Map<String, Object>> likes = projectsMapper.findLikes(educationArray,city,pslArray,shortName );
        List<Map<String, Object>> like2 =new ArrayList<>();
       /* for(Map<String, Object> map :likes){
       	 if(Integer.valueOf(String.valueOf(map.get("id"))) == id){
       		 likes.remove(map);
       	 }
        }
        like2.addAll(likes);*/
        result.setData(likes);
		return result;
	}
    /**
     * 相似竞品新接口
     * @param id
     * @return
     */
    @Override
    public CommonDto<List<XiangsiDto>> findProjectall(int id,Integer pageNumber,Integer pageSize) {
		 CommonDto<List<XiangsiDto>>  result = new  CommonDto<List<XiangsiDto>> ();

		 if (pageNumber == null || pageNumber < 1){
		     pageNumber = pageNum;
         }

         if (pageSize == null || pageSize < 0){
		     pageSize = pageSizeDefalut;
         }

        Projects projects =new Projects();
        projects.setId(id);
        projects  = projectsMapper.selectByPrimaryKey(id);
        String city = projects.getCity();
        String shortName = projects.getShortName();
        ProjectSegmentation projectSegmentation =new ProjectSegmentation();
        projectSegmentation.setProjectId(id);
        List<ProjectSegmentation> ps = projectSegmentationMapper.select(projectSegmentation);
        List<String> a=new LinkedList<String>();
        String [] educationArray =null;
        if(ps.size()>0){
        for(ProjectSegmentation d:ps){
            a.add(d.getSegmentationName());
            educationArray= new String[a.size()];
            educationArray=a.toArray(educationArray);
        }
        }
        ProjectFinancingLog projectFinancingLog =new  ProjectFinancingLog();
        projectFinancingLog.setProjectId(id);
        List<ProjectFinancingLog> psl = projectFinancingLogMapper.select(projectFinancingLog);
        List<String> a1=new LinkedList<String>();
        String [] pslArray =null;
        if(psl.size()>0){
        for(ProjectFinancingLog d:psl){
            a1.add(d.getStage());
            pslArray= new String[a1.size()];
            pslArray=a1.toArray(pslArray);
        }
        }

        //获取项目标签
        String projectTags = projects.getItemLabel();
        String[] projectTagArry = null;
        if (projectTags != null && projectTags != ""){
            projectTagArry = projectTags.split("、");
        }


        int startPage = (pageNumber-1)*pageSize;

        List<XiangsiDto> likes = new ArrayList<>();
        if (educationArray != null && projectTagArry != null){
            likes = projectsMapper.findLikesall(educationArray,projectTagArry,shortName,id,startPage,pageSize);
        }


        //处理城市为空,轮次为空的情况
        for (XiangsiDto xd:likes){
            if (xd.getCity() == null){
                xd.setCity("");
            }

            if (xd.getStage() == null){
                xd.setStage("");
            }
        }

        result.setData(likes);
		return result;
	}

    /**
     * 查询项目关注状态
     * @param id
     * @param token
     * @return
     */

	@Override
	public CommonDto<Map<String, Object>> findFollow(Integer id,String token) {
        CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();
        Map<String, Object> map =new HashedMap();
        if(token != null){
            String userId =token;

           Follow follow = followMapper.findfollowyn(userId,id);
           if(follow !=null){
               if(follow.getStatus() == 1){
                   map.put("followstatus",true);
               }else{
                   map.put("followstatus",false);
               }

           }else{
           map.put("followstatus",false);
           }
        }else{
            result.setStatus(50001);
            result.setMessage("token不存在");
        }

       result.setData(map);
		return result;
	}

    /**
     * 管理员添加项目管理员接口
     * @param projectId 项目id
     * @param userId 用户id
     * @return
     */
    @Override
    public CommonDto<String> adminAddAdministractor(Integer projectId,Integer userId){
	    CommonDto<String> result = new CommonDto<>();

	    if (projectId == null){
	        result.setData(null);
	        result.setStatus(50001);
	        result.setMessage("项目id不能为空");

	        return result;
        }

        if (userId == null){
	        result.setMessage("用户id不能为空");
	        result.setStatus(50001);
	        result.setData(null);

	        return result;
        }

	    Date now = new Date();
	    ProjectAdministrator projectAdministratorForInsert = new ProjectAdministrator();
	    projectAdministratorForInsert.setUserId(userId);
	    //设置为1表示是普通管理员吗？？？？？
	    projectAdministratorForInsert.setTypes(1);
	    projectAdministratorForInsert.setCreateTime(now);
	    projectAdministratorForInsert.setProjectsId(projectId);
	    projectAdministratorForInsert.setYn(0);

	    projectAdministratorMapper.insert(projectAdministratorForInsert);

	    result.setData(null);
	    result.setStatus(200);
	    result.setMessage("success");

	    return result;
    }
}


