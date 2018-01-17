package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.github.pagehelper.PageRowBounds;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.CommonTotal;
import com.lhjl.tzzs.proxy.dto.EventDto;
import com.lhjl.tzzs.proxy.dto.bluewave.ReportReqBody;
import com.lhjl.tzzs.proxy.mapper.ReportMapper;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl extends GenericService implements ReportService {


    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private ReportColumnService columnService;
    @Autowired
    private ReportSegmentationService segmentationService;
    @Autowired
    private ReportLabelService labelService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${event.trigger.url}")
    private String eventUrl;


    @Transactional(readOnly = true)
    @Override
    public CommonDto<List<Report>> queryReport(Integer appId, ReportReqBody reqBody) {

        CommonTotal<List<Report>> result = new CommonTotal<>();

        Report report = new Report();
        report.setComments(reqBody.getComments());
        report.setContent(reqBody.getContent());
        report.setCoverUrl(reqBody.getCoverUrl());
        report.setCreater(reqBody.getCreater());
        report.setFromRul(reqBody.getFromRul());
        report.setId(reqBody.getId());
        report.setSourceTextUrl(reqBody.getSourceTextUrl());
        report.setStatus(reqBody.getStatus());
        report.setSubTitle(reqBody.getSubTitle());
        report.setTitle(reqBody.getTitle());
        report.setWeightingFactor(reqBody.getWeightingFactor());

        int offset = (reqBody.getPageNo() - 1) * reqBody.getPageSize();
        int limit = reqBody.getPageSize();

        PageRowBounds rowBounds = new PageRowBounds(offset, limit);
        List<Report> list = reportMapper.selectByRowBounds(report, new RowBounds(offset, limit));
        result.setData(list);
        result.setMessage("success");
        result.setStatus(200);
        result.setTotal(rowBounds.getTotal());


        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public CommonDto<Report> getReportById(Integer appId, Integer id) {
        CommonDto<Report> result = new CommonDto<>();
        result.setData(reportMapper.selectByPrimaryKey(id));
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    @Transactional
    @Override
    public CommonDto<String> saveOrUpdate(Integer appId, ReportReqBody reqBody) {
       
    	
    	CommonDto<String> result = new CommonDto<>();

        Report report = new Report();
        report.setComments(reqBody.getComments());
        report.setContent(reqBody.getContent());
        report.setCoverUrl(reqBody.getCoverUrl());
        report.setCreater(reqBody.getCreater());
        report.setFromRul(reqBody.getFromRul());
        report.setId(reqBody.getId());
        report.setSourceTextUrl(reqBody.getSourceTextUrl());
        report.setStatus(reqBody.getStatus());
        report.setSubTitle(reqBody.getSubTitle());
        report.setTitle(reqBody.getTitle());
        report.setWeightingFactor(reqBody.getWeightingFactor());
        report.setCreater(reqBody.getCreater());
        Integer num = null;
        if (null == report.getId()){
            num = reportMapper.insert(report);
            this.sendNewsEvent(reqBody.getCreater(),num,reqBody.getColumns());
        }else{
            num = reportMapper.updateByPrimaryKeySelective(report);
        }

        List<MetaColumn> columns = reqBody.getColumns();
        List<Integer> segmentations = reqBody.getSegmentations();
        List<String> labels = reqBody.getReportLabels();

       ReportColumn metaColumn =new ReportColumn();
        metaColumn.setReportId(report.getId());

        columnService.deleteAll(report.getId());
        for (MetaColumn column : columns){
            metaColumn.setColumnId(column.getId());
            columnService.save(metaColumn);
        }

        ReportSegmentation reportSegmentation = new ReportSegmentation();
        reportSegmentation.setReportId(report.getId());

        segmentationService.deleteAll(report.getId());
        for (Integer segmentation : segmentations){
            reportSegmentation.setSegmentationId(segmentation);
            segmentationService.save(reportSegmentation);
        }

        ReportLabel reportLabel = new ReportLabel();
        reportLabel.setReportId(report.getId());

        labelService.deleteAll(report.getId());
        for (String lablel : labels){
            reportLabel.setName(lablel);
            labelService.save(reportLabel);
        }

        result.setMessage("success");
        result.setStatus(200);
        result.setData(String.valueOf(num));

        return result;
    }

    private void sendNewsEvent(String creater, Integer projectId, List<MetaColumn> columnList) {
        EventDto eventDto = new EventDto();
        List<Integer> projectIds = new ArrayList<>();
        projectIds.add(projectId);
        eventDto.setFromUser(creater);
        eventDto.setProjectIds(projectIds);
        eventDto.setColumnList(columnList);
        eventDto.setEventType("NEWS");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EventDto> entity = new HttpEntity<>(eventDto, headers);
        HttpEntity<CommonDto<String>> investors =  restTemplate.exchange(eventUrl+"/trigger/event", HttpMethod.POST,entity,new ParameterizedTypeReference<CommonDto<String>>(){} );
    }
}
