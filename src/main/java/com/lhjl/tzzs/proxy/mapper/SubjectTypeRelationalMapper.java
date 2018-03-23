package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.SubjectTypeRelational;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

public interface SubjectTypeRelationalMapper extends OwnerMapper<SubjectTypeRelational> {

    SubjectTypeRelational selectBySubjectIdAndProjectType(@Param("subjectId") Integer subjectId, @Param("projectType") Integer projectType);
}