package com.lhjl.tzzs.proxy.service.bluewave.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.bluewave.ReportReqBody;
import com.lhjl.tzzs.proxy.mapper.MetaColumnMapper;
import com.lhjl.tzzs.proxy.mapper.MetaSegmentationMapper;
import com.lhjl.tzzs.proxy.mapper.ReportColumnMapper;
import com.lhjl.tzzs.proxy.mapper.ReportMapper;
import com.lhjl.tzzs.proxy.model.Report;
import com.lhjl.tzzs.proxy.model.ReportColumn;
import com.lhjl.tzzs.proxy.model.ReportSegmentation;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.bluewave.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public CommonDto<List<Report>> queryReport(ReportReqBody reqBody) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public CommonDto<Report> getReportById(Integer id) {
        CommonDto<Report> result = new CommonDto<>();
        result.setData(reportMapper.selectByPrimaryKey(id));
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    @Transactional
    @Override
    public CommonDto<String> saveOrUpdate(ReportReqBody reqBody) {
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
        Integer num = null;
        if (null == report.getId()){
            num = reportMapper.insert(report);
        }else{
            num = reportMapper.updateByPrimaryKeySelective(report);
        }

        List<Integer> columns = reqBody.getColumns();
        List<Integer> segmentations = reqBody.getSegmentations();
        List<String> labels = reqBody.getReportLabels();

       ReportColumn metaColumn =new ReportColumn();
        metaColumn.setReportId(report.getId());


        for (Integer column : columns){
            metaColumn.setColumnId(column);
            columnService.save(metaColumn);
        }

//        ReportSegmentation



        result.setMessage("success");
        result.setStatus(200);
        result.setData(String.valueOf(num));

        return result;
    }
}
