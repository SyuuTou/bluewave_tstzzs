package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ProjectAdministratorMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.mapper.UserTokenMapper;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.model.ProjectAdministrator;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.service.AdminProjectService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AdminProjectServiceImpl implements AdminProjectService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AdminProjectServiceImpl.class);

    @Autowired
    private ProjectsMapper projectsMapper;

    @Autowired
    private ProjectAdministratorMapper projectAdministratorMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public CommonDto<List<Map<String,Object>>> getProjectList(Integer pageNum, Integer pageSize, String shortName, Integer projectType){
        CommonDto<List<Map<String,Object>>> result =new CommonDto<>();
        List<Map<String,Object>> list = new ArrayList<>();

        //先验证参数
        if (pageNum == null){
            pageNum = 0;
        }

        if (pageSize == null){
            pageSize = 10;
        }

        if (shortName == null || "undefined".equals(shortName)){
            shortName = "";
        }

        if (projectType == null){
            result.setMessage("项目类型不能为空");
            result.setData(null);
            result.setStatus(50001);

            return result;
        }

        Integer startNum = pageSize*pageNum;

        //查询项目
        if (projectType == 0){
            //查数据导入的项目
            List<Map<String,Object>> projectList = projectsMapper.adminGetProjectListOne(startNum,pageSize,shortName,projectType);
            if (projectList.size() > 0){
                for (Map<String,Object> map:projectList){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (map.get("short_name") == null){
                        map.put("short_name","");
                    }
                    if (map.get("kernel_desc") == null){
                        map.put("kernel_desc","");
                    }
                    if (map.get("project_investment_highlights") == null){
                        map.put("project_investment_highlights","");
                    }
                    if (map.get("url") == null){
                        map.put("url","");
                    }
                    if (map.get("item_label") == null){
                        map.put("item_label","");
                    }
                    if (map.get("create_time") == null){
                        map.put("create_time","");
                    }else {
                        map.put("create_time",sdf.format(map.get("create_time")));
                    }
                    if (map.get("name") == null){
                        map.put("name","");
                    }
                    if (map.get("rating_stage") == null){
                        map.put("rating_stage","未评级");
                    }else {
                        Integer ratingStage= (Integer)map.get("rating_stage");
                        String ratingStageString = "";
                        switch (ratingStage){
                            case 0:ratingStageString="D级";
                            break;
                            case 1:ratingStageString="C级";
                                break;
                            case 2:ratingStageString="B级";
                                break;
                            case 3:ratingStageString="A级";
                                break;
                            case 4:ratingStageString="S级";
                                break;
                            default:
                                ratingStageString="";
                        }
                        map.put("rating_stage",ratingStageString);
                    }

                }
            }

            result.setMessage("success");
            result.setData(projectList);
            result.setStatus(200);

        }else if (projectType == 1){
            //查用户提交的项目
            List<Map<String,Object>> projectList =projectsMapper.adminGetProjectListTwo(startNum,pageSize,shortName,projectType);
            if (projectList.size() > 0){
                for (Map<String,Object> map:projectList){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (map.get("short_name") == null){
                        map.put("short_name","");
                    }
                    if (map.get("kernel_desc") == null){
                        map.put("kernel_desc","");
                    }
                    if (map.get("project_investment_highlights") == null){
                        map.put("project_investment_highlights","");
                    }
                    if (map.get("url") == null){
                        map.put("url","");
                    }
                    if (map.get("item_label") == null){
                        map.put("item_label","");
                    }
                    if (map.get("create_time") == null){
                        map.put("create_time","");
                    }else {
                        map.put("create_time",sdf.format(map.get("create_time")));
                    }
                    if (map.get("name") == null){
                        map.put("name","");
                    }
                    if (map.get("rating_stage") == null){
                        map.put("rating_stage","未评级");
                    }else {
                        Integer ratingStage= (Integer)map.get("rating_stage");
                        String ratingStageString = "";
                        switch (ratingStage){
                            case 0:ratingStageString="D级";
                                break;
                            case 1:ratingStageString="C级";
                                break;
                            case 2:ratingStageString="B级";
                                break;
                            case 3:ratingStageString="A级";
                                break;
                            case 4:ratingStageString="S级";
                                break;
                            default:
                                ratingStageString="";
                        }
                        map.put("rating_stage",ratingStageString);
                    }

                }
            }

            result.setMessage("success");
            result.setData(projectList);
            result.setStatus(200);

        }else {
            result.setStatus(502);
            result.setData(list);
            result.setMessage("项目类型不正确");

            return result;
        }


        return result;
    }

    /**
     * 一键设置没有管理员项目的管理员
     * @param administractorId 管理员id
     * @return
     */
    @Override
    public CommonDto<String> setProjectAdminOnestep(Integer administractorId){
        CommonDto<String> result = new CommonDto<>();

        if (administractorId == null){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("管理员id不能为空");

            return result;
        }

        //先去看看用户是否是有效的
        Users users = usersMapper.selectByPrimaryKey(administractorId);
        if (users == null){
            result.setMessage("该编号对应的管理员不存在");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        //再判断用户是否被删除了
        Example userTokenExample = new Example(UserToken.class);
        userTokenExample.and().andEqualTo("userId",administractorId);
        List<UserToken> userToken = userTokenMapper.selectByExample(userTokenExample);
        if (userToken.size() < 1){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("用户的信息已经被清除了，请检查后重输入");

            return result;
        }


        Date now =new Date();
        //先获取到项目列表
        Example projectExample = new Example(Projects.class);
        projectExample.and().andIsNull("projectSource");
        List<Projects> projectIdList = projectsMapper.selectByExample(projectExample);
        if (projectIdList.size() > 0){
            for (Projects p:projectIdList){
                //先将该项目的其他管理员删除
                Example deleteProjectExample = new Example(ProjectAdministrator.class);
                deleteProjectExample.and().andEqualTo("projectsId",p.getId()).andEqualTo("userId",administractorId);


                projectAdministratorMapper.deleteByExample(deleteProjectExample);

                //创建项目的管理员
                ProjectAdministrator projectAdministrator = new ProjectAdministrator();
                projectAdministrator.setYn(0);
                projectAdministrator.setProjectsId(p.getId());
                projectAdministrator.setCreateTime(now);
                projectAdministrator.setUserId(administractorId);
                projectAdministrator.setTypes(1);

                projectAdministratorMapper.insertSelective(projectAdministrator);
            }
        }

        log.info("执行完毕");

        result.setMessage("success");
        result.setStatus(200);
        result.setData(null);


        return result;
    }

    @Override
    public CommonDto<String> creatProjectAdministractors(Integer projectId,String administractorsId){
        CommonDto<String> result = new CommonDto<>();
        Date now =new Date();

        if (projectId == null){
            result.setMessage("项目id不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (administractorsId == null || "".equals(administractorsId) || "undefined".equals(administractorsId)){
            result.setData(null);
            result.setStatus(502);
            result.setMessage("管理员不能为空");
        }

        //解析项目管理员数组
        String[] adminIds = administractorsId.split(",");

        List<Integer> adminIdsInt = new ArrayList<>();
        for (int i=0;i<adminIds.length;i++){
            if (!("".equals(adminIds[i]))){
                Integer aa = Integer.valueOf(adminIds[i]);
                adminIdsInt.add(aa);
            }
        }
        //添加管理员
        for (int j = 0;j<adminIdsInt.size();j++){
            //先判断当前项目是否有当前管理员，有的话啥也不做，没有的话创建
            Example paExample = new Example(ProjectAdministrator.class);
            paExample.and().andEqualTo("userId",adminIdsInt.get(j)).andEqualTo("projectsId",projectId);

            List<ProjectAdministrator> projectAdministratorList =projectAdministratorMapper.selectByExample(paExample);

            if (projectAdministratorList.size() > 0){
                //有的话啥也不做
            }else {
                //没有的话创建
                ProjectAdministrator projectAdministrator = new ProjectAdministrator();

                projectAdministrator.setTypes(1);
                projectAdministrator.setUserId(adminIdsInt.get(j));
                projectAdministrator.setCreateTime(now);
                projectAdministrator.setProjectsId(projectId);
                projectAdministrator.setYn(0);

                projectAdministratorMapper.insertSelective(projectAdministrator);
            }

        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData(null);

        return result;
    }
}
