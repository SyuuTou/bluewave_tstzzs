package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.dto.ProjectsSendDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import com.lhjl.tzzs.proxy.service.ProjectsSendService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by 蓝海巨浪 on 2017/10/23.
 */
@Service
public class ProjectsSendServiceImpl implements ProjectsSendService{

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private ProjectSendLogsMapper projectSendLogsMapper;
    @Resource
    private ProjectFinancingInvestmentInstitutionRelationshipMapper projectFinancingInvestmentInstitutionRelationshipMapper;
    @Resource
    private ProjectFinancingApprovalMapper projectFinancingApprovalMapper;
    @Resource
    private ProjectFinancingHistoryMapper projectFinancingHistoryMapper;
    @Resource
    private EvaluateService evaluateService;
    @Resource
    private FoundersMapper foundersMapper;
    @Resource
    private FoundersEducationMapper foundersEducationMapper;
    @Resource
    private FoundersWorkMapper foundersWorkMapper;

    /**
     * 项目投递
     * @param params 投递参数
     * @param userId 用户ID
     * @return
     */
    @Override
    public CommonDto<String> ctuisongsecond(ProjectsSendDto params, int userId) {
        CommonDto<String> result = new CommonDto<>();

        //更新用户表数据
        Users users = new Users();
        users.setId(userId);
        users = usersMapper.selectByPrimaryKey(users);
        users.setCompanyName(params.getTuisongxiangmubiao7companywhe());
        users.setCompanyDesc(params.getTuisongxiangmubiao7companypro());
        users.setCompanyDuties(params.getTuisongxiangmubiao7assumeoffi());
        users.setActualName(params.getTuisongxiangmubiao7realname());
        users.setCity(params.getTuisongxiangmubiao7city());
        users.setDesc(params.getTuisongxiangmubiao7profile());
        if(params.getTuisongxiangmubiao7mailbox() != null && !"".equals(params.getTuisongxiangmubiao7mailbox())){
            users.setEmail(params.getTuisongxiangmubiao7mailbox());
        }
        if(params.getTuisongxiangmubiao7wechatnumb() != null && !"".equals(params.getTuisongxiangmubiao7wechatnumb())){
            users.setWechat(params.getTuisongxiangmubiao7wechatnumb());
        }
        if("投资人".equals(params.getTuisongxiangmubiao7identityty())){
            users.setIdentityType(0);
        }
        if("创业者".equals(params.getTuisongxiangmubiao7identityty())){
            users.setIdentityType(1);
        }
        if("产业公司".equals(params.getTuisongxiangmubiao7identityty())){
            users.setIdentityType(2);
        }
        if("媒体".equals(params.getTuisongxiangmubiao7identityty())){
            users.setIdentityType(3);
        }
        if("研究机构".equals(params.getTuisongxiangmubiao7identityty())){
            users.setIdentityType(4);
        }
        usersMapper.updateByPrimaryKey(users);

        //更新项目投递记录
        ProjectSendLogs projectSendLogs = new ProjectSendLogs();
        projectSendLogs.setId(Integer.parseInt(params.getXmid()));
        projectSendLogs = projectSendLogsMapper.selectByPrimaryKey(projectSendLogs);
        projectSendLogs.setUserid(userId);
        projectSendLogs.setCity(params.getTuisongxiangmubiao7city());
        projectSendLogs.setCompanyName(params.getTuisongxiangmubiao7companywhe());
        projectSendLogs.setCompanyDesc(params.getTuisongxiangmubiao7companypro());
        projectSendLogs.setField(params.getTuisongxiangmubiao7industryfi());
        if(params.getTuisongxiangmubiao7projecttag() != null && !"".equals(params.getTuisongxiangmubiao7projecttag())){
            projectSendLogs.setProjectTags(params.getTuisongxiangmubiao7projecttag());
        }
        projectSendLogs.setCreatTime(new Date());
        projectSendLogsMapper.updateByPrimaryKey(projectSendLogs);
        int projectId = projectSendLogs.getId();

        //更新投递项目-机构关系表
        //插入
        String[] investmentIds = params.getTsid().split(",");
        for(String investmentId : investmentIds){
            ProjectFinancingInvestmentInstitutionRelationship relationship = new ProjectFinancingInvestmentInstitutionRelationship();
            relationship.setProjectSendLogId(projectId);
            relationship.setInvestmentInstitutionId(Integer.parseInt(investmentId));
            relationship.setAssociatedTime(new Date());
            projectFinancingInvestmentInstitutionRelationshipMapper.insert(relationship);
        }

        //更新融资申请表
        ProjectFinancingApproval oldProjectFinancingApproval = new ProjectFinancingApproval();
        oldProjectFinancingApproval.setProjectSendLogId(projectId);
        oldProjectFinancingApproval.setUserId(userId);
        oldProjectFinancingApproval = projectFinancingApprovalMapper.selectOne(oldProjectFinancingApproval);
        if(oldProjectFinancingApproval != null){
            result.setStatus(301);
            result.setMessage("该项目已投递过了");
            return result;
        }

        ProjectFinancingApproval projectFinancingApproval = new ProjectFinancingApproval();
        projectFinancingApproval.setProjectSendLogId(projectId);
        projectFinancingApproval.setUserId(userId);
        if(params.getTuisongxiangmubiao7currentdem() != null && !"".equals(params.getTuisongxiangmubiao7currentdem())){
            projectFinancingApproval.setCurrentDemand(params.getTuisongxiangmubiao7currentdem());
        }
        projectFinancingApproval.setFinancingRounds(params.getTuisongxiangmubiao7roundoffin());
        projectFinancingApproval.setFinancingAmount(new BigDecimal(params.getTuisongxiangmubiao7financinga()));
        projectFinancingApproval.setFinancingCurrency(Integer.parseInt(params.getTuisongxiangmubiao7financingu()));
        projectFinancingApproval.setTransferShares(new BigDecimal(params.getTuisongxiangmubiao7sellingsha()));
        projectFinancingApprovalMapper.insert(projectFinancingApproval);

        //更新创始人记录
        int foundersId = 0;
        Founders founders = new Founders();
        founders.setUserId(userId);
        founders = foundersMapper.selectOne(founders);
        if(founders != null){
            foundersId = founders.getId();
        }else{
            //插入
            Founders newFounders = new Founders();
            newFounders.setUserId(userId);
            foundersMapper.insert(newFounders);
            foundersId = newFounders.getId();
        }

        //更新教育经历
        if(params.getTuisongxiangmubiao7educationa() != null && !"".equals(params.getTuisongxiangmubiao7educationa())){
            String[] educations = params.getTuisongxiangmubiao7educationa().split(",");
            //删除之前的记录
            FoundersEducation old = new FoundersEducation();
            old.setFounderId(foundersId);
            foundersEducationMapper.delete(old);
            for(String education : educations){
                FoundersEducation foundersEducation = new FoundersEducation();
                foundersEducation.setFounderId(foundersId);
                foundersEducation.setEducationExperience(education);
                foundersEducationMapper.insert(foundersEducation);
            }
        }

        //更新工作经历
        if(params.getTuisongxiangmubiao7workexperi() != null && !"".equals(params.getTuisongxiangmubiao7workexperi())){
            String[] works = params.getTuisongxiangmubiao7workexperi().split(",");
            //删除之前的记录
            FoundersWork old = new FoundersWork();
            old.setFounderId(foundersId);
            foundersWorkMapper.delete(old);
            for(String work : works){
                FoundersWork foundersWork = new FoundersWork();
                foundersWork.setFounderId(foundersId);
                foundersWork.setWorkExperience(work);
                foundersWorkMapper.insert(foundersWork);
            }
        }

        result.setStatus(200);
        result.setMessage("项目投递数据更新成功");
        return result;
    }

