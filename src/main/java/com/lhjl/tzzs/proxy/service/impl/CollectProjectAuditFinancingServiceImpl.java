package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditFinancingDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ProjectSendAuditBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendFinancingApprovalBMapper;
import com.lhjl.tzzs.proxy.model.ProjectSendAuditB;
import com.lhjl.tzzs.proxy.model.ProjectSendB;
import com.lhjl.tzzs.proxy.model.ProjectSendFinancingApprovalB;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditFinancingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@Service
public class CollectProjectAuditFinancingServiceImpl implements CollectProjectAuditFinancingService {

    @Autowired
    private ProjectSendFinancingApprovalBMapper projectSendFinancingApprovalBMapper;

    @Autowired
    private ProjectSendAuditBMapper projectSendAuditBMapper;

    @Override
    public CommonDto<CollectProjectAuditFinancingDto> getCollectProjectAuditFinancing(Integer projectId) {
        CommonDto<CollectProjectAuditFinancingDto> result = new CommonDto<>();

        CollectProjectAuditFinancingDto collectProjectAuditFinancingDto = new CollectProjectAuditFinancingDto();

        ProjectSendAuditB projectSendAuditB = new ProjectSendAuditB();
        projectSendAuditB.setId(projectId);

        ProjectSendAuditB projectSendAuditB1 = projectSendAuditBMapper.selectByPrimaryKey(projectSendAuditB);

        if(null == projectSendAuditB1){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }

        ProjectSendFinancingApprovalB projectSendFinancingApprovalB = projectSendFinancingApprovalBMapper.selectBySendProjectId(projectSendAuditB1.getProjectSendBId());

        if(null == projectSendFinancingApprovalB){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }

        collectProjectAuditFinancingDto.setProjectId(projectSendFinancingApprovalB.getProjectSendBId());
        collectProjectAuditFinancingDto.setFinancingStage(projectSendFinancingApprovalB.getStage());
        collectProjectAuditFinancingDto.setFinancingAmount(projectSendFinancingApprovalB.getAmount());
        collectProjectAuditFinancingDto.setCurrencyType(projectSendFinancingApprovalB.getCurrency());
        collectProjectAuditFinancingDto.setShareStock(projectSendFinancingApprovalB.getShareDivest());
        collectProjectAuditFinancingDto.setFinancingUse(projectSendFinancingApprovalB.getProjectFinancingUseful());

        result.setStatus(200);
        result.setMessage("success");
        result.setData(collectProjectAuditFinancingDto);
        return result;
    }

}
