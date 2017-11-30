package com.lhjl.tzzs.proxy.service.impl;

import com.github.pagehelper.Page;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectsSendDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import com.lhjl.tzzs.proxy.service.ProjectInformationDetailsService;
import com.lhjl.tzzs.proxy.service.common.CommonUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zyy on 2017/11/27.
 */
@Service
public class ProjectInformationDetailsServiceImpl implements ProjectInformationDetailsService {
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private ProjectSendLogsMapper projectSendLogsMapper;
    //关系表
    @Resource
    private ProjectFinancingInvestmentInstitutionRelationshipMapper projectFinancingInvestmentInstitutionRelationshipMapper;

    //项目融资申请表
    @Resource
    private ProjectFinancingApprovalMapper projectFinancingApprovalMapper;
    //历史表
    @Resource
    private ProjectFinancingHistoryMapper projectFinancingHistoryMapper;
    @Resource
    private EvaluateService evaluateService;
    //创收人
    @Resource
    private FoundersMapper foundersMapper;
    //创始人教育
    @Resource
    private FoundersEducationMapper foundersEducationMapper;
    //创始人工作
    @Resource
    private FoundersWorkMapper foundersWorkMapper;
    //相似精品
    @Resource
    private ProjectSendLogCompetingMapper projectSendLogCompetingMapper;

    @Resource
    private ProjectSendTeamMemberMapper projectSendTeamMemberMapper;

    //团队教育背景
    @Resource
    private ProjectSendTeamMemberEducationMapper projectSendTeamMemberEducationMapper;

    //团队工作背景
    @Resource
    private ProjectSendTeamMemberWorkMapper projectSendTeamMemberWorkMapper;

    @Resource
    private ProjectsMapper projectsMapper;

    @Resource
    private ProjectSegmentationMapper projectSegmentationMapper;

    @Resource
    private ProjectTeamMemberMapper projectTeamMemberMapper;

    @Resource
    private ProjectTeamMemberEducationMapper projectTeamMemberEducationMapper;


    @Resource
    private ProjectTeamMemberWorkMapper projectTeamMemberWorkMapper;

    @Autowired
    private CommonUserService  commonUserService;

    @Resource
    private ProjectCompetitiveProductsMapper projectCompetitiveProductsMapper;

