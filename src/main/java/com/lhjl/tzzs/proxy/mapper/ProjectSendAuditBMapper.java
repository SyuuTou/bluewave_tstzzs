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
  /*  List<Map<String,Object>> adminGetProjectSendList(@Param("searchWord") String searchWord,@Param("begainTime") String begainTime,
                                                     @Param("endTime") String endTime,@Param("projetcSource") Integer projetcSource,
                                                     @Param("creatTimeOrder") Integer creatTimeOrder,@Param("creatTimeOrderDesc") Integer creatTimeOrderDesc,
                                                     @Param("auditTimeOrder") Integer auditTimeOrder,@Param("auditTimeOrderDesc") Integer auditTimeOrderDesc,
                                                     @Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);
   */
//    List<Map<String,Object>> adminGetProjectSendList(ProjectSendBAdminListInputDto body);
    List<ProjectSendBAdminListOutputDto> adminGetProjectSendList(ProjectSendBAdminListInputDto body);


    /*Integer adminGetProjectSendListCount(@Param("searchWord") String searchWord,@Param("begainTime") String begainTime,
                                                             @Param("endTime") String endTime,@Param("projetcSource") Integer projetcSource,
                                                             @Param("creatTimeOrder") Integer creatTimeOrder,@Param("creatTimeOrderDesc") Integer creatTimeOrderDesc,
                                                             @Param("auditTimeOrder") Integer auditTimeOrder,@Param("auditTimeOrderDesc") Integer auditTimeOrderDesc,
                                                             @Param("startPage") Integer startPage,@Param("pageSize") Integer pageSize);
    */
    Long adminGetProjectSendListCount(ProjectSendBAdminListInputDto body);

    Map<String,Object> searchProjectSendLogoInfo(@Param("projectSendId") Integer projectSendId,@Param("appid") Integer appid);
}