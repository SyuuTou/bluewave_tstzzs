package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.ChangePrincipalInputDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.VIPOutputDto;
import com.lhjl.tzzs.proxy.dto.investorDto.InvestorListInputDto;
import com.lhjl.tzzs.proxy.investorDto.InvestorsOutputDto;
import com.lhjl.tzzs.proxy.mapper.AdminUserMapper;
import com.lhjl.tzzs.proxy.mapper.DatasOperationManageMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorDemandCharacterMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorDemandMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorDemandSegmentationMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorDemandSpeedwayMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorDemandStageMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorInvestmentCaseMapper;
import com.lhjl.tzzs.proxy.mapper.InvestorsMapper;
import com.lhjl.tzzs.proxy.mapper.MetaAdminTypeMapper;
import com.lhjl.tzzs.proxy.mapper.MetaUserLevelMapper;
import com.lhjl.tzzs.proxy.mapper.UserIntegralConsumeMapper;
import com.lhjl.tzzs.proxy.mapper.UserLevelRelationMapper;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.mapper.UsersPayMapper;
import com.lhjl.tzzs.proxy.model.AdminUser;
import com.lhjl.tzzs.proxy.model.DatasOperationManage;
import com.lhjl.tzzs.proxy.model.MetaUserLevel;
import com.lhjl.tzzs.proxy.model.UserLevelRelation;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.service.InvestorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Value("${pageNum}")
    private Integer pageNumDefault ;

    @Value("${pageSize}")
    private Integer pageSizeDefault;
    
    @Transactional(readOnly = true)
	@Override
	public CommonDto<Map<String,Object>> listInvestorsInfos(Integer appid, InvestorListInputDto body) {
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
        //进行时间字符串的转换
        list.forEach((e)->{  
        	if(e.getUpdateTime() !=null) {
        		e.setUpdateTimeStr(sdf.format(e.getUpdateTime()));
        	}
        	if(e.getCreateTime() !=null) {
        		e.setCreateTimeStr(sdf.format(e.getCreateTime()));  
        	}
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
	public CommonDto<List<Users>> matchUsers(Integer appid, String keyword) {
		CommonDto<List<Users>> result =new CommonDto<>();
		
		List<Users> matchUserList = usersMapper.matchUsersExcepteBlackList(keyword);
		
		result.setData(matchUserList);
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
				dom.setDataType("投资人");  
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
	public CommonDto<DatasOperationManage> echoInvestorsManagementInfo(Integer appid, Integer id) {
		CommonDto<DatasOperationManage> result =new CommonDto<>();
		DatasOperationManage dom =new DatasOperationManage();
		dom.setDataId(id);
		dom.setDataType("投资人");
		//一个投资人只有一条的运营管理记录
		dom = datasOperationManageMapper.selectOne(dom);
		if(dom !=null) {
			dom.setRecommand(dom.getBasicsRecommend()+dom.getDynamicRecommand()+dom.getOperationRecommend());
		}
		
		result.setData(dom !=null ?dom : new DatasOperationManage());
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
		dom.setDataType("投资人");
		
		body.setDataType("投资人");
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
	        result.setStatus(200); 
	        result.setMessage("投资人存在重读数据，数据库数据存在问题");
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
		for(AdminUser tmp:tstzzsAdmins) {
//			设置用户的公司名称
			Users user = usersMapper.selectByPrimaryKey(tmp.getUserId());
			tmp.setCompanyName(user.getCompanyName());
			//设置用户的职位类型名称
			Integer type = tmp.getAdminType();
			tmp.setDutyName(metaAdminTypeMapper.selectByPrimaryKey(type).getName());
			
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
		UserLevelRelation url = userLevelRelationMapper.selectOne(ulr);
		if(url ==null) {
			//返回一个默认初始化的用户会员对象
			vod.setUserLevelRelation(new UserLevelRelation());
		}else {
			vod.setUserLevelRelation(url);
			vod.setCostNum(userIntegralConsumeMapper.getCostNum(userId));
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
		
		UserLevelRelation ulr=new UserLevelRelation();
		ulr.setUserId(body.getUserId());
		ulr.setYn(1);  
		System.err.println(ulr+"**url*");
		//用户会员等级表的查询实体
		UserLevelRelation user = userLevelRelationMapper.selectOne(ulr);
		System.err.println(user+"**user*");
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
