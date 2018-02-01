package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditBasicInfoDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ProjectSendBMapper;
import com.lhjl.tzzs.proxy.model.ProjectSendB;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditBasicInfoService;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@Service
public class CollectProjectAuditBasicInfoServiceImpl implements CollectProjectAuditBasicInfoService {

    @Autowired
    private ProjectSendBMapper projectSendBMapper;

    @Override
    public CommonDto<CollectProjectAuditBasicInfoDto> getCollectProjectAuditBasicInfo(Integer projectId) {
        CommonDto<CollectProjectAuditBasicInfoDto> result = new CommonDto<>();

        CollectProjectAuditBasicInfoDto collectProjectAuditBasicInfoDto = new CollectProjectAuditBasicInfoDto();

        ProjectSendB projectSendB = projectSendBMapper.selectByPrimaryKey(projectId);
        if(null == projectSendB){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }

        collectProjectAuditBasicInfoDto.setCompanyFullName(projectSendB.getFullName());
        collectProjectAuditBasicInfoDto.setKernelDesc(projectSendB.getKernelDesc());
        collectProjectAuditBasicInfoDto.setUrl(projectSendB.getUrl());
        collectProjectAuditBasicInfoDto.setEstablishedTime(String.valueOf(projectSendB.getCreateTime()));

        result.setStatus(200);
        result.setMessage("success");
        result.setData(collectProjectAuditBasicInfoDto);
        return result;
    }
}
