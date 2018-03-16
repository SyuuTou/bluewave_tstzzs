package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectFinancingLogInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectFinancingLogOutputDto;
import com.lhjl.tzzs.proxy.dto.projectfinancinglog.ProjectFinancingLogHeadInputDto;
import com.lhjl.tzzs.proxy.dto.projectfinancinglog.ProjectFinancingLogHeadOutputDto;
import com.lhjl.tzzs.proxy.mapper.ProjectFinancialLogFollowStatusMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectFinancingLogMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.mapper.UserTokenMapper;
import com.lhjl.tzzs.proxy.mapper.UsersMapper;
import com.lhjl.tzzs.proxy.model.ProjectFinancialLogFollowStatus;
import com.lhjl.tzzs.proxy.model.ProjectFinancingLog;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.model.UserToken;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.ProjectFinancingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectFinancingLogServiceImpl extends GenericService implements ProjectFinancingLogService {

    @Value("${pageNum}")
    private Integer defalutPageNum;

    @Value("${pageSize}")
    private Integer defalutPageSize;

    @Autowired
    private ProjectFinancingLogMapper projectFinancingLogMapper;
    @Autowired
    private ProjectsMapper projectsMapper;
    @Autowired
    private ProjectFinancialLogFollowStatusMapper projectFinancialLogFollowStatusMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private UsersMapper usersMapper;
    
    @Transactional(readOnly=true)
    @Override
    public CommonDto<Map<String, Object>> getProjectFinancingLogList(ProjectFinancingLogInputDto body) {
        CommonDto<Map<String,Object>> result = new CommonDto<>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
        //输入实际字符串格式化对象
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //输出时间字符串格式化对象
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Map<String,Object> map = new HashMap<>();
        //格式化输入时间字符串
        if (body.getBeginTimeInputStr() != null){
            try {
				body.setBeginTime(sdf2.parse(body.getBeginTimeInputStr()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
        }
        if (body.getEndTimeInputStr() != null){
            try {
				body.setEndTime(sdf2.parse(body.getEndTimeInputStr()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
        }
      //格式化页码的默认值
        if (body.getCurrentPage() == null){
            body.setCurrentPage(defalutPageNum);
        }

        if (body.getPageSize() == null){
            body.setPageSize(defalutPageSize);  
        }
        body.setStart( (body.getCurrentPage()-1) * body.getPageSize() );
        this.LOGGER.info("**"+body);
        //数据输出
        List<ProjectFinancingLogOutputDto> projectFinancingLogList = projectFinancingLogMapper.getProjectFinancingLogLists(body);
        Integer totalcount = projectFinancingLogMapper.getProjectFinancingLogListCount(body);
        /**
         * 格式化输出时间转换为字符串
         */
        projectFinancingLogList.forEach((e)->{
        	if(e.getFinancingTime() != null) {
        		e.setFinancingTimeOutputStr(sdf.format(e.getFinancingTime()));
        	}
			if(e.getCreateTime() != null) {
				e.setCreateTimeOutputStr(sdf3.format(e.getCreateTime()));	
		  	}
			if(e.getUpdateTime() != null) {
				e.setUpdateTimeOutputStr(sdf3.format(e.getUpdateTime()));
			}
//			格式化审核时间输出字符串
			if(e.getApprovalTime() != null) {
				e.setApprovalTimeOutputStr(sdf3.format(e.getApprovalTime()));
			}
        });
        
        map.put("total",totalcount);
        map.put("list",projectFinancingLogList);
        
        map.put("currentPage",body.getCurrentPage()); 
        map.put("pageSize",body.getPageSize());
        

        result.setData(map);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

	@Override
	public CommonDto<ProjectFinancingLogHeadOutputDto> echoProjectFinancingLogHead(Integer appid,
			Integer projectFinancingLogId) {
		CommonDto<ProjectFinancingLogHeadOutputDto> result=new CommonDto<>();
		ProjectFinancingLogHeadOutputDto ProjectFinancingLogHead = projectFinancingLogMapper.echoProjectFinancingLogHead(projectFinancingLogId);
		//取得提交者的token
		String submitterToken = ProjectFinancingLogHead.getSubmitter();
		
		if(submitterToken==null || "".equals(submitterToken)) {
			ProjectFinancingLogHead.setUser("天使指数后台");
		}else {
			//根据提交者token获取提交者姓名
			UserToken query=new UserToken();
			query.setToken(submitterToken);
			try {
				query = userTokenMapper.selectOne(query);
			}catch(Exception e){
				result.setData(null);
		        result.setStatus(500);
		        result.setMessage("DB数据存在异常，token不唯一");
				return result;
			}
			if(query!=null) {
				Users user = usersMapper.selectByPrimaryKey(query.getUserId());
				//设置提交人姓名
				if(user!=null) {
					ProjectFinancingLogHead.setUser(user.getActualName());
					//
				}else {
					ProjectFinancingLogHead.setUser("无效token，平台不存在该token所对应的用户记录");
				}
			}else {
				ProjectFinancingLogHead.setUser("无效token，平台不存在该token所对应的记录");
			}
		}
		
		result.setData(ProjectFinancingLogHead);
        result.setStatus(200);
        result.setMessage("success");
		return result;
	}

	@Override
	public CommonDto<List<Projects>> blurScanProjectByShortName(Integer appid, String keyword) {
		CommonDto<List<Projects>> result=new CommonDto<>();
		List<Projects> projects=new ArrayList<>();
		if(keyword != null && !(keyword.equals(""))) {
			projects = projectsMapper.blurScanProjectByShortName(keyword);  
		}
		result.setData(projects==null || projects.size()==0 ? null : projects);
        result.setStatus(200);
        result.setMessage("success");
		return result;
	}
	
	@Transactional
	@Override
	public CommonDto<Map<String,Object>> saveOrUpdateProjectLog(Integer appid, ProjectFinancingLogHeadInputDto body) {
		CommonDto<Map<String,Object>> result=new CommonDto<>();
		
		if(body.getShortName() == null || body.getShortName().equals("")) {//如果没有关联相关的项目，那么直接返回提示信息
			result.setData(null);
	        result.setStatus(500);
	        result.setMessage("请输入相关的公司信息");
			return result;
		}
		//获取该项目简称所对应的项目
		Projects pro =new Projects();
		pro.setShortName(body.getShortName());
		try {
			pro = projectsMapper.selectOne(pro);
		}catch(Exception e) {
			result.setData(null);
	        result.setStatus(500);
	        result.setMessage("数据库数据存在问题，项目简称不唯一");
	        return result;
		}
		
		//用于获取最终的投资事件id
		Integer projectLogId = body.getProjectFinancingLogId();
		//用于获取最终的项目id
		Integer projectId=null;
		if(pro != null) {
			projectId=pro.getId();
		}
		
		if(body.getProjectFinancingLogId() == null) {//执行投资事件增加 ,
			if(pro != null) {//该项目存在项目表中，为该项目增加一条投资事件
				ProjectFinancingLog pfl=new ProjectFinancingLog();
				pfl.setProjectId(pro.getId());
				pfl.setSerialNumber(pro.getSerialNumber());
				pfl.setCreateTime(new Date());
				pfl.setYn(0);
				
				projectFinancingLogMapper.insertSelective(pfl);
				//获取该投资事件的id
				projectLogId=pfl.getId();
				projectId=pro.getId();
			}else {//该项目不存在项目表中，新建一个项目，并增加一条投资事件，获取该投资事件的id
				Projects projectsInsertEntity = new Projects();
				projectsInsertEntity.setShortName(body.getShortName());
//				projectsInsertEntity.setSerialNumber(serialNumber);//如何设置项目序列号
//				projectsInsertEntity.setYn(1);
				projectsInsertEntity.setUpdateTime(new Date());//create_time指的是公司的创建事件，而此处应该是新增记录的时间
				projectsMapper.insertSelective(projectsInsertEntity);
				
				projectId=projectsInsertEntity.getId();
				
				//进行投资事件的增加
				ProjectFinancingLog pfl=new ProjectFinancingLog();
				pfl.setProjectId(projectsInsertEntity.getId());
				pfl.setSerialNumber(projectsInsertEntity.getSerialNumber());//项目的序列号去哪里获得？
				pfl.setCreateTime(new Date());
				pfl.setYn(0);
				
				projectFinancingLogMapper.insertSelective(pfl);
				//获取该投资事件的id
				projectLogId=pfl.getId();
			}
		}else {//执行投资事件执行关联项目的简称的更新
			//根据id获取投资事件的实体
			ProjectFinancingLog pfl = projectFinancingLogMapper.selectByPrimaryKey(body.getProjectFinancingLogId());
			Projects projects=new Projects();
			//获取该投资事件所对应的项目实体
			if(pfl != null) {
				projects = projectsMapper.selectByPrimaryKey(pfl.getProjectId());
			}
			//如果该投资事件所对应的项目实体简称和输入的简称一致,或者输入简称所对应的项目在项目表中不存在，那么更换该投资时间所对应的项目简称即可
			if(projects!=null) {
				if(pro == null) {//输入简称所代表的项目在数据表projects中不存在
					//Projects更新实体的作成
					Projects projectsUpdateEntity =new Projects();
					projectsUpdateEntity.setId(pfl.getProjectId());
					projectsUpdateEntity.setShortName(body.getShortName());
					//更新projects表中该项目的简称
					projectsMapper.updateByPrimaryKeySelective(projectsUpdateEntity);
					//设置项目的主键id
					projectId = projectsUpdateEntity.getId();
				}else if(! projects.getShortName().equals(body.getShortName())){
					result.setData(null);
			        result.setStatus(500);
			        result.setMessage("输入简称所对应的项目在项目表中存在，但不是该投资事件所对应的原项目");
			        return result;
				}
			}else {
				result.setData(null);
		        result.setStatus(500);
		        result.setMessage("DB数据存在错误，该投资事件关联的项目在项目表中不存在");
				return result;
			}
		}
		
		//在获取投资事件的基础之上， 增加投资事件的跟进状态（只是插入就可以了）
		ProjectFinancialLogFollowStatus pflfs=new ProjectFinancialLogFollowStatus();
		pflfs.setProjectFinancialLogId(projectLogId);
		pflfs.setMetaFollowStatusId(body.getFollowStatus());
		pflfs.setDescription(body.getDescription());
		pflfs.setSubmitorToken(body.getSubmitorToken());
		pflfs.setCreateTime(new Date());
		projectFinancialLogFollowStatusMapper.insertSelective(pflfs);
		
		Map<String,Object> map=new HashMap<>();
		map.put("projectLogId", projectLogId);
		map.put("projectId", projectId);  
		
		result.setData(map);
        result.setStatus(200);
        result.setMessage("success");
		return result;
	}

	@Override
	public CommonDto<ProjectFinancingLog> echoProjectFinancingLogById(Integer appid, Integer projectFinancingLogId) {
		CommonDto<ProjectFinancingLog> result=new CommonDto<>();
		ProjectFinancingLog projectFinancingLog = projectFinancingLogMapper.selectByPrimaryKey(projectFinancingLogId);
		result.setData(projectFinancingLog);
        result.setStatus(200);
        result.setMessage("success");
		return result;
	}
}
