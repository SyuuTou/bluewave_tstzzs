package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 项目基础数据获取工具类
 * @author lmy
 *
 */
@Component
public class ProjectsServiceImplUtil {

    @Resource
    private ProjectsMapper projectsMapper;

    /**
     * 获取项目基础数据
     *
     * @param userId                      用户ID
     * @param type                        机构类型
     * @param segmentation                所属领域
     * @param stage                       轮次
     * @param city                        城市
     * @param working_background_desc     工作背景
     * @param educational_background_desc 教育背景
     * @param sizea                       起始页
     * @param froma                       每页记录数
     * @return
     */
    @Cacheable(value = "getBaseProjectInfo", keyGenerator = "wiselyKeyGenerator")
   // @CacheEvict(value = "getBaseProjectInfo", allEntries=true)
    public List<Map<String, Object>> getBaseProjectInfo(String userId, String type, String segmentation,
                                                        String stage, String city,
                                                        String working_background_desc, String educational_background_desc,
                                                        Integer sizea, Integer froma) {

        String[] segmentations = null;
        String[] stages = null;
        String[] cities = null;
        String[] working_background_descs = null;
        String[] educational_background_descs = null;

        if (type != null && !"".equals(type)) {
            if (type.contains("行业指数机构")) {
                type = "0,1";
                String[] type2 = type.split(",");
                int[] types = new int[type2.length];
                for (int i = 0; i < type2.length; i++) {
                    types[i] = Integer.parseInt(type2[i]);
                }
            }
            if ("50指数机构".equals(type)) {
                type ="1";
                String[] type2 = type.split(",");
                int[] types = new int[type2.length];
                for (int i = 0; i < type2.length; i++) {
                    types[i] = Integer.parseInt(type2[i]);
                }
            }

        }
        String[] type2 = type.split(",");
        int[] types = new int[type2.length];
        if (type != null && !"".equals(type)) {
            for (int i = 0; i < type2.length; i++) {
                types[i] = Integer.parseInt(type2[i]);
            }
        }

        if (segmentation != null && !"".equals(segmentation)) {
            segmentations = segmentation.split(",");
        }

        if (stage != null && !"".equals(stage)) {
            stages = stage.split(",");
        }

        if (city != null && !"".equals(city)) {
            cities = city.split(",");
        }

        if (working_background_desc != null && !"".equals(working_background_desc)) {
            working_background_descs = working_background_desc.split(",");
        }

        if (educational_background_desc != null && !"".equals(educational_background_desc)) {
            educational_background_descs = educational_background_desc.split(",");

        }

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        if ((cities == null || cities.length == 0) && (working_background_descs == null || working_background_descs.length == 0) && (educational_background_descs == null || educational_background_descs.length == 0)) {
//            list = projectsMapper.findProjectBySegmentation(userId, types, segmentations, stages, sizea, froma);
//        } else {
//            list = projectsMapper.findProjectBySview(userId, types, segmentations, stages, cities, working_background_descs, educational_background_descs, sizea, froma);
//        }
        list = projectsMapper.findProjectBySview(userId, types, segmentations, stages, cities, working_background_descs, educational_background_descs, sizea, froma);
        return list;
    }

    /**
     * 搜索更多的缓存
     * @param userId
     * @param shortName
     * @param sizea
     * @param froma
     * @return
     */
    @Cacheable(value = "getSearchBaseProjectAll", keyGenerator = "wiselyKeyGenerator")
    public List<Map<String, Object>> getSearchBaseProjectAll(String userId, String shortName, Integer sizea, Integer froma) {
        List<Map<String, Object>> list = projectsMapper.findProjectByShortNameAll(shortName, userId, sizea, froma);
        return  list;
    }

    /**
     * 三条记录缓存
     * @param userId
     * @param shortName
     * @return
     */
    @Cacheable(value = "getSearchBaseProject", keyGenerator = "wiselyKeyGenerator")
    public List<Map<String, Object>> getSearchBaseProject(String userId, String shortName) {
        List<Map<String, Object>> list =projectsMapper.findProjectByShortName(userId,shortName);
        return  list;
    }

    /**
     * 查询我关注的项目
     * @param userId
     * @param beginNum
     * @param pageSize
     * @return
     */
   /* @Cacheable(value = "getSearchBaseMyProject", keyGenerator = "wiselyKeyGenerator")
    public List<Map<String, Object>> getSearchBaseMyProject(String userId,Integer beginNum,Integer pageSize) {
        List<Map<String, Object>> list =projectsMapper.findProjectByUserId(userId,beginNum,pageSize);
        return  list;
    }*/
    /**
     * 获取项目基础数据
     *
     * @param userId                      用户ID
     * @param type                        机构类型
     * @param segmentation                所属领域
     * @param stage                       轮次
     * @param city                        城市
     * @param working_background_desc     工作背景
     * @param educational_background_desc 教育背景
     * @param sizea                       起始页
     * @param froma                       每页记录数
     * @return
     */
   //@Cacheable(value = "getBaseProjectInfoA", keyGenerator = "wiselyKeyGenerator")
   @CacheEvict(value = "getBaseProjectInfoA", allEntries=true)
    public List<Map<String, Object>> getBaseProjectInfoA(String userId, String type, String segmentation,
                                                        String stage, String city,
                                                        String working_background_desc, String educational_background_desc,
                                                        Integer sizea, Integer froma) {
        
        String[] segmentations = null;
        String[] stages = null;
        String[] cities = null;
        String[] working_background_descs = null;
        String[] educational_background_descs = null;
        
        if (type != null && !"".equals(type)) {
                if (type.contains("行业指数机构")) {
                    type = "0,1";
                    String[] type2 = type.split(",");
                   int[] types = new int[type2.length];
                    for (int i = 0; i < type2.length; i++) {
                        types[i] = Integer.parseInt(type2[i]);
                    }
                }
                if ("50指数机构".equals(type)) {
                    type ="1";
                    String[] type2 = type.split(",");
                    int[] types = new int[type2.length];
                    for (int i = 0; i < type2.length; i++) {
                        types[i] = Integer.parseInt(type2[i]);
                    }
                }

        }
        String[] type2 = type.split(",");
        int[] types = new int[type2.length];
        if (type != null && !"".equals(type)) {
              for (int i = 0; i < type2.length; i++) {
                  types[i] = Integer.parseInt(type2[i]);
              }
        }
        if (segmentation != null && !"".equals(segmentation)) {
            segmentations = segmentation.split(",");
        }

        if (stage != null && !"".equals(stage)) {
            stages = stage.split(",");
        }

        if (city != null && !"".equals(city)) {
            cities = city.split(",");
        }

        if (working_background_desc != null && !"".equals(working_background_desc)) {
            working_background_descs = working_background_desc.split(",");
        }

        if (educational_background_desc != null && !"".equals(educational_background_desc)) {
            educational_background_descs = educational_background_desc.split(",");

        }

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        if ((cities == null || cities.length == 0) && (working_background_descs == null || working_background_descs.length == 0) && (educational_background_descs == null || educational_background_descs.length == 0)) {
//            list = projectsMapper.findProjectBySegmentation(userId, types, segmentations, stages, sizea, froma);
//        } else {
//            list = projectsMapper.findProjectBySview(userId, types, segmentations, stages, cities, working_background_descs, educational_background_descs, sizea, froma);
//        }
        list = projectsMapper.findProjectBySviewA(userId,types,segmentations, stages, cities, working_background_descs, educational_background_descs, sizea, froma);
        return list;
    }
}