    /**
     * 项目详情页面进行修改
     * @param params
     * @param userId
     * @return
     */
			@Override
			public CommonDto<String> informationDetails(ProjectsSendDto params, int userId) {
			    CommonDto<String> result =new CommonDto<>();
                    Projects projects = new Projects();
                    projects.setId(params.getPid());
                    projects = projectsMapper.selectOne(projects);
                    //更新主库
                    if (projects != null) {
                        projects.setShortName(params.getCompanyshortname());
                        projects.setFullName(params.getTuisongxiangmubiao7companywhe());
                        projects.setKernelDesc(params.getOneintroduct());
                        projects.setCommet(params.getTuisongxiangmubiao7companypro());
                        projects.setUrl(params.getWebsite());
                        projects.setItemLabel(params.getTuisongxiangmubiao7projecttag());
                        projects.setCity(params.getTuisongxiangmubiao7city());
                        projects.setProjectLogo(params.getLogo());
                        projects.setProjectInvestmentHighlights(params.getHighlights());
                        projectsMapper.updateByPrimaryKey(projects);
                        ProjectSegmentation ps = new ProjectSegmentation();
                        ps.setProjectId(projects.getId());
                        projectSegmentationMapper.delete(ps);
                        String[] fieldArry = params.getTuisongxiangmubiao7industryfi().split(",");
                        List<ProjectSegmentation> dataLogWorklist = new Page<ProjectSegmentation>();
                        for (int b = 0; b < fieldArry.length; b++) {
                            ProjectSegmentation ps1 = new ProjectSegmentation();
                            ps1.setSegmentationName(fieldArry[b]);
                            ps1.setProjectId(projects.getId());
                            dataLogWorklist.add(ps1);
                        }
                        projectSegmentationMapper.insertList(dataLogWorklist);
                        //相似竞品
                        ProjectSendLogCompeting projectSendLogCompeting = new ProjectSendLogCompeting();
                        List<ProjectSendLogCompeting> dataLogWorklist1 = new Page<ProjectSendLogCompeting>();
                        String competing = params.getCompeting();
                        projectSendLogCompeting.setProjectSendLogsId(Integer.parseInt(params.getXmid()));
                        List<ProjectSendLogCompeting> one = projectSendLogCompetingMapper.select(projectSendLogCompeting);
                        if(competing ==null && "".equals(competing)){
                            projectSendLogCompetingMapper.delete(projectSendLogCompeting);
                        }else {
                            if (one.size() > 0) {
                                projectSendLogCompetingMapper.delete(projectSendLogCompeting);
                                ProjectSendLogCompeting dataLogEducation2 = new ProjectSendLogCompeting();
                                dataLogEducation2.setProjectSendLogsId(projects.getId());
                                projectSendLogCompetingMapper.delete(dataLogEducation2);
                                String[] fieldArry1 = competing.split(",");
                                for (int b = 0; b < fieldArry1.length; b++) {
                                    ProjectSendLogCompeting projectSendLogCompeting1 = new ProjectSendLogCompeting();
                                    projectSendLogCompeting1.setCompetingProductName(fieldArry1[b]);
                                    projectSendLogCompeting1.setProjectSendLogsId(Integer.parseInt(params.getXmid()));
                                    dataLogWorklist1.add(projectSendLogCompeting1);
                                }
                                projectSendLogCompetingMapper.insertList(dataLogWorklist1);
                            } else {
                                String[] fieldArry1 = competing.split(",");
                                for (int b = 0; b < fieldArry1.length; b++) {
                                    ProjectSendLogCompeting projectSendLogCompeting1 = new ProjectSendLogCompeting();
                                    projectSendLogCompeting1.setCompetingProductName(fieldArry1[b]);
                                    projectSendLogCompeting1.setProjectSendLogsId(Integer.parseInt(params.getXmid()));
                                    dataLogWorklist1.add(projectSendLogCompeting1);
                                }
                                projectSendLogCompetingMapper.insertList(dataLogWorklist1);
                            }
                        }
                        ProjectCompetitiveProducts projectCompetitiveProducts =new ProjectCompetitiveProducts();
                        projectCompetitiveProducts.setProjectId(projects.getId());
                        List<ProjectCompetitiveProducts> list =projectCompetitiveProductsMapper.select(projectCompetitiveProducts);
                        if(list.size()>0) {
                            projectCompetitiveProductsMapper.delete(projectCompetitiveProducts);
                        }
                        List<ProjectCompetitiveProducts> dataLogWorklist2 = new Page<ProjectCompetitiveProducts>();
                        for(ProjectSendLogCompeting ee :dataLogWorklist1){
                            ProjectCompetitiveProducts projectCompetitiveProducts1 =new ProjectCompetitiveProducts();
                            projectCompetitiveProducts1.setProjectId(projects.getId());
                            projectCompetitiveProducts1.setCompetitiveProductsName(ee.getCompetingProductName());
                            dataLogWorklist2.add(projectCompetitiveProducts1);

                        }
                        projectCompetitiveProductsMapper.insertList(dataLogWorklist2);
                        //团队成员
                        ProjectSendTeamMember projectSendTeamMember = new ProjectSendTeamMember();
                        projectSendTeamMember.setProjectSendLogsId(Integer.parseInt(params.getXmid()));
                        List<ProjectSendTeamMember> projectSendTeamMemberList = projectSendTeamMemberMapper.select(projectSendTeamMember);
                        ProjectTeamMember t = new ProjectTeamMember();
                        t.setProjectId(projects.getId());
                        projectTeamMemberMapper.delete(t);
                        if (projectSendTeamMemberList.size() > 0){
                            for (ProjectSendTeamMember pst:projectSendTeamMemberList){
                                ProjectTeamMember projectTeamMember = new ProjectTeamMember();
                                projectTeamMember.setCreateTime(pst.getCreateTime());
                                projectTeamMember.setMumberDesc(pst.getMemberDesc());
                                projectTeamMember.setMumberDuties(pst.getMemberDuties());
                                projectTeamMember.setMumberName(pst.getMemberName());
                                projectTeamMember.setProjectId(projects.getId());
                                projectTeamMember.setShareRatio(pst.getShareRatio());
                                projectTeamMember.setYn(0);

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

                                ProjectTeamMemberEducation projectSendTeamMemberEducationForSearch1 = new ProjectTeamMemberEducation();
                                projectSendTeamMemberEducationForSearch1.setProjectTeamMemberId(pst.getId());


                                ProjectTeamMemberWork projectSendTeamMemberWorkForSearch1 = new ProjectTeamMemberWork();
                                projectSendTeamMemberWorkForSearch1.setProjectTeamMemberId(pst.getId());

                                List<ProjectTeamMemberEducation> projectSendTeamMemberEducationList1 = projectTeamMemberEducationMapper.select(projectSendTeamMemberEducationForSearch1);
                                List<ProjectTeamMemberWork> projectSendTeamMemberWorkList1 = projectTeamMemberWorkMapper.select(projectSendTeamMemberWorkForSearch1);
                                if(projectSendTeamMemberEducationList1.size()>0){
                                    projectTeamMemberEducationMapper.delete(projectSendTeamMemberEducationForSearch1);

                                }
                                if(projectSendTeamMemberWorkList1.size()>0){
                                    projectTeamMemberWorkMapper.delete(projectSendTeamMemberWorkForSearch1);

                                }
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
                    }else{
                        ProjectSendLogCompeting projectSendLogCompeting = new ProjectSendLogCompeting();
                        List<ProjectSendLogCompeting> dataLogWorklist1 = new Page<ProjectSendLogCompeting>();
                        String competing = params.getCompeting();
                        projectSendLogCompeting.setProjectSendLogsId(Integer.parseInt(params.getXmid()));
                        List<ProjectSendLogCompeting> one = projectSendLogCompetingMapper.select(projectSendLogCompeting);
                        if(competing ==null && "".equals(competing)){
                            projectSendLogCompetingMapper.delete(projectSendLogCompeting);
                        }else {
                            if (one.size() > 0) {
                                projectSendLogCompetingMapper.delete(projectSendLogCompeting);
                                String[] fieldArry1 = competing.split(",");
                                for (int b = 0; b < fieldArry1.length; b++) {
                                    ProjectSendLogCompeting projectSendLogCompeting1 = new ProjectSendLogCompeting();
                                    projectSendLogCompeting1.setCompetingProductName(fieldArry1[b]);
                                    projectSendLogCompeting1.setProjectSendLogsId(Integer.parseInt(params.getXmid()));
                                    dataLogWorklist1.add(projectSendLogCompeting1);
                                }
                                projectSendLogCompetingMapper.insertList(dataLogWorklist1);
                            } else {
                                String[] fieldArry1 = competing.split(",");
                                for (int b = 0; b < fieldArry1.length; b++) {
                                    ProjectSendLogCompeting projectSendLogCompeting1 = new ProjectSendLogCompeting();
                                    projectSendLogCompeting1.setCompetingProductName(fieldArry1[b]);
                                    projectSendLogCompeting1.setProjectSendLogsId(Integer.parseInt(params.getXmid()));
                                    dataLogWorklist1.add(projectSendLogCompeting1);
                                }
                                projectSendLogCompetingMapper.insertList(dataLogWorklist1);
                            }
                        }
                    }
                    //更新用户信息
                    Users users = new Users();
                    users.setId(userId);
                    users = usersMapper.selectByPrimaryKey(users);
                    users.setCompanyName(params.getTuisongxiangmubiao7companywhe());
                    users.setCompanyDesc(params.getTuisongxiangmubiao7companypro());
                    users.setCompanyDuties(params.getTuisongxiangmubiao7assumeoffi());
                    users.setActualName(params.getTuisongxiangmubiao7realname());
                    users.setCity(params.getTuisongxiangmubiao7city());
                    users.setDesc(params.getTuisongxiangmubiao7profile());
                    if (params.getTuisongxiangmubiao7mailbox() != null && !"".equals(params.getTuisongxiangmubiao7mailbox())) {
                        users.setEmail(params.getTuisongxiangmubiao7mailbox());
                    }
                    if (params.getTuisongxiangmubiao7wechatnumb() != null && !"".equals(params.getTuisongxiangmubiao7wechatnumb())) {
                        users.setWechat(params.getTuisongxiangmubiao7wechatnumb());
                    }
                    usersMapper.updateByPrimaryKey(users);
                    ProjectSendLogs projectSendLogs = new ProjectSendLogs();
                    projectSendLogs.setId(Integer.parseInt(params.getXmid()));
                    projectSendLogs = projectSendLogsMapper.selectByPrimaryKey(projectSendLogs);
                    projectSendLogs.setUserid(userId);
                    projectSendLogs.setCity(params.getTuisongxiangmubiao7city());
                    projectSendLogs.setCompanyName(params.getTuisongxiangmubiao7companywhe());
                    projectSendLogs.setCompanyDesc(params.getTuisongxiangmubiao7companypro());
                    projectSendLogs.setField(params.getTuisongxiangmubiao7industryfi());
                    String [] woel ={};
                    if (params.getTuisongxiangmubiao7projecttag() != null && !"".equals(params.getTuisongxiangmubiao7projecttag())) {
                        projectSendLogs.setProjectTags(params.getTuisongxiangmubiao7projecttag());
                    }else{
                        projectSendLogs.setProjectTags("");
                    }
                    projectSendLogs.setCompanyLogo(params.getLogo());
                    if (params.getCompanyshortname() != null || "".equals(params.getCompanyshortname())) {
                        projectSendLogs.setCompanyShortName(params.getCompanyshortname());
                    } else {
                        result.setStatus(50001);
                        result.setMessage("公司简称不能为空");
                    }

                    if (params.getOneintroduct() != null || "".equals(params.getOneintroduct())) {
                        projectSendLogs.setCompanyOneSentenceIntroduct(params.getOneintroduct());
                    } else {
                        result.setStatus(50001);
                        result.setMessage("一句话介绍不能为空");
                    }

                    if (params.getHighlights() != null || "".equals(params.getHighlights())) {
                        projectSendLogs.setCompanyInvestmentHighlights(params.getHighlights());
                    } else {
                        result.setStatus(50001);
                        result.setMessage("投资亮点不能为空");
                    }
                    projectSendLogs.setCompanyOfficialWebsite(params.getWebsite());
                    projectSendLogsMapper.updateByPrimaryKey(projectSendLogs);
                    int projectId = projectSendLogs.getId();
                    projectSendTeamMemberMapper.updateTeame(projectId,userId);
                ProjectFinancingHistory projectFinancingHistory =new ProjectFinancingHistory();
                projectFinancingHistory.setProjectSendLogId(params.getXmid());
                projectFinancingHistory.setUserId(userId);
                projectFinancingHistory=projectFinancingHistoryMapper.selectOne(projectFinancingHistory);
                if(projectFinancingHistory != null){
                    ProjectFinancingHistory projectFinancingHistory3 =new ProjectFinancingHistory();
                    projectFinancingHistory3.setProjectSendLogId("-1");
                    projectFinancingHistory3.setUserId(userId);
                    projectFinancingHistory3=projectFinancingHistoryMapper.selectOne(projectFinancingHistory3);
                    if( projectFinancingHistory3 !=null) {
                        projectFinancingHistoryMapper.delete(projectFinancingHistory);
                        projectFinancingHistoryMapper.updateHistory(projectId, userId);
                    }
                }else {
                    ProjectFinancingHistory projectFinancingHistory3 =new ProjectFinancingHistory();
                    projectFinancingHistory3.setProjectSendLogId("-1");
                    projectFinancingHistory3.setUserId(userId);
                    projectFinancingHistory3=projectFinancingHistoryMapper.selectOne(projectFinancingHistory3);
                    if( projectFinancingHistory3 !=null) {
                        projectFinancingHistoryMapper.updateHistory(projectId, userId);
                    }
                }

                    //更新融资历史
                    ProjectFinancingApproval oldProjectFinancingApproval = new ProjectFinancingApproval();
                    oldProjectFinancingApproval.setProjectSendLogId(projectId);
                    oldProjectFinancingApproval.setUserId(userId);
                    oldProjectFinancingApproval = projectFinancingApprovalMapper.selectOne(oldProjectFinancingApproval);
                    if (oldProjectFinancingApproval != null) {
                        if (params.getTuisongxiangmubiao7currentdem() != null && !"".equals(params.getTuisongxiangmubiao7currentdem())) {
                            oldProjectFinancingApproval.setCurrentDemand(params.getTuisongxiangmubiao7currentdem());
                        }
                        oldProjectFinancingApproval.setFinancingRounds(params.getTuisongxiangmubiao7roundoffin());
                        oldProjectFinancingApproval.setFinancingAmount(new BigDecimal(params.getTuisongxiangmubiao7financinga()));
                        oldProjectFinancingApproval.setFinancingCurrency(Integer.parseInt(params.getTuisongxiangmubiao7financingu()));
                        oldProjectFinancingApproval.setTransferShares(new BigDecimal(params.getTuisongxiangmubiao7sellingsha()));
                        projectFinancingApprovalMapper.updateByPrimaryKey(oldProjectFinancingApproval);
                    } else {
                        ProjectFinancingApproval projectFinancingApproval1 = new ProjectFinancingApproval();
                        projectFinancingApproval1.setProjectSendLogId(projectId);
                        projectFinancingApproval1.setUserId(userId);
                        if (params.getTuisongxiangmubiao7currentdem() != null && !"".equals(params.getTuisongxiangmubiao7currentdem())) {
                            projectFinancingApproval1.setCurrentDemand(params.getTuisongxiangmubiao7currentdem());
                        }
                        projectFinancingApproval1.setFinancingRounds(params.getTuisongxiangmubiao7roundoffin());
                        projectFinancingApproval1.setFinancingAmount(new BigDecimal(params.getTuisongxiangmubiao7financinga()));
                        projectFinancingApproval1.setFinancingCurrency(Integer.parseInt(params.getTuisongxiangmubiao7financingu()));
                        projectFinancingApproval1.setTransferShares(new BigDecimal(params.getTuisongxiangmubiao7sellingsha()));
                        projectFinancingApprovalMapper.insert(projectFinancingApproval1);
                    }
                    int foundersId = 0;
                    Founders founders = new Founders();
                    founders.setUserId(userId);
                    founders = foundersMapper.selectOne(founders);
                    if (founders != null) {
                        foundersId = founders.getId();
                    } else {
                        //插入
                        Founders newFounders = new Founders();
                        newFounders.setUserId(userId);
                        foundersMapper.insert(newFounders);
                        foundersId = newFounders.getId();

                    }

                    //更新教育经历
                    if (params.getTuisongxiangmubiao7educationa() != null && !"".equals(params.getTuisongxiangmubiao7educationa())) {
                        String[] educations = params.getTuisongxiangmubiao7educationa().split(",");
                        //删除之前的记录
                        FoundersEducation old = new FoundersEducation();
                        old.setFounderId(foundersId);
                        foundersEducationMapper.delete(old);
                        for (String education : educations) {
                            FoundersEducation foundersEducation = new FoundersEducation();
                            foundersEducation.setFounderId(foundersId);
                            foundersEducation.setEducationExperience(education);
                            foundersEducationMapper.insert(foundersEducation);
                        }
                    }else{
                        FoundersEducation foundersEducation = new FoundersEducation();
                        foundersEducation.setFounderId(foundersId);
                        foundersEducationMapper.delete( foundersEducation);
                    }

                    //更新工作经历
                    if (params.getTuisongxiangmubiao7workexperi() != null && !"".equals(params.getTuisongxiangmubiao7workexperi())) {
                        String[] works = params.getTuisongxiangmubiao7workexperi().split(",");
                        //删除之前的记录
                        FoundersWork old = new FoundersWork();
                        old.setFounderId(foundersId);
                        foundersWorkMapper.delete(old);
                        for (String work : works) {
                            FoundersWork foundersWork = new FoundersWork();
                            foundersWork.setFounderId(foundersId);
                            foundersWork.setWorkExperience(work);
                            foundersWorkMapper.insert(foundersWork);
                        }
                    }else{
                        FoundersWork foundersWork = new FoundersWork();
                        foundersWork.setFounderId(foundersId);
                        foundersWorkMapper.delete(foundersWork);
                    }
			return result;
		}
}


