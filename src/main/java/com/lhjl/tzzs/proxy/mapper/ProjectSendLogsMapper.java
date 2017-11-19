package com.lhjl.tzzs.proxy.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.lhjl.tzzs.proxy.model.ProjectSendLogs;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProjectSendLogsMapper extends OwnerMapper<ProjectSendLogs> {
	List<Map<String,Object>> findSendList(@Param("shortName") String shortName,@Param("beginNum") Integer beginNum,@Param("pageSize") Integer pageSize);
	List<Map<String,Object>> findSendListAll(@Param("beginNum") Integer beginNum,@Param("pageSize") Integer pageSize);
}