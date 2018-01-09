package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.MetaInvestmentInstitutionTeamType;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetaInvestmentInstitutionTeamTypeMapper extends OwnerMapper<MetaInvestmentInstitutionTeamType> {

    /**
     * 查询机构成员对应的
     * @param institutionId
     * @return
     */
    List<MetaInvestmentInstitutionTeamType> selectTeamTypeByInstitutionId(@Param("institutionId") Integer institutionId);
}