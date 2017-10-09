package com.lhjl.tzzs.proxy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    @Autowired
    private Environment environment;


    @Override
    public CommonDto<List<Map<String, Object>>> findProjectByUserId(String userId, Integer pageNum, Integer pageSize) {
        CommonDto<List<Map<String, Object>>> result = new CommonDto<List<Map<String, Object>>>();
        //初始化分页信息
        if(pageNum == null){
            pageNum = Integer.parseInt(environment.getProperty("pageNum"));
        }
        if(pageSize == null){
            pageSize = Integer.parseInt(environment.getProperty("pageSize"));
        }

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
    public CommonDto<Map<String,List<Map<String, Object>>>> findProjectByShortNameAll(String shortName,String userId,String size,String from) {
        CommonDto<Map<String,List<Map<String, Object>>>> result = new CommonDto<Map<String,List<Map<String, Object>>>>();
        int sizeb = Integer.parseInt(size);
        int froma =Integer.parseInt(from);
        int sizea=(sizeb-1)*froma;
        if(sizeb*froma >= 100){
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（100条）");
            return result;
        }
        Map<String,List<Map<String, Object>>> dataMap = new HashMap<String,List<Map<String, Object>>>();
        List<Map<String, Object>> list = projectsMapper.findProjectByShortNameAll(shortName,userId,sizea,froma);
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
        String size ="0";
        String from ="10";
        if(sereachDto.getSize() != "undefined"){
            size=sereachDto.getSize();

        }
        if(sereachDto.getFrom() !="undefined"){
            from=sereachDto.getFrom();
        }
        int sizeb =Integer.parseInt(size);
        int froma =Integer.parseInt(from);
        int sizea=(sizeb-1)*froma;
        if(sizeb*froma >= 100){
            result.setStatus(201);
            result.setMessage("查询记录数超出限制（100条）");
            return result;
        }
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
        List<Map<String, Object>> list =projectsMapper.findProjectBySview(userId,types,segmentations,stages, cities, working_background_descs,educational_background_descs,sizea,froma);
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
        return result;
    }


}