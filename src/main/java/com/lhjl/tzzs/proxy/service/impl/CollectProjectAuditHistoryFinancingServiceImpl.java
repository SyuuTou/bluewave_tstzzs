package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditHistoryFinancingDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ProjectSendAuditBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendFinancingHistoryBMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSendInvestorBMapper;
import com.lhjl.tzzs.proxy.model.ProjectSendAuditB;
import com.lhjl.tzzs.proxy.model.ProjectSendFinancingHistoryB;
import com.lhjl.tzzs.proxy.model.ProjectSendInvestorB;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditHistoryFinancingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@Service
public class CollectProjectAuditHistoryFinancingServiceImpl implements CollectProjectAuditHistoryFinancingService {

    @Autowired
    private ProjectSendFinancingHistoryBMapper projectSendFinancingHistoryBMapper;

    @Autowired
    private ProjectSendInvestorBMapper projectSendInvestorBMapper;

    @Autowired
    private ProjectSendAuditBMapper projectSendAuditBMapper;

    @Override
    public CommonDto<List<CollectProjectAuditHistoryFinancingDto>> getCollectProjectAuditHistoryFinancing(Integer projectId) {

        CommonDto<List<CollectProjectAuditHistoryFinancingDto>> result = new CommonDto<>();

        List<CollectProjectAuditHistoryFinancingDto> collectProjectAuditHistoryFinancingDtoList = new ArrayList<>();

        ProjectSendAuditB projectSendAuditB = new ProjectSendAuditB();
        projectSendAuditB.setId(projectId);

        ProjectSendAuditB projectSendAuditB1 = projectSendAuditBMapper.selectByPrimaryKey(projectSendAuditB);

        if(null == projectSendAuditB1){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }

        ProjectSendFinancingHistoryB projectSendFinancingHistoryB = new ProjectSendFinancingHistoryB();
        projectSendFinancingHistoryB.setProjectSendBId(projectSendAuditB1.getProjectSendBId());

        List<ProjectSendFinancingHistoryB> projectSendFinancingHistoryBList = projectSendFinancingHistoryBMapper.select(projectSendFinancingHistoryB);

        if(null != projectSendFinancingHistoryBList && projectSendFinancingHistoryBList.size() != 0){
            for(ProjectSendFinancingHistoryB projectSendFinancingHistoryB_i : projectSendFinancingHistoryBList){
                CollectProjectAuditHistoryFinancingDto collectProjectAuditHistoryFinancingDto = new CollectProjectAuditHistoryFinancingDto();

                collectProjectAuditHistoryFinancingDto.setHistoryFinancingId(projectSendFinancingHistoryB_i.getId());
                collectProjectAuditHistoryFinancingDto.setStage(projectSendFinancingHistoryB_i.getStage());
                collectProjectAuditHistoryFinancingDto.setProjectId(projectSendFinancingHistoryB_i.getProjectSendBId());
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                collectProjectAuditHistoryFinancingDto.setInvestTime(sdf.format(projectSendFinancingHistoryB_i.getFinancingTime()));
                collectProjectAuditHistoryFinancingDto.setInvestAmount(projectSendFinancingHistoryB.getAmount());
                collectProjectAuditHistoryFinancingDto.setCurrencyType(projectSendFinancingHistoryB_i.getCurrency()); //融资金额币种单位
                collectProjectAuditHistoryFinancingDto.setAssessmentAmount(projectSendFinancingHistoryB.getTotalAmount());
                ProjectSendInvestorB projectSendInvestorB = new ProjectSendInvestorB();
                projectSendInvestorB.setPsFinancingHistoryBId(projectSendFinancingHistoryB_i.getId());
                List<ProjectSendInvestorB> projectSendInvestorBList = projectSendInvestorBMapper.select(projectSendInvestorB);
                String[] projectSendInvestorBsArr = null;
                List<String> investorNameList = new ArrayList<>();
                List<CollectProjectAuditHistoryFinancingDto.CollectInvestorDto> collectInvestorDtoList = new ArrayList<>();
                if(null != projectSendInvestorBList){
                    projectSendInvestorBList.forEach(projectSendInvestorB_i -> {
                        CollectProjectAuditHistoryFinancingDto.CollectInvestorDto collectInvestorDto = new CollectProjectAuditHistoryFinancingDto.CollectInvestorDto();
                        collectInvestorDto.setInvestorId(projectSendInvestorB_i.getId());
                        collectInvestorDto.setInvestorShortName(projectSendInvestorB_i.getInvestorName());
                        collectInvestorDto.setStockPer(projectSendInvestorB_i.getStockRatio());
                        collectInvestorDtoList.add(collectInvestorDto);
                        investorNameList.add(projectSendInvestorB_i.getInvestorName());
                    });
                    projectSendInvestorBsArr = new String[investorNameList.size()];
                    investorNameList.toArray(projectSendInvestorBsArr);
                }
                collectProjectAuditHistoryFinancingDto.setInvestors(projectSendInvestorBsArr);
                collectProjectAuditHistoryFinancingDto.setCollectInvestorDtoList(collectInvestorDtoList);

                collectProjectAuditHistoryFinancingDtoList.add(collectProjectAuditHistoryFinancingDto);
            };
        }

        result.setStatus(200);
        result.setMessage("success");
        result.setData(collectProjectAuditHistoryFinancingDtoList);
        return result;
    }

}
