package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsSegmentation;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InvestmentInstitutionsSegmentationMapper extends OwnerMapper<InvestmentInstitutionsSegmentation> {

    /**
     * 根据机构id获取机构领域信息
     * @param institutionId
     * @return
     */
    List<Map<String,Object>> findSegment(@Param("institutionId") Integer institutionId);


    /**
     * 获取机构投资年份
     * @param institutionId
     * @return
     */
    List<Map<String,Object>> findYear(@Param("institutionId") Integer institutionId);
}