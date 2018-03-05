package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.mapper.ProjectCompetitiveProductsMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectLabelsMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectSegmentationMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.model.ProjectCompetitiveProducts;
import com.lhjl.tzzs.proxy.model.ProjectLabels;
import com.lhjl.tzzs.proxy.model.ProjectSegmentation;
import com.lhjl.tzzs.proxy.model.Projects;
import com.lhjl.tzzs.proxy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lanhaijulang on 2018/2/2.
 */
@Service
public class ProjectMatchServiceImpl implements ProjectMatchService {

    @Autowired
    private ProjectsMapper projectsMapper;

    @Autowired
    private ProjectLabelsMapper projectLabelsMapper;

    @Autowired
    private ProjectCompetitiveProductsMapper projectCompetitiveProductsMapper;

    @Autowired
    private ProjectSegmentationMapper projectSegmentationMapper;

    @Override
    public CommonDto<Map<String, Object>> getProjectMatch(String searchWord) {
        CommonDto<Map<String, Object>> result = new CommonDto<>();

        Map<String, Object> projectMatchInfoMap = new HashMap<>();

        Projects projects = projectsMapper.selectByProjectShortName(searchWord);

        if(null == searchWord || "".equals(searchWord)){
            result.setStatus(502);
            result.setMessage("请输入项目名称");
            result.setData(null);
            return  result;
        }

        if(null == projects){
            result.setStatus(502);
            result.setMessage("没有匹配到该项目");
            result.setData(null);
            return result;
        }

        CollectProjectAuditBasicInfoDto collectProjectAuditBasicInfoDto = new CollectProjectAuditBasicInfoDto();

        CollectProjectAuditCompanyIntroDto collectProjectAuditCompanyIntroDto = new CollectProjectAuditCompanyIntroDto();
        CollectProjectAuditInvestHighlightDto collectProjectAuditInvestHighlightDto = new CollectProjectAuditInvestHighlightDto();
        CollectProjectAuditFinancingDto collectProjectAuditFinancingDto = new CollectProjectAuditFinancingDto();
        List<CollectProjectAuditHistoryFinancingDto> collectProjectAuditHistoryFinancingDtoList = new ArrayList<>();
        CollectProjectAuditTeamDto collectProjectAuditTeamDto = new CollectProjectAuditTeamDto();
        if(null != projects){
            collectProjectAuditBasicInfoDto.setCompanyFullName(projects.getFullName());
            collectProjectAuditBasicInfoDto.setKernelDesc(projects.getKernelDesc());
            collectProjectAuditBasicInfoDto.setUrl(projects.getUrl());

            ProjectLabels projectLabels = new ProjectLabels();
            projectLabels.setProjectId(projects.getId());
            List<ProjectLabels> projectLabelsList = projectLabelsMapper.select(projectLabels);
            List<String> projectLabelNames = new ArrayList<>();
            String[] projectLabelArr = null;
            if(null == projectLabelsList){
                collectProjectAuditBasicInfoDto.setCompanyTag(projectLabelArr);
            }else{
                projectLabelsList.forEach( projectLabels_i -> {
                    projectLabelNames.add(projectLabels_i.getLabelName());
                });
                projectLabelArr = new String[projectLabelNames.size()];
                collectProjectAuditBasicInfoDto.setCompanyTag(projectLabelArr);
            }

            ProjectCompetitiveProducts projectCompetitiveProducts = new ProjectCompetitiveProducts();
            projectCompetitiveProducts.setProjectId(projects.getId());
            List<ProjectCompetitiveProducts> projectCompetitiveProductsList = projectCompetitiveProductsMapper.select(projectCompetitiveProducts);
            List<String> projectCompetitiveProductNames = new ArrayList<>();
            String[] projectCompetitiveProductArr = null;
            if(null == projectCompetitiveProductsList){
                collectProjectAuditBasicInfoDto.setCompetitiveProduct(projectCompetitiveProductArr);
            }else{
                projectCompetitiveProductsList.forEach( projectCompetitiveProduct_i -> {
                    projectCompetitiveProductNames.add(projectCompetitiveProduct_i.getCompetitiveProductsName());
                });
                projectCompetitiveProductArr = new String[projectCompetitiveProductNames.size()];
                collectProjectAuditBasicInfoDto.setCompetitiveProduct(projectCompetitiveProductArr);
            }

            ProjectSegmentation projectSegmentation = new ProjectSegmentation();
            projectSegmentation.setProjectId(projects.getId());
            List<ProjectSegmentation> projectSegmentationList = projectSegmentationMapper.select(projectSegmentation);
            List<String> projectSegmentationNames = new ArrayList<>();
            String[] projectSegmentationArr = null;
            if(null == projectSegmentationList){
                collectProjectAuditBasicInfoDto.setSegmentation(projectCompetitiveProductArr);
            }else{
                projectSegmentationList.forEach( projectSegmentation_i -> {
                    projectSegmentationNames.add(projectSegmentation_i.getSegmentationName());
                });
                projectCompetitiveProductArr = new String[projectSegmentationNames.size()];
                collectProjectAuditBasicInfoDto.setSegmentation(projectCompetitiveProductArr);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            collectProjectAuditBasicInfoDto.setEstablishedTime(sdf.format(projects.getCreateTime()));
            collectProjectAuditBasicInfoDto.setCompanyEmail(projects.getCompanyEmail());
            collectProjectAuditBasicInfoDto.setCompanyAddress(projects.getAddress());
            collectProjectAuditBasicInfoDto.sethREmail(projects.getCompanyHrEmail());
            collectProjectAuditBasicInfoDto.setIsInvestforward(projects.getForeignInvestmentYn());

            collectProjectAuditCompanyIntroDto.setProjectId(projects.getId());
            collectProjectAuditCompanyIntroDto.setCompanyIntroduction(projects.getCommet());

            collectProjectAuditInvestHighlightDto.setProjectId(projects.getId());
            collectProjectAuditInvestHighlightDto.setInvestHighlight(projects.getProjectInvestmentHighlights());

            //TODO 融资需求

            //TODO 融资历史

            //TODO 团队成员


        }

        collectProjectAuditBasicInfoDto.setCompanyFullName(projects.getFullName());

        projectMatchInfoMap.put("公司基本信息",collectProjectAuditBasicInfoDto);
        projectMatchInfoMap.put("公司简介", collectProjectAuditCompanyIntroDto);
        projectMatchInfoMap.put("公司投资亮点", collectProjectAuditInvestHighlightDto);
        projectMatchInfoMap.put("融资需求", collectProjectAuditFinancingDto);
        projectMatchInfoMap.put("融资历史", collectProjectAuditHistoryFinancingDtoList);
        projectMatchInfoMap.put("团队成员", collectProjectAuditTeamDto);

        result.setStatus(200);
        result.setMessage("success");
        result.setData(projectMatchInfoMap);
        return result;
    }
}
