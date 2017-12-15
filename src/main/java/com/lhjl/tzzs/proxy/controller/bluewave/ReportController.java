package com.lhjl.tzzs.proxy.controller.bluewave;


import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.bluewave.ReportReqBody;
import com.lhjl.tzzs.proxy.model.MetaColumn;
import com.lhjl.tzzs.proxy.model.MetaSegmentation;
import com.lhjl.tzzs.proxy.model.Report;
import com.lhjl.tzzs.proxy.service.bluewave.ColumnService;
import com.lhjl.tzzs.proxy.service.bluewave.ReportService;
import com.lhjl.tzzs.proxy.service.bluewave.SegmentationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    @Resource
    private ColumnService columnService;
    @Resource
    private SegmentationService segmentationService;
    @Resource
    private ReportService reportService;

    @GetMapping("columns")
    public CommonDto<List<MetaColumn>> columnQuery(){
        CommonDto<List<MetaColumn>> result = null;

        try {
            result = columnService.queryAll();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<>();
            result.setData(null);
            result.setStatus(500);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @GetMapping("segmentations")
    public CommonDto<List<MetaSegmentation>> segmentationQuery(){
        CommonDto<List<MetaSegmentation>> result = null;

        try {
            result = segmentationService.queryAll();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<>();
            result.setData(null);
            result.setStatus(500);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("report/list")
    public CommonDto<List<Report>> reportList(@RequestBody ReportReqBody reqBody){
        CommonDto<List<Report>> result = null;

        result = reportService.queryReport(reqBody);

        return result;

    }

    @GetMapping("report/id")
    public CommonDto<Report> reportById(Integer id){
        CommonDto<Report> result = null;

        result = reportService.getReportById(id);

        return result;
    }

    @PutMapping("report")
    public CommonDto<String> reportSaveOrUpdate(@RequestBody ReportReqBody reqBody ){

        CommonDto<String> result = null;

        try {
            result = reportService.saveOrUpdate(reqBody);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<>();
            result.setStatus(500);
            result.setMessage("服务器繁忙，请售后再试。");
        }

        return  result;

    }

}
