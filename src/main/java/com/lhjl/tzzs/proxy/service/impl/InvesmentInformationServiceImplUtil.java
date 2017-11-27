package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionsDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zyy on 2017/11/26.
 */
@Component
public class InvesmentInformationServiceImplUtil {

    @Resource
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;


    @Value("${statistics.beginTime}")
    private String beginTime;

    @Value("${statistics.endTime}")
    private String endTime;

    @Cacheable(value = "getSearchBase50All", keyGenerator = "wiselyKeyGenerator")
    //@CacheEvict(value = "getSearchBase50All", allEntries=true)
    public  List<InvestmentInstitutionsDto> getSearchBase50All(Integer pageNum, Integer pageSize) {
        Integer beginNum = (pageNum - 1) * pageSize;
        List<InvestmentInstitutionsDto> list = investmentInstitutionsMapper.findInvestment50New("1", beginNum, pageSize,beginTime,endTime);
        return  list;
    }

}
