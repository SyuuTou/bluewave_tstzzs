package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.FounderEducationService;
import com.lhjl.tzzs.proxy.service.FounderWorkService;
import com.lhjl.tzzs.proxy.service.ProjectSendBService;
import com.lhjl.tzzs.proxy.service.bluewave.UserLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProjectSendBServiceImpl implements ProjectSendBService{

    @Resource
    private UserLoginService userLoginService;

    @Autowired
    private ProjectSendPrepareidBMapper projectSendPrepareidBMapper;

    @Autowired
    private ProjectSendBMapper projectSendBMapper;

    @Autowired
    private ProjectSendSegmentationBMapper projectSendSegmentationBMapper;

    @Autowired
    private ProjectSendTagsBMapper projectSendTagsBMapper;

    @Autowired
    private ProjectSendCompetingBMapper projectSendCompetingBMapper;

    @Autowired
    private ProjectSendFinancingApprovalBMapper projectSendFinancingApprovalBMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Resource
    private FounderWorkService founderWorkService;

    @Resource
    private FounderEducationService founderEducationService;

    @Autowired
    private FoundersMapper foundersMapper;

    /**
     * 获取prepareid的方法
     * @param token 用户token
     * @param appid 应用id
     * @return
     */
    @Override
    public Integer getPrepareId(String token, Integer appid) {
        Integer prepareId = null;

        if (token == null || "".equals(token) || "undefined".equals(token)){
            return prepareId;
        }

        Integer userId = userLoginService.getUserIdByToken(token,appid);
        if (userId == -1){
            return prepareId;
        }

        Example prepareExample = new Example(ProjectSendPrepareidB.class);
        prepareExample.and().andEqualTo("userId",userId).andEqualTo("auditStatus",0).andEqualTo("appid",appid);

        List<ProjectSendPrepareidB> projectSendPrepareidBList = projectSendPrepareidBMapper.selectByExample(prepareExample);
        if (projectSendPrepareidBList.size() > 0){
            prepareId = projectSendPrepareidBList.get(0).getId();
        }else {
            //创建prepareId
            prepareId = creatPrepareId(userId,appid);
        }
        return prepareId;
    }

    /**
     * 更新提交项目信息方法
     * @param body
     * @param appid
     * @return
     */
    @Transactional
    @Override
    public CommonDto<String> updateProject(ProjectSendBDto body, Integer appid) {
        CommonDto<String> result  = new CommonDto<>();
        if (body.getToken() == null || "".equals(body.getToken()) || "undefined".equals(body.getToken())){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("用户token不能为空");

            return result;
        }

        if (body.getProjectSendId() == null){
            result.setMessage("提交项目id不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }
        if (StringUtils.isAnyBlank(body.getShortName(),body.getKernelDesc(),body.getProjectInvestmentHighlights(),body.getCommet(),body.getCity())){
            result.setMessage("必填项有为空的情况，请检查");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        ProjectSendB projectSendB = new ProjectSendB();
        projectSendB.setId(body.getProjectSendId());
        if (body.getProjectLogo() != null){
            projectSendB.setProjectLogo(body.getProjectLogo());
        }
        projectSendB.setShortName(body.getShortName());
        if (body.getFullName() != null){
            projectSendB.setFullName(body.getFullName());
        }
        projectSendB.setKernelDesc(body.getKernelDesc());
        projectSendB.setProjectInvestmentHighlights(body.getProjectInvestmentHighlights());
        projectSendB.setCommet(body.getCommet());
        if (body.getUrl() != null){
            projectSendB.setUrl(body.getUrl());
        }
        projectSendB.setCity(body.getCity());

        projectSendBMapper.updateByPrimaryKeySelective(projectSendB);

        result.setData(null);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }



    /**
     * 创建prepareId方法
     * @param userId
     * @param appid
     * @return
     */
    @Transactional
    public Integer creatPrepareId(Integer userId,Integer appid){
        Integer result  = null;

        ProjectSendPrepareidB projectSendPrepareidB = new ProjectSendPrepareidB();
        projectSendPrepareidB.setAppid(appid);
        projectSendPrepareidB.setUserId(userId);
        projectSendPrepareidB.setAuditStatus(0);

        projectSendPrepareidBMapper.insert(projectSendPrepareidB);

        result = projectSendPrepareidB.getId();

        return result;
    }

    /**
     * 创建一个/查找一个待编辑的提交项目id
     * @param prepareid
     * @param appid
     * @param userId
     * @return
     */
    @Transactional
    public Integer creatProject(Integer prepareid,Integer appid,Integer userId){
        Integer result  = null;
        Date now = new Date();

        ProjectSendB projectSendB = new ProjectSendB();
        projectSendB.setAppid(appid);
        projectSendB.setPrepareId(prepareid);
        projectSendB.setUserId(userId);
        projectSendB.setEditStatus(0);

       ProjectSendB projectSendBResult =  projectSendBMapper.selectOne(projectSendB);
       if (projectSendBResult != null){
           result = projectSendBResult.getId();
       }else {
           projectSendB.setCreateTime(now);

           projectSendBMapper.insert(projectSendB);
           result = projectSendB.getId();
       }

        return result;
    }

    /**
     * 创建提交项目领域信息
     * @param body
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> createProjectSegmentation(ProjectSendBDto body,Integer appid){
        CommonDto<String> result = new CommonDto<>();

        if (body.getProjectSendId() == null){
            result.setStatus(502);
            result.setMessage("提交项目id不能为空");
            result.setData(null);

            return result;
        }

        if (body.getProjectSegmentation().size() < 1){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("请选择项目领域");

            return result;
        }

        if (body.getProjectSegmentation().size() > 0){
            for (String s:body.getProjectSegmentation()){
                ProjectSendSegmentationB projectSendSegmentationB = new ProjectSendSegmentationB();
                projectSendSegmentationB.setAppid(appid);
                projectSendSegmentationB.setProjectSendBId(body.getProjectSendId());
                projectSendSegmentationB.setSegmentationName(s);

                projectSendSegmentationBMapper.insertSelective(projectSendSegmentationB);
            }
        }

        result.setMessage("success");
        result.setData(null);
        result.setStatus(200);

        return result;
    }

    /**
     * 创建提交项目标签
     * @param body
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> createProjectTags(ProjectSendBDto body,Integer appid){
        CommonDto<String> result = new CommonDto<>();

        if (body.getProjectSendId() == null){
            result.setStatus(502);
            result.setMessage("提交项目id不能为空");
            result.setData(null);

            return result;
        }

        if (body.getProjectTags().size() < 1){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("请选择项目领域");

            return result;
        }

        if (body.getProjectTags().size() > 0){
            for (String s:body.getProjectTags()){
                ProjectSendTagsB projectSendTagsB = new ProjectSendTagsB();
                projectSendTagsB.setAppid(appid);
                projectSendTagsB.setProjectSendBId(body.getProjectSendId());
                projectSendTagsB.setTagName(s);

                projectSendTagsBMapper.insertSelective(projectSendTagsB);
            }
        }

        result.setStatus(200);
        result.setData(null);
        result.setMessage("success");

        return result;
    }

    /**
     * 创建提交项目竞品
     * @param body
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> createProjectCompeting(ProjectSendBDto body,Integer appid){
        CommonDto<String> result  = new CommonDto<>();

        if (body.getProjectSendId() == null){
            result.setStatus(502);
            result.setMessage("提交项目id不能为空");
            result.setData(null);

            return result;
        }

        if (body.getProjectCompeting().size() < 1){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("请选择项目领域");

            return result;
        }

        if (body.getProjectCompeting().size() > 0){
            for (String s:body.getProjectCompeting()){
                ProjectSendCompetingB projectSendCompetingB = new ProjectSendCompetingB();
                projectSendCompetingB.setAppid(appid);
                projectSendCompetingB.setCompetingName(s);
                projectSendCompetingB.setProjectSendBId(body.getProjectSendId());

                projectSendCompetingBMapper.insertSelective(projectSendCompetingB);
            }
        }

        result.setMessage("success");
        result.setData(null);
        result.setStatus(200);

        return result;
    }

    /**
     * 创建提交项目申请信息
     * @param body
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> createProjectFinancingApproval(ProjectSendBDto body,Integer appid){
        CommonDto<String> result  = new CommonDto<>();

        if (body.getStage() == null || "".equals(body.getStage()) || "undefined".equals(body.getStage())){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("请输入融资阶段");

            return result;
        }

        if (body.getAmount() == null){
            result.setMessage("请输入融资金额");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (body.getShareDivest() == null ){
            result.setMessage("请输入出让股份");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        ProjectSendFinancingApprovalB projectSendFinancingApprovalB = new ProjectSendFinancingApprovalB();
        projectSendFinancingApprovalB.setStage(body.getStage());
        projectSendFinancingApprovalB.setAmount(body.getAmount());
        if (body.getCurrency() == null){
            body.setCurrency(0);
        }
        projectSendFinancingApprovalB.setCurrency(body.getCurrency());
        projectSendFinancingApprovalB.setShareDivest(body.getShareDivest());
        if (body.getProjectFinancingUseful() != null){
            projectSendFinancingApprovalB.setProjectFinancingUseful(body.getProjectFinancingUseful());
        }

        projectSendFinancingApprovalBMapper.insertSelective(projectSendFinancingApprovalB);

        result.setStatus(200);
        result.setMessage("success");
        result.setData(null);

        return result;
    }

    /**
     * 更新用户信息
     * @param body
     * @param appid
     * @return
     */
    @Transactional
    public CommonDto<String> updateUserInfo(ProjectSendBDto body,Integer appid){
        CommonDto<String> result  = new CommonDto<>();
        Date now = new Date();

        if (body.getToken() == null){
            result.setStatus(502);
            result.setMessage("用户token不能为空");
            result.setData(null);

            return result;
        }

        if (StringUtils.isAnyBlank(body.getActualName(),body.getCompanyDuties(),body.getDesc(),body.getEmail(),body.getWechat())){
            result.setData(null);
            result.setMessage("用户信息必填项中，包含未填写项，请检查");
            result.setStatus(520);

            return result;
        }

        Integer userId = userLoginService.getUserIdByToken(body.getToken(),appid);
        if (userId == -1){
            result.setData(null);
            result.setMessage("用户token无效");
            result.setStatus(502);

            return result;
        }

        Users users = new Users();
        users.setId(userId);
        users.setActualName(body.getActualName());
        users.setCompanyDuties(body.getCompanyDuties());
        users.setDesc(body.getDesc());
        users.setEmail(body.getEmail());
        users.setWechat(body.getWechat());

        usersMapper.updateByPrimaryKeySelective(users);

        //更新创始人信息
        Example founderExample = new Example(Founders.class);
        founderExample.and().andEqualTo("userId",userId);

        List<Founders> foundersList = foundersMapper.selectByExample(founderExample);

        Integer founderId = null;
        if (foundersList.size() > 0){
            founderId = foundersList.get(0).getId();
        }else {
            Founders founders = new Founders();
            founders.setName(body.getActualName());
            founders.setUserId(userId);
            founders.setYn(0);
            founders.setCreateTime(now);

            foundersMapper.insertSelective(founders);
            founderId = founders.getId();
        }

        founderWorkService.createOrUpdateFounderWork(founderId,body.getWorkExperience());
        founderEducationService.createOrUpdateFounderEducation(founderId,body.getEducationExperience());

        return result;
    }

}
