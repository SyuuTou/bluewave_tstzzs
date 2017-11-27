package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.Subject;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectMapper extends OwnerMapper<Subject> {

    List<Subject> getIntelligentSearchInfo(@Param("inputsWords") String inputsWords,@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);

    List<Subject> getIntelligentSearchInfoFullName(@Param("inputsWords") String inputsWords,@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);

}