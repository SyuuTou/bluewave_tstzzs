package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CollectProjectAuditBasicInfoDto;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.CollectProjectAuditBasicInfoService;
import com.lhjl.tzzs.proxy.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanhaijulang on 2018/2/1.
 */
@Service
public class CollectProjectAuditBasicInfoServiceImpl implements CollectProjectAuditBasicInfoService {

    @Autowired
    private ProjectSendBMapper projectSendBMapper;

    @Autowired
    private ProjectSendTagsBMapper projectSendTagsBMapper;

    @Autowired
    private ProjectSendCompetingBMapper projectSendCompetingBMapper;

    @Autowired
    private ProjectSendSegmentationBMapper projectSendSegmentationBMapper;

    @Autowired
    private ProjectSendAuditBMapper projectSendAuditBMapper;

    @Override
    public CommonDto<CollectProjectAuditBasicInfoDto> getCollectProjectAuditBasicInfo(Integer projectId) {
        CommonDto<CollectProjectAuditBasicInfoDto> result = new CommonDto<>();

        CollectProjectAuditBasicInfoDto collectProjectAuditBasicInfoDto = new CollectProjectAuditBasicInfoDto();

        ProjectSendAuditB projectSendAuditB = new ProjectSendAuditB();
        projectSendAuditB.setId(projectId);

        ProjectSendAuditB projectSendAuditB1 = projectSendAuditBMapper.selectByPrimaryKey(projectSendAuditB);

        if(null == projectSendAuditB1){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }

        ProjectSendB projectSendB = projectSendBMapper.selectByPrimaryKey(projectSendAuditB1.getProjectSendBId());
        if(null == projectSendB){
            result.setStatus(300);
            result.setMessage("failed");
            result.setData(null);
            return result;
        }

        collectProjectAuditBasicInfoDto.setCompanyFullName(projectSendB.getFullName());
        collectProjectAuditBasicInfoDto.setKernelDesc(projectSendB.getKernelDesc());
        collectProjectAuditBasicInfoDto.setUrl(projectSendB.getUrl());
        collectProjectAuditBasicInfoDto.setCompanyCity(projectSendB.getCity());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        collectProjectAuditBasicInfoDto.setEstablishedTime(sdf.format(projectSendB.getCreateTime()));

        List<ProjectSendTagsB> projectSendTagsBList = projectSendTagsBMapper.selectByProjectId(projectSendAuditB1.getProjectSendBId());
        List<String> projectSendTagsBLists = new ArrayList<>();
        String[] projectSendTagsBArr = null;
        if(null != projectSendTagsBList){
            projectSendTagsBList.forEach(projectSendTags_i ->{
                projectSendTagsBLists.add(projectSendTags_i.getTagName());
            });
            projectSendTagsBArr = new String[projectSendTagsBLists.size()];
            projectSendTagsBLists.toArray(projectSendTagsBArr);
        }
        collectProjectAuditBasicInfoDto.setCompanyTag(projectSendTagsBArr);

        List<ProjectSendCompetingB> projectSendCompetingBList = projectSendCompetingBMapper.selectByProjectId(projectSendAuditB1.getProjectSendBId());
        List<String> projectSendCompetingBLists = new ArrayList<>();
        String[] projectSendCompetingBArr = null;
        if(null != projectSendCompetingBList){
            projectSendCompetingBList.forEach(projectSendCompetingB_i ->{
                projectSendCompetingBLists.add(projectSendCompetingB_i.getCompetingName());
            });
            projectSendCompetingBArr = new String[projectSendCompetingBLists.size()];
            projectSendCompetingBLists.toArray(projectSendCompetingBArr);
        }
        collectProjectAuditBasicInfoDto.setCompetitiveProduct(projectSendCompetingBArr);

        List<ProjectSendSegmentationB> projectSendSegmentationBList = projectSendSegmentationBMapper.selectByProjectId(projectSendAuditB1.getProjectSendBId());
        List<String> projectSendSegmentationBLists = new ArrayList<>();
        String[] projectSendSegmentationBArr = null;
        if(null != projectSendSegmentationBList){
            projectSendSegmentationBList.forEach(projectSendSegmentationB_i ->{
                projectSendSegmentationBLists.add(projectSendSegmentationB_i.getSegmentationName());
            });
            projectSendSegmentationBArr = new String[projectSendSegmentationBLists.size()];
            projectSendSegmentationBLists.toArray(projectSendSegmentationBArr);
        }
        collectProjectAuditBasicInfoDto.setSegmentation(projectSendSegmentationBArr);

        result.setStatus(200);
        result.setMessage("success");
        result.setData(collectProjectAuditBasicInfoDto);
        return result;
    }
}
