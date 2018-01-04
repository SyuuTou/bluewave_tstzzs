package com.lhjl.tzzs.proxy.mapper;

import com.lhjl.tzzs.proxy.model.ProjectSendAuditB;
import com.lhjl.tzzs.proxy.utils.OwnerMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectSendAuditBMapper extends OwnerMapper<ProjectSendAuditB> {
    List<ProjectSendAuditB> searchProjectSendAuditB(@Param("userid") Integer userid,@Param("appid") Integer appid);
}