    /**
     * 项目投递回显
     * @param userId 用户ID
     * @param tsid 投递项目ID
     * @return
     */
    @Override
    public CommonDto<Map<String, Object>> rtuisonghuixian(int userId, String tsid) {
        CommonDto<Map<String, Object>> result = new CommonDto<>();
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, Object> datas = new HashMap<String, Object>();

        CommonDto<Map<String, List<LabelList>>> hotsdatas = evaluateService.queryHotData();

        Example example = new Example(ProjectSendLogs.class);
        example.and().andEqualTo("userid", userId);
        example.setOrderByClause("creat_time desc");
        List<ProjectSendLogs> sendLogsList = projectSendLogsMapper.selectByExample(example);
        ProjectSendLogs sendLogs = new ProjectSendLogs();
        int projectId = 0;
        if(sendLogsList.size() > 0){
            sendLogs = sendLogsList.get(0);
            projectId = sendLogs.getId();
        }

        Users users = new Users();
        users.setId(userId);
        users = usersMapper.selectByPrimaryKey(users);

        ProjectFinancingApproval approval = new ProjectFinancingApproval();
        approval.setUserId(userId);
        approval.setProjectSendLogId(projectId);
        approval = projectFinancingApprovalMapper.selectOne(approval);

        Founders founders = new Founders();
        founders.setUserId(userId);
        founders = foundersMapper.selectOne(founders);
        List<FoundersEducation> educations = new ArrayList<>();
        List<FoundersWork> works = new ArrayList<>();
        if(founders != null){
            int foundersId = founders.getId();
            FoundersEducation foundersEducation = new FoundersEducation();
            foundersEducation.setFounderId(foundersId);
            educations = foundersEducationMapper.select(foundersEducation);

            FoundersWork foundersWork = new FoundersWork();
            foundersWork.setFounderId(foundersId);
            works = foundersWorkMapper.select(foundersWork);
        }

        //所在城市(users)
        List<LabelList> cities = hotsdatas.getData().get("cityKey");
        if(users != null && users.getCity() != null && !"".equals(users.getCity())){
            String cityStr = users.getCity();
            for(LabelList labellist : cities){
                if(cityStr.equals(labellist.getName())){
                    labellist.setChecked(true);
                }
            }
            datas.put("tuisongxiangmubiao7city", cityStr);
        }else{
            datas.put("tuisongxiangmubiao7city", "");
        }
        datas.put("chengshiming", cities);

        //行业领域(send_logs)
        List<LabelList> industrys = hotsdatas.getData().get("industryKey");
        String industryStr = sendLogs.getField();
        if(industryStr != null && !"".equals(industryStr)){
            String[] industryArray = industryStr.split(",");
            for(LabelList labelList : industrys){
                for(String string : industryArray){
                    if(string.equals(labelList.getName())){
                        labelList.setChecked(true);
                    }
                }
            }
        }
        datas.put("tuisongxiangmubiao7industryfi", industrys);

        //融资轮次(approve)
        String roundNameStr = "";
        if(approval != null && !"".equals(approval.getFinancingRounds()) && approval.getFinancingRounds() != null){
            roundNameStr = approval.getFinancingRounds();
        }
        datas.put("tuisongxiangmubiao7roundoffin", roundNameStr);

        //身份类型(users)
        List<LabelList> identities = new ArrayList<>();
        LabelList identify1 = new LabelList();
        identify1.setName("投资人");
        identify1.setValue("投资人");
        identify1.setChecked(false);
        LabelList identify2 = new LabelList();
        identify2.setName("创业者");
        identify2.setValue("创业者");
        identify2.setChecked(false);
        LabelList identify3 = new LabelList();
        identify3.setName("产业公司");
        identify3.setValue("产业公司");
        identify3.setChecked(false);
        LabelList identify4 = new LabelList();
        identify4.setName("媒体");
        identify4.setValue("媒体");
        identify4.setChecked(false);
        LabelList identify5 = new LabelList();
        identify5.setName("研究机构");
        identify5.setValue("研究机构");
        identify5.setChecked(false);
        identities.add(identify1);
        identities.add(identify1);
        identities.add(identify1);
        identities.add(identify1);
        identities.add(identify1);

        if(users != null && users.getIdentityType() != null){
            int identity = users.getIdentityType();
            String identityStr = "";
            switch(identity){
                case 0:
                    identityStr = "投资人";
                    break;
                case 1:
                    identityStr = "创业者";
                    break;
                case 2:
                    identityStr = "产业公司";
                    break;
                case 3:
                    identityStr = "媒体";
                    break;
                case 4:
                    identityStr = "研究机构";
                    break;
            }
            for(LabelList list : identities){
                if(identityStr.equals(list.getName())){
                    list.setChecked(true);
                }
            }
            datas.put("tuisongxiangmubiao7identityty", identityStr);
        }else{
            datas.put("tuisongxiangmubiao7identityty", "");
        }
        datas.put("bigBoss", identities);

        if(users != null){
            //公司名称
            datas.put("tuisongxiangmubiao7companywhe", users.getCompanyName());
            //公司简介
            datas.put("tuisongxiangmubiao7companypro", users.getCompanyDesc());
            //真是姓名
            datas.put("tuisongxiangmubiao7realname", users.getActualName());
            //担任职务
            datas.put("tuisongxiangmubiao7assumeoffi", users.getCompanyDuties());
            //个人简介
            datas.put("tuisongxiangmubiao7profile", users.getDesc());
            //邮箱
            datas.put("tuisongxiangmubiao7mailbox", users.getEmail());
            //微信号
            datas.put("tuisongxiangmubiao7wechatnumb", users.getWechat());
        }else{
            //公司名称
            datas.put("tuisongxiangmubiao7companywhe", "");
            //公司简介
            datas.put("tuisongxiangmubiao7companypro", "");
            //真是姓名
            datas.put("tuisongxiangmubiao7realname", "");
            //担任职务
            datas.put("tuisongxiangmubiao7assumeoffi", "");
            //个人简介
            datas.put("tuisongxiangmubiao7profile", "");
            //邮箱
            datas.put("tuisongxiangmubiao7mailbox", "");
            //微信号
            datas.put("tuisongxiangmubiao7wechatnumb", "");
        }

        if(approval != null){
            //融资金额
            datas.put("tuisongxiangmubiao7financinga", approval.getFinancingAmount());
            //融资金额单位
            datas.put("tuisongxiangmubiao7financingu", approval.getFinancingCurrency());
            //出让股份
            datas.put("tuisongxiangmubiao7sellingsha", approval.getTransferShares());
            //当前需求
            datas.put("tuisongxiangmubiao7currentdem", approval.getCurrentDemand());
        }else{
            //融资金额
            datas.put("tuisongxiangmubiao7financinga", "");
            //融资金额单位
            datas.put("tuisongxiangmubiao7financingu", "");
            //出让股份
            datas.put("tuisongxiangmubiao7sellingsha", "");
            //当前需求
            datas.put("tuisongxiangmubiao7currentdem", "");
        }

        //项目标签
        datas.put("tuisongxiangmubiao7projecttag", sendLogs.getProjectTags());

        //教育经历
        List<String> educationStr = new ArrayList<>();
        if(educations.size() > 0){
            for(FoundersEducation education : educations){
                educationStr.add(education.getEducationExperience());
            }
        }
        datas.put("tuisongxiangmubiao7educationa", educationStr);

        //工作经历
        List<String> workStr = new ArrayList<>();
        if(works.size() > 0){
            for(FoundersWork work : works){
                workStr.add(work.getWorkExperience());
            }
        }
        datas.put("tuisongxiangmubiao7workexperi", workStr);

        //获取最新项目ID
        ProjectSendLogs projectSendLogs = new ProjectSendLogs();
        projectSendLogs.setUserid(userId);
        projectSendLogsMapper.insert(projectSendLogs);
        int newId = projectSendLogs.getId();

        data.put("jieguo", datas);
        data.put("success", true);
        data.put("tsid", newId);

        result.setStatus(200);
        result.setMessage("回显数据获取成功");
        result.setData(data);
        return result;
    }

