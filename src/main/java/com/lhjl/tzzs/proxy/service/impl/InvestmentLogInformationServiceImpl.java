package com.lhjl.tzzs.proxy.service.impl;

import com.github.pagehelper.Page;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentLogInformationDto;
import com.lhjl.tzzs.proxy.dto.LabelList;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.EvaluateService;
import com.lhjl.tzzs.proxy.service.InvestmentLogInformationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lmy on 2017/11/23.
 */
@Service
public class InvestmentLogInformationServiceImpl  implements InvestmentLogInformationService {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvestmentDataImpl.class);

     @Autowired
	 private InvestmentInstitutionInformationMapper investmentInstitutionInformationMapper;

     @Autowired
	 private UserTokenMapper userTokenMapper;

     @Autowired
	 private EvaluateService evaluateService;

	//投资机构表
	@Resource
	private InvestmentInstitutionsMapper investmentInstitutionsMapper;

	//创始人表
	@Resource
	private FoundersMapper foundersMapper;

	//投融资项目表
	@Resource
	private ProjectsMapper projectsMapper;

	//项目融资记录表
	@Resource
	private ProjectFinancingLogMapper projectFinancingLogMapper;

	//投资人教育经历
	@Resource
	private FoundersEducationMapper foundersEducationMapper;

	//投资人工作经历
	@Resource
	private FoundersWorkMapper foundersWorkMapper;

	//项目细分领域表
	@Resource
	private ProjectSegmentationMapper projectSegmentationMapper;

	//投资人表
	@Resource
	private InvestorsMapper investorsMapper;

	//用户表
	@Resource
	private UsersMapper usersMapper;
	//记录表
	@Resource
	private  InvestmentDataLogMapper investmentDataLogMapper;

	@Resource
	private DataLogDomainMapper dataLogDomainMapper;

	@Resource
	private DataLogEducationMapper  dataLogEducationMapper;

	@Resource
	private  DataLogWorkMapper dataLogWorkMapper;

	/**
	 * 保存与修改机构信息的列表
	 * @param params
	 * @return
	 */
	@Transactional
	@Override
	public CommonDto<String> saveInvestmentLogInformation(InvestmentLogInformationDto params) {
		CommonDto<String> result = new CommonDto<String>();
		     String city = params.getCity();
		     String domanin = params.getDomanin(); //领域
		     String fullName = params.getFullName(); //公司全称
		     String investmentIdea = params.getInvestementIdea(); //投资理念
		     String jianjie = params.getJianjie(); //简介
		     Integer logId = params.getLogId(); //数据的logid
		     String logo = params.getLogo(); //公司logo
		     BigDecimal meiAmount = params.getMeiAmount(); //美元规模
		     BigDecimal meiAmountMax = params.getMeiAmountMax(); //美元最多
		     BigDecimal meiAmountMin = params.getMeiAmountMin(); //美元最少
		     BigDecimal renAmount = params.getRenAmount(); //人民币规模
		     String oneIntroduction = params.getOneIntroduction(); //一句话介绍
		     BigDecimal renAmountMax = params.getRenAmountMax(); //人民币的最多
		     BigDecimal renAmountMin = params.getRenAmountMin(); //人民币最少
		     String representative = params.getRepresentative(); //投资人
		     String shortName = params.getShortName();  //机构简称
		     String stage = params.getStage(); // 投资轮次
		     String token = params.getToken(); //用户的token
		     String website = params.getWebsite(); //官网
		     Integer jigouId =params.getJigouId(); //记录id
			//参数验证
			if (StringUtil.isEmpty(shortName)){
				result.setStatus(50001);
				result.setMessage("请填写投资项目简称");
				result.setData(null);

				return result;
			}

			if (StringUtil.isEmpty(token)){
				result.setStatus(50001);
				result.setMessage("缺少用户id");
			}

			UserToken userToken = new UserToken();
			userToken.setToken(token);
			userToken = userTokenMapper.selectOne(userToken);
			if(userToken !=null){
			InvestmentInstitutionInformation investmentInstitutionInformation = new InvestmentInstitutionInformation();
			investmentInstitutionInformation.setUserId(userToken.getUserId());
			if(null ==  logId || "".equals(logId)) {
				investmentInstitutionInformation.setLogId(-1);
			}else{
				investmentInstitutionInformation.setLogId(logId);
			}
			investmentInstitutionInformation = investmentInstitutionInformationMapper.selectOne(investmentInstitutionInformation);
			if(investmentInstitutionInformation !=null){
				//更改信息列表保存
			investmentInstitutionInformation.setCity(city);
			investmentInstitutionInformation.setDomain(domanin);
			investmentInstitutionInformation.setFullName(fullName);
			investmentInstitutionInformation.setInvestmentIdea(investmentIdea);
			investmentInstitutionInformation.setLogo(logo);
			investmentInstitutionInformation.setMeiAmount(meiAmount);
			investmentInstitutionInformation.setMeiAmountMax(meiAmountMax);
			investmentInstitutionInformation.setMeiAmountMix(meiAmountMin);
			investmentInstitutionInformation.setOneintroduction(oneIntroduction);
			investmentInstitutionInformation.setRenAmount(renAmount);
			investmentInstitutionInformation.setRenAmountMax(renAmountMax);
			investmentInstitutionInformation.setRenAmountMix(renAmountMin);
			investmentInstitutionInformation.setRepresentative(representative);
			investmentInstitutionInformation.setShortName(shortName);
			investmentInstitutionInformation.setStage(stage);
			investmentInstitutionInformation.setSummary(jianjie);
			investmentInstitutionInformation.setWebsite(website);
			investmentInstitutionInformationMapper.updateByPrimaryKey(investmentInstitutionInformation);
			}else {
				   InvestmentInstitutionInformation investmentInstitutionInformation1 = new InvestmentInstitutionInformation();
					investmentInstitutionInformation1.setUserId(userToken.getUserId());
					investmentInstitutionInformation1.setCity(city);
					investmentInstitutionInformation1.setDomain(domanin);
					investmentInstitutionInformation1.setFullName(fullName);
					investmentInstitutionInformation1.setInvestmentIdea(investmentIdea);
					investmentInstitutionInformation1.setLogId(-1);
					investmentInstitutionInformation1.setLogo(logo);
					investmentInstitutionInformation1.setMeiAmount(meiAmount);
					investmentInstitutionInformation1.setMeiAmountMax(meiAmountMax);
					investmentInstitutionInformation1.setMeiAmountMix(meiAmountMin);
					investmentInstitutionInformation1.setOneintroduction(oneIntroduction);
					investmentInstitutionInformation1.setRenAmount(renAmount);
					investmentInstitutionInformation1.setRenAmountMax(renAmountMax);
					investmentInstitutionInformation1.setRenAmountMix(renAmountMin);
					investmentInstitutionInformation1.setRepresentative(representative);
					investmentInstitutionInformation1.setShortName(shortName);
					investmentInstitutionInformation1.setStage(stage);
					investmentInstitutionInformation1.setSummary(jianjie);
					investmentInstitutionInformation1.setWebsite(website);
					investmentInstitutionInformationMapper.insert(investmentInstitutionInformation1);

			}
			}else{
				result.setStatus(5101);
				result.setMessage("用户token不存在");

			}

			result.setMessage("success");
			result.setStatus(200);
			result.setData(shortName);

		return result;
	}

	/**
	 * 机构信息数据的回显
	 * @param body
	 * @return
	 */
	@Override
	public CommonDto<Map<String, Object>> findinvestmentlog(InvestmentLogInformationDto body) {
		CommonDto<Map<String, List<LabelList>>> hotsdatas = evaluateService.queryHotData();
		CommonDto<Map<String, Object>>  result = new CommonDto<>();
		Map<String, Object> data = new HashMap<>();
		Integer logId =body.getLogId();
		String token =body.getToken();

		UserToken userToken = new UserToken();
		userToken.setToken(token);
		userToken = userTokenMapper.selectOne(userToken);
		if(userToken !=null) {
			InvestmentInstitutionInformation investmentInstitutionInformation =new InvestmentInstitutionInformation();
			investmentInstitutionInformation.setUserId(userToken.getUserId());
			if(null ==  logId || "".equals(logId)) {
				investmentInstitutionInformation.setLogId(-1);
			}else{
				investmentInstitutionInformation.setLogId(logId);
			}
			investmentInstitutionInformation = investmentInstitutionInformationMapper.selectOne(investmentInstitutionInformation);
			if(investmentInstitutionInformation !=null) {
				//投资阶段
				List<LabelList> list3 = new LinkedList<>();
				LabelList labelList3 = new LabelList();
				labelList3.setName("种子轮");
				labelList3.setValue("种子");
				labelList3.setChecked(false);
				LabelList labelList4 = new LabelList();
				labelList4.setName("天使轮");
				labelList4.setValue("天使轮");
				labelList4.setChecked(false);
				LabelList labelList5 = new LabelList();
				labelList5.setName("Pre-A轮");
				labelList5.setValue("Pre-A轮");
				labelList5.setChecked(false);
				LabelList labelList6 = new LabelList();
				labelList6.setName("A轮");
				labelList6.setValue("A轮");
				labelList6.setChecked(false);
				LabelList labelList7 = new LabelList();
				labelList7.setName("B轮");
				labelList7.setValue("B轮");
				labelList7.setChecked(false);
				LabelList labelList8 = new LabelList();
				labelList8.setName("C轮");
				labelList8.setValue("C轮");
				labelList8.setChecked(false);
				LabelList labelList9 = new LabelList();
				labelList9.setName("Pre-IPO");
				labelList9.setValue("Pre-IPO");
				labelList9.setChecked(false);
				LabelList labelList10 = new LabelList();
				labelList10.setName("战略投资");
				labelList10.setValue("战略投资");
				labelList10.setChecked(false);
				LabelList labelList11 = new LabelList();
				labelList11.setName("并购");
				labelList11.setValue("并购");
				labelList11.setChecked(false);
				list3.add(labelList3);
				list3.add(labelList4);
				list3.add(labelList5);
				list3.add(labelList6);
				list3.add(labelList7);
				list3.add(labelList8);
				list3.add(labelList9);
				list3.add(labelList10);
				list3.add(labelList11);
				for (LabelList label :list3) {
					String[] industryArray = investmentInstitutionInformation.getStage().split(",");
					for (String string : industryArray) {
						if (string.equals(label.getName())){
							label.setChecked(true);
						}
					}
				}
				data.put("stage", list3);
				data.put("stageName", investmentInstitutionInformation.getStage());
				//公司的logo
				data.put("logo",investmentInstitutionInformation.getLogo());
				data.put("city",investmentInstitutionInformation.getCity());
				data.put("fullName",investmentInstitutionInformation.getFullName());
				data.put("website",investmentInstitutionInformation.getWebsite());
				data.put("investmentIdea",investmentInstitutionInformation.getInvestmentIdea());
				data.put("meiAmount",investmentInstitutionInformation.getMeiAmount());
				data.put("renAmountMax",investmentInstitutionInformation.getRenAmountMax());
				data.put("renAmount",investmentInstitutionInformation.getRenAmount());
				data.put("renAmountMin",investmentInstitutionInformation.getRenAmountMix());
				data.put("representative",investmentInstitutionInformation.getRepresentative());
				data.put("meiAmountMax",investmentInstitutionInformation.getMeiAmountMax());
				data.put("meiAmountMin",investmentInstitutionInformation.getMeiAmountMix());
				data.put("oneintroduction",investmentInstitutionInformation.getOneintroduction());
				data.put("jianjie",investmentInstitutionInformation.getSummary());
				data.put("shortName",investmentInstitutionInformation.getShortName());
				//地域
				List<LabelList> cities = hotsdatas.getData().get("cityKey");
				String cityStr =investmentInstitutionInformation.getCity();
				for(LabelList labellist : cities){
					if(cityStr.equals(labellist.getName())){
						labellist.setChecked(true);
					}
				}
				data.put("cityKey",cities);//城市
                //行业领域
				List<LabelList> industrys = hotsdatas.getData().get("industryKey");
				for(LabelList label :industrys){
					if(investmentInstitutionInformation != null && investmentInstitutionInformation.getDomain() != null && !"".equals(investmentInstitutionInformation.getDomain())){
						String[] industryArray = investmentInstitutionInformation.getDomain().split(",");
						for(String string : industryArray){
							if(string.equals(label.getName())){
								label.setChecked(true);
							}
						}
					}
				}
				data.put("domain", industrys);
				data.put("domainName",investmentInstitutionInformation.getDomain());
			}else{
				List<LabelList> list3 = new LinkedList<>();
				LabelList labelList3 = new LabelList();
				labelList3.setName("种子轮");
				labelList3.setValue("种子");
				labelList3.setChecked(false);
				LabelList labelList4 = new LabelList();
				labelList4.setName("天使轮");
				labelList4.setValue("天使轮");
				labelList4.setChecked(false);
				LabelList labelList5 = new LabelList();
				labelList5.setName("Pre-A轮");
				labelList5.setValue("Pre-A轮");
				labelList5.setChecked(false);
				LabelList labelList6 = new LabelList();
				labelList6.setName("A轮");
				labelList6.setValue("A轮");
				labelList6.setChecked(false);
				LabelList labelList7 = new LabelList();
				labelList7.setName("B轮");
				labelList7.setValue("B轮");
				labelList7.setChecked(false);
				LabelList labelList8 = new LabelList();
				labelList8.setName("C轮");
				labelList8.setValue("C轮");
				labelList8.setChecked(false);
				LabelList labelList9 = new LabelList();
				labelList9.setName("Pre-IPO");
				labelList9.setValue("Pre-IPO");
				labelList9.setChecked(false);
				LabelList labelList10 = new LabelList();
				labelList10.setName("战略投资");
				labelList10.setValue("战略投资");
				labelList10.setChecked(false);
				LabelList labelList11 = new LabelList();
				labelList11.setName("并购");
				labelList11.setValue("并购");
				labelList11.setChecked(false);
				list3.add(labelList3);
				list3.add(labelList4);
				list3.add(labelList5);
				list3.add(labelList6);
				list3.add(labelList7);
				list3.add(labelList8);
				list3.add(labelList9);
				list3.add(labelList10);
				list3.add(labelList11);
				data.put("stage",list3);
				data.put("stageName", "");
				//公司的logo
				data.put("logo","");
				data.put("city","");
				data.put("fullName","");
				data.put("website","");
				data.put("investmentIdea","");
				data.put("meiAmount","");
				data.put("renAmountMax","");
				data.put("renAmount","");
				data.put("renAmountMin","");
				data.put("meiAmountMax","");
				data.put("meiAmountMin","");
				data.put("oneintroduction","");
				data.put("jianjie","");
				data.put("shortName","");
				data.put("representative","");
				//地域
				List<LabelList> cities = hotsdatas.getData().get("cityKey");
				data.put("cityKey",cities);//城市
				//行业领域
				List<LabelList> industrys = hotsdatas.getData().get("industryKey");
				data.put("domain", industrys);
				data.put("domainName","");

			}
		}else{
			result.setStatus(5101);
			result.setMessage("用户token不存在");
		}
		result.setData(data);
		return  result;
	}



	/**
	 * 首次添加数据信息
	 */

	@Transactional
	@Override
	public CommonDto<String> saveInvestmentDatanew(String project_name, String project_full_name, String summary, String field,  String city, String rounds, String amount,  String currency,  String stock_right,  Date dateDate,  String founder_name, String founder_work, String founder_education,String userId,String zhiwu){
		CommonDto<String> result = new CommonDto<String>();


		//参数验证
		if (StringUtil.isEmpty(project_name)){
			result.setStatus(50001);
			result.setMessage("请填写投资项目简称");
			result.setData(null);

			return result;
		}
//        if (StringUtil.isEmpty(project_full_name)){
//            result.setStatus(50001);
//            result.setMessage("请填写投资项目的工商注册全称");
//            result.setData(null);
//
//            return result;
//        }
//        if (StringUtil.isEmpty(summary)){
//            result.setStatus(50001);
//            result.setMessage("请填写一句话介绍");
//            result.setData(null);
//
//            return result;
//        }else if (summary.length()>50){
//            result.setStatus(50003);
//            result.setMessage("一句话介绍长度不能超过50个字符");
//            result.setData(null);
//
//            return result;
//        }
		if (StringUtil.isEmpty(field)){
			result.setStatus(50001);
			result.setMessage("请填写所属细分领域");
			result.setData(null);

			return result;
		}
		if (StringUtil.isEmpty(city)){
			result.setStatus(50001);
			result.setMessage("请填写地域");
			result.setData(null);

			return result;
		}
		if (StringUtil.isEmpty(rounds)){
			result.setStatus(50001);
			result.setMessage("请填写投资轮次");
			result.setData(null);

			return result;
		}
		if (StringUtil.isEmpty(amount)){
			result.setStatus(50001);
			result.setMessage("请填写投资金额");
			result.setData(null);

			return result;
		}
		if (StringUtil.isEmpty(currency)){
			result.setStatus(50001);
			result.setMessage("请填写投资币种");
			result.setData(null);

			return result;
		}
//        if (StringUtil.isEmpty(stock_right)){
//            result.setStatus(50001);
//            result.setMessage("请填写股份占比");
//            result.setData(null);
//
//            return result;
//        }
		if (dateDate  == null){
			result.setStatus(50001);
			result.setMessage("请填写投资时间");
			result.setData(null);

			return result;
		}
		if (StringUtil.isEmpty(founder_name)){
			result.setStatus(50001);
			result.setMessage("请填写创始人姓名");
			result.setData(null);

			return result;
		}
//        if (StringUtil.isEmpty(founder_work)){
//            result.setStatus(50001);
//            result.setMessage("请填写创始人工作背景");
//            result.setData(null);
//
//            return result;
//        }
//        if (StringUtil.isEmpty(founder_education)){
//            result.setStatus(50001);
//            result.setMessage("请填写创始人毕业院校");
//            result.setData(null);
//
//            return result;
//        }
		if (StringUtil.isEmpty(userId)){
			result.setStatus(50001);
			result.setMessage("缺少用户id");
		}
		//插入主表信息
		Integer userID= usersMapper.findByUuid(userId);
		InvestmentDataLog i2 =new InvestmentDataLog();
		i2.setUserId(userID);
		i2.setShortName(project_name);
		i2.setStage(rounds);
		InvestmentDataLog one = investmentDataLogMapper.selectOne(i2);
		if(one==null){
			int currency1 =Integer.parseInt(currency);
			InvestmentDataLog i =new InvestmentDataLog();
			i.setYn(currency1);
			i.setCity(city);
			BigDecimal bigDecimalAmount = new BigDecimal(amount);
			if(stock_right ==null || "".equals(stock_right)) {

			}else{
				BigDecimal bigDecimalStockRight = new BigDecimal(stock_right);
				i.setStockRight(bigDecimalStockRight);
			}
			i.setAmont(bigDecimalAmount);
			i.setCompanyName(project_full_name);
			i.setCreateName(founder_name);
			i.setCreateTime(new Date());
			i.setFinanTime(dateDate);
			i.setShortName(project_name);
			i.setStage(rounds);
			i.setUserId(userID);
			i.setWordIntroduction(summary);
			i.setYn(currency1);
			i.setZhiwu(zhiwu);
			//
			i.setAuditYn(0);
			investmentDataLogMapper.insert(i);

			// 变更关联表的id
			InvestmentInstitutionInformation investmentInstitutionInformation =new InvestmentInstitutionInformation();
			investmentInstitutionInformation.setLogId(-1);
			investmentInstitutionInformation.setUserId(userID);
			investmentInstitutionInformation=investmentInstitutionInformationMapper.selectOne(investmentInstitutionInformation);
			if(investmentInstitutionInformation !=null) {
				investmentInstitutionInformation.setLogId(i.getId());
				investmentInstitutionInformationMapper.updateByPrimaryKey(investmentInstitutionInformation);
			}
			//插入细分领域
			String[] fieldArry = field.split(",");
			List<DataLogDomain> projectSegmentationList =new Page<DataLogDomain>();
			for (int a=0; a < fieldArry.length;a++){
				DataLogDomain projectSegmentation =new DataLogDomain();
				projectSegmentation.setCreateTime(new Date());
				projectSegmentation.setDomainName(fieldArry[a]);
				projectSegmentation.setLogId(i.getId());
				projectSegmentation.setUserId(userID);
				projectSegmentationList.add(projectSegmentation);
			}
			dataLogDomainMapper.insertList(projectSegmentationList);
			//教育背景
			if(founder_education ==null || "".equals(founder_education))  {

			} else {
				String[] educationArry = founder_education.split(",");
				List<DataLogEducation> dataLogEducationlist = new Page<DataLogEducation>();
				for (int a = 0; a < educationArry.length; a++) {
					DataLogEducation dataLogEducation = new DataLogEducation();
					dataLogEducation.setEducationName(educationArry[a]);
					dataLogEducation.setLogId(i.getId());
					dataLogEducation.setUserId(userID);
					dataLogEducationlist.add(dataLogEducation);
				}
				dataLogEducationMapper.insertList(dataLogEducationlist);
			}
			//工作背景
			if(founder_work ==null || "".equals(founder_work))  {

			}else {
				String[] workArry = founder_work.split(",");
				List<DataLogWork> dataLogWorklist = new Page<DataLogWork>();
				for (int a = 0; a < workArry.length; a++) {
					DataLogWork dataLogEducation = new DataLogWork();
					dataLogEducation.setWorkName(workArry[a]);
					dataLogEducation.setLogId(i.getId());
					dataLogEducation.setUserId(userID);
					dataLogWorklist.add(dataLogEducation);
				}
				dataLogWorkMapper.insertList(dataLogWorklist);
			}
		}else{
			result.setStatus(204);
			result.setMessage("不能提交重复项目");
		}
		return result;
	}

	/**
	 * 数据回显的
	 * @param id
	 * @return
	 */
	@Override
	public CommonDto<Map<String, Object>> findinformationnew(Integer id) {
		CommonDto<Map<String, Object>> result =new CommonDto<Map<String, Object>>();
		CommonDto<Map<String,List<LabelList>>> data = evaluateService.queryHotData();
		Map<String,Object> map =new HashMap<>();
		InvestmentDataLog p =new InvestmentDataLog();
		p.setId(id);
		InvestmentDataLog pi = investmentDataLogMapper.selectOne(p);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(pi.getFinanTime());
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		String dateString2 = formatter2.format(pi.getCreateTime());
		//基本信息
		map.put("FinanTime",dateString);
		map.put("createtime",dateString2);
		map.put("Institutional_name",pi.getInstitutionalName());
		map.put("short_name",pi.getShortName());
		map.put("company_name",pi.getCompanyName());
		map.put("word_introduction",pi.getWordIntroduction());
		map.put("amont",pi.getAmont());
		map.put("stock_right",pi.getStockRight());
		map.put("create_name",pi.getCreateName());
		map.put("currency",pi.getYn());
		map.put("zhiwu",pi.getZhiwu());
		map.put("id",pi.getId());

		if("".equals(pi.getCity())){
			List<LabelList> cities = data.getData().get("cityKey");
			map.put("city",cities);//城市
			map.put("chengshi",pi.getCity());//城市
		}else{
			List<LabelList> cities = data.getData().get("cityKey");
			String cityStr =pi.getCity();
			for(LabelList labellist : cities){
				if(cityStr.equals(labellist.getName())){
					labellist.setChecked(true);
				}
				map.put("city",cities);//城市
				map.put("chengshi",pi.getCity());
			}
		}
		List<LabelList> industrys = data.getData().get("industryKey");
		DataLogDomain dataLogDomain =new DataLogDomain();
		dataLogDomain.setLogId(id);
		List<DataLogDomain> list = dataLogDomainMapper.select(dataLogDomain);
		if(list != null){
			List<String> a=new LinkedList<String>();
			String [] industryArray =null;
			for(DataLogDomain d:list){
				a.add(d.getDomainName());
				industryArray = new String[a.size()];
				industryArray=a.toArray(industryArray);

				for(LabelList labelList : industrys){
					for(String string : industryArray){
						if(string.equals(labelList.getName())){
							labelList.setChecked(true);
						}
					}
				}
			}
		}
		map.put("industrys",industrys);
		//工作框回显
		DataLogWork dataLogWork =new DataLogWork();
		dataLogWork.setLogId(id);
		List<DataLogWork> work = dataLogWorkMapper.select(dataLogWork);
		if(work.size()>0){
			List<String> a=new LinkedList<String>();
			String [] workArray =null;
			for(DataLogWork d:work){
				a.add(d.getWorkName());
				workArray= new String[a.size()];
				workArray=a.toArray(workArray);
				map.put("work",workArray);
			}
		}else{
			String [] workArray =null;
			map.put("work",workArray);
		}
		//教育背景框回填
		DataLogEducation  dataLogEducation  =new  DataLogEducation ();
		dataLogEducation.setLogId(id);
		List<DataLogEducation> education  = dataLogEducationMapper.select(dataLogEducation );
		if(education.size()>0){
			List<String> a=new LinkedList<String>();
			String [] educationArray =null;
			for(DataLogEducation d:education){
				a.add(d.getEducationName());
				educationArray= new String[a.size()];
				educationArray=a.toArray(educationArray);
				map.put("education",educationArray);
			}

		}else{
			String [] educationArray =null;
			map.put("education",educationArray);
		}
		//轮次
		List<LabelList> label =new LinkedList<>();
		LabelList labelList =new LabelList();
		labelList.setName("天使轮");
		labelList.setValue("天使轮");
		labelList.setChecked(false);

		LabelList labelList2 =new LabelList();
		labelList2.setName("Pre-A轮");
		labelList2.setValue("Pre-A轮");
		labelList2.setChecked(false);

		LabelList labelList3 =new LabelList();
		labelList3.setName("A轮");
		labelList3.setValue("A轮");
		labelList3.setChecked(false);

		LabelList labelList4 =new LabelList();
		labelList4.setName("B轮");
		labelList4.setValue("B轮");
		labelList4.setChecked(false);

		LabelList labelList5 =new LabelList();
		labelList5.setName("C轮");
		labelList5.setValue("C轮");
		labelList5.setChecked(false);

		label.add(labelList);
		label.add(labelList2);
		label.add(labelList3);
		label.add(labelList4);
		label.add(labelList5);
		String stage =pi.getStage();

		for(LabelList li : label){
			if(stage.equals(li.getName())){
				li.setChecked(true);
			}
		}
		map.put("agency",label);//轮次
		result.setData(map);
		return result;
	}

	/**
	 * 修改页面
	 */

	@Transactional
	@Override
	public CommonDto<String> saveInvestmentData1new(String project_name, String project_full_name, String summary, String field,  String city, String rounds, String amount,  String currency,  String stock_right,  Date dateDate,  String founder_name, String founder_work, String founder_education,String userId,String zhiwu,Integer id){
		CommonDto<String> result = new CommonDto<String>();


		//参数验证
		if (StringUtil.isEmpty(project_name)){
			result.setStatus(50001);
			result.setMessage("请填写投资项目简称");
			result.setData(null);

			return result;
		}
		if (StringUtil.isEmpty(field)){
			result.setStatus(50001);
			result.setMessage("请填写所属细分领域");
			result.setData(null);

			return result;
		}
		if (StringUtil.isEmpty(city)){
			result.setStatus(50001);
			result.setMessage("请填写地域");
			result.setData(null);

			return result;
		}
		if (StringUtil.isEmpty(rounds)){
			result.setStatus(50001);
			result.setMessage("请填写投资轮次");
			result.setData(null);

			return result;
		}
		if (StringUtil.isEmpty(amount)){
			result.setStatus(50001);
			result.setMessage("请填写投资金额");
			result.setData(null);

			return result;
		}
		if (StringUtil.isEmpty(currency)){
			result.setStatus(50001);
			result.setMessage("请填写投资币种");
			result.setData(null);

			return result;
		}

		if (dateDate  == null){
			result.setStatus(50001);
			result.setMessage("请填写投资时间");
			result.setData(null);

			return result;
		}
		if (StringUtil.isEmpty(founder_name)){
			result.setStatus(50001);
			result.setMessage("请填写创始人姓名");
			result.setData(null);

			return result;
		}

		if (StringUtil.isEmpty(userId)){
			result.setStatus(50001);
			result.setMessage("缺少用户id");
		}
		if(id == null){
			result.setStatus(50001);
			result.setMessage("页面出现错误");
		}
		//插入主表信息
		Integer userID= usersMapper.findByUuid(userId);
		InvestmentDataLog i2 =new InvestmentDataLog();
		i2.setUserId(userID);
		i2.setShortName(project_name);
		i2.setStage(rounds);
		InvestmentDataLog one = investmentDataLogMapper.selectOne(i2);

		if(one==null ){

			int currency1 =Integer.parseInt(currency);
			InvestmentDataLog i =new InvestmentDataLog();
			i.setYn(currency1);
			i.setCity(city);
			BigDecimal bigDecimalAmount = new BigDecimal(amount);
			if(stock_right ==null || "".equals(stock_right)) {

			}else{
				BigDecimal bigDecimalStockRight = new BigDecimal(stock_right);
				i.setStockRight(bigDecimalStockRight);
			}
			i.setAmont(bigDecimalAmount);
			i.setCompanyName(project_full_name);
			i.setCreateName(founder_name);
			i.setCreateTime(new Date());
			i.setFinanTime(dateDate);
			i.setShortName(project_name);
			i.setStage(rounds);
			i.setUserId(userID);
			i.setWordIntroduction(summary);
			i.setYn(currency1);
			i.setZhiwu(zhiwu);
			i.setId(id);
			//
			i.setAuditYn(0);
			investmentDataLogMapper.updateByPrimaryKey(i);
			//插入细分领域
			DataLogDomain projectSegmentation1 =new DataLogDomain();
			projectSegmentation1.setLogId(id);
			dataLogDomainMapper.delete(projectSegmentation1);
			String[] fieldArry = field.split(",");
			List<DataLogDomain> projectSegmentationList =new Page<DataLogDomain>();
			for (int a=0; a < fieldArry.length;a++){
				DataLogDomain projectSegmentation =new DataLogDomain();
				projectSegmentation.setCreateTime(new Date());
				projectSegmentation.setDomainName(fieldArry[a]);
				projectSegmentation.setLogId(id);
				projectSegmentation.setUserId(userID);
				projectSegmentationList.add(projectSegmentation);
			}
			dataLogDomainMapper.insertList(projectSegmentationList);
			//教育背景
			DataLogEducation dataLogEducation1 =new DataLogEducation();
			dataLogEducation1.setLogId(id);
			List<DataLogEducation> list =dataLogEducationMapper.select(dataLogEducation1);
			if(list.size()>0) {
				dataLogEducationMapper.delete(dataLogEducation1);
			}
			if(founder_education ==null || "".equals(founder_education))  {

			} else {
				String[] educationArry = founder_education.split(",");
				List<DataLogEducation> dataLogEducationlist = new Page<DataLogEducation>();
				for (int a = 0; a < educationArry.length; a++) {
					DataLogEducation dataLogEducation = new DataLogEducation();
					dataLogEducation.setEducationName(educationArry[a]);
					dataLogEducation.setLogId(i.getId());
					dataLogEducation.setUserId(userID);
					dataLogEducationlist.add(dataLogEducation);
				}
				dataLogEducationMapper.insertList(dataLogEducationlist);
			}
			//工作背景
			DataLogWork dataLogEducation2 =new DataLogWork();
			dataLogEducation2.setLogId(id);
			List<DataLogWork> list2 =dataLogWorkMapper.select(dataLogEducation2);
			if(list2.size()>0) {
				dataLogWorkMapper.delete(dataLogEducation2);
			}
			if(founder_work ==null || "".equals(founder_work))  {

			}else {
				String[] workArry = founder_work.split(",");
				List<DataLogWork> dataLogWorklist = new Page<DataLogWork>();
				for (int a = 0; a < workArry.length; a++) {
					DataLogWork dataLogEducation = new DataLogWork();
					dataLogEducation.setWorkName(workArry[a]);
					dataLogEducation.setLogId(i.getId());
					dataLogEducation.setUserId(userID);
					dataLogWorklist.add(dataLogEducation);
				}
				dataLogWorkMapper.insertList(dataLogWorklist);
			}
		}else{
			if(String.valueOf(id).equals(String.valueOf(one.getId()))){
				int currency1 =Integer.parseInt(currency);
				InvestmentDataLog i =new InvestmentDataLog();
				i.setYn(currency1);
				i.setCity(city);
				BigDecimal bigDecimalAmount = new BigDecimal(amount);
				if(stock_right ==null || "".equals(stock_right)) {

				}else{
					BigDecimal bigDecimalStockRight = new BigDecimal(stock_right);
					i.setStockRight(bigDecimalStockRight);
				}
				i.setAmont(bigDecimalAmount);
				i.setCompanyName(project_full_name);
				i.setCreateName(founder_name);
				i.setCreateTime(new Date());
				i.setFinanTime(dateDate);
				i.setShortName(project_name);
				i.setStage(rounds);
				i.setUserId(userID);
				i.setWordIntroduction(summary);
				i.setYn(currency1);
				i.setZhiwu(zhiwu);
				i.setId(id);
				//
				i.setAuditYn(0);
				investmentDataLogMapper.updateByPrimaryKey(i);
				//插入细分领域
				DataLogDomain projectSegmentation1 =new DataLogDomain();
				projectSegmentation1.setLogId(id);
				dataLogDomainMapper.delete(projectSegmentation1);
				String[] fieldArry = field.split(",");
				List<DataLogDomain> projectSegmentationList =new Page<DataLogDomain>();
				for (int a=0; a < fieldArry.length;a++){
					DataLogDomain projectSegmentation =new DataLogDomain();
					projectSegmentation.setCreateTime(new Date());
					projectSegmentation.setDomainName(fieldArry[a]);
					projectSegmentation.setLogId(id);
					projectSegmentation.setUserId(userID);
					projectSegmentationList.add(projectSegmentation);
				}
				dataLogDomainMapper.insertList(projectSegmentationList);
				//教育背景
				DataLogEducation dataLogEducation1 =new DataLogEducation();
				dataLogEducation1.setLogId(id);
				List<DataLogEducation> list =dataLogEducationMapper.select(dataLogEducation1);
				if(list.size()>0) {
					dataLogEducationMapper.delete(dataLogEducation1);
				}
				if(founder_education ==null || "".equals(founder_education))  {

				} else {
					String[] educationArry = founder_education.split(",");
					List<DataLogEducation> dataLogEducationlist = new Page<DataLogEducation>();
					for (int a = 0; a < educationArry.length; a++) {
						DataLogEducation dataLogEducation = new DataLogEducation();
						dataLogEducation.setEducationName(educationArry[a]);
						dataLogEducation.setLogId(i.getId());
						dataLogEducation.setUserId(userID);
						dataLogEducationlist.add(dataLogEducation);
					}
					dataLogEducationMapper.insertList(dataLogEducationlist);
				}
				//工作背景
				DataLogWork dataLogEducation2 =new DataLogWork();
				dataLogEducation2.setLogId(id);
				List<DataLogWork> list2 =dataLogWorkMapper.select(dataLogEducation2);
				if(list2.size()>0) {
					dataLogWorkMapper.delete(dataLogEducation2);
				}
				if(founder_work ==null || "".equals(founder_work))  {

				}else {
					String[] workArry = founder_work.split(",");
					List<DataLogWork> dataLogWorklist = new Page<DataLogWork>();
					for (int a = 0; a < workArry.length; a++) {
						DataLogWork dataLogEducation = new DataLogWork();
						dataLogEducation.setWorkName(workArry[a]);
						dataLogEducation.setLogId(i.getId());
						dataLogEducation.setUserId(userID);
						dataLogWorklist.add(dataLogEducation);
					}
					dataLogWorkMapper.insertList(dataLogWorklist);
				}

			}else{
				result.setStatus(204);
				result.setMessage("不能提交重复项目");
			}
		}
		return result;
	}
	
}
