package com.lhjl.tzzs.proxy.controller.bluewave;


import com.lhjl.tzzs.proxy.controller.weixin.GenericController;
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
public class ReportController extends GenericController {


    @Resource
    private ColumnService columnService;
    @Resource
    private SegmentationService segmentationService;
    @Resource
    private ReportService reportService;

    @GetMapping("/v{appid}/columns")
    public CommonDto<List<MetaColumn>> columnQuery(@PathVariable("appid") Integer appId ){
        CommonDto<List<MetaColumn>> result = null;

        try {
            result = columnService.queryAll(appId);
        } catch (Exception e) {
            this.logger.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<>();
            result.setData(null);
            result.setStatus(500);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("/v{appid}/columns")
    public CommonDto<String> columnSaveOrUpdate(@PathVariable("appid") Integer appId ,@RequestBody MetaColumn column){
        CommonDto<String> result = null;

        result = columnService.saveOrUpdate(appId,column);

        return result;
    }

    @PostMapping("/v{appid}/columns/add")
    public CommonDto<String> columnSave(@PathVariable("appid") Integer appId ,@RequestBody MetaColumn column){
        CommonDto<String> result = null;

        try {
            result = columnService.save(appId,column);
        } catch (Exception e) {
            this.logger.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<>();
            result.setStatus(500);
            result.setMessage("服务器繁忙，请售后再试。");
        }

        return result;
    }

    @GetMapping("/v{appid}/segmentations")
    public CommonDto<List<MetaSegmentation>> segmentationQuery(@PathVariable("appid") Integer appId ){
        CommonDto<List<MetaSegmentation>> result = null;

        try {
            result = segmentationService.queryAll(appId);
        } catch (Exception e) {
            this.logger.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<>();
            result.setData(null);
            result.setStatus(500);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("/v{appid}/report/list")
    public CommonDto<List<Report>> reportList(@PathVariable("appid") Integer appId ,@RequestBody ReportReqBody reqBody){
        CommonDto<List<Report>> result = null;

        try {
            result = reportService.queryReport(appId,reqBody);
        } catch (Exception e) {
            this.logger.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<>();
            result.setStatus(500);
            result.setMessage("服务器繁忙，请售后再试。");
        }

        return result;

    }

    @GetMapping("/v{appid}/report/{id}")
    public CommonDto<Report> reportById(@PathVariable("appid") Integer appId ,@PathVariable("id") Integer id){
        CommonDto<Report> result = null;

        try {
            result = reportService.getReportById(appId,id);
        } catch (Exception e) {
            this.logger.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<>();
            result.setStatus(500);
            result.setMessage("服务器繁忙，请售后再试。");
        }

        return result;
    }

    @PutMapping("/v{appid}/report")
    public CommonDto<String> reportSaveOrUpdate(@PathVariable("appid") Integer appId ,@RequestBody ReportReqBody reqBody ){

        CommonDto<String> result = null;

        try {
            result = reportService.saveOrUpdate(appId,reqBody);
        } catch (Exception e) {
            this.logger.error(e.getMessage(),e.fillInStackTrace());
            result = new CommonDto<>();
            result.setStatus(500);
            result.setMessage("服务器繁忙，请售后再试。");
        }

        return  result;

    }

}
