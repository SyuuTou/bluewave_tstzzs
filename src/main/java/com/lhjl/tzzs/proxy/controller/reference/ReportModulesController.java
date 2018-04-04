package com.lhjl.tzzs.proxy.controller.reference;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.ReportModules;
import com.lhjl.tzzs.proxy.model.ReportModulesFiles;
import com.lhjl.tzzs.proxy.service.reference.ReportModulesService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ReportModulesController extends GenericController {

    @Resource(name = "reportModulesService")
    private ReportModulesService reportModulesService;

    /**
     * 获取模块列表及相关文件列表
     * @param appId
     * @param reportId
     * @return
     */
    @GetMapping("v{appId}/reprot/modules/list/{reportId}")
    public CommonDto<List<ReportModules>> queryReportModules(@PathVariable Integer appId, @PathVariable Integer reportId){
        CommonDto<List<ReportModules>> result = null;

        try {
            result = reportModulesService.queryReportModulesList(appId,reportId);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
            return new CommonDto<>(null,"failed", 500);
        }

        return result;
    }

    @PostMapping("v{appId}/report/modules/{reportId}")
    public CommonDto<String> createReportModules(@PathVariable Integer reportId, @RequestBody ReportModules reportModules, @PathVariable Integer appId){
        CommonDto<String> result = null;

        try {
            reportModules.setReportId(reportId);
            result = reportModulesService.createReportModules(appId,reportModules);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
            return new CommonDto<>(null, "failed", 500);
        }

        return result;
    }

    @DeleteMapping("v{appId}/report/modules/{reportModulesId}")
    public CommonDto<String> deleteReportModules(@PathVariable Integer appId, @PathVariable Integer reportModulesId){
        CommonDto<String> result = null;

        try {
            result = reportModulesService.deleteReportModulesById(appId, reportModulesId);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(), e.fillInStackTrace());
            return new CommonDto<>(null, "failed", 500);
        }

        return  result;
    }

    @GetMapping("v{appId}/report/modules/files/{reporModulesId}")
    public CommonDto<List<ReportModulesFiles>> queryReportModulesFiles(@PathVariable Integer appId, @PathVariable Integer reportModulesId){
        CommonDto<List<ReportModulesFiles>> result = null;

        try {
            result = reportModulesService.queryReportModulesFilesList(appId, reportModulesId);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            return new CommonDto<>(null, "failed", 500);
        }

        return result;
    }

    @DeleteMapping("v{appId}/report/modules/files/{reportModulesFileId}")
    public CommonDto<String> deleteReportModulesFiles(@PathVariable Integer appId, @PathVariable Integer reportModulesFileId){
        CommonDto<String> result = null;

        try {
            result = reportModulesService.deleteReportModulesFilesById(appId, reportModulesFileId);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            return new CommonDto<>(null, "failed", 500);
        }

        return result;
    }

    @PostMapping("v{appId}/report/{reportModulesId}/files/{reportModulesFileId}")
    public CommonDto<String> uploadFile(@PathVariable Integer appId, @PathVariable Integer reportModulesId, @PathVariable Integer reportModulesFileId, @RequestParam("file") MultipartFile file){
        CommonDto<String> result = null;

        try {
            result = reportModulesService.saveOrUpdateReportModulesFile(appId, reportModulesId, reportModulesFileId, file);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            return new CommonDto<>(null, "failed", 500);
        }

        return result;
    }



}
