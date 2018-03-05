package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;

import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/2.
 */
public interface ProjectMatchService {

    CommonDto<Map<String, Object>> getProjectMatch(String searchWord);

}
