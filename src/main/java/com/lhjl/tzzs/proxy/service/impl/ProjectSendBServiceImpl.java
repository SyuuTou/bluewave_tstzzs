package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.*;
import com.lhjl.tzzs.proxy.service.bluewave.UserLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Resource
    private ProjectSendTeamBService projectSendTeamBService;

    @Resource
    private ProjectSendFinancingHistoryBService projectSendFinancingHistoryBService;

    @Autowired
    private FoundersEducationMapper foundersEducationMapper;

    @Autowired
    private FoundersWorkMapper foundersWorkMapper;

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
     * 读项目信息接口
     * @param token
     * @param appid
     * @return
     */
    @Override
    public CommonDto<ProjectSendBOutDto> readProjectInfomation(String token, Integer appid) {

        CommonDto<ProjectSendBOutDto> result  = new CommonDto<>();

        if (StringUtils.isAnyBlank(token)){
            result.setStatus(502);
            result.setMessage("用户token不能为空");
            result.setData(null);

            return result;
        }

        Integer userId = userLoginService.getUserIdByToken(token,appid);
        if (userId == -1){
            result.setMessage("用户token非法");
            result.setData(null);
            result.setStatus(502);
            return result;
        }

        Integer prepareId = getPrepareId(token,appid);
        if (prepareId == null){
            result.setStatus(502);
            result.setData(null);
            result.setMessage("生产prepareId出错");

            return result;
        }

        List<ProjectSendB> projectSendBList = projectSendBMapper.getLastCreateProject(userId,appid,prepareId);
        if (projectSendBList.size() > 0){
            //原prepareid
            Integer prepareid = projectSendBList.get(0).getPrepareId();
            //新prepareid
            Integer newprepareid = getPrepareId(token,appid);
            //老提交项目id
            Integer projectSendBId = projectSendBList.get(0).getId();
            //复制项目信息获取新项目id
            CommonDto<Integer> newProjectSendBC = copyProject(prepareid,newprepareid,appid,userId,projectSendBId);
            Integer newProjectSendBId = newProjectSendBC.getData();

            result =readProjectSendById(newProjectSendBId,appid,userId,newprepareid);

        }else {
            result = readProjectFirstTime(token,appid,userId);
        }

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

    /**
     * 复制项目信息的方法
     * @param prepareid 预生成id
     * @param newprepareid 新预生成id
     * @param appid 应用id
     * @param userId 用户id
     * @param projectSendBId 原提交项目id
     * @return
     */
    @Transactional
    @Override
    public CommonDto<Integer> copyProject(Integer prepareid,Integer newprepareid,Integer appid,Integer userId,Integer projectSendBId){
        CommonDto<Integer> result  = new CommonDto<>();
        if (prepareid ==null || appid == null || userId == null || projectSendBId == null){
            result.setMessage("缺少参数无法完成复制");
            result.setData(null);
            result.setStatus(502);

            return result;
        }
        Integer id =0;
        //复制项目主体信息
        ProjectSendSearchDto projectSendSearchDto = new ProjectSendSearchDto();
        projectSendSearchDto.setNewprepareid(newprepareid);
        projectSendSearchDto.setPrepareid(prepareid);

        projectSendBMapper.copyProjectSendB(projectSendSearchDto);
        Integer newprojectSendId = projectSendSearchDto.getId();

        ProjectSendSearchCommenDto projectSendSearchCommenDto = new ProjectSendSearchCommenDto();
        projectSendSearchCommenDto.setNewid(newprojectSendId);
        projectSendSearchCommenDto.setOldid(projectSendBId);

        //复制项目相关信息
        projectSendCompetingBMapper.copyProjectSendCompetingB(projectSendSearchCommenDto);
        projectSendSegmentationBMapper.copyProjectSendSegmentationB(projectSendSearchCommenDto);
        projectSendTagsBMapper.copyProjectSendTagsB(projectSendSearchCommenDto);
        projectSendFinancingApprovalBMapper.copyProjectSendFinancingApprovalB(projectSendSearchCommenDto);

        //复制团队成员
        projectSendTeamBService.copyProjectSendBTeam(appid, projectSendBId);

        //复制融资历史
        projectSendFinancingHistoryBService.copyProjectSendFinancingHistoryB(newprojectSendId,projectSendBId,appid);

        result.setData(newprojectSendId);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    /**
     * 提交项目信息回显
     * @param projectSendBId
     * @param appid
     * @param userid
     * @param newprepareid
     * @return
     */
    @Transactional(readOnly = true)
    public CommonDto<ProjectSendBOutDto> readProjectSendById(Integer projectSendBId,Integer appid,Integer userid,Integer newprepareid){
        CommonDto<ProjectSendBOutDto> result = new CommonDto<>();
        ProjectSendBOutDto projectSendBOutDto = new ProjectSendBOutDto();

        ProjectSendB projectSendB =  projectSendBMapper.selectByPrimaryKey(projectSendBId);
        if (projectSendB == null){
            result.setMessage("服务器端发生逻辑错误，请联系后端");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        String projectLogo = "";
        if (projectSendB.getProjectLogo() != null){
            projectLogo = projectSendB.getProjectLogo();
        }
        projectSendBOutDto.setProjectLogo(projectLogo);
        projectSendBOutDto.setShortName(projectSendB.getShortName());
        projectSendBOutDto.setKernelDesc(projectSendB.getKernelDesc());
        projectSendBOutDto.setProjectInvestmentHighlights(projectSendB.getProjectInvestmentHighlights());
        projectSendBOutDto.setCommet(projectSendB.getCommet());
        String url = "";
        if (projectSendB.getUrl() != ""){
            url = projectSendB.getUrl();
        }
        projectSendBOutDto.setUrl(url);
        projectSendBOutDto.setCity(projectSendB.getCity());

        //项目领域
        List<String> segmentation = new ArrayList<>();
        ProjectSendSegmentationB projectSendSegmentationB = new ProjectSendSegmentationB();
        projectSendSegmentationB.setProjectSendBId(projectSendBId);
        projectSendSegmentationB.setAppid(appid);

        List<ProjectSendSegmentationB> projectSendSegmentationBList = projectSendSegmentationBMapper.select(projectSendSegmentationB);
        if (projectSendSegmentationBList.size() > 0){
            for (ProjectSendSegmentationB pssb:projectSendSegmentationBList){
                segmentation.add(pssb.getSegmentationName());
            }
        }
        projectSendBOutDto.setProjectSegmentation(segmentation);

        //项目标签
        List<String> projectTags = new ArrayList<>();
        ProjectSendTagsB projectSendTagsB = new ProjectSendTagsB();
        projectSendTagsB.setProjectSendBId(projectSendBId);
        projectSendTagsB.setAppid(appid);

        List<ProjectSendTagsB> projectSendTagsBList = projectSendTagsBMapper.select(projectSendTagsB);
        if (projectSendTagsBList.size() > 0){
            for (ProjectSendTagsB pstb:projectSendTagsBList){
                projectTags.add(pstb.getTagName());
            }
        }
        projectSendBOutDto.setProjectTags(projectTags);

        //当前融资信息
        ProjectSendFinancingApprovalB projectSendFinancingApprovalB = new ProjectSendFinancingApprovalB();
        projectSendFinancingApprovalB.setProjectSendBId(projectSendBId);
        projectSendFinancingApprovalB.setAppid(appid);

        List<ProjectSendFinancingApprovalB> projectSendFinancingApprovalBList = projectSendFinancingApprovalBMapper.select(projectSendFinancingApprovalB);
        if (projectSendFinancingApprovalBList.size() > 0){
            projectSendBOutDto.setStage(projectSendFinancingApprovalBList.get(0).getStage());
            projectSendBOutDto.setAmount(projectSendFinancingApprovalBList.get(0).getAmount());
            projectSendBOutDto.setCurrency(projectSendFinancingApprovalBList.get(0).getCurrency());
            projectSendBOutDto.setShareDivest(projectSendFinancingApprovalBList.get(0).getShareDivest());
            projectSendBOutDto.setProjectFinancingUseful(projectSendFinancingApprovalBList.get(0).getProjectFinancingUseful());

        }else {
            result.setMessage("没找到当前融资信息");
            result.setStatus(502);
            return result;
        }

        //项目竞品
        List<String> projectCompetion= new ArrayList<>();
        ProjectSendCompetingB projectSendCompetingB = new ProjectSendCompetingB();
        projectSendCompetingB.setProjectSendBId(projectSendBId);
        projectSendCompetingB.setAppid(appid);

        List<ProjectSendCompetingB> projectSendCompetingBList = projectSendCompetingBMapper.select(projectSendCompetingB);
        if (projectSendCompetingBList.size() > 0){
            for (ProjectSendCompetingB pscb:projectSendCompetingBList){
                projectCompetion.add(pscb.getCompetingName());
            }
        }
        projectSendBOutDto.setProjectCompeting(projectCompetion);


        //获取创始人信息
        Users users = usersMapper.selectByPrimaryKey(userid);

        projectSendBOutDto.setActualName(users.getActualName());
        projectSendBOutDto.setCompanyDuties(users.getCompanyDuties());
        projectSendBOutDto.setDesc(users.getDesc());
        String email = "";
        if (users.getEmail() != null){
            email = users.getEmail();
        }
        projectSendBOutDto.setEmail(email);
        String wechat = "";
        if (users.getWechat() != null){
            wechat = users.getWechat();
        }
        projectSendBOutDto.setWechat(wechat);

        List<String> founderEducation = new ArrayList<>();
        List<String> founderWork = new ArrayList<>();
        Founders founders = new Founders();
        founders.setUserId(userid);

        List<Founders> foundersList = foundersMapper.select(founders);
        if (foundersList.size() > 0){
            Integer founderId = foundersList.get(0).getId();
            FoundersEducation foundersEducation = new FoundersEducation();
            foundersEducation.setFounderId(founderId);

            List<FoundersEducation> foundersEducationList = foundersEducationMapper.select(foundersEducation);
            if (foundersEducationList.size() > 0){
                for (FoundersEducation fe:foundersEducationList){
                    founderEducation.add(fe.getEducationExperience());
                }
            }

            FoundersWork foundersWork = new FoundersWork();
            foundersWork.setFounderId(founderId);

            List<FoundersWork> foundersWorkList = foundersWorkMapper.select(foundersWork);
            if (foundersWorkList.size() > 0){
                for (FoundersWork fw:foundersWorkList){
                    founderWork.add(fw.getWorkExperience());
                }
            }

        }

        projectSendBOutDto.setEducationExperience(founderEducation);
        projectSendBOutDto.setWorkExperience(founderWork);
        projectSendBOutDto.setProjectSendId(projectSendBId);
        projectSendBOutDto.setPrepareId(newprepareid);

        result.setStatus(200);
        result.setMessage("success");
        result.setData(projectSendBOutDto);

        return result;
    }

    /**
     * 用户第一次访问项目读的接口
     * @param token
     * @param appid
     * @param userId
     * @return
     */
    @Transactional(readOnly = true)
    public CommonDto<ProjectSendBOutDto> readProjectFirstTime(String token ,Integer appid,Integer userId){
        CommonDto<ProjectSendBOutDto> result  = new CommonDto<>();
        ProjectSendBOutDto projectSendBOutDto = new ProjectSendBOutDto();

        Integer prepareId = getPrepareId(token, appid);
        Integer projectSendBId = creatProject(prepareId,appid,userId);

        Users users = usersMapper.selectByPrimaryKey(userId);
        String shortName = "";
        if (users.getCompanyName() != null){
            shortName =users.getCompanyName();
        }
        projectSendBOutDto.setShortName(shortName);
        String city = "";
        if (users.getCity() != null){
            city = users.getCity();
        }
        projectSendBOutDto.setCity(city);
        projectSendBOutDto.setActualName(users.getActualName());
        projectSendBOutDto.setCompanyDuties(users.getCompanyDuties());
        String desc = "";
        if (users.getDesc() != null){
            desc = users.getDesc();
        }
        projectSendBOutDto.setDesc(desc);
        String email = "";
        if (users.getEmail() != null){
            email = users.getEmail();
        }
        projectSendBOutDto.setEmail(email);
        String wechat = "";
        if (users.getWechat() != null){
            wechat = users.getWechat();
        }
        projectSendBOutDto.setWechat(wechat);

        String companyDesc = "";
        if (users.getCompanyDesc() != null){
            companyDesc = users.getCompanyDesc();
        }
        projectSendBOutDto.setCommet(companyDesc);

        List<String> founderEducation = new ArrayList<>();
        List<String> founderWork = new ArrayList<>();
        Founders founders = new Founders();
        founders.setUserId(userId);

        List<Founders> foundersList = foundersMapper.select(founders);
        if (foundersList.size() > 0){
            Integer founderId = foundersList.get(0).getId();
            FoundersEducation foundersEducation = new FoundersEducation();
            foundersEducation.setFounderId(founderId);

            List<FoundersEducation> foundersEducationList = foundersEducationMapper.select(foundersEducation);
            if (foundersEducationList.size() > 0){
                for (FoundersEducation fe:foundersEducationList){
                    founderEducation.add(fe.getEducationExperience());
                }
            }

            FoundersWork foundersWork = new FoundersWork();
            foundersWork.setFounderId(founderId);

            List<FoundersWork> foundersWorkList = foundersWorkMapper.select(foundersWork);
            if (foundersWorkList.size() > 0){
                for (FoundersWork fw:foundersWorkList){
                    founderWork.add(fw.getWorkExperience());
                }
            }

        }

        projectSendBOutDto.setEducationExperience(founderEducation);
        projectSendBOutDto.setWorkExperience(founderWork);
        projectSendBOutDto.setProjectSendId(projectSendBId);
        projectSendBOutDto.setPrepareId(prepareId);

        //设置返回空值
        projectSendBOutDto.setProjectLogo("");
        projectSendBOutDto.setFullName("");
        projectSendBOutDto.setKernelDesc("");
        projectSendBOutDto.setProjectInvestmentHighlights("");
        projectSendBOutDto.setUrl("");
        List<String> segmentation = new ArrayList<>();
        projectSendBOutDto.setProjectSegmentation(segmentation);
        List<String> projectTags = new ArrayList<>();
        projectSendBOutDto.setProjectTags(projectTags);
        projectSendBOutDto.setStage("");
        BigDecimal a = BigDecimal.ZERO;
        projectSendBOutDto.setAmount(a);
        projectSendBOutDto.setCurrency(0);
        projectSendBOutDto.setShareDivest("");
        projectSendBOutDto.setProjectFinancingUseful("");
        List<String> projectCompetation = new ArrayList<>();
        projectSendBOutDto.setProjectCompeting(projectCompetation);


        result.setData(projectSendBOutDto);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }
}
