package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectPreferDto;

/**
 * @author caochuangui
 * @date 2018-1-24 16:19:33
 */
public interface ProjectAdminPreferService {
    CommonDto<ProjectPreferDto> getProjectprefer(Integer projectId,Integer subjectType);

    CommonDto<String> addOrUpdatePrefer(ProjectPreferDto body);
}
