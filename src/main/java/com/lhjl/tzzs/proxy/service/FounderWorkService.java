package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.FoundersWork;

import java.util.List;

public interface FounderWorkService {
    /**
     * 工作经历检索检索
     * @param inputsWords 输入内容
     * @param pageSize 返回数量
     * @return
     */
    CommonDto<List<FoundersWork>> findFounderWork(String inputsWords,Integer pageSize);
}
