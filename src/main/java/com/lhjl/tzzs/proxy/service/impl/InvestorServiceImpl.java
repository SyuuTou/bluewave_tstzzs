package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.ChangePrincipalInputDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.VIPOutputDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorListInputDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorsOutputDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.InvestorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service(value = "investorServiceImpl")
public class InvestorServiceImpl implements InvestorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvestorServiceImpl.class);

    @Autowired
    private InvestorsMapper investorsMapper;
    @Autowired
    private InvestorDemandMapper investorDemandMapper;
    @Autowired
    private InvestorDemandCharacterMapper investorDemandCharacterMapper;
    @Autowired
    private InvestorDemandSegmentationMapper investorDemandSegmentationMapper;
    @Autowired
    private InvestorDemandSpeedwayMapper investorDemandSpeedwayMapper;
    @Autowired
    private InvestorDemandStageMapper investorDemandStageMapper;
    @Autowired
    private InvestorInvestmentCaseMapper investorInvestmentCaseMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private DatasOperationManageMapper datasOperationManageMapper;
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private UserLevelRelationMapper userLevelRelationMapper;
    @Autowired
    private MetaUserLevelMapper metaUserLevelMapper;
    @Autowired
    private MetaAdminTypeMapper metaAdminTypeMapper;
    @Autowired
    private UserIntegralConsumeMapper userIntegralConsumeMapper;
    @Autowired
    private UsersPayMapper usersPayMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;
    
    @Value("${pageNum}")
    private Integer pageNumDefault ;

    @Value("${pageSize}")
    private Integer pageSizeDefault;
    
    @Transactional(readOnly = true)
	@Override
	public CommonDto<Map<String,Object>> listInvestorsInfos(Integer appid, InvestorListInputDto body) {

    	if (StringUtil.isEmpty(body.getToken())){
    		return new CommonDto<>(null, "Token 不能为空。", 500);
		}
    	//根据token获取当前用户
		UserToken query = new UserToken();
    	query.setToken(body.getToken());
    	UserToken userToken = userTokenMapper.selectOne(query);
    	
		Users users = usersMapper.selectByPrimaryKey(userToken.getUserId());


		CommonDto<Map<String,Object>> result =new CommonDto<>();
		Map<String,Object> map=new HashMap<>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//验证、格式化参数信息
        //默认设置为10条记录
        if (body.getPageSize() == null){
            body.setPageSize(pageSizeDefault);
        }
        //默认设置为第一页
        if (body.getCurrentPage() == null){
            body.setCurrentPage(pageNumDefault);
        }
        //设置开始索引  
        body.setStart((long) ((body.getCurrentPage()-1) * body.getPageSize())) ;
        
        AdminUser au=new AdminUser();
        au.setMetaAppId(appid);
        au.setUserId(userToken.getUserId());
        //设置管理员的管理员类型
        try {
        	au = adminUserMapper.selectOne(au);
        	if(au != null) {
        		//设置管理员的类型  
        		body.setAdminType(au.getAdminType());
        		//设置当前的（管理员）负责人的名称
				if (StringUtil.isNotEmpty(users.getActualName())) {
					body.setAdminName(users.getActualName());
				}
        	} 
        	
        }catch(Exception e) {
        	result.setData(null);
            result.setStatus(500);
            result.setMessage("数据库数据存在问题，相同的用户id存在两条数据");
    		return result;
        }
        //输入时间字符串转换为日期
        try{
        	if(body.getStartTimeStr() !=null) {
            	body.setStartTime(sdf.parse(body.getStartTimeStr()));
            }
            if(body.getEndTimeStr() !=null) {
            	body.setEndTime(sdf.parse(body.getEndTimeStr()));
            }
        }catch(Exception e){
        	result.setData(null);
            result.setStatus(200);
            result.setMessage("日期字符串输入格式不正确");
    		return result;  
        }
        List<InvestorsOutputDto> list = investorsMapper.listInvestorsInfos(body);
        
        //日期转换为输出时间字符串
        list.forEach((e)->{  
        	if(e.getUpdateTime() !=null) {
        		e.setUpdateTimeStr(sdf.format(e.getUpdateTime()));
        	}
        	if(e.getCreateTime() !=null) {
        		e.setCreateTimeStr(sdf.format(e.getCreateTime()));  
        	}
        	if(e.getCheckTime() !=null) {
        		e.setCheckTimeOutputStr(sdf.format(e.getCheckTime()));  
        	}
        	/*if(e.getSubmitter() !=null) {  
        		
        		UserToken ut = new UserToken();
        		ut.setToken(e.getSubmitter());
        		
        		ut = userTokenMapper.selectOne(ut);
        			
        		Users user = usersMapper.selectByPrimaryKey(ut.getUserId());
        		e.setSubmitterName(user.getActualName());
        	}*/
        });
        Long total = investorsMapper.getInvestorsListCount(body);
        
        //规范数据封装格式，便于前台接受数据
        map.put("list", list);
        map.put("total", total);
        map.put("currentPage", body.getCurrentPage());
        map.put("pageSize", body.getPageSize());
        
        result.setData(map);
        result.setStatus(200);
        result.setMessage("success");
		return result;
	}

	@Override
	public CommonDto<Boolean> matchUsers(Integer appid, Integer userId,Integer investorId) {
		CommonDto<Boolean> result =new CommonDto<>();
		Investors investor=new Investors();
		investor.setId(investorId);
		investor.setUserId(userId);
		
		investorsMapper.updateByPrimaryKeySelective(investor);
		
		result.setData(true);
        result.setStatus(200);
        result.setMessage("success");
		return result;
	}
	@Transactional
	@Override
	public CommonDto<Boolean> changeIrPrincipalBatchOrSingle(Integer appid, ChangePrincipalInputDto body) {
		CommonDto<Boolean> result =new CommonDto<>();
		DatasOperationManage dom=new DatasOperationManage();
		//删除所有选中投资人的记录信息
		if(body.getInvestorIds() !=null && body.getInvestorIds().size()!=0) {
			body.getInvestorIds().forEach((e)->{
				dom.setDataId(e);
				dom.setDataType("INVESTOR");  
				dom.setIrPrincipal(body.getIrPrincipal());
				if(datasOperationManageMapper.findInvestor(dom) ==null) {//不存在相关的投资人，执行插入设置
					//新增插入时间
					dom.setCreateTime(new Date());
					datasOperationManageMapper.addInvestorIrPrincipal(dom);  
				}else {//执行相关的更新操作
					dom.setUpdateTime(new Date());
					datasOperationManageMapper.changeInvestorIrPrincipal(dom);
				}
				
			});
		}
			
		result.setData(true);
        result.setStatus(200);
        result.setMessage("success");
		return result;
	}

	@Override
	public CommonDto<DatasOperationManage> echoInvestorsManagementInfo(Integer appid, Integer investorId) {
		CommonDto<DatasOperationManage> result =new CommonDto<>();
		DatasOperationManage dom =new DatasOperationManage();
		dom.setDataId(investorId);
		dom.setDataType("INVESTOR");
		//一个投资人只有一条的运营管理记录
		try {
			dom = datasOperationManageMapper.selectOne(dom);
		}catch(Exception e) {
			result.setData(null);
	        result.setStatus(500); 
	        result.setMessage("由于请求参数不正确导致数据库查询出多条相关的运营纪录");
			return result;
		}
		if(dom !=null) {
			Integer basicsRecommend = dom.getBasicsRecommend();
			if(basicsRecommend==null) {
				basicsRecommend=0;
			}
			Integer dynamicRecommand = dom.getDynamicRecommand();
			if(dynamicRecommand==null) {
				dynamicRecommand=0;
			}
			Integer operationRecommend = dom.getOperationRecommend();
			if(operationRecommend==null) {
				operationRecommend=0;  
			}
			dom.setRecommand(basicsRecommend + dynamicRecommand+ operationRecommend );
		}
		result.setData(dom !=null ? dom : new DatasOperationManage());
        result.setStatus(200); 
        result.setMessage("success");
		return result;
	}
	@Transactional
	@Override
	public CommonDto<Boolean> saveOrUpdateInvestorsManagement(Integer appid, DatasOperationManage body) {
		CommonDto<Boolean> result =new CommonDto<>();
		
		DatasOperationManage dom =new DatasOperationManage();
		dom.setDataId(body.getDataId());
		dom.setDataType("INVESTOR");
		
		body.setDataType("INVESTOR");
		try {
			if( datasOperationManageMapper.selectOne(dom) != null) {//执行更新操作
				body.setUpdateTime(new Date());
				datasOperationManageMapper.updateByPrimaryKeySelective(body);
			}else {//执行增加
				body.setCreateTime(new Date());
				datasOperationManageMapper.insertSelective(body);
			}
		}catch(Exception e ) {
			result.setData(true);
	        result.setStatus(500); 
	        result.setMessage("运营管理表中存在投资人冗余数据，数据存在问题");
			return result;
		}
		result.setData(true);
        result.setStatus(200); 
        result.setMessage("success");
        
		return result;
	}

	@Override
	public CommonDto<List<AdminUser>> getTstzzsAdmin(Integer appid,String keyword) {
		CommonDto<List<AdminUser>> result =new CommonDto<>();
		
		List<AdminUser> tstzzsAdmins = adminUserMapper.selectTstzzsAdmins(keyword);
		if(tstzzsAdmins !=null && tstzzsAdmins.size()!=0) {
			for(AdminUser tmp:tstzzsAdmins) {
//				设置用户的公司名称
				Users user = usersMapper.selectByPrimaryKey(tmp.getUserId());
				if(user != null) {
					tmp.setCompanyName(user.getCompanyName());
					//设置用户的职位类型名称
					Integer type = tmp.getAdminType();
					tmp.setDutyName(metaAdminTypeMapper.selectByPrimaryKey(type).getName());
				}
			}
		}
		result.setData(tstzzsAdmins);
        result.setStatus(200); 
        result.setMessage("success");
		return result;
	}

	@Override
	public CommonDto<VIPOutputDto> echoInvestorsVIPInfo(Integer appid, Integer userId) {
		CommonDto<VIPOutputDto> result=new CommonDto<>();
		VIPOutputDto vod = new VIPOutputDto();
		UserLevelRelation ulr=new UserLevelRelation();
		ulr.setUserId(userId);
		ulr.setYn(1);  
		
		ulr = userLevelRelationMapper.selectOne(ulr);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(ulr ==null) {
			//返回一个默认初始化的用户会员对象
			vod.setUserLevelRelation(new UserLevelRelation());
		}else {
			ulr.setBeginTimeStr(sdf.format(ulr.getBeginTime()));
			ulr.setEndTimeStr(sdf.format(ulr.getEndTime()));
			
			vod.setUserLevelRelation(ulr);
			vod.setCostNum(Math.abs(userIntegralConsumeMapper.getCostNum(userId)));
			vod.setActualVipCostNum(usersPayMapper.getActualVipCostNum(userId));
			vod.setSumIntegrateCostNum(usersPayMapper.getSumIntegrateCostNum(userId));
			vod.setSumPayNum(usersPayMapper.getSumPayNum(userId));
		}  
		
		result.setData(vod);
        result.setStatus(200); 
        result.setMessage("success");
		return result;
	}
	
	@Transactional
	@Override
	public CommonDto<Boolean> saveOrUpdateInvestorsVIPInfo(Integer appid, UserLevelRelation body) {
		CommonDto<Boolean> result=new CommonDto<>();
		
		if(body.getUserId() == null) {
			result.setData(false);
	        result.setStatus(500); 
	        result.setMessage("请输入相关的userId");    
			return result;
		}
		UserLevelRelation ulr=new UserLevelRelation();
		ulr.setUserId(body.getUserId());
		ulr.setYn(1);  
		UserLevelRelation user=null;
		//用户会员等级表的查询实体
		try {
			user = userLevelRelationMapper.selectOne(ulr);
		}catch(Exception e) {
			result.setData(false);
	        result.setStatus(500); 
	        result.setMessage("数据库记录存在非法数据");
			return result;
		}
		
		
		//进行数据格式的规范化
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if(body.getEndTimeStr() != null) {
				body.setEndTime(sdf.parse(body.getEndTimeStr()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
			result.setData(false);
	        result.setStatus(200); 
	        result.setMessage("日期字符串格式化错误");    
			return result;
		}
		//如果用户输入的截止日期小于当前日期，则用户输入非法
		if(body.getEndTime().getTime() < new Date().getTime()) {
			result.setData(false);
	        result.setStatus(500); 
	        result.setMessage("用户输入截止日期非法");
			return result;
		}
		//不存在相关的用户会员记录
		if(user == null) {//进行相应的插入操作
			//设置会员的创建时间以及变更的时间
			body.setBeginTime(new Date());
			body.setCreateTime(new Date());
			body.setYn(1);
			//后台管理员添加时需要设置状态为："赠送"
			body.setStatus(4);
			userLevelRelationMapper.insertSelective(body);
		}else {
			//将user的有效位变更为无效
			user.setYn(0);
			userLevelRelationMapper.updateByPrimaryKeySelective(user);
			
			body.setCreateTime(new Date());
			body.setYn(1);
			body.setStatus(4);
			if(user.getLevelId() !=body.getLevelId()) {//用户的会员等级进行了变更
				
				body.setBeginTime(new Date());
				userLevelRelationMapper.insertSelective(body);
			}else {//用户的会员等级没有进行变更，会员的开始时间没有发生变化，要取得之前会员的开始时间
				body.setBeginTime(user.getBeginTime());
				userLevelRelationMapper.insertSelective(body);
			}
		}
		
		result.setData(true);
        result.setStatus(200); 
        result.setMessage("success");
		return result;
	}

	@Override
	public CommonDto<List<MetaUserLevel>> sourceMetaUserLevels(Integer appid) {
		CommonDto<List<MetaUserLevel>> result =new CommonDto<List<MetaUserLevel>>();
		List<MetaUserLevel> metaUserLevels = metaUserLevelMapper.selectAll();
		
		result.setData(metaUserLevels);
        result.setStatus(200);
        result.setMessage("success");
		return result;
	}
    
}