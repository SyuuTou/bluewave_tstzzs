package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendListDto;

import java.util.List;
import java.util.Map;

public interface FindSendProjectService {
    CommonDto<List<Map<String,Object>>> findIntegralsSend(String shortName, Integer pageNum, Integer pageSize);
}
