package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.service.AdminProjectService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AdminProjectServiceImpl implements AdminProjectService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AdminProjectServiceImpl.class);

    @Autowired
    private ProjectsMapper projectsMapper;

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
}
