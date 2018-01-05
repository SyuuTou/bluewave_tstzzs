package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionTeam;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestmentInstitutionTeamMapper extends OwnerMapper<InvestmentInstitutionTeam> {

    /**
     * 读取团队成员列表的查询方法
     * @param institutionId
     * @param metaType
     * @return
     */
    List<InvestmentInstitutionTeam> getTeamMemberListByType(@Param("institutionId") Integer institutionId,@Param("metaType") Integer metaType);
}