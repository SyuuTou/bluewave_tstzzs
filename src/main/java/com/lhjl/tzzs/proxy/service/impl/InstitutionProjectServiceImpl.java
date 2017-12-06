package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InstitutionsProjectDto.InstitutionsProjectInputDto;
import com.lhjl.tzzs.proxy.dto.InstitutionsProjectDto.InstitutionsProjectOutputDto;
import com.lhjl.tzzs.proxy.mapper.FollowMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSegmentationMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.model.Follow;
import com.lhjl.tzzs.proxy.model.ProjectSegmentation;
import com.lhjl.tzzs.proxy.service.InstitutionsProjectService;
import com.lhjl.tzzs.proxy.service.UserExistJudgmentService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InstitutionProjectServiceImpl implements InstitutionsProjectService{

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InstitutionProjectServiceImpl.class);

    @Autowired
    private ProjectsMapper projectsMapper;

    @Resource
    private UserExistJudgmentService userExistJudgmentService;

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private ProjectSegmentationMapper projectSegmentationMapper;
    /**
     * 获取机构投资项目列表接口
     * @param body
     * @return
     */
    @Override
    public CommonDto<List<Map<String,Object>>> findInstitutionProject(InstitutionsProjectInputDto body) {
        CommonDto<List<Map<String,Object>>> resulut = new CommonDto<>();
        //先验证参数
        if (body.getInstitutionId() == null){
            resulut.setMessage("机构id不能为空");
            resulut.setData(null);
            resulut.setStatus(502);

            return resulut;
        }

        if (body.getFields() == null){
            body.setFields("");
        }

        if (body.getFinancingTime() == null){
            body.setFinancingTime("");
        }

        if (body.getStage() == null){
            body.setStage("");
        }

        if (body.getToken() == null){
            resulut.setStatus(502);
            resulut.setData(null);
            resulut.setMessage("用户token不能为空");

            return resulut;
        }

        if (body.getStartPage() == null || body.getStartPage() < 0){
            body.setStartPage(0);
        }

        if (body.getPageSize() == null || body.getPageSize() < 0){
            body.setPageSize(20);
        }

        //获取用户id
        Integer userId = userExistJudgmentService.getUserId(body.getToken());
        if (userId == -1){
            resulut.setMessage("用户token无效，请检查");
            resulut.setData(null);
            resulut.setStatus(502);

            return resulut;
        }


        Integer startPage = body.getStartPage()*body.getPageSize();

        //获取到项目列表
        List<Map<String,Object>> projectList = new ArrayList<>();
        projectList = getInstitutionProject(body.getInstitutionId(),body.getStage(),body.getFields(),body.getFinancingTime(),startPage,body.getPageSize());

        if(projectList.size() > 0){
            for (Map<String,Object> m:projectList){

                //计算关注数
                Follow follow = new Follow();
                Integer projectId = (Integer)m.get("id");
                follow.setProjectsId(projectId);
                follow.setStatus(1);
                Integer followNum = followMapper.selectCount(follow);

                //当图片没有的时候返回空
                m.put("followNum",followNum);
                if (m.get("project_logo") == null){
                    m.put("project_logo","");
                }

                //获取项目领域
                Example psegmentExample = new Example(ProjectSegmentation.class);
                psegmentExample.and().andEqualTo("projectId",projectId);

                List<String> psegment = new ArrayList<>();
                List<ProjectSegmentation> psegmentationList = projectSegmentationMapper.selectByExample(psegmentExample);
                if (psegmentationList.size()>0){
                    if (psegmentationList.size()<3){
                        for (ProjectSegmentation pss:psegmentationList){
                            psegment.add(pss.getSegmentationName());
                        }
                    }else {
                        for (int i =0;i<3;i++){
                            psegment.add(psegmentationList.get(i).getSegmentationName());
                        }
                    }
                }

                m.put("segmentations",psegment);
            }
        }

        //获取当前用户关注项目
        Follow userFollow = new Follow();
        userFollow.setUserId(body.getToken());
        userFollow.setStatus(1);


        //判断当前用户是否关注了列表里面的项目
        List<Follow> followList = followMapper.select(userFollow);
        if (followList.size() > 0){
            for (Follow f:followList){
                for (Map<String,Object> mm:projectList){
                    Integer xmid = (Integer) mm.get("id");
                    if (String.valueOf(f.getProjectsId()).equals(String.valueOf(xmid))){
                        mm.put("follow",true);
                    }else {
                        mm.put("follow",false);
                    }
                }
            }
        }else {
            for (Map<String,Object> mma:projectList){
                    mma.put("follow",false);
            }
        }



        resulut.setStatus(200);
        resulut.setData(projectList);
        resulut.setMessage("success");

        return resulut;
    }

    /**
     * 获取机构项目列表的私有方法
     * @param institutionId 机构id
     * @param stage 轮次
     * @param segmentationName 阶段名称
     * @param financingTime 融资时间
     * @param startNum 开始页码
     * @param pageSize 一页显示数量
     * @return
     */
    //@Cacheable(value = "getInstitutionProject", keyGenerator = "wiselyKeyGenerator")
    private List<Map<String,Object>> getInstitutionProject(Integer institutionId,String stage,String segmentationName,String financingTime,Integer startNum,Integer pageSize){
        List<Map<String,Object>> result = new ArrayList<>();

        result = projectsMapper.findInstitutionProject(institutionId,stage,segmentationName,financingTime,startNum,pageSize);

        return result;
    }
}
