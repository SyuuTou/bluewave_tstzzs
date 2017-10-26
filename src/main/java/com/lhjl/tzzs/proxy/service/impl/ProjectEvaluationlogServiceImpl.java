package com.lhjl.tzzs.proxy.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhjl.tzzs.proxy.dto.AssessmentDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.mapper.ProjectEvaluationLogMapper;
import com.lhjl.tzzs.proxy.mapper.UserTokenMapper;
import com.lhjl.tzzs.proxy.model.ProjectEvaluationLog;
import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import com.lhjl.tzzs.proxy.service.ProjectEvaluationlogService;
@Service
public class ProjectEvaluationlogServiceImpl implements ProjectEvaluationlogService {
	
    @Resource
    private ProjectEvaluationLogMapper projectEvaluationLogMapper;
    @Resource
    private UserTokenMapper  userTokenMapper;
    @Resource
    private EvaluateService evaluateService; 
    
	
	/**
	 * 保存评估数据
	 */
    @Transactional
	@Override
	public CommonDto<Map<String,Object>> saveAssessment(AssessmentDto params) {
		     CommonDto<Map<String,Object>> result =new CommonDto<Map<String,Object>> ();
		     Map<String,Object> map =new HashMap<String,Object>();
			 ProjectEvaluationLog  projectEvaluationlog= new  ProjectEvaluationLog ();
			 UserToken userToken =new UserToken();
			 userToken.setToken(params.getToken());
		     userToken =userTokenMapper.selectOne(userToken);
		     projectEvaluationlog.setUserId(userToken.getUserId());//用户id
		     projectEvaluationlog.setCity(params.getPinggulishibiao7city());
		     projectEvaluationlog.setCompanyName(params.getPinggulishibiao7corporaten());
		     projectEvaluationlog.setDomain(params.getPinggulishibiao7domainowne());//领域
		     projectEvaluationlog.setEducation(params.getPinggulishibiao7university());
		     projectEvaluationlog.setFinancingStage(params.getPinggulishibiao7financingp());
		     projectEvaluationlog.setWork(params.getPinggulishibiao7workbackgr());
		     projectEvaluationlog.setCreatTime(new Date());
		     projectEvaluationLogMapper.insert(projectEvaluationlog);
		     map.put("success",true);
		     result.setData(map);
			 return result;
		}
   /**
    * 评估页面初次进入接口
    * 评估页面再次进入回显接口
    */
	@Override
	public CommonDto<Map<String, Object>> findInvestorsApproval(String token) {
		CommonDto<Map<String, Object>> result =new CommonDto<Map<String, Object>> ();
		Map<String,Object> map =new HashMap<>();
		UserToken userToken =new UserToken();
		 userToken.setToken(token);
		 userToken = userTokenMapper.selectOne(userToken);
		 Integer userId = userToken.getUserId();
		 CommonDto<Map<String,List<LabelList>>> data = evaluateService.queryHotData();
		 map=projectEvaluationLogMapper.findInvestorsApproval(userId);
		 if(map !=null){
			 Map<String,Object> remenxinxi =new HashMap<>();
			 map.put("jieduan",map.get("financing_stage"));
			 map.put("lingyu",map.get("domain"));
			 remenxinxi.put("jiaoyubeijing",map.get("education"));
			 remenxinxi.put("gongzuobeijing",map.get("work"));
			 map.put("pinggulishibiao7corporaten",map.get("company_name"));
			 if("天使轮".equals(map.get("financing_stage"))){
			 List<LabelList> list4 = new ArrayList<>();
			 LabelList labelList=new LabelList();
			 LabelList labelList1=new LabelList();
			 labelList.setName("天使轮");
			 labelList.setValue("天使轮");
			 labelList.setChecked(true);
			 labelList1.setName("Pre-A轮");
			 labelList1.setValue("Pre-A轮");
			 labelList1.setChecked(false);
			 list4.add(labelList);
			 list4.add(labelList1);
			 map.put("pinggulishibiao7financingp",list4);//轮次简称
			 }else{
				 List<LabelList> list4 = new ArrayList<>();
				 LabelList labelList=new LabelList();
				 LabelList labelList1=new LabelList();
				 labelList.setName("天使轮");
				 labelList.setValue("天使轮");
				 labelList.setChecked(false);
				 labelList1.setName("Pre-A轮");
				 labelList1.setValue("Pre-A轮");
				 labelList1.setChecked(true);
				 list4.add(labelList);
				 list4.add(labelList1);
				 map.put("pinggulishibiao7financingp",list4);//轮次简称 
			 }
			 //城市
		     if("".equals(map.get("city"))){
				 List<LabelList> cities = data.getData().get("cityKey");
		    	 LabelList list =new LabelList();
		    	 List<LabelList> lista =new ArrayList();
				 list.setName("不限");
				 list.setValue("不限");
				 list.setChecked(true);
				 lista.add(list);
				 lista.addAll(cities);
				 remenxinxi.put("cityKey",lista);//城市
				 remenxinxi.put("chengshi","");//城市
		   }else{
				 List<LabelList> cities = data.getData().get("cityKey");
			     LabelList list =new LabelList();
			     List<LabelList> lista =new ArrayList();
				 list.setName("不限");
				 list.setValue("不限");
				 list.setChecked(false);
		         String cityStr =String.valueOf(map.get("city"));
			     for(LabelList labellist : cities){
			     if(cityStr.equals(labellist.getName())){
			     labellist.setChecked(true);
			              }
			          }
			     lista.add(list);
				 lista.addAll(cities);
				 remenxinxi.put("cityKey",lista);//城市
				 remenxinxi.put("chengshi","");//城市
		        }
		     //领域
		     if("".equals(map.get("domain"))){
				 List<LabelList> industryKeies = data.getData().get("industryKey");
		    	 LabelList list =new LabelList();
		    	 List<LabelList>  listb =new ArrayList();
				 list.setName("不限");
				 list.setValue("不限");
				 list.setChecked(true);
				 listb.add(list);
				 listb.addAll(industryKeies);
				 remenxinxi.put("industryKey",listb);//
				 map.put("industryKey",listb);
		   }else{
			     List<LabelList> industryKeies = data.getData().get("industryKey");
		    	 LabelList list =new LabelList();
		    	 List<LabelList>  listb =new ArrayList();
				 list.setName("不限");
				 list.setValue("不限");
				 list.setChecked(false);
		         String i =String.valueOf(map.get("domain"));
			     for(LabelList labellist : industryKeies){
			     if(i.equals(labellist.getName())){
			     labellist.setChecked(true);
			              }
			          }
			     listb.add(list);
				 listb.addAll(industryKeies);
				 remenxinxi.put("industryKey",listb);//
				 map.put("industryKey",listb);
		        }
		     //教育背景
		     if("".equals(map.get("education"))){
				 List<LabelList> educationKeies = data.getData().get("educationKey");
		    	 LabelList list =new LabelList();
		    	 List<LabelList>  listc =new ArrayList();
				 list.setName("不限");
				 list.setValue("不限");
				 list.setChecked(true);
				 listc.add(list);
				 listc.addAll(educationKeies);
				 remenxinxi.put("educationKey",listc);//
				 remenxinxi.put("gongzuobeijing","");//
		   }else{
				 List<LabelList> educationKeies = data.getData().get("educationKey");
		    	 LabelList list =new LabelList();
		    	 List<LabelList>  listc =new ArrayList();
				 list.setName("不限");
				 list.setValue("不限");
				 list.setChecked(false);
		         String eStr =String.valueOf(map.get("education"));
			     for(LabelList labellist : educationKeies){
			     if(eStr.equals(labellist.getName())){
			     labellist.setChecked(true);
			              }
			          }
			     listc.add(list);
				 listc.addAll(educationKeies);
				 remenxinxi.put("educationKey",listc);//
				 remenxinxi.put("gongzuobeijing","");
		        }
		     //工作背景
		     if("".equals(map.get("work"))){
				 List<LabelList> workKeies = data.getData().get("workKey");
		    	 LabelList list =new LabelList();
		    	 List<LabelList>  listd =new ArrayList();
				 list.setName("不限");
				 list.setValue("不限");
				 list.setChecked(true);
				 listd.add(list);
				 listd.addAll(workKeies);
				 remenxinxi.put("workKey",listd);
		   }else{
			   List<LabelList> workKeies = data.getData().get("workKey");
		    	 LabelList list =new LabelList();
		    	 List<LabelList>  listd =new ArrayList();
				 list.setName("不限");
				 list.setValue("不限");
				 list.setChecked(false);
		         String wStr =String.valueOf(map.get("work"));
			     for(LabelList labellist : workKeies){
			     if(wStr.equals(labellist.getName())){
			     labellist.setChecked(true);
			              }
			          }
			     listd.add(list);
				 listd.addAll(workKeies);
				 remenxinxi.put("workKey",listd);
		        }
			     map.put("remenxinxi",remenxinxi);
		 }else{
			 map=new HashMap<>();
			 map.put("jieduan","天使轮");
			 map.put("lingyu","");
			 Map<String,Object> remenxinxi =new HashMap<>();
			 
			 List<LabelList> cities = data.getData().get("cityKey");
			 LabelList list =new LabelList();
			 List<LabelList>  lista =new ArrayList();
			 list.setName("不限");
			 list.setValue("不限");
			 list.setChecked(true);
			 lista.add(list);
			 lista.addAll(cities);
			 remenxinxi.put("cityKey",lista);//城市
			 remenxinxi.put("chengshi","");//城市
			 
			 List<LabelList> industryKeies = data.getData().get("industryKey");
			 LabelList list1 =new LabelList();
			 List<LabelList>  listb =new ArrayList();
			 list1.setName("不限");
			 list1.setValue("不限");
			 list1.setChecked(true);
			 listb.add(list1);
			 listb.addAll(industryKeies);
			 remenxinxi.put("industryKey",listb);//
			 map.put("industryKey",listb);
			 remenxinxi.put("jiaoyubeijing","");//
			 
			 List<LabelList> educationKeies = data.getData().get("educationKey");
			 LabelList list2 =new LabelList();
			 List<LabelList>  listc =new ArrayList();
			 list2.setName("不限");
			 list2.setValue("不限");
			 list2.setChecked(true);
			// educationKeies.add(list2);
			 listc.add(list2);
			 listc.addAll(educationKeies);
			 remenxinxi.put("educationKey",listc);//
			 remenxinxi.put("gongzuobeijing","");//
			 
			 List<LabelList> workKeies = data.getData().get("workKey");
			 LabelList list3 =new LabelList();
			 List<LabelList>  listd =new ArrayList();
			 list3.setName("不限");
			 list3.setValue("不限");
			 list3.setChecked(true);
			// workKeies.add(list3);
			 listd.add(list3);
			 listd.addAll(workKeies);
			 remenxinxi.put("workKey",listd);//
			 map.put("remenxinxi",remenxinxi);
			 map.put("pinggulishibiao7corporaten","");//公司简称
			 List<LabelList> list4 = new ArrayList<>();
			 LabelList labelList=new LabelList();
			 LabelList labelList1=new LabelList();
			 labelList.setName("天使轮");
			 labelList.setValue("天使轮");
			 labelList.setChecked(true);
			 labelList1.setName("Pre-A轮");
			 labelList1.setValue("Pre-A轮");
			 labelList1.setChecked(false);
			 list4.add(labelList);
			 list4.add(labelList1);
			 map.put("pinggulishibiao7financingp",list4);//轮次简称
		 }

		 result.setData(map);
		return result;
	}
	/**
	 *公司名称历史记录
	 */
@Override
public CommonDto<List<Map<String, Object>>>findEvaluationLog(String token) {
	CommonDto<List<Map<String, Object>>> result =new CommonDto<List<Map<String, Object>>>();
	 UserToken userToken =new UserToken();
	 userToken.setToken(token);
	 userToken = userTokenMapper.selectOne(userToken);
	 Integer userId = userToken.getUserId();
	 List<Map<String, Object>> list = projectEvaluationLogMapper.findEvaluationLog(userId);
	 if(list.size()>0){
	 for(Map<String,Object> map :list){
		 map.put("pinggulishibiao7corporaten",String.valueOf(map.get("company_name")));
		 map.put("pinggulishibiao7assessment",String.valueOf(map.get("creat_time")));
		 map.put("_id",token);
	 }
	 }else{
		 result.setStatus(202);
		 result.setMessage("无查询数据");
	 }
	 result.setData(list);
	 return result;
}
}