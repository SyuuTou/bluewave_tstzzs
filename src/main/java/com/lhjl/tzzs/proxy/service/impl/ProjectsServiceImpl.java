package com.lhjl.tzzs.proxy.service.impl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.service.ProjectsService;

/**
 * 项目
 * @author lmy
 *
 */
@Service
public class ProjectsServiceImpl implements ProjectsService {

    @Resource
    private ProjectsMapper projectsMapper;
    @Resource
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;


    @Override
    public CommonDto<List<Projects>> findProjectByUserId(String userId) {
        CommonDto<List<Projects>> result = new CommonDto<List<Projects>>();
        List<Projects> list = projectsMapper.findProjectByUserId(userId);
        result.setData(list);
        result.setStatus(200);
        result.setMessage("ok");
        return result;
    }

    @Override
    public CommonDto<List<Map<String, Object>>> findProjectByShortName(String shortName,String userId) {
        CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
        List<Map<String, Object>> list = projectsMapper.findProjectByShortName(shortName,userId);
        List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
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

    @Override
    public CommonDto<Map<String,List<Map<String, Object>>>> findProjectByShortNameAll(String shortName,String userId) {
        CommonDto<Map<String,List<Map<String, Object>>>> result = new CommonDto<Map<String,List<Map<String, Object>>>>();
        Map<String,List<Map<String, Object>>> dataMap = new HashMap<String,List<Map<String, Object>>>();
        List<Map<String, Object>> list = projectsMapper.findProjectByShortNameAll(shortName,userId);
        List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
        for(Map<String, Object> map : list){
            if(map.get("yn") == null){
                map.put("yn", 0);
            }
            list2.add(map);
        }
        List<Map<String, Object>> list3 = investmentInstitutionsMapper.findByInvestmentShortNameAll(shortName,userId);
        dataMap.put("projectskey", list2);
        dataMap.put("investmentkey", list3);
        result.setData(dataMap);
        result.setStatus(200);
        result.setMessage("ok");
        return result;
    }

    @Override
    public CommonDto<List<Map<String, Object>>> findProjectBySview(Integer type, String segmentation,
                                                                   String stage, String city,String userId, String working_background_desc, String educational_background_desc) {
        CommonDto<List<Map<String,Object>>> result =new CommonDto<List<Map<String, Object>>>();
        List<Map<String, Object>> list =projectsMapper.findProjectBySview(type, segmentation, stage, city, userId,working_background_desc, educational_background_desc);
        List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
        for(Map<String, Object> map :list){
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



}