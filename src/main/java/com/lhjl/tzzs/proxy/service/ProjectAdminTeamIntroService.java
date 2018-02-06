package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;

import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/6.
 */
public interface ProjectAdminTeamIntroService {

    CommonDto<String> addOrUpdatePojectTeamIntro(Integer projectId, String teamIntroduction);

    CommonDto<Map<String, Object>> getPojectTeamIntro(Integer projectId);


}
