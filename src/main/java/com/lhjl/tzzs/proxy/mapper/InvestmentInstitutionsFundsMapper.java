package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsFunds;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestmentInstitutionsFundsMapper extends OwnerMapper<InvestmentInstitutionsFunds> {
    List<InvestmentInstitutionsFunds> getFundList(@Param("subjectId") Integer subjectId);
    InvestmentInstitutionsFunds selectByProjectId(@Param("projectId") Integer projectId);
}