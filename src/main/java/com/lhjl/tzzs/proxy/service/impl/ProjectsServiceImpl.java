package com.lhjl.tzzs.proxy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lhjl.tzzs.proxy.mapper.FollowMapper;
import com.lhjl.tzzs.proxy.mapper.InterviewMapper;
import com.lhjl.tzzs.proxy.model.Follow;
import com.lhjl.tzzs.proxy.model.Interview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.lhjl.tzzs.proxy.dto.CommonDto;

import com.lhjl.tzzs.proxy.dto.SereachDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.service.ProjectsService;

/**
 * 项目
 * @author lmy
 *
 */
@Service
public class ProjectsServiceImpl implements ProjectsService {
    @Value("${statistics.beginTime}")
    private String beginTime ;

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

    /**
     * 查询我关注的项目
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> findProjectByUserId(String userId, Integer pageNum, Integer pageSize) {
        CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();

        //计算查询起始记录
        Integer beginNum = (pageNum-1)*pageSize;
        //最多返回100条记录
        if(beginNum > 100){
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（100条）");
            return result;
        }else{
            pageSize = (100 - beginNum)>=pageSize?pageSize:(100-beginNum);
        }

        List<Map<String, Object>> list = projectsMapper.findProjectByUserId(userId, beginNum, pageSize);
        //List<Map<String, Object>> list =projectsServiceImplUtil.getSearchBaseMyProject(userId,beginNum,pageSize);
        Follow follow = new Follow();
        Interview interview = new Interview();
        for (Map<String, Object> obj : list){
            follow.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            follow.setStatus(1);
            follow.setUserId(null);
            obj.put("num", followMapper.selectCount(follow));
            follow.setUserId(userId);
            if (followMapper.selectCount(follow)>0){
                obj.put("yn", 1);
            }else {
                obj.put("yn", 0);
            }

            interview.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            obj.put("inum",interviewMapper.selectCount(interview));
        }

        //判断是否还有查询结果
        if(list.size() <= 0){
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
     * @param shortName
     * @param userId
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> findProjectByShortName(String shortName,String userId) {
        CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
        List<Map<String, Object>> list= projectsMapper.findProjectByShortName(shortName,userId);
        List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
        Follow follow = new Follow();
        Interview interview = new Interview();
        for (Map<String, Object> obj : list){
            follow.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            follow.setStatus(1);
            follow.setUserId(null);
            obj.put("num", followMapper.selectCount(follow));
            follow.setUserId(userId);
            if (followMapper.selectCount(follow)>0){
                obj.put("yn", 1);
            }else {
                obj.put("yn", 0);
            }

            interview.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            obj.put("inum",interviewMapper.selectCount(interview));
        }
        for(Map<String, Object> map : list){
            if(map.get("yn") == null){
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
     * @param shortName
     * @param userId
     * @param size
     * @param from
     * @return
     */
    @Override
    public CommonDto<Map<String,List<Map<String, Object>>>> findProjectByShortNameAll(String shortName,String userId,String size,String from) {
        CommonDto<Map<String,List<Map<String, Object>>>> result = new CommonDto<Map<String,List<Map<String, Object>>>>();
        int sizeb = Integer.parseInt(size);
        int froma =Integer.parseInt(from);
        int sizea=(sizeb-1)*froma;
        if(sizea > 100){
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（100条）");
            return result;
        }{
        	froma = (100 - sizea)>=froma ?froma :(100 - sizea);
        }
        Map<String,List<Map<String, Object>>> dataMap = new HashMap<String,List<Map<String, Object>>>();
        //List<Map<String, Object>> list = projectsMapper.findProjectByShortNameAll(shortName,userId,sizea,froma);
        List<Map<String, Object>> list= projectsServiceImplUtil.getSearchBaseProjectAll(userId,shortName,sizea,froma);
        Follow follow = new Follow();
        Interview interview = new Interview();
        for (Map<String, Object> obj : list){
            follow.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            follow.setStatus(1);
            follow.setUserId(null);
            obj.put("num", followMapper.selectCount(follow));
            follow.setUserId(userId);
            if (followMapper.selectCount(follow)>0){
                obj.put("yn", 1);
            }else {
                obj.put("yn", 0);
            }

            interview.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            obj.put("inum",interviewMapper.selectCount(interview));
        }
        if(list.size() <= 0){
            result.setStatus(202);
            result.setMessage("无查询数据");
            return result;
        }
        List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();

        for(Map<String, Object> map : list){
            if(map.get("yn") == null){
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
     * @param sereachDto 筛选条件
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> findProjectBySview(SereachDto sereachDto) {
        CommonDto<List<Map<String,Object>>> result =new CommonDto<List<Map<String, Object>>>();

        String userId =sereachDto.getUserId();
        String type = sereachDto.getInvestment_institutions_type();
        String segmentation = sereachDto.getSegmentation();
        String stage =sereachDto.getStage();
        String city  =sereachDto.getCity();
        String working_background_desc =sereachDto.getWorking_background_desc();
        String educational_background_desc=sereachDto.getEducational_background_desc();

        String size ="0";
        String from ="10";
        if(sereachDto.getPageNum() != null && !"".equals(sereachDto.getPageNum())){
            size=sereachDto.getPageNum();
        }
        if(sereachDto.getPageSize() !=null && !"".equals(sereachDto.getPageSize())){
            from=sereachDto.getPageSize();
        }
        int sizeb =Integer.parseInt(size);
        int froma =Integer.parseInt(from);
        int sizea=(sizeb-1)*froma;
        if(sizea > 100){
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（100条）");
            return result;
        }{
        	froma = (100 - sizea)>=froma ?froma :(100 - sizea);
        }

//	    List citiess = Arrays.asList(cities);
//	        String[] stages = sereachDto.getStage().split(",");

        //查询项目基础数据
        List<Map<String, Object>> list= projectsServiceImplUtil.getBaseProjectInfo(userId, type, segmentation, stage,
                city, working_background_desc, educational_background_desc, sizea, froma);

        //查询项目实时统计数据
        Follow follow = new Follow();
        Interview interview = new Interview();
        for (Map<String, Object> obj : list){
            follow.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            follow.setStatus(1);
            follow.setUserId(null);
            obj.put("num", followMapper.selectCount(follow));
            follow.setUserId(userId);
            if (followMapper.selectCount(follow)>0){
                obj.put("yn", 1);
            }else {
                obj.put("yn", 0);
            }

            interview.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            obj.put("inum",interviewMapper.selectCount(interview));
        }
        if(list.size() <= 0){
            result.setStatus(202);
            result.setMessage("无查询数据");
            return result;
        }
        List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
        for(Map<String, Object> map :list){
            if(map.get("yn") == null){
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
     * @param sereachDto 筛选条件
     * @return
     */
    @Override
    public CommonDto<List<Map<String, Object>>> findProjectBySviewA(SereachDto sereachDto) {
        CommonDto<List<Map<String,Object>>> result =new CommonDto<List<Map<String, Object>>>();

        String userId =sereachDto.getUserId();
        String type = sereachDto.getInvestment_institutions_type();
        String segmentation = sereachDto.getSegmentation();
        String stage =sereachDto.getStage();
        String city  =sereachDto.getCity();
        String working_background_desc =sereachDto.getWorking_background_desc();
        String educational_background_desc=sereachDto.getEducational_background_desc();

        String size ="0";
        String from ="10";
        if(sereachDto.getPageNum() != null && !"".equals(sereachDto.getPageNum())){
            size=sereachDto.getPageNum();
        }
        if(sereachDto.getPageSize() !=null && !"".equals(sereachDto.getPageSize())){
            from=sereachDto.getPageSize();
        }
        int sizeb =Integer.parseInt(size);
        int froma =Integer.parseInt(from);
        int sizea=(sizeb-1)*froma;
        if(sizea > 100){
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（100条）");
            return result;
        }{
            froma = (100 - sizea)>=froma ?froma :(100 - sizea);
        }

//	    List citiess = Arrays.asList(cities);
//	        String[] stages = sereachDto.getStage().split(",");

        //查询项目基础数据
        List<Map<String, Object>> list= projectsServiceImplUtil.getBaseProjectInfoA(userId, type, segmentation, stage,
                city, working_background_desc,educational_background_desc, sizea, froma,endTime,beginTime);

        //查询项目实时统计数据
        Follow follow = new Follow();
        Interview interview = new Interview();
        for (Map<String, Object> obj : list){
            follow.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            follow.setStatus(1);
            follow.setUserId(null);
            obj.put("num", followMapper.selectCount(follow));
            follow.setUserId(userId);
            if (followMapper.selectCount(follow)>0){
                obj.put("yn", 1);
            }else {
                obj.put("yn", 0);
            }

            interview.setProjectsId(Integer.valueOf(String.valueOf(obj.get("ID"))));
            obj.put("inum",interviewMapper.selectCount(interview));
        }
        if(list.size() <= 0){
            result.setStatus(202);
            result.setMessage("无查询数据");
            return result;
        }
        List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
        for(Map<String, Object> map :list){
            if(map.get("yn") == null){
                map.put("yn", 0);
            }
            list2.add(map);
        }
        result.setData(list2);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }
}