package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.CompanyIntelligentOutputDto;

import java.util.List;

public interface SubjectService {
    /**
     * 公司检索接口
     * @param inputsWords 输入内容
     * @param pageSize  一页返回数量
     * @param inputsType 搜全程还是搜简称
     * @return
     */
    CommonDto<List<CompanyIntelligentOutputDto>> getCompanyIntelligent(String inputsWords, Integer pageSize,Integer inputsType);
}
