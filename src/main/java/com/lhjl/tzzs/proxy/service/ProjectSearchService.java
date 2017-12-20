package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectReqDto;
import com.lhjl.tzzs.proxy.dto.ProjectResDto;

import java.util.List;

public interface ProjectSearchService {

    CommonDto<List<ProjectResDto>> projectFilter(ProjectReqDto reqDto);
}
