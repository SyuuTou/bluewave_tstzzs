package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.FoundersEducation;

import java.util.List;

public interface FounderEducationService {
    /**
     * 通过输入内容获取相似教育经历接口
     * @param inputsWords 输入内容
     * @param pageSize 显示条数
     * @return
     */
    CommonDto<List<FoundersEducation>> getFounderEducationIntelligent(String inputsWords, Integer pageSize);
}
