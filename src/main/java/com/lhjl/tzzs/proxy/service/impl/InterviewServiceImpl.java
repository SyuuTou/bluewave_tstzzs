package com.lhjl.tzzs.proxy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InterviewCommentDto;
import com.lhjl.tzzs.proxy.dto.InterviewDetailsOutputDto;
import com.lhjl.tzzs.proxy.dto.InterviewListInputDto;
import com.lhjl.tzzs.proxy.dto.InterviewListOutputDto;
import com.lhjl.tzzs.proxy.dto.UpdateModifyInputDto;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.InterviewService;

/**
 * 约谈
 * @author PP
 *
 */
@Service
public class InterviewServiceImpl extends GenericService implements InterviewService {
	@Value("${pageSize}")
	private Integer pageSizeDefault;
	
	@Value("${pageNum}")
	private Integer pageNumDefault;
	
    @Resource 
    private InterviewMapper interviewMapper;
    
    @Resource
    private FollowMapper followMapper;
    
    @Resource
    private UsersMapper usersMapper;
    
    @Resource
    private ProjectsMapper projectsMapper;
    
    @Resource
    private FoundersEducationMapper foundersEducationMapper;
    
    @Resource
    private FoundersWorkMapper foundersWorkMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;
    /**
     * 插入约谈记录
     */
    @Override
    @Transactional
    public void insertInterview(Interview interview) {
        interviewMapper.insert(interview);
    }
    
    
	@Override
	@Transactional
	public CommonDto<String> updateFollowStatus1(Integer yn, Integer projectId, String userId) {
		CommonDto<String> result =new CommonDto<String>();

        try {
            Follow follow = new Follow();
            follow.setProjectsId(projectId);
            List<Follow> list = followMapper.select(follow);
            if(list.size() == 0|| list.get(0) == null ){
                yn = 1;
                follow.setCreateTime(new Date());
                follow.setProjectsId(projectId);
                follow.setStatus( yn);
                follow.setUserId(userId);
                followMapper.insert(follow);
                result.setStatus(200);
                result.setData("sucess");
            }else{
                if( yn == 0){
                     yn= 1;
                    follow.setCreateTime(new Date());
                    follow.setProjectsId(projectId);
                    follow.setStatus( yn);
                    follow.setUserId(userId);
                    followMapper.insert(follow);
                    result.setStatus(200);
                    result.setData("sucess");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
       }
	
	@Transactional
	@Override
	public CommonDto<Boolean> addComent(Integer appid, InterviewCommentDto body) {
		CommonDto<Boolean> result=new CommonDto<>();
		if(body != null) {
			Integer id = body.getId();
			String comment = body.getComment();
			Interview interview = new Interview();
			interview.setId(id);
			interview.setComment(comment);
			try {
				interviewMapper.updateByPrimaryKeySelective(interview);
				
				result.setData(true);
				result.setMessage("数据更新成功");
				result.setStatus(200);
			}catch(Exception e) {
				result.setData(false);
				result.setMessage("数据更新失败");
				result.setStatus(500);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public CommonDto<Boolean> updateStatus(UpdateModifyInputDto reqBody, Integer appid) {
		
		CommonDto<Boolean> result=new CommonDto<>();
		try {
			if(reqBody != null) {  
				interviewMapper.updateStatusByInterviewId(reqBody.getId(),reqBody.getStatus());
				result.setData(true);
				result.setStatus(200);
				result.setMessage("数据更新成功");
			}
		}catch(Exception e) {
			e.printStackTrace();
			result.setData(false);
			result.setStatus(500);
			result.setMessage("数据更新失败");
		}
		return result;
	}

//	@Transactional
	@Override
	public CommonDto<InterviewDetailsOutputDto> echoInterviewInfo(Integer id, String projectShortName,Integer appid) {
		CommonDto<InterviewDetailsOutputDto> result = new CommonDto<InterviewDetailsOutputDto>();
		this.LOGGER.info(id+"*id**");
		InterviewDetailsOutputDto outputDto = new InterviewDetailsOutputDto();
		outputDto.setId(id);
		//查询相关的用户数据
		Interview interview = interviewMapper.selectByPrimaryKey(id);
		
		this.LOGGER.info(interview+"**interview*");
		
		if(interview != null) {
			//实例化一个Users对象 用于作为查找条件
			UserToken userToken = new UserToken();
			userToken.setToken(interview.getUserId());
			//根据UUID查询数据库中的唯一一条user对象
			userToken=userTokenMapper.selectOne(userToken);
			Users user = usersMapper.selectByPrimaryKey(userToken.getUserId());
			
			if (user != null) {
				outputDto.setActualName(user.getActualName());
				outputDto.setCompanyName(user.getCompanyDesc());
				outputDto.setPhonenumber(user.getPhonenumber());
				outputDto.setCompanyDuties(user.getCompanyDuties());
				outputDto.setWechat(user.getWechat());
				outputDto.setEmail(user.getEmail());
				outputDto.setCity(user.getCity());
				outputDto.setIndustry(user.getIndustry());
				outputDto.setIdentityType(user.getIdentityType());
				outputDto.setUserDesc(user.getDesc());
				outputDto.setCompanyDesc(user.getCompanyDesc());
				outputDto.setDemand(user.getDemand());
				outputDto.setWorkCard(user.getWorkCard());
			}
			
			//获取用户id
			Integer userPrimaryKey = null;
			if(user !=null) {
				userPrimaryKey = user.getId();
			}
			//获取用户的教育经历以及工作经历
			List<String> userEducations = foundersEducationMapper.findFoundersEducationsByUserId(userPrimaryKey);
			if (userEducations != null) {
				outputDto.setEducationExperience(userEducations);
			}
			List<String> userWorks = foundersWorkMapper.findFoundersWorksByUserId(userPrimaryKey);
			if(userWorks != null) {
				outputDto.setWorkExperience(userWorks);
			}
			//获取项目的相关信息
			Projects pro = new Projects();
			pro.setShortName(projectShortName);
			
			try {
				pro=projectsMapper.selectOne(pro);
				if(pro !=null) {
					outputDto.setShortName(pro.getShortName());
					outputDto.setCommet(pro.getCommet());
				}
				
				Interview interviewRecord = interviewMapper.selectByPrimaryKey(id);
				if(interviewRecord!=null) {
					outputDto.setInterviewDesc(interviewRecord.getDesc());
					outputDto.setComment(interviewRecord.getComment());
					outputDto.setFollowStatus(interviewRecord.getStatus());
				}
				
				result.setData(outputDto);
				result.setMessage("数据查询成功");
				result.setStatus(200);
				
			}catch(Exception e) {
				this.LOGGER.info(e.getMessage(), e.fillInStackTrace());
				result.setData(null);
				result.setMessage("数据库项目简称不唯一");
				result.setStatus(500);
			}
			
		}else {
			result.setData(null);
			result.setMessage("数据查询失败");
			result.setStatus(500);
		}
		
		return result;
	}


	@Override
	public CommonDto<Map<String, Object>> getInterviewList(InterviewListInputDto body) {
		CommonDto<Map<String,Object>> result = new CommonDto<>();
		try {
			 Map<String,Object> map = new HashMap<>();
		        
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
		        
		        //获取所有的约谈输出记录
		        List<InterviewListOutputDto> interviewList = interviewMapper.getInterviewList(body);
		        //查询出的所有的行数
		        Long total = interviewMapper.getInterviewListCount(body);
		        
		        map.put("list", interviewList);
		        map.put("total", total);
		        map.put("currentPage", body.getCurrentPage());
		        map.put("pageSize", body.getPageSize());
		        
		        result.setData(map);
		        result.setStatus(200);
				result.setMessage("数据查询成功");
		}catch(Exception e) {
			e.printStackTrace();
			result.setData(null);
			result.setStatus(500);
			result.setMessage("数据查询失败");
		}
       
		return result;
	}

	
}