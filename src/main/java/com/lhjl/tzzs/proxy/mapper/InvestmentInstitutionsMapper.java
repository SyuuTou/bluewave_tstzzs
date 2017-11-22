package com.lhjl.tzzs.proxy.mapper;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
@Mapper
public interface InvestmentInstitutionsMapper extends OwnerMapper<InvestmentInstitutions> {
    List<InvestmentInstitutionsDto> serachInstitution(@Param("shortName") String shortName, @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);
    List<Map<String, Object>>findByInvestmentShortNameAll(@Param("shortName") String shortName,@Param("userId") String userId);
    List<Map<String,Object>> findInvestment50(@Param("type") String type, @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize,@Param("beginTime") String beginTime,@Param("endTime") String endTime);
    List<Map<String,Object>> findInvestmentall(@Param("type") String type, @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize,@Param("beginTime") String beginTime,@Param("endTime") String endTime);
    List<InvestmentInstitutionsDto> screenInstitutionAll (@Param("workArray") Integer [] workArray,@Param("types") Integer [] types ,@Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);
     List<Integer>serachSendProjectId(@Param("workArray1")Integer [] workArray1);
    List<InvestmentInstitutions> findeInvestmentByShortName(@Param("shortName") String shortName,@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);
}