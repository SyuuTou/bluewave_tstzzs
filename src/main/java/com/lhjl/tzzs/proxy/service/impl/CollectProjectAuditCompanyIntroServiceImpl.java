package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditCompanyIntroDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ProjectSendBMapper;
import com.lhjl.tzzs.proxy.model.ProjectSendB;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditCompanyIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@Service
public class CollectProjectAuditCompanyIntroServiceImpl implements CollectProjectAuditCompanyIntroService {

    @Autowired
    private ProjectSendBMapper projectSendBMapper;

    @Override
    public CommonDto<CollectProjectAuditCompanyIntroDto> getCollectProjectAuditCompanyIntro(Integer projectId) {
        CommonDto<CollectProjectAuditCompanyIntroDto> result = new CommonDto<>();

        CollectProjectAuditCompanyIntroDto collectProjectAuditCompanyIntroDto = new CollectProjectAuditCompanyIntroDto();

        ProjectSendB projectSendB = projectSendBMapper.selectByPrimaryKey(projectId);

        if(null == projectSendB){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }

        collectProjectAuditCompanyIntroDto.setProjectId(projectId);
        collectProjectAuditCompanyIntroDto.setCompanyIntroduction(projectSendB.getCommet());

        result.setStatus(200);
        result.setMessage("success");
        result.setData(collectProjectAuditCompanyIntroDto);
        return result;
    }
}
