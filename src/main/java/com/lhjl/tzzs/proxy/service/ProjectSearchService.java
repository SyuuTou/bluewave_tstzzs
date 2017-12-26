package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.HistogramList;
import com.lhjl.tzzs.proxy.dto.ProjectReqDto;
import com.lhjl.tzzs.proxy.dto.ProjectResDto;

import java.util.List;
import java.util.Map;

public interface ProjectSearchService {

    CommonDto<List<ProjectResDto>> projectFilter(ProjectReqDto reqDto);

    CommonDto<List<ProjectResDto>> projectFilterOrderByFinancing(ProjectReqDto reqDto);

    CommonDto<List<Map<String,Object>>> ralatedInstitution(ProjectReqDto reqDto);

    CommonDto<List<HistogramList>> projectFilterStatistices(ProjectReqDto reqDto);
}