    /**
     * 融资历史记录
     * @param tsid 项目ID
     * @param rongzilishi 融资历史信息
     * @return
     */
    @Override
    public CommonDto<String> ctuisongthird(String tsid, String rongzilishi) {
        CommonDto<String> result = new CommonDto<>();

        //保存融资历史记录
        ProjectFinancingHistory projectFinancingHistory = new ProjectFinancingHistory();
        projectFinancingHistory.setProjectSendLogId(tsid);
        projectFinancingHistory = projectFinancingHistoryMapper.selectOne(projectFinancingHistory);
        if(projectFinancingHistory != null){
            projectFinancingHistory.setHistory(rongzilishi);
            projectFinancingHistoryMapper.updateByPrimaryKey(projectFinancingHistory);
        }else{
            ProjectFinancingHistory newProjectFinancingHistory = new ProjectFinancingHistory();
            newProjectFinancingHistory.setProjectSendLogId(tsid);
            newProjectFinancingHistory.setHistory(rongzilishi);
            projectFinancingHistoryMapper.insert(newProjectFinancingHistory);
        }

        result.setStatus(200);
        result.setMessage("融资历史保存成功");
        return result;
    }

    /**
     * 融资历史回显
     * @param tsid 投递项目ID
     * @return
     */
    @Override
    public CommonDto<Map<String, Object>> rtuisongthird(String tsid, int userId) {
        CommonDto<Map<String, Object>> result = new CommonDto<>();
        Map<String, Object> data = new HashMap<>();
        String history = "";
        //查出当前用户投递信息
        Example example = new Example(ProjectSendLogs.class);
        example.and().andEqualTo("userid", userId);
        example.setOrderByClause("creat_time desc");
        List<ProjectSendLogs> sendLogsList = projectSendLogsMapper.selectByExample(example);
        if(sendLogsList.size() > 0){
            for(ProjectSendLogs sendLogs : sendLogsList){
                ProjectFinancingHistory projectFinancingHistory = new ProjectFinancingHistory();
                projectFinancingHistory.setProjectSendLogId(sendLogs.getId() + "");
                projectFinancingHistory = projectFinancingHistoryMapper.selectOne(projectFinancingHistory);
                if(projectFinancingHistory != null){
                    history = projectFinancingHistory.getHistory();
                    break;
                }
            }
        }
        data.put("historyList", history);
        result.setStatus(200);
        result.setMessage("融资历史回显数据获取成功");
        result.setData(data);
        return result;
    }
}
