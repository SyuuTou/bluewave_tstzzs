package com.lhjl.tzzs.proxy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.dto.InterviewListInputDto;
import com.lhjl.tzzs.proxy.dto.InterviewListOutputDto;
import com.lhjl.tzzs.proxy.model.Interview;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;

public interface InterviewMapper extends OwnerMapper<Interview> {
	 List<Map<String,Object>> findProjectInterviewByIds(@Param("projectsIds") Integer[] projectsIds);

	/**
	 * 根据约谈的id更新约谈的状态
	 * @param interviewId
	 * @param status
	 * @return
	 */
	Integer updateStatusByInterviewId(Integer interviewId, Integer status);
	
	/**
	 * 根据id查询单条记录
	 * @param id
	 * @return
	 */
	Interview findById(@Param("id") Integer id);
	/**
	 * 获取访谈记录的列表数据
	 * @param body
	 * @return
	 */
	List<InterviewListOutputDto> getInterviewList(InterviewListInputDto body);
	/**
	 * 获取列表显示的行数
	 * @param body
	 * @return
	 */
	Long getInterviewListCount(InterviewListInputDto body);
}