package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.Interview;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InterviewMapper extends OwnerMapper<Interview> {
    List<Map<String,Object>> findProjectInterviewByIds(@Param("projectsIds") Integer[] projectsIds);
}