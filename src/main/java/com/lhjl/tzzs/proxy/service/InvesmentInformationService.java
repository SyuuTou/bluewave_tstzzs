package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionsDto;
import com.lhjl.tzzs.proxy.dto.SaveInformationDto;

import java.util.List;

/**
 * Created by zyy on 2017/11/22.
 */
public interface InvesmentInformationService {
    CommonDto<List<InvestmentInstitutionsDto>> findFiveInvestmentNew(Integer pageNum, Integer pageSize, String token);
    CommonDto<List<InvestmentInstitutionsDto>> findNotFiveInvestmentNew(Integer pageNum, Integer pageSize,String token);
    CommonDto<List<InvestmentInstitutionsDto>> findRecommendCreater(String token);
    CommonDto<List<InvestmentInstitutionsDto>> findRecommendInvestor(String token);

    /**
     * 获取最近活跃的机构
     * @param body
     * @return
     */
    CommonDto<List<InvestmentInstitutionsDto>> findRecentlyInstitution(SaveInformationDto body);
}
