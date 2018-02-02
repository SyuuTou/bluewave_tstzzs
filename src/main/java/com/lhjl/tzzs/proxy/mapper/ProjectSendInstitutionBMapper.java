package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ProjectSendInstitutionB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectSendInstitutionBMapper extends OwnerMapper<ProjectSendInstitutionB> {
    List<Integer> getInstitutionIdsByPrepareId(@Param("prepareId") Integer prepareId);
}