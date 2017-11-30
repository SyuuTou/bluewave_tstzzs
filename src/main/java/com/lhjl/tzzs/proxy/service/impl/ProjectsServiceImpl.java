package com.lhjl.tzzs.proxy.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.lhjl.tzzs.proxy.service.ProjectsService;
import tk.mybatis.mapper.entity.Example;

/**
 * 项目
 *
 * @author lmy
 */
@Service
public class ProjectsServiceImpl implements ProjectsService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ProjectsServiceImpl.class);

    @Value("${statistics.beginTime}")
    private String beginTime;

    @Value("${statistics.endTime}")
    private String endTime;

    @Resource
    private ProjectsMapper projectsMapper;
    @Resource
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;
    @Autowired
    private Environment environment;
    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private InterviewMapper interviewMapper;

    @Resource
    private ProjectsServiceImplUtil projectsServiceImplUtil;

    @Autowired
    private ProjectFinancingLogMapper projectFinancingLogMapper;

    @Autowired
    private ProjectFinancingHistoryInvestorsMapper projectFinancingHistoryInvestorsMapper;

    @Autowired
    private ProjectTeamMemberMapper projectTeamMemberMapper;

    @Autowired
    private ProjectTeamMemberEducationMapper projectTeamMemberEducationMapper;

    @Autowired
    private ProjectTeamMemberWorkMapper projectTeamMemberWorkMapper;

    @Autowired
    private ProjectSegmentationMapper projectSegmentationMapper;

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @Autowired
    private ProjectAdministratorMapper projectAdministratorMapper;

    @Resource
    private UsersMapper usersMapper;

    /**
     * 查询我关注的项目
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> findProjectByUserId(String userId, Integer pageNum, Integer pageSize) {
        CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();

        //计算查询起始记录
        Integer beginNum = (pageNum - 1) * pageSize;
        //最多返回100条记录
        if (beginNum > 100) {
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（100条）");
            return result;
        } else {
            pageSize = (100 - beginNum) >= pageSize ? pageSize : (100 - beginNum);
        }

        List<Map<String, Object>> list = projectsMapper.findProjectByUserId(userId, beginNum, pageSize);
        //List<Map<String, Object>> list =projectsServiceImplUtil.getSearchBaseMyProject(userId,beginNum,pageSize);
        Follow follow = new Follow();
        Interview interview = new Interview();
        for (Map<String, Object> obj : list) {
            follow.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            follow.setStatus(1);
            follow.setUserId(null);
            obj.put("num", followMapper.selectCount(follow));
            follow.setUserId(userId);
            if (followMapper.selectCount(follow) > 0) {
                obj.put("yn", 1);
            } else {
                obj.put("yn", 0);
            }

            interview.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            obj.put("inum", interviewMapper.selectCount(interview));
        }

        //判断是否还有查询结果
        if (list.size() <= 0) {
            result.setStatus(202);
            result.setMessage("无查询数据");
            return result;
        }
        result.setData(list);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    /**
     * 搜索结果页面值搜查出三条记录
     *
     * @param shortName
     * @param userId
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> findProjectByShortName(String shortName, String userId) {
        CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
        List<Map<String, Object>> list = projectsMapper.findProjectByShortName(shortName, userId);
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        Follow follow = new Follow();
        Interview interview = new Interview();
        for (Map<String, Object> obj : list) {
            follow.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            follow.setStatus(1);
            follow.setUserId(null);
            obj.put("num", followMapper.selectCount(follow));
            follow.setUserId(userId);
            if (followMapper.selectCount(follow) > 0) {
                obj.put("yn", 1);
            } else {
                obj.put("yn", 0);
            }

            interview.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            obj.put("inum", interviewMapper.selectCount(interview));
        }
        for (Map<String, Object> map : list) {
            if (map.get("yn") == null) {
                map.put("yn", 0);
            }
            list2.add(map);
        }
        result.setData(list2);
        result.setStatus(200);
        result.setMessage("ok");
        return result;
    }

    /**
     * 搜索更多结果
     *
     * @param shortName
     * @param userId
     * @param size
     * @param from
     * @return
     */
    @Override
    public CommonDto<Map<String, List<Map<String, Object>>>> findProjectByShortNameAll(String shortName, String userId, String size, String from) {
        CommonDto<Map<String, List<Map<String, Object>>>> result = new CommonDto<Map<String, List<Map<String, Object>>>>();
        int sizeb = Integer.parseInt(size);
        int froma = Integer.parseInt(from);
        int sizea = (sizeb - 1) * froma;
        if (sizea > 100) {
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（100条）");
            return result;
        }
        {
            froma = (100 - sizea) >= froma ? froma : (100 - sizea);
        }
        Map<String, List<Map<String, Object>>> dataMap = new HashMap<String, List<Map<String, Object>>>();
        //List<Map<String, Object>> list = projectsMapper.findProjectByShortNameAll(shortName,userId,sizea,froma);
        List<Map<String, Object>> list = projectsServiceImplUtil.getSearchBaseProjectAll(userId, shortName, sizea, froma);
        Follow follow = new Follow();
        Interview interview = new Interview();
        for (Map<String, Object> obj : list) {
            follow.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            follow.setStatus(1);
            follow.setUserId(null);
            obj.put("num", followMapper.selectCount(follow));
            follow.setUserId(userId);
            if (followMapper.selectCount(follow) > 0) {
                obj.put("yn", 1);
            } else {
                obj.put("yn", 0);
            }

            interview.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            obj.put("inum", interviewMapper.selectCount(interview));
        }
        if (list.size() <= 0) {
            result.setStatus(202);
            result.setMessage("无查询数据");
            return result;
        }
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();

        for (Map<String, Object> map : list) {
            if (map.get("yn") == null) {
                map.put("yn", 0);
            }

            list2.add(map);
        }
       /* List<Map<String, Object>> list3 = investmentInstitutionsMapper.findByInvestmentShortNameAll(shortName,userId);
        dataMap.put("projectskey", list2);
        dataMap.put("investmentkey", list3);*/
        dataMap.put("projectskey", list2);
        result.setData(dataMap);
        result.setStatus(200);
        result.setMessage("ok");
        return result;
    }


    /**
     * 项目筛选
     *
     * @param sereachDto 筛选条件
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> findProjectBySview(SereachDto sereachDto) {
        CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();

        String userId = sereachDto.getUserId();
        String type = sereachDto.getInvestment_institutions_type();
        String segmentation = sereachDto.getSegmentation();
        String stage = sereachDto.getStage();
        String city = sereachDto.getCity();
        String working_background_desc = sereachDto.getWorking_background_desc();
        String educational_background_desc = sereachDto.getEducational_background_desc();

        String size = "0";
        String from = "20";
        if (sereachDto.getPageNum() != null && !"".equals(sereachDto.getPageNum())) {
            size = sereachDto.getPageNum();
        }
        if (sereachDto.getPageSize() != null && !"".equals(sereachDto.getPageSize())) {
            from = sereachDto.getPageSize();
        }
        int sizeb = Integer.parseInt(size);
        int froma = 20;
        if (null != from) {
            froma = Integer.parseInt(from);
        }
        int sizea = (sizeb - 1) * froma;
        if (sizea > 100) {
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（100条）");
            return result;
        }else
        {
            froma = (100 - sizea) >= froma ? froma : (100 - sizea);
        }

//	    List citiess = Arrays.asList(cities);
//	        String[] stages = sereachDto.getStage().split(",");

        //查询项目基础数据
        List<Map<String, Object>> list = projectsServiceImplUtil.getBaseProjectInfo(userId, type, segmentation, stage,
                city, working_background_desc, educational_background_desc, sizea, froma);

        //查询项目实时统计数据
        Follow follow = new Follow();
        Interview interview = new Interview();
        for (Map<String, Object> obj : list) {
            follow.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            follow.setStatus(1);
            follow.setUserId(null);
            obj.put("num", followMapper.selectCount(follow));
            follow.setUserId(userId);
            if (followMapper.selectCount(follow) > 0) {
                obj.put("yn", 1);
            } else {
                obj.put("yn", 0);
            }

            interview.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            obj.put("inum", interviewMapper.selectCount(interview));
        }
        if (list.size() <= 0) {
            result.setStatus(202);
            result.setMessage("无查询数据");
            return result;
        }
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : list) {
            if (map.get("yn") == null) {
                map.put("yn", 0);
            }
            list2.add(map);
        }
        result.setData(list2);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    /**
     * 图表项目筛选
     *
     * @param sereachDto 筛选条件
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> findProjectBySviewA(SereachDto sereachDto) {
        CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();

        try {
            String userId = sereachDto.getUserId();
            String type = sereachDto.getInvestment_institutions_type();
            String segmentation = sereachDto.getSegmentation();
            String stage = sereachDto.getStage();
            String city = sereachDto.getCity();
            String working_background_desc = sereachDto.getWorking_background_desc();
            String educational_background_desc = sereachDto.getEducational_background_desc();

            String size = "0";
            String from = "20";
            if (sereachDto.getPageNum() != null && !"".equals(sereachDto.getPageNum())) {
                size = sereachDto.getPageNum();
            }
            if (sereachDto.getPageSize() != null && !"".equals(sereachDto.getPageSize())) {
                from = sereachDto.getPageSize();
            }
            int sizeb = 0;
            if (null != size) {
                sizeb = Integer.parseInt(size);
            }

            int froma = 20;
            if (null != from) {
                froma = Integer.parseInt(from);
            }
            int sizea = (sizeb - 1) * froma;
            if (sizea > 100) {
                result.setStatus(201);
                result.setMessage("查询记录数超出限制（100条）");
                return result;
            }else
            {
                froma = (100 - sizea) >= froma ? froma : (100 - sizea);
            }

//	    List citiess = Arrays.asList(cities);
//	        String[] stages = sereachDto.getStage().split(",");

            //查询项目基础数据
            List<Map<String, Object>> list = projectsServiceImplUtil.getBaseProjectInfoA(userId, type, segmentation, stage,
                    city, working_background_desc, educational_background_desc, sizea, froma, endTime, beginTime);

            //查询项目实时统计数据
            Follow follow = new Follow();
            Interview interview = new Interview();
            for (Map<String, Object> obj : list) {
                follow.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
                follow.setStatus(1);
                follow.setUserId(null);
                obj.put("num", followMapper.selectCount(follow));
                follow.setUserId(userId);
                if (followMapper.selectCount(follow) > 0) {
                    obj.put("yn", 1);
                } else {
                    obj.put("yn", 0);
                }

                interview.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
                obj.put("inum", interviewMapper.selectCount(interview));
            }
            if (list.size() <= 0) {
                result.setStatus(202);
                result.setMessage("无查询数据");
                return result;
            }
            List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> map : list) {
                if (map.get("yn") == null) {
                    map.put("yn", 0);
                }
                list2.add(map);
            }
            result.setData(list2);
            result.setStatus(200);
            result.setMessage("success");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取项目详情
     * @param body
     * @return
     */
    @Override
    public CommonDto<ProjectDetailOutputDto> getProjectDetails(Map<String,Object> body){
        CommonDto<ProjectDetailOutputDto> result =new CommonDto<>();
        ProjectDetailOutputDto projectDetailOutputDto = new ProjectDetailOutputDto();

        if (body.get("projectId") == null || "".equals(body.get("projectId"))){
            result.setStatus(50001);
            result.setMessage("项目id不能为空");
            result.setData(null);

            return result;
        }

        if (body.get("token") == null || "".equals(body.get("token"))){
            result.setMessage("用户token不能为空");
            result.setData(null);
            result.setStatus(50001);

            return result;
        }

        Integer xmid = (Integer) body.get("projectId");

        Projects projects = projectsMapper.selectByPrimaryKey(xmid);

        //查不到项目报错就
        if (projects == null ){
            result.setMessage("没有找到项目信息，请检查项目id");
            result.setData(null);
            result.setStatus(50001);

            return result;
        }
        //获取项目融资阶段
        Example projectFinancingLogExample = new Example(ProjectFinancingLog.class);
        projectFinancingLogExample.and().andEqualTo("projectId",xmid).andEqualTo("status",1);
        projectFinancingLogExample.setOrderByClause("create_time DESC");

        String financingStage = "";

       List<ProjectFinancingLog> projectFinancingLogList = projectFinancingLogMapper.selectByExample(projectFinancingLogExample);
       //看看现在的融资历史是否有，有的话拿到融资阶段
       if (projectFinancingLogList.size()  >  0){
        financingStage = projectFinancingLogList.get(0).getStage();
       }else{
           Example projectFinancingLogListOne = new Example(ProjectFinancingLog.class);
           projectFinancingLogListOne.and().andEqualTo("projectId",xmid);
           projectFinancingLogListOne.setOrderByClause("create_time DESC");

            List<ProjectFinancingLog> projectFinancingLogList1 = projectFinancingLogMapper.selectByExample(projectFinancingLogListOne);
            if (projectFinancingLogList1.size() > 0){
                financingStage = projectFinancingLogList1.get(0).getStage();
            }
       }



        //获取项目的领域
        String field = "";
        Example projectSegExample = new Example(ProjectSegmentation.class);
        projectSegExample.and().andEqualTo("projectId",xmid);
        PageHelper pageHelper = new PageHelper();
        PageHelper.startPage(0,3);

        List<ProjectSegmentation> projectSegmentationList = projectSegmentationMapper.selectByExample(projectSegExample);
        if (projectSegmentationList.size() > 0){
            for (ProjectSegmentation ps:projectSegmentationList){
                field += ps.getSegmentationName();
                field += " ";
            }
        }

        //设置数据
        projectDetailOutputDto.setField(field);
        projectDetailOutputDto.setFinancingStage(financingStage);
        projectDetailOutputDto.setShortName(projects.getShortName());
        projectDetailOutputDto.setFullName(projects.getFullName());
        projectDetailOutputDto.setKernelDesc(projects.getKernelDesc());
        projectDetailOutputDto.setCommet(projects.getCommet());
        projectDetailOutputDto.setUrl(projects.getUrl());
        projectDetailOutputDto.setTerritory(projects.getCity());
        projectDetailOutputDto.setProjectLogo(projects.getProjectLogo());
        if (projects.getProjectInvestmentHighlights() == null){
            projects.setProjectInvestmentHighlights("");
        }
        projectDetailOutputDto.setProjectInvestmentHighlights(projects.getProjectInvestmentHighlights());



        result.setStatus(200);
        result.setMessage("success");
        result.setData(projectDetailOutputDto);

        return result;
    }

    /**
     * 获取项目当前融资信息
     * @param body
     * @return
     */
    @Override
    public CommonDto<Map<String,Object>> getProjectFinancingNow(Map<String,Object> body){
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        Map<String,Object> obj = new HashMap<>();

        if (body.get("projectId") == null || "".equals(body.get("projectId"))){
            result.setStatus(50001);
            result.setMessage("项目id不能为空");
            result.setData(null);

            return result;
        }

        if (body.get("token") == null || "".equals(body.get("token"))){
            result.setMessage("用户token不能为空");
            result.setData(null);
            result.setStatus(50001);

            return result;
        }

        Integer xmid = (Integer) body.get("projectId");

        ProjectFinancingLog projectFinancingLogForSearch = new ProjectFinancingLog();
        projectFinancingLogForSearch.setProjectId(xmid);
        projectFinancingLogForSearch.setStatus(1);

        List<ProjectFinancingLog> projectFinancingLogList = projectFinancingLogMapper.select(projectFinancingLogForSearch);
        if (projectFinancingLogList.size() > 0){
            ProjectFinancingLog projectFinancingLog = projectFinancingLogList.get(0);
            obj.put("hasData",true);
            String stage = "";
            if (projectFinancingLog.getStage() != null){
                stage = projectFinancingLog.getStage();
            }
            obj.put("stage",stage);

            obj.put("amount",projectFinancingLog.getAmount());
            String stockRight ="未知";
            if (projectFinancingLog.getStockRight() != null){
                BigDecimal bigDecimal =projectFinancingLog.getStockRight().setScale(2,BigDecimal.ROUND_DOWN);
                stockRight = String.valueOf(bigDecimal) + "%";
            }
            obj.put("stockRight",stockRight);
            String projectFinancingUseful = "";
            if (projectFinancingLog.getProjectFinancingUseful() != null){
                projectFinancingUseful = projectFinancingLog.getProjectFinancingUseful();
            }
            obj.put("projectFinancingUseful",projectFinancingUseful);

            String currency = "人民币";
            if (projectFinancingLog.getCurrency() != null){
                if (projectFinancingLog.getCurrency() == 1){
                    currency = "美元";
                }
            }
            obj.put("currency",currency);

            result.setData(obj);
            result.setMessage("success");
            result.setStatus(200);
        } else {
            obj.put("hasData",false);
            obj.put("stage","");
            obj.put("amount","");
            obj.put("stockRight","");
            obj.put("projectFinancingUseful","");
            obj.put("currency","");

            result.setData(obj);
            result.setMessage("success");
            result.setStatus(200);
        }

        return result;
    }

    /**
     * 获取项目历史融资信息
     * @param body
     * @return
     */
    @Override
    public CommonDto<List<Map<String,Object>>> getProjectFinancingHistory(Map<String,Object> body){
        CommonDto<List<Map<String,Object>>> result = new CommonDto<>();
        List<Map<String,Object>> list = new ArrayList<>();



        if (body.get("projectId") == null || "".equals(body.get("projectId"))){
            result.setStatus(50001);
            result.setMessage("项目id不能为空");
            result.setData(null);

            return result;
        }

        if (body.get("token") == null || "".equals(body.get("token"))){
            result.setMessage("用户token不能为空");
            result.setData(null);
            result.setStatus(50001);

            return result;
        }

        Integer xmid = (Integer) body.get("projectId");



        List<ProjectFinancingLog> projectFinancingLogList = projectFinancingLogMapper.selectProjectFinancingLogList(xmid,2);
        //如果融资历史有就返回
        if (projectFinancingLogList.size() > 0){
            for (ProjectFinancingLog pfl:projectFinancingLogList){
                Map<String,Object> obj = new HashMap<>();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
                String dateString ="";
                if (pfl.getFinancingTime() != null){
                    dateString = simpleDateFormat.format(pfl.getFinancingTime());
                }

                //获取投资方姓名
                Integer pflid = pfl.getId();
                ProjectFinancingHistoryInvestors projectFinancingHistoryInvestors = new ProjectFinancingHistoryInvestors();
                projectFinancingHistoryInvestors.setProjectFinancingHistoryId(pflid);

                List<ProjectFinancingHistoryInvestors> projectFinancingHistoryInvestorsList = projectFinancingHistoryInvestorsMapper.select(projectFinancingHistoryInvestors);
                StringBuffer touzifang= new StringBuffer();
                if (projectFinancingHistoryInvestorsList.size() > 0){
                    if (projectFinancingHistoryInvestorsList.size() > 2){
                        for (Integer i = 0;i < 1 ;i++){
                            touzifang.append(projectFinancingHistoryInvestorsList.get(i).getInvestorName());
                            touzifang.append(" ");
                        }
                    }else {
                        for (ProjectFinancingHistoryInvestors pfhi:projectFinancingHistoryInvestorsList){
                            touzifang.append(pfhi.getInvestorName());
                            touzifang.append(" ");
                        }
                    }
                }

                obj.put("financingTime",dateString);
                obj.put("stage",pfl.getStage());
                obj.put("touzifang",touzifang);

                list.add(obj);
            }

            result.setData(list);
            result.setMessage("success");
            result.setStatus(200);
        }else {
            result.setStatus(200);
            result.setMessage("success");
            result.setData(list);
        }


        return result;
    }

    /**
     * 获取团队成员接口
     * @param body
     * @return
     */
    @Override
    public CommonDto<List<Map<String,Object>>> getProjectFinancingTeam(Map<String,Object> body){
        CommonDto<List<Map<String,Object>>> result = new CommonDto<>();
        List<Map<String,Object>> obj = new ArrayList<>();

        if (body.get("projectId") == null || "".equals(body.get("projectId"))){
            result.setStatus(50001);
            result.setMessage("项目id不能为空");
            result.setData(null);

            return result;
        }

        if (body.get("token") == null || "".equals(body.get("token"))){
            result.setMessage("用户token不能为空");
            result.setData(null);
            result.setStatus(50001);

            return result;
        }

        Integer xmid = (Integer) body.get("projectId");

        ProjectTeamMember projectTeamMember =new ProjectTeamMember();
        projectTeamMember.setProjectId(xmid);
        projectTeamMember.setYn(0);


        List<ProjectTeamMember> projectTeamMemberList = projectTeamMemberMapper.select(projectTeamMember);
        if (projectTeamMemberList.size() > 0){

            for (ProjectTeamMember ptm:projectTeamMemberList){
                Map<String,Object> map = new HashMap<>();

                //获取教育经历
                ProjectTeamMemberEducation projectTeamMemberEducation = new ProjectTeamMemberEducation();
                projectTeamMemberEducation.setProjectTeamMemberId(ptm.getId());
                StringBuffer jiaoyujingli = new StringBuffer();
                StringBuffer gongzuojingli = new StringBuffer();

                List<ProjectTeamMemberEducation> projectTeamMemberEducationList = projectTeamMemberEducationMapper.select(projectTeamMemberEducation);
                if (projectTeamMemberEducationList.size() > 0){
                    if (projectTeamMemberEducationList.size() > 3){
                        for (int j = 0;j<2;j++){
                            jiaoyujingli.append(projectTeamMemberEducationList.get(j).getEducationExperience());
                            jiaoyujingli.append(" ");
                        }
                    }else {
                        for (ProjectTeamMemberEducation ptme:projectTeamMemberEducationList){
                            jiaoyujingli.append(ptme.getEducationExperience());
                            jiaoyujingli.append(" ");
                        }
                    }
                }


                //获取工作经历
                ProjectTeamMemberWork projectTeamMemberWork = new ProjectTeamMemberWork();
                projectTeamMemberWork.setProjectTeamMemberId(ptm.getId());

                List<ProjectTeamMemberWork> projectTeamMemberWorkList = projectTeamMemberWorkMapper.select(projectTeamMemberWork);
                if (projectTeamMemberWorkList.size() > 0){
                    if (projectTeamMemberWorkList.size() > 3){
                        for (int k = 0;k<2;k++){
                            gongzuojingli.append(projectTeamMemberWorkList.get(k).getWorkExperience());
                            gongzuojingli.append(" ");
                        }
                    }else {
                        for (ProjectTeamMemberWork ptmw:projectTeamMemberWorkList){
                            gongzuojingli.append(ptmw.getWorkExperience());
                            gongzuojingli.append(" ");
                        }
                    }
                }

                //组合数据返回前端
                map.put("mumberName",ptm.getMumberName());
                map.put("mumberDuties",ptm.getMumberDuties());
                map.put("jiaoyujingli",jiaoyujingli);
                map.put("gongzuojingli",gongzuojingli);
                map.put("shareRato",ptm.getShareRatio());
                map.put("mumberDesc",ptm.getMumberDesc());

                obj.add(map);

                result.setMessage("success");
                result.setStatus(200);
                result.setData(obj);

            }
        }else{
            result.setData(obj);
            result.setStatus(200);
            result.setMessage("success");
        }

        return result;
    }

    @Override
    public CommonDto<List<Map<String,Object>>> getProjectCompeting(Map<String,Object> body){
        CommonDto<List<Map<String,Object>>> result = new CommonDto<>();
        List<Map<String,Object>> list = new ArrayList<>();

        if (body.get("projectId") == null || "".equals(body.get("projectId"))){
            result.setStatus(50001);
            result.setMessage("项目id不能为空");
            result.setData(null);

            return result;
        }

        if (body.get("token") == null || "".equals(body.get("token"))){
            result.setMessage("用户token不能为空");
            result.setData(null);
            result.setStatus(50001);

            return result;
        }

        Integer xmid = (Integer) body.get("projectId");

        Projects projects = projectsMapper.selectByPrimaryKey(xmid);

        //String lunci = projects
        return result;
    }

    /**
     * 判断项目是否是我的 的接口
     * @param xmid 项目id
     * @param token 用户token
     * @return
     */
    @Override
    public CommonDto<Boolean> judgeProjectIsMine(Integer xmid,String token){
    CommonDto<Boolean> result  = new CommonDto<>();
    Integer userid = userExistJudgmentService.getUserId(token);
    if (userid == -1){
        result.setMessage("用户token非法，请检查");
        result.setData(false);
        result.setStatus(502);

        return result;
    }

    Projects projects = projectsMapper.selectByPrimaryKey(xmid);
    if (projects == null){
        result.setMessage("没有找到当前项目id的项目，请检查");
        result.setStatus(502);
        result.setData(false);

        return result;
    }else{
        Integer userIdForDuibi = projects.getUserid();
        if (userid.equals(userIdForDuibi)){
            result.setData(true);
            result.setStatus(200);
            result.setMessage("success");
        }else {
            result.setMessage("项目和用户不匹配");
            result.setStatus(502);
            result.setData(false);
        }
    }


    return result;
    }

    /**
     * 获取项目管理员列表接口
     * @param body
     * @return
     */
    @Override
    public CommonDto<List<ProjectAdministratorOutputDto>> getProjectAdministractorList(Map<String,Integer> body){
        CommonDto<List<ProjectAdministratorOutputDto>> result = new CommonDto<>();
        List<ProjectAdministratorOutputDto> list = new ArrayList<>();

        if (body.get("projectId") == null){
            result.setData(list);
            result.setStatus(50001);
            result.setMessage("项目id不能为空，请检查");

            return result;
        }


        //先获取到项目信息中的简称
        Projects projects = projectsMapper.selectByPrimaryKey(body.get("projectId"));

        if (projects == null){
            result.setMessage("没找到该项目id对应的项目，请检查");
            result.setStatus(50001);
            result.setData(list);

            return result;
        }

        String projectName = projects.getShortName();

        //拿项目id去查项目的管理员
        ProjectAdministrator projectAdministrator = new ProjectAdministrator();
        projectAdministrator.setProjectsId(body.get("projectId"));
        projectAdministrator.setYn(0);

        List<ProjectAdministrator> projectAdministratorList = projectAdministratorMapper.select(projectAdministrator);

        if (projectAdministratorList.size() > 0){
            //获取项目管理员的信息并加入到list中去
            for (ProjectAdministrator pa:projectAdministratorList){
                //获取管理员信息
                Map<String,String> users = usersMapper.findUserInfoAssemble(pa.getUserId());
                if (users != null){
                    //获取到管理员的oppenid,token,真实姓名，昵称，头像

                    String userOppenid =users.get("openId");
                    String userToken = users.get("token");
                    String userNickName = users.get("nick_name");
                    String userRealName = users.get("actual_name");
                    String userHeadpic = "";
                    if (users.get("headpic_real") == null || "".equals(users.get("headpic_real"))){
                        userHeadpic = users.get("headpic");
                    }else {
                        userHeadpic = users.get("headpic_real");
                    }

                    String companyDuties = "";
                    if (users.get("company_duties") != null){
                        companyDuties=users.get("company_duties");
                    }

                    //放到返回值里
                    ProjectAdministratorOutputDto projectAdministratorOutputDto= new  ProjectAdministratorOutputDto();
                    projectAdministratorOutputDto.setCompanyName(projectName);
                    projectAdministratorOutputDto.setHeadpic(userHeadpic);
                    projectAdministratorOutputDto.setNickName(userNickName);
                    projectAdministratorOutputDto.setRealName(userRealName);
                    projectAdministratorOutputDto.setToken(userToken);
                    projectAdministratorOutputDto.setCompanyDuties(companyDuties);

                    list.add(projectAdministratorOutputDto);
                }

            }

            result.setData(list);
            result.setStatus(200);
            result.setMessage("success");
        }else {
            result.setMessage("该项目没有管理员");
            result.setStatus(502);
            result.setData(list);
        }


        return result;
    }

    /**
     * 通过项目id获取项目信息接口
     * @param body
     * @return
     */
    @Override
    public CommonDto<ProjectComplexOutputDto> getProjectComplexInfo(Map<String,Integer> body){
        CommonDto<ProjectComplexOutputDto> result = new CommonDto<>();

        if (body.get("projectId") == null){
            result.setData(null);
            result.setMessage("项目id不能为空");
            result.setStatus(50001);

            log.info("通过项目id获取项目信息接口场景");
            log.info("项目id不能为空");

            return result;
        }

        Projects projects = projectsMapper.selectByPrimaryKey(body.get("projectId"));
        if (projects == null){
            result.setStatus(50001);
            result.setMessage("当前项目id没有找到项目信息，请检查项目id");
            result.setData(null);

            log.info("通过项目id获取项目信息接口场景");
            log.info("当前项目id没有找到项目信息，请检查项目id");

            return result;
        }

        Integer xmid = body.get("projectId");
        //获取项目融资阶段
        Example projectFinancingLogExample = new Example(ProjectFinancingLog.class);
        projectFinancingLogExample.and().andEqualTo("projectId",xmid).andEqualTo("status",1);
        projectFinancingLogExample.setOrderByClause("create_time DESC");

        String financingStage = "";

        List<ProjectFinancingLog> projectFinancingLogList = projectFinancingLogMapper.selectByExample(projectFinancingLogExample);
        //看看现在的融资历史是否有，有的话拿到融资阶段
        if (projectFinancingLogList.size()  >  0){
            financingStage = projectFinancingLogList.get(0).getStage();
        }else{
            Example projectFinancingLogListOne = new Example(ProjectFinancingLog.class);
            projectFinancingLogListOne.and().andEqualTo("projectId",xmid);
            projectFinancingLogListOne.setOrderByClause("create_time DESC");

            List<ProjectFinancingLog> projectFinancingLogList1 = projectFinancingLogMapper.selectByExample(projectFinancingLogListOne);
            if (projectFinancingLogList1.size() > 0){
                financingStage = projectFinancingLogList1.get(0).getStage();
            }
        }



        //获取项目的领域
        String field = "";
        Example projectSegExample = new Example(ProjectSegmentation.class);
        projectSegExample.and().andEqualTo("projectId",xmid);
        PageHelper pageHelper = new PageHelper();
        PageHelper.startPage(0,3);

        List<ProjectSegmentation> projectSegmentationList = projectSegmentationMapper.selectByExample(projectSegExample);
        if (projectSegmentationList.size() > 0){
            for (ProjectSegmentation ps:projectSegmentationList){
                field += ps.getSegmentationName();
                field += " ";
            }
        }
        String fieldEdit = field.substring(0,field.length()-1);

        ProjectComplexOutputDto projectComplexOutputDto =new ProjectComplexOutputDto();
        projectComplexOutputDto.setProjectDesc(projects.getKernelDesc());
        projectComplexOutputDto.setProjectId(body.get("projectId"));
        String logo = "";
        if (projects.getProjectLogo() != null){
            logo = projects.getProjectLogo();
        }
        projectComplexOutputDto.setProjectLogo(logo);
        projectComplexOutputDto.setProjectShortName(projects.getShortName());
        projectComplexOutputDto.setSegmentation(fieldEdit);
        projectComplexOutputDto.setCity(projects.getCity());
        projectComplexOutputDto.setRound(financingStage);

        String projectInvestmentHighlights = "";
        if (projects.getProjectInvestmentHighlights() != null){
            projectInvestmentHighlights = projects.getProjectInvestmentHighlights();
        }
        projectComplexOutputDto.setProjectInvestmentHighlights(projectInvestmentHighlights);


        result.setData(projectComplexOutputDto);
        result.setMessage("success");
        result.setStatus(200);

        return result;
    }
}