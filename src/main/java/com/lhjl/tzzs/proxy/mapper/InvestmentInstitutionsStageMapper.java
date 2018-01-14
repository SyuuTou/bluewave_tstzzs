package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsStage;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InvestmentInstitutionsStageMapper extends OwnerMapper<InvestmentInstitutionsStage> {
	
    /**
     * 根据机构id获取机构阶段
     * @param institutionId
     * @return
     */
    List<Map<String,Object>> findInstitutionStage(@Param("institutionId") Integer institutionId);
    
    /**
     * 批量增加
     * @return
     */
    Boolean addBatch(List<InvestmentInstitutionsStage> list);

    /**
     * 根据机构id获取机构阶段和数量
     * @param institutionId
     * @return
     */
    List<Map<String,Object>> findInstitutionStageCount(@Param("institutionId") Integer institutionId);
}