package com.lhjl.tzzs.proxy.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.lhjl.tzzs.proxy.dto.CommonDto;

import com.lhjl.tzzs.proxy.dto.SereachDto;
import com.lhjl.tzzs.proxy.service.ProjectsService;
import tk.mybatis.mapper.entity.Example;

/**
 * 项目
 *
 * @author lmy
 */
@Service
public class ProjectsServiceImpl implements ProjectsService {
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
    public CommonDto<Projects> getProjectDetails(Map<String,Object> body){
        CommonDto<Projects> result =new CommonDto<>();

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

        result.setStatus(200);
        result.setMessage("success");
        result.setData(projects);

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
                stockRight = String.valueOf(projectFinancingLog.getStockRight());
            }
            obj.put("stockRight",stockRight);
            String projectFinancingUseful = "";
            if (projectFinancingLog.getProjectFinancingUseful() != null){
                projectFinancingUseful = projectFinancingLog.getProjectFinancingUseful();
            }
            obj.put("projectFinancingUseful",projectFinancingUseful);

            result.setData(obj);
            result.setMessage("success");
            result.setStatus(200);
        } else {
            obj.put("hasData",false);
            obj.put("stage","");
            obj.put("amount","");
            obj.put("stockRight","");
            obj.put("projectFinancingUseful","");

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
}