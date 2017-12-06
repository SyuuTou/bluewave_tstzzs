package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.InstitutionsProjectDto.InstitutionsProjectInputDto;
import com.lhjl.tzzs.proxy.dto.InstitutionsProjectDto.InstitutionsProjectOutputDto;

import java.util.List;
import java.util.Map;

public interface InstitutionsProjectService {
    /**
     * 获取机构投资项目列表接口
     * @param body
     * @return
     */
    CommonDto<List<Map<String,Object>>>  findInstitutionProject(InstitutionsProjectInputDto body);
}
