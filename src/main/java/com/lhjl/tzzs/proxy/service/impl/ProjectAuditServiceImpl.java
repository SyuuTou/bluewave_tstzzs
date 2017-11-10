package com.lhjl.tzzs.proxy.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAuditInputDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.ProjectAuditService;
import com.lhjl.tzzs.proxy.utils.JsonUtils;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProjectAuditServiceImpl implements ProjectAuditService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProjectAuditServiceImpl.class);

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
        //获取项目是否在数据库已经存在
        ProjectSendLogs projectSendLogs = projectSendLogsMapper.selectByPrimaryKey(body.getProjectSourceId());

        Projects projects = new Projects();
        projects.setShortName(projectSendLogs.getCompanyShortName());

        List<Projects> projectsListForC =projectsMapper.select(projects);

        if (projectsListForC.size() > 0){
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

        }else {
            //根据项目来源创建项目信息
            if (body.getProjctSourceType() == 0){
                result  = projectAuditOfTypeOne(body);
            }else if(body.getProjctSourceType() == 1){
                result = projectAuditOfTypeTwo(body);
            }else {
                result.setMessage("项目源类型不存在");
                result.setData(null);
                result.setStatus(50001);
            }
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
        projects.setItemLabel(projectSendLogs.getProjectTags());
        projects.setProjectSource(0);

        projectsMapper.insertSelective(projects);

        //拿到项目id
        Integer xmid = projects.getId();

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
              projectFinancingLog.setApprovalTime(now);

              projectFinancingLogMapper.insertSelective(projectFinancingLog);

          }

        //创建项目历史融资信息
        //获取
        ProjectFinancingHistory projectFinancingHistoryForSearch = new ProjectFinancingHistory();
        projectFinancingHistoryForSearch.setProjectSendLogId(String.valueOf(body.getProjectSourceId()));

        List<ProjectFinancingHistory> projectFinancingHistoryList = projectFinancingHistoryMapper.select(projectFinancingHistoryForSearch);
        if (projectFinancingApprovalList.size() > 0){
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

         result.setStatus(200);
         result.setData(null);
         result.setMessage("success");

        return result;
    }

    /**
     * 审核投资人提交项目
     * @param body
     * @return
     */
    private CommonDto<String> projectAuditOfTypeTwo(ProjectAuditInputDto body){
        CommonDto<String> result =new CommonDto<>();

        return result;
    }

}
