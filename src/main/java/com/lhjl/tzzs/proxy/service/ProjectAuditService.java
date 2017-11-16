package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAuditInputDto;
import com.lhjl.tzzs.proxy.dto.XiangsiDto;

public interface ProjectAuditService {
    CommonDto<String> adminAuditProject(ProjectAuditInputDto body);
    CommonDto<List<Map<String, Object>>> findProject(Integer id);
    CommonDto<List<XiangsiDto>> findProjectall(Integer id);

    /**
     * 查找我的关注状态
     * @param id
     * @return
     */
    CommonDto<Map<String,Object>> findFollow(Integer id,String token);
}
