package com.lhjl.tzzs.proxy.mapper;


import com.lhjl.tzzs.proxy.dto.ProjectSendAuditBListDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAdminListInputDto;
import com.lhjl.tzzs.proxy.dto.ProjectSendBAdminListOutputDto;
import com.lhjl.tzzs.proxy.model.ProjectSendAuditB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectSendAuditBMapper extends OwnerMapper<ProjectSendAuditB> {
	/**
	 * 
	 * @param userid
	 * @param appid
	 * @return
	 */
    List<ProjectSendAuditB> searchProjectSendAuditB(@Param("userid") Integer userid,@Param("appid") Integer appid);
    /**
     * 
     * @param shortName
     * @param startPage
     * @param pageSize
     * @param appid
     * @return
     */
    List<ProjectSendAuditBListDto> searchProjectSendAuditBList(@Param("shortName") String shortName,@Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize ,@Param("appid") Integer appid);

    /**
     * 管理员获取采集提交项目列表
     * @return
     */
    List<ProjectSendBAdminListOutputDto> adminGetProjectSendList(ProjectSendBAdminListInputDto body);

    /**
     * 获取项目审核列表的总数
     * @param body
     * @return
     */
    Long adminGetProjectSendListCount(ProjectSendBAdminListInputDto body);

    Map<String,Object> searchProjectSendLogoInfo(@Param("projectSendId") Integer projectSendId,@Param("appid") Integer appid);
}