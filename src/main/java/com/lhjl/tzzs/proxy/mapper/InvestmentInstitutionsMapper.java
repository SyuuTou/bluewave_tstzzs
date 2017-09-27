package com.lhjl.tzzs.proxy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
@Mapper
public interface InvestmentInstitutionsMapper extends OwnerMapper<InvestmentInstitutions> {
    List<Map<String, Object>>findByInvestmentShortNameAll(@Param("shortName") String shortName,@Param("userId") String userId);
}