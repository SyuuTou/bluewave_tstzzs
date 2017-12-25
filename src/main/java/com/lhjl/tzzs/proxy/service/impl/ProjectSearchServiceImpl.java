package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ProjectReqDto;
import com.lhjl.tzzs.proxy.dto.ProjectResDto;
import com.lhjl.tzzs.proxy.mapper.ProjectsMapper;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.ProjectSearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("projectSearchService")
public class ProjectSearchServiceImpl extends GenericService implements ProjectSearchService {

    @Autowired
    private ProjectsMapper projectsMapper;

    @Value("${statistics.beginTime}")
    private String beginTime;

    @Value("${statistics.endTime}")
    private String endTime;

    @Transactional(readOnly = true)
    @Override
    public CommonDto<List<ProjectResDto>> projectFilter(ProjectReqDto reqDto) {


        if (StringUtils.isNotEmpty(reqDto.getCity())){
            reqDto.setCity("'"+reqDto.getCity().replace(",","','")+"'");
        }

        if (StringUtils.isNotEmpty(reqDto.getSegmentation())){
            reqDto.setSegmentation("'"+reqDto.getSegmentation().replace(",","','")+"'");
        }

        if (StringUtils.isNotEmpty(reqDto.getEdus())){
            reqDto.setEdus("'"+reqDto.getEdus().replace(",","','")+"'");
        }

        if (StringUtils.isNotEmpty(reqDto.getWorks())){
            reqDto.setWorks("'"+reqDto.getWorks().replace(",","','")+"'");
        }

        if (StringUtils.isNotEmpty(reqDto.getStage())){
            reqDto.setStage("'"+reqDto.getStage().replace(",","','")+"'");
        }

        reqDto.setBeginTime(beginTime);
        reqDto.setEndTime(endTime);

        reqDto.setOffset((reqDto.getPageNo()-1)*reqDto.getPageSize());
        List<ProjectResDto> projectResDtos = projectsMapper.projectFilter(reqDto);


        return new CommonDto<>(projectResDtos,"success", 200);
    }

    @Transactional(readOnly = true)
    @Override
    public CommonDto<List<ProjectResDto>> projectFilterOrderByFinancing(ProjectReqDto reqDto) {

        if (StringUtils.isNotEmpty(reqDto.getCity())){
            reqDto.setCity("'"+reqDto.getCity().replace(",","','")+"'");
        }

        if (StringUtils.isNotEmpty(reqDto.getSegmentation())){
            reqDto.setSegmentation("'"+reqDto.getSegmentation().replace(",","','")+"'");
        }

        if (StringUtils.isNotEmpty(reqDto.getEdus())){
            reqDto.setEdus("'"+reqDto.getEdus().replace(",","','")+"'");
        }

        if (StringUtils.isNotEmpty(reqDto.getWorks())){
            reqDto.setWorks("'"+reqDto.getWorks().replace(",","','")+"'");
        }

        if (StringUtils.isNotEmpty(reqDto.getStage())){
            reqDto.setStage("'"+reqDto.getStage().replace(",","','")+"'");
        }

        reqDto.setBeginTime(beginTime);
        reqDto.setEndTime(endTime);

        reqDto.setOffset((reqDto.getPageNo()-1)*reqDto.getPageSize());
        List<ProjectResDto> projectResDtos = new ArrayList<>();
        if (reqDto.getFinancingRecently() == 1){
            projectResDtos = projectsMapper.projectFilterTwo(reqDto);
        }else {
            projectResDtos = projectsMapper.projectFilterOne(reqDto);
        }


        return new CommonDto<>(projectResDtos,"success", 200);
    }
}
