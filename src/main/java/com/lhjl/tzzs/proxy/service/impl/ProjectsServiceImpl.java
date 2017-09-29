package com.lhjl.tzzs.proxy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lhjl.tzzs.proxy.dto.CommonDto;

import com.lhjl.tzzs.proxy.dto.SereachDto;
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
    public CommonDto<List<Map<String, Object>>> findProjectByUserId(String userId) {
        CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
        List<Map<String, Object>> list = projectsMapper.findProjectByUserId(userId);
        result.setData(list);
        result.setStatus(200);
        result.setMessage("success");
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
	public CommonDto<List<Map<String, Object>>> findProjectBySview(SereachDto sereachDto) {
		CommonDto<List<Map<String,Object>>> result =new CommonDto<List<Map<String, Object>>>();
        String userId =sereachDto.getUserId();
        String  type = sereachDto.getInvestment_institutions_type();
        int[] types = {};
        String [] segmentations ={};
        String [] stages={};
        String [] cities={};
        String [] working_background_descs ={};
        String [] educational_background_descs ={};

        if(type != null && !"".equals(type)) {
            String[] type2 = type.split(",");
            types = new int[type2.length];
            for (int i=0; i<type2.length; i++){
                types[i] = Integer.parseInt(type2[i]);
            }
        }

	      String segmentation = sereachDto.getSegmentation();
        if(segmentation !=null && !"".equals(segmentation)){
            segmentations=segmentation.split(",");
        }

        String stage =sereachDto.getStage();
        if(stage != null && !"".equals(stage)){
            stages =stage.split(",");
        }

        String  city  =sereachDto.getCity();
        if(city != null && !"".equals(city)){
            cities =city.split(",");
        }
        String  working_background_desc =sereachDto.getWorking_background_desc();
        if(working_background_desc !=null && !"".equals(working_background_desc)){
            working_background_descs=working_background_desc.split(",");
        }

        String   educational_background_desc=sereachDto.getEducational_background_desc();
        if(educational_background_desc !=null && !"".equals(educational_background_desc)){
            educational_background_descs=educational_background_desc.split(",");

        }
//	    List citiess = Arrays.asList(cities);
//	        String[] stages = sereachDto.getStage().split(",");
	        List<Map<String, Object>> list =projectsMapper.findProjectBySview(userId,types,segmentations,stages, cities, working_background_descs,educational_background_descs);
	        List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
	        for(Map<String, Object> map :list){
	            if(map.get("yn") == null){
	                map.put("yn", 0);
	            }
	            list2.add(map);
	            }
	            result.setData(list2);
	      return result;
	   }


}