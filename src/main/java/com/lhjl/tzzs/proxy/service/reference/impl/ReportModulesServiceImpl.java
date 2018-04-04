package com.lhjl.tzzs.proxy.service.reference.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ReportModulesFilesLabelsMapper;
import com.lhjl.tzzs.proxy.mapper.ReportModulesFilesMapper;
import com.lhjl.tzzs.proxy.mapper.ReportModulesMapper;
import com.lhjl.tzzs.proxy.model.ReportModules;
import com.lhjl.tzzs.proxy.model.ReportModulesFiles;
import com.lhjl.tzzs.proxy.model.ReportModulesFilesLabels;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.reference.ReportModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service("reportModulesService")
public class ReportModulesServiceImpl extends GenericService implements ReportModulesService {

    @Autowired
    private ReportModulesMapper reportModulesMapper;
    @Autowired
    private ReportModulesFilesMapper reportModulesFilesMapper;
    @Autowired
    private ReportModulesFilesLabelsMapper reportModulesFilesLabelsMapper;

    @Override
    public CommonDto<List<ReportModules>> queryReportModulesList(Integer appId, Integer reportId) {
        ReportModules reportModules = new ReportModules();
        reportModules.setReportId(reportId);

        List<ReportModules> reportModulesList = reportModulesMapper.select(reportModules);
        reportModulesList.forEach(item -> {
            item.setReportModulesFilesList(queryReportModulesFilesList(appId,item.getId()).getData());
        });

        return new CommonDto<>(reportModulesList,"success", 200);
    }

    @Override
    public CommonDto<String> createReportModules(Integer appId, ReportModules reportModules) {
        try {
            reportModulesMapper.insert(reportModules);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            return new CommonDto<>(null,"failed",5002);
        }
        return new CommonDto<>(String.valueOf(reportModules.getId()), "success", 200);
    }

    @Override
    public CommonDto<String> deleteReportModulesById(Integer appId, Integer reportModulesId) {

        reportModulesMapper.deleteByPrimaryKey(reportModulesId);

        return new CommonDto<>("ok", "success", 200);
    }

    @Override
    public CommonDto<List<ReportModulesFiles>> queryReportModulesFilesList(Integer appId, Integer reporModulesId) {

        ReportModulesFiles reportModulesFiles = new ReportModulesFiles();
        reportModulesFiles.setReportModulesId(reporModulesId);

        List<ReportModulesFiles> reportModulesFilesList = reportModulesFilesMapper.select(reportModulesFiles);
        reportModulesFilesList.forEach((item) -> {
            ReportModulesFilesLabels query = new ReportModulesFilesLabels();
            query.setReportModulesFilesId(item.getId());
            item.setReportModulesFilesLabels(reportModulesFilesLabelsMapper.select(query));
        });

        return new CommonDto<>(reportModulesFilesList, "succeess", 200);
    }

    @Override
    public CommonDto<String> deleteReportModulesFilesById(Integer appId, Integer reportModulesFileId) {
        reportModulesFilesMapper.deleteByPrimaryKey(reportModulesFileId);
        ReportModulesFilesLabels query  = new ReportModulesFilesLabels();
        query.setReportModulesFilesId(reportModulesFileId);
        reportModulesFilesLabelsMapper.delete(query);
        return new CommonDto<>("ok","success", 200);
    }

    @Override
    public CommonDto<String> saveOrUpdateReportModulesFile(Integer appId, Integer reportModulesId, Integer reportModulesFileId, MultipartFile file) {
        return null;
    }
}
