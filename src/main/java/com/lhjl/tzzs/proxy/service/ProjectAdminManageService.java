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
	/**
	 * 获取基金管理信息
	 * @param subjectId
	 * @param subjectType 主体类型（1项目；2机构）
	 * @return
	 */
    CommonDto<ProjectManageDto> getProjectMange(Integer subjectId,Integer subjectType);

    CommonDto<String> addOrUpdateManage(ProjectManageDto body);

    CommonDto<List<MetaInvestType>> getInvestType();

    CommonDto<List<String>> getClassicCase(String inputWord);

}
