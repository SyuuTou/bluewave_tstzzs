package com.lhjl.tzzs.proxy.service;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.ProjectBusinessPlanImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectBusinessPlanService {

    CommonDto<List<ProjectBusinessPlanImage>> resolveProjectBusinessPlan(MultipartFile file, Integer projectId, String token);

    CommonDto<String> updateProjectBusinessPlanImage(List<ProjectBusinessPlanImage> reqDto, Integer projectId);
}
