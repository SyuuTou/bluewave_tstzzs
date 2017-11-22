package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.SearchInvestmentRecord;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchInvestmentRecordMapper extends OwnerMapper<SearchInvestmentRecord> {
    List<SearchInvestmentRecord> serachInstitutionRecord(@Param("userId") Integer userId);
    List<SearchInvestmentRecord> serachInstitutionHot();
}