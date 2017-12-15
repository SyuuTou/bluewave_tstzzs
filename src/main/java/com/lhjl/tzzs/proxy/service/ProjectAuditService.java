package com.lhjl.tzzs.proxy.service;

import java.util.List;
import java.util.Map;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectAuditInputDto;
import com.lhjl.tzzs.proxy.dto.XiangsiDto;

public interface ProjectAuditService {
    CommonDto<String> adminAuditProject(ProjectAuditInputDto body);
    CommonDto<List<Map<String, Object>>> findProject(Integer id);
    CommonDto<List<XiangsiDto>> findProjectall(int id,Integer pageNumber,Integer pageSize);

    /**
     * 查找我的关注状态
     * @param id
     * @return
     */
    CommonDto<Map<String,Object>> findFollow(Integer id,String token);

    /**
     * 管理员添加项目管理员接口
     * @param projectId 项目id
     * @param userId 用户id
     * @return
     */
    CommonDto<String> adminAddAdministractor(Integer projectId,Integer userId);

}
