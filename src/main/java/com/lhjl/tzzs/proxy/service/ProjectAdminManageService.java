package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectManageDto;
import com.lhjl.tzzs.proxy.model.MetaInvestType;

import java.util.List;

/**
 * @author caochuangui
 * @date 2018-1-24 16:19:19
 */
public interface ProjectAdminManageService {

    CommonDto<ProjectManageDto> getProjectMange(Integer companyId);

    CommonDto<String> addOrUpdateManage(ProjectManageDto body);

    CommonDto<List<MetaInvestType>> getInvestType();

    CommonDto<List<String>> getClassicCase(String inputWord);

